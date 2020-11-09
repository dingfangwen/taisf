package com.taisf.services.refund.entity;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

public class RefundEntity  extends BaseEntity{
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 支付业务单号
     */
    private String refundSn;

    /**
     * 订单编号编号
     */
    private String orderSn;

    /**
     * 退款类型 1.原路返回 2.银行卡,3余额
     */
    private Integer refundType;

    /**
     * 来源 1.安卓 2.ios 3.m站 4.微信 5.其他
     */
    private Integer sourceType;

    /**
     * 退款状态 1.待审核 2.审核失败 3.审核成功 4.打款成功 5.打款失败 6.打款中 7.调用支付平台失败 
     */
    private Integer refundStatus;

    /**
     * 退款类型 1.微信 2.支付宝 3.银行卡
     */
    private Integer cardType;

    /**
     * 退款账号信息
     */
    private String cardNo;

    /**
     * 退款人
     */
    private String refundName;

    /**
     * 退款uid
     */
    private String refundUid;

    /**
     * 收款id
     */
    private Integer recordId;

    /**
     * 退款失败重试次数
     */
    private Integer retryTimes;

    /**
     * 支付金额
     */
    private Integer payFee;

    /**
     * 退款金额
     */
    private Integer refundFee;

    /**
     * 退款成功时间
     */
    private Date refundTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    /**
     * 供应商code
     */
    private String supplierCode;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRefundSn() {
        return refundSn;
    }

    public void setRefundSn(String refundSn) {
        this.refundSn = refundSn == null ? null : refundSn.trim();
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getRefundName() {
        return refundName;
    }

    public void setRefundName(String refundName) {
        this.refundName = refundName == null ? null : refundName.trim();
    }

    public String getRefundUid() {
        return refundUid;
    }

    public void setRefundUid(String refundUid) {
        this.refundUid = refundUid == null ? null : refundUid.trim();
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    public Integer getPayFee() {
        return payFee;
    }

    public void setPayFee(Integer payFee) {
        this.payFee = payFee;
    }

    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}