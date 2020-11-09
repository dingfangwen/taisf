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
public enum ApplicationCodeEnum {


    USER(1,"user","用户"){
        @Override
        public UserTypeEnum getUserType() {
            return UserTypeEnum.YONGHU;
        }
    },
    H5(3,"open","open"){
        @Override
        public UserTypeEnum getUserType() {
            return UserTypeEnum.YONGHU;
        }
    },
    KNIGHT(2,"knight","骑士"){
        @Override
        public UserTypeEnum getUserType() {
            return UserTypeEnum.SONGCAN;
        }
    }
    ;
    private int code;
    private String applicationCode;
    private String name;

    public String getApplicationCode() {
        return applicationCode;
    }

    private ApplicationCodeEnum(int code, String applicationCode, String name) {
        this.code = code;
        this.applicationCode = applicationCode;
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

    public static ApplicationCodeEnum getTypeByCode(Integer  code) {
        if (Check.NuNObj(code)){
            return null;
        }
        ApplicationCodeEnum[] enums = ApplicationCodeEnum.values();
        for(ApplicationCodeEnum enumtype:enums) {
            if(enumtype.getCode() == code) {
                return enumtype;
            }
        }
        return null;
    }


    /**
     * 根据code获取类型
     * @param applicationCode
     * @return
     */
    public static ApplicationCodeEnum getTypeByApplicationCode(String applicationCode) {
        if(Check.NuNStr(applicationCode)){
            return null;
        }
        ApplicationCodeEnum[] enums = ApplicationCodeEnum.values();
        for(ApplicationCodeEnum enumtype:enums) {
            if(enumtype.getApplicationCode().equals(applicationCode)) {
                return enumtype;
            }
        }
        return null;
    }


    /**
     * 获取当前的用户类型
     * @return
     */
    public UserTypeEnum getUserType(){
        return null;
    }

}
