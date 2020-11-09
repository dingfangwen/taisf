package com.jk.services.payment.entity;

import com.jk.framework.base.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.List;


/**
 * <p>支付的实体对象</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/5/11.
 * @version 1.0
 * @since 1.0
 */
public class PayInfo extends BaseEntity{

	private static final long serialVersionUID = 301213424446703L;

	/**
	 * 业务ID
	 */
	private String bizId;
	/**
	 * 业务内容
	 */
	private String content;
	/**
	 * 产品编码
	 */
	private String productCode;

	/**
	 * 调用的具体code
	 */
	private String code;
	/**
	 * 同步通知的URl
	 */
	private String returnUrl;
	/**
	 * 异步通知的URl
	 */
	private String notifyUrl;

	/**
	 * 微信公众号ID
	 */
	private String openid;


	/**
	 * 金额
	 */
	private Integer amount;


	//退款的信息
	/**
	 * 原始业务id
	 */
	private String orgBizId;

	/**
	 * 原始业务流水号
	 */
	private String orgInSerialNo;




	/**
	 * 支付类型  付款，收款，退款等
	 */
	private String type;
	private String name;
	/**
	 * code 密钥
	 */
	private String codeKey;

	/**
	 * 获取账户id
	 */
	private Integer accountId;
	/**
	 * 合作者
	 */
	private String partner;
	/**
	 * 合作者账号
	 */
	private String account;
	/**
	 * 合作者密钥
	 */
	private String partnerKey;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 支付id
	 */
	private Integer payId;
	/**
	 * 支付总数
	 */
	private Integer count;
	/**
	 * 支付批次号
	 */
	private String  batchNo;
	/**
	 * 支付签名
	 */
	private String paySign;
	/**
	 * 证书名称
	 */
	private String caFileName;
	/**
	 * 证书密码
	 */
	private String caPassword;


	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeKey() {
		return codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPartnerKey() {
		return partnerKey;
	}

	public void setPartnerKey(String partnerKey) {
		this.partnerKey = partnerKey;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPayId() {
		return payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

	public String getCaFileName() {
		return caFileName;
	}

	public void setCaFileName(String caFileName) {
		this.caFileName = caFileName;
	}

	public String getCaPassword() {
		return caPassword;
	}

	public void setCaPassword(String caPassword) {
		this.caPassword = caPassword;
	}


	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}


	public String getOrgBizId() {
		return orgBizId;
	}

	public void setOrgBizId(String orgBizId) {
		this.orgBizId = orgBizId;
	}

	public String getOrgInSerialNo() {
		return orgInSerialNo;
	}

	public void setOrgInSerialNo(String orgInSerialNo) {
		this.orgInSerialNo = orgInSerialNo;
	}
}
