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
public class CreateOrderVO extends BaseEntity{


    /**
     * 订单编号
     */
    private String orderSn;


    /**
     *  订单状态
     */
    private Integer orderStatus;

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }


    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
}
