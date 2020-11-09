package com.taisf.services.common.valenum;

import com.jk.framework.base.utils.Check;

/**
 * <p>用户角色</p>
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
public enum UserRoleEnum {

    //1 用户 2老板

    USER(1,"普通餐"),
    BOSS(2,"老板餐")
    ;
    private int code;
    private String name;

    private UserRoleEnum(int code, String name) {
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

    public static UserRoleEnum getTypeByCode(Integer code) {
        if(Check.NuNObj(code)){
            return null;
        }
        UserRoleEnum[] enums = UserRoleEnum.values();
        for(UserRoleEnum enumtype:enums) {
            if(enumtype.getCode() == code) {
                return enumtype;
            }
        }
        return null;
    }

}
