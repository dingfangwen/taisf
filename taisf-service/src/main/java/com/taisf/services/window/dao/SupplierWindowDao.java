package com.taisf.services.window.dao;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.dao.page.PageBounds;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.window.entity.SupplierWindowEntity;
import com.taisf.services.window.req.SupplierWindowListRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zhangzhengguang
 * @date:2018/9/19
 * @description:
 **/
@Repository("supplierWindowDao")
public class SupplierWindowDao extends BaseDao {

    private String SQLID = "supplierWindowDao.";

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(SupplierWindowDao.class);

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:新增
     **/
    public int saveSupplierWindow(SupplierWindowEntity entity){
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
    public SupplierWindowEntity getSupplierWindowById(Integer id){
        return mybatisDaoContext.findOneSlave(SQLID+"selectByPrimaryKey", SupplierWindowEntity.class, id);
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:修改
     **/
    public int updateSupplierWindow(SupplierWindowEntity entity){
        return mybatisDaoContext.update(SQLID + "updateByPrimaryKeySelective", entity);
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:删除
     **/
    public int deleteSupplierWindow(Integer id){
        Map<String,Object> param = new HashMap<>();
        param.put("id",id);
        return mybatisDaoContext.delete(SQLID + "deleteByPrimaryKey",param);
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:分页查询
     **/
    public PagingResult<SupplierWindowEntity> pageListSupplierWindow(SupplierWindowListRequest request){
        PageBounds pageBounds = new PageBounds();
        pageBounds.setLimit(request.getLimit());
        pageBounds.setPage(request.getPage());
        return mybatisDaoContext.findForPage(SQLID+"pageListSupplierWindow",SupplierWindowEntity.class,request,pageBounds);
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:查询所有
     * **/
    public List<SupplierWindowEntity> findListSupplierWindow(SupplierWindowListRequest request){
        return mybatisDaoContext.findAll(SQLID+"pageListSupplierWindow",SupplierWindowEntity.class,request);
    }

}
