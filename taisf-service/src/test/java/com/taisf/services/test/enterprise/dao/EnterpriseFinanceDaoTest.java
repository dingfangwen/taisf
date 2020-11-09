package com.taisf.services.test.enterprise.dao;

import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.enterprise.dao.EnterpriseDayDao;
import com.taisf.services.enterprise.dao.EnterpriseFinanceDao;
import com.taisf.services.enterprise.entity.EnterpriseDayEntity;
import com.taisf.services.enterprise.entity.EnterpriseFinanceEntity;
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
public class EnterpriseFinanceDaoTest  extends BaseTest {


    @Resource(name = "enterprise.enterpriseFinanceDao")
    private EnterpriseFinanceDao enterpriseFinanceDao;


    @Test
    public void saveEnterpriseFinanceTest() {
        EnterpriseFinanceEntity entity = new EnterpriseFinanceEntity();
        entity.setEnterpriseCode("code");
        entity.setCheckType(1);
        entity.setEnterpriseAccount("11");
        entity.setEnterpriseTax("tax");
        entity.setFinanceName("fpname");
        entity.setFeeDay(1);
        entity.setInvoiceTitle("title");
        enterpriseFinanceDao.saveEnterpriseFinance(entity);
    }



    @Test
    public void updateEnterpriseFinanceTest() {
        EnterpriseFinanceEntity entity = new EnterpriseFinanceEntity();
        entity.setEnterpriseCode("code");
        entity.setCheckType(1);
        entity.setEnterpriseAccount("11");
        entity.setEnterpriseTax("tax");
        entity.setFinanceName("fpname");
        entity.setFeeDay(1);
        entity.setInvoiceTitle("title");
        enterpriseFinanceDao.updateEnterpriseFinance(entity);
    }

    @Test
    public void getEnterpriseFinanceByCodeTest() {
        EnterpriseFinanceEntity rst = enterpriseFinanceDao.getEnterpriseFinanceByCode("code");
        System.out.println(JsonEntityTransform.Object2Json(rst));
    }

}
