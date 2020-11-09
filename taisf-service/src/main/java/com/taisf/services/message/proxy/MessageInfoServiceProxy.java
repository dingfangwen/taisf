package com.taisf.services.message.proxy;

import com.jk.framework.base.constant.YesNoEnum;
import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.common.valenum.ApplicationCodeEnum;
import com.taisf.services.common.valenum.JumpTypeEnum;
import com.taisf.services.message.api.MessageInfoService;
import com.taisf.services.message.entity.MessageInfoEntity;
import com.taisf.services.message.req.MessageInfoReq;
import com.taisf.services.message.service.MessageInfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author:zhangzhengguang
 * @date:2018/9/11
 * @description:
 **/
@Service("basedata.messageInfoServiceProxy")
public class MessageInfoServiceProxy implements MessageInfoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageInfoServiceProxy.class);


	@Autowired
	private MessageInfoServiceImpl messageInfoService;

	/**
	 * 取餐发送消息
	 * @param uid
	 * @return
	 */
	@Override
	public DataTransferObject<Void> sendMessage4CloseCell(String uid){

		DataTransferObject<Void> dto = new DataTransferObject<>();
		LogUtil.info(LOGGER, "sendMessage4CloseCell uid:{}", JsonEntityTransform.Object2Json(uid));
		if (Check.NuNStr(uid)) {
			dto.setErrorMsg("参数异常");
			return dto;
		}
		try {
			MessageInfoEntity messageInfoEntity = new MessageInfoEntity();
			messageInfoEntity.setApplicationCode(ApplicationCodeEnum.USER.getApplicationCode());
			messageInfoEntity.setTitle("取餐成功");
			String cxt = "客官您好，您的餐品已成功取出，祝您用餐愉快！";
			messageInfoEntity.setContent(cxt);
			messageInfoEntity.setJumpType(JumpTypeEnum.ORDER_LIST.getCode());
			messageInfoEntity.setUserSpecify(YesNoEnum.YES.getCode());
			//保存消息
			messageInfoService.saveMessageInfo4User(messageInfoEntity,uid);
		} catch (Exception e) {
			dto.setErrorMsg("保存MessageInfo异常");
			LogUtil.error(LOGGER, "保存MessageInfo异常:{}", e);
		}
		return dto;
	}


	/**
	 * 成功发送消息
	 * @param uid
	 * @return
	 */
	@Override
	public DataTransferObject<Void> sendMessage4ApplyCell(String uid,String address,String buffetFid,String cellNum) {
		DataTransferObject<Void> dto = new DataTransferObject<>();
		LogUtil.info(LOGGER, "sendMessage4ApplyCell uid:{}", JsonEntityTransform.Object2Json(uid));
		if (Check.NuNStr(uid)) {
			dto.setErrorMsg("参数异常");
			return dto;
		}
		try {
			MessageInfoEntity messageInfoEntity = new MessageInfoEntity();
			messageInfoEntity.setApplicationCode(ApplicationCodeEnum.USER.getApplicationCode());
			messageInfoEntity.setTitle("取餐提醒");
			String cxt = "客官您好，您预定的餐食已出炉啦，请您到：{address}{buffetFid}柜{cellNum}格自助取餐。点击查看详情";
			cxt = cxt.replace("{address}",address);
			cxt = cxt.replace("{buffetFid}",buffetFid);
			cxt = cxt.replace("{cellNum}",cellNum);
			messageInfoEntity.setContent(cxt);
			messageInfoEntity.setJumpType(JumpTypeEnum.ORDER_LIST.getCode());
			messageInfoEntity.setUserSpecify(YesNoEnum.YES.getCode());
			//保存消息
			messageInfoService.saveMessageInfo4User(messageInfoEntity,uid);
		} catch (Exception e) {
			dto.setErrorMsg("保存MessageInfo异常");
			LogUtil.error(LOGGER, "保存MessageInfo异常:{}", e);
		}
		return dto;
	}




	@Override
	public DataTransferObject<MessageInfoEntity> saveMessageInfo(MessageInfoEntity messageInfoEntity) {
		DataTransferObject<MessageInfoEntity> dto = new DataTransferObject<>();
		LogUtil.info(LOGGER, "saveMessageInfo messageInfoEntity:{}", JsonEntityTransform.Object2Json(messageInfoEntity));
		if (Check.NuNObj(messageInfoEntity) || Check.NuNObjs(messageInfoEntity.getUserNotify(), messageInfoEntity.getUserSpecify())) {
			dto.setErrorMsg("参数异常");
			return dto;
		}
		try {
			messageInfoService.saveMessageInfo(messageInfoEntity);
			dto.setData(messageInfoEntity);
		} catch (Exception e) {
			dto.setErrorMsg("保存MessageInfo异常");
			LogUtil.error(LOGGER, "保存MessageInfo异常:{}", e);
		}
		return dto;
	}

	@Override
	public DataTransferObject<Void> updateMessageInfo(MessageInfoEntity entity) {
		DataTransferObject<Void> dto = new DataTransferObject<>();
		try {
			messageInfoService.updateMessageInfo(entity);
		} catch (Exception e) {
			dto.setErrorMsg("更新MessageInfo异常");
			LogUtil.error(LOGGER, "更新MessageInfo异常:{}", e);
		}
		return dto;
	}

	@Override
	public DataTransferObject<List<MessageInfoEntity>> getMessageInfoList(MessageInfoReq request) {
		DataTransferObject<List<MessageInfoEntity>> dto = new DataTransferObject<>();
		try {
			List<MessageInfoEntity> messageInfoList = messageInfoService.getMessageInfoList(request);
			dto.setData(messageInfoList);
		} catch (Exception e) {
			dto.setErrorMsg("查询MessageInfo异常");
			LogUtil.error(LOGGER, "查询MessageInfo异常:{}", e);
		}
		return dto;
	}



}
