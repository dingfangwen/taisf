package com.taisf.services.stock.request;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>商品</p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi
 * @version 1.0
 * @since 1.0
 */
public class ProductStockReq extends BaseEntity {
    private Integer id;

    private Integer week;
    /**
     * 午餐库存ID
     */
    private Integer lunchStockId;
    /**
     * 午餐限制库存
     */
    private Integer lunchProductLimit;


    /**
     * 晚餐库存ID
     */
    private Integer dinnerStockId;
    /**
     * 晚餐限制库存
     */
    private Integer dinnerProductLimit;

    private Integer supplierProductType;

    public Integer getSupplierProductType() {
        return supplierProductType;
    }

    public void setSupplierProductType(Integer supplierProductType) {
        this.supplierProductType = supplierProductType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getLunchStockId() {
        return lunchStockId;
    }

    public void setLunchStockId(Integer lunchStockId) {
        this.lunchStockId = lunchStockId;
    }

    public Integer getLunchProductLimit() {
        return lunchProductLimit;
    }

    public void setLunchProductLimit(Integer lunchProductLimit) {
        this.lunchProductLimit = lunchProductLimit;
    }

    public Integer getDinnerStockId() {
        return dinnerStockId;
    }

    public void setDinnerStockId(Integer dinnerStockId) {
        this.dinnerStockId = dinnerStockId;
    }

    public Integer getDinnerProductLimit() {
        return dinnerProductLimit;
    }

    public void setDinnerProductLimit(Integer dinnerProductLimit) {
        this.dinnerProductLimit = dinnerProductLimit;
    }
}