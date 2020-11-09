package com.taisf.services.order.manager;

import com.taisf.services.order.dao.CartDao;
import com.taisf.services.order.dto.CartBaseRequest;
import com.taisf.services.order.entity.CartEntity;
import com.taisf.services.product.entity.ProductEntity;
import com.taisf.services.supplier.dao.SupplierPackageDao;
import com.taisf.services.supplier.dao.SupplierProductDao;
import com.taisf.services.supplier.dto.SupplierProductRequest;
import com.taisf.services.supplier.entity.SupplierPackageEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>购物车先关操作</p>
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
@Service("order.cartManagerImpl")
public class CartManagerImpl {

	@Resource(name = "order.cartDao")
	private CartDao cartDao;


	/**
	 * 获取当前购物车的列表
	 * @author afi
	 * @param userUid
	 * @param businessUid
	 * @return
	 */
	public List<CartEntity> getCartByUserId(String userUid, String businessUid){
		//获取购物车列表
		return cartDao.getCartByUserId(userUid,businessUid);
	}


	/**
	 * 获取当前商品在购物车中的信息
	 * @param cartBaseRequest
	 * @return
	 */
	public CartEntity getCartByProduct(CartBaseRequest cartBaseRequest){
		return cartDao.getCartByProduct(cartBaseRequest);
	}


	/**
	 * 清空购物车
	 * @param userUid
	 * @param businessUid
	 * @return
	 */
	public int delUserCart(String userUid, String businessUid){

		return cartDao.delUserCart(userUid,businessUid);
	}


	/**
	 * 删除购物车
	 * @author afi
	 * @param id
	 * @return
	 */
	public int delCart(Integer id){
		return cartDao.delCart(id);
	}
	/**
	 * 修改购物车
	 * @author afi
	 * @param record
	 * @return
	 */
	public int updateCart(CartEntity record){
		return cartDao.updateCart(record);
	}


	/**
	 * 增加购物车
	 * @author afi
	 * @param record
	 * @return
	 */
	public int saveCart(CartEntity record){
		return cartDao.saveCart(record);
	}


}
