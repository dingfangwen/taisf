package com.taisf.services.order.vo;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

/**
 * <p>订单的统计</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/2/5.
 * @version 1.0
 * @since 1.0
 */
public class OrderSendStatsVo extends BaseEntity {

    private static final long serialVersionUID = 301231231201446703L;


    /**
     * 地址fid
     */
    private  String addressFid;

    /**
     * 地址
     */
    private String address;

    /**
     * 订单类型
     */
    private Integer orderType;
    /**
     * 订单数量
     */
    private Integer orderNum;

    /**
     * 企业编号
     */
    private String enterpriseCode;

    private String enterpriseName;

    private Date createTime;



    /**
     * 联系电话
     */
    private String conTel;

    /**
     * 联系姓名
     */
    private String conName;


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAddressFid() {
        return addressFid;
    }

   
    public void setAddressFid(String addressFid) {
        this.addressFid = addressFid;
    }

   
    public String getAddress() {
        return address;
    }

   
    public void setAddress(String address) {
        this.address = address;
    }

   
    public Integer getOrderType() {
        return orderType;
    }

   
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

   
    public String getEnterpriseCode() {
        return enterpriseCode;
    }

   
    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getConTel() {
        return conTel;
    }

    public void setConTel(String conTel) {
        this.conTel = conTel;
    }

    public String getConName() {
        return conName;
    }

    public void setConName(String conName) {
        this.conName = conName;
    }
}
