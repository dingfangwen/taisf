package com.taisf.services.supplier.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.taisf.services.common.valenum.AppScanTypeEnum;
import com.taisf.services.order.dto.SupStatsRequest;
import com.taisf.services.supplier.api.SupplierService;
import com.taisf.services.supplier.dao.SupplierDao;
import com.taisf.services.supplier.dto.SupplierRequest;
import com.taisf.services.supplier.entity.SupplierEntity;
import com.taisf.services.supplier.manager.SupplierManagerImpl;
import com.taisf.services.supplier.vo.SupplierPayInfo;
import com.taisf.services.user.entity.UserEntity;
import com.taisf.services.user.manager.UserManagerImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("supplier.supplierServiceProxy")
public class SupplierServiceProxy implements SupplierService{
	
	@Resource(name = "supplier.supplierManagerImpl")
    private SupplierManagerImpl supplierManager;

	@Autowired
    private SupplierDao supplierDao;


	@Resource(name = "user.userManagerImpl")
	private UserManagerImpl userManager;


	/**
	 * 获取供应商列表
	 * @return
	 */
	@Override
	public DataTransferObject<List<SupplierEntity>> getSupplierList(String supplierCode){
		DataTransferObject<List<SupplierEntity>> dto = new DataTransferObject<List<SupplierEntity>>();
		List<SupplierEntity> supplierList = supplierManager.getSupplierList(supplierCode);
		dto.setData(supplierList);
		return dto;
	}

	 /**
     * 获取供应商列表
     */
	@Override
	public DataTransferObject<List<SupplierEntity>> getAllSupplierList() {
		DataTransferObject<List<SupplierEntity>> dto = new DataTransferObject<List<SupplierEntity>>();
		List<SupplierEntity> supplierList = supplierManager.getAllSupplierList();
		dto.setData(supplierList);
        return dto;
	}

	/**
	 * @author:zhangzhengguang
	 * @date:2017/10/14
	 * @description:当前销售维护的供应商列表
	 **/
	@Override
	public DataTransferObject<PagingResult<SupplierEntity>> supplierPageList(SupplierRequest request) {
		DataTransferObject<PagingResult<SupplierEntity>> dto = new DataTransferObject<>();
		PagingResult<SupplierEntity> pagingResult = supplierDao.supplierPageList(request);
		dto.setData(pagingResult);
        return dto;
	}


	/**
	 * 获取供应商信息
	 * @param supplierCode
	 * @return
	 */
	@Override
	public DataTransferObject<SupplierEntity> getSupplierInfo(String  supplierCode) {
		DataTransferObject<SupplierEntity> dto = new DataTransferObject<>();
		SupplierEntity supplierEntity = supplierDao.getSupplierByCode(supplierCode);
		if (Check.NuNObj(supplierEntity)){
			dto.setErrorMsg("当前企业不存在");
			return dto;
		}
		dto.setData(supplierEntity);
		return dto;
	}


	/**
	 * 获取供应商信息
	 * @param payCode
	 * @return
	 */
	@Override
	public DataTransferObject<SupplierPayInfo> getPayInfo4payCode(String  payCode){
		DataTransferObject<SupplierPayInfo> dto = new DataTransferObject<>();
		if (Check.NuNStr(payCode)){
			dto.setErrorMsg("参数异常");
			return dto;
		}
		UserEntity user = userManager.getUserByPayCode(payCode);
		if (Check.NuNObj(user)){
			dto.setErrorMsg("当前骑手不存在");
			return dto;
		}
		SupplierEntity supplierEntity = supplierDao.getSupplierByCode(user.getBizCode());
		if (Check.NuNObj(supplierEntity)){
			dto.setErrorMsg("当前骑手不属于任何一个供应商");
			return dto;
		}
		SupplierPayInfo info = new SupplierPayInfo();
		BeanUtils.copyProperties(supplierEntity,info);
		info.setPayCode(user.getPayCode());
		dto.setData(info);
		return dto;
	}

	/**
	 * 获取骑士的信息
	 * @param knightUid
	 * @return
	 */
	@Override
	public DataTransferObject<SupplierPayInfo> getPayInfo4knight(String  knightUid){

		DataTransferObject<SupplierPayInfo> dto = new DataTransferObject<>();
		if (Check.NuNStr(knightUid)){
			dto.setErrorMsg("参数异常");
			return dto;
		}
		UserEntity user = userManager.getUserByUid4Supply(knightUid);
		if (Check.NuNObj(user)){
			dto.setErrorMsg("当前用户不存在");
			return dto;
		}
		if (Check.NuNStr(user.getPayCode())){
			dto.setErrorMsg("当前骑手未开通收款权限");
			return dto;
		}
		SupplierEntity supplierEntity = supplierDao.getSupplierByCode(user.getBizCode());
		if (Check.NuNObj(supplierEntity)){
			dto.setErrorMsg("当前骑手不属于任何一个供应商");
			return dto;
		}
		SupplierPayInfo info = new SupplierPayInfo();
		BeanUtils.copyProperties(supplierEntity,info);
		info.setPayCode(AppScanTypeEnum.QISHOU_PAY.transScanCode(user.getPayCode()));
		dto.setData(info);
		return dto;

	}
}
