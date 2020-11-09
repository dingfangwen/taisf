package com.taisf.services.supplier.entity;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

/**
 * <p>
 * 供应商的组合商品信息
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
public class SupplierPackageEntity extends BaseEntity {

    /** 序列化id */
    private static final long serialVersionUID = 7380668097871543955L;
    private Integer id;

    /**
     * 周几
     */
    private Integer week;

    private String supplierCode;

    private String title;

    private String packagePic;

    /**
     * 套餐价格
     */
    private Integer packagePrice;

    private Integer packageStatus;

    private Integer bigCode;

    private Integer smallCode;

    private Integer suCode;

    private Integer tangCode;

    private Integer drinkCode;

    private Integer foodCode;

    private Integer fruitCode;

    private Date createTime;

    /**
     * 是否午餐
     */
    private Integer forLunch;

    /**
     * 是否晚餐
     */
    private Integer forDinner;

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


    public Integer getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(Integer packagePrice) {
        this.packagePrice = packagePrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode == null ? null : supplierCode.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPackagePic() {
        return packagePic;
    }

    public void setPackagePic(String packagePic) {
        this.packagePic = packagePic == null ? null : packagePic.trim();
    }

    public Integer getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(Integer packageStatus) {
        this.packageStatus = packageStatus;
    }

    public Integer getBigCode() {
        return bigCode;
    }

    public void setBigCode(Integer bigCode) {
        this.bigCode = bigCode;
    }

    public Integer getSmallCode() {
        return smallCode;
    }

    public void setSmallCode(Integer smallCode) {
        this.smallCode = smallCode;
    }

    public Integer getSuCode() {
        return suCode;
    }

    public void setSuCode(Integer suCode) {
        this.suCode = suCode;
    }

    public Integer getTangCode() {
        return tangCode;
    }

    public void setTangCode(Integer tangCode) {
        this.tangCode = tangCode;
    }

    public Integer getDrinkCode() {
        return drinkCode;
    }

    public void setDrinkCode(Integer drinkCode) {
        this.drinkCode = drinkCode;
    }

    public Integer getFoodCode() {
        return foodCode;
    }

    public void setFoodCode(Integer foodCode) {
        this.foodCode = foodCode;
    }

    public Integer getFruitCode() {
        return fruitCode;
    }

    public void setFruitCode(Integer fruitCode) {
        this.fruitCode = fruitCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }
}