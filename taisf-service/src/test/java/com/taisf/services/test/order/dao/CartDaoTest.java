package com.taisf.services.test.order.dao;

import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.order.dao.CartDao;
import com.taisf.services.order.entity.CartEntity;
import com.taisf.services.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
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
public class CartDaoTest extends BaseTest {


    @Resource(name = "order.cartDao")
    private CartDao cartDao;


    @Test
    public void saveCartTest() {
        CartEntity productEntity = new CartEntity();
        productEntity.setBusinessUid("b");
        productEntity.setUserUid("u");
        productEntity.setProductCode(1);
        productEntity.setSupplierProductType(1);
        productEntity.setProductNum(1);
        cartDao.saveCart(productEntity);
    }


    @Test
    public void updateCartTest() {
        CartEntity productEntity = new CartEntity();
        productEntity.setId(1);
        productEntity.setBusinessUid("b");
        productEntity.setUserUid("u");
        productEntity.setProductCode(1);
        productEntity.setSupplierProductType(1);
        productEntity.setProductNum(1);
        cartDao.updateCart(productEntity);
    }


    @Test
    public void getCartByUserIdTest() {

        List<CartEntity> list = cartDao.getCartByUserId("u","b");

        System.out.println(JsonEntityTransform.Object2Json(list));
    }


}
