package com.taisf.api.pay.dto;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>订单的支付参数</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/5/16.
 * @version 1.0
 * @since 1.0
 */
public class BasePayRequest extends BaseEntity {

    private static final long serialVersionUID = 243223422342303L;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 支付方式
     */
    private Integer payType;

    /**
     * 支付提示内容
     */
    private String content;

    /**
     * 支付号
     */
    private String productCode;

    /**
     * 公共号id
     */
    private String openId;

    /**
     * 用户id
     */
    private String uid;

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
