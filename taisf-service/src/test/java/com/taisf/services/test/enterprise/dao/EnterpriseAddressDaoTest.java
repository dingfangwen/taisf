package com.taisf.services.test.enterprise.dao;

import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.enterprise.dao.EnterpriseAddressDao;
import com.taisf.services.enterprise.entity.EnterpriseAddressEntity;
import com.taisf.services.test.common.BaseTest;
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
 * @author afi on on 2017/9/20.
 * @version 1.0
 * @since 1.0
 */
public class EnterpriseAddressDaoTest  extends BaseTest {


    @Resource(name = "enterprise.enterpriseAddressDao")
    private EnterpriseAddressDao enterpriseAddressDao;


    @Test
    public void saveEnterpriseAddressTest() {
        EnterpriseAddressEntity entity = new EnterpriseAddressEntity();
        entity.setEnterpriseCode("code");
        entity.setAddress("add");
        entity.setConName("name");
        entity.setConTel("tel");
        entity.setSendNum(1);
        enterpriseAddressDao.saveEnterpriseAddress(entity);
    }

    @Test
    public void getEnterpriseAddressByCodeTest() {
        List<EnterpriseAddressEntity> list =
        enterpriseAddressDao.getEnterpriseAddressByCode("code");

        System.out.println(JsonEntityTransform.Object2Json(list));
    }


    @Test
    public void updateEnterpriseAddressTest() {
        EnterpriseAddressEntity entity = new EnterpriseAddressEntity();
        entity.setId(1);
        entity.setEnterpriseCode("code");
        entity.setAddress("add");
        entity.setConName("name");
        entity.setConTel("tel");
        entity.setSendNum(1);
        enterpriseAddressDao.updateEnterpriseAddress(entity);
    }

}
