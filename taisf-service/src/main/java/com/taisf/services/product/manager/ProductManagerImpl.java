package com.taisf.services.product.manager;

import com.jk.framework.base.utils.Check;
import com.taisf.services.product.dao.ProductDao;
import com.taisf.services.product.entity.ProductEntity;
import com.taisf.services.supplier.entity.SupplierPackageEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>商品信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/23.
 * @version 1.0
 * @since 1.0
 */
@Service("product.productManagerImpl")
public class ProductManagerImpl {

	@Resource(name = "product.productDao")
	private ProductDao productDao;

	/**
	 * 根据id获取当前的菜单list
	 * @author afi
	 * @param list
	 * @return
	 */
	public List<ProductEntity> getProductByList(List<Integer> list){
		//获取商品列表
		return productDao.getProductByList(list);
	}

	/**
	 * 获取商品map
	 * @author afi
	 * @param list
	 * @return
	 */
	public Map<String,ProductEntity> getProductByMap(List<Integer> list){
		Map<String,ProductEntity> map = new HashMap<>();
		if (Check.NuNCollection(list)){
			return map;
		}
		List<ProductEntity> listPro = productDao.getProductByList(list);
		if (!Check.NuNCollection(listPro)){
			for (ProductEntity productEntity : listPro) {
				map.put(productEntity.getId()+"",productEntity);
			}
		}
		return map;
	}


}
