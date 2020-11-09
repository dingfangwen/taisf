package com.taisf.services.stock.entity;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

public class StockProductEntity  extends BaseEntity {

    private static final long serialVersionUID = 301231231201446703L;


    /**
     * 逻辑id
     */
    private Integer id;

    /**
     * 单位编码
     */
    private String supplierCode;

    /**
     * 商品code
     */
    private Integer productCode;

    /**
     * 周
     */
    private Integer week;

    /**
     * 类型
     */
    private Integer supplierProductType;

    /**
     * 订单类型 
     */
    private Integer orderType;

    /**
     * 商品数量限制
     */
    private Integer productLimit;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode == null ? null : supplierCode.trim();
    }

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    public Integer getSupplierProductType() {
        return supplierProductType;
    }

    public void setSupplierProductType(Integer supplierProductType) {
        this.supplierProductType = supplierProductType;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getProductLimit() {
        return productLimit;
    }

    public void setProductLimit(Integer productLimit) {
        this.productLimit = productLimit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }
}