package com.taisf.services.enterprise.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.enterprise.entity.EnterpriseConfigEntity;

/**
 * <p>企业配置</p>
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
@Repository("enterprise.enterpriseConfigDao")
public class EnterpriseConfigDao extends BaseDao {

    private String SQLID = "enterprise.enterpriseConfigDao.";

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(EnterpriseConfigDao.class);


    /**
     * 获取当前的企业配置
     * @author afi
     * @param enterpriseCode
     * @return
     */
    public EnterpriseConfigEntity getEnterpriseConfigByCode(String enterpriseCode){
        return mybatisDaoContext.findOne(SQLID+"getEnterpriseConfigByCode", EnterpriseConfigEntity.class, enterpriseCode);
    }

    /**
     * 增加配置信息
     * @author afi
     * @param record
     * @return
     */
    public int saveEnterpriseConfig(EnterpriseConfigEntity record){

        return mybatisDaoContext.save(SQLID + "saveEnterpriseConfig", record);
    }
    /**
     * 修改配置信息
     * @author afi
     * @param record
     * @return
     */
    public int updateEnterpriseConfig(EnterpriseConfigEntity record){
        return mybatisDaoContext.update(SQLID + "updateEnterpriseConfig", record);
    }

    /**
     * 根据企业编号修改配置信息
     * @author afi
     * @param record
     * @return
     */
    public int updateConfigByEnterpriseCode(EnterpriseConfigEntity record){
        return mybatisDaoContext.update(SQLID + "updateConfigByEnterpriseCode", record);
    }
    
}
