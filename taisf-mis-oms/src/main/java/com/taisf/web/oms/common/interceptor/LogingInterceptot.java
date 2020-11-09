package com.taisf.web.oms.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jk.framework.base.utils.Check;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.ups.entity.EmployeeEntity;
import com.taisf.web.oms.common.constant.LoginConstant;


/**
 * 登录用户校验拦截器
 * @author lunan
 * @date 2017-04-7
 * @version 1.0
 *
 */
public class LogingInterceptot extends HandlerInterceptorAdapter{
	
	/**
	 * 日志对象
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(LogingInterceptot.class);
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String contextPath = request.getContextPath();
		LogUtil.info(LOGGER,"进入login方法");
		HttpSession session = request.getSession();
		
		EmployeeEntity user = (EmployeeEntity) session.getAttribute(LoginConstant.SESSION_KEY);
		if(!Check.NuNObj(user)){
			return true;
		}else{
			response.sendRedirect(contextPath +"/login");
			return false;
		}
	}
}
