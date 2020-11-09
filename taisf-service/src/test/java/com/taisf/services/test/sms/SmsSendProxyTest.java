package com.taisf.services.test.sms;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.head.Header;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.sms.proxy.SmsSendProxy;
import com.taisf.services.test.common.BaseTest;
import com.taisf.services.user.api.UserService;
import com.taisf.services.user.dto.*;
import com.taisf.services.user.entity.AccountLogEntity;
import com.taisf.services.user.vo.RegistInfoVO;
import com.taisf.services.user.vo.UserAccountVO;
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
public class SmsSendProxyTest extends BaseTest {

    @Resource(name = "sms.smsSendProxy")
    private SmsSendProxy smsSendProxy;


    @Test
    public void sendSmsTest() {

        String tel = "18911123545";
        String content = "你好";

        DataTransferObject dataTransferObject = smsSendProxy.sendSmsCode(tel, content);
        System.out.println(JsonEntityTransform.Object2Json(dataTransferObject));

    }


}
