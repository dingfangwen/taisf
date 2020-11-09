package com.taisf.services.order.dao;


import com.jk.framework.base.utils.Check;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.order.entity.OrderPayEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * <p>
 * 城市信息
 * </p>
 * <p/>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2016/4/1.
 * @version 1.0
 * @since 1.0
 */
@Repository("order.payDao")
public class OrderPayDao extends BaseDao{

	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory.getLogger(OrderPayDao.class);
	
	private String SQLID = "order.payDao.";
	
	
	/**
	 * 插入资源记录
	 * @author afi
	 * @param orderPayEntity
	 */
	public int saveOrderPay(OrderPayEntity orderPayEntity) {
		if(Check.NuNObj(orderPayEntity)){
			LogUtil.info(logger,"current orderPayEntity is null on insertPay"+orderPayEntity);
		}
		return mybatisDaoContext.save(SQLID + "saveOrderPay", orderPayEntity);
	}
	
	
	/**
	 * 根据订单号获取付信息
	 * @author afi
	 * @param orderSn
	 * @return
	 */
	public OrderPayEntity getOrderPayByOrderSn(String orderSn){
		return mybatisDaoContext.findOne(SQLID + "selectByOrderSn", OrderPayEntity.class, orderSn);
	}

	
	
	/**
	 * 更新资源记录
	 * @author afi
	 * @param orderPayEntity
	 */
	public int updateOrderPay(OrderPayEntity orderPayEntity){
		return mybatisDaoContext.update(SQLID + "updateOrderPay", orderPayEntity);
	}

}
