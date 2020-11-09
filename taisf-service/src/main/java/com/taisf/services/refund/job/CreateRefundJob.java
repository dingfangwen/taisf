package com.taisf.services.refund.job;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.rst.ResponseDto;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.SignUtils;
import com.jk.framework.base.utils.ValueUtil;
import com.jk.framework.common.utils.CloseableHttpUtil;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.common.valenum.RecordPayTypeEnum;
import com.taisf.services.recharge.manager.RechargeManagerImpl;
import com.taisf.services.refund.constants.PayConstant;
import com.taisf.services.refund.constants.RefundStatusEnum;
import com.taisf.services.refund.dto.RefundJobRequest;
import com.taisf.services.refund.dto.RefundRequest;
import com.taisf.services.refund.entity.RefundEntity;
import com.taisf.services.refund.manager.RefundManagerImpl;
import com.taisf.services.user.entity.AccountLogEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/1/2.
 * @version 1.0
 * @since 1.0
 */
@Service("refund.createRefundJob")
public class CreateRefundJob {


    private static final Logger LOGGER = LoggerFactory.getLogger(CreateRefundJob.class);



    @Resource(name = "pay.payConstant")
    private PayConstant payConstant;


    @Resource(name = "refund.refundServiceImpl")
    private RefundManagerImpl refundManagerImpl;

    @Resource(name = "recharge.rechargeManagerImpl")
    private RechargeManagerImpl rechargeManager;


    /**
     * 申请退款
     * @author afi
     * @param
     * @return
     */
    public DataTransferObject<Void> breakRefund(String refundSn){
        RefundJobRequest pageRequest = new RefundJobRequest();
        pageRequest.setRefundSn(refundSn);
        pageRequest.setRefundStatus(RefundStatusEnum.PASS.getCode());
        return dealRefund4All(pageRequest);
    }

    /**
     * 处理退款逻辑
     * @param pageRequest
     * @return
     */
    private DataTransferObject<Void> dealRefund4All(RefundJobRequest pageRequest) {
        DataTransferObject dto =new DataTransferObject();

        PagingResult<RefundEntity> pagingResult = refundManagerImpl.getRefundPass(pageRequest);
        if (pagingResult.getTotal() <= 0){
            LogUtil.info(LOGGER,"待退款条数为0,直接返回");
            return dto;
        }
        int pageAll = ValueUtil.getPage(ValueUtil.getintValue(pagingResult.getTotal()),pageRequest.getLimit());
        for(int page=1 ; page <= pageAll ; page++){
            pageRequest.setPage(page);
            PagingResult<RefundEntity> rst = refundManagerImpl.getRefundPass(pageRequest);
            if (!Check.NuNCollection(rst.getList())){
                for (RefundEntity refundEntity : rst.getList()) {
                    Integer cardType = refundEntity.getCardType();
                    RecordPayTypeEnum recordPayTypeEnum = RecordPayTypeEnum.getTypeByCode(cardType);
                    if (Check.NuNObj(recordPayTypeEnum)){
                        LogUtil.info(LOGGER,"异常的支付类型:par:{}", JsonEntityTransform.Object2Json(refundEntity));
                        return dto;
                    }
                    if (recordPayTypeEnum.getCode() == RecordPayTypeEnum.YUE.getCode() ){
                        this.dealRefund4Yue(refundEntity);
                    }else {
                        //直接调用退款逻辑
                        this.dealRefund4Open(refundEntity);
                    }
                }
            }

        }
        return dto;
    }


    /**
     * 申请退款
     * @author afi
     * @param
     * @return
     */
    public DataTransferObject<Void> createRefund(String refundSn){
        RefundJobRequest pageRequest = new RefundJobRequest();
        pageRequest.setRefundSn(refundSn);
        pageRequest.setRefundStatus(RefundStatusEnum.FAIL.getCode());
        return dealRefund4All(pageRequest);

    }

