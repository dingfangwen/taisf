package com.taisf.services.user.vo;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>周几的的vo</p>
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
public class DayVO extends BaseEntity{

    /**
     * 名称
     */
    private Boolean dayFlag;

    /**
     * 开始时间
     */
    private Integer week;

    /**
     * 显示名称
     */
    private String title;


    public Boolean getDayFlag() {
        return dayFlag;
    }

    public void setDayFlag(Boolean dayFlag) {
        this.dayFlag = dayFlag;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
