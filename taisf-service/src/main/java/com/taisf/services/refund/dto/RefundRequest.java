package com.taisf.services.refund.dto;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>退款操作</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/3/25.
 * @version 1.0
 * @since 1.0
 */
public class RefundRequest extends BaseEntity{

    private static final long serialVersionUID = 301222341446703L;

    /**
     * 退款单号
     */
    private String refundSn;

    /**
     * 订单编号
     */
    private String orderSn;


    /**
     * 退款状态
     */
    private Integer refundStatus;


    /**
     * 原始状态
     */
    private Integer oldStatus;

    /**
     * 操作人
     */
    private String opName;

    /**
     * 操作人uid
     */
    private String opUid;

    /**
     * 备注
     */
    private String remark;

    /**
     * 失败重试计数
     */
    private Integer retryTime;


    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Integer getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(Integer oldStatus) {
        this.oldStatus = oldStatus;
    }

    public String getRefundSn() {
        return refundSn;
    }

    public void setRefundSn(String refundSn) {
        this.refundSn = refundSn;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public String getOpUid() {
        return opUid;
    }

    public void setOpUid(String opUid) {
        this.opUid = opUid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(Integer retryTime) {
        this.retryTime = retryTime;
    }
}
