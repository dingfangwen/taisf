package com.taisf.services.test.user.dao;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.test.common.BaseTest;
import com.taisf.services.user.dao.UserDao;
import com.taisf.services.user.dto.UserAccountRequest;
import com.taisf.services.user.entity.UserEntity;
import com.taisf.services.user.vo.UserAccountVO;
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
public class UserDaoTest extends BaseTest {



    @Resource(name = "user.userDao")
    private UserDao userDao;




	@Test
	public void updateIsPwdTest() {

		int  aa=  userDao.updateIsPwd("2c91340c613249c401613b1217640016",1);
		System.out.println(JsonEntityTransform.Object2Json(aa));
	}



	@Test
	public void updateUserPwdTest() {

		int  aa=  userDao.updateUserPwd("2c91340c613249c401613b1217640016","e10adc3949ba59abbe56e057f20f883e");
		System.out.println(JsonEntityTransform.Object2Json(aa));
	}


	@Test
	public void getUserAccountPageTest() {
		UserAccountRequest request = new UserAccountRequest();


		PagingResult<UserAccountVO> aa=  userDao.getUserAccountPage(request);
		System.out.println(JsonEntityTransform.Object2Json(aa));
	}



	@Test
	public void getUserByUidTest() {

		UserEntity aa=  userDao.getUserByUid("111");
		System.out.println(JsonEntityTransform.Object2Json(aa));
	}



    @Test
    public void testAddEntity() {
    	UserEntity userEntity = new UserEntity();
    	userEntity.setUserPhone("23164324796");
    	userEntity.setUserPassword("fdasfdsafdas");
    	userEntity.setUserStatus(1);
    	userEntity.setUserType(1);
    	userEntity.setUserBusinessStatus(1);
    	userEntity.setCreateTime(new Date());
    	userEntity.setUpdateTime(new Date());
    	int aa=  userDao.add(userEntity);
        System.out.println(aa);
    }
    
    @Test
    public void testQuerySelective() {
    	UserEntity userEntity = new UserEntity();
    	userEntity.setUserPhone("13164324796");
    	userEntity.setUserPassword("fdasfdsafdas1111");
    	UserEntity aa=  userDao.queryBySelective(userEntity);
        System.out.println(aa);
    }
    
    @Test
    public void testQueryCountSelective() {
    	UserEntity userEntity = new UserEntity();
    	userEntity.setUserPhone("13164324796");
    	userEntity.setUserType(1);
    	Integer result=  userDao.queryCountBySelective(userEntity);
        System.out.println(result);
    }
    

}
