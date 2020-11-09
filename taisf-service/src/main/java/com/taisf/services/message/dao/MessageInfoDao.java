package com.taisf.services.message.dao;

import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.UUIDGenerator;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.message.entity.MessageInfoEntity;
import com.taisf.services.message.req.MessageInfoReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author:zhangzhengguang
 * @date:2018/9/11
 * @description:
 **/
@Repository
public class MessageInfoDao extends BaseDao {
	private static final Logger LOGGER= LoggerFactory.getLogger(MessageInfoDao.class);

	private String SQLID = "base.messageInfoDao.";




	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/11
	 * @description:保存
	 **/
	public int saveMessageInfo(MessageInfoEntity messageInfoEntity) {
		if (Check.NuNStr(messageInfoEntity.getFid())){
			messageInfoEntity.setFid(UUIDGenerator.hexUUID());
		}
		if (Check.NuNObj(messageInfoEntity.getCreateTime())) {
			messageInfoEntity.setCreateTime(new Date());
		}
		if (Check.NuNObj(messageInfoEntity.getEffectiveTime())) {
			messageInfoEntity.setEffectiveTime(new Date());
		}
		return mybatisDaoContext.save(SQLID + "saveMessageInfo", messageInfoEntity);
	}

	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/11
	 * @description:更新
	 **/
	public int updateMessageInfo(MessageInfoEntity entity) {
		return mybatisDaoContext.update(SQLID + "updateByPrimaryKeySelective", entity);
	}

	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/11
	 * @description:条件查询返回集合
	 **/
	public List<MessageInfoEntity> getMessageInfoList(MessageInfoReq request) {
		return mybatisDaoContext.findAll(SQLID + "getMessageInfoList", request);
	}






}