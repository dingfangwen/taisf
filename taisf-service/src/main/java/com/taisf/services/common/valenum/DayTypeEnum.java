package com.taisf.services.common.valenum;

/**
 * <p>日历类型</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2016/4/1.
 * @version 1.0
 * @since 1.0
 */
public enum DayTypeEnum {


    SEND(1,"送餐"),
    NO(2,"不送餐"),
    ;
    private int code;
    private String name;

    private DayTypeEnum(int code, String name) {
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

    public static DayTypeEnum getTypeByCode(int code) {
        DayTypeEnum[] enums = DayTypeEnum.values();
        for(DayTypeEnum enumtype:enums) {
            if(enumtype.getCode() == code) {
                return enumtype;
            }
        }
        return null;
    }

}
