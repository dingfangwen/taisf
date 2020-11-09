package com.taisf.services.productpackage.api;

import com.jk.framework.base.entity.DataTransferObject;
import com.taisf.services.productpackage.entity.ProductPackageEntity;

public interface ProductPackageService {
    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:新增
     **/
    DataTransferObject<Void> saveProductPackage(ProductPackageEntity entity);

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:根据id查询
     **/
    DataTransferObject<ProductPackageEntity> getProductPackageById(Integer id);

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:修改
     **/
    DataTransferObject<Void> updateProductPackage(ProductPackageEntity entity);

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:删除
     **/
    DataTransferObject<Void> deleteProductPackage(Integer productId,Integer packageId);

   
}
