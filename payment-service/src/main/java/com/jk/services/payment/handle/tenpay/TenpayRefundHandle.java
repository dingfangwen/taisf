package com.jk.services.payment.handle.tenpay;

import com.jk.framework.base.utils.*;
import com.jk.framework.common.utils.CloseableHttpsUtil;
import com.jk.services.payment.constant.PayConstants;
import com.jk.services.payment.constant.PaymentConstant;
import com.jk.services.payment.constant.TenRefundEnum;
import com.jk.services.payment.entity.PayDetailEntity;
import com.jk.services.payment.entity.PayEntity;
import com.jk.services.payment.entity.PayInfo;
import com.jk.services.payment.exception.PaymentException;
import com.jk.services.payment.handle.PaymentHandle;
import com.jk.services.payment.utils.PayKeyUtil;
import com.jk.services.payment.utils.PayUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * <p>微信申请退款</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/6/13.
 * @version 1.0
 * @since 1.0
 */

@Service
public class TenpayRefundHandle extends PaymentHandle {

    private static final Logger LOGGER = LoggerFactory.getLogger(TenpayAppHandle.class);

    @Override
    public String getRequestUrl() {
        return "https://api.mch.weixin.qq.com/secapi/pay/refund";
    }

    @Override
    public RequestMethod getRequestMethod() {
        return RequestMethod.post;
    }

    @Override
    public String getRequestCharset() {
        return "utf-8";
    }

    /**
     * 保存请求数据
     * @param payInfo
     * @throws Exception
     */
    public String doRefundBusiness(PayInfo payInfo) throws Exception {
        /**
         * 保存业务数据
         */
        this.doBusiness(payInfo);

        /**
         * 处理退款信息
         */
        return this.processRefund(payInfo);
    }


