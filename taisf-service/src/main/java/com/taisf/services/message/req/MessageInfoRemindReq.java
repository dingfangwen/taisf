package com.taisf.services.message.req;

import com.jk.framework.base.entity.BaseEntity;

import java.util.List;

public class MessageInfoRemindReq extends BaseEntity {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private List<MessageInfoReq> list;

	private String applicationCode;

	/**
	 * 平台 0 全部 1.安卓 2.ios 3.m站 4.微信 5.小程序
	 */
	private Integer platform;

	/**
	 * 用户id
	 */
	private String userId;

	public List<MessageInfoReq> getList() {
		return list;
	}

	public void setList(List<MessageInfoReq> list) {
		this.list = list;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
