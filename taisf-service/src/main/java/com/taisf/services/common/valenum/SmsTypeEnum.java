package com.taisf.services.common.valenum;

import com.jk.framework.base.utils.Check;

/**
 * <p>验证码类型</p>
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
public enum SmsTypeEnum {


    OPEN_REGIST(200,10000,"用户注册"),
    USER_REGIST(100,10000,"用户注册"),
    USER_LOG(101,10000,"用户登录"),
    PWD_USER(102,10000,"修改密码"),
    PWD_ACCOUNT(103,10000,"修改支付密码")
    ;
    private int code;
    private int time;
    private String name;


    private SmsTypeEnum(int code,int time, String name) {
        this.code = code;
        this.time = time;
        this.name = name;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static SmsTypeEnum getTypeByCode(Integer code) {
        if(Check.NuNObj(code)){
            return null;
        }
        SmsTypeEnum[] enums = SmsTypeEnum.values();
        for(SmsTypeEnum enumtype:enums) {
            if(enumtype.getCode() == code) {
                return enumtype;
            }
        }
        return null;
    }

}
