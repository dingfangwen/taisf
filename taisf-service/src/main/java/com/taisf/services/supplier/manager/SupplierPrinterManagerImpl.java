package com.taisf.services.supplier.manager;

import com.taisf.services.supplier.dao.SupplierPrinterDao;
import com.taisf.services.supplier.dto.SupplierPrinterRequest;
import com.taisf.services.supplier.entity.SupplierPrintterEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author:zhangzhengguang
 * @date:2018/9/19
 * @description:
 **/
@Service("supplierPrinterManagerImpl")
public class SupplierPrinterManagerImpl {

	@Resource(name = "supplierPrinterDao")
	private SupplierPrinterDao productClassifyDao;


	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/21
	 * @description:查询供应商的所有打印机
	 **/
	public List<SupplierPrintterEntity> findListSupplierPrinter(SupplierPrinterRequest request){
		return productClassifyDao.findListSupplierPrinter(request);
	}




}
