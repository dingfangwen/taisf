package com.taisf.services.test.sms;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.sms.proxy.MailSendProxy;
import com.taisf.services.sms.proxy.SmsSendProxy;
import com.taisf.services.sms.util.RegexUtil;
import com.taisf.services.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * <p>账户的dao测试</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/3/21.
 * @version 1.0
 * @since 1.0
 */
public class MailSendProxyTest extends BaseTest {

    @Resource(name = "mail.smsSendProxy")
    private MailSendProxy mailSendProxy;

    @Resource(name = "sms.smsSendProxy")
    private SmsSendProxy smsSendProxy;


    @Test
    public void sendSmsTest() {
        String tel = "dingfangwen@jianke.com";
        String content = "你好";
        DataTransferObject dataTransferObject = mailSendProxy.sendSmsCode(tel, content);
        System.out.println(JsonEntityTransform.Object2Json(dataTransferObject));
    }


    @Test
    public void sendTest() {
        String tel = "dingfangwen@jianke.com";
//        String tel = "18911123545";
        String content = "123";
        DataTransferObject dataTransferObject = dealSendCode(tel, content);
        System.out.println(JsonEntityTransform.Object2Json(dataTransferObject));
    }



    /**
     *
     * @param userTel
     * @param msgCode
     * @return
     */
    private DataTransferObject dealSendCode(String userTel,String msgCode){
        DataTransferObject dataTransferObject ;
        if (RegexUtil.checkMail(userTel)){
            dataTransferObject = mailSendProxy.sendSmsCode(userTel, msgCode);
        }else if(RegexUtil.checkPhone(userTel)){
            dataTransferObject = smsSendProxy.sendSmsCode(userTel, msgCode);
        }else {
            dataTransferObject  = new DataTransferObject();
            dataTransferObject.setErrorMsg("异常的账号格式");
        }
        return dataTransferObject;

    }

}
