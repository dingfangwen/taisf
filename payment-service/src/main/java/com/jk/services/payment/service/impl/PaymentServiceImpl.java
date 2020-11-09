package com.jk.services.payment.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.DesUtils;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.SignUtils;
import com.jk.framework.common.utils.CloseableHttpUtil;
import com.jk.services.log.PaymentLog;
import com.jk.services.payment.constant.PayConstants;
import com.jk.services.payment.constant.PaymentConstant;
import com.jk.services.payment.dao.PayAccountDao;
import com.jk.services.payment.dao.PayDao;
import com.jk.services.payment.dao.PayDetailDao;
import com.jk.services.payment.entity.*;
import com.jk.services.payment.exception.PaymentException;
import com.jk.services.payment.service.PaymentService;
import com.jk.services.payment.utils.PayKeyUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>支付的逻辑的实现</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/5/13.
 * @version 1.0
 * @since 1.0
 */
@Service
public class PaymentServiceImpl  extends PaymentConstant implements PaymentService  {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);





    @Autowired
    private PayDao payDao;

    @Autowired
    private PayDetailDao payDetailDao;

    @Autowired
    private PayAccountDao payAccountDao;

    /**
     * 更新
     * @author afi
     * @param pay
     * @return
     */
    @Override
    public int update(PayEntity pay) {
        return payDao.update(pay);
    }


    /**
     * 验证参数合法,并处理退款的逻辑
     * @param payInfo
     */
    @Override
    public String dealRefundParameter(PayInfo payInfo,DataTransferObject dto){
        String code = "";
        if(payInfo == null){
            dto.setErrorMsg("参数错误");
            return code;
        }
        if (Check.NuNStr(payInfo.getBizId())
                || Check.NuNStr(payInfo.getOrgBizId())
                || Check.NuNStr(payInfo.getNotifyUrl())
                || Check.NuNStr(payInfo.getOrgInSerialNo())
                || Check.NuNObj(payInfo.getAmount())
                ){
            dto.setErrorMsg("参数错误");
            return code;
        }

        PayEntity hasPay = payDao.selectByInfoHasPayBySerialNo(payInfo.getOrgInSerialNo());
        if (Check.NuNObj(hasPay)){
            dto.setErrorMsg("未获取支付信息");
            return code;
        }else {
            //同步业务线
            payInfo.setProductCode(hasPay.getProductCode());
        }
        if (!payInfo.getOrgBizId().equals(hasPay.getBizId())){
            dto.setErrorMsg("参数非法篡改");
            return code;
        }
        if(Check.NuNObj(hasPay.getAccountId())){
            dto.setErrorMsg("未获取账户信息");
            return code;
        }

        PayAccountEntity payAccount = payAccountDao.selectByPrimaryKey(hasPay.getAccountId());
        if(payAccount == null) {
            dto.setErrorMsg("code错误");
            return code;
        }else {
            code = payAccount.getCode();
        }
        //填充账户信息
        this.changePayInfo(payInfo,payAccount);
        return code;
    }
    /**
     * 校验参数信息,并拼装参数
     * @author afi
     * @param payInfo
     */
    @Override
    public void dealPayParameter(PayInfo payInfo,DataTransferObject dto) {
        if(payInfo == null){
            dto.setErrorMsg("参数错误");
            return;
        }
        try {
            Assert.hasText(payInfo.getCode(), "code不允许为空！");
            Assert.hasText(payInfo.getBizId(), "bizId不允许为空！");
            Assert.hasText(payInfo.getAmount()+"", "amount不允许为空！");
            Assert.hasText(payInfo.getContent(), "content不允许为空！");
            Assert.hasText(payInfo.getProductCode(), "productCode不允许为空！");
            Assert.hasText(payInfo.getNotifyUrl(), "notifyUrl不允许为空！");
        } catch (Exception e) {
            dto.setErrorMsg("参数错误");
            return;
        }
        Map<String, Object> queryMap = new HashMap<String, Object>();
            queryMap.put("code", payInfo.getCode());
        queryMap.put("productCode", payInfo.getProductCode());
        PayAccountEntity payAccount = payAccountDao.selectObject(queryMap);
        if(payAccount == null) {
            dto.setErrorMsg("code错误");
            return;
        }
        this.changePayInfo(payInfo,payAccount);
    }

    /**
     * 获取账号信息
     * @author afi
     * @param payInfo
     * @return
     */
    @Override
    public PayAccountEntity getPayAccount(PayInfo payInfo) {
        try {
            Assert.notNull(payInfo, "参数错误！");
            Assert.hasText(payInfo.getCode(), "code不允许为空！");
            Assert.hasText(payInfo.getProductCode(), "productCode不允许为空！");
        } catch (Exception e) {
            throw new PaymentException(PayConstants.EXCEPTION_STATUS, e.getMessage());
        }
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("code", payInfo.getCode());
        queryMap.put("productCode", payInfo.getProductCode());
        PayAccountEntity payAccount = payAccountDao.selectObject(queryMap);
        if(payAccount == null) {
            throw new PaymentException(PayConstants.EXCEPTION_STATUS, "code错误！");
        }
        this.changePayInfo(payInfo, payAccount);
        PayAccountEntity returnAccount = new PayAccountEntity();
        returnAccount.setAccount(payAccount.getAccount());
        returnAccount.setAccountName(payAccount.getAccountName());
        returnAccount.setCode(payAccount.getCode());
        returnAccount.setName(payAccount.getName());
        returnAccount.setProductCode(payAccount.getProductCode());
        return returnAccount;
    }


    /**
     * 填充账户信息
     * @author afi
     * @param payInfo
     * @param payAccount
     */
    private void changePayInfo(PayInfo payInfo,PayAccountEntity payAccount) {
        payInfo.setName(payAccount.getName());
        payInfo.setPartner(payAccount.getPartner());
        payInfo.setPartnerKey(payAccount.getPartnerKey());
        payInfo.setAccount(payAccount.getAccount());
        payInfo.setPassword(payAccount.getPassword());
        payInfo.setCodeKey(payAccount.getCodeKey());
        payInfo.setCaFileName(payAccount.getCaFileName());
        payInfo.setCaPassword(payAccount.getCaPassword());
        payInfo.setAccountId(payAccount.getId());
    }

    /**
     * 创建支付信息
     * @author afi
     * @param pay
     * @return
     */
    @Override
    public PayInfo createPayInfo(PayEntity pay) {
        PayInfo payInfo  =  new PayInfo();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("code", pay.getCode());
        queryMap.put("productCode", pay.getProductCode());
        PayAccountEntity payAccount = payAccountDao.selectObject(queryMap);
        if(payAccount == null) {
            throw new PaymentException(PayConstants.EXCEPTION_STATUS, "code错误:"+pay.getCode());
        }
        payInfo.setPartner(payAccount.getPartner());
        payInfo.setPartnerKey(payAccount.getPartnerKey());
        payInfo.setAccount(payAccount.getAccount());
        payInfo.setPassword(payAccount.getPassword());
        payInfo.setAmount(pay.getAmount());
        payInfo.setBizId(pay.getBizId());
        payInfo.setContent(pay.getContent());
        payInfo.setCode(pay.getCode());
        payInfo.setPayId(pay.getId());
        payInfo.setProductCode(pay.getProductCode());
        payInfo.setReturnUrl(pay.getReturnUrl());
        payInfo.setNotifyUrl(pay.getNotifyUrl());
        payInfo.setBatchNo(pay.getId()+"");
        return payInfo;
    }

    /**
     * 插入支付信息
     * @author afi
     * @param payInfo
     */
    @Override
    public void insertPay(PayInfo payInfo) {

        LOGGER.info("保存参数：payInfo:{}",JsonEntityTransform.Object2Json(payInfo));
        PayEntity pay = payDao.selectByInfo(payInfo);
        if(pay != null){
            /**
             * 如果当前订单已经支付成功 或者已经通知成功直接返回成功
             */
            if(PayConstants.PayStatus.STATUS_SUCCESS.getCodeStr().equals(pay.getStatus()) || PayConstants.PayStatus.STATUS_70000.getCodeStr().equals(pay.getStatus())){
                throw new PaymentException(PayConstants.EXCEPTION_STATUS, "此订单已操作成功!");
            }
            payInfo.setAmount(pay.getAmount());
            payInfo.setBizId(pay.getBizId());
            payInfo.setContent(pay.getContent());
            payInfo.setCode(pay.getCode());
            payInfo.setType(pay.getType());
            payInfo.setProductCode(pay.getProductCode());
            payInfo.setReturnUrl(pay.getReturnUrl());
            payInfo.setNotifyUrl(pay.getNotifyUrl());
            payInfo.setPayId(pay.getId());
        }else{
            pay = new PayEntity();
            pay.setAmount(payInfo.getAmount());
            pay.setBizId(payInfo.getBizId());
            pay.setContent(payInfo.getContent());
            pay.setCode(payInfo.getCode());
            pay.setType(PayConstants.OpType.TYPE_PAY.getCode());
            pay.setProductCode(payInfo.getProductCode());
            pay.setReturnUrl(payInfo.getReturnUrl());
            pay.setNotifyUrl(payInfo.getNotifyUrl());
            /**
             * 默认是草稿状态
             */
            pay.setStatus(PayConstants.PayStatus.STATUS_10000.getCodeStr());
            pay.setStatusDesc(PayConstants.PayStatus.STATUS_10000.getName());
            pay.setAccountId(payInfo.getAccountId());
            payDao.insert(pay);
            PayDetailEntity payDetail = new PayDetailEntity();
            payDetail.setAmount(pay.getAmount());
            payDetail.setBizId(pay.getBizId());
            payDetail.setBizName(payInfo.getContent());
            payDetail.setPayId(pay.getId());
            payDetail.setStatus(pay.getStatus());
            payDetail.setStatusDesc(pay.getStatusDesc());
            payDetailDao.insert(payDetail);
            payInfo.setPayId(pay.getId());
        }
    }

    /**
     * 跟新支付状态
     * 保存支付记录
     * @author afi
     * @param pay
     * @param payLog
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void savePayAndLog(PayEntity pay,PayLogEntity payLog) {
        payDao.update(pay);
        LOGGER_PAY_LOG.error("落地支付日志:"+JsonEntityTransform.Object2Json(payLog));
        //增加同时更新detail表
        if(pay.getId() != null){
            PayDetailEntity payDetail = new PayDetailEntity();
            payDetail.setPayId(pay.getId());
            payDetail.setStatus(pay.getStatus());
            payDetail.setStatusDesc(pay.getStatusDesc());
            payDetailDao.update(payDetail);
        }
    }

    @Override
    public PayEntity selectPayById(Integer id) {
        return payDao.selectById(id);
    }


    /**
     * 通过加工之后的id获取真实的id信息
     * @author afi
     * @param id
     * @return
     */
    @Override
    public PayEntity selectPayByakeId(String id){
        return payDao.selectById(PayKeyUtil.getRealKey(payPreKey,id));
    }


    /**
     * 获取app的支付信息
     * @author afi
     * @param code
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public String getAppString(String code, Map<String, Object> map) throws Exception{
        String returnStr = "";
        if(PayConstants.HandleType.Handle_alipay_App.getCode().equals(code)){
            LOGGER.info("支付宝App组装参数：{}",JsonEntityTransform.Object2Json(map));
            if (map != null) {
                String orderString = (String)map.get("orderString");
                return orderString;
            }
        }else if(PayConstants.HandleType.Handle_tenpay_App.getCode().equals(code)){
            LOGGER.info("微信App支付 组装参数：{}",JsonEntityTransform.Object2Json(map));
            return URLEncoder.encode(JsonEntityTransform.Object2Json(map),"utf-8");
        }else if(PayConstants.HandleType.Handle_weChat_Wap.getCode().equals(code)){
            LOGGER.info("微信公账号支付 组装参数：{}",JsonEntityTransform.Object2Json(map));
            return URLEncoder.encode(JsonEntityTransform.Object2Json(map),"utf-8");
        }else if(PayConstants.HandleType.Handle_tenpay_Refund.getCode().equals(code)){
            LOGGER.info("微信退款 组装参数：{}",JsonEntityTransform.Object2Json(map));
            return URLEncoder.encode(JsonEntityTransform.Object2Json(map),"utf-8");
        }
        return returnStr;
    }

    /**
     * 更新通知结果
     * @author afi
     * @param content
     */
    @Override
    public void saveNotifyResult(String content) {
        try {
            content = DesUtils.decrypt(content);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            return;
        }
        LOGGER.info("保存的content：{}",content);
        if(StringUtils.isNotBlank(content)){
            List<ReturnPay> resultList = new ArrayList<ReturnPay>();
            //通知
            resultList = JsonEntityTransform.json2ObjectList(content, ReturnPay.class);
            for(ReturnPay returnPay : resultList){
                if(PayConstants.SUCCESS_STATUS.equals(returnPay.getCode())) {
                    PayEntity updatePay = new PayEntity();
                    updatePay.setId(returnPay.getId());
                    PayEntity pay = payDao.selectById(returnPay.getId());
                    if(PayConstants.PayStatus.STATUS_SUCCESS.getCodeStr().equals(pay.getStatus())){
                        updatePay.setStatus(PayConstants.PayStatus.STATUS_70000.getCodeStr());
                        updatePay.setStatusDesc(PayConstants.PayStatus.STATUS_70000.getName());
                    }
                    if(PayConstants.PayStatus.STATUS_40000.getCodeStr().equals(pay.getStatus())){
                        updatePay.setStatus(PayConstants.PayStatus.STATUS_80000.getCodeStr());
                        updatePay.setStatusDesc(PayConstants.PayStatus.STATUS_80000.getName());
                    }
                    this.payDao.update(updatePay);
                }
            }
        }
    }


    @Override
    public String notifyListByUrl(String notifyUrl, List<ReturnPay> returnPayList) throws Exception{
        LOGGER.info("返回请求的URL ：{}  ， 内容：{}", notifyUrl,JsonEntityTransform.Object2Json(returnPayList));
        for(ReturnPay returnPay : returnPayList){
            String codeKey = returnPay.getCodeKey();
            returnPay.setPaySign(SignUtils.md5Sign(codeKey, JSON.parseObject(JsonEntityTransform.Object2Json(returnPay), new TypeReference<Map<String, Object>>() {})));
        }
        String parameter = JsonEntityTransform.Object2Json(returnPayList);
        LOGGER.info("准备请求参数：{}", parameter);
        String sec = DesUtils.encrypt(parameter);
        LOGGER.info("准备请求参数,加密之后：{}", sec);
        String rst = CloseableHttpUtil.sendPost(notifyUrl,sec);
        LOGGER.info("同步信息：{}", rst);
        LOGGER.info("同步信息,解密后:{}", DesUtils.decrypt(rst));
        return rst;
    }

}
