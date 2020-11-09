package com.taisf.services.common.valenum;

/**
 * <p>订单展示状态</p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi
 * @version 1.0
 * @since 1.0
 */
public enum EnterpriseOrderStatsStatusShowEnum {


    /**
     * 退款中
     */
    REFUND("待付款", 1),

    /**
     *
     */
    SEND("配送中", 2);


    // 成员变量
    private String name;
    private int code;


    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    // 构造方法
    private EnterpriseOrderStatsStatusShowEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }


    // 普通方法
    public static EnterpriseOrderStatsStatusShowEnum getByCode(int code) {
        for (EnterpriseOrderStatsStatusShowEnum c : EnterpriseOrderStatsStatusShowEnum.values()) {
            if (c.code == code) {
                return c;
            }
        }
        return null;
    }
}
