package com.taisf.services.device.common.valenum;

import com.jk.framework.base.utils.Check;

/**
 * <p>格子状态</p>
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
public enum CellUserStatus {

    //1：未使用，2：已占用，3：存物中，4：已存物，5：取物中

    /**
     * 未知
     */
    UNKNOWN("未知", 0),

    /**
     * 未使用
     */
    EMPTY("未使用", 1),

    /**
     * 已占用
     */
    OCCUPY("已占用", 2),


    /**
     * 存物中
     */
    HAS_ING("存物中", 3),


    /**
     * 已存物
     */
    HAS_ED("已存物", 4),


    /**
     * 存物中
     */
    OUT_ING("取物中", 5),

    ;



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
    private CellUserStatus(String name, int code) {
        this.name = name;
        this.code = code;
    }


    /**
     *
     * @param code
     * @return
     */
    public static CellUserStatus getByCode(Integer code) {
        if (Check.NuNObj(code)){
            return null;
        }
        for (CellUserStatus c : CellUserStatus.values()) {
            if (c.code == code) {
                return c;
            }
        }
        return null;
    }
}
