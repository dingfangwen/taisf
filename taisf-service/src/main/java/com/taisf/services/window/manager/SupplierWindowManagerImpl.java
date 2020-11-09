package com.taisf.services.window.manager;

import com.jk.framework.base.page.PagingResult;
import com.taisf.services.window.dao.SupplierWindowDao;
import com.taisf.services.window.entity.SupplierWindowEntity;
import com.taisf.services.window.req.SupplierWindowListRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author:zhangzhengguang
 * @date:2018/9/19
 * @description:
 **/
@Service("supplierWindowManagerImpl")
public class SupplierWindowManagerImpl {

	@Resource(name = "supplierWindowDao")
	private SupplierWindowDao supplierWindowDao;


	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/19
	 * @description:新增
	 **/
	public int saveSupplierWindow(SupplierWindowEntity entity){
		return supplierWindowDao.saveSupplierWindow(entity);
	}

	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/19
	 * @description:根据id查询
	 **/
	public SupplierWindowEntity getSupplierWindowById(Integer id){
		return supplierWindowDao.getSupplierWindowById(id);
	}

	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/19
	 * @description:修改
	 **/
	public int updateSupplierWindow(SupplierWindowEntity entity){
		return supplierWindowDao.updateSupplierWindow(entity);
	}

	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/19
	 * @description:删除
	 **/
	public int deleteSupplierWindow(Integer id){
		return supplierWindowDao.deleteSupplierWindow(id);
	}

	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/19
	 * @description:分页查询
	 **/
	public PagingResult<SupplierWindowEntity> pageListSupplierWindow(SupplierWindowListRequest request){
		return supplierWindowDao.pageListSupplierWindow(request);
	}

	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/19
	 * @description:查询所有
	 * **/
	public List<SupplierWindowEntity> findListSupplierWindow(SupplierWindowListRequest request){
		return supplierWindowDao.findListSupplierWindow(request);
	}



}
