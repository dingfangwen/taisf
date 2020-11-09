package com.taisf.services.test.order.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.common.valenum.OrderTypeEnum;
import com.taisf.services.order.api.OrderService;
import com.taisf.services.order.dto.CreateOrderRequest;
import com.taisf.services.order.dto.OrderInfoRequest;
import com.taisf.services.order.dto.RefundOrderRequest;
import com.taisf.services.order.vo.*;
import com.taisf.services.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

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
public class OrderServiceProxyTest extends BaseTest {


    @Resource(name = "order.orderServiceProxy")
    private OrderService orderService;



    @Test
    public void getBuffetByOrderSnTest() {
        DataTransferObject<BuffetOrderVO> classify = orderService.getBuffetByOrderSn("TA181214Z3851QZQ105052","afi");
        System.out.println(JsonEntityTransform.Object2Json(classify));
    }





    @Test
    public void getOrderInfoWaitingListTest() {

        DataTransferObject<List<OrderInfoVO>> classify = orderService.getOrderInfoWaitingList("11");
        System.out.println(JsonEntityTransform.Object2Json(classify));

    }



    @Test
    public void faceOrderTest() {

//        par:{"businessUid":"jipin","userUid":"2c91340c6135fb79016135fb79e60000","addressFid":null,"pwd":"","orderType":null,"source":1,"enterpriseCode":"qpg001","price":100,"payCode":""},needPwd:true

        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setUserUid("baozi");
//        createOrderRequest.setBusinessUid("jipin");
        createOrderRequest.setPayCode("7777");
        createOrderRequest.setPwd("96e79218965eb72c92a549dd5a330112");
        createOrderRequest.setSource(3);
        createOrderRequest.setPrice(100);
        DataTransferObject<FaceVO> classify = orderService.faceOrder(createOrderRequest,true);
        System.out.println(JsonEntityTransform.Object2Json(classify));
    }



    @Test
    public void initOrderTest() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setUserUid("baozi");
        createOrderRequest.setBusinessUid("jipin");
        createOrderRequest.setOrderType(OrderTypeEnum.DINNER_COMMON.getCode());
//        createOrderRequest.setPwd("96e79218965eb72c92a549dd5a330112");
        createOrderRequest.setSource(1);
        createOrderRequest.setEnterpriseCode("0001");
        DataTransferObject<OrderSaveInfo> classify = orderService.initOrder(createOrderRequest);
        System.out.println(JsonEntityTransform.Object2Json(classify));


    }


    @Test
    public void createOrderTest() {

        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setUserUid("baozi");
        createOrderRequest.setBusinessUid("jipin");
        createOrderRequest.setOrderType(OrderTypeEnum.DINNER_COMMON.getCode());
        createOrderRequest.setPwd("96e79218965eb72c92a549dd5a330112");
        createOrderRequest.setSource(1);
        createOrderRequest.setAddressFid("2c91cb36616a0f9001616a0f90290000");
        createOrderRequest.setEnterpriseCode("0001");
        DataTransferObject<CreateOrderVO> classify = orderService.createOrder(createOrderRequest);
        System.out.println(JsonEntityTransform.Object2Json(classify));

    }



    @Test
    public void initExtOrderTest() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setUserUid("afi");
        createOrderRequest.setBusinessUid("123");
        createOrderRequest.setOrderType(OrderTypeEnum.DINNER_EXT.getCode());
        createOrderRequest.setPwd("123");
        createOrderRequest.setSource(1);
        createOrderRequest.setAddressFid("111");
        DataTransferObject<OrderSaveInfo> classify = orderService.initExtOrder(createOrderRequest);
        System.out.println(JsonEntityTransform.Object2Json(classify));

    }


    @Test
    public void createExtOrderTest() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setUserUid("afi");
        createOrderRequest.setBusinessUid("123");
        createOrderRequest.setOrderType(OrderTypeEnum.DINNER_EXT.getCode());
        createOrderRequest.setPwd("123");
        createOrderRequest.setSource(1);
        createOrderRequest.setAddressFid("111");
        DataTransferObject<String> classify = orderService.createExtOrder(createOrderRequest);
        System.out.println(JsonEntityTransform.Object2Json(classify));
    }



    @Test
    public void getOrderInfoPageTest() {
        OrderInfoRequest createOrderRequest = new OrderInfoRequest();
        createOrderRequest.setUserUid("2c91cb36661a48aa01661a4f58510004");
//        createOrderRequest.setKnightType(0);
//        createOrderRequest.setBalanceFlag(true);
        DataTransferObject<PagingResult<OrderInfoVO>> classify = orderService.getOrderInfoPage(createOrderRequest);
        System.out.println(JsonEntityTransform.Object2Json(classify));
    }




    @Test
    public void getOrderDetailBySnTest() {
        DataTransferObject<OrderDetailVO> classify = orderService.getOrderDetailBySn("171009OKT029M9215107");
        System.out.println(JsonEntityTransform.Object2Json(classify));
    }



    @Test
    public void refundOrderTest() {
        RefundOrderRequest refundOrderRequest = new RefundOrderRequest();
        refundOrderRequest.setOpId("2c91cb3667aa98e5016a53203db10005");
        refundOrderRequest.setOrderSn("TA190910DCKAF9OG193315");
        DataTransferObject<Void> classify = orderService.refundOrder(refundOrderRequest);
        System.out.println(JsonEntityTransform.Object2Json(classify));
    }

    @Test
    public void getOrderListPageByEnterprisCodeTest() {
        OrderInfoRequest orderInfoRequest = new OrderInfoRequest();
        orderInfoRequest.setEnterpriseCode("0001");
        //orderInfoRequest.setUserName("周明敬");
        //orderInfoRequest.setUserTel("18610407470");
        orderInfoRequest.setOrderStatus(1);
        DataTransferObject<PagingResult<OrderInfoVO>> dto = orderService.getOrderListPageByEnterprisCode(orderInfoRequest);
        System.out.println(JsonEntityTransform.Object2Json(dto));
    }






}
