package com.taisf.services.order.entity;

import com.jk.framework.base.constant.YesNoEnum;
import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

/**
 * <p>购物车</p>
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
public class CartEntity extends BaseEntity {


    private static final long serialVersionUID = 1312313213123211L;

    /**
     * 逻辑id
     */
    private Integer id;

    /**
     * 商家uid
     */
    private String businessUid;

    /**
     * 用户uid
     */
    private String userUid;

    /**
     * 商品配型
     */
    private Integer supplierProductType;

    /**
     * 商品code
     */
    private Integer productCode;

    /**
     * 商品数量
     */
    private Integer productNum;


    /**
     * 创建时间
     */
    private Date createTime;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


    public Integer getSupplierProductType() {
        return supplierProductType;
    }

    public void setSupplierProductType(Integer supplierProductType) {
        this.supplierProductType = supplierProductType;
    }

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
