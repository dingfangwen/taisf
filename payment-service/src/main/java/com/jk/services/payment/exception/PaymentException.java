package com.jk.services.payment.exception;
/**
 * 
 * @业务类异常
 * @author afi
 */
public class PaymentException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String code;
	private String remark;
	public PaymentException(String code, String message) {
		super(message);
		this.code = code;
	}

	public PaymentException(String code, String message, Throwable t) {
		super(message,t);
		this.code = code;
	}

	public PaymentException(String code, String message, String remark){
		super(message);
		this.code = code;
		this.remark = remark;
	}

	public String getCode() {
		return code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
