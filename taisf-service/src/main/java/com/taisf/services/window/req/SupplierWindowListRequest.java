package com.taisf.services.window.req;

import com.jk.framework.base.page.PageRequest;

/**
 * @author zhangzhengguang
 * @create 2018-09-19
 **/
public class SupplierWindowListRequest extends PageRequest {

    private String supplierCode;
    private String windowName;

    public String getWindowName() {
        return windowName;
    }

    public void setWindowName(String windowName) {
        this.windowName = windowName;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }
}
