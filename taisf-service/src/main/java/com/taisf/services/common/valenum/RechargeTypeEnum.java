package com.taisf.services.common.valenum;

import com.jk.framework.base.utils.Check;

/**
 * <p>充值类型</p>
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
public enum RechargeTypeEnum {


    USER(1,"用户"),
    ENTERPRISE(2,"企业")
    ;
    private int code;
    private String name;

    private RechargeTypeEnum(int code, String name) {
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

    public static RechargeTypeEnum getTypeByCode(Integer code) {
        if(Check.NuNObj(code)){
            return null;
        }
        RechargeTypeEnum[] enums = RechargeTypeEnum.values();
        for(RechargeTypeEnum enumtype:enums) {
            if(enumtype.getCode() == code) {
                return enumtype;
            }
        }
        return null;
    }

}
