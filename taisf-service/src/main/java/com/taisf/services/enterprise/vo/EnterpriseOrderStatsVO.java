package com.taisf.services.enterprise.vo;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>企业订单的额统计新</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/20.
 * @version 1.0
 * @since 1.0
 */
public class EnterpriseOrderStatsVO  extends BaseEntity{

    private static final long serialVersionUID = 301231231201446703L;

    /**
     * 统计区间
     */
    private String time;

    private String enterpriseCode;

    private String enterpriseName;


    /**
     * 企业类型
     */
    private Integer enterpriseType;

    /**
     * 企业类型
     */
    private String enterpriseTypeName;

    /**
     * 订单数量
     */
    private Integer orderNum;


    /**
     * 面对面收款
     */
    private Integer faceNum;

    /**
     * 骑手收款
     */
    private Integer knightNum;



    /**
     * 全部数量
     */
    private Integer allNum;

    /**
     * 支付
     */
    private Integer payMoney;

    /**
     * 余额支付
     */
    private Integer payBalance;


    public Integer getFaceNum() {
        return faceNum;
    }

    public void setFaceNum(Integer faceNum) {
        this.faceNum = faceNum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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


    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getAllNum() {
        return allNum;
    }

    public void setAllNum(Integer allNum) {
        this.allNum = allNum;
    }

    public Integer getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Integer payMoney) {
        this.payMoney = payMoney;
    }

    public Integer getPayBalance() {
        return payBalance;
    }

    public void setPayBalance(Integer payBalance) {
        this.payBalance = payBalance;
    }

    public Integer getKnightNum() {
        return knightNum;
    }

    public void setKnightNum(Integer knightNum) {
        this.knightNum = knightNum;
    }


    public Integer getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(Integer enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public String getEnterpriseTypeName() {
        return enterpriseTypeName;
    }

    public void setEnterpriseTypeName(String enterpriseTypeName) {
        this.enterpriseTypeName = enterpriseTypeName;
    }
}
