package com.taisf.services.pay.entity;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

/**
 * <p>用户的充值</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/4/16.
 * @version 1.0
 * @since 1.0
 */
public class RechargeOrderEntity extends BaseEntity{

    /**
     * 主键id 主键id
     */
    private Integer id;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 支付类型 1.微信 2.支付宝支付 3.余额支付
     */
    private Integer payType;


    /**
     * 收款金额
     */
    private Integer totalFee;

    /**
     * 应收金额
     */
    private Integer needMoney;

    /**
     * 支付流水号
     */
    private String tradeNo;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 结算单状态0:不需要结算 1：结算中 2：结算完
     */
    private Integer accountsStatus;

    /**
     * 支付状态 0：未支付 1：已支付
     */
    private Integer payStatus;

    private String enterpriseCode;

    /**
     * 供应商code
     */
    private String supplierCode;

    /**
     * 预订人uid
     */
    private String userUid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date lastModifyDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getNeedMoney() {
        return needMoney;
    }

    public void setNeedMoney(Integer needMoney) {
        this.needMoney = needMoney;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getAccountsStatus() {
        return accountsStatus;
    }

    public void setAccountsStatus(Integer accountsStatus) {
        this.accountsStatus = accountsStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode == null ? null : enterpriseCode.trim();
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode == null ? null : supplierCode.trim();
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid == null ? null : userUid.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

}
