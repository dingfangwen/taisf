package com.taisf.services.product.dto;

import com.jk.framework.base.page.PageRequest;

/**
 * @author zhangzhengguang
 * @create 2017-10-11
 **/
public class ProductListRequest extends PageRequest {
    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品分类
     */
    private String productClassify;

    /**
     * 商品配型
     */
    private Integer productType;


    /**
     * @see com.taisf.services.common.valenum.SupplierProductTypeEnum
     */
    private Integer supplierProductType;

    /**
     * 周几
     */
    private Integer week;

    /**
     * 商品属性
     */
    private Integer productSource;

    private String supplierCode;

    private Integer forLunch;

    private Integer forDinner;
    private String windowCode;

    private Integer packageId;

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getWindowCode() {
        return windowCode;
    }

    public void setWindowCode(String windowCode) {
        this.windowCode = windowCode;
    }

    public Integer getForLunch() {
        return forLunch;
    }

    public void setForLunch(Integer forLunch) {
        this.forLunch = forLunch;
    }

    public Integer getForDinner() {
        return forDinner;
    }

    public void setForDinner(Integer forDinner) {
        this.forDinner = forDinner;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductClassify() {
        return productClassify;
    }

    public void setProductClassify(String productClassify) {
        this.productClassify = productClassify;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getProductSource() {
        return productSource;
    }

    public void setProductSource(Integer productSource) {
        this.productSource = productSource;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getSupplierProductType() {
        return supplierProductType;
    }

    public void setSupplierProductType(Integer supplierProductType) {
        this.supplierProductType = supplierProductType;
    }
}
