package com.taisf.services.refund.constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("pay.payConstant")
public class PayConstant {

	@Value("#{'${PAY_KEY:}'}")
	public String PAY_KEY;


	@Value("#{'${pay_api_url:}'}")
	public String PAY_API_URL;


	@Value("#{'${product_code_ask:}'}")
	public String PRODUCT_CODE_ASK;


	@Value("#{'${refund_platform_url:}'}")
	public String REFUND_PLATFORM_URL;




}