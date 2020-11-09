package com.taisf.services.enterprise.entity;

import java.util.List;

import com.jk.framework.base.entity.BaseEntity;

public class EnterpriseModel extends BaseEntity{

	private static final long serialVersionUID = -4003577591885489185L;
	
	private EnterpriseEntity enterpriseEntity;
	
	private EnterpriseConfigEntity configEntity;
	
	private List<EnterpriseAddressEntity> addressEntityList;
	
	private EnterpriseFinanceEntity financeEntity;

	public EnterpriseEntity getEnterpriseEntity() {
		return enterpriseEntity;
	}

	public void setEnterpriseEntity(EnterpriseEntity enterpriseEntity) {
		this.enterpriseEntity = enterpriseEntity;
	}

	public EnterpriseConfigEntity getConfigEntity() {
		return configEntity;
	}

	public void setConfigEntity(EnterpriseConfigEntity configEntity) {
		this.configEntity = configEntity;
	}

	public List<EnterpriseAddressEntity> getAddressEntityList() {
		return addressEntityList;
	}

	public void setAddressEntityList(List<EnterpriseAddressEntity> addressEntityList) {
		this.addressEntityList = addressEntityList;
	}

	public EnterpriseFinanceEntity getFinanceEntity() {
		return financeEntity;
	}

	public void setFinanceEntity(EnterpriseFinanceEntity financeEntity) {
		this.financeEntity = financeEntity;
	}

}
