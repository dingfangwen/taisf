package com.taisf.api.pay.constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("payConstant")
public class PayConstant {
	

	@Value("#{'${PAY_KEY}'}")
	public String PAY_KEY;

	@Value("#{'${pay_api_url}'}")
	public String PAY_API_URL;

	@Value("#{'${payment_platform_url}'}")
	public String PAYMENT_PLATFORM_URL;

	@Value("#{'${product_code_order}'}")
	public String PRODUCT_CODE_ORDER;



}