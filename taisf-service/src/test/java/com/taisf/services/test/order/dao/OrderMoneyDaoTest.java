package com.taisf.services.test.order.dao;

import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.order.dao.OrderMoneyDao;
import com.taisf.services.order.entity.OrderMoneyEntity;
import com.taisf.services.test.common.BaseTest;
import org.junit.Test;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>订单金额测试</p>
 * <p/>
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
public class OrderMoneyDaoTest extends BaseTest {


    @Resource(name = "order.orderMoneyDao")
    private OrderMoneyDao orderMoneyDao;



    @Test
    public void getOrderMoneyByOrderSnTest() {
        OrderMoneyEntity orderMoneyEntity = orderMoneyDao.getOrderMoneyByOrderSn("16042157HI7218182613");
        System.out.println(JsonEntityTransform.Object2Json(orderMoneyEntity));
    }

    @Test
    public void saveOrderMoneyTest() {
        OrderMoneyEntity orderMoneyEntity = new OrderMoneyEntity();
        orderMoneyEntity.setOrderSn("16042157HI7218182613");
        orderMoneyEntity.setCouponMoney(1);
        orderMoneyEntity.setDiscountMoney(1);
        orderMoneyEntity.setRedMoney(1);
        orderMoneyEntity.setNeedPay(1);
        orderMoneyEntity.setPayMoney(1);
        orderMoneyEntity.setPayBalance(1);
        orderMoneyEntity.setSumMoney(111);
        orderMoneyEntity.setCarryMoney(1);
        orderMoneyDao.saveOrderMoney(orderMoneyEntity);
    }

    @Test
    public void updateOrderMoneyTest() {
        OrderMoneyEntity orderMoneyEntity = new OrderMoneyEntity();
        orderMoneyEntity.setOrderSn("16042157HI7218182613");
        orderMoneyEntity.setCouponMoney(3);
        orderMoneyEntity.setDiscountMoney(3);
        orderMoneyEntity.setRedMoney(3);
        orderMoneyEntity.setNeedPay(3);
        orderMoneyEntity.setPayMoney(3);
        orderMoneyEntity.setPayBalance(3);
        orderMoneyEntity.setSumMoney(3);
        orderMoneyEntity.setCarryMoney(3);
        orderMoneyDao.updateOrderMoney(orderMoneyEntity);
    }


}
