package com.taisf.services.user.vo;

import com.jk.framework.base.entity.BaseEntity;
import com.taisf.services.common.util.WeekUtil;
import com.taisf.services.user.entity.UserEntity;

import java.util.List;

/**
 * <p>首页的信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/7.
 * @version 1.0
 * @since 1.0
 */
public class IndexVO extends IndexBaseVO{



    /**
     * 显示名 头信息
     */
    private String timeTitle =  "点餐（以下今天餐单）";

    /**
     * 显示名
     */
    private String timeMsg;

    /**
     * 时间倒计时
     */
    private Long timeLast = 0L;

    /**
     * 默认不可下单
     */
    private Boolean orderFlag = false;

    /**
     * 订单类型
     */
    private Integer orderType;

    /**
     * 供应商code
     */
    private String supplierCode;

    private Integer week = WeekUtil.getWeek();

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    /**
     * 天
     */
    private List<DayVO>  dayList;

    public List<DayVO> getDayList() {
        return dayList;
    }

    public void setDayList(List<DayVO> dayList) {
        this.dayList = dayList;
    }

    public String getTimeMsg() {
        return timeMsg;
    }

    public void setTimeMsg(String timeMsg) {
        this.timeMsg = timeMsg;
    }


    public Long getTimeLast() {
        return timeLast;
    }

    public void setTimeLast(Long timeLast) {
        this.timeLast = timeLast;
    }

    public String getTimeTitle() {
        return timeTitle;
    }

    public void setTimeTitle(String timeTitle) {
        this.timeTitle = timeTitle;
    }

    public Boolean getOrderFlag() {
        return orderFlag;
    }

    public void setOrderFlag(Boolean orderFlag) {
        this.orderFlag = orderFlag;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }
}
