package com.taisf.services.test.user.dao;


import com.taisf.services.test.common.BaseTest;
import com.taisf.services.user.dao.LoginTokenDao;
import com.taisf.services.user.entity.LoginTokenEntity;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/3/13.
 * @version 1.0
 * @since 1.0
 */
public class LoginTokenDaoTest extends BaseTest {



    @Resource(name = "user.loginTokenDao")
    private LoginTokenDao loginTokenDao;


    @Test
    public void testAddEntity() {
    	LoginTokenEntity loginTokenEntity = new LoginTokenEntity();
    	loginTokenEntity.setUserId("1");
    	loginTokenEntity.setExpireTime(new Date());
    	int aa= loginTokenDao.saveLoginToken(loginTokenEntity);
        System.out.println(aa);
    }

}
