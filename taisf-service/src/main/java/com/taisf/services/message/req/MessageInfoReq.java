package com.taisf.services.message.req;

import com.jk.framework.base.page.PageRequest;

import java.util.Date;

/**
 * @author zhangzhengguang
 * @create 2018-09-11
 **/
public class MessageInfoReq extends PageRequest {
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 上次数据获取时间
     */
    private Date lastTime;

    /**
     * 标题
     */
    private String title;

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
	 * 用户id 前端鉴权使用
	 */
    private String userId;

    /**
     * 系统当前时间
     */
    private Date sysTime;


    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

    public Date getSysTime() {
        return sysTime;
    }

    public void setSysTime(Date sysTime) {
        this.sysTime = sysTime;
    }
}
