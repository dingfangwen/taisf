package com.taisf.services.stock.vo;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>获取当前的统计情况</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/3/23.
 * @version 1.0
 * @since 1.0
 */
public class StockHasVO extends BaseEntity {

    private static final long serialVersionUID = 301231231201446703L;


    /**
     * 商品的code
     */
    private  Integer productCode;


    /**
     * 商品类型
     */
    private  Integer supplierProductType;


    /**
     * 商品的占用数
     */
    private  Integer productNum;

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    public Integer getSupplierProductType() {
        return supplierProductType;
    }

    public void setSupplierProductType(Integer supplierProductType) {
        this.supplierProductType = supplierProductType;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }
}
