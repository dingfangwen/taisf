package com.taisf.services.supplier.dao;

import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.supplier.entity.SupplierExtEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * <p>供应商的扩展信息</p>
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
@Repository("supplier.supplierExtDao")
public class SupplierExtDao extends BaseDao {

    private String SQLID = "supplier.supplierExtDao.";

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(SupplierExtDao.class);

    /**
     * 获取当前的供应商扩展信息
     * @author afi
     * @param supplierCode
     * @return
     */
    public SupplierExtEntity getSupplierExtByCode(String supplierCode){
        return mybatisDaoContext.findOne(SQLID+"getSupplierExtByCode", SupplierExtEntity.class, supplierCode);
    }

    /**
     * 增加供应商扩展信息
     * @author afi
     * @param record
     * @return
     */
    public int saveSupplierExt(SupplierExtEntity record){
        return mybatisDaoContext.save(SQLID + "saveSupplierExt", record);
    }
    /**
     * 修改供应商扩展信息
     * @author afi
     * @param record
     * @return
     */
    public int updateSupplierExt(SupplierExtEntity record){
        return mybatisDaoContext.update(SQLID + "updateSupplierExt", record);
    }
}
