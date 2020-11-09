package com.taisf.services.enterprise.vo;

import com.taisf.services.enterprise.entity.EnterpriseEntity;

public class EnterpriseExtVO extends EnterpriseEntity {

	private static final long serialVersionUID = -1668510788153905796L;
	
	/**
     * 是否午餐
     */
    private Integer forLunch;
    
    /**
     * 是否晚餐
     */
    private Integer forDinner;

	public Integer getForLunch() {
		return forLunch;
	}

	public void setForLunch(Integer forLunch) {
		this.forLunch = forLunch;
	}

	public Integer getForDinner() {
		return forDinner;
	}

	public void setForDinner(Integer forDinner) {
		this.forDinner = forDinner;
	}

}
