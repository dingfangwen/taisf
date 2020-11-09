package com.taisf.services.order.vo;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/2/6.
 * @version 1.0
 * @since 1.0
 */
public class FaceVO  extends BaseEntity{

    /**
     * 支付码
     */
    private String payCode;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 当前时间
     */
    private Date payTime = new Date();

    /**
     * 供应商code
     */
    private String supplierName;

    /**
     * 供应商code
     */
    private String supplierCode;

    /**
     * 金额 分
     */
    private Integer price;

    /**
     *  订单状态
     */
    private Integer orderStatus;

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
}
