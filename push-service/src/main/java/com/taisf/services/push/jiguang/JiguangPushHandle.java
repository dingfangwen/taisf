package com.taisf.services.push.jiguang;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.google.gson.Gson;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;

import com.taisf.services.push.constant.PushHandleConstant;
import com.taisf.services.push.core.PushPar;
import com.taisf.services.push.handle.PushHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * <p>苹果官方的推送消息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/1.
 * @version 1.0
 * @since 1.0
 */
@Service(PushHandleConstant.HANDLE_JIGUANG)
public class JiguangPushHandle extends PushJiguangConstant implements PushHandle {

    private static final Logger LOGGER = LoggerFactory.getLogger(JiguangPushHandle.class);

    /**
     * 极光的客户端信息
     */
    JPushClient jpushClient;

    @Override
    public Object sendPush(PushPar pushPar) {

        if (Check.NuNObj(jpushClient)){
            //初始化当前的推送客户端
            this.instanceJPushClient();
        }
        //拼装推送的相关信息
        PushPayload payload = buildPushObject_register(pushPar);

        try {
            PushResult result = jpushClient.sendPush(payload);
            LOGGER.info("Got result - " + result);
            return result;
        } catch (APIConnectionException e) {
            // Connection error, should retry later
            LOGGER.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            LOGGER.error("Should review the error, and fix the request", e);
            LOGGER.info("HTTP Status: " + e.getStatus());
            LOGGER.info("Error Code: " + e.getErrorCode());
            LOGGER.info("Error Message: " + e.getErrorMessage());
        }
        return null;
    }



    /**
     * 获取push的推送服务
     * @author afi
     * @return
     */
    private void instanceJPushClient(){
        if (Check.NuNStr(JIGUANG_APPKEY)){
            throw new JiguangPushException("极光的appid为空");
        }

        if (Check.NuNStr(JIGUANG_APPSECRET)){
            throw new JiguangPushException("极光的秘钥为空");
        }
        jpushClient = new JPushClient(JIGUANG_APPSECRET, JIGUANG_APPKEY);

        if (Check.NuNObj(jpushClient)){
            throw new JiguangPushException("初始化极光推送信息失败");
        }
    }


    /**
     * 获取注册信息
     * @author afi
     * @return
     */
    public PushPayload buildPushObject_register(PushPar pushPar) {
        Audience audience = Audience.registrationId(pushPar.getToken());
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(audience)
                .setMessage(this.getMsg(pushPar))
                .build();
    }


    private Message getMsg(PushPar pushPar){
    	return Message.newBuilder().setTitle(pushPar.getTitle())
    			.setMsgContent(pushPar.getContent())
    					.addExtras(pushPar.getExtra())
    			.build();
    }


}
