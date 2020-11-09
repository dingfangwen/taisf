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
public enum OrdersStatusShowEnum {

    //10:待支付 11:部分支付 30:取消 40.已经退款 50.已支付 60.已配送 70.已签收

    /**
     * 待付款
     */
    NOPAYED("待付款", 1),

    /**
     * 已取消
     */
    CANCLED("已取消", 3),

    /**
     * 退款
     */
    REFUND("退款", 4),


    /**
     * 已支付
     */
    HAS_PAYED("已支付", 5),
    /**
     * 已配送
     */
    HAS_SEND("配送中", 6),


    /**
     * 完成
     */
    FINISH("完成", 7),

    /**
     * 退款成功
     */
    REFUND_YES("退款成功", 8),

    /**
     * 退款失败
     */
    REFUND_NO("退款失败", 9),


    /**
     * 未知
     */
    UNKNOWN("未知", 0);






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
    private OrdersStatusShowEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }



    // 普通方法
    public static OrdersStatusShowEnum getByCode(int code) {
        for (OrdersStatusShowEnum c : OrdersStatusShowEnum.values()) {
            if (c.code == code) {
                return c;
            }
        }
        return null;
    }
}
