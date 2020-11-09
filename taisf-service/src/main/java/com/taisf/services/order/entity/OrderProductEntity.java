package com.taisf.services.order.entity;

import com.jk.framework.base.entity.BaseEntity;

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
public class OrderProductEntity extends BaseEntity {

    private static final long serialVersionUID = 1312313213123211L;

    /**
     * 逻辑id
     */
    private Integer id;

    /**
     * 订单编号
     */
    private String orderSn;


    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品配型
     */
    private Integer productType;

    /**
     * 商品code
     */
    private Integer productCode;

    /**
     * 商品数量
     */
    private Integer productNum;

    /**
     * 商品单价
     */
    private Integer productPrice;


    private String productClassify;


    public String getProductClassify() {
        return productClassify;
    }

    public void setProductClassify(String productClassify) {
        this.productClassify = productClassify;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }
}