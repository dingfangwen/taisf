package com.taisf.services.product.dao;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.dao.page.PageBounds;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.product.dto.ProductListRequest;
import com.taisf.services.product.entity.ProductEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>商品信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/13.
 * @version 1.0
 * @since 1.0
 */
@Repository("product.productDao")
public class ProductDao extends BaseDao {

    private String SQLID = "product.productDao.";

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(ProductDao.class);




    /**
     * 根据id获取当前的菜单
     * @author afi
     * @param id
     * @return
     */
    public ProductEntity getProductById(Integer id){
        return mybatisDaoContext.findOneSlave(SQLID+"getProductById", ProductEntity.class, id);
    }


    /**
     * @author:zhangzhengguang
     * @date:2017/10/11
     * @description:分页查询商品列表
     **/
    public PagingResult<ProductEntity> pageListProduct(ProductListRequest request){
        PageBounds pageBounds = new PageBounds();
        pageBounds.setLimit(request.getLimit());
        pageBounds.setPage(request.getPage());
        return mybatisDaoContext.findForPage(SQLID+"pageListProduct",ProductEntity.class,request,pageBounds);
    }


    /**
     * 根据id获取当前的菜单list
     * @author afi
     * @param packageId
     * @return
     */
    public List<ProductEntity> getProductByPackageId(Integer packageId){
        Map<String,Object> par = new HashMap<>();
        par.put("packageId",packageId);
        return mybatisDaoContext.findAll(SQLID+"getProductByPackageId", ProductEntity.class, par);
    }

    /**
     * 根据id获取当前的菜单list
     * @author afi
     * @param list
     * @return
     */
    public List<ProductEntity> getProductByList(List<Integer> list){
        Map<String,Object> par = new HashMap<>();
        par.put("productIds",list);
        return mybatisDaoContext.findAll(SQLID+"getProductByList", ProductEntity.class, par);
    }


    /**
     * 修改商品信息
     * @author afi
     * @param record
     * @return
     */
    public int updateProduct(ProductEntity record){
       return mybatisDaoContext.update(SQLID + "updateProduct", record);
    }


    /**
     * 新增商品信息
     * @author afi
     * @param record
     * @return
     */
    public int saveProduct(ProductEntity record){
        if (Check.NuNObj(record.getCreateTime())){
            record.setCreateTime(new Date());
        }
        return mybatisDaoContext.save(SQLID + "saveProduct", record);
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/13
     * @description:根据分类查询菜品集合
     **/
    public List<ProductEntity> getListByClassify(Integer productClassify){
        Map<String,Object>  map = new HashMap<>();
        map.put("productClassify",productClassify);
        return mybatisDaoContext.findAll(SQLID+"getListByClassify", ProductEntity.class, map);
    }

    public List<ProductEntity> findAll(ProductListRequest request){
        return mybatisDaoContext.findAll(SQLID+"pageListProduct",ProductEntity.class,request);
    }


    /**
     * 获取当前分类的
     * @param supplierCode
     * @return
     */
    public List<ProductEntity> getProductBySupplierCode(String supplierCode){
        Map<String,Object>  map = new HashMap<>();
        map.put("supplierCode",supplierCode);
        return mybatisDaoContext.findAll(SQLID+"getProductBySupplierCode", ProductEntity.class, map);
    }



}
