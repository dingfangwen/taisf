package com.taisf.services.common.valenum;

import com.jk.framework.base.utils.Check;

/**
 * <p>用户状态/p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/26.
 * @version 1.0
 * @since 1.0
 */
public enum UserStatusEnum {


    AVAILABLE(1,"未激活"),
    ACTIVITY(2,"可用"),
    FORBIDDEN(3,"注销"){
        @Override
        public boolean checkOk() {
            return false;
        }
    },
    FREEZE(4,"冻结"){
        @Override
        public boolean checkOk() {
            return false;
        }
    };

    private int code;
    private String name;
    private UserStatusEnum(int code, String name) {
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

    public static UserStatusEnum getTypeByCode(Integer code) {
        if (Check.NuNObj(code)){
            return null;
        }
        UserStatusEnum[] enums = UserStatusEnum.values();
        for(UserStatusEnum enumtype:enums) {
            if(enumtype.getCode() == code) {
                return enumtype;
            }
        }
        return null;
    }

    public boolean checkOk(){
        return true;
    }
}
