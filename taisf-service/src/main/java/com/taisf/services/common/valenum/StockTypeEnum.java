package com.taisf.services.common.valenum;

public enum StockTypeEnum {

	IN(1, "入库"),
	OUT(2, "出库");

	private int code;
	private String name;

	private StockTypeEnum(int code, String name) {
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

	public static StockTypeEnum getTypeByCode(int code) {
		StockTypeEnum[] enums = StockTypeEnum.values();
		for (StockTypeEnum enumtype : enums) {
			if (enumtype.getCode() == code) {
				return enumtype;
			}
		}
		return null;
	}

}
