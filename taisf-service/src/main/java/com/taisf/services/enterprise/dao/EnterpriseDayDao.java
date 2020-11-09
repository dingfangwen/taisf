package com.taisf.services.enterprise.dao;

import com.jk.framework.base.utils.DateUtil;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.enterprise.dto.EnterpriseDayRequest;
import com.taisf.services.enterprise.entity.EnterpriseDayEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>企业基本信息</p>
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
@Repository("enterprise.enterpriseDayDao")
public class EnterpriseDayDao extends BaseDao {

    private String SQLID = "enterprise.enterpriseDayDao.";

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(EnterpriseDayDao.class);


    /**
     * 获取当前的企业配送时间
     * @author afi
     * @param enterpriseCode
     * @return
     */
    public List<EnterpriseDayEntity> getEnterpriseDaysByCode(String enterpriseCode){
        return mybatisDaoContext.findAll(SQLID+"getEnterpriseDaysByCode", EnterpriseDayEntity.class, enterpriseCode);
    }

    /**
     * 获取当前的企业配送时间
     * @author afi
     * @param enterpriseCode
     * @return
     */
    public List<EnterpriseDayEntity> getEnterpriseDaysByTime(String enterpriseCode, Date start,Date end){
        Map<String,Object> par = new HashMap<>();
        par.put("enterpriseCode",enterpriseCode);
        par.put("start", DateUtil.intFormat(start));
        par.put("end",DateUtil.intFormat(end));
        return mybatisDaoContext.findAll(SQLID+"getEnterpriseDaysByTime", EnterpriseDayEntity.class, par);
    }


    /**
     * 获取对象
     * @param enterpriseDayRequest
     */
    public EnterpriseDayEntity  getEnterpriseDays(EnterpriseDayRequest enterpriseDayRequest){
        return mybatisDaoContext.findOne(SQLID + "getEnterpriseDays",EnterpriseDayEntity.class, enterpriseDayRequest);
    }

    /**
     * 删除
     * @param enterpriseDayRequest
     */
    public int  delEnterpriseDays(EnterpriseDayRequest enterpriseDayRequest){

        return mybatisDaoContext.delete(SQLID + "delEnterpriseDays", enterpriseDayRequest);
    }

    /**
     * 增加企业
     * @author afi
     * @param record
     * @return
     */
    public int saveEnterpriseDay(EnterpriseDayEntity record){

        return mybatisDaoContext.save(SQLID + "saveEnterpriseDay", record);
    }
    /**
     * 修改企业信息
     * @author afi
     * @param record
     * @return
     */
    public int updateEnterpriseDay(EnterpriseDayEntity record){
        return mybatisDaoContext.update(SQLID + "updateEnterpriseDay", record);
    }

}
