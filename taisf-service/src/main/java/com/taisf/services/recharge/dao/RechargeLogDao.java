package com.taisf.services.recharge.dao;

import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.recharge.entity.RechargeLogEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>充值记录信息</p>
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
@Repository("recharge.rechargeLogDao")
public class RechargeLogDao extends BaseDao {

    private String SQLID = "recharge.rechargeLogDao.";

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(RechargeLogDao.class);

    /**
     * 获取充值信息
     * @author afi
     * @param rechargeSn
     * @return
     */
    public List<RechargeLogEntity> getRechargeLogBySn(String rechargeSn){
        return mybatisDaoContext.findAll(SQLID+"getRechargeLogBySn", RechargeLogEntity.class, rechargeSn);
    }

    /**
     * 新增充值信息
     * @author afi
     * @param record
     * @return
     */
    public int saveRechargeLog(RechargeLogEntity record){

        return mybatisDaoContext.save(SQLID + "saveRechargeLog", record);
    }

}
