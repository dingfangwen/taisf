package com.taisf.services.test.order.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.common.valenum.OrderTypeEnum;
import com.taisf.services.common.valenum.SupplierProductTypeEnum;
import com.taisf.services.order.api.CartService;
import com.taisf.services.order.dto.CartAddRequest;
import com.taisf.services.order.dto.CartBaseRequest;
import com.taisf.services.order.dto.CartCleanRequest;
import com.taisf.services.order.vo.CartInfoVO;
import com.taisf.services.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/9.
 * @version 1.0
 * @since 1.0
 */
public class CartServiceProxyTest extends BaseTest {


    @Resource(name = "order.cartServiceProxy")
    private CartService cartService;




    @Test
    public void cartInfoTest() {

        DataTransferObject<CartInfoVO> classify = cartService.cartInfo("2c91340c5ffd23350160019b29cc000f","jipin","0001");
        System.out.println(JsonEntityTransform.Object2Json(classify));

    }


    @Test
    public void delCartTest() {
        CartAddRequest cartAddRequest = new CartAddRequest();
        cartAddRequest.setBusinessUid("123");
        cartAddRequest.setSupplierProductType(SupplierProductTypeEnum.PRODUCT.getCode());
        cartAddRequest.setProductCode(1);
        cartAddRequest.setUserUid("afi");
        DataTransferObject<CartInfoVO> classify = cartService.delCart(cartAddRequest);
        System.out.println(JsonEntityTransform.Object2Json(classify));

    }

    @Test
    public void cartCleanTest() {
        CartCleanRequest cartCleanRequest = new CartCleanRequest();

        DataTransferObject<Void> classify = cartService.cartClean(cartCleanRequest);
        System.out.println(JsonEntityTransform.Object2Json(classify));

    }




    @Test
    public void cartInfoTest1() {

        DataTransferObject<CartInfoVO> classify = cartService.cartInfo("baozi","jipin","0001");
        System.out.println(JsonEntityTransform.Object2Json(classify));

    }



    @Test
    public void addCartPackageTest() {

        CartAddRequest cartAddRequest = new CartAddRequest();

        cartAddRequest.setBusinessUid("jipin");
        cartAddRequest.setSupplierProductType(SupplierProductTypeEnum.PACKAGE.getCode());
        cartAddRequest.setProductCode(280);
        cartAddRequest.setUserUid("baozi");
        cartAddRequest.setEnterpriseCode("0001");
        cartAddRequest.setOrderType(OrderTypeEnum.DINNER_COMMON.getCode());
        DataTransferObject<CartInfoVO> classify = cartService.addCart(cartAddRequest);
        System.out.println(JsonEntityTransform.Object2Json(classify));

    }



    @Test
    public void addCartTest() {

        CartAddRequest cartAddRequest = new CartAddRequest();

        cartAddRequest.setBusinessUid("jipin");
        cartAddRequest.setSupplierProductType(SupplierProductTypeEnum.PRODUCT.getCode());
        cartAddRequest.setProductCode(188);
        cartAddRequest.setUserUid("baozi");
        cartAddRequest.setEnterpriseCode("001");
        cartAddRequest.setOrderType(OrderTypeEnum.DINNER_COMMON.getCode());
        DataTransferObject<CartInfoVO> classify = cartService.addCart(cartAddRequest);
        System.out.println(JsonEntityTransform.Object2Json(classify));

    }


    @Test
    public void addCartTest1() {

        CartAddRequest cartAddRequest = new CartAddRequest();

        cartAddRequest.setBusinessUid("jipin");
        cartAddRequest.setSupplierProductType(SupplierProductTypeEnum.PRODUCT.getCode());
        cartAddRequest.setProductCode(3);
        cartAddRequest.setUserUid("afi");
        cartAddRequest.setEnterpriseCode("qpg001");
        cartAddRequest.setOrderType(OrderTypeEnum.DINNER_COMMON.getCode());
        DataTransferObject<CartInfoVO> classify = cartService.addCart(cartAddRequest);
        System.out.println(JsonEntityTransform.Object2Json(classify));

    }





}
