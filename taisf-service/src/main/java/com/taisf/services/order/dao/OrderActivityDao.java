package com.taisf.services.order.dao;


import com.jk.framework.base.exception.BusinessException;
import com.jk.framework.base.utils.Check;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.order.entity.OrderActivityEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * <p>
 * 订单 活动 信息
 * </p>
 * <p/>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2017/9/14.
 * @version 1.0
 * @since 1.0
 */
@Repository("order.activityDao")
public class OrderActivityDao extends BaseDao{
	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory.getLogger(OrderActivityDao.class);
	private String SQLID = "order.activityDao.";

	
	/**
	 * 插入资源记录
	 * @author afi
	 * @param activityEntity
	 */
	public void saveOrderActivity(OrderActivityEntity activityEntity) {
		if(Check.NuNObj(activityEntity) || Check.NuNStr(activityEntity.getOrderSn())){
        	LogUtil.info(logger, "activityEntity  insertActivityRes param is : " + activityEntity);
            throw new BusinessException("activityEntity  insertActivityRes param is : " + activityEntity);
        }
		mybatisDaoContext.save(SQLID + "saveOrderActivity", activityEntity);
	}


    /**
     * 获取当前订单的优惠券信息
	 * @author afi
     * @param orderSn
     * @return
     */
    public List<OrderActivityEntity> getActivityByOrderSn(String orderSn){
        if(Check.NuNStr(orderSn)){
           return null;
        }
        return mybatisDaoContext.findAll(SQLID + "getActivityByOrderSn", OrderActivityEntity.class, orderSn);
    }



}
