package com.taisf.services.device.common;

import com.jk.framework.base.exception.BusinessException;
import com.jk.framework.base.utils.Check;
import com.taisf.services.device.api.LinkServoice;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * <p>基础容器</p>
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
public class BaseContext implements ApplicationContextAware {

	/**
	 * spring 容器
	 */
	protected ApplicationContext applicationContext;


	@Override
	public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 获取当前的配置信息
	 * @param configCode
	 * @return
	 */
	public LinkServoice getLinkService(String configCode){

//		ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext-device.xml");

		LinkServoice linkServoice =  (LinkServoice) applicationContext.getBean(configCode);
		if (Check.NuNObj(linkServoice)){
			throw new BusinessException("异常的配置信息");
		}
		return linkServoice;
	}



}
