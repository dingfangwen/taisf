package com.taisf.services.message.service;

import com.jk.framework.base.utils.UUIDGenerator;
import com.taisf.services.message.dao.MessageInfoDao;
import com.taisf.services.message.dao.MessageUserDao;
import com.taisf.services.message.entity.MessageInfoEntity;
import com.taisf.services.message.entity.MessageUserEntity;
import com.taisf.services.message.req.MessageInfoReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author:zhangzhengguang
 * @date:2018/9/11
 * @description:
 **/
@Component("base.messageInfoServiceImpl")
public class MessageInfoServiceImpl {

	private static final Logger LOGGER= LoggerFactory.getLogger(MessageInfoServiceImpl.class);

	@Autowired
	private MessageInfoDao messageInfoDao;

	@Autowired
	private MessageUserDao messageUserDao;

	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/11
	 * @description:保存
	 * @editor: wonderful
	 **/
	public int saveMessageInfo4User(MessageInfoEntity messageInfoEntity,String uid) {
		String fid = UUIDGenerator.hexUUID();
		messageInfoEntity.setFid(fid);
		int num = messageInfoDao.saveMessageInfo(messageInfoEntity);
		if (num > 0){
			MessageUserEntity messageUserEntity = new MessageUserEntity();
			messageUserEntity.setUserId(uid);
			messageUserEntity.setMessageFid(fid);
			messageUserDao.saveMessageUser(messageUserEntity);
		}

		return num;
	}


	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/11
	 * @description:保存
	 * @editor: wonderful
	 **/
	public int saveMessageInfo(MessageInfoEntity messageInfoEntity) {
		return 	messageInfoDao.saveMessageInfo(messageInfoEntity);
	}

	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/11
	 * @description:更新
	 **/
	public int updateMessageInfo(MessageInfoEntity entity) {
		return messageInfoDao.updateMessageInfo(entity);
	}

	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/11
	 * @description:条件查询返回集合
	 **/
	public List<MessageInfoEntity> getMessageInfoList(MessageInfoReq request) {
		return messageInfoDao.getMessageInfoList(request);
	}




}
