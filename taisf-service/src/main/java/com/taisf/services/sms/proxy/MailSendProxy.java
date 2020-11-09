package com.taisf.services.sms.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.DateUtil;
import com.jk.framework.base.utils.MD5Util;
import com.jk.framework.common.utils.CloseableHttpUtil;
import com.jk.framework.log.utils.LogUtil;
import com.sun.mail.util.MailSSLSocketFactory;
import com.taisf.services.sms.constant.SmsConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayInputStream;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <p>发送邮箱</p>
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
@Service("mail.smsSendProxy")
public class MailSendProxy extends SmsConstant {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailSendProxy.class);

    /**
     * 发送短信
     * @param tel
     * @param msgCode
     */
    public DataTransferObject sendSmsCode(String tel,String msgCode){
        DataTransferObject dto = new DataTransferObject();
        LogUtil.info(LOGGER,"邮件入参,tel:{},content:{}", tel,msgCode);
        if (Check.NuNStr(tel)){
            dto.setErrorMsg("请填写邮件");
            return dto;
        }
        String content = "尊敬的馋滴用户您好，您的登录验证码为：" + msgCode +"，（有效期为10分钟），请您尽快登录，注意保密！";
        if (Check.NuNStr(content)){
            dto.setErrorMsg("请填写内容");
            return dto;
        }
        Session session = assembleSession(MAIL_PORT,MAIL_HOST);
        Message msg=new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(MAIL_FROM));
            msg.setSubject("【馋滴网】登录验证码");
            //设置收件人
            Address address[] = acceptAddressList(tel);
            msg.setRecipients(MimeMessage.RecipientType.TO, address);
//            msg.setText(content);
            msg.setContent(content, "text/html;charset=utf-8");
            Transport transport = session.getTransport();
            transport.connect(MAIL_FROM, MAIL_PASS);
            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            dto.setErrorMsg("发送邮件异常");
            LogUtil.error(LOGGER, "sendMsgFileDs方法发送邮件异常:{}", e);
        }
        LogUtil.info(LOGGER,"发邮件,tel:{},content:{}", tel,content);
        return dto;
    }


    /**
     * 设置收件地址
     * @param acceptAddress
     * @return
     */
    public Address[] acceptAddressList(String acceptAddress) {
        // 创建邮件的接收者地址，并设置到邮件消息中
        Address[] tos = null;
        try {
            tos = new InternetAddress[1];
            tos[0] = new InternetAddress(acceptAddress);
        } catch (AddressException e) {
            LogUtil.error(LOGGER, "返回邮件地址异常:{}", e);
        }
        return tos;
    }


    /**
     * 设置smtp
     * @param port
     * @param host
     * @return
     */
    public Session assembleSession( String port, String host) {
        Session session = null;
        Properties props=new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.port", port);
        props.setProperty("mail.smtp.host", host);//邮件服务器
        props.setProperty("mail.smtp.socketFactory.port", port);//设置ssl端口
        props.setProperty("mail.smtp.socketFactory.fallback", "false");

        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");


//        //开启安全协议
//        MailSSLSocketFactory sf = null;
//        try {
//            sf = new MailSSLSocketFactory();
//            sf.setTrustAllHosts(true);
//        } catch (GeneralSecurityException e1) {
//            e1.printStackTrace();
//        }
//        props.put("mail.smtp.ssl.socketFactory", sf);
        props.put("mail.smtp.ssl.enable", "false");




        session = Session.getInstance(props);
        return session;
    }

}
