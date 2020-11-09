package com.taisf.services.common.valenum;

/**
 * <p>余额操作类型</p>
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
public enum AccountTypeEnum {

    //账户变更类型 1:收入 2:消费 3:提现 4:充值

    INCOME(1,"收入"),
    CONSUME(2,"消费"),
    WITHDRAW(3,"提现"),
    FILL(4,"充值"),
    CHANGE(5,"转账"),
    REFUND(6,"退款"),
    ;
    private int code;
    private String name;

    private AccountTypeEnum(int code, String name) {
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

    public static AccountTypeEnum getTypeByCode(int code) {
        AccountTypeEnum[] enums = AccountTypeEnum.values();
        for(AccountTypeEnum enumtype:enums) {
            if(enumtype.getCode() == code) {
                return enumtype;
            }
        }
        return null;
    }

}
