package com.taisf.services.refund.manager;

import com.jk.framework.base.exception.BusinessException;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.ValueUtil;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.common.valenum.OrdersStatusEnum;
import com.taisf.services.order.dao.OrderBaseDao;
import com.taisf.services.order.entity.OrderEntity;
import com.taisf.services.common.valenum.RecordPayTypeEnum;
import com.taisf.services.order.dao.OrderBaseDao;
import com.taisf.services.recharge.manager.RechargeManagerImpl;
import com.taisf.services.refund.constants.RefundStatusEnum;
import com.taisf.services.refund.dao.RefundDao;
import com.taisf.services.refund.dao.RefundLogDao;
import com.taisf.services.refund.dto.RefundJobRequest;
import com.taisf.services.refund.dto.RefundQueryRequest;
import com.taisf.services.refund.dto.RefundRequest;
import com.taisf.services.refund.entity.RefundEntity;
import com.taisf.services.refund.entity.RefundLogEntity;
import com.taisf.services.refund.vo.RefundVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


/**
 * @author:zhangzhengguang
 * @date:2017/12/21
 * @description:退款
 **/
@Service("refund.refundServiceImpl")
public class RefundManagerImpl {

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(RefundManagerImpl.class);

    @Resource(name = "refund.refundDao")
    private RefundDao refundDao;


    @Resource(name="refund.refundLogDao")
    private RefundLogDao refundLogDao;

    @Resource(name="recharge.rechargeManagerImpl")
    private RechargeManagerImpl rechargeManager;

    @Resource(name = "order.orderBaseDao")
    private OrderBaseDao orderBaseDao;
    /**
     * 查询审核通过的退款列表 分页
     * @param request
     * @return
     */
    public PagingResult<RefundEntity> getRefundPass(RefundJobRequest request) {
        return refundDao.getRefundPass(request);
    }

    /**
     * 根据id查询
     * @param refundSn
     * @return
     */
    public RefundEntity findRefundByCode(String refundSn){
        return refundDao.findRefundByCode(refundSn);
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/12/21
     * @description:分页条件查询
     **/
    public PagingResult<RefundVo> refundPageList(RefundQueryRequest request) {
        return refundDao.refundPageList(request);
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/12/21
     * @description:根据id查询详情
     **/
    public RefundEntity findRefundById(Integer id) {
        return refundDao.findRefundById(id);
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/12/21
     * @description:后台审核当前的退款单
     **/
    public int updateRefundAudit(RefundEntity refundEntity) {
        //如果驳回 修改订单的状态 REFUND_NO
        if(refundEntity.getRefundStatus() == RefundStatusEnum.NO_PASS.getCode()){

            OrderEntity base = new OrderEntity();
            base.setOrderSn(refundEntity.getOrderSn());
            base.setOrderStatus(OrdersStatusEnum.REFUND_NO.getCode());
            orderBaseDao.updateOrderBaseByOrderSn(base);
        }
        return refundDao.updateRefund(refundEntity);
    }


    /**
     * 强制更新当前的退款单状态
     * @authior afi
     * @param refundRequest
     */
    public void updateRefundSuccessForce(RefundRequest refundRequest){

        //修改状态
        int num = refundDao.updateRefundStatusAndRetryTimes(refundRequest.getRefundSn(),refundRequest.getRefundStatus(),refundRequest.getOldStatus(), 1);
        if (num > 1){
            LogUtil.error(logger,"异常的更新条数:par:{}",JsonEntityTransform.Object2Json(refundRequest));
            throw new BusinessException("异常的更新条数");
        }else if (num == 0){
            return;
        }

        //处理日志
        RefundLogEntity entity = new RefundLogEntity();
        entity.setRefundSn(refundRequest.getRefundSn());
        entity.setStatusFrom(refundRequest.getOldStatus());
        entity.setStatusTo(refundRequest.getRefundStatus());
        entity.setOpName(refundRequest.getOpName());
        entity.setOpUid(refundRequest.getOpUid());
        entity.setRemark(refundRequest.getRemark());
        entity.setCreateTime(new Date());
        refundLogDao.saveRefundLog(entity);


        //打款成功变更订单为打款成功
        orderBaseDao.refundOrderSuccess(refundRequest.getOrderSn());

    }



    /**
     * 更新退款状态
     * @author afi
     * @param refundRequest
     * @param refund
     */
    public void updateRefund4ChangeAll(RefundRequest refundRequest, RefundEntity refund ) {
        LogUtil.info(logger,"[退款] 保存退款操作记录:refundRequest:{} refund:{} ", JsonEntityTransform.Object2Json(refundRequest),JsonEntityTransform.Object2Json(refund));

        RefundEntity refundEntity = refundDao.findRefundByCode(refund.getRefundSn());
        Integer retryTimes = 1; //重试次数
        LogUtil.info(logger,"[退款] 退款失败重试次数记录: DB:retryTimes:{}; retryTime的值:{}", refundEntity.getRetryTimes(), refundRequest.getRetryTime());
        if (ValueUtil.getintValue(refundEntity.getRefundStatus()) == refundRequest.getRefundStatus()){
            //重复调用,幂等返回
            return;
        }

        //修改状态
        int num = refundDao.updateRefundStatusAndRetryTimes(refund.getRefundSn(),refundRequest.getRefundStatus(),refund.getRefundStatus(), retryTimes);
        if (num != 1){
            LogUtil.error(logger,"异常的更新条数:par:{}",JsonEntityTransform.Object2Json(refundRequest));
            throw new BusinessException("异常的更新条数");
        }

        if (refundRequest.getRefundStatus() == RefundStatusEnum.SUCCESS.getCode()){
            //打款成功变更订单为打款成功
            orderBaseDao.refundOrderSuccess(refundEntity.getOrderSn());
        }

        RefundLogEntity entity = new RefundLogEntity();
        entity.setRefundSn(refund.getRefundSn());
        entity.setStatusFrom(refund.getRefundStatus());
        entity.setStatusTo(refundRequest.getRefundStatus());
        entity.setOpName(refundRequest.getOpName());
        entity.setOpUid(refundRequest.getOpUid());
        entity.setRemark(refundRequest.getRemark());
        entity.setCreateTime(new Date());
        refundLogDao.saveRefundLog(entity);


        RecordPayTypeEnum recordPayTypeEnum = RecordPayTypeEnum.getTypeByCode(refundEntity.getCardType());
        if (Check.NuNObj(recordPayTypeEnum)){
            LogUtil.info(logger,"异常的支付类型:par:{}", JsonEntityTransform.Object2Json(refundEntity));
            return ;
        }
        if (recordPayTypeEnum.getCode() == RecordPayTypeEnum.YUE.getCode()
                && refundRequest.getRefundStatus() == RefundStatusEnum.SUCCESS.getCode()
                ){
            //处理当前的余额信息
            rechargeManager.refundByOrder(refund.getRefundUid(),refund.getRefundFee(),refund.getRefundSn());
        }
    }
}
