package com.taisf.services.supplier.dao;

import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.UUIDGenerator;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.supplier.entity.SupplierContactsEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>供应商的联系人信息</p>
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
@Repository("supplier.supplierContactsDao")
public class SupplierContactsDao  extends BaseDao {

    private String SQLID = "supplier.supplierContactsDao.";

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(SupplierContactsDao.class);

    /**
     * 获取当前的联系人信息
     * @author afi
     * @param supplierCode
     * @return
     */
    public List<SupplierContactsEntity> getSupplierContactsByCode(String supplierCode){
        return mybatisDaoContext.findAll(SQLID+"getSupplierContactsByCode", SupplierContactsEntity.class, supplierCode);
    }

    /**
     * 增加联系人信息
     * @author afi
     * @param record
     * @return
     */
    public int saveSupplierContacts(SupplierContactsEntity record){
        if (Check.NuNStr(record.getFid())){
            record.setFid(UUIDGenerator.hexUUID());
        }
        return mybatisDaoContext.save(SQLID + "saveSupplierContacts", record);
    }
    /**
     * 修改联系人信息
     * @author afi
     * @param record
     * @return
     */
    public int updateSupplierContacts(SupplierContactsEntity record){
        return mybatisDaoContext.update(SQLID + "updateSupplierContacts", record);
    }
}
