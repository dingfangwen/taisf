package com.jk.services.payment.entity;

import com.jk.framework.base.entity.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

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
public class PayEntity extends BaseEntity{

	private static final long serialVersionUID = -1320292760426408041L;

    private Integer id;

	/**
	 * 获取账户id
	 */
	private Integer accountId;
	/**
	 * 产品编码(主页、手机WAP页)
	 */
    private String productCode;
	/**
	 * 业务类型(支付  退款  等)
	 */
    private String type;
	/**
	 * 编码(alipayWapHandle)
	 */
    private String code;
	/**
	 * 业务id
	 */
    private String bizId;
	/**
	 * 业务内容
	 */
    private String content;
	/**
	 * 业务发生时间
	 */
    private Date bizTime;
	/**
	 * 同步通知的url
	 */
    private String returnUrl;
	/**
	 * 异步通知的url
	 */
    private String notifyUrl;
	/**
	 * 状态
	 */
    private String status;
	/**
	 * 状态描述
	 */
    private String statusDesc;
	/**
	 * 金额
	 */
    private Integer amount;
	/**
	 * 信息
	 */
    private String remark;
	/**
	 * 请求的id
	 */
    private String reqId;
	/**
	 * 响应的id
	 */
    private String resId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Date getBizTime() {
		return bizTime;
	}

	public void setBizTime(Date bizTime) {
		this.bizTime = bizTime;
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
}