package com.taisf.services.test.order.dao;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.enterprise.dto.EnterpriseListRequest;
import com.taisf.services.order.dao.OrderInfoDao;
import com.taisf.services.order.dto.EnterpriseStatsRequest;
import com.taisf.services.order.dto.OrderInfoRequest;
import com.taisf.services.enterprise.vo.EnterpriseOrderStatsVO;
import com.taisf.services.order.vo.OrderInfoVO;
import com.taisf.services.order.vo.OrderSendStatsVo;
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
 * @author afi on on 2017/9/30.
 * @version 1.0
 * @since 1.0
 */
public class OrderInfoDaoTest extends BaseTest {


    @Resource(name = "order.orderInfoDao")
    private OrderInfoDao orderInfoDao;



    @Test
    public void finOrderDistributionListTest() {
        EnterpriseListRequest orderInfoRequest = new EnterpriseListRequest();
//        orderInfoRequest.setUserUid("ff8080815e848f1d015e848f1de40000");

        PagingResult<OrderSendStatsVo> orderEntity = orderInfoDao.finOrderDistributionList(orderInfoRequest);
        System.out.println(JsonEntityTransform.Object2Json(orderEntity));
    }

    @Test
    public void getEnterpriseOrderStatsTest() {
        EnterpriseStatsRequest orderInfoRequest = new EnterpriseStatsRequest();
//        orderInfoRequest.setUserUid("ff8080815e848f1d015e848f1de40000");

        List<EnterpriseOrderStatsVO> orderEntity = orderInfoDao.getEnterpriseOrderStats(orderInfoRequest);
        System.out.println(JsonEntityTransform.Object2Json(orderEntity));
    }


    @Test
    public void getOrderInfoPageTest() {
        OrderInfoRequest orderInfoRequest = new OrderInfoRequest();
        orderInfoRequest.setUserUid("ff8080815e848f1d015e848f1de40000");

        PagingResult<OrderInfoVO> orderEntity = orderInfoDao.getOrderInfoPage(orderInfoRequest);
        System.out.println(JsonEntityTransform.Object2Json(orderEntity));
    }

}