    /**
     * 处理退款信息
     * @param payInfo
     */
    private String processRefund(PayInfo payInfo) throws Exception {
        try{
            // 根据biz_id 查找 付款payinfo
            Map<String, Object> queryMap  = new HashMap<String, Object>();
            queryMap.put("resId", payInfo.getOrgInSerialNo());
            queryMap.put("bizId", payInfo.getOrgBizId());
            PayEntity hasPay = this.payDao.selectByResIdAndBizId(queryMap);
            if(hasPay == null){
                LOGGER.error("没有获取payId=" + payInfo.getPayId() + "的相关pay支付信息.");
                return PayConstants.FAIL;
            }

            PayDetailEntity payDetail = payDetailDao.selectByPayId(hasPay.getId());
            if (payDetail == null) {
                LOGGER.error("没有获取payId=" + payInfo.getPayId() + "的相关payDetail信息.");
                return PayConstants.FAIL;
            }
            if(StringUtils.isEmpty(payDetail.getOutSerialNo())){
                LOGGER.error("没有获取payId=" + payInfo.getPayId() + "的微信退款单号refund_id为空.");
                return PayConstants.FAIL;
            }

            // 组装请求参数
            SortedMap<String, Object> param = getParameterMap(payInfo,payDetail);
            LOGGER.info("包装请求参数完成,par:{}",JsonEntityTransform.Object2Json(param));
            String content = XmlUtils.getRequestXml(param);
            // 发送退款信息
            String result = this.sendPayInfo(content,payInfo,payInfo.getCaFileName(), Check.NuNStr(payInfo.getCaPassword())?payInfo.getPartner():payInfo.getCaPassword());
            processReturnMsg(result, payInfo);
            LOGGER.info("处理请求支付ID={}任务完成！", payInfo.getPayId());
            return  result;
        }catch (Exception e){
            LOGGER.error("发送微信支付,ID="+payInfo.getPayId()+",异常"+e.getMessage(),e);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 微信的重试机制
     */
    public void doTask() {
        try {
            LOGGER.info("微信退款查询任务开始：{}", DateUtil.timestampFormat(new Date()));
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", "tenpayRefundHandle");
            List<PayInfo> list = payDao.queryBankListByCode(map);
            if (Check.NuNCollection(list)){
                LOGGER.info("待微信退款的数据0：{}");
                return;
            }
            for(PayInfo payInfo : list){

                PayDetailEntity payDetail = payDetailDao.selectByPayId(payInfo.getPayId());
                if (payDetail == null) {
                    LOGGER.error("没有获取payId=" + payInfo.getPayId() + "的相关payDetail信息.");
                    continue ;
                }
                if(StringUtils.isEmpty(payDetail.getOutSerialNo())){
                    LOGGER.error("没有获取payId=" + payInfo.getPayId() + "的微信退款单号refund_id为空.");
                    continue ;
                }
                String reuqestXml = XmlUtils.getRequestXml(getQueryParamMap(payInfo,payDetail));
                LOGGER.info("发送请求:"+reuqestXml);
                //调用退款
                String result = CloseableHttpsUtil.sendPost(null,getQueryUrl(),reuqestXml,null);
                LOGGER.info("返回结果:"+XmlUtils.formatXml(result));
                processQueryResult(result,payInfo);
            }
            LOGGER.info("微信退款查询任务结束：{}", DateUtil.timestampFormat(new Date()));
        }catch (Exception e){
            LOGGER.error("查询退款失败,",e);
        }
    }

    /**
     * @param result
     * @param payInfo
     * @throws Exception
     */
    private void processQueryResult(String result,PayInfo payInfo) throws Exception {
        try {
            LOGGER.info("method={},_{}","processQueryResult","微信退款处理查询结果开始.");
            Document document = DocumentHelper.parseText(result);
            String returnCode = XmlUtils.getDocumentNode(document, "/xml/return_code");
            String returMsg = XmlUtils.getDocumentNode(document, "/xml/return_msg");
            LOGGER.info("returnCode={}   , returMsg= {}" ,returnCode,returMsg);
            if(!"SUCCESS".equals(returnCode)){
                //保存当前的处理信息
                LOGGER_PAY_LOG.error("落地微信退款失败日志: payId:{}, status:{} result:{}",payInfo.getPayId(), PayConstants.PayStatus.STATUS_40000.getCodeStr(),result);
            }else{
                String result_code = XmlUtils.getDocumentNode(document, "/xml/result_code");
                String refund_status_0 = XmlUtils.getDocumentNode(document, "/xml/refund_status_0");
                LOGGER.info("result_code={}   , refund_status_0={}" ,result_code,refund_status_0);
                // SUCCESS—退款成功
                String state = PayConstants.PayStatus.STATUS_SUCCESS.getCodeStr();
                String state_desc = PayConstants.PayStatus.STATUS_SUCCESS.getName();

                /**
                 * FAIL—退款失败
                 * NOTSURE—未确定，需要商户原退款单号重新发起
                 * CHANGE—转入代发，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，
                 *                  需要商户人工干预，通过线下或者财付通转账的方式进行退款。
                 */
                if(TenRefundEnum.FAIL.getKey().equals(refund_status_0)
                        ||TenRefundEnum.NOTSURE.getKey().equals(refund_status_0)
                        ||TenRefundEnum.CHANGE.getKey().equals(refund_status_0)){
                    state = PayConstants.PayStatus.STATUS_40000.getCodeStr();
                    state_desc = PayConstants.PayStatus.STATUS_40000.getName() + "，微信返回：["+ TenRefundEnum.getValue(refund_status_0)+"]";

                }else if(TenRefundEnum.PROCESSING.getKey().equals(refund_status_0)){
                    //保存当前的处理信息
                    LOGGER_PAY_LOG.error("落地微信退款成功日志: payId:{}, status:{} result:{}",payInfo.getPayId(), PayConstants.PayStatus.STATUS_40000.getCodeStr(),result);
                    return;
                }

                savePayAndLog(payInfo.getPayId(),
                        state,
                        state_desc,
                        PayConstants.RESPONSE,
                        result);
                PayDetailEntity payDetail = new PayDetailEntity();
                payDetail.setStatus(state);
                payDetail.setStatusDesc(state_desc);
                payDetail.setPayId(payInfo.getPayId());
                payDetailDao.update(payDetail);
                LOGGER.info("method={},_{}","processQueryResult","微信退款处理查询结果结束.");
            }
        }catch (Exception e){
            throw new Exception("处理查询结果失败,"+e.getMessage());
        }
    }


    /**
     * 退款请求查询url
     * @return
     */
    private String getQueryUrl(){
        return "https://api.mch.weixin.qq.com/pay/refundquery";
    }

    /**
     * 退款请求查询输入
     * @return
     * @throws Exception
     */
    private SortedMap<String,Object>  getQueryParamMap(PayInfo payInfo,PayDetailEntity payDetail) throws Exception {
        try {
            SortedMap<String,Object> parameters = new TreeMap<String,Object>();
            // 公众账号ID
            parameters.put("appid", payInfo.getAccount());
            // 商户号
            parameters.put("mch_id", payInfo.getPartner());
            // 随机字符串
            parameters.put("nonce_str", "tenpay" + System.currentTimeMillis());
            // 微信订单号
            parameters.put("transaction_id", payDetail.getInSerialNo());
            // 商户订单号
            parameters.put("out_trade_no", payDetail.getPayId());
            // 商户退款单号
            parameters.put("out_refund_no", payInfo.getPayId());
            // 微信退款单号
            parameters.put("refund_id", payDetail.getOutSerialNo()) ;
            parameters.put("sign", SignUtils.md5Sign("&key="+payInfo.getPartnerKey(),parameters).toUpperCase());
            return parameters;
        }catch (Exception e){
            throw new Exception("组装请求参数失败,"+e.getMessage());
        }
    }

    /**
     * 保存业务数据
     * @param payInfo
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void doBusiness(PayInfo payInfo) throws Exception {
        try {
            //微信退款
            payInfo.setCode(PayConstants.HandleType.Handle_tenpay_Refund.getCode());

            PayEntity has = payDao.selectByInfoWithoutMoney(payInfo);
            if(has != null){
                payInfo.setPayId(has.getId());
                return;
            }

            LOGGER.info("业务ID:{} , 保存微信退款信息:{}",payInfo.getBizId(), JsonEntityTransform.Object2Json(payInfo));
            PayEntity pay = new PayEntity();
            pay.setContent(payInfo.getContent());
            pay.setAmount(payInfo.getAmount());
            pay.setBizId(payInfo.getBizId());
            pay.setCode(payInfo.getCode());
            pay.setType(PayConstants.OpType.TYPE_REFUND.getCode());//退款
            pay.setProductCode(payInfo.getProductCode());
            pay.setReturnUrl(payInfo.getReturnUrl());
            pay.setNotifyUrl(payInfo.getNotifyUrl());
            pay.setStatus(PayConstants.PayStatus.STATUS_10000.getCodeStr());
            pay.setStatusDesc(PayConstants.PayStatus.STATUS_10000.getName());
            pay.setResId(payInfo.getOrgInSerialNo());
            pay.setReqId(payInfo.getOrgBizId());
            pay.setAccountId(payInfo.getAccountId());
            payDao.insert(pay);

            //支付明细
            PayDetailEntity payDetail =new PayDetailEntity();
            payDetail.setAmount(payInfo.getAmount());
            payDetail.setBizId(payInfo.getOrgBizId());
            payDetail.setBizName(payInfo.getContent());
            payDetail.setStatus(PayConstants.PayStatus.STATUS_10000.getCodeStr());
            payDetail.setStatusDesc(PayConstants.PayStatus.STATUS_10000.getName());
            payDetail.setPayId(pay.getId());
            payDetail.setInSerialNo(payInfo.getOrgInSerialNo());
            payDetailDao.insert(payDetail);
            payInfo.setPayId(pay.getId());

            LOGGER.info("支付Id= {} ,保存微信退款信息成功！", payInfo.getPayId());

        }catch (Exception e){
            LOGGER.error("保存支付ID="+payInfo.getPayId()+",异常"+e.getMessage(),e);
            throw new Exception("保存退款请求结果失败,"+e.getMessage());
        }
    }

    /**
     * 处理退款结果
     * @param result
     * @param payInfo
     * @throws Exception
     */
    private void processReturnMsg(String result,PayInfo payInfo) throws Exception {
        try {
            LOGGER.info("处理退款结果开始.");
            Document document = DocumentHelper.parseText(result);
            String retcode = XmlUtils.getDocumentNode(document, "/xml/return_code");
            LOGGER.info("retcode=" + retcode);
            String retmsg = XmlUtils.getDocumentNode(document, "/xml/return_msg");
            LOGGER.info("retmsg=" + retmsg);
            String err_code = XmlUtils.getDocumentNode(document, "/xml/err_code");
            LOGGER.info("err_code=" + err_code);
            String err_code_des = XmlUtils.getDocumentNode(document, "/xml/err_code_des");
            LOGGER.info("err_code_des=" + err_code_des);
            String out_refund_no = XmlUtils.getDocumentNode(document, "/xml/out_refund_no");
            LOGGER.info("out_refund_no=" + out_refund_no);
            String refund_id = XmlUtils.getDocumentNode(document, "/xml/refund_id");
            LOGGER.info("refund_id=" + refund_id);

            if ("SUCCESS".equals(retcode)
                    && "OK".equals(retmsg)
                    && StringUtils.isEmpty(err_code)
                    && StringUtils.isEmpty(err_code_des)){

                // 更新支付信息和新增日志
                savePayAndLog(payInfo.getPayId(),
                        PayConstants.PayStatus.STATUS_SUCCESS.getCodeStr(),
                        PayConstants.PayStatus.STATUS_SUCCESS.getName(),
                        PayConstants.RESPONSE,
                        result,
                        refund_id);

                PayDetailEntity payDetail = new PayDetailEntity();
                payDetail.setStatus(PayConstants.PayStatus.STATUS_SUCCESS.getCodeStr());
                payDetail.setStatusDesc(PayConstants.PayStatus.STATUS_SUCCESS.getName());
                payDetail.setOutSerialNo(refund_id);
                payDetail.setPayId(payInfo.getPayId());
                payDetailDao.update(payDetail);
            }else{
                String msg = PayConstants.PayStatus.STATUS_40000.getName() + ":err_code=" + err_code + ",err_code_des=" + err_code_des;
                savePayAndLog(payInfo.getPayId(),
                        PayConstants.PayStatus.STATUS_40000.getCodeStr(),
                        msg,
                        PayConstants.RESPONSE,
                        result);
                PayDetailEntity payDetail = new PayDetailEntity();
                payDetail.setStatus(PayConstants.PayStatus.STATUS_40000.getCodeStr());
                payDetail.setStatusDesc(msg);
                payDetail.setPayId(payInfo.getPayId());
                payDetailDao.update(payDetail);
            }
            LOGGER.info("处理退款结果结束.");
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            throw new Exception("处理退款结果失败,"+e.getMessage());
        }
    }
    /**
     *  参考官方文档：https://pay.weixin.qq.com/wiki/doc/api/app.php?chapter=9_4&index=6
     * @param payInfo
     * @param payDetail
     * @return
     */
    private SortedMap<String, Object> getParameterMap(PayInfo payInfo,PayDetailEntity payDetail) throws Exception{
        try{
            SortedMap<String,Object> parameterMap = new TreeMap<String,Object>();
            // 公众账号ID
            parameterMap.put("appid", payInfo.getAccount());
            // 商户号
            parameterMap.put("mch_id", payInfo.getPartner());
            // 随机字符串
            parameterMap.put("nonce_str", "tenpay" + System.currentTimeMillis());
            // 微信订单号
            parameterMap.put("transaction_id", payDetail.getInSerialNo());
            // 商户订单号
//            parameterMap.put("out_trade_no", PayKeyUtil.getFakeKey(payPreKey,hasPay.getId()));
//            // 商户退款单号
//            parameterMap.put("out_refund_no", PayKeyUtil.getFakeKey(payPreKey,hasPay.getId()));

            parameterMap.put("out_trade_no", payDetail.getPayId());
            // 商户退款单号
            parameterMap.put("out_refund_no", payDetail.getPayId());

            // 总金额
            parameterMap.put("total_fee", payDetail.getAmount());
            // 退款金额
            parameterMap.put("refund_fee", payDetail.getAmount());
            // 操作员:操作员帐号, 默认为商户号
            parameterMap.put("op_user_id", payInfo.getPartner());
            // 签名
            parameterMap.put("sign", SignUtils.md5Sign("&key="+payInfo.getPartnerKey(),parameterMap).toUpperCase());
            return parameterMap;
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            throw new Exception("组装请求参数失败,"+e.getMessage());
        }
    }

    @Override
    public String returnHandle(PayInfo payInfo, String json) {
        return PayConstants.SUCCESS_STATUS;
    }

    /**
     * 获取当前的真实的支付信息
     * @param payInfo
     * @return
     */
    private PayEntity transPayInfo(PayInfo payInfo){
        Map<String, Object> queryMap  = new HashMap<String, Object>();
        queryMap.put("resId", payInfo.getOrgInSerialNo());
        queryMap.put("bizId", payInfo.getOrgBizId());
        PayEntity pay = this.payDao.selectByResIdAndBizId(queryMap);
        if(pay == null ){
            throw new PaymentException(PayConstants.EXCEPTION_STATUS, "查询支付信息出错! bizId="+payInfo.getOrgBizId()+",res_id="+payInfo.getOrgInSerialNo());
        }
        return  pay;
    }

    @Override
    public Map<String, Object> getParameterMap(PayInfo payInfo, HttpServletRequest request) {
        Map<String,Object> parameterMap = new TreeMap<String,Object>();
        // 公众账号ID
        parameterMap.put("appid", payInfo.getAccount());
        // 商户号
        parameterMap.put("mch_id", payInfo.getPartner());
        // 随机字符串
        parameterMap.put("nonce_str", "tenpay" + System.currentTimeMillis());
        // 商户订单号
        parameterMap.put("out_trade_no", payInfo.getPayId());
        // 总金额
        parameterMap.put("total_fee", payInfo.getAmount());
        // 退款金额
        parameterMap.put("refund_fee", payInfo.getAmount());
        // 操作员:操作员帐号, 默认为商户号
        parameterMap.put("op_user_id", payInfo.getPartner());
        return parameterMap;
    }


    /**
     * 调用微信的退款
     * @author afi
     * @param content
     * @param payInfo
     * @param caFileName
     * @param CaPass
     * @return
     * @throws Exception
     */
    public String sendPayInfo(String content,PayInfo payInfo,String caFileName,String CaPass) throws Exception{
		try {
			LOGGER.info("支付Id= {} ,发送微信退款请求参数：{}", payInfo.getPayId(), content);
            //获取证书
            SSLConnectionSocketFactory sslsf = PayUtil.getSslsf(PayUtil.getTemplate(caFileName),CaPass);
            //调用退款
            String result = CloseableHttpsUtil.sendPost(sslsf,this.getRequestUrl(),content,null);
            LOGGER_PAY_LOG.error("接收微信退款返回参数: payId:{}, status:{} result:{}",payInfo.getPayId(), PayConstants.PayStatus.STATUS_40000.getCodeStr(),result);
			return result;
		}catch (Exception e){
			throw new Exception("发送微信支付异常,支付Id:"+payInfo.getPayId()+",异常信息:"+e.getMessage());
		}
	}
    @Override
    public void verifyNotify(PayInfo payInfo, String json) {}

    @Override
    public String notifyHandle(PayInfo payInfo, String result) {
        try {
            LOGGER.info("微信退款切面:payInfo:{},result:{}",JsonEntityTransform.Object2Json(payInfo),result);
            Document document = DocumentHelper.parseText(result);
            String retcode = XmlUtils.getDocumentNode(document, "/xml/return_code");
            LOGGER.info("retcode=" + retcode);
            String retmsg = XmlUtils.getDocumentNode(document, "/xml/return_msg");
            LOGGER.info("retmsg=" + retmsg);
            String err_code = XmlUtils.getDocumentNode(document, "/xml/err_code");
            LOGGER.info("err_code=" + err_code);
            String err_code_des = XmlUtils.getDocumentNode(document, "/xml/err_code_des");
            LOGGER.info("err_code_des=" + err_code_des);
            String out_refund_no = XmlUtils.getDocumentNode(document, "/xml/out_refund_no");
            LOGGER.info("out_refund_no=" + out_refund_no);
            String refund_id = XmlUtils.getDocumentNode(document, "/xml/refund_id");
            LOGGER.info("refund_id=" + refund_id);

            if ("SUCCESS".equals(retcode)
                    && "OK".equals(retmsg)
                    && StringUtils.isEmpty(err_code)
                    && StringUtils.isEmpty(err_code_des)){
                LOGGER.info("微信退款切面 成功:payInfo:{},result:{}",JsonEntityTransform.Object2Json(payInfo),result);
                return PayConstants.SUCCESS;
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
        }
        LOGGER.info("微信退款切面 失败:payInfo:{},result:{}",JsonEntityTransform.Object2Json(payInfo),result);
        return PayConstants.FAIL;
    }
}
