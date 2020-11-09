package com.taisf.services.test.order.dao;

import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.order.dao.OrderActivityDao;
import com.taisf.services.order.entity.OrderActivityEntity;
import com.taisf.services.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>活动的测试类</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/14.
 * @version 1.0
 * @since 1.0
 */
public class OrderActivityDaoTest extends BaseTest {


    @Resource(name = "order.activityDao")
    private OrderActivityDao activityDao;




    @Test
    public void getActivityByOrderSnTest(){
        List<OrderActivityEntity> ch = activityDao.getActivityByOrderSn("160615M78KJF37211510");
        System.out.println(JsonEntityTransform.Object2Json(ch));
    }

    @Test
    public void saveOrderActivityTest(){
        OrderActivityEntity oae = new OrderActivityEntity();
        oae.setOrderSn("160615M78KJF37211510");
        oae.setAcName("test");
        oae.setAcType(1);
        oae.setAcMoney(30);
        activityDao.saveOrderActivity(oae);
    }

}
