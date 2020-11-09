package com.taisf.services.device.entity;

import com.jk.framework.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p> 设备绑定关系 </p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @version 1.0
 * @since 1.0
 */
public class DeviceLinkEntity extends BaseEntity {

    private static final long serialVersionUID = -99961040324798380L;

    /**
    * 主键id
    */
    private Integer id;
    /**
    * 订单编号
    */
    private String orderSn;
    /**
    * 格子编号
    */
    private String cellSn;
    /**
    * 设备编号
    */
    private String deviceId;
    /**
    * 餐柜fid
    */
    private String buffetFid;
    /**
    * 绑定状态 1: 绑定中 2: 已释放
    */
    private Integer linkStatus;
    /**
    * 申请时间
    */
    private Date applyTime;
    /**
    * 申请时间
    */
    private Date releaseTime;
    /**
    * 释放时间
    */
    private Date createTime;
    /**
    * 最后修改时间
    */
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getCellSn() {
        return cellSn;
    }

    public void setCellSn(String cellSn) {
        this.cellSn = cellSn;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getBuffetFid() {
        return buffetFid;
    }

    public void setBuffetFid(String buffetFid) {
        this.buffetFid = buffetFid;
    }

    public Integer getLinkStatus() {
        return linkStatus;
    }

    public void setLinkStatus(Integer linkStatus) {
        this.linkStatus = linkStatus;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}