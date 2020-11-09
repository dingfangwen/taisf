package com.taisf.api.common.interceptor;


import com.jk.framework.base.head.Header;
import com.jk.framework.base.rst.ResponseDto;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.cache.redis.api.RedisOperations;
import com.jk.framework.cache.redis.constant.RedisConstant;
import com.taisf.api.common.constants.ReturnEnum;
import com.taisf.api.common.util.StreamUtils;
import com.taisf.services.user.vo.UserModelVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @Description: TOKEN拦截器
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

    private final static Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

    
    @Autowired
	private RedisOperations redisOperations;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //         request.setAttribute(HeadersInterceptor.USERID, "afi");
        //         return true;

         final Header header = (Header) request.getAttribute(HeadersInterceptor.HEADER);
         String userToken = (String)request.getAttribute(HeadersInterceptor.TOKEN);


         String loginKey = RedisConstant.LOGIN_KEY + userToken;
         ResponseDto dto = new ResponseDto("登录已失效", ReturnEnum.TOKEN_INVALID.getCode());
         if(!redisOperations.exists(loginKey)){
        	 StreamUtils.outMessage(JsonEntityTransform.Object2Json(dto), response);
        	 return false;
         }
    	 String userStr = redisOperations.get(loginKey);
         UserModelVO userModel = JsonEntityTransform.json2Object(userStr, UserModelVO.class);
         if(userModel == null){
        	 StreamUtils.outMessage(JsonEntityTransform.Object2Json(dto), response);
        	 return false;
         }
        if(Check.NuNStr(header.getDeviceUuid())){
            StreamUtils.outMessage(JsonEntityTransform.Object2Json( new ResponseDto("获取权限失败")), response);
            return false;
        }
        if(!header.getDeviceUuid().equals(userModel.getDeviceUuid())){
            StreamUtils.outMessage(JsonEntityTransform.Object2Json( new  ResponseDto("登录已失效", ReturnEnum.TOKEN_INVALID.getCode())), response);
            return false;
        }
        request.setAttribute(HeadersInterceptor.USERID, userModel.getUserId());
        request.setAttribute(HeadersInterceptor.USER, userModel);
         return true;
    }
}
