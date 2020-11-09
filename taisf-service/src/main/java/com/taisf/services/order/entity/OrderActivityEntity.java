package com.taisf.services.order.entity;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

/**
 * <p>订单参加的活动</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/14.
 * @version 1.0
 * @since 1.0
 */
public class OrderActivityEntity extends BaseEntity {

    /** 序列化id */
    private static final long serialVersionUID = 7380668097871543955L;

    /** id*/
    private Integer id;

    /** 订单编号  */
    private String orderSn;

    /** 活动id*/
    private String acCode;

    /** 活动名称 */
    private String acName;

    /** 活动 金额*/
    private Integer acMoney;

    /**
     * 活动类型
     */
    private Integer acType;

    /**  使用时间 */
    private Date usedTime;

    /**
     * 优惠券状态
     */
    private Integer acStatus;



    public Integer getAcType() {
        return acType;
    }

    public void setAcType(Integer acType) {
        this.acType = acType;
    }

    public Integer getAcStatus() {
        return acStatus;
    }

    public void setAcStatus(Integer acStatus) {
        this.acStatus = acStatus;
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
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public String getAcCode() {
        return acCode;
    }

    public void setAcCode(String acCode) {
        this.acCode = acCode;
    }

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName == null ? null : acName.trim();
    }

    public Integer getAcMoney() {
        return acMoney;
    }

    public void setAcMoney(Integer acMoney) {
        this.acMoney = acMoney;
    }

    public Date getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }

}
