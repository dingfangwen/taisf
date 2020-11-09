package com.taisf.services.test.enterprise.dao;

import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.enterprise.dao.EnterpriseConfigDao;
import com.taisf.services.enterprise.dao.EnterpriseContactsDao;
import com.taisf.services.enterprise.entity.EnterpriseConfigEntity;
import com.taisf.services.enterprise.entity.EnterpriseContactsEntity;
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
public class EnterpriseContactsDaoTest  extends BaseTest {


    @Resource(name = "enterprise.enterpriseContactsDao")
    private EnterpriseContactsDao enterpriseContactsDao;


    @Test
    public void saveEnterpriseContactsTest() {
        EnterpriseContactsEntity entity = new EnterpriseContactsEntity();
        entity.setEnterpriseCode("code");
        entity.setConMail("mail");
        entity.setConMobile("mobile");
        entity.setConName("name");
        entity.setConTel("tel");
        entity.setQqNo("no");

        enterpriseContactsDao.saveEnterpriseContacts(entity);
    }


    @Test
    public void updateEnterpriseContactsTest() {

        EnterpriseContactsEntity entity = new EnterpriseContactsEntity();
        entity.setEnterpriseCode("code");
        entity.setConMail("mail");
        entity.setConMobile("mobile");
        entity.setConName("name");
        entity.setConTel("tel");
        entity.setQqNo("no");
        entity.setId(1);

        enterpriseContactsDao.updateEnterpriseContacts(entity);
    }



    @Test
    public void getEnterpriseContactsByCodeTest() {
        List<EnterpriseContactsEntity> rst = enterpriseContactsDao.getEnterpriseContactsByCode("code");
        System.out.println(JsonEntityTransform.Object2Json(rst));
    }

}
