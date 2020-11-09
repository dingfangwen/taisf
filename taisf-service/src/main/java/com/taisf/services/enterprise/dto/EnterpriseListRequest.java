package com.taisf.services.enterprise.dto;

import com.jk.framework.base.page.PageRequest;

import java.util.Date;

public class EnterpriseListRequest extends PageRequest {

	private static final long serialVersionUID = -8308160586793003064L;

	private String enterpriseCode;

	private String enterpriseName;

	private Date openTime;

	private Date tillTime;

	private Integer enterpriseType;
	
	private String manger;

	private  String supplierCode;

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getTillTime() {
		return tillTime;
	}

	public void setTillTime(Date tillTime) {
		this.tillTime = tillTime;
	}

	public Integer getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(Integer enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	public String getManger() {
		return manger;
	}

	public void setManger(String manger) {
		this.manger = manger;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
