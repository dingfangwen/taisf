package com.taisf.services.test.enterprise.dao;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.enterprise.dao.EnterpriseDao;
import com.taisf.services.enterprise.dto.EnterprisePageRequest;
import com.taisf.services.enterprise.entity.EnterpriseEntity;
import com.taisf.services.test.common.BaseTest;
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
 * @author afi on on 2017/9/20.
 * @version 1.0
 * @since 1.0
 */
public class EnterpriseDaoTest  extends BaseTest {


    @Resource(name = "enterprise.enterpriseDao")
    private EnterpriseDao enterpriseDao;




    @Test
    public void getEnterpriseByPageTest() {
        EnterprisePageRequest request = new EnterprisePageRequest();
        request.setEnterpriseCode("code");
        PagingResult<EnterpriseEntity> rst = enterpriseDao.getEnterpriseByPage(request);
        System.out.println(JsonEntityTransform.Object2Json(rst));
    }



    @Test
    public void saveEnterpriseTest() {
        EnterpriseEntity entity = new EnterpriseEntity();
        entity.setEnterpriseCode("code");
        entity.setAreaCode("mail");
        entity.setAreaName("mobile");
        entity.setCityCode("name");
        entity.setCityName("cityName");
        entity.setEnterpriseName("nanem");
        entity.setEnterpriseTel("tel");
        entity.setEnterpriseType(1);
        entity.setManger("111");
        entity.setOpenTime(new Date());
        entity.setProvinceCode("pcode");
        entity.setProvinceName("pname");
        entity.setStreet("str");
        enterpriseDao.saveEnterprise(entity);
    }



    @Test
    public void updateEnterpriseTest() {
        EnterpriseEntity entity = new EnterpriseEntity();
        entity.setId(1);
        entity.setEnterpriseCode("code");
        entity.setAreaCode("mail");
        entity.setAreaName("mobile");
        entity.setCityCode("name");
        entity.setCityName("cityName");
        entity.setEnterpriseName("nanem");
        entity.setEnterpriseTel("tel");
        entity.setEnterpriseType(1);
        entity.setManger("111");
        entity.setOpenTime(new Date());
        entity.setProvinceCode("pcode");
        entity.setProvinceName("pname");
        entity.setStreet("str");
        enterpriseDao.updateEnterprise(entity);
    }



    @Test
    public void getEnterpriseByCodeTest() {
        EnterpriseEntity rst = enterpriseDao.getEnterpriseByCode("code");
        System.out.println(JsonEntityTransform.Object2Json(rst));
    }


}
