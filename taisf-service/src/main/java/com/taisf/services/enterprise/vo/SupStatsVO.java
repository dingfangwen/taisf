package com.taisf.services.enterprise.vo;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>企业订单的额统计新</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/20.
 * @version 1.0
 * @since 1.0
 */
public class SupStatsVO extends BaseEntity{

    private static final long serialVersionUID = 301231231201446703L;

    /**
     * 统计区间
     */
    private String time;

    private String supplierCode;

    private String supplierName;


    /**
     * 支付
     */
    private Integer payMoney;

    /**
     * 余额支付
     */
    private Integer payBalance;



    /**
     * 充值次数
     */
    private Integer rechargeNum;

    /**
     * 充值金额
     */
    private Integer rechargePrice;



    /**
     * 自主充值次数
     */
    private Integer orderRechargeNum;

    /**
     * 自主充值金额
     */
    private Integer orderRechargePrice;


    public Integer getRechargeNum() {
        return rechargeNum;
    }

    public void setRechargeNum(Integer rechargeNum) {
        this.rechargeNum = rechargeNum;
    }

    public Integer getRechargePrice() {
        return rechargePrice;
    }

    public void setRechargePrice(Integer rechargePrice) {
        this.rechargePrice = rechargePrice;
    }

    public Integer getOrderRechargeNum() {
        return orderRechargeNum;
    }

    public void setOrderRechargeNum(Integer orderRechargeNum) {
        this.orderRechargeNum = orderRechargeNum;
    }

    public Integer getOrderRechargePrice() {
        return orderRechargePrice;
    }

    public void setOrderRechargePrice(Integer orderRechargePrice) {
        this.orderRechargePrice = orderRechargePrice;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Integer payMoney) {
        this.payMoney = payMoney;
    }

    public Integer getPayBalance() {
        return payBalance;
    }

    public void setPayBalance(Integer payBalance) {
        this.payBalance = payBalance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
