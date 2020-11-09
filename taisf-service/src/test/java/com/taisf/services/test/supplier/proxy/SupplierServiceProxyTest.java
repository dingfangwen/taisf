package com.taisf.services.test.supplier.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.supplier.api.SupplierService;
import com.taisf.services.supplier.dto.SupplierPrinterRequest;
import com.taisf.services.supplier.entity.SupplierEntity;
import com.taisf.services.supplier.entity.SupplierPrintterEntity;
import com.taisf.services.supplier.manager.SupplierPrinterManagerImpl;
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
public class SupplierServiceProxyTest extends BaseTest {


    @Resource(name = "supplier.supplierServiceProxy")
    private SupplierService supplierService;

    @Resource(name = "supplierPrinterManagerImpl")
    private SupplierPrinterManagerImpl supplierPrinterManagerImpl;



    @Test
    public void getSupplierInfoTest() {

        DataTransferObject<SupplierEntity> classify = supplierService.getSupplierInfo("jipin");
        System.out.println(JsonEntityTransform.Object2Json(classify));

    }
  @Test
    public void findListSupplierPrinterTest() {
      SupplierPrinterRequest request = new SupplierPrinterRequest();
      request.setSupplierCode("jipin");
      request.setIsDefault(0);
      List<SupplierPrintterEntity> list = supplierPrinterManagerImpl.findListSupplierPrinter(request);

      System.out.println(JsonEntityTransform.Object2Json(list));

    }



}
