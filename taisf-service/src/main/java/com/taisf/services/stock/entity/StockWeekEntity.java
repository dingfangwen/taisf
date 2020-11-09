package com.taisf.services.stock.entity;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

public class StockWeekEntity  extends BaseEntity {

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
     * 库存类型 1.入库 2:出库
     */
    private Integer stockType;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 周开始值
     */
    private String weekSn;

    /**
     * 周 1:周一 7:周日
     */
    private Integer week;

    /**
     * 商品code
     */
    private Integer productCode;


    /**
     * 商品name
     */
    private String productName;


    /**
     * 类型
     */
    private Integer supplierProductType;

    /**
     * 订单类型 
     */
    private Integer orderType;

    /**
     * 卖出数量
     */
    private Integer productNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    public Integer getStockType() {
        return stockType;
    }

    public void setStockType(Integer stockType) {
        this.stockType = stockType;
    }

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

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public String getWeekSn() {
        return weekSn;
    }

    public void setWeekSn(String weekSn) {
        this.weekSn = weekSn == null ? null : weekSn.trim();
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
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

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}