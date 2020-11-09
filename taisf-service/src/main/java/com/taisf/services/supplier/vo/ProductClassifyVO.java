package com.taisf.services.supplier.vo;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>分类的信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/26.
 * @version 1.0
 * @since 1.0
 */
public class ProductClassifyVO extends BaseEntity {


    /**
     * 分类code
     */
    private String productClassify;


    /**
     * 分类的显示名称
     */
    private String productClassifyName;


    public String getProductClassify() {
        return productClassify;
    }

    public void setProductClassify(String productClassify) {
        this.productClassify = productClassify;
    }

    public String getProductClassifyName() {
        return productClassifyName;
    }

    public void setProductClassifyName(String productClassifyName) {
        this.productClassifyName = productClassifyName;
    }
}
