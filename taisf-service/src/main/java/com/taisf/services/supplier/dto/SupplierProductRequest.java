package com.taisf.services.supplier.dto;

import com.jk.framework.base.page.PageRequest;

import java.util.List;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/25.
 * @version 1.0
 * @since 1.0
 */
public class SupplierProductRequest extends PageRequest {


    /**
     * 周
     */
    private Integer week;
    /**
     * 套餐名称
     */
    private String title;

    /**
     * 午餐晚餐
     */
    private Integer lunchDinner;

    /**
     * 供应商的code
     */
    private String supplierCode;

    /**
     * 商品分类
     */
    private Integer productClassify;


    /**
     * 订单类型
     */
    private Integer orderType;

    /**
     * 商品id
     */
    private List<Integer> productIds;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLunchDinner() {
        return lunchDinner;
    }

    public void setLunchDinner(Integer lunchDinner) {
        this.lunchDinner = lunchDinner;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Integer getProductClassify() {
        return productClassify;
    }

    public void setProductClassify(Integer productClassify) {
        this.productClassify = productClassify;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }
}
