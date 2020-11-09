package com.taisf.services.pay.dto;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

/**
 * <p>支付回调</p>
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
public class PayRecordRequest extends BaseEntity{

    private static final long serialVersionUID = 301222341446703L;

    /**
     * 订单编号
     */
    private String orderSn;


    /**
     * 支付类型 1.微信 2.支付宝支付
     */
    private Integer payType;


    /**
     * 收款金额
     */
    private Integer totalFee;


    /**
     * 支付流水号
     */
    private String tradeNo;

    /**
     * 支付返回code
     */
    private String payCode;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 支付时间 秒
     */
    private Integer paytime;


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

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getPaytime() {
        return paytime;
    }

    public void setPaytime(Integer paytime) {
        this.paytime = paytime;
    }

}
