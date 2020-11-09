package com.taisf.services.user.vo;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>饭的vo</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/4/12.
 * @version 1.0
 * @since 1.0
 */
public class FanVO extends BaseEntity{

    /**
     * 名称
     */
    private String name;

    /**
     * 开始时间
     */
    private String start;

    /**
     * 结束时间
     */
    private String end;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
