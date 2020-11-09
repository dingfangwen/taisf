package com.taisf.services.test.order.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.common.valenum.OrderTypeEnum;
import com.taisf.services.order.api.OrderOpService;
import com.taisf.services.order.api.OrderService;
import com.taisf.services.order.dto.*;
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
public class OrderOpServiceProxyTest extends BaseTest {


    @Resource(name = "order.orderOpServiceProxy")
    private OrderOpService orderService;


    @Test
    public void finishOrderTest() {
        FinishOrderRequest request = new FinishOrderRequest();
        request.setOpId("afi");
        request.setOrderSn("171016EWA23FJF114304");
        DataTransferObject<Void> classify = orderService.finishOrder(request);
        System.out.println(JsonEntityTransform.Object2Json(classify));

    }


    @Test
    public void buffetInOrderTest() {
        BuffetInOrderRequest request = new BuffetInOrderRequest();
        request.setOpId("afi");
        request.setOrderSn("TA200425D0243V27170419");
        request.setBuffetFid("CD-200131-6666");
        DataTransferObject<Void> classify = orderService.buffetInOrder(request);
        System.out.println(JsonEntityTransform.Object2Json(classify));

    }

    @Test
    public void buffetOutOrderBySystemCloseDaoTest() {
        BuffetOutOrderRequest request = new BuffetOutOrderRequest();
        request.setOpId("afi");
        request.setOrderSn("171016EWA23FJF114304");
        DataTransferObject<Void> classify = orderService.buffetOutOrderBySystemCloseDao(request,"aaa","","");
        System.out.println(JsonEntityTransform.Object2Json(classify));

    }



    @Test
    public void buffetOutOrder4aaaTest() {
        BuffetOutOrderRequest request = new BuffetOutOrderRequest();
        request.setOpId("2c91cb36661a48aa01661a4f58510004");
        request.setOrderSn("TA200425ND18M02V150744");
        DataTransferObject<Void> classify = orderService.buffetOutOrder(request);
        System.out.println(JsonEntityTransform.Object2Json(classify));

    }



    @Test
    public void buffetOutOrder4bbbTest() {
        BuffetOutOrderRequest request = new BuffetOutOrderRequest();
        request.setOpId("2c91cb36661a48aa01661a4f58510004");
        request.setOrderSn("TA2004219920T97S182341");
        DataTransferObject<Void> classify = orderService.buffetOutOrder(request);
        System.out.println(JsonEntityTransform.Object2Json(classify));

    }





}
