package com.taisf.services.order.entity;


import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>订单的金额</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2017/9/14.
 * @version 1.0
 * @since 1.0
 */
public class OrderMoneyEntity extends BaseEntity {


    /** 序列化id  */
    private static final long serialVersionUID = 496423458112315698L;


    /** 订单编号 */
    private String orderSn;

    /** uid */
    private String uid;



    /** 总金额 所有费用之和 */
    private Integer sumMoney;

    /** 优惠券金额 分 */
    private Integer couponMoney ;

    /** 折扣金额 */
    private Integer discountMoney;

    /** 红包支付金额 */
    private Integer redMoney;


    /** 应付金额 */
    private Integer needPay;

    /** 实际支付金额 */
    private Integer payMoney;

    /** 实际支付余额 */
    private Integer payBalance;

    /** 运费 */
    private Integer carryMoney;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Integer getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Integer sumMoney) {
        this.sumMoney = sumMoney;
    }

    public Integer getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(Integer couponMoney) {
        this.couponMoney = couponMoney;
    }

    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }

    public Integer getRedMoney() {
        return redMoney;
    }

    public void setRedMoney(Integer redMoney) {
        this.redMoney = redMoney;
    }

    public Integer getNeedPay() {
        return needPay;
    }

    public void setNeedPay(Integer needPay) {
        this.needPay = needPay;
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

    public Integer getCarryMoney() {
        return carryMoney;
    }

    public void setCarryMoney(Integer carryMoney) {
        this.carryMoney = carryMoney;
    }
}
