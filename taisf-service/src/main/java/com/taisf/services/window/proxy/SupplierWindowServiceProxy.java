package com.taisf.services.window.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.window.api.SupplierWindowService;
import com.taisf.services.window.entity.SupplierWindowEntity;
import com.taisf.services.window.manager.SupplierWindowManagerImpl;
import com.taisf.services.window.req.SupplierWindowListRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:zhangzhengguang
 * @date:2018/9/19
 * @description:
 **/
@Component("supplierWindowServiceProxy")
public class SupplierWindowServiceProxy implements SupplierWindowService {


    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierWindowServiceProxy.class);

    @Resource(name = "supplierWindowManagerImpl")
    private SupplierWindowManagerImpl supplierWindowManagerImpl;


    @Override
    public DataTransferObject<Void> saveSupplierWindow(SupplierWindowEntity entity) {
        DataTransferObject<Void> dto = new DataTransferObject();
        try {
            supplierWindowManagerImpl.saveSupplierWindow(entity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "saveSupplierWindow error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("保存失敗");
            return dto;
        }
        return dto;
    }

    @Override
    public DataTransferObject<SupplierWindowEntity> getSupplierWindowById(Integer id) {
        DataTransferObject<SupplierWindowEntity> dto = new DataTransferObject();
        try {
            SupplierWindowEntity supplierWindowEntity = supplierWindowManagerImpl.getSupplierWindowById(id);
            dto.setData(supplierWindowEntity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "getSupplierWindowById error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("查询失敗");
            return dto;
        }
        return dto;
    }

    @Override
    public DataTransferObject<Void> updateSupplierWindow(SupplierWindowEntity entity) {
        DataTransferObject<Void> dto = new DataTransferObject();
        try {
            supplierWindowManagerImpl.updateSupplierWindow(entity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "updateSupplierWindow error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("更新失敗");
            return dto;
        }
        return dto;
    }

    @Override
    public DataTransferObject<Void> deleteSupplierWindow(Integer id) {
        DataTransferObject<Void> dto = new DataTransferObject();
        try {
            supplierWindowManagerImpl.deleteSupplierWindow(id);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "deleteSupplierWindow error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("删除失敗");
            return dto;
        }
        return dto;
    }

    @Override
    public DataTransferObject<PagingResult<SupplierWindowEntity>> pageListSupplierWindow(SupplierWindowListRequest request) {
        DataTransferObject<PagingResult<SupplierWindowEntity>> dto = new DataTransferObject();
        try {
            PagingResult<SupplierWindowEntity> result = supplierWindowManagerImpl.pageListSupplierWindow(request);
            dto.setData(result);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "pageListSupplierWindow error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("分页查询失敗");
            return dto;
        }
        return dto;
    }

    @Override
    public DataTransferObject<List<SupplierWindowEntity>> findListSupplierWindow(SupplierWindowListRequest request) {
        DataTransferObject<List<SupplierWindowEntity>> dto = new DataTransferObject();
        try {
            List<SupplierWindowEntity> listSupplierWindow = supplierWindowManagerImpl.findListSupplierWindow(request);
            dto.setData(listSupplierWindow);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "findListSupplierWindow error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("查询所有失敗");
            return dto;
        }
        return dto;
    }
}
