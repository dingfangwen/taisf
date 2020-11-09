package com.taisf.services.supplier.dto;

import com.jk.framework.base.page.PageRequest;

/**
 * @author zhangzhengguang
 * @create 2017-10-14
 **/
public class SupplierRequest extends PageRequest {
    private String supplierName;

    private String manger;

    public String getManger() {
        return manger;
    }

    public void setManger(String manger) {
        this.manger = manger;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
