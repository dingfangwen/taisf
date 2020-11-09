package com.taisf.services.test.ups;

import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.test.base.BaseTest;
import com.taisf.services.ups.dao.EmployeeDao;
import com.taisf.services.ups.entity.EmployeeEntity;
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
 * @author afi on on 2018/1/12.
 * @version 1.0
 * @since 1.0
 */
public class EmployeeDaoTest extends BaseTest {



    @Resource(name = "ups.employeeDao")
    private EmployeeDao employeeDao;


    @Test
    public void findEmployeeByUidTest(){

        EmployeeEntity byUid = employeeDao.findEmployeeByUid("132");

        System.out.println(JsonEntityTransform.Object2Json(byUid));
    }
}
