package com.taisf.services.test.enterprise.dao;

import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.enterprise.dao.EnterpriseDao;
import com.taisf.services.enterprise.dao.EnterpriseDayDao;
import com.taisf.services.enterprise.entity.EnterpriseDayEntity;
import com.taisf.services.enterprise.entity.EnterpriseEntity;
import com.taisf.services.test.common.BaseTest;
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
 * @author afi on on 2017/9/20.
 * @version 1.0
 * @since 1.0
 */
public class EnterpriseDayDaoTest  extends BaseTest {


    @Resource(name = "enterprise.enterpriseDayDao")
    private EnterpriseDayDao enterpriseDayDao;


    @Test
    public void saveEnterpriseDayTest() {
        EnterpriseDayEntity entity = new EnterpriseDayEntity();
        entity.setEnterpriseCode("code");
        entity.setDayTime("time");
        entity.setDayType(1);
        entity.setOpCode("opcode");
        entity.setOpName("opname");
        enterpriseDayDao.saveEnterpriseDay(entity);
    }



    @Test
    public void updateEnterpriseDayTest() {
        EnterpriseDayEntity entity = new EnterpriseDayEntity();
        entity.setEnterpriseCode("code");
        entity.setDayTime("time");
        entity.setDayType(1);
        entity.setOpCode("opcode");
        entity.setOpName("opname");
        enterpriseDayDao.updateEnterpriseDay(entity);
    }



    @Test
    public void getEnterpriseDaysByCodeTest() {
        List<EnterpriseDayEntity> rst = enterpriseDayDao.getEnterpriseDaysByCode("code");
        System.out.println(JsonEntityTransform.Object2Json(rst));
    }

}
