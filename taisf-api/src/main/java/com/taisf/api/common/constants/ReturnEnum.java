package com.taisf.api.common.constants;


/**
 * 
 *返回前端的错误码
 */
public enum ReturnEnum {
	TOKEN_INVALID(10001,"用户登录失效","用户登录失效"),
	ORDER_WAIT(30001,"订单未生成","订单未生成");

    private int code;
    private String name;
    private String desc;
    private ReturnEnum(int code,String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static ReturnEnum getTypeByCode(int code) {
    	ReturnEnum[] enums = ReturnEnum.values();
    	for(ReturnEnum enumtype:enums) {
            if(enumtype.getCode() == code) {
                return enumtype;
            }
        }
        return null;
    }
}
