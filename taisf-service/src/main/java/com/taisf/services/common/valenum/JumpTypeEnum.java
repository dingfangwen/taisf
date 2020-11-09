package com.taisf.services.common.valenum;

/**
 * <p>消息跳转类型</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2016/9/1.
 * @version 1.0
 * @since 1.0
 */
public enum JumpTypeEnum {

    //跳转方式:1 首页,2H5 2 定点列表

    INDEX(1,"首页"),
    H5(2,"H5"),
    ORDER_LIST(3,"订单列表"),
    ;
    private int code;
    private String name;

    private JumpTypeEnum(int code, String name) {
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

    public static JumpTypeEnum getTypeByCode(int code) {
        JumpTypeEnum[] enums = JumpTypeEnum.values();
        for(JumpTypeEnum enumtype:enums) {
            if(enumtype.getCode() == code) {
                return enumtype;
            }
        }
        return null;
    }

}
