package com.taisf.services.pay.vo;

import com.jk.framework.base.entity.BaseEntity;
import com.jk.framework.excel.annotation.FieldMeta;
import com.jk.framework.excel.annotation.ForceStr;
import com.jk.framework.excel.annotation.MoneyPenny2Yuan;
import com.jk.framework.excel.annotation.TimeFormatPattern;

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
public class RechargeOrderVO extends BaseEntity{


    /**
     * 订单编号
     */
    @FieldMeta(name="订单编号",order=1)
    private String orderSn;

    /**
     * 支付类型 1.微信 2.支付宝支付 3.余额支付
     */
    @FieldMeta(skip = true)
    private Integer payType;


    /**
     * 收款金额
     */
    @MoneyPenny2Yuan
    @FieldMeta(name="收款金额",order=1)
    private Integer totalFee;

    /**
     * 支付流水号
     */
    @FieldMeta(name="支付流水号",order=1)
    @ForceStr
    private String tradeNo;

    /**
     * 支付时间
     */
    @TimeFormatPattern
    @FieldMeta(name="支付时间",order=1)
    private Date payTime;


    /**
     * 支付状态 0：未支付 1：已支付
     */
    @FieldMeta(skip = true)
    private Integer payStatus;

    /**
     * 预订人uid
     */
    @FieldMeta(name="预订人uid",order=1)
    private String userUid;


    /**
     * 预订人电话
     */
    @FieldMeta(name="预订人电话",order=1)
    private String userTel;


    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
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

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }
}
