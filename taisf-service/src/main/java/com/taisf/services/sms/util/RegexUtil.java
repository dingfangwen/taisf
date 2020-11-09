package com.taisf.services.sms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2019/9/17.
 * @version 1.0
 * @since 1.0
 */
public class RegexUtil {



    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((19[0-9])|(18[0-9])|(17[0-9])|(16[0-9])|(15[0-9])|(14[0-9])|(13[0-9]))\\d{8}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";


    /**
     * 校验邮箱
     * @param mail
     */
    public static boolean checkMail(String mail){
        return checkRegex(mail,REGEX_EMAIL);
    }


    /**
     * 校验手机号
     * @param phone
     */
    public static boolean checkPhone(String phone){
        return checkRegex(phone,REGEX_MOBILE);
    }

    /**
     *
     * @param context
     * @param patternStr
     * @return
     */
    public static boolean checkRegex(String context,String patternStr){
        Pattern pattern= Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(context);
        if(matcher.matches()){
            return true;
        }else {
            return false;
        }

    }


    public static void main(String[] args) {
        System.out.println(checkPhone("11921501553"));
    }
}
