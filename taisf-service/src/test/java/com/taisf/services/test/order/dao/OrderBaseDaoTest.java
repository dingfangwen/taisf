package com.taisf.services.test.order.dao;


import com.jk.framework.base.utils.UUIDGenerator;
import com.taisf.services.order.dao.OrderBaseDao;
import com.taisf.services.order.entity.OrderEntity;
import com.taisf.services.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.*;


/**
 * <p>订单类测测试</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2016/3/31.
 * @version 1.0
 * @since 1.0
 */
public class OrderBaseDaoTest extends BaseTest {


    @Resource(name = "order.orderBaseDao")
    private OrderBaseDao orderBaseDao;




    @Test
    public void getOrderBaseByOrderSnTest() {
        OrderEntity orderEntity = orderBaseDao.getOrderBaseByOrderSn("orderSn");
    }


    @Test
    public void saveOrderBaseTest(){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderSn("orderSn");
        orderEntity.setProvinceCode("BJ");
        orderEntity.setCityCode("BJ");
        orderEntity.setAreaCode("东城区");
        orderEntity.setOrderSource(1);
        orderEntity.setOrderStatus(20);
        orderEntity.setAccountsStatus(0);
        orderEntity.setPayStatus(0);
        orderEntity.setBusinessUid("bid");
        orderEntity.setUserUid(UUIDGenerator.hexUUID());
        orderEntity.setUserTel("18301315875");
        orderEntity.setUserName("小草");

        orderEntity.setPayStatus(0);
        orderEntity.setOrderStatus(20);
        orderEntity.setMark("tripPurposetripPurposetripPurposetripPurposetripPurposetripPurposet");

        orderEntity.setPayTime(new Date());
        orderEntity.setCreateTime(new Date());
        orderEntity.setSendTime(new Date());
        orderBaseDao.saveOrderBase(orderEntity);
    }



    @Test
    public void updateOrderBaseByOrderSnTest(){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderSn("orderSn");
        orderEntity.setProvinceCode("BJ");
        orderEntity.setCityCode("BJ");
        orderEntity.setAreaCode("东城区");
        orderEntity.setOrderSource(1);
        orderEntity.setOrderStatus(20);
        orderEntity.setAccountsStatus(0);
        orderEntity.setPayStatus(0);
        orderEntity.setBusinessUid("bid");
        orderEntity.setUserUid(UUIDGenerator.hexUUID());
        orderEntity.setUserTel("18301315875");
        orderEntity.setUserName("小草");

        orderEntity.setPayStatus(0);
        orderEntity.setOrderStatus(20);
        orderEntity.setMark("tripPurposetripPurposetripPurposetripPurposetripPurposetripPurposet");

        orderEntity.setPayTime(new Date());
        orderEntity.setCreateTime(new Date());
        orderEntity.setSendTime(new Date());
        orderBaseDao.updateOrderBaseByOrderSn(orderEntity);
    }

}

