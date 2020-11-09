package com.taisf.services.order.dao;

import com.jk.framework.base.exception.BusinessException;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.DateUtil;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.common.valenum.OrdersStatusEnum;
import com.taisf.services.order.entity.OrderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 订单的主表操作 </p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2016/3/12.
 * @version 1.0
 * @since 1.0
 */
@Repository("order.orderBaseDao")
public class OrderBaseDao extends BaseDao{
	
	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory.getLogger(OrderBaseDao.class);

	private String SQLID = "order.orderBaseDao.";


    /**
     * 结束订单
     * @param orderSn
     * @param oldStatus
     * @return
     */
    public int payOrder(String orderSn,int oldStatus,boolean face){
        Map<String,Object> par = new HashMap<>();
        par.put("orderSn",orderSn);
        par.put("oldStatus",oldStatus);
        if (face){
            par.put("orderStatus", OrdersStatusEnum.RECEIVE.getCode());
        }else {
            par.put("orderStatus",OrdersStatusEnum.HAS_PAY.getCode());
        }
        return mybatisDaoContext.update(SQLID + "payOrder", par);
    }



    /**
     * 放餐
     * @param orderSn
     * @param oldStatus
     * @return
     */
    public int buffetOutOrder(String orderSn,int oldStatus){
        Map<String,Object> par = new HashMap<>();
        par.put("orderSn",orderSn);
        par.put("oldStatus",oldStatus);
        par.put("orderStatus",OrdersStatusEnum.RECEIVE.getCode());
        return mybatisDaoContext.update(SQLID + "updateOrderStatusOnly", par);
    }


    /**
     * 放餐
     * @param orderSn
     * @param oldStatus
     * @return
     */
    public int buffetInOrder(String orderSn,int oldStatus,String senderUid){
        Map<String,Object> par = new HashMap<>();
        par.put("orderSn",orderSn);
        par.put("oldStatus",oldStatus);
        par.put("knightUid",senderUid);
        par.put("orderStatus",OrdersStatusEnum.SEND_BUFFET.getCode());
        return mybatisDaoContext.update(SQLID + "updateOrderStatusOnly", par);
    }


    /**
     * 结束订单
     * @param orderSn
     * @param oldStatus
     * @return
     */
    public int finishOrder(String orderSn,int oldStatus,String senderUid){
        Map<String,Object> par = new HashMap<>();
        par.put("orderSn",orderSn);
        par.put("oldStatus",oldStatus);
        par.put("knightUid",senderUid);
        par.put("orderStatus",OrdersStatusEnum.RECEIVE.getCode());
        return mybatisDaoContext.update(SQLID + "updateOrderStatusOnly", par);
    }




    /**
     * 退款成功
     * @param orderSn
     * @return
     */
    public int refundOrderSuccess(String orderSn){
        Map<String,Object> par = new HashMap<>();
        par.put("orderSn",orderSn);
        return mybatisDaoContext.update(SQLID + "refundOrderSuccess", par);
    }




    /**
     * 取消订单
     * @param orderSn
     * @param oldStatus
     * @return
     */
    public int cancelOrder(String orderSn,int oldStatus){
        Map<String,Object> par = new HashMap<>();
        par.put("orderSn",orderSn);
        par.put("oldStatus",oldStatus);
        return mybatisDaoContext.update(SQLID + "cancelOrder", par);
    }



    /**
     * 申请退款
     * @param orderSn
     * @param oldStatus
     * @return
     */
    public int refundOrder(String orderSn,int oldStatus){
        Map<String,Object> par = new HashMap<>();
        par.put("orderSn",orderSn);
        par.put("oldStatus",oldStatus);
        return mybatisDaoContext.update(SQLID + "refundOrder", par);
    }



    /**
     * 获取当前订单的信息
     * @author afi
     * @param orderSn
     * @return
     */
    public OrderEntity getOrderBaseByOrderSn(String orderSn){
        if(Check.NuNStr(orderSn)){
        	LogUtil.error(logger,"orderSn is null on getOrderINfoByOrderSn");
            throw  new BusinessException("orderSn is null on getOrderINfoByOrderSn");
        }
        return mybatisDaoContext.findOne(SQLID + "getOrderBaseByOrderSn", OrderEntity.class, orderSn);
    }

    /**
     * 获取当前的待取消的定点列表
     * @author afi
     * @return
     */
    public List<OrderEntity> getOrder2Canceled(){
        Map<String,Object> par = new HashMap<>();
        par.put("time", DateUtil.jumpMinute(new Date(),-15));
        return mybatisDaoContext.findAll(SQLID + "getOrder2Canceled", OrderEntity.class, par);
    }


    /**
     * 保存订单信息
     * @author afi
     * @param orderEntity
     * @return
     */
    public int saveOrderBase(OrderEntity orderEntity){
        if (Check.NuNObj(orderEntity)){
            orderEntity.setCreateTime(new Date());
        }
        return mybatisDaoContext.save(SQLID + "saveOrderBase", orderEntity);
    }

    /**
     * 更新订单的信息
     * @author afi
     * @param orderEntity
     * @return
     */
    public int updateOrderBaseByOrderSn(OrderEntity orderEntity){
        if(Check.NuNObj(orderEntity)){
        	LogUtil.error(logger,"orderEntity is null on updateOrderBaseByOrderSn");
            throw new BusinessException("orderEntity is null on updateOrderBaseByOrderSn");
        }
        if(Check.NuNStr(orderEntity.getOrderSn())){
        	LogUtil.error(logger,"orderSn is null on updateOrderBaseByOrderSn");
            throw new BusinessException("orderSn is null on updateOrderBaseByOrderSn");
        }
        return mybatisDaoContext.update(SQLID + "updateOrderBaseByOrderSn", orderEntity);
    }
 
    
    



}
