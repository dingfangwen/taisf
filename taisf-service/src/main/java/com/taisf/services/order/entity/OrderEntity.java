package com.taisf.services.order.entity;


import com.jk.framework.base.entity.BaseEntity;
import java.util.Date;

/**
 * <p>订单实体类</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2017/9/14.
 * @version 1.0
 * @since 1.0
 */
public class OrderEntity extends BaseEntity {

    /** 序列化id  */
    private static final long serialVersionUID = -1312312937998L;

    /** id */
    private Integer id;

    /** 订单编号 */
    private String orderSn;

    /** 标题 */
    private String title;

    /**  省code */
    private String provinceCode;
    /** 城市code */
    private String cityCode;

    /** 区域code */
    private String areaCode;


    /**
     * 收货地址
     */
    private String addressFid;

    /**  收货地址 */
    private String  address;




    /** 是否自提 */
    private Integer isSelf;


    /** 订单来源 */
    private Integer orderSource;

    /**
     *  订单状态
     */
    private Integer orderStatus;

    /**
     * 评价状态
     */
    private Integer evaStatus;

    /** 付款单状态 */
    private Integer accountsStatus;

    /** 支付状态 */
    private Integer payStatus;

    /** 订单类型 */
    private Integer orderType;


    /** 面对面收款类型 */
    private Integer faceType;


    /** 商家Uid */
    private String businessUid;
    
    /** 用户Uid */
    private String userUid;

    /** 用户电话 */
    private String userTel;

    /** 用户名称 */
    private String userName;

    /**
     * 用户编号
     */
    private String userCode;
    /**
     * 配送人
     */
    private String knightUid;
    
    /** 订单支付时间 */
    private Date payTime;

    /** 送餐付时间 */
    private Date sendTime;

    /** 创建时间 */
    private Date createTime;

    /** 最后修改时间 */
    private Date lastModifyDate;

    /** 备注 */
    private String mark;

    private String enterpriseCode;

    /**
     * 供应商code
     */
    private String supplierCode;

    /**
     * 订单的商品信息
     */
    private String orderJson;


    public String getOrderJson() {
        return orderJson;
    }

    public void setOrderJson(String orderJson) {
        this.orderJson = orderJson;
    }

    public String getAddressFid() {
        return addressFid;
    }

    public void setAddressFid(String addressFid) {
        this.addressFid = addressFid;
    }

    public Integer getIsSelf() {
        return isSelf;
    }

    public void setIsSelf(Integer isSelf) {
        this.isSelf = isSelf;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getEvaStatus() {
        return evaStatus;
    }

    public void setEvaStatus(Integer evaStatus) {
        this.evaStatus = evaStatus;
    }

    public Integer getAccountsStatus() {
        return accountsStatus;
    }

    public void setAccountsStatus(Integer accountsStatus) {
        this.accountsStatus = accountsStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
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

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getKnightUid() {
        return knightUid;
    }

    public void setKnightUid(String knightUid) {
        this.knightUid = knightUid;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getFaceType() {
        return faceType;
    }

    public void setFaceType(Integer faceType) {
        this.faceType = faceType;
    }
}
