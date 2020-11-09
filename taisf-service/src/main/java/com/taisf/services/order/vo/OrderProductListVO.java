package com.taisf.services.order.vo;

import com.taisf.services.order.entity.OrderProductEntity;

/**
 * <p>订单商品表</p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi
 * @since 1.0
 * @version 1.0
 */
public class OrderProductListVO extends OrderProductEntity {

    private String classifyName;

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }
}