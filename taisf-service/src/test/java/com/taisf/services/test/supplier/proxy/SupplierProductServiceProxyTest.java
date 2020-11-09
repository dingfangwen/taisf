package com.taisf.services.test.supplier.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.enterprise.dao.EnterpriseConfigDao;
import com.taisf.services.supplier.api.SupplierProductService;
import com.taisf.services.supplier.dto.SupplierProductRequest;
import com.taisf.services.supplier.vo.ProductClassifyInfo;
import com.taisf.services.supplier.vo.ProductClassifyVO;
import com.taisf.services.supplier.vo.SelectInfo4Week;
import com.taisf.services.supplier.vo.SupplierProductVO;
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
 * @author afi on on 2017/10/7.
 * @version 1.0
 * @since 1.0
 */
public class SupplierProductServiceProxyTest extends BaseTest {


    @Resource(name = "supplier.supplierProductServiceProxy")
    private SupplierProductService supplierProductService;




    @Test
    public void getSupplierClassifyProductTest() {

        DataTransferObject<List<ProductClassifyInfo>> classify = supplierProductService.getSupplierClassifyProduct("jipin","qpg001");
        System.out.println(JsonEntityTransform.Object2Json(classify));

    }

    @Test
    public void getSupplierProductClassifyTest() {
        DataTransferObject<List<ProductClassifyVO>> classify = supplierProductService.getSupplierProductClassify("123");
        System.out.println(JsonEntityTransform.Object2Json(classify));

    }

//    @Test
//    public void getSupplierProductListTest() {
//
//        SupplierProductRequest supplierProductRequest =  new SupplierProductRequest();
//        supplierProductRequest.setSupplierCode("123");
//        supplierProductRequest.setProductClassify(1);
//
//        DataTransferObject<List<SupplierProductVO>> supplierProductList = supplierProductService.getSupplierProductList(supplierProductRequest);
//        System.out.println(JsonEntityTransform.Object2Json(supplierProductList));
//
//    }


    @Test
    public void getSupplierClassifyProductByWeekTest() {

        DataTransferObject<SelectInfo4Week> classify = supplierProductService.getSupplierClassifyProductByWeek("0001","jipin",null);
        System.out.println(JsonEntityTransform.Object2Json(classify));

    }



}
