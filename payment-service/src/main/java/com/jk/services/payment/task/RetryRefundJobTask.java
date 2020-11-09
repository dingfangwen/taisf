package com.jk.services.payment.task;

import com.jk.services.payment.handle.tenpay.TenpayRefundHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>退款请求</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author lunan  on 2017/6/15.
 * @version 1.0
 * @since 1.0
 */
@Service
public class RetryRefundJobTask extends TaskJob{
	private static final Logger LOGGER = LoggerFactory.getLogger(RetryRefundJobTask.class);

	@Autowired
	TenpayRefundHandle tenpayRefundHandle;


	//@Autowired
	//AliRefundHandle aliRefundHandle;


	@Override
	public void doTask() {
		//微信的退款
		tenpayRefundHandle.doTask();

		//支付宝的退款
		//aliRefundHandle.doTask();
	}
}
