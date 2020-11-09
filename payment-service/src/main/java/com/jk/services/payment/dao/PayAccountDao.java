package com.jk.services.payment.dao;

import com.jk.services.payment.entity.PayAccountEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * <p>支付账号</p>
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
@Repository("payment.payAccountDao")
public class PayAccountDao extends BaseDao{

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(PayAccountDao.class);

    private String SQLID = "payment.payAccountDao.";

    public int insert(PayAccountEntity record){
        return mybatisDaoContext.save(SQLID + "insert",record);
    }

    public PayAccountEntity selectByPrimaryKey(Integer id){
        return mybatisDaoContext.findOne(SQLID + "selectByPrimaryKey",PayAccountEntity.class,id);
    }

    public int update(PayAccountEntity record){
        return mybatisDaoContext.update(SQLID + "update",record);
    }

    public PayAccountEntity selectObject(Map<String, Object> map){
        return mybatisDaoContext.findOne(SQLID + "selectObject",PayAccountEntity.class,map);
    }
}