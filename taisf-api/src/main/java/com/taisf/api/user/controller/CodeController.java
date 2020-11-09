package com.taisf.api.user.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.head.Header;
import com.jk.framework.base.rst.ResponseDto;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.DateUtil;
import com.jk.framework.base.utils.UUIDGenerator;
import com.jk.framework.cache.redis.api.RedisOperations;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.api.common.abs.AbstractController;
import com.taisf.api.util.HeaderUtil;
import com.taisf.services.common.valenum.SmsTypeEnum;
import com.taisf.services.sms.proxy.MailSendProxy;
import com.taisf.services.sms.proxy.SmsSendProxy;
import com.taisf.services.sms.util.RegexUtil;
import com.taisf.services.user.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <p>用户先关接口</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/send")
public class CodeController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeController.class);

    //短信接口地址
    private static final   String url = "http://112.90.92.102:16655/smsgwhttp/sms/submit";

    /**
     * 图片TOKEN rediskey
     */
    public static final String SMS_IMG_TOKEN = "SMS_IMG_TOKEN_";

    /**
     * 图片rediskey
     */
    public static final String SMS_IMG = "SMS_IMG_";


    /**
     * 图片验证码有效期
     */
    public static final Integer SMS_IMG_SECONDS = 2 * 60;


    /**
     * 图片token有效期
     */
    public static final Integer SMS_IMG_TOKEN_SECONDS = 60 * 60;




    @Autowired
    private UserService userService;

    @Autowired
    private RedisOperations redisOperation;


    @Autowired
    private DefaultKaptcha captchaProducer;


    @Resource(name = "sms.smsSendProxy")
    private SmsSendProxy smsSendProxy;

    @Resource(name = "mail.smsSendProxy")
    private MailSendProxy mailSendProxy;


    /**
     * 获取token
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getToken", method = RequestMethod.GET)
    public @ResponseBody
    ResponseDto getToken(HttpServletRequest request, HttpServletResponse response){
        DataTransferObject<Map<String, String>> dto = new DataTransferObject();
        String tokenUUID = UUIDGenerator.hexUUID();
        Map<String, String> token = new HashMap<String, String>();
        token.put("token", tokenUUID);
        dto.setData(token);
        redisOperation.setex(SMS_IMG_TOKEN + tokenUUID, SMS_IMG_TOKEN_SECONDS, "");
        return dto.trans2Res();
    }

    /**
     * 获取图片验证码
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "authCode", method = RequestMethod.GET)
    public @ResponseBody
    ResponseDto authCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String,Object> param = getMap(request);
        if (Check.NuNObjs(param)) {
            return new ResponseDto("参数异常");
        }
        String token = (String)param.get("token");
        if(!redisOperation.exists(SMS_IMG_TOKEN + token)){
            return new ResponseDto("请刷新页面，重新注册");
        }
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");


        String capText = captchaProducer.createText();
        LogUtil.info(LOGGER,"当前的验证码为:{},token:{}",capText,token);
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        redisOperation.setex(SMS_IMG + token, SMS_IMG_SECONDS, capText);
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }


    /**
     * 发送验证码
     * @param request
     * @param response
     * @param code
     * @param userTel
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="code")
    public @ResponseBody
    ResponseDto sendSmsCode(HttpServletRequest request, HttpServletResponse response,Integer code,String userTel,String sign,String random,String authCode) throws Exception{
        DataTransferObject dto = new DataTransferObject<>();
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        if(Check.NuNStr(header.getApplicationCode())
                || Check.NuNStr(header.getDeviceUuid())){
            return new ResponseDto("异常的头信息");
        }

        if (Check.NuNObj(code)) {
            return new ResponseDto("参数异常");
        }

        SmsTypeEnum smsTypeEnum = SmsTypeEnum.getTypeByCode(code);
        if (Check.NuNObj(smsTypeEnum)){
            return new ResponseDto("异常的code");
        }

        if (smsTypeEnum.getCode() == SmsTypeEnum.USER_REGIST.getCode()){
            //注册,先校验手机号
            DataTransferObject dtoCheck =userService.checkRegist(userTel);
            if (!dtoCheck.checkSuccess()){
                return dtoCheck.trans2Res();
            }
        }else if (smsTypeEnum.getCode() == SmsTypeEnum.OPEN_REGIST.getCode()){
            if (Check.NuNObjs( random, authCode)){
                return new ResponseDto("参数异常");
            }
            String imgCode = redisOperation.get(SMS_IMG + random);
            if(Check.NuNObj(imgCode) || !authCode.equals(imgCode)){
                return new ResponseDto("图形验证码不正确");
            }
        }
        Map<String,Object> parSign = new HashMap<>();
        parSign.put("code",code);
        parSign.put("userTel",userTel);
        parSign.put("random",random);
        parSign.put("sign",sign);
//        boolean  signFlag = SignUtils.checkMapSign("Oj0mUTVY",parSign);
//        if (!signFlag){
//
//            return new ResponseDto("验签失败");
//        }
        String  key = HeaderUtil.getCodeStr(header,code);
        if (Check.NuNStr(key)){
            return new ResponseDto("参数异常");
        }
        if (!dealCodeLimitTime(userTel,false)){
            return new ResponseDto("超出条数限制");
        }
        String msgCode = getRandom(4);
        redisOperation.setex(key, smsTypeEnum.getTime(), msgCode);
        dto.setData(msgCode);
        //发送信息
        DataTransferObject dataTransferObject = this.dealSendCode(userTel,msgCode);
        if (dataTransferObject.checkSuccess()){
            //增加调用次数
            this.dealCodeLimitTime(userTel,true);
            return dto.trans2Res();
        }else {
            return dataTransferObject.trans2Res();
        }
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


    /**
     * 校验手机号是否正常发送
     * @author afi
     * @param userTel
     * @return
     */
    private boolean dealCodeLimitTime(String userTel,boolean add){
        boolean falg = true;
        String key = DateUtil.dateFormat(new Date()) + userTel;
        int step = 0;
        if (add){
            step = 1;
        }
        Long has =redisOperation.incrByStep(key,step);
        if(has  > 10){
            falg = false;
        }
        return falg;
    }

    private  static  String getRandom(int length){
        String val = "";
        Random random = new Random();
        for(int i = 0; i < length; ++i) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }

}
