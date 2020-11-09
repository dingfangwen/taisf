package com.taisf.services.common.valenum;

public enum FinanceCheckTypeEnum {

	// 企业财务结算类型 1:月结 2:季结 3:半年结 4:年结
	MONTH(1, "月结"),
	QUARTER(2, "季结"),
	HALF_YEAR(3, "半年结"),
	YEAR(4, "年结");
	
	private int code;
	private String name;

	private FinanceCheckTypeEnum(int code, String name) {
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

	public static FinanceCheckTypeEnum getTypeByCode(int code) {
		FinanceCheckTypeEnum[] enums = FinanceCheckTypeEnum.values();
		for (FinanceCheckTypeEnum enumtype : enums) {
			if (enumtype.getCode() == code) {
				return enumtype;
			}
		}
		return null;
	}

}
