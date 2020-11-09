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
public enum LinkStatus {



    LINK_SUCCESS("在线", 1),

    LINK_RELEASE("释放", 2);




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
    private LinkStatus(String name, int code) {
        this.name = name;
        this.code = code;
    }


    /**
     *
     * @param code
     * @return
     */
    public static LinkStatus getByCode(Integer code) {
        if (Check.NuNObj(code)){
            return null;
        }
        for (LinkStatus c : LinkStatus.values()) {
            if (c.code == code) {
                return c;
            }
        }
        return null;
    }
}
