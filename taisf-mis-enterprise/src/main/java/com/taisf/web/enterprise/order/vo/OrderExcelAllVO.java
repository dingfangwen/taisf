package com.taisf.web.enterprise.order.vo;

import com.jk.framework.base.entity.BaseEntity;
import com.jk.framework.excel.annotation.FieldMeta;
import com.jk.framework.excel.annotation.MoneyPenny2Yuan;
import com.jk.framework.excel.annotation.TimeFormatPattern;

import java.util.Date;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/6/17.
 * @version 1.0
 * @since 1.0
 */
public class OrderExcelAllVO extends BaseEntity {


    /**
     * 订单编号
     */
    @FieldMeta(name="订单编号",order=1)
    private String orderSn;


    /**
     * 下单时间
     */
    @TimeFormatPattern
    @FieldMeta(name="下单时间",order=1)
    private Date createTime;


    /**
     * 用户姓名
     */
    @FieldMeta(name="用户姓名",order=1)
    private String userName;


    /**
     * 工号
     */
    @FieldMeta(name="工号",order=1)
    private String userCode;


    /**
     * 用户电话
     */
    @FieldMeta(name="用户电话",order=1)
    private String userTel;


    /**
     * 企业编号
     */
    @FieldMeta(name="企业编号",order=1)
    private String enterpriseCode;


    /**
     * 企业名称
     */
    @FieldMeta(name="企业名称",order=1)
    private String enterpriseName;


    /**
     * 订单金额
     */
    @MoneyPenny2Yuan
    @FieldMeta(name="订单金额",order=1)
    private Integer sumMoney;



    /**
     * 订单类型
     */
    @FieldMeta(name="订单类型",order=1)
    private String orderTypeStr;


    /**
     * 配送方式
     */
    @FieldMeta(name="配送方式",order=1)
    private Integer isSelf;



    /**
     * 状态
     */
    @FieldMeta(name="状态",order=1)
    private String orderStatusStr;


    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
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


    public Integer getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Integer sumMoney) {
        this.sumMoney = sumMoney;
    }

    public String getOrderTypeStr() {
        return orderTypeStr;
    }

    public void setOrderTypeStr(String orderTypeStr) {
        this.orderTypeStr = orderTypeStr;
    }

    public Integer getIsSelf() {
        return isSelf;
    }

    public void setIsSelf(Integer isSelf) {
        this.isSelf = isSelf;
    }

    public String getOrderStatusStr() {
        return orderStatusStr;
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }
}
