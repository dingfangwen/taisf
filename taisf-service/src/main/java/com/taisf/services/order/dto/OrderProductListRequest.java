package com.taisf.services.order.dto;

import com.jk.framework.base.page.PageRequest;

/**
 * @author zhangzhengguang
 * @create 2017-10-17
 **/
public class OrderProductListRequest extends PageRequest {

    private String orderSn;

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }
}
