package com.taisf.services.test.order.dao;

import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.order.dao.OrderLogDao;
import com.taisf.services.order.entity.OrderLogEntity;
import com.taisf.services.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>订单log测试</p>
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
public class OrderLogDaoTest extends BaseTest {


    @Resource(name = "order.orderLogDao")
    private OrderLogDao orderLogDao;




    @Test
    public void getOrderLogListByOrderSn() {
        List<OrderLogEntity> list = orderLogDao.getOrderLogListByOrderSn("ssssss");
        System.out.println(JsonEntityTransform.Object2Json(list));
    }



    @Test
    public void TestinsertOrderLogOrderSnIsNull() {
        OrderLogEntity log = new OrderLogEntity();
        log.setFromStatus(1);
        log.setToStatus(2);
        orderLogDao.insertOrderLog(log);
    }


    @Test
    public void TestinsertOrderLog() {
        OrderLogEntity log = new OrderLogEntity();
        log.setOrderSn("ssssss");
        log.setFromStatus(1);
        log.setToStatus(2);
        orderLogDao.insertOrderLog(log);
    }

}
