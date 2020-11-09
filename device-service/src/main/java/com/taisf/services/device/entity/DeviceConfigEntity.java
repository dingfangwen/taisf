package com.taisf.services.device.entity;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

/**
 * <p> 设备配置信息 </p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @version 1.0
 * @since 1.0
 */
public class DeviceConfigEntity extends BaseEntity {

    private static final long serialVersionUID = -99961040324798380L;

    /**
    * 主键id
    */
    private Integer id;
    /**
    * 配置编号
    */
    private String configSn;
    /**
    * 地址
    */
    private String serverUrl;
    /**
    * appId
    */
    private String appId;
    /**
    * 秘钥
    */
    private String appSecret;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfigSn() {
        return configSn;
    }

    public void setConfigSn(String configSn) {
        this.configSn = configSn;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}