package com.taisf.web.enterprise.common.interceptor;

import com.taisf.web.enterprise.common.constant.MisConstant;
import com.taisf.web.enterprise.common.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 初始化全局参数(具体其他全局参数都可以在这里配置)
 * @author afi
 * @date 2016-03-25
 * @version 1.0
 *
 */
public class InitGlobalParamsInterceptor extends HandlerInterceptorAdapter{

	/**
	 * 日志对象
	 */
	private static final Logger log = LoggerFactory.getLogger(InitGlobalParamsInterceptor.class);
	/**
	 * 静态资源地址
	 */
	private String staticResourceUrl;

	/**
	 * js的版本号
	 */
	private String jsVersion;
	
	/**
	 * 静态图片icon资源地址
	 */
	private String staticIconUrl;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String serverHost = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		String serverContext = request.getContextPath().equalsIgnoreCase("/") ? "" : request.getContextPath();
		 // 增加服务器路径
		request.setAttribute("SERVER_HOST",serverHost);
        // 上下文
		request.setAttribute("SERVER_CONTEXT", serverContext);
		
		String basePath = null;
		if(serverHost!=null&&serverContext!=null){
			basePath = serverHost+serverContext+"/";
		}
        request.setAttribute("basePath", basePath);
		//CDN的静态地址
		request.setAttribute("staticResourceUrl", PropertiesUtil.getString(MisConstant.static_resource_url));
		request.setAttribute("VERSION","?"+ PropertiesUtil.getString(MisConstant.js_version));
		request.setAttribute("staticIconUrl", PropertiesUtil.getString(MisConstant.static_icon_url));

		return super.preHandle(request, response, handler);
	}

	public String getStaticResourceUrl() {
		return staticResourceUrl;
	}

	public void setStaticResourceUrl(String staticResourceUrl) {
		this.staticResourceUrl = staticResourceUrl;
	}

	public String getJsVersion() {
		return jsVersion;
	}

	public void setJsVersion(String jsVersion) {
		this.jsVersion = jsVersion;
	}

	public String getStaticIconUrl() {
		return staticIconUrl;
	}

	public void setStaticIconUrl(String staticIconUrl) {
		this.staticIconUrl = staticIconUrl;
	}
}
