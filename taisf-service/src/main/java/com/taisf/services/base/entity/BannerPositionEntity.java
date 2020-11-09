package com.taisf.services.base.entity;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

public class BannerPositionEntity extends BaseEntity{
    /**
     * 主键id
     */
    private Integer id;

    /**
     * APPID
     */
    private String appCode;

    /**
     * 位置名称
     */
    private String postionName;

    /**
     * 位置code
     */
    private String positionCode;

    /**
     * 是否删除 0:未删除 1:已经删除
     */
    private Integer isDel;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    public String getPostionName() {
        return postionName;
    }

    public void setPostionName(String postionName) {
        this.postionName = postionName == null ? null : postionName.trim();
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode == null ? null : positionCode.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
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