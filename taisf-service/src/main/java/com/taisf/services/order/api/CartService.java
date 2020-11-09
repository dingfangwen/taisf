package com.taisf.services.order.api;

import com.jk.framework.base.entity.DataTransferObject;
import com.taisf.services.order.dto.CartAddRequest;
import com.taisf.services.order.dto.CartBaseRequest;
import com.taisf.services.order.dto.CartCleanRequest;
import com.taisf.services.order.vo.CartInfoVO;

/**
 * <p>购物车的接口</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/28.
 * @version 1.0
 * @since 1.0
 */
public interface CartService {

    /**
     * 获取购物车列表信息
     * @param cartCleanRequest
     * @return
     */
    DataTransferObject<Void> cartClean(CartCleanRequest cartCleanRequest);
    /**
     * 添加购物车
     * @author afi
     * @param cartAddRequest
     * @return
     */
    DataTransferObject<CartInfoVO> addCart(CartAddRequest cartAddRequest);


    /**
     * 删除购物车
     * @author afi
     * @param cartBaseRequest
     * @return
     */
    DataTransferObject<CartInfoVO> delCart(CartAddRequest cartBaseRequest);


    /**
     * 获取购物车列表信息
     * @param businessUid
     * @param userUid
     * @return
     */
    DataTransferObject<CartInfoVO> cartInfo(String userUid, String businessUid,String enterpriseCode);



}
