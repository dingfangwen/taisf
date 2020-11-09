package com.taisf.services.order.entity;


import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;


/**
 * <p>订单的支付记录</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2017/9/12.
 * @version 1.0
 * @since 1.0
 */
public class OrderPayEntity extends BaseEntity {
    /**
	 * 序列化id
	 */
	private static final long serialVersionUID = -221672672185423748L;

	/** id */
    private Integer id;

    /** 订单编号 */
    private String orderSn;
    
    /** 用户uid */
    private String payUid;

    /** 支付类型 */
    private Integer payType;

    /** 支付金额 */
    private Integer payMoney;
    

    /** 支付状态 */
    private Integer payStatus;

    /** 交易流水号 */
    private String tradeNo;

    /** 创建时间  */
    private Date createTime;

    /** 最后更新时间 */
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
        this.orderSn = orderSn;
    }

    public String getPayUid() {
        return payUid;
    }

    public void setPayUid(String payUid) {
        this.payUid = payUid;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Integer payMoney) {
        this.payMoney = payMoney;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
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