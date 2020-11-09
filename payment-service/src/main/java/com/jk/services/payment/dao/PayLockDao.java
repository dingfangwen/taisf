package com.jk.services.payment.dao;

import com.jk.services.payment.entity.PayLockEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * <p>支付锁</p>
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

@Repository("payment.payLockDao")
public class PayLockDao extends BaseDao{

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(PayLockDao.class);

    private String SQLID = "payment.payLockDao.";

    public int insert(PayLockEntity record){
        return mybatisDaoContext.save(SQLID + "insert",record);
    }

    public int delete(PayLockEntity record){
        return mybatisDaoContext.delete(SQLID + "delete",record);
    }
}