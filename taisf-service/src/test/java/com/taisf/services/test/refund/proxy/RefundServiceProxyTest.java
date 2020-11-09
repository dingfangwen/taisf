package com.taisf.services.test.refund.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.refund.entity.RefundEntity;
import com.taisf.services.refund.proxy.RefundServiceProxy;
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
 * @author afi on on 2018/1/27.
 * @version 1.0
 * @since 1.0
 */
public class RefundServiceProxyTest extends BaseTest {


    @Resource(name = "refund.refundServiceProxy")
    private RefundServiceProxy refundServiceProxy;



    @Test
    public void cartInfoTest() {


        RefundEntity refundEntity = new RefundEntity();

        refundEntity.setId(28);
        refundEntity.setRefundStatus(3);
        refundEntity.setRefundFee(100);

        DataTransferObject<Void> classify = refundServiceProxy.updateRefund(refundEntity);
        System.out.println(JsonEntityTransform.Object2Json(classify));

    }


}
