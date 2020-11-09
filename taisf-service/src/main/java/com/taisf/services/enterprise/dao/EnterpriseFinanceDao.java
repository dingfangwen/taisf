package com.taisf.services.enterprise.dao;

import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.enterprise.entity.EnterpriseFinanceEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * <p>企业财务信息</p>
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
@Repository("enterprise.enterpriseFinanceDao")
public class EnterpriseFinanceDao extends BaseDao {

    private String SQLID = "enterprise.enterpriseFinanceDao.";

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(EnterpriseFinanceDao.class);


    /**
     * 获取当前的企业财务信息
     * @author afi
     * @param enterpriseCode
     * @return
     */
    public EnterpriseFinanceEntity getEnterpriseFinanceByCode(String enterpriseCode){
        return mybatisDaoContext.findOne(SQLID+"getEnterpriseFinanceByCode", EnterpriseFinanceEntity.class, enterpriseCode);
    }

    /**
     * 增加企业财务
     * @author afi
     * @param record
     * @return
     */
    public int saveEnterpriseFinance(EnterpriseFinanceEntity record){

        return mybatisDaoContext.save(SQLID + "saveEnterpriseFinance", record);
    }
    /**
     * 修改企业财务信息
     * @author afi
     * @param record
     * @return
     */
    public int updateEnterpriseFinance(EnterpriseFinanceEntity record){
        return mybatisDaoContext.update(SQLID + "updateEnterpriseFinance", record);
    }

}
