package com.taisf.services.common.valenum;

/**
 * <p>商品属性</p>
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
public enum ProductSourceEnum {

    //1 清真 2 西餐 3 普通

    QINGZHEN(1,"清真"),
    XICAN(2,"西餐"),
    COMMON(3,"普通")
    ;
    private int code;
    private String name;

    private ProductSourceEnum(int code, String name) {
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

    public static ProductSourceEnum getTypeByCode(int code) {
        ProductSourceEnum[] enums = ProductSourceEnum.values();
        for(ProductSourceEnum enumtype:enums) {
            if(enumtype.getCode() == code) {
                return enumtype;
            }
        }
        return null;
    }

}
