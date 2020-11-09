package com.taisf.services.supplier.manager;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.ValueUtil;
import com.taisf.services.order.vo.OrderProductVO;
import com.taisf.services.product.dao.ProductDao;
import com.taisf.services.product.entity.ProductEntity;
import com.taisf.services.supplier.dao.SupplierDao;
import com.taisf.services.supplier.dao.SupplierPackageDao;
import com.taisf.services.supplier.dao.SupplierProductDao;
import com.taisf.services.supplier.dto.SupplierProductRequest;
import com.taisf.services.supplier.entity.SupplierEntity;
import com.taisf.services.supplier.entity.SupplierPackageEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


/**
 * <p>供应商的商品信息</p>
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
@Service("supplier.supplierPackageManagerImpl")
public class SupplierPackageManagerImpl {


	@Resource(name = "supplier.supplierPackageDao")
	private SupplierPackageDao supplierPackageDao;


	@Resource(name = "product.productDao")
	private ProductDao productDao;


	/**
	 * 获取套餐的名称
	 * @param preTitle
	 * @param packageId
	 * @return
	 */
	public String dealPackageTitle(String preTitle,Integer packageId,Map<String,OrderProductVO> titleMap,int num){
		if (Check.NuNObj(titleMap)){
			titleMap = new HashMap<>();
		}
		StringBuffer sb = new StringBuffer();
		sb.append("【");
		sb.append(preTitle);
		sb.append("】");
		List<ProductEntity> list = this.getSupplierProByPackageId(packageId);
		if (Check.NuNCollection(list)){
			return sb.toString();
		}
		int i= 0;
		for (ProductEntity productEntity : list) {
			String key = ValueUtil.getStrValue(productEntity.getId());
			if (i > 0){
				sb.append("+");
			}
			sb.append(productEntity.getProductName());
			i++;
			//处理普通商品
			if (titleMap.containsKey(key)){
				OrderProductVO has = titleMap.get(key);
				has.setProductNum(has.getProductNum() + num);
			}else {
				OrderProductVO has = new OrderProductVO();
				has.setProductNum(num);
				has.setProductCode(productEntity.getId());
				has.setProductName(productEntity.getProductName());
				has.setProductPrice(productEntity.getPriceSale());
				titleMap.put(key,has);
			}

		}
		return sb.toString();
	}


	/**
	 * 获取当前的供应商打包信息
	 * @author afi
	 * @param packageId
	 * @return
	 */
	public List<ProductEntity> getSupplierProByPackageId(Integer packageId){
//		List<ProductEntity> proList = new ArrayList<>();
//		List<Integer> list = new ArrayList<>();
//		SupplierPackageEntity packageEntity = supplierPackageDao.getSupplierPackageById(packageId);
//		if (Check.NuNObj(packageEntity)) {
//			return proList;
//		}
//		if (!Check.NuNObj(packageEntity.getBigCode())){
//			list.add(packageEntity.getBigCode());
//		}
//		if (!Check.NuNObj(packageEntity.getSmallCode())){
//			list.add(packageEntity.getSmallCode());
//		}
//		if (!Check.NuNObj(packageEntity.getSuCode())){
//			list.add(packageEntity.getSuCode());
//		}
//		if (!Check.NuNObj(packageEntity.getTangCode())){
//			list.add(packageEntity.getTangCode());
//		}
//		if (!Check.NuNObj(packageEntity.getDrinkCode())){
//			list.add(packageEntity.getDrinkCode());
//		}
//		if (!Check.NuNObj(packageEntity.getFoodCode())){
//			list.add(packageEntity.getFoodCode());
//		}
//		if (!Check.NuNObj(packageEntity.getFruitCode())){
//			list.add(packageEntity.getFruitCode());
//		}
//		if (Check.NuNCollection(list)){
//			return proList;
//		}
		Map<String,ProductEntity> tmp = new HashMap<>();
		//获取商品列表
		List<ProductEntity>  productEntityList = productDao.getProductByPackageId(packageId);
		if (!Check.NuNCollection(productEntityList)){
			for (ProductEntity entity : productEntityList) {
				tmp.put(entity.getId()+"",entity);
			}
		}
//		for (Integer id : list) {
//			String key = id+"";
//			if (tmp.containsKey(key)){
//				proList.add(tmp.get(key));
//			}
//		}
		return productEntityList;
	}





}
