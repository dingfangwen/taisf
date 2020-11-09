package com.taisf.services.order.vo;

import com.taisf.services.order.entity.OrderEntity;
import com.taisf.services.order.entity.OrderProductEntity;

import java.util.List;

/**
 * <p>订单的信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/30.
 * @version 1.0
 * @since 1.0
 */
public class OrderInfoVO extends OrderEntity {


    /**
     * 总金额 所有费用之和
     */
    private Integer sumMoney;

    /**
     * 优惠券金额 分
     */
    private Integer couponMoney;

    /**
     * 折扣金额
     */
    private Integer discountMoney;

    /**
     * 红包支付金额
     */
    private Integer redMoney;


    /**
     * 应付金额
     */
    private Integer needPay;

    /**
     * 实际支付金额
     */
    private Integer payMoney;

    /**
     * 实际支付余额
     */
    private Integer payBalance;

    /**
     * 运费
     */
    private Integer carryMoney;

    /**
     * 订单的商品列表
     */
    List<OrderProductEntity> list;

    private String supplierName;

    /**
     * 企业name
     */
    private String enterpriseName;

    /**
     * 是否可退款
     */
    private Integer canRefund = 0;

    private String orderTypeStr;

    /**
     * 支付方式
     */
    private String payTypeStr = "";


    /**
     * 餐柜编号
     */
    private String buffetFid ;


    /**
     * 格子编号
     */
    private String cellSn ;

    public String getBuffetFid() {
        return buffetFid;
    }

    public void setBuffetFid(String buffetFid) {
        this.buffetFid = buffetFid;
    }

    public String getCellSn() {
        return cellSn;
    }

    public void setCellSn(String cellSn) {
        this.cellSn = cellSn;
    }

    public String getPayTypeStr() {
        return payTypeStr;
    }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }

    public String getOrderTypeStr() {
        return orderTypeStr;
    }

    public void setOrderTypeStr(String orderTypeStr) {
        this.orderTypeStr = orderTypeStr;
    }

    public Integer getCanRefund() {
        return canRefund;
    }

    public void setCanRefund(Integer canRefund) {
        this.canRefund = canRefund;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<OrderProductEntity> getList() {
        return list;
    }

    public void setList(List<OrderProductEntity> list) {
        this.list = list;
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

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
}
