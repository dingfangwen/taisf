package com.taisf.services.order.vo;

import com.jk.framework.base.entity.BaseEntity;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.ValueUtil;

/**
 * <p>订单</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/11/9.
 * @version 1.0
 * @since 1.0
 */
public class OrderProductVO  extends BaseEntity implements Comparable{


    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品配型
     */
    private Integer productPrice;

    /**
     * 商品code
     */
    private Integer productCode;

    /**
     * 商品数量
     */
    private Integer productNum;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
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

    @Override
    public int compareTo(Object o) {
        if (Check.NuNObj(o)){
            return 1;
        }
        OrderProductVO com = (OrderProductVO) o;
        return ValueUtil.getintValue(com.getProductPrice()) - ValueUtil.getintValue(productPrice);
    }
}
