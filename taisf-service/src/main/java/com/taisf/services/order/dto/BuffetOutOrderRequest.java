package com.taisf.services.order.dto;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>防止餐柜</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/17.
 * @version 1.0
 * @since 1.0
 */
public class BuffetOutOrderRequest extends BaseEntity {

    private static final long serialVersionUID = 301231231201446703L;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 操作人姓名
     */
    private String opName;

    /**
     * 操作人id
     */
    private String opId;


    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public String getOpId() {
        return opId;
    }

    public void setOpId(String opId) {
        this.opId = opId;
    }
}
