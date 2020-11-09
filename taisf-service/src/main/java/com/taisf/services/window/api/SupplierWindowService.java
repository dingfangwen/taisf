package com.taisf.services.window.api;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.taisf.services.window.entity.SupplierWindowEntity;
import com.taisf.services.window.req.SupplierWindowListRequest;

import java.util.List;

public interface SupplierWindowService {
    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:新增
     **/
    DataTransferObject<Void> saveSupplierWindow(SupplierWindowEntity entity);

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:根据id查询
     **/
    DataTransferObject<SupplierWindowEntity> getSupplierWindowById(Integer id);

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:修改
     **/
    DataTransferObject<Void> updateSupplierWindow(SupplierWindowEntity entity);

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:删除
     **/
    DataTransferObject<Void> deleteSupplierWindow(Integer id);

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:分页查询
     **/
    DataTransferObject<PagingResult<SupplierWindowEntity>>  pageListSupplierWindow(SupplierWindowListRequest request);

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:查询所有
     * **/
    DataTransferObject<List<SupplierWindowEntity>> findListSupplierWindow(SupplierWindowListRequest request);
}
