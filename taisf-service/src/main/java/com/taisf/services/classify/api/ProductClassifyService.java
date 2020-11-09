package com.taisf.services.classify.api;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.taisf.services.classify.entity.ProductClassifyEntity;
import com.taisf.services.classify.req.ProductClassifyListRequest;

import java.util.List;

public interface ProductClassifyService {
    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:新增
     **/
    DataTransferObject<Void> saveProductClassify(ProductClassifyEntity entity);

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:根据id查询
     **/
    DataTransferObject<ProductClassifyEntity> getProductClassifyById(Integer id);

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:修改
     **/
    DataTransferObject<Void> updateProductClassify(ProductClassifyEntity entity);

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:删除
     **/
    DataTransferObject<Void> deleteProductClassify(Integer id);

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:分页查询
     **/
    DataTransferObject<PagingResult<ProductClassifyEntity>>  pageListProductClassify(ProductClassifyListRequest request);

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:查询所有
     * **/
    DataTransferObject<List<ProductClassifyEntity>> findListProductClassify(ProductClassifyListRequest request);
}
