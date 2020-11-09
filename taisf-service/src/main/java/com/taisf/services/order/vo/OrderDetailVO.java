package com.taisf.services.order.vo;

import com.jk.framework.base.entity.BaseEntity;
import com.taisf.services.order.entity.OrderEntity;
import com.taisf.services.order.entity.OrderMoneyEntity;
import com.taisf.services.order.entity.OrderPayEntity;
import com.taisf.services.order.entity.OrderProductEntity;
import com.taisf.services.user.entity.UserAddressEntity;

import java.util.List;

/**
 * <p>获取订单相关信息</p>
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
public class OrderDetailVO extends BaseEntity{


    /**
     * 订单信息
     */
    private OrderEntity orderEntity;


    /**
     * 订单的金额信息
     */
    private OrderMoneyEntity orderMoneyEntity;


    /**
     * 订单的支付信息
     */
    private OrderPayEntity orderPayEntity;


    /**
     * 支付类型
     */
    private String payInfo = "";

    /**
     * 订单商品信息
     */
    private List<OrderProductEntity> list;


    public String getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public OrderMoneyEntity getOrderMoneyEntity() {
        return orderMoneyEntity;
    }

    public void setOrderMoneyEntity(OrderMoneyEntity orderMoneyEntity) {
        this.orderMoneyEntity = orderMoneyEntity;
    }

    public OrderPayEntity getOrderPayEntity() {
        return orderPayEntity;
    }

    public void setOrderPayEntity(OrderPayEntity orderPayEntity) {
        this.orderPayEntity = orderPayEntity;
    }

    public List<OrderProductEntity> getList() {
        return list;
    }

    public void setList(List<OrderProductEntity> list) {
        this.list = list;
    }
}
