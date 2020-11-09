package com.taisf.services.test.pay.dao;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.pay.dao.RechargeOrderDao;
import com.taisf.services.pay.dto.RechargeOrderListRequest;
import com.taisf.services.pay.entity.RechargeOrderEntity;
import com.taisf.services.pay.vo.RechargeOrderVO;
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
 * @author afi on on 2018/4/16.
 * @version 1.0
 * @since 1.0
 */
public class RechargeOrderDaoTest extends BaseTest {


    @Resource(name = "pay.rechargeOrderDao")
    private RechargeOrderDao rechargeOrderDao;




    @Test
    public void findRechargeOrderByPageTest() {
        RechargeOrderListRequest rechargeOrderListRequest =new RechargeOrderListRequest();
        PagingResult<RechargeOrderVO> page = rechargeOrderDao.findRechargeOrderByPage(rechargeOrderListRequest);
        System.out.println(JsonEntityTransform.Object2Json(page));
    }



    @Test
    public void getRechargeOrderByOrderSnTest() {
        RechargeOrderEntity orderByOrderSn = rechargeOrderDao.getRechargeOrderByOrderSn("111");
        System.out.println(JsonEntityTransform.Object2Json(orderByOrderSn));
    }


    @Test
    public void saveRechargeOrderTest() {
        RechargeOrderEntity  rechargeOrderEntity = new RechargeOrderEntity();
        rechargeOrderEntity.setNeedMoney(1);
        rechargeOrderEntity.setUserUid("1231");
        rechargeOrderEntity.setOrderSn("sn111");
        rechargeOrderEntity.setSupplierCode("supplierCode");
        rechargeOrderEntity.setEnterpriseCode("enterpriseCode");
        int orderByOrderSn = rechargeOrderDao.saveRechargeOrder(rechargeOrderEntity);
        System.out.println(JsonEntityTransform.Object2Json(orderByOrderSn));
    }
}
