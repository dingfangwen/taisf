package com.taisf.services.base.entity;

import com.jk.framework.base.entity.BaseEntity;

public class EmployeeSupplierEntity extends BaseEntity {
    private Integer id;

    private String userId;

    private String supplierCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode == null ? null : supplierCode.trim();
    }
}