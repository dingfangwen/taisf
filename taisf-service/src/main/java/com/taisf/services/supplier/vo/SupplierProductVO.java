package com.taisf.services.supplier.vo;

import com.taisf.services.product.entity.ProductEntity;

import java.util.List;

/**
 * <p>供应商的商品列表</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/26.
 * @version 1.0
 * @since 1.0
 */
public class SupplierProductVO  extends ProductEntity {

    /**
     * 供应商的特殊描述
     */
    private Integer supplierProductType;


    /**
     * 供应商的特殊描述
     */
    private String supplierProductDes;

    /**
     * 订单类型列表
     */
    private List<OrderTypeVO> orderList;


    public List<OrderTypeVO> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderTypeVO> orderList) {
        this.orderList = orderList;
    }

    public Integer getSupplierProductType() {
        return supplierProductType;
    }

    public void setSupplierProductType(Integer supplierProductType) {
        this.supplierProductType = supplierProductType;
    }

    public String getSupplierProductDes() {
        return supplierProductDes;
    }

    public void setSupplierProductDes(String supplierProductDes) {
        this.supplierProductDes = supplierProductDes;
    }
}
