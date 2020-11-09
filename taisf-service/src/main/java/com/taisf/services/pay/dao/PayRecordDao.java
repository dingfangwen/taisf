package com.taisf.services.pay.dao;

import com.jk.framework.base.exception.BusinessException;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.DateUtil;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.order.entity.OrderProductEntity;
import com.taisf.services.pay.entity.PayRecordEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>支付回调</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/3/25.
 * @version 1.0
 * @since 1.0
 */
@Repository("pay.payRecordDao")
public class PayRecordDao extends BaseDao {

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(PayRecordDao.class);

    private String SQLID = "pay.payRecordDao.";

    /**
     * 获取当前的配置信息
     * @author afi
     * @param orderSn
     * @return
     */
    public List<PayRecordEntity> getPayRecordByOrderSn(String orderSn){
        Map<String,Object> par = new HashMap<>();
        par.put("orderSn",orderSn);
        return mybatisDaoContext.findAll(SQLID + "getPayRecordBySn",PayRecordEntity.class,par);
    }


    /**
     * 获取当前的用户当前的金额
     * @param userId
     * @return
     */
    public Long getUserCostToday(String userId){

        Map<String,Object> par = new HashMap<>();
        par.put("userId",userId);
        par.put("start",DateUtil.getDayStart(new Date()));
        par.put("end",DateUtil.getDayEnd(new Date()));
        return mybatisDaoContext.count(SQLID + "getUserCostToday",par);
    }

    /**
     * 保存支付回调信息
     * @author afi
     * @param recordEntity
     * @return
     */
    public int savePayRecord(PayRecordEntity  recordEntity){
        if (Check.NuNObj(recordEntity.getCreateTime())){
            recordEntity.setCreateTime(new Date());
        }
        return mybatisDaoContext.save(SQLID + "savePayRecord",recordEntity);
    }


    /**
     * 修改支付记录
     * @author afi
     * @param recordEntity
     * @return
     */
    public int updatePayRecord(PayRecordEntity  recordEntity){
        if (Check.NuNObjs(recordEntity.getOrderSn())){
            throw new BusinessException("更新主键判断为空");
        }
        return mybatisDaoContext.update(SQLID + "updatePayRecord",recordEntity);
    }


    public List<PayRecordEntity> getPayRecordByOrderSnList(List<String> orderSnList){
        return mybatisDaoContext.findAll(SQLID + "getPayRecordBySnList",PayRecordEntity.class,orderSnList);
    }

}
