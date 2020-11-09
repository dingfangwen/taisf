package com.taisf.services.test.pay.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.pay.dto.PayRecordRequest;
import com.taisf.services.pay.proxy.PayServiceProxy;
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
 * @author afi on on 2017/12/22.
 * @version 1.0
 * @since 1.0
 */
public class PayServiceProxyTest extends BaseTest {


    @Resource(name = "pay.payServiceProxy")
    private PayServiceProxy payServiceProxy;


    @Test
    public void saveProductTest() {
        PayRecordRequest payRecordRequest = new PayRecordRequest();
        payRecordRequest.setOrderSn("TA17112850L8VPRJ160056");
        payRecordRequest.setPayCode("payCode");
        payRecordRequest.setPaytime(123131231);
        payRecordRequest.setPayType(1);
        payRecordRequest.setTotalFee(11);
        payRecordRequest.setTradeNo("no");
        DataTransferObject<Void> dto = payServiceProxy.dealOrderPay(payRecordRequest);

        System.out.println(JsonEntityTransform.Object2Json(dto));
    }


}
