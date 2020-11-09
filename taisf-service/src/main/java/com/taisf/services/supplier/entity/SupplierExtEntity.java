package com.taisf.services.supplier.entity;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

/**
 * <p>
 * 供应商的扩展信息
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
public class SupplierExtEntity extends BaseEntity {

    /** 序列化id */
    private static final long serialVersionUID = 7380668097871543955L;
    private Integer id;

    private String supplierCode;

    private String qualificationUrl;

    private Date qualificationTime;

    private String licenseUrl;

    private String productivityUrl;

    private String businessUrl;

    private String truckUrl;

    private String scaleUrl;

    private String consumerUrl;

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

    public String getQualificationUrl() {
        return qualificationUrl;
    }

    public void setQualificationUrl(String qualificationUrl) {
        this.qualificationUrl = qualificationUrl == null ? null : qualificationUrl.trim();
    }

    public Date getQualificationTime() {
        return qualificationTime;
    }

    public void setQualificationTime(Date qualificationTime) {
        this.qualificationTime = qualificationTime;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl == null ? null : licenseUrl.trim();
    }

    public String getProductivityUrl() {
        return productivityUrl;
    }

    public void setProductivityUrl(String productivityUrl) {
        this.productivityUrl = productivityUrl == null ? null : productivityUrl.trim();
    }

    public String getBusinessUrl() {
        return businessUrl;
    }

    public void setBusinessUrl(String businessUrl) {
        this.businessUrl = businessUrl == null ? null : businessUrl.trim();
    }

    public String getTruckUrl() {
        return truckUrl;
    }

    public void setTruckUrl(String truckUrl) {
        this.truckUrl = truckUrl == null ? null : truckUrl.trim();
    }

    public String getScaleUrl() {
        return scaleUrl;
    }

    public void setScaleUrl(String scaleUrl) {
        this.scaleUrl = scaleUrl == null ? null : scaleUrl.trim();
    }

    public String getConsumerUrl() {
        return consumerUrl;
    }

    public void setConsumerUrl(String consumerUrl) {
        this.consumerUrl = consumerUrl == null ? null : consumerUrl.trim();
    }
}