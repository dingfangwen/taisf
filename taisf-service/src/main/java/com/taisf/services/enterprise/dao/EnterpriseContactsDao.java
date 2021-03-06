package com.taisf.services.enterprise.dao;

import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.UUIDGenerator;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.enterprise.entity.EnterpriseContactsEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>企业联系人</p>
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
@Repository("enterprise.enterpriseContactsDao")
public class EnterpriseContactsDao extends BaseDao {

    private String SQLID = "enterprise.enterpriseContactsDao.";

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(EnterpriseContactsDao.class);


    /**
     * 获取当前的联系人信息
     * @author afi
     * @param enterpriseCode
     * @return
     */
    public List<EnterpriseContactsEntity> getEnterpriseContactsByCode(String enterpriseCode){
        return mybatisDaoContext.findAll(SQLID+"getEnterpriseContactsByCode", EnterpriseContactsEntity.class, enterpriseCode);
    }

    /**
     * 增加联系人信息
     * @author afi
     * @param record
     * @return
     */
    public int saveEnterpriseContacts(EnterpriseContactsEntity record){
        if (Check.NuNStr(record.getFid())){
            record.setFid(UUIDGenerator.hexUUID());
        }
        return mybatisDaoContext.save(SQLID + "saveEnterpriseContacts", record);
    }
    /**
     * 修改联系人信息
     * @author afi
     * @param record
     * @return
     */
    public int updateEnterpriseContacts(EnterpriseContactsEntity record){
        return mybatisDaoContext.update(SQLID + "updateEnterpriseContacts", record);
    }

}
