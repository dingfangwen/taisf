package com.taisf.services.common.valenum;

import com.jk.framework.base.utils.Check;

public enum EnterpriseTypeEnum {

	// 企业类型 1:平台委托 2:自主开发
	PLATFORM_DELEGATE(1, "平台委托"),
	SELF_DEVELOPMENT(2, "自主开发");
	
	private int code;
	private String name;

	private EnterpriseTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static EnterpriseTypeEnum getTypeByCode(Integer code) {
		if (Check.NuNObj(code)){
			return  null;
		}
		EnterpriseTypeEnum[] enums = EnterpriseTypeEnum.values();
		for (EnterpriseTypeEnum enumtype : enums) {
			if (enumtype.getCode() == code) {
				return enumtype;
			}
		}
		return null;
	}

}
