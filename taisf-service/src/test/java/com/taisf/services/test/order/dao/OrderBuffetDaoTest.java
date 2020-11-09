package com.taisf.services.test.order.dao;

import com.jk.framework.base.utils.UUIDGenerator;
import com.taisf.services.order.dao.OrderBaseDao;
import com.taisf.services.order.dao.OrderBuffetDao;
import com.taisf.services.order.entity.OrderBuffetEntity;
import com.taisf.services.order.entity.OrderEntity;
import com.taisf.services.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>TODO</p>
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
public class OrderBuffetDaoTest  extends BaseTest {


    @Resource(name = "order.orderBuffetDao")
    private OrderBuffetDao orderBuffetDao;


    @Test
    public void saveOrderBuffetTest(){
        OrderBuffetEntity entity = new OrderBuffetEntity();
        entity.setOrderSn("orderSn");
        entity.setBuffetFid("111");
        entity.setCellSn("111");

        entity.setCreateTime(new Date());
        orderBuffetDao.saveOrderBuffet(entity);
    }


}
