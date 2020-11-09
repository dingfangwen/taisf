package com.taisf.services.test.enterprise.manager;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.enterprise.api.EnterpriseService;
import com.taisf.services.enterprise.dto.EnterpriseDayRequest;
import com.taisf.services.enterprise.manager.EnterpriseManagerImpl;
import com.taisf.services.test.common.BaseTest;
import com.taisf.services.user.vo.DayVO;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;
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
 * @author afi on on 2018/4/20.
 * @version 1.0
 * @since 1.0
 */
public class EnterpriseManagerImplTest extends BaseTest{


    @Resource(name = "enterprise.enterpriseManagerImpl")
    private EnterpriseManagerImpl enterpriseManager;

    @Test
    public void getCurrentWeekTest() {
        List<DayVO> getCurrentWeek  = enterpriseManager.getCurrentWeek("0001");
        System.out.println(JsonEntityTransform.Object2Json(getCurrentWeek));
    }

}
