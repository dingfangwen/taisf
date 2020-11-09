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
public class SupOrderStatsVO extends BaseEntity{

    private static final long serialVersionUID = 301231231201446703L;

    /**
     * 统计区间
     */
    private String time;

    private String supplierCode;

    private String supplierName;


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

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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
}
