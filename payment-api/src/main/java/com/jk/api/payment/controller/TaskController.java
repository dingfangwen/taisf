package com.jk.api.payment.controller;


import com.jk.api.payment.base.BaseController;
import com.jk.services.payment.entity.PayEntity;
import com.jk.services.payment.service.PaymentService;
import com.jk.services.payment.task.TaskJob;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 定时任务
 * @author PP
 *
 */
@Controller
public class TaskController extends BaseController {
	private static final Logger LOGGER  = LoggerFactory.getLogger(TaskController.class);


	@Autowired
	protected PaymentService paymentService;



	@RequestMapping("/task")
	@ResponseBody
	public String task(String job, HttpServletRequest request, HttpServletResponse response){
		if(StringUtils.isNotBlank(job)){
			TaskJob taskJob  = this.getTaskJob(job);
			taskJob.doTask();
		}
		return "success";
	}
	@RequestMapping("/status")
	@ResponseBody
	public String status(String id,String status){
		if(StringUtils.isNotBlank(id) && StringUtils.isNotBlank(status)){
			PayEntity pay = new PayEntity();
			pay.setId(Integer.parseInt(id));
			pay.setStatus(status);
			paymentService.update(pay);
			LOGGER.info("update id= {},status={}",id,status);
		}
		return "success";
	}
}
