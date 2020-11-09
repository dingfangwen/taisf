package com.taisf.services.device.constant;

/**
 * 发送类型
 */
public enum SendTypeEnum {


    ALL(1,"全部发送");
    private int code;
    private String name;

    private SendTypeEnum(int code, String name) {
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

    public static SendTypeEnum getTypeByCode(int code) {
        SendTypeEnum[] enums = SendTypeEnum.values();
        for(SendTypeEnum enumtype : enums) {
            if(enumtype.getCode() == code) {
                return enumtype;
            }
        }
        return null;
    }

    }
