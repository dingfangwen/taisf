package com.taisf.services.supplier.entity;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

public class SupplierPrintterEntity extends BaseEntity{
    private Integer id;

    /**
     * 供应商表code
     */
    private String supplierCode;

    /**
     * 打印机编号
     */
    private String printerNum;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否默认打印 0否 1是
     */
    private Integer isDefault;

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

    public String getPrinterNum() {
        return printerNum;
    }

    public void setPrinterNum(String printerNum) {
        this.printerNum = printerNum == null ? null : printerNum.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
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
}