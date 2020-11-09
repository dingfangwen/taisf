package com.taisf.services.test.enterprise.dao;

import com.taisf.services.enterprise.dao.EnterpriseFinanceDao;
import com.taisf.services.enterprise.entity.EnterpriseFinanceEntity;
import com.taisf.services.supplier.dao.SupplierPackageDao;
import com.taisf.services.supplier.entity.SupplierPackageEntity;
import com.taisf.services.test.common.BaseTest;
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
 * @author afi on on 2017/10/7.
 * @version 1.0
 * @since 1.0
 */
public class SupplierPackageDaoTest extends BaseTest {


    @Resource(name = "supplier.supplierPackageDao")
    private SupplierPackageDao supplierPackageDao;


    @Test
    public void saveSupplierPackageTest() {
        SupplierPackageEntity entity = new SupplierPackageEntity();
        entity.setSupplierCode("code");
        entity.setPackagePic("pic");
        entity.setTitle("套餐");
        entity.setPackagePrice(1);
        entity.setPackageStatus(1);
        entity.setBigCode(1);
        entity.setSmallCode(2);
        entity.setSuCode(3);
        entity.setTangCode(4);
        entity.setDrinkCode(5);
        entity.setFoodCode(6);
        entity.setFruitCode(7);

//        supplierPackageDao.saveSupplierPackage(entity);
    }

}
