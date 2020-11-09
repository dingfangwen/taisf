package com.taisf.services.supplier.dao;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.dao.page.PageBounds;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.product.ProductVO;
import com.taisf.services.product.dto.ProductListRequest;
import com.taisf.services.product.entity.ProductEntity;
import com.taisf.services.supplier.dto.SupplierProductRequest;
import com.taisf.services.supplier.entity.SupplierProductEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>供应商的财务信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/20.
 * @version 1.0
 * @since 1.0
 */
@Repository("supplier.supplierProductDao")
public class SupplierProductDao extends BaseDao {

    private String SQLID = "supplier.supplierProductDao.";

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(SupplierProductDao.class);




    /**
     * 获取当前供应商的菜单信息
     * @author afi
     * @param supplierProductRequest
     * @return
     */
    public List<ProductEntity> getProductListBySupplierAndType(SupplierProductRequest supplierProductRequest){
        return mybatisDaoContext.findAll(SQLID+"getProductListBySupplierAndType", ProductEntity.class, supplierProductRequest);
    }


    /**
     * 获取当前的供应商菜单信息
     * @author afi
     * @param supplierCode
     * @return
     */
    public List<SupplierProductEntity> getSupplierProductByCode(String supplierCode){
        return mybatisDaoContext.findAll(SQLID+"getSupplierProductByCode", SupplierProductEntity.class, supplierCode);
    }

    /**
     * 增加供应商菜单信息
     * @author afi
     * @param record
     * @return
     */
    public int saveSupplierProduct(SupplierProductEntity record){
        if (Check.NuNObj(record.getCreateTime())){
            record.setCreateTime(new Date());
        }
        return mybatisDaoContext.save(SQLID + "saveSupplierProduct", record);
    }
    /**
     * 修改供应商菜单信息
     * @author afi
     * @param record
     * @return
     */
    public int updateSupplierProduct(SupplierProductEntity record){
        return mybatisDaoContext.update(SQLID + "updateSupplierProduct", record);
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/12
     * @description:根据用户id关联用户供餐商中间表得到上架code查询供餐商菜品信息
     **/
    public List<SupplierProductEntity> getSupplierProductByUserId(String userId){
        return mybatisDaoContext.findAll(SQLID+"getSupplierProductByUserId", SupplierProductEntity.class, userId);
    }


    public List<SupplierProductEntity> getSupplierProductByCodeAndWeek(String supplierCode,Integer week){
        Map<String,Object> par = new HashMap<>();
        par.put("supplierCode",supplierCode);
        par.put("week",week);

        return mybatisDaoContext.findAll(SQLID+"getSupplierProductByCodeAndWeek", SupplierProductEntity.class, par);
    }



    /**
     * @author:zhangzhengguang
     * @date:2017/10/12
     * @description:撤回菜品
     **/
    public int deleteByUserIdAndProudctIdAndWeek(String supplierCode,Integer productId,Integer week){
        Map<String,Object> map = new HashMap<>();
        map.put("productId",productId);
        map.put("supplierCode",supplierCode);
        map.put("week",week);
        return mybatisDaoContext.delete(SQLID + "deleteByUserIdAndProudctIdAndWeek", map);
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/12
     * @description:撤回菜品
     **/
    public int deleteBySupplierCodeAndProudctId(String supplierCode,Integer productId){
        Map<String,Object> map = new HashMap<>();
        map.put("productId",productId);
        map.put("supplierCode",supplierCode);
        return mybatisDaoContext.delete(SQLID + "deleteBySupplierCodeAndProudctId", map);
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/13
     * @description:分页查询菜品列表
     **/
    public PagingResult<ProductVO> pageListProduct(ProductListRequest request){
        PageBounds pageBounds = new PageBounds();
        pageBounds.setLimit(request.getLimit());
        pageBounds.setPage(request.getPage());
        return mybatisDaoContext.findForPage(SQLID+"pageListSupplierProduct",ProductVO.class,request,pageBounds);
    }
}
