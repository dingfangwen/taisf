package com.jk.api.payment.base;

import com.jk.api.dependency.common.abs.AbstractController;
import com.jk.services.payment.task.TaskJob;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


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
public class BaseContext extends AbstractController implements ApplicationContextAware {

	/**
	 * spring 容器
	 */
	protected ApplicationContext applicationContext;


	@Override
	public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
		this.applicationContext = applicationContext;
	}


	public TaskJob getTaskJob(String job){
		return (TaskJob) applicationContext.getBean(job);
	}


}
