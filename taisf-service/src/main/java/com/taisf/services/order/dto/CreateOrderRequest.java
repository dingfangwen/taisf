package com.taisf.services.order.dto;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>创建订单的参数</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/28.
 * @version 1.0
 * @since 1.0
 */
public class CreateOrderRequest extends BaseEntity{

    private static final long serialVersionUID = 301231201446703L;

    /**
     * 商家uid
     */
    private String businessUid;

    /**
     * 用户uid
     */
    private String userUid;

    /**
     * 收货地址
     */
    private String addressFid;


    /**
     * 支付密码
     */
    private String pwd;

    /**
     * 订单类型
     */
    private Integer orderType;

    /**
     * 来源
     */
    private Integer source;

    /**
     * 企业编码
     */
    private String enterpriseCode;

    /**
     * 金额
     */
    private Integer price;


    /**
     * 收款码
     */
    private String payCode;


    /**
     * 混合支付
     */
    private Integer mix;

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getBusinessUid() {
        return businessUid;
    }

    public void setBusinessUid(String businessUid) {
        this.businessUid = businessUid;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getAddressFid() {
        return addressFid;
    }

    public void setAddressFid(String addressFid) {
        this.addressFid = addressFid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }


    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getMix() {
        return mix;
    }

    public void setMix(Integer mix) {
        this.mix = mix;
    }
}
