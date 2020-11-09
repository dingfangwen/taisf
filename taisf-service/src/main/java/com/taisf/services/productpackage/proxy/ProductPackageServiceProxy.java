package com.taisf.services.productpackage.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.productpackage.api.ProductPackageService;
import com.taisf.services.productpackage.entity.ProductPackageEntity;
import com.taisf.services.productpackage.manager.ProductPackageManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author:zhangzhengguang
 * @date:2018/9/19
 * @description:
 **/
@Component("productPackageServiceProxy")
public class ProductPackageServiceProxy implements ProductPackageService {


    private static final Logger LOGGER = LoggerFactory.getLogger(ProductPackageServiceProxy.class);

    @Resource(name = "productPackageManagerImpl")
    private ProductPackageManagerImpl productPackageManagerImpl;


    @Override
    public DataTransferObject<Void> saveProductPackage(ProductPackageEntity entity) {
        DataTransferObject<Void> dto = new DataTransferObject();
        try {
            productPackageManagerImpl.saveProductPackage(entity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "saveProductPackage error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("保存失敗");
            return dto;
        }
        return dto;
    }

    @Override
    public DataTransferObject<ProductPackageEntity> getProductPackageById(Integer id) {
        DataTransferObject<ProductPackageEntity> dto = new DataTransferObject();
        try {
            ProductPackageEntity supplierWindowEntity = productPackageManagerImpl.getProductPackageById(id);
            dto.setData(supplierWindowEntity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "getProductPackageById error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("查询失敗");
            return dto;
        }
        return dto;
    }

    @Override
    public DataTransferObject<Void> updateProductPackage(ProductPackageEntity entity) {
        DataTransferObject<Void> dto = new DataTransferObject();
        try {
            productPackageManagerImpl.updateProductPackage(entity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "updateProductPackage error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("更新失敗");
            return dto;
        }
        return dto;
    }

    @Override
    public DataTransferObject<Void> deleteProductPackage(Integer productId,Integer packageId) {
        DataTransferObject<Void> dto = new DataTransferObject();
        try {
            productPackageManagerImpl.deleteProductPackage(productId,packageId);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "deleteProductPackage error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("删除失敗");
            return dto;
        }
        return dto;
    }

}
