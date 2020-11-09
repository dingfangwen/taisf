package com.taisf.services.test.enterprise.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.enterprise.api.EnterpriseService;
import com.taisf.services.enterprise.dto.EnterpriseDayRequest;
import com.taisf.services.enterprise.dto.EnterprisePageRequest;
import com.taisf.services.enterprise.entity.EnterpriseDayEntity;
import com.taisf.services.enterprise.vo.EnterpriseDispatchVO;
import com.taisf.services.enterprise.vo.EnterpriseListDay;
import com.taisf.services.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>企业相关测试类</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/19.
 * @version 1.0
 * @since 1.0
 */
public class EnterpriseServiceProxyTest extends BaseTest {


    @Resource(name = "enterprise.enterpriseServiceProxy")
    private EnterpriseService enterpriseService;


    @Test
    public void chnageEnterpriseDayTest() {
        EnterpriseDayRequest enterpriseDayRequest = new EnterpriseDayRequest();
        enterpriseDayRequest.setEnterpriseCode("code");
        enterpriseDayRequest.setDayTime(new Date());
        enterpriseDayRequest.setDayType(2);
        DataTransferObject<Void> dto = enterpriseService.changeEnterpriseDay(enterpriseDayRequest);
        System.out.println(JsonEntityTransform.Object2Json(dto));
    }

    @Test
    public void getEnterpriseListDayTest() {
        EnterprisePageRequest request = new EnterprisePageRequest();
        request.setEnterpriseCode("code");
        DataTransferObject<List<EnterpriseListDay>> dto = enterpriseService.getEnterpriseListDay("code");
        System.out.println(JsonEntityTransform.Object2Json(dto));

    }


    @Test
    public void getEnterpriseDispatchByPageTest() {
        EnterprisePageRequest request = new EnterprisePageRequest();
        request.setEnterpriseCode("code");
        DataTransferObject<PagingResult<EnterpriseDispatchVO>> dto = enterpriseService.getEnterpriseDispatchByPage(request);
        System.out.println(JsonEntityTransform.Object2Json(dto));

    }

    @Test
    public void dealEnterpriseDaysTest() {

        DataTransferObject<List<EnterpriseDayEntity>> dto = enterpriseService.dealEnterpriseDays("code");
        System.out.println(JsonEntityTransform.Object2Json(dto));

    }
}
