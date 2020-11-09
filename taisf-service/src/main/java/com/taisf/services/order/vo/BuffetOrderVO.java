package com.taisf.services.order.vo;

import com.taisf.services.buffet.entity.BuffetEntity;

/**
 * <p>订单的餐柜信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2019/4/22.
 * @version 1.0
 * @since 1.0
 */
public class BuffetOrderVO extends BuffetEntity {


    /**
     * 餐柜编号
     */
    private String buffetFid ;


    /**
     * 格子编号
     */
    private String cellSn ;


    /**
     * 订单编号
     */
    private String orderSn;



    /**
     *  订单状态
     */
    private Integer orderStatus;

    /**
     * 详细信息
     */
    private String addressFull;


    public String getAddressFull() {
        return addressFull;
    }

    public void setAddressFull(String addressFull) {
        this.addressFull = addressFull;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

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

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }
}
