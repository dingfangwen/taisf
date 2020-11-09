package com.taisf.api.common.abs;


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpSession;

/**
 * <p>抽象拦截器</p>
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
public abstract class AbstractInterceptor extends HandlerInterceptorAdapter {


    /**
     * 获取当前登录的用户
     *
     * @param session
     *
     * @return
     */
    public static String getCurrentUserName(HttpSession session) {
//        Object sessionUser = session.getAttribute(Constant.CURRENT_LOGIN_USER_NAME);
//        if (sessionUser == null) {
//            return null;
//        }
//        String useraccountVO = (String) sessionUser;
//        return useraccountVO;
        return "";
    }


}
