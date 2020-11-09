package com.jk.api.payment.vo;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/5/8.
 * @version 1.0
 * @since 1.0
 */
public class CreateVO extends BaseEntity{

    private static final long serialVersionUID = 30122246703L;

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
     * 金额
     */
    private Integer amount;


    /**
     * 微信公众号ID
     */
    private String openid;


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


}
