package com.taisf.services.message.api;

import com.jk.framework.base.entity.DataTransferObject;
import com.taisf.services.message.entity.MessageInfoEntity;
import com.taisf.services.message.req.MessageInfoReq;

import java.util.List;

/**
 * @author:zhangzhengguang
 * @date:2018/9/11
 * @description:
 **/
public interface MessageInfoService {

	/**
	 * 取餐发送消息
	 * @param uid
	 * @return
	 */
	DataTransferObject<Void> sendMessage4CloseCell(String uid);


	/**
	 * 成功发送消息
	 * @param uid
	 * @return
	 */
	DataTransferObject<Void> sendMessage4ApplyCell(String uid,String address,String buffetFid,String cellNum);


	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/11
	 * @description:保存
	 **/
	DataTransferObject<MessageInfoEntity> saveMessageInfo(MessageInfoEntity messageInfoEntity);

	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/11
	 * @description:更新
	 **/
	DataTransferObject<Void> updateMessageInfo(MessageInfoEntity entity);

	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/11
	 * @description:条件查询返回集合
	 **/
	DataTransferObject<List<MessageInfoEntity>> getMessageInfoList(MessageInfoReq request);

}
