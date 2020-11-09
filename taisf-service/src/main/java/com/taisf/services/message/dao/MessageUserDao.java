package com.taisf.services.message.dao;

import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.message.entity.MessageUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @author:afi
 * @date:2018/9/11
 * @description:
 **/
@Repository
public class MessageUserDao extends BaseDao {
	private static final Logger LOGGER= LoggerFactory.getLogger(MessageUserDao.class);

	private String SQLID = "base.messageUserDao.";



	/**
	 * @author:afi
	 * @date:2018/9/11
	 * @description:保存
	 **/
	public int saveMessageUser(MessageUserEntity messageUserEntity) {

		return mybatisDaoContext.save(SQLID + "saveMessageUser", messageUserEntity);
	}



}