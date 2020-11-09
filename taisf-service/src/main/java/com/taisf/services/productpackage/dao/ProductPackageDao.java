package com.taisf.services.productpackage.dao;

import com.jk.framework.base.utils.Check;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.productpackage.entity.ProductPackageEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:zhangzhengguang
 * @date:2018/9/19
 * @description:
 **/
@Repository("productPackageDao")
public class ProductPackageDao extends BaseDao {

    private String SQLID = "productPackageDao.";

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(ProductPackageDao.class);

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:新增
     **/
    public int saveProductPackage(ProductPackageEntity entity) {
        if (Check.NuNObj(entity.getCreateTime())) {
            entity.setCreateTime(new Date());
        }
        return mybatisDaoContext.save(SQLID + "insertSelective", entity);
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:根据id查询
     **/
    public ProductPackageEntity getProductPackageById(Integer id) {
        return mybatisDaoContext.findOneSlave(SQLID + "selectByPrimaryKey", ProductPackageEntity.class, id);
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:修改
     **/
    public int updateProductPackage(ProductPackageEntity entity) {
        return mybatisDaoContext.update(SQLID + "updateByPrimaryKeySelective", entity);
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:删除
     **/
    public int deleteProductPackage(Integer productId,Integer packageId) {
        Map<String, Object> param = new HashMap<>();
        param.put("productId", productId);
        param.put("packageId", packageId);
        return mybatisDaoContext.delete(SQLID + "deleteProductPackage", param);
    }

}
