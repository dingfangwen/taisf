package com.jk.services.payment.dao;


import com.jk.services.payment.entity.PayDetailEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * <p>支付明细</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/5/13.
 * @version 1.0
 * @since 1.0
 */
@Repository("payment.payDetailDao")
public class PayDetailDao extends BaseDao{

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(PayDetailDao.class);

    private String SQLID = "payment.payDetailDao.";


    public int insert(PayDetailEntity record){
        return mybatisDaoContext.save(SQLID + "insert",record);
    }


    public PayDetailEntity selectByPayId(Integer payId){
        return mybatisDaoContext.findOne(SQLID + "selectByPayId",PayDetailEntity.class,payId);
    }

    public int update(PayDetailEntity record){
        return mybatisDaoContext.update(SQLID + "update",record);
    }
}