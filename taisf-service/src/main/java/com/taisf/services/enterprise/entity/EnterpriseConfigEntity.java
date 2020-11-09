package com.taisf.services.enterprise.entity;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;


/**
 * <p>
 * 企业的配置信息
 * </p>
 * <p/>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2017/9/20.
 * @version 1.0
 * @since 1.0
 */
public class EnterpriseConfigEntity extends BaseEntity {

    /** 序列化id */
    private static final long serialVersionUID = 7380668097871543955L;
    private Integer id;

    private String enterpriseCode;

    /**
     * 员工餐价格
     */
    private Integer empPrice;

    /**
     * 老板餐价格
     */
    private Integer bossPrice;

    /**
     * 是否午餐
     */
    private Integer forLunch;

    /**
     * 午餐开始时间
     */
    private String lunchStart;

    /**
     * 午餐结束时间
     */
    private String lunchEnd;

    /**
     * 是否晚餐
     */
    private Integer forDinner;

    /**
     * 晚餐开始时间
     */
    private String dinnerStart;

    /**
     * 晚餐开始时间
     */
    private String dinnerEnd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode == null ? null : enterpriseCode.trim();
    }


    public Integer getEmpPrice() {
        return empPrice;
    }

    public void setEmpPrice(Integer empPrice) {
        this.empPrice = empPrice;
    }

    public Integer getBossPrice() {
        return bossPrice;
    }

    public void setBossPrice(Integer bossPrice) {
        this.bossPrice = bossPrice;
    }

    public Integer getForLunch() {
        return forLunch;
    }

    public void setForLunch(Integer forLunch) {
        this.forLunch = forLunch;
    }


    public Integer getForDinner() {
        return forDinner;
    }

    public void setForDinner(Integer forDinner) {
        this.forDinner = forDinner;
    }

    public String getLunchStart() {
        return lunchStart;
    }

    public void setLunchStart(String lunchStart) {
        this.lunchStart = lunchStart;
    }

    public String getLunchEnd() {
        return lunchEnd;
    }

    public void setLunchEnd(String lunchEnd) {
        this.lunchEnd = lunchEnd;
    }

    public String getDinnerStart() {
        return dinnerStart;
    }

    public void setDinnerStart(String dinnerStart) {
        this.dinnerStart = dinnerStart;
    }

    public String getDinnerEnd() {
        return dinnerEnd;
    }

    public void setDinnerEnd(String dinnerEnd) {
        this.dinnerEnd = dinnerEnd;
    }
}