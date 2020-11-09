package com.taisf.services.supplier.api;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.taisf.services.product.ProductVO;
import com.taisf.services.product.dto.ProductListRequest;
import com.taisf.services.supplier.entity.SupplierProductEntity;
import com.taisf.services.supplier.vo.ProductClassifyInfo;
import com.taisf.services.supplier.vo.ProductClassifyVO;
import com.taisf.services.supplier.vo.SelectInfo4Week;

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
public interface SupplierProductService {


    /**
     * 获取当前的列表信息
     *
     * @param supplierCode
     * @return
     */
    DataTransferObject<SelectInfo4Week> getSupplierClassifyProductByWeek(String enterpriseCode,String supplierCode, Integer week);

    /**
     * 获取当前的列表信息
     * @param supplierCode
     * @return
     */
    DataTransferObject<List<ProductClassifyVO>> getSupplierProductClassify(String supplierCode);

    /**
     * 获取当前的列表信息
     * @param supplierCode
     * @return
     */
    DataTransferObject<List<ProductClassifyInfo>> getSupplierClassifyProduct(String supplierCode,String enterpriseCode);


//    /**
//     * 获取当前供应商的商品了列表
//     * @author  afi
//     * @param supplierProductRequest
//     * @return
//     */
//    DataTransferObject<List<SupplierProductVO>> getSupplierProductList(SupplierProductRequest supplierProductRequest);

    /**
     * @author:zhangzhengguang
     * @date:2017/10/12
     * @description:根据用户id关联用户供餐商中间表得到上架code查询供餐商菜品信息
     **/
    DataTransferObject<List<SupplierProductEntity>> getSupplierProductByUserId(String userId);

    /**
     * 获取当前周的上架情况
     * @param userId
     * @param week
     * @return
     */
    DataTransferObject<List<SupplierProductEntity>> getSupplierProductByCodeAndWeek(String userId,Integer week);

    /**
     * @author:zhangzhengguang
     * @date:2017/10/12
     * @description:撤回菜品
     **/
    DataTransferObject<Void> deleteBySupplierCodeAndProudctId(String supplierCode, Integer productId);

    /**
     * @author:zhangzhengguang
     * @date:2017/10/12
     * @description:撤回菜品
     **/
    DataTransferObject<Void> deleteByUserIdAndProudctIdAndWeek(String userId, Integer productId,Integer week);

    /**
     * @author:zhangzhengguang
     * @date:2017/10/13
     * @description:商家添加菜品
     **/
    DataTransferObject<Void> saveSupplierProduct(SupplierProductEntity supplierProductEntity);

    /**
     * @author:zhangzhengguang
     * @date:2017/10/13
     * @description:分页查询菜品
     **/
    DataTransferObject<PagingResult<ProductVO>> pageListProduct(ProductListRequest request);
}
