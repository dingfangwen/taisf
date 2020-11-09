package com.taisf.services.supplier.vo;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>订单类型</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/4/13.
 * @version 1.0
 * @since 1.0
 */
public class OrderTypeVO  extends BaseEntity{

    /**
     * 订单类型
     */
    private Integer orderType;

    /**
     * 订单类型名字
     */
    private String orderTypeName;


    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }
}
