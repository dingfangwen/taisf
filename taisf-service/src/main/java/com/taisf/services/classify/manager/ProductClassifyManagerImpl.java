package com.taisf.services.classify.manager;

import com.jk.framework.base.page.PagingResult;
import com.taisf.services.classify.dao.ProductClassifyDao;
import com.taisf.services.classify.entity.ProductClassifyEntity;
import com.taisf.services.classify.req.ProductClassifyListRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author:zhangzhengguang
 * @date:2018/9/19
 * @description:
 **/
@Service("productClassifyManagerImpl")
public class ProductClassifyManagerImpl {

	@Resource(name = "productClassifyDao")
	private ProductClassifyDao productClassifyDao;


	/**
	 * 获取供应商的分类
	 * @param supplierCode
	 * @return
	 */
	public List<ProductClassifyEntity> listProductClassifyBySupplierCode(String supplierCode){
		return productClassifyDao.listProductClassifyBySupplierCode(supplierCode);
	}


	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/19
	 * @description:新增
	 **/
	public int saveProductClassify(ProductClassifyEntity entity){
		return productClassifyDao.saveProductClassify(entity);
	}

	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/19
	 * @description:根据id查询
	 **/
	public ProductClassifyEntity getProductClassifyById(Integer id){
		return productClassifyDao.getProductClassifyById(id);
	}

	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/19
	 * @description:修改
	 **/
	public int updateProductClassify(ProductClassifyEntity entity){
		return productClassifyDao.updateProductClassify(entity);
	}

	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/19
	 * @description:删除
	 **/
	public int deleteProductClassify(Integer id){
		return productClassifyDao.deleteProductClassify(id);
	}

	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/19
	 * @description:分页查询
	 **/
	public PagingResult<ProductClassifyEntity> pageListProductClassify(ProductClassifyListRequest request){
		return productClassifyDao.pageListProductClassify(request);
	}

	/**
	 * @author:zhangzhengguang
	 * @date:2018/9/19
	 * @description:查询所有
	 * **/
	public List<ProductClassifyEntity> findListProductClassify(ProductClassifyListRequest request){
		return productClassifyDao.findListProductClassify(request);
	}



}
