package com.taisf.services.product.entity;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

/**
 * <p>商品</p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi
 * @since 1.0
 * @version 1.0
 */
public class ProductEntity extends BaseEntity {
    /**
     * 逻辑id
     */
    private Integer id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * @see com.taisf.services.common.valenum.SupplierProductTypeEnum
     */
    private Integer supplierProductType;

    /**
     * 商品分类
     */
    private String productClassify;

    /**
     * 商品配型
     */
    private Integer productType;



    /**
     * 商品属性
     */
    private Integer productSource;

    /**
     * 描述信息
     */
    private String productDes;

    /**
     * 默认图片
     */
    private String productPic;


    /**
     * 销售价
     */
    private Integer priceSale;

    /**
     * 销售价
     */
    private Integer priceMarket;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除 0：否 1：是
     */
    private Integer isDel;

    private Integer forLunch;

    private Integer forDinner;

    private String supplierCode;
    private String windowCode;

    private String totalProductName;
    private Integer calorie;

    public Integer getCalorie() {
        return calorie;
    }

    public void setCalorie(Integer calorie) {
        this.calorie = calorie;
    }

    public String getTotalProductName() {
        return totalProductName;
    }

    public void setTotalProductName(String totalProductName) {
        this.totalProductName = totalProductName;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getWindowCode() {
        return windowCode;
    }

    public void setWindowCode(String windowCode) {
        this.windowCode = windowCode;
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

    public Integer getPriceMarket() {
        return priceMarket;
    }

    public void setPriceMarket(Integer priceMarket) {
        this.priceMarket = priceMarket;
    }

    private static final long serialVersionUID = 1L;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductClassify() {
        return productClassify;
    }

    public void setProductClassify(String productClassify) {
        this.productClassify = productClassify;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getProductSource() {
        return productSource;
    }

    public void setProductSource(Integer productSource) {
        this.productSource = productSource;
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public Integer getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(Integer priceSale) {
        this.priceSale = priceSale;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }


    public Integer getSupplierProductType() {
        return supplierProductType;
    }

    public void setSupplierProductType(Integer supplierProductType) {
        this.supplierProductType = supplierProductType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productName=").append(productName);
        sb.append(", productClassify=").append(productClassify);
        sb.append(", productType=").append(productType);
        sb.append(", productSource=").append(productSource);
        sb.append(", productDes=").append(productDes);
        sb.append(", productPic=").append(productPic);
        sb.append(", priceSale=").append(priceSale);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDel=").append(isDel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}