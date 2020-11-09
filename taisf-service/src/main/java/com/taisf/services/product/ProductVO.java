package com.taisf.services.product;

import com.taisf.services.product.entity.ProductEntity;

/**
 * @author zhangzhengguang
 * @create 2017-10-12
 **/
public class ProductVO extends ProductEntity {

    private  String classifyName;

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }
}
