package com.taisf.services.test.supplier.dao;

import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.product.entity.ProductEntity;
import com.taisf.services.supplier.dao.SupplierProductDao;
import com.taisf.services.supplier.dto.SupplierProductRequest;
import com.taisf.services.supplier.entity.SupplierProductEntity;
import com.taisf.services.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
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
 * @author afi on on 2017/10/7.
 * @version 1.0
 * @since 1.0
 */
public class SupplierProductDaoTest extends BaseTest {


    @Resource(name = "supplier.supplierProductDao")
    private SupplierProductDao supplierProductDao;





    @Test
    public void getProductListBySupplierAndTypeTest() {
        SupplierProductRequest entity = new SupplierProductRequest();
        entity.setSupplierCode("jipin");

        List<Integer> list = new ArrayList<>();
        list.add(86);

        entity.setProductIds(list);
        List<ProductEntity> out = supplierProductDao.getProductListBySupplierAndType(entity);


        System.out.println(JsonEntityTransform.Object2Json(out));
    }


    @Test
    public void saveSupplierProductTest() {
        SupplierProductEntity entity = new SupplierProductEntity();
        entity.setSupplierCode("code");
        entity.setProductType(1);
        entity.setProductCode(1);
        entity.setProductPrice(1);
        supplierProductDao.saveSupplierProduct(entity);
    }
}
