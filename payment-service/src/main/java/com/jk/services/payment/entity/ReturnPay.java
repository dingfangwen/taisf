package com.jk.services.payment.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class ReturnPay implements  Serializable{
	
	private static final long serialVersionUID = -2737962917127134957L;
	private Integer id;
	private String bizId;//业务ID
	private String content;//业务内容
	private String productCode;//产品编码
	private BigDecimal amount;//金额
	private String code;//调用的具体code
	private String returnUrl;//通知的URl
	private String notifyUrl;//异步通知的url
	private String serialNo;
	private String status;
	private String statusDesc;
	private String codeKey;
	private Date bizTime;
	private String paySign;
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodeKey() {
		return codeKey;
	}
	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}
	public Date getBizTime() {
		return bizTime;
	}
	public void setBizTime(Date bizTime) {
		this.bizTime = bizTime;
	}
	public String getPaySign() {
		return paySign;
	}
	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
}
