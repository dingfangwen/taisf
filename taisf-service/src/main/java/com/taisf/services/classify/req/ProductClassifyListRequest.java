package com.taisf.services.classify.req;

import com.jk.framework.base.page.PageRequest;

/**
 * @author zhangzhengguang
 * @create 2018-09-19
 **/
public class ProductClassifyListRequest extends PageRequest {

    private String supplierCode;
    private String classifyName;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }
}
