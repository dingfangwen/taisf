package com.taisf.services.message.req;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

public class MessageInfoRemindRes extends BaseEntity{
    /**
	 * @Field @serialVersionUID : 
	 */
	private static final long serialVersionUID = 1L;

	/**系统当前时间（接口调用时间）*/
	private Date systemTime;
	/**消息主题*/
	private String subject;
	/**当前主题的消息数*/
	private Long count;

	public Date getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(Date systemTime) {
		this.systemTime = systemTime;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
}
