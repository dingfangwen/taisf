package com.taisf.services.device.logic.bbb.vo;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;
import java.util.List;

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
public class DeviceBBBVO extends BaseEntity {


    private String title;
    private String appId;
    private String deviceId;
    private String deviceName;
    private String deviceStatus;
    private Integer cellNumCount;
    private Date createTime;

    private String statusDesc;


    private List<CellBBBVO> cells;


    public List<CellBBBVO> getCells() {
        return cells;
    }

    public void setCells(List<CellBBBVO> cells) {
        this.cells = cells;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public Integer getCellNumCount() {
        return cellNumCount;
    }

    public void setCellNumCount(Integer cellNumCount) {
        this.cellNumCount = cellNumCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
