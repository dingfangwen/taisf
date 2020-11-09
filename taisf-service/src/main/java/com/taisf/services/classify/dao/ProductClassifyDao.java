package com.taisf.services.classify.dao;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.dao.page.PageBounds;
import com.taisf.services.classify.entity.ProductClassifyEntity;
import com.taisf.services.classify.req.ProductClassifyListRequest;
import com.taisf.services.common.dao.BaseDao;
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
@Repository("productClassifyDao")
public class ProductClassifyDao extends BaseDao {

    private String SQLID = "productClassifyDao.";

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(ProductClassifyDao.class);

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:新增
     **/
    public int saveProductClassify(ProductClassifyEntity entity){
        if (Check.NuNObj(entity.getCreateTime())){
            entity.setCreateTime(new Date());
        }
        return mybatisDaoContext.save(SQLID + "insertSelective", entity);
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:根据id查询
     **/
    public ProductClassifyEntity getProductClassifyById(Integer id){
        return mybatisDaoContext.findOneSlave(SQLID+"selectByPrimaryKey", ProductClassifyEntity.class, id);
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:修改
     **/
    public int updateProductClassify(ProductClassifyEntity entity){
        return mybatisDaoContext.update(SQLID + "updateByPrimaryKeySelective", entity);
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:删除
     **/
    public int deleteProductClassify(Integer id){
        Map<String,Object> param = new HashMap<>();
        param.put("id",id);
        return mybatisDaoContext.delete(SQLID + "deleteByPrimaryKey",param);
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:分页查询
     **/
    public PagingResult<ProductClassifyEntity> pageListProductClassify(ProductClassifyListRequest request){
        PageBounds pageBounds = new PageBounds();
        pageBounds.setLimit(request.getLimit());
        pageBounds.setPage(request.getPage());
        return mybatisDaoContext.findForPage(SQLID+"pageListProductClassify",ProductClassifyEntity.class,request,pageBounds);
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:查询所有
     * **/
    public List<ProductClassifyEntity> findListProductClassify(ProductClassifyListRequest request){
        return mybatisDaoContext.findAll(SQLID+"pageListProductClassify",ProductClassifyEntity.class,request);
    }

    /**
     * 获取供应商的分类
     * @param supplierCode
     * @return
     */
    public List<ProductClassifyEntity> listProductClassifyBySupplierCode(String supplierCode){
        return mybatisDaoContext.findAll(SQLID+"listProductClassifyBySupplierCode",ProductClassifyEntity.class,supplierCode);
    }


}
