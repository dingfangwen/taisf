package com.taisf.services.push.apple;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>苹果相关的推送消息</p>
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
@Component("pushAppleConstant")
public class PushAppleConstant {


    @Value("${certificate.path}")
    protected String CERTIFICATE_PATH;


    @Value("${certificate.passWord}")
    protected String CERTIFICATE_PASSWORD;

    @Value("${apple.env}")
    protected String APPLE_ENV;


    @Value("${certificate.path.user}")
    protected String CERTIFICATE_PATH_USER;


    @Value("${certificate.passWord.user}")
    protected String CERTIFICATE_PASSWORD_USER;



}
