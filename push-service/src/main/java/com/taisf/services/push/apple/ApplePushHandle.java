package com.taisf.services.push.apple;

import com.jk.framework.base.utils.Check;
//import com.notnoop.apns.APNS;
//import com.notnoop.apns.ApnsNotification;
//import com.notnoop.apns.ApnsService;
//import com.notnoop.apns.PayloadBuilder;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsNotification;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.PayloadBuilder;
import com.taisf.services.push.constant.PushHandleConstant;
import com.taisf.services.push.core.PushPar;
import com.taisf.services.push.handle.PushHandle;
import org.springframework.stereotype.Service;

import java.util.List;

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
@Service(PushHandleConstant.HANDLE_APPLE)
public class ApplePushHandle extends PushAppleConstant implements PushHandle {

    /**
     * 苹果官方的处理
     */
    private ApnsService service ;



    @Override
    public Object sendPush(PushPar pushPar) {

        if (Check.NuNObj(service)){
            //初始化当前的证书信息
            this.instanceAppleService();
        }

        PayloadBuilder payloadBuilder = APNS.newPayload();
        payloadBuilder.customField("customContent",pushPar.getExtra());
        String payload = payloadBuilder.badge(1).sound("default").localizedArguments().alertBody("content").
                alertTitle(pushPar.getTitle()).alertBody(pushPar.getContent()).build();
        Apns apns = JsonEntityTransform.json2Object(payload,Apns.class);
        System.out.println(apns.trans2par());
        List<ApnsNotification> apnsNotifications = (List<ApnsNotification>) service.push(pushPar.getToken(), apns.trans2par());
        return apnsNotifications;
    }

    /**
     * 获取苹果的推送服务
     * @author afi
     * @return
     */
    private void instanceAppleService(){
        if (Check.NuNStr(CERTIFICATE_PATH)){
            throw new ApplePushException("苹果证书路径为空");
        }

        if (Check.NuNStr(CERTIFICATE_PATH)){
            throw new ApplePushException("苹果证书密码为空");
        }

        ApnsService service = null ;
        if(APPLE_ENV.equals("dev")){
            service = APNS.newService().withCert(CERTIFICATE_PATH, CERTIFICATE_PASSWORD)
                    .withSandboxDestination()
                    .build();
        }else{
            service = APNS.newService().withCert(CERTIFICATE_PATH, CERTIFICATE_PASSWORD)
                    .withProductionDestination()
                    .build();
        }

        this.service =  service;
        if (Check.NuNObj(service)){
            throw new ApplePushException("初始化苹果推送信息失败");
        }
    }


}
