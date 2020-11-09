package com.taisf.services.device.logic.bbb.vo;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;
import java.util.Map;

/**
 * <p></p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2020/4/20 23:05
 * @version 1.0
 */
public class CellBBBVO extends BaseEntity {


    private Integer cellId;
    private String cellNo;
    private String appId;
    private String deviceId;
    private Integer cellStatus;
    private Date updateTime;


    private String cellInfo;


    public String getCellInfo() {
        return cellInfo;
    }

    public void setCellInfo(String cellInfo) {
        this.cellInfo = cellInfo;
    }

    public Integer getCellId() {
        return cellId;
    }


    public void setCellId(Integer cellId) {
        this.cellId = cellId;
    }

    public String getCellNo() {
        return cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(Integer cellStatus) {
        this.cellStatus = cellStatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
