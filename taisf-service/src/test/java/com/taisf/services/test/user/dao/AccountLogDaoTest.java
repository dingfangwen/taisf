package com.taisf.services.test.user.dao;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.test.common.BaseTest;
import com.taisf.services.user.dao.AccountLogDao;
import com.taisf.services.user.dto.UserMoneyRequest;
import com.taisf.services.user.vo.AccountUserLogVO;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * <p>账户的dao测试</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/3/21.
 * @version 1.0
 * @since 1.0
 */
public class AccountLogDaoTest extends BaseTest {

    @Resource(name = "user.accountLogDao")
    private AccountLogDao accountLogDao;

    @Test
    public void rechargeMoneyLogByPageTest() {
        UserMoneyRequest userMoneyRequest = new UserMoneyRequest();
        userMoneyRequest.setSupplierCode("jipin");
        PagingResult<AccountUserLogVO> aa = accountLogDao.rechargeMoneyLogByPage(userMoneyRequest);
        System.out.println(JsonEntityTransform.Object2Json(aa));
    }
}
