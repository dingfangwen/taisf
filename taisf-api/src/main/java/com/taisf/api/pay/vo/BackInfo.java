package com.taisf.api.pay.vo;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>支付的回调</p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi  on 2017/5/29 0029.
 * @version 1.0
 * @since 1.0
 */
public class BackInfo extends BaseEntity {

    private static final long serialVersionUID = 21790266608492L;

    private  Integer id;
    /** 订单号 */
    private  String bizId;
    /** 描述信息 业务内容 */
    private  String content;
    /** 业务编码 */
    private  String code;
    /** 业务编码 */
    private  String codeKey;
    /** 回调地址 */
    private String notifyUrl;
    /** 回调地址 */
    private String returnUrl;


    /** 产品编码 */
    private  String productCode;
    /** 金额 分 */
    private int amount;
    /** 序列号、流水号 */
    private  String serialNo;
    /** 支付时间 */
    private Long bizTime;
    /** 订单号 */
    private  String paySign;
    /** 状态 */
    private  String status;
    /** 状态描述 */
    private  String statusDesc;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    



	public Long getBizTime() {
		return bizTime;
	}

	public void setBizTime(Long bizTime) {
		this.bizTime = bizTime;
	}

	public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
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

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
