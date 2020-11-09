package com.taisf.services.sms.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.DateUtil;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.MD5Util;
import com.jk.framework.common.utils.CloseableHttpUtil;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.sms.constant.SmsConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>发送短信</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/29.
 * @version 1.0
 * @since 1.0
 */
@Service("sms.smsSendProxy")
public class SmsSendProxy extends SmsConstant {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsSendProxy.class);

    /**
     * 发送短信
     * @param tel
     * @param msgCode
     */
    public DataTransferObject sendSmsCode(String tel,String msgCode){
        DataTransferObject dto = new DataTransferObject();

        String content = "【馋滴网】验证码:" + msgCode +"，您正在使用短信验证。";
        LogUtil.info(LOGGER,"短线入参,tel:{},content:{}", tel,content);
        if (Check.NuNStr(tel)){
            dto.setErrorMsg("请填写电话");
            return dto;
        }
        if (Check.NuNStr(content)){
            dto.setErrorMsg("请填写内容");
            return dto;
        }
        Map<String,String> map = new HashMap<>();
        String seed = dealSeed();
        map.put(NAME_KEY,name);
        map.put(SEED_KEY,seed);
        map.put(DEST_KEY,tel);
        map.put(CONTENT_KEY,content);
        map.put(KEY_KEY,dealKey(seed));
        String rst = CloseableHttpUtil.sendFormPost(URL,map);
        if (!rst.contains("success")){
            dto.setErrorMsg(rst);
        }
        LogUtil.info(LOGGER,"发送短信,tel:{},content:{},结果:{}", tel,content,rst);
        return dto;
    }


    /**
     * 获取当前的时间戳
     * @return
     */
    private static String  dealSeed(){
       return DateUtil.dateFormat(new Date(),DateUtil.intTimestampPattern);
    }

    /**
     * 获取当前的时间戳
     * @return
     */
    private static String  dealKey(String seed){
        return MD5Util.MD5Encode(MD5Util.MD5Encode(password) + seed);
    }
}
