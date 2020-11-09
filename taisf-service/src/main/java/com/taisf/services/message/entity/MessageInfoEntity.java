package com.taisf.services.message.entity;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

public class MessageInfoEntity extends BaseEntity{

    private static final long serialVersionUID = -1396734085107067060L;
    private Integer id;

    /**
     * 编号
     */
    private String fid;

    /**
     * 应用 jkHospital 健客医院 jkAgent 健客行 jkDiabetes 糖尿病 jkCloudClinic 诊所
     */
    private String applicationCode;

    /**
     * 平台 0 全部 1.安卓 2.ios 3.m站 4.微信 5.小程序
     */
    private Integer platform;

    /**
     * 主题
     */
    private String subject;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subhead;

    /**
     * 消息内容
     */
    private String content;

    private String url;

    /**
     * 跳转方式:1原生,2H5
     */
    private Integer jumpType;

    /**
     * 消息状态 0 禁用 1 正常
     */
    private Integer state;

    /**
     * 是否指定用户 0:否 1:是
     */
    private Integer userSpecify;

    /**
     * 是否只通知用户 0:否 1:是
     */
    private Integer userNotify;

    /**
     * 关联推送Id
     */
    private Integer taskId;

    /**
     * 生效时间
     */
    private Date effectiveTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 指定用户列表 以,分隔
     */
    private String userIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode == null ? null : applicationCode.trim();
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSubhead() {
        return subhead;
    }

    public void setSubhead(String subhead) {
        this.subhead = subhead == null ? null : subhead.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getJumpType() {
        return jumpType;
    }

    public void setJumpType(Integer jumpType) {
        this.jumpType = jumpType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
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

    public Integer getUserSpecify() {
        return userSpecify;
    }

    public void setUserSpecify(Integer userSpecify) {
        this.userSpecify = userSpecify;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getUserNotify() {
        return userNotify;
    }

    public void setUserNotify(Integer userNotify) {
        this.userNotify = userNotify;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }
}