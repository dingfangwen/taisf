package com.taisf.services.order.vo;

import com.jk.framework.base.constant.YesNoEnum;
import com.taisf.services.order.entity.CartEntity;

/**
 * <p>购物车展示</p>
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
public class CartVO extends CartEntity{

    /**
     * 商品名称
     */
    private String productName;


    /**
     * 商品价格
     */
    private Integer productPrice;


    /**
     * 是否失效 1:失效 0:未失效
     */
    private Integer outFlag = YesNoEnum.NO.getCode();


    /**
     * 失效原因
     */
    private String outDes ;


    public Integer getOutFlag() {
        return outFlag;
    }

    public void setOutFlag(Integer outFlag) {
        this.outFlag = outFlag;
    }

    public String getOutDes() {
        return outDes;
    }

    public void setOutDes(String outDes) {
        this.outDes = outDes;
    }

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
}
