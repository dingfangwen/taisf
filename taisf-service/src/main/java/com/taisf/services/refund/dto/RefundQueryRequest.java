package com.taisf.services.refund.dto;

import com.jk.framework.base.page.PageRequest;

/**
 * @author zhangzhengguang
 * @create 2017-12-21
 **/
public class RefundQueryRequest extends PageRequest {
    private String userName;
    private String userPhone;
    private Integer refundStatus;

    private String supplierCode;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }
}
