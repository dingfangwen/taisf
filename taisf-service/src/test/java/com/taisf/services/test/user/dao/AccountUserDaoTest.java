package com.taisf.services.test.user.dao;

import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.enterprise.vo.EnterpriseAccountVO;
import com.taisf.services.test.common.BaseTest;
import com.taisf.services.user.dao.UserAccountDao;
import com.taisf.services.user.entity.UserAccountEntity;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
public class AccountUserDaoTest extends BaseTest {

    @Resource(name = "user.accountUserDao")
    private UserAccountDao accountUserDao;


    @Test
    public void getEnterpriseAccountByListTest() {
        List<String> userIdList = new ArrayList<>();
        userIdList.add("code");
        List<EnterpriseAccountVO>  aa = accountUserDao.getEnterpriseAccountByList(userIdList);
        System.out.println(JsonEntityTransform.Object2Json(aa));
    }

    @Test
    public void getUserAccountByListTest() {
        List<String> userIdList = new ArrayList<>();
        userIdList.add("afi");
        List<UserAccountEntity>  aa = accountUserDao.getUserAccountByList(userIdList);
        System.out.println(JsonEntityTransform.Object2Json(aa));
    }


    @Test
    public void frozenUserBalanceTest() {
        int aa = accountUserDao.frozenUserBalance("did",199);
        System.out.println(JsonEntityTransform.Object2Json(aa));
    }


    @Test
    public void getAccountUserByUidTest() {
        UserAccountEntity aa = accountUserDao.getAccountUserByUid("did");
        System.out.println(JsonEntityTransform.Object2Json(aa));
    }

    @Test
    public void saveDoctorPatientTest() {
        UserAccountEntity entity = new UserAccountEntity();
        entity.setUserId("did");
        int aa = accountUserDao.saveAccountUser(entity);
        System.out.println(JsonEntityTransform.Object2Json(aa));
    }
}
