package com.taisf.services.message.vo;

import com.jk.framework.base.entity.BaseEntity;
import com.taisf.services.message.entity.MessageInfoEntity;

import java.util.Date;
import java.util.List;

public class MessageInfoVO extends BaseEntity{

	/**
	 * @Field @serialVersionUID : 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<MessageInfoEntity> list;
	
	private Date systemTime;

	public List<MessageInfoEntity> getList() {
		return list;
	}

	public void setList(List<MessageInfoEntity> list) {
		this.list = list;
	}

	public Date getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(Date systemTime) {
		this.systemTime = systemTime;
	}

	public MessageInfoVO(List<MessageInfoEntity> list, Date systemTime) {
		this.list = list;
		this.systemTime = systemTime;
	}

	public MessageInfoVO() {
	}
}
