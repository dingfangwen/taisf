package com.taisf.services.order.dto;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>购物车的基本信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/28.
 * @version 1.0
 * @since 1.0
 */
public class CartBaseRequest extends BaseEntity{

    private static final long serialVersionUID = 301231201446703L;



    /**
     * 商家uid
     */
    private String businessUid;

    /**
     * 用户uid
     */
    private String userUid;

    /**
     * 商品code
     */
    private Integer productCode;

    /**
     * 商品类型
     */
    private Integer supplierProductType;

    /**
     * 企业编码
     */
    private String enterpriseCode;

    /**
     * 企业编码
     */
    private Integer orderType;

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public Integer getSupplierProductType() {
        return supplierProductType;
    }

    public void setSupplierProductType(Integer supplierProductType) {
        this.supplierProductType = supplierProductType;
    }

    public String getBusinessUid() {
        return businessUid;
    }

    public void setBusinessUid(String businessUid) {
        this.businessUid = businessUid;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }
}
