package com.taisf.services.productpackage.manager;

import com.taisf.services.productpackage.dao.ProductPackageDao;
import com.taisf.services.productpackage.entity.ProductPackageEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author:zhangzhengguang
 * @date:2018/9/19
 * @description:
 **/
@Service("productPackageManagerImpl")
public class ProductPackageManagerImpl {

    @Resource(name = "productPackageDao")
    private ProductPackageDao productPackageDao;


    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:新增
     **/
    public int saveProductPackage(ProductPackageEntity entity) {
        return productPackageDao.saveProductPackage(entity);
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:根据id查询
     **/
    public ProductPackageEntity getProductPackageById(Integer id) {
        return productPackageDao.getProductPackageById(id);
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:修改
     **/
    public int updateProductPackage(ProductPackageEntity entity) {
        return productPackageDao.updateProductPackage(entity);
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:删除
     **/
    public int deleteProductPackage(Integer productId,Integer packageId) {
        return productPackageDao.deleteProductPackage(productId,packageId);
    }

}
