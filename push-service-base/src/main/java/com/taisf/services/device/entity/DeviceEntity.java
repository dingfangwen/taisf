package com.taisf.services.device.entity;

import com.jk.framework.base.entity.BaseEntity;

public class DeviceEntity extends BaseEntity{
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 标题
     */
    private String regId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 推送消息的token
     */
    private String deviceToken;

    /**
     * 推送类型：1：苹果 2：小米 3：华为 4：极光
     */
    private Integer pushType;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 版本
     */
    private String version;

    /**
     * 应用code
     */
    private String applicationcode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId == null ? null : regId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken == null ? null : deviceToken.trim();
    }

    public Integer getPushType() {
        return pushType;
    }

    public void setPushType(Integer pushType) {
        this.pushType = pushType;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getApplicationcode() {
        return applicationcode;
    }

    public void setApplicationcode(String applicationcode) {
        this.applicationcode = applicationcode == null ? null : applicationcode.trim();
    }
}