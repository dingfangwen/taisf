package com.taisf.services.supplier.dao;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.dao.page.PageBounds;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.supplier.dto.SupplierProductRequest;
import com.taisf.services.supplier.entity.SupplierPackageEntity;
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
@Repository("supplier.supplierPackageDao")
public class SupplierPackageDao extends BaseDao {

    private String SQLID = "supplier.supplierPackageDao.";

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(SupplierPackageDao.class);


//    /**
//     * 获取礼包列表
//     * @author afi
//     * @param list
//     * @return
//     */
//    public List<SupplierPackageEntity> getSupplierPackageByList(List<Integer> list){
//        Map<String,Object> par = new HashMap<>();
//        par.put("productIds",list);
//        return mybatisDaoContext.findAll(SQLID+"getSupplierPackageByList", SupplierPackageEntity.class, par);
//    }
//
//
//
//
//    /**
//     * 获取当前的供应商打包信息
//     * @author afi
//     * @param supplierCode
//     * @return
//     */
//    public List<SupplierPackageEntity> getSupplierPackageByCodeAndWeek(String supplierCode,Integer week){
//        Map<String,Object> par = new HashMap<>();
//        par.put("supplierCode",supplierCode);
//        par.put("week",week);
//        return mybatisDaoContext.findAll(SQLID+"getSupplierPackageByCodeAndWeek", SupplierPackageEntity.class, par);
//    }
//
//    /**
//     * 获取当前的供应商打包信息
//     * @author afi
//     * @param supplierCode
//     * @return
//     */
//    public List<SupplierPackageEntity> getSupplierPackageByCode(String supplierCode){
//        return mybatisDaoContext.findAll(SQLID+"getSupplierPackageByCode", SupplierPackageEntity.class, supplierCode);
//    }
//
//    /**
//     * 增加供应商组合商品信息
//     * @author afi
//     * @param record
//     * @return
//     */
//    public int saveSupplierPackage(SupplierPackageEntity record){
//        if (Check.NuNObj(record.getCreateTime())){
//            record.setCreateTime(new Date());
//        }
//        return mybatisDaoContext.save(SQLID + "saveSupplierPackage", record);
//    }
//    /**
//     * 修改供应商组合商品信息
//     * @author afi
//     * @param record
//     * @return
//     */
//    public int updateSupplierPackage(SupplierPackageEntity record){
//        return mybatisDaoContext.update(SQLID + "updateSupplierPackage", record);
//    }
//
//
//    /**
//     * @author:zhangzhengguang
//     * @date:2017/10/13
//     * @description:分页查询套餐列表
//     **/
//    public PagingResult<SupplierPackageEntity> pageListSupplierProduct(SupplierProductRequest request){
//        PageBounds pageBounds = new PageBounds();
//        pageBounds.setLimit(request.getLimit());
//        pageBounds.setPage(request.getPage());
//        return mybatisDaoContext.findForPage(SQLID+"pageListSupplierProduct",SupplierPackageEntity.class,request,pageBounds);
//    }
//
//    /**
//     * @author:zhangzhengguang
//     * @date:2017/10/13
//     * @description:删除组合套餐
//     **/
//    public int deleteByPrimaryKey(Integer id){
//        Map<String,Object> map = new HashMap<>();
//        map.put("id",id);
//        return mybatisDaoContext.delete(SQLID + "deleteByPrimaryKey",map);
//    }
//
//    /**
//     * @author:zhangzhengguang
//     * @date:2017/10/13
//     * @description:查询组合套餐信息根据ID
//     **/
//    public SupplierPackageEntity getSupplierPackageById(Integer id){
//        Map<String,Object> map = new HashMap<>();
//        map.put("id",id);
//        return mybatisDaoContext.findOne(SQLID + "getById",SupplierPackageEntity.class,map);
//    }
//
//    /**
//     * @author:zhangzhengguang
//     * @date:2017/10/13
//     * @description:修改组合套餐信息根据ID
//     **/
//    public int updateSupplierPackageById(SupplierPackageEntity record){
//        return mybatisDaoContext.update(SQLID + "updateSupplierPackageById", record);
//    }
}
