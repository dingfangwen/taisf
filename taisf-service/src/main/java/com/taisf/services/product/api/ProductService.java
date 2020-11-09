package com.taisf.services.product.api;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.taisf.services.product.dto.ProductListRequest;
import com.taisf.services.product.entity.ProductEntity;

import java.util.List;

/**
 * <p>商品列表</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/3/27.
 * @version 1.0
 * @since 1.0
 */
public interface ProductService {
    /**
     * @author:zhangzhengguang
     * @date:2017/10/11
     * @description:分页查询商品列表
     **/
    DataTransferObject<PagingResult<ProductEntity>> pageListProduct(ProductListRequest request);

    /**
     * @author:zhangzhengguang
     * @date:2017/10/12
     * @description:新增商品
     **/
    DataTransferObject<Void> saveProduct(ProductEntity productEntity);

    /**
     * @author:zhangzhengguang
     * @date:2017/10/12
     * @description:根据ID查询菜品
     **/
    DataTransferObject<ProductEntity> getProductById(Integer id);

    /**
     * @author:zhangzhengguang
     * @date:2017/10/12
     * @description:根据ID修改菜品
     **/
    DataTransferObject<Void> updateProduct(ProductEntity productEntity);

    /**
     * @author:zhangzhengguang
     * @date:2017/10/13
     * @description:根据分类查询菜品集合
     **/
    DataTransferObject<List<ProductEntity>> getListByClassify(Integer productClassify);
}
