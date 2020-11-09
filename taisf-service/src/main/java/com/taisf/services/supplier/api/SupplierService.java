package com.taisf.services.supplier.api;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.taisf.services.order.dto.SupStatsRequest;
import com.taisf.services.supplier.dto.SupplierRequest;
import com.taisf.services.supplier.entity.SupplierEntity;
import com.taisf.services.supplier.vo.SupplierPayInfo;

import java.util.List;

public interface SupplierService {




	/**
	 * 获取供应商列表
	 * @return
	 */
	DataTransferObject<List<SupplierEntity>> getSupplierList(String supplierCode);

	/**
	 * 获取供应商列表
	 * @return
	 */
	DataTransferObject<List<SupplierEntity>> getAllSupplierList();

	/**
	 * @author:zhangzhengguang
	 * @date:2017/10/14
	 * @description:当前销售维护的供应商列表
	 **/
	DataTransferObject<PagingResult<SupplierEntity>> supplierPageList(SupplierRequest request);


	/**
	 * 获取供应商信息
	 * @param supplierCode
	 * @return
	 */
	DataTransferObject<SupplierEntity> getSupplierInfo(String  supplierCode);


	/**
	 * 获取供应商信息
	 * @param nightUid
	 * @return
	 */
	DataTransferObject<SupplierPayInfo> getPayInfo4knight(String  nightUid);

	/**
	 * 获取供应商信息
	 * @param payCode
	 * @return
	 */
	DataTransferObject<SupplierPayInfo> getPayInfo4payCode(String  payCode);

}
