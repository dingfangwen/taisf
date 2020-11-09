package com.taisf.services.order.dao;

import com.jk.framework.base.exception.BusinessException;
import com.jk.framework.base.utils.Check;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.order.entity.OrderBuffetEntity;
import com.taisf.services.order.entity.OrderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * <p>订单餐柜信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2019/4/4.
 * @version 1.0
 * @since 1.0
 */
@Repository("order.orderBuffetDao")
public class OrderBuffetDao extends BaseDao {

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(OrderBuffetDao.class);

    private String SQLID = "order.orderBuffetDao.";


    /**
     * 保存订单餐柜信息
     * @author afi
     * @param orderBuffetEntity
     * @return
     */
    public int saveOrderBuffet(OrderBuffetEntity orderBuffetEntity){
        if (Check.NuNObj(orderBuffetEntity)){
            orderBuffetEntity.setCreateTime(new Date());
        }
        return mybatisDaoContext.save(SQLID + "saveOrderBuffet", orderBuffetEntity);
    }

}
