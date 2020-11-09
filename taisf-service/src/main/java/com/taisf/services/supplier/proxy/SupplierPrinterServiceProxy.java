package com.taisf.services.supplier.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.Check;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.classify.proxy.ProductClassifyServiceProxy;
import com.taisf.services.supplier.api.SupplierPrinterService;
import com.taisf.services.supplier.dto.SupplierPrinterRequest;
import com.taisf.services.supplier.entity.SupplierPrintterEntity;
import com.taisf.services.supplier.manager.SupplierPrinterManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:zhangzhengguang
 * @date:2018/9/19
 * @description:
 **/
@Component("supplierPrinterServiceProxy")
public class SupplierPrinterServiceProxy implements SupplierPrinterService {


    private static final Logger LOGGER = LoggerFactory.getLogger(ProductClassifyServiceProxy.class);

    @Resource(name = "supplierPrinterManagerImpl")
    private SupplierPrinterManagerImpl supplierPrinterManagerImpl;



    @Override
    public DataTransferObject<List<SupplierPrintterEntity>> findListSupplierPrinter(SupplierPrinterRequest request){
        DataTransferObject<List<SupplierPrintterEntity>> dto = new DataTransferObject();
        if (Check.NuNObj(request) ||Check.NuNObj(request.getSupplierCode())){
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            List<SupplierPrintterEntity> listSupplierPrinter = supplierPrinterManagerImpl.findListSupplierPrinter(request);
            dto.setData(listSupplierPrinter);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "findListSupplierPrinter error:{}", e);
            dto.setErrorMsg("系统异常");
            return dto;
        }
        return dto;
    }

}
