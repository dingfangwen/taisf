package com.taisf.services.supplier.entity;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

/**
 * <p>
 * 供应商的财务信息
 * </p>
 * <p/>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2017/9/20.
 * @version 1.0
 * @since 1.0
 */
public class SupplierFinanceEntity extends BaseEntity {

    /** 序列化id */
    private static final long serialVersionUID = 7380668097871543955L;
    private Integer id;

    private String supplierCode;

    private Integer manageFee;

    private Date backRate;

    private Integer orderFee;

    private Integer feeDay;

    private Integer checkType;

    private String invoiceTitle;

    private String supplierAccount;

    private String supplierTax;

    private String manager;

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

    public Integer getManageFee() {
        return manageFee;
    }

    public void setManageFee(Integer manageFee) {
        this.manageFee = manageFee;
    }

    public Date getBackRate() {
        return backRate;
    }

    public void setBackRate(Date backRate) {
        this.backRate = backRate;
    }

    public Integer getOrderFee() {
        return orderFee;
    }

    public void setOrderFee(Integer orderFee) {
        this.orderFee = orderFee;
    }

    public Integer getFeeDay() {
        return feeDay;
    }

    public void setFeeDay(Integer feeDay) {
        this.feeDay = feeDay;
    }

    public Integer getCheckType() {
        return checkType;
    }

    public void setCheckType(Integer checkType) {
        this.checkType = checkType;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
    }

    public String getSupplierAccount() {
        return supplierAccount;
    }

    public void setSupplierAccount(String supplierAccount) {
        this.supplierAccount = supplierAccount == null ? null : supplierAccount.trim();
    }

    public String getSupplierTax() {
        return supplierTax;
    }

    public void setSupplierTax(String supplierTax) {
        this.supplierTax = supplierTax == null ? null : supplierTax.trim();
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }
}