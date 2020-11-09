package com.taisf.services.enterprise.dto;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

/**
 * <p>更新状态</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/19.
 * @version 1.0
 * @since 1.0
 */
public class EnterpriseDayRequest extends BaseEntity{

    private static final long serialVersionUID = 301231231201446703L;

    /**
     * 编号
     */
    private String enterpriseCode;

    /**
     * 禁用天数
     */
    private Date dayTime;

    /**
     * 禁用天数
     */
    private String dayTimeStr;


    /**
     * 1: 配送 2:不配送
     */
    private Integer dayType;

    private String opCode;

    private String opName;

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public Date getDayTime() {
        return dayTime;
    }

    public void setDayTime(Date dayTime) {
        this.dayTime = dayTime;
    }

    public Integer getDayType() {
        return dayType;
    }

    public void setDayType(Integer dayType) {
        this.dayType = dayType;
    }

    public String getDayTimeStr() {
        return dayTimeStr;
    }

    public void setDayTimeStr(String dayTimeStr) {
        this.dayTimeStr = dayTimeStr;
    }
}
