package com.jk.services.payment.constant;

import com.jk.services.log.PaymentLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("paymentConstant")
public class PaymentConstant {

	protected static final Logger LOGGER_PAY_LOG = LoggerFactory.getLogger(PaymentLog.class);

	@Value("${returnUrl}")
	protected String returnUrl;
	@Value("${notifyUrl}")
	protected String notifyUrl;
	@Value("${wapUrl}")
	protected String wapUrl;

//	@Value("${payPreKey}")

	protected String payPreKey="";

	@Value("${notifyAlipayUrl}")
	protected String notifyAlipayUrl;


}
