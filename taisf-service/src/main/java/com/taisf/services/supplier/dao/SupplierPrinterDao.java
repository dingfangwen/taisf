package com.taisf.services.supplier.dao;

import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.supplier.dto.SupplierPrinterRequest;
import com.taisf.services.supplier.entity.SupplierPrintterEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("supplierPrinterDao")
public class SupplierPrinterDao extends BaseDao {

    private String SQLID = "supplierPrinterDao.";

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(SupplierPrinterDao.class);


    /**
     * @author:zhangzhengguang
     * @date:2018/9/21
     * @description:查询供应商的所有打印机
     **/
    public List<SupplierPrintterEntity> findListSupplierPrinter(SupplierPrinterRequest request){
        return mybatisDaoContext.findAll(SQLID+"findListSupplierPrinter", SupplierPrintterEntity.class,request);
    }


}
