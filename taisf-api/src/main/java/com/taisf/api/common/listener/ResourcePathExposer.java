package com.taisf.api.common.listener;


import com.jk.framework.log.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * <p>设置静态资源的版本号，这里是硬编码的实现，如果可以的话，可以放在配置文件中。
 *    当前每次服务器启动的时候会被加载一次
 * </p>
 * @author yd
 *
 */
public class ResourcePathExposer implements ServletContextAware{

	/**
	 * ServletContext的上下文
	 */
	private ServletContext servletContext; 
	
	public ResourcePathExposer(){
		initResourceVersion();
	}
	/**
	 * 日志对象
	 */
    private final static Logger logger = LoggerFactory.getLogger(ResourcePathExposer.class);

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext=servletContext;
	}

	public ServletContext getServletContext() {  
		return servletContext;  
	} 

	/**
	 * 初始化版本号
	 * @author yd
	 */
	public void initResourceVersion(){  
		
		String version = "?v=";  
		Long timestap = new Date().getTime();//当前时间戳
		Random ran=new Random();
		int random=0;
		//获取四位不重复的随机数字
		m:while(true){
			int n=ran.nextInt(10000);
			random=n;
			int[] bs=new int[4];
			for(int i=0;i<bs.length;i++){
				bs[i]=n%10;
				n/=10;
			}
			Arrays.sort(bs);
			for(int i=1;i<bs.length;i++){
				if(bs[i-1]==bs[i]){
					continue m;
				}
			}
			break;
		}
		version = version + timestap+random;
        LogUtil.info(logger,"当前静态资源文件的版本号VERSION:{}",version);

		getServletContext().setAttribute("VERSION", version);  
	}  

}
