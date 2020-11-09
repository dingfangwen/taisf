package com.jk.services.payment.test.http;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.services.payment.entity.PayInfo;
import com.jk.services.payment.service.PaymentService;
import com.jk.services.payment.test.base.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/26.
 * @version 1.0
 * @since 1.0
 */
public class AliRefundHandleTest  extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AliRefundHandleTest.class);




    @Autowired
    protected PaymentService paymentService;




    @Test
    public void refundTest() throws Exception{

        PayInfo payInfo = new PayInfo();
        payInfo.setOrgBizId("2605");
        payInfo.setBizId("123");
        payInfo.setOrgInSerialNo("2017102621001004640234843164");
        payInfo.setReturnUrl("http://bjtest.keymop.com/pay/refund/notice");
        payInfo.setNotifyUrl("http://bjtest.keymop.com/pay/refund/notice");
        payInfo.setAmount(200);
        DataTransferObject dto = new DataTransferObject();
        paymentService.dealRefundParameter(payInfo,dto);
//        if (dto.checkSuccess()){
//            String rst =   aliRefundHandle.doRefundBusiness(payInfo);
//            aliRefundHandle.notifyHandle(payInfo,rst);
//        }


        System.out.println(111);
        Thread.sleep(300000);
        System.out.println(111);

    }







}
