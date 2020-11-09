package com.taisf.services.supplier.api;

import com.jk.framework.base.entity.DataTransferObject;
import com.taisf.services.supplier.dto.SupplierPrinterRequest;
import com.taisf.services.supplier.entity.SupplierPrintterEntity;

import java.util.List;

public interface SupplierPrinterService {


    /**
     * @author:zhangzhengguang
     * @date:2018/9/21
     * @description:查询供应商的所有打印机
     **/
    DataTransferObject<List<SupplierPrintterEntity>> findListSupplierPrinter(SupplierPrinterRequest request);

}
