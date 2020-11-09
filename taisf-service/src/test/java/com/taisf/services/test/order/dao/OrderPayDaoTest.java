package com.taisf.services.test.order.dao;


import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.order.dao.OrderPayDao;
import com.taisf.services.order.entity.OrderPayEntity;
import com.taisf.services.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class OrderPayDaoTest  extends BaseTest {
	
    @Resource(name = "order.payDao")
    private OrderPayDao payDao;
	
    @Test
	public void insertOrderPayResTest(){
		OrderPayEntity ope = new OrderPayEntity();
		ope.setOrderSn("8a9e9cd253d0597c0153d0597c760001");
		ope.setPayType(1);
		ope.setPayMoney(45);
		ope.setPayStatus(1);
		ope.setTradeNo("1231");
		payDao.saveOrderPay(ope);
	}
    

    
    @Test
	public void updateOrderPayTest(){
    	OrderPayEntity ope = new OrderPayEntity();
    	ope.setId(1);
    	ope.setPayMoney(80);
    	payDao.updateOrderPay(ope);
	}
    
    @Test
    public void queryTradeNumByHouseFidTest(){

		OrderPayEntity list = this.payDao.getOrderPayByOrderSn("8a9e9cd253d0597c0153d0597c760001");
		System.err.println(JsonEntityTransform.Object2Json(list));
    }
    
}