    /**
     * 处理退款的逻辑-支付平台
     * @author afi
     * @param refundEntity
     */
    private void dealRefund4Yue(RefundEntity refundEntity){
        LogUtil.info(LOGGER,"[退款] 余额退款参数:par:{}", JsonEntityTransform.Object2Json(refundEntity));

        RefundRequest refundRequest =new RefundRequest();
        refundRequest.setRefundSn(refundEntity.getRefundSn());
        refundRequest.setOrderSn(refundEntity.getOrderSn());
        refundRequest.setOpName("system");
        refundRequest.setOpUid("001");
        String remark = "直接退款到余额";
        Integer refundStatus = RefundStatusEnum.SUCCESS.getCode();
        Integer retryTime = 1; //失败记录重试次数
        refundRequest.setRemark(remark);
        refundRequest.setRefundStatus(refundStatus);
        refundRequest.setOldStatus(refundEntity.getRefundStatus());
        refundRequest.setRetryTime(retryTime);
        AccountLogEntity has = rechargeManager.getAccountLogByBizSn(refundEntity.getRefundSn());
        try {
            if (!Check.NuNObj(has)){
                LogUtil.info(LOGGER,"[退款] 幂等处理:par:{}", JsonEntityTransform.Object2Json(refundEntity));
                //幂等成功,直接强制更新当前的订单和退款单状态
                refundManagerImpl.updateRefundSuccessForce(refundRequest);
            }else {
                LogUtil.info(LOGGER,"[退款] 更新退款:par:{}", JsonEntityTransform.Object2Json(refundEntity));
                //处理当前的退款
                refundManagerImpl.updateRefund4ChangeAll(refundRequest,refundEntity);
            }
        }catch (Exception e){
            LogUtil.error(LOGGER, "【调用退款失败】error:{}", e);
        }
        LogUtil.info(LOGGER,"[退款] 余额退款成功:par:{}", JsonEntityTransform.Object2Json(refundEntity));
    }

    /**
     * 处理退款的逻辑-支付平台
     * @author afi
     * @param refundEntity
     */
    private void dealRefund4Open(RefundEntity refundEntity){
        LogUtil.info(LOGGER,"[退款] 支付平台退款参数:par:{}", JsonEntityTransform.Object2Json(refundEntity));
        ResponseDto responseDto = null;
        try {
            Map<String,Object> par =new HashMap<>();
            par.put("bizId",refundEntity.getRefundSn());
            par.put("content","问诊退款");

            par.put("returnUrl",payConstant.PAY_API_URL+"/refund/notice");
            par.put("notifyUrl",payConstant.PAY_API_URL+"/refund/notice");
            par.put("amount",refundEntity.getRefundFee());
            par.put("orgBizId",refundEntity.getOrderSn());
            par.put("orgInSerialNo",refundEntity.getCardNo());
            final String out = SignUtils.md5Sign(payConstant.PAY_KEY, par);
            par.put("paySign", out);
            //直接调用支付接口
            LogUtil.info(LOGGER,"[退款] 调用支付平台参数:par:{}",JsonEntityTransform.Object2Json(par));
            String jsonRst = CloseableHttpUtil.sendPost(payConstant.REFUND_PLATFORM_URL,par);
            responseDto = JsonEntityTransform.json2Entity(jsonRst,ResponseDto.class);
            LogUtil.info(LOGGER,"[退款] 调用支付平台结果:par:{}",jsonRst);
        }catch (Exception e){
            LogUtil.error(LOGGER, "【调用退款失败】pay:{}",JsonEntityTransform.Object2Json(refundEntity));
            LogUtil.error(LOGGER, "【调用退款失败】error:{}", e);
        }

        RefundRequest refundRequest =new RefundRequest();
        refundRequest.setRefundSn(refundEntity.getRefundSn());
        refundRequest.setOpName("system");
        refundRequest.setOpUid("001");
        //更新当前的退款单状态
        String remark = null;
        Integer refundStatus = null;
        Integer retryTime = 0; //失败记录重试次数
        if (!Check.NuNObj(responseDto)
                && responseDto.getMsg().isSuccess()){
            //成功
            remark = "定时任务提交退款";
            refundStatus = RefundStatusEnum.SENDING.getCode();
        }else {
            //失败
            remark = "定时任务提交退款,调用支付系统失败";
            refundStatus = RefundStatusEnum.BREAK.getCode();
            retryTime = 1; //失败数据库数据就加1
        }
        refundRequest.setRemark(remark);
        refundRequest.setRefundStatus(refundStatus);
        refundRequest.setRetryTime(retryTime);
        try {
            refundManagerImpl.updateRefund4ChangeAll(refundRequest,refundEntity);
        }catch (Exception e){
            LogUtil.error(LOGGER, "【调用退款失败】error:{}", e);
        }
    }
}
