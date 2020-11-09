package com.taisf.services.test.pay.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.DateUtil;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.ValueUtil;
import com.taisf.services.enterprise.vo.SupRechargeStatsVO;
import com.taisf.services.order.dto.SupStatsRequest;
import com.taisf.services.pay.dto.RechargeOrderRequest;
import com.taisf.services.pay.entity.RechargeOrderEntity;
import com.taisf.services.pay.proxy.RechargeOrderProxy;
import com.taisf.services.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Map;

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
public class RechargeOrderProxyTest extends BaseTest {


    @Resource(name = "pay.rechargeOrderProxy")
    private RechargeOrderProxy rechargeOrderProxy;





    @Test
    public void getSelfRechargeSupStatsMapTest() {
        SupStatsRequest request = new SupStatsRequest();
        Map<String,SupRechargeStatsVO> dto = rechargeOrderProxy.getSelfRechargeSupStatsMap(request);
        System.out.println(JsonEntityTransform.Object2Json(dto));
    }




    @Test
    public void getRechargeOrderByOrderSnTest() {
        DataTransferObject<RechargeOrderEntity> dto = rechargeOrderProxy.getRechargeOrderByOrderSn("CZ180421MV0I5HMB155531");
        System.out.println(JsonEntityTransform.Object2Json(dto));
    }



    @Test
    public void createRechargeOrderTest() {
        RechargeOrderRequest payRecordRequest = new RechargeOrderRequest();
        payRecordRequest.setUserUid("baozi");
        payRecordRequest.setNeedMoney(100);
        DataTransferObject<String> dto = rechargeOrderProxy.createRechargeOrder(payRecordRequest);
        System.out.println(JsonEntityTransform.Object2Json(dto));
    }



    @Test
    public void dealRechargeOrder4PayReturnTest() {
        try{

            String aa = "{\"id\":null,\"orderSn\":\"CZ180522O8TB43WY150641\",\"payType\":1,\"totalFee\":1,\"needMoney\":null,\"tradeNo\":\"4200000111201805226810873462\",\"payTime\":1526972809987,\"accountsStatus\":null,\"payStatus\":null,\"enterpriseCode\":null,\"supplierCode\":null,\"userUid\":null,\"createTime\":null,\"lastModifyDate\":null}";

            RechargeOrderEntity recordRequest = JsonEntityTransform.json2Object(aa,RechargeOrderEntity.class);

            recordRequest.setOrderSn("CZ180509A3Y7869C143248");
//            recordRequest.setUserUid("baozi");
//            recordRequest.setTotalFee(100);
//            recordRequest.setPayType(1);
//            recordRequest.setPayTime(DateUtil.getTime(ValueUtil.getintValue(System.currentTimeMillis())));
//            recordRequest.setTradeNo("1111");


            DataTransferObject<Void> dto = rechargeOrderProxy.dealRechargeOrder4PayReturn(recordRequest);
            System.out.println(JsonEntityTransform.Object2Json(dto));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
