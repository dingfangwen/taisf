package com.taisf.services.test.order.dao;

import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.order.dao.OrderProductDao;
import com.taisf.services.order.entity.OrderProductEntity;
import com.taisf.services.product.dao.ProductDao;
import com.taisf.services.product.entity.ProductEntity;
import com.taisf.services.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>商品的测试</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2016/3/25.
 * @version 1.0
 * @since 1.0
 */
public class OrderProductDaoTest extends BaseTest {


    @Resource(name = "order.orderProductDao")
    private OrderProductDao orderProductDao;

    @Test
    public void saveOrderProductTest() {
        OrderProductEntity productEntity = new OrderProductEntity();
        productEntity.setProductCode(1);
        productEntity.setProductName("name");
        productEntity.setProductNum(1);
        productEntity.setProductPrice(1);
        productEntity.setProductType(1);
        productEntity.setOrderSn("orderSn");
        orderProductDao.saveOrderProduct(productEntity);
    }



    @Test
    public void getOrderProductByOrderSnListTest() {
        List<String> par = new ArrayList<>();
        par.add("orderSn");

        List<OrderProductEntity> list = orderProductDao.getOrderProductByOrderSnList(par);
        System.out.println(JsonEntityTransform.Object2Json(list));
    }


    @Test
    public void getOrderProductByOrderSnTest() {
        List<OrderProductEntity> list = orderProductDao.getOrderProductByOrderSn("orderSn");
        System.out.println(JsonEntityTransform.Object2Json(list));
    }

}
