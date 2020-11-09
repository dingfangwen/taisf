package com.taisf.services.device.common.valenum;

import com.jk.framework.base.utils.Check;

/**
 * <p>设备状态</p>
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
public enum DeviceStatusShowEnum {

    //0：未知状态，1：在线，2：已离线，3：未激活，4：已禁用

    /**
     * 未知
     */
    UNKNOWN("未知", 0),

    /**
     * 在线
     */
    ON_LINE("在线", 1),

    /**
     * 已离线
     */
    OFF_LINE("已离线", 2),


    /**
     * 未激活
     */
    NO_ACTIVITY("未激活", 3),



    /**
     * 已禁用
     */
    FORBIDEN("已禁用", 0);



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
    private DeviceStatusShowEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }


    /**
     *
     * @param code
     * @return
     */
    public static DeviceStatusShowEnum getByCode(Integer code) {
        if (Check.NuNObj(code)){
            return null;
        }
        for (DeviceStatusShowEnum c : DeviceStatusShowEnum.values()) {
            if (c.code == code) {
                return c;
            }
        }
        return null;
    }
}
