package com.taisf.services.device.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.DateUtil;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.ValueUtil;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.device.api.PushService;
import com.taisf.services.device.constant.PushConstant;
import com.taisf.services.device.constant.PushTypeEnum;
import com.taisf.services.device.constant.SendTypeEnum;
import com.taisf.services.device.dto.PushMessage;
import com.taisf.services.device.entity.DeviceEntity;
import com.taisf.services.device.manager.DeviceServiceImpl;
import com.taisf.services.device.utils.SpringContextUtil;
import com.taisf.services.push.core.PushPar;
import com.taisf.services.push.handle.PushHandle;
import com.taisf.services.push.request.CellApplyRequest;
import com.taisf.services.push.request.CellCloseRequest;
import com.taisf.services.push.request.MoneySendRequest;
import com.taisf.services.push.request.SendRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component("push.pushServiceProxy")
public class PushServiceProxy implements PushService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PushServiceProxy.class);

    @Resource(name = "push.deviceServiceImpl")
    private DeviceServiceImpl deviceServiceImpl;

    @Autowired
    private SpringContextUtil springContext;


    /**
     * 取餐成功
     * @param cellCloseRequest
     * @return
     */
    @Override
    public DataTransferObject<Void> sendCloseCellSuccess(CellCloseRequest cellCloseRequest){

        LogUtil.info(LOGGER,"取餐成功消息,par:{}", JsonEntityTransform.Object2Json(cellCloseRequest));

        DataTransferObject<Void> dto = new DataTransferObject();
        //发送推送消息
        this.dealPush(dto,cellCloseRequest,cellCloseRequest.transPushPar());
        return dto;
    }

    /**
     * 申请格子成功
     * @param cellApplyRequest
     * @return
     */
    @Override
    public DataTransferObject<Void> sendApplyCellSuccess(CellApplyRequest cellApplyRequest){

        LogUtil.info(LOGGER,"发送推送申请成功消息,par:{}", JsonEntityTransform.Object2Json(cellApplyRequest));

        DataTransferObject<Void> dto = new DataTransferObject();

        //发送推送消息
        this.dealPush(dto,cellApplyRequest,cellApplyRequest.transPushPar());
        return dto;

    }


    /**
     * 开始发送push下拍戏
     * @param dto
     * @param sendRequest
     */
    private void dealPush(DataTransferObject dto ,SendRequest sendRequest,PushPar pushPar ){
        if (!dto.checkSuccess()){
            return;
        }
        LogUtil.info(LOGGER,"发送推送申请成功推送,par:{}", JsonEntityTransform.Object2Json(sendRequest));

        if (Check.NuNObj(sendRequest)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("参数异常");
            return ;
        }
        if (Check.NuNObjs(sendRequest.getUserId())) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("参数异常");
            return ;
        }
        DeviceEntity device = deviceServiceImpl.getDeviceByUserId(sendRequest.getUserId());
        if (Check.NuNObj(device)){
            LogUtil.info(LOGGER,"当前用户没有上报用户信息,par:{}", JsonEntityTransform.Object2Json(sendRequest));
            return ;
        }
        sendRequest.setPushType(device.getPushType());
        sendRequest.setToken(device.getDeviceToken());

        List<String> tokenList = new ArrayList<>();
        tokenList.add(device.getDeviceToken());
        pushPar.setToken(tokenList);
        PushHandle handle = springContext.getBean(PushTypeEnum.getTypeByCode(sendRequest.getPushType()).getHandle());
        handle.sendPush(pushPar);
    }




    /**
     * 发送消息
     * @param moneySendRequest
     * @return
     */
    @Override
    public DataTransferObject<Void> sendMoneySuccess(MoneySendRequest moneySendRequest) {

        LogUtil.info(LOGGER,"发送推送消息,par:{}", JsonEntityTransform.Object2Json(moneySendRequest));

        DataTransferObject<Void> dto = new DataTransferObject();
        if (Check.NuNObj(moneySendRequest)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("参数异常");
            return dto;
        }
        if (Check.NuNObjs(moneySendRequest.getUserId())) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("参数异常");
            return dto;
        }
        DeviceEntity device = deviceServiceImpl.getDeviceByUserId(moneySendRequest.getUserId());
        if (Check.NuNObj(device)){
            LogUtil.info(LOGGER,"当前用户没有上报用户信息,par:{}", JsonEntityTransform.Object2Json(moneySendRequest));
            return dto;
        }
        moneySendRequest.setPushType(device.getPushType());
        moneySendRequest.setToken(device.getDeviceToken());
        PushPar pushPar = moneySendRequest.transPushPar();
        PushHandle handle = springContext.getBean(PushTypeEnum.getTypeByCode(moneySendRequest.getPushType()).getHandle());
        handle.sendPush(pushPar);
        return dto;
    }


    @Override
    public DataTransferObject<Void> registDevice(DeviceEntity deviceEnity) {
        DataTransferObject<Void> dto = new DataTransferObject();
        if (Check.NuNObj(deviceEnity)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("参数异常");
            return dto;
        }
        if (Check.NuNObjs(deviceEnity.getRegId())) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("参数异常");
            return dto;
        }
        if (Check.NuNObjs(deviceEnity.getUserId())) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("参数异常");
            return dto;
        }
        if(Check.NuNObj(deviceEnity.getPushType())){
            deviceEnity.setPushType(PushTypeEnum.JIGUANG.getCode());
        }

        try {
            //获取用户信息
            DeviceEntity device = deviceServiceImpl.getDeviceByUserId(deviceEnity.getUserId());
            if(!Check.NuNObj(device)){
                if (ValueUtil.getStrValue(device.getRegId()).equals(ValueUtil.getStrValue(deviceEnity.getRegId()))){
                    return dto;
                }
            }
            deviceServiceImpl.updateOrSaveDevice(deviceEnity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【注册设备失败】error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("注册设备失败");
            return dto;
        }
        return dto;
    }

    @Override
    public DataTransferObject<Void> logoutDevice(DeviceEntity deviceEnity) {
        DataTransferObject<Void> dto = new DataTransferObject();
        if (Check.NuNObj(deviceEnity)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("参数异常");
            return dto;
        }

        if (Check.NuNObjs(deviceEnity.getRegId(), deviceEnity.getUserId())) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("参数异常");
            return dto;
        }

        try {
            deviceServiceImpl.updateUserLogout(deviceEnity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【注消设备失败】error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("注消设备失败");
            return dto;
        }
        return dto;
    }


    @Override
    public DataTransferObject<Void> pushMessage(List<String> userIds, PushMessage pushMessage) {
        DataTransferObject<Void> dto = new DataTransferObject();
        if (Check.NuNCollection(userIds)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("参数异常");
            return dto;
        }
        try {
            List<DeviceEntity> result = deviceServiceImpl.listDeviceByUserId(userIds);
            if (Check.NuNCollection(result)) {
                return dto;
            }
            Map<Integer, List<String>> pushMap = new HashMap<>();

            List<String> jiGuangPush = new ArrayList<String>();
            for (DeviceEntity device : result) {
                String token = device.getDeviceToken();
                jiGuangPush.add(token);
            }

            if (!Check.NuNCollection(jiGuangPush)){
                pushMap.put(PushTypeEnum.JIGUANG.getCode(), jiGuangPush);
            }
            for (Map.Entry<Integer, List<String>> entry : pushMap.entrySet()) {
                sendMessage(pushMessage, entry.getValue(), entry.getKey(), null);
            }
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【发送推送消息失败】error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("发送推送消息失败");
            return dto;
        }
        return dto;
    }

    private void sendMessage(PushMessage pushMessage, List<String> token, Integer pushType, Integer sendType) {
        PushHandle handle = springContext.getBean(PushTypeEnum.getTypeByCode(pushType).getHandle());
        Map<String, String> param = new HashMap<String, String>();
        param.put("messageType", pushMessage.getMessageType());
        param.put("sendTime", DateUtil.dateFormat(pushMessage.getSendTime(), DateUtil.timestampPattern));
        param.put("title", pushMessage.getTitle());
        param.put("content", pushMessage.getContent());
        param.put("noticeId", pushMessage.getNoticeId());
        param.put("messageUrl", pushMessage.getMessageUrl());
        param.put("bizContent", pushMessage.getBizContent());
        PushPar par = new PushPar();
        par.setSendType(sendType);
        par.setTitle(pushMessage.getTitle());
        par.setToken(token);
        par.setContent(pushMessage.getContent());
        par.setExtra(param);
        handle.sendPush(par);
    }

    private void sendPaingMessage(Integer pushType, PushMessage pushMessage) {
        int limit = PushConstant.PUSH_LIMIT;
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("limit", limit);
        queryParam.put("page", 1);
        queryParam.put("pushType", pushType);
        PushHandle handle = springContext.getBean(PushTypeEnum.getTypeByCode(pushType).getHandle());
        PagingResult<DeviceEntity> result = deviceServiceImpl.listDeviceByPushType(queryParam);
        Long totalRows = result.getTotal();
        Long totalPage = (totalRows - 1) / limit + 1;
        for (int i = 1; i <= totalPage; i++) {
            if (i != 1) {
                queryParam.put("page", i);
                result = deviceServiceImpl.listDeviceByPushType(queryParam);
            }
            List<String> tokenList = new ArrayList<String>();
            for (DeviceEntity device : result.getList()) {
                String token = device.getDeviceToken();
                tokenList.add(token);
            }
            sendMessage(pushMessage, tokenList, pushType, SendTypeEnum.ALL.getCode());
        }
    }
}
