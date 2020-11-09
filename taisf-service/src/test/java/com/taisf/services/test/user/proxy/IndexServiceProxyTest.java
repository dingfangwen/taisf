package com.taisf.services.test.user.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.common.valenum.OrderTypeEnum;
import com.taisf.services.enterprise.entity.EnterpriseAddressEntity;
import com.taisf.services.test.common.BaseTest;
import com.taisf.services.user.api.IndexService;
import com.taisf.services.user.vo.IndexBaseVO;
import com.taisf.services.user.vo.IndexVO;
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
 * @author afi on on 2017/10/11.
 * @version 1.0
 * @since 1.0
 */
public class IndexServiceProxyTest extends BaseTest {

    @Resource(name = "user.indexServiceProxy")
    private IndexService indexService;


    @Test
    public void getUserAddressListTest() {
        DataTransferObject<List<EnterpriseAddressEntity>> dto = indexService.getUserAddressList("afi");
        System.out.println(JsonEntityTransform.Object2Json(dto));
    }


    @Test
    public void getUserBaseInfoTest() {
        DataTransferObject<IndexBaseVO> dto = indexService.getUserBaseInfo("afi");
        System.out.println(JsonEntityTransform.Object2Json(dto));
    }


    @Test
    public void getIndexTest() {

        DataTransferObject<IndexVO> dto = indexService.getIndex("2c91340c613249c401613b1217640016");

        System.out.println(JsonEntityTransform.Object2Json(dto));
    }


    @Test
    public void getOrderTypeTest() {

        DataTransferObject<OrderTypeEnum> dto = indexService.getOrderType("0001");

        System.out.println(JsonEntityTransform.Object2Json(dto));
    }

}
