package com.taisf.services.test.reRecharge.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.enterprise.vo.SupRechargeStatsVO;
import com.taisf.services.order.dto.SupStatsRequest;
import com.taisf.services.recharge.api.RechargeService;
import com.taisf.services.recharge.dto.BalanceMoneyAvgRequest;
import com.taisf.services.recharge.dto.BalanceMoneyOneRequest;
import com.taisf.services.recharge.dto.BalanceMoneyRequest;
import com.taisf.services.recharge.dto.ChargeRequest;
import com.taisf.services.recharge.vo.EnterpriseStatsNumber;
import com.taisf.services.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
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
 * @author afi on on 2017/10/14.
 * @version 1.0
 * @since 1.0
 */
public class RechargeServiceProxyTest extends BaseTest {



    @Resource(name = "recharge.rechargeServiceProxy")
    private RechargeService rechargeService;




    @Test
    public void getSupRechargeStatsMapTest() {
        SupStatsRequest request = new SupStatsRequest();
        Map<String,SupRechargeStatsVO> map = rechargeService.getSupRechargeStatsMap(request);
        System.out.println(JsonEntityTransform.Object2Json(map));

    }





    @Test
    public void balanceMoneyListTest() {
        BalanceMoneyRequest request = new BalanceMoneyRequest();
        List<BalanceMoneyRequest> listBalance = new ArrayList<>();
        request.setUserPhone("18210174986");
        request.setMoneyYuan(33.00);
        listBalance.add(request);
        DataTransferObject<Void> chargeMoney = rechargeService.balanceMoneyList(listBalance,"0001");
        System.out.println(JsonEntityTransform.Object2Json(chargeMoney));

    }


    @Test
    public void balanceMoneyOneTest() {
        BalanceMoneyOneRequest request = new BalanceMoneyOneRequest();
        /*request.setEnterpriseCode("code");
        request.setUserPhone("15120096722");
        request.setMoneyYuan(11.11);*/
        DataTransferObject<Void> chargeMoney = rechargeService.balanceMoneyOne(request);
        System.out.println(JsonEntityTransform.Object2Json(chargeMoney));

    }



    @Test
    public void balanceMoneyAvgTest() {
        BalanceMoneyAvgRequest request = new BalanceMoneyAvgRequest();
        request.setEnterpriseCode("code");
        request.setEmpNum(2);
        request.setEmpMoneyYuan(10.0);

        DataTransferObject<Void> chargeMoney = rechargeService.balanceMoneyAvg(request);
        System.out.println(JsonEntityTransform.Object2Json(chargeMoney));

    }




    @Test
    public void getEnterpriseStatsNumberTest() {
        DataTransferObject<EnterpriseStatsNumber> chargeMoney = rechargeService.getEnterpriseStatsNumber("code");
        System.out.println(JsonEntityTransform.Object2Json(chargeMoney));

    }



    @Test
    public void chargeMoneyTest() {

        ChargeRequest request = new ChargeRequest();
        request.setEnterpriseCode("code");
        request.setMoneyYuan(100.0);
        request.setOpId("id");
        request.setOpName("name");
        DataTransferObject<Void> chargeMoney = rechargeService.chargeMoney(request);
        System.out.println(JsonEntityTransform.Object2Json(chargeMoney));

    }




}
