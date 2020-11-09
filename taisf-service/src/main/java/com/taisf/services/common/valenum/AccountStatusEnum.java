package com.taisf.services.common.valenum;

import com.jk.framework.base.utils.Check;

/**
 * <p>账户状态</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/6/26.
 * @version 1.0
 * @since 1.0
 */
public enum AccountStatusEnum {

    AVAILABLE(1,"可用","正常可用中"){
        @Override
        public boolean checkOk() {
            return true;
        }
    },
    FORBIDDEN(2,"禁用","账户已禁用"),
    FREEZN(3,"冻结","账户已冻结");

    private int code;
    private String name;
    private String desc;
    private AccountStatusEnum(int code, String name, String desc) {
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

    public static AccountStatusEnum getTypeByCode(Integer code) {
        if (Check.NuNObj(code)){
            return null;
        }
        AccountStatusEnum[] enums = AccountStatusEnum.values();
        for(AccountStatusEnum enumtype:enums) {
            if(enumtype.getCode() == code) {
                return enumtype;
            }
        }
        return null;
    }

    /**
     * 验证当前状态是否可以交易
     * @return
     */
    public boolean checkOk(){
        return false;
    }
}
