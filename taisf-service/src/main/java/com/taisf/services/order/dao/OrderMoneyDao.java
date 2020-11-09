package com.taisf.services.order.dao;


import com.jk.framework.base.exception.BusinessException;
import com.jk.framework.base.utils.Check;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.order.entity.OrderMoneyEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * <p>订单的金额的操作 </p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2016/4/8.
 * @version 1.0
 * @since 1.0
 */
@Repository("order.orderMoneyDao")
public class OrderMoneyDao extends BaseDao{
	
	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory.getLogger(OrderMoneyDao.class);

	private String SQLID = "order.orderMoneyDao.";



    /**
     * 获取订单的金额
     * @author afi
     * @param orderSn
     * @return
     */
    public OrderMoneyEntity getOrderMoneyByOrderSn(String orderSn) {
        return mybatisDaoContext.findOne(SQLID + "getOrderMoneyByOrderSn", OrderMoneyEntity.class, orderSn);
    }


    /**
     * 保存订单金额信息
     * @author afi
     * @param orderMoneyEntity
     * @return
     */
    public int saveOrderMoney(OrderMoneyEntity orderMoneyEntity){
        if(Check.NuNObj(orderMoneyEntity)){
        	LogUtil.info(logger,"orderMoneyEntity is null on insertOrderMoney");
            throw new BusinessException("orderMoneyEntity is null on insertOrderMoney");
        }
        return mybatisDaoContext.save(SQLID + "saveOrderMoney", orderMoneyEntity);
    }

    /**
     * 保存订单金额信息
     * @author afi
     * @param orderMoneyEntity
     * @return
     */
    public int updateOrderMoney(OrderMoneyEntity orderMoneyEntity){
        if(Check.NuNObj(orderMoneyEntity)){
        	LogUtil.info(logger,"orderMoneyEntity is null on insertOrderMoney");
            throw new BusinessException("orderMoneyEntity is null on insertOrderMoney");
        }
        if(Check.NuNStr(orderMoneyEntity.getOrderSn())){
        	LogUtil.info(logger,"orderSn is null on insertOrderMoney");
            throw new BusinessException("orderSn is null on insertOrderMoney");
        }
        
        return mybatisDaoContext.update(SQLID + "updateOrderMoney", orderMoneyEntity);
    }

    
}
