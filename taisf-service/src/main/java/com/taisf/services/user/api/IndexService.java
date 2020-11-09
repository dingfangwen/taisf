package com.taisf.services.user.api;

import com.jk.framework.base.entity.DataTransferObject;
import com.taisf.services.common.valenum.OrderTypeEnum;
import com.taisf.services.enterprise.entity.EnterpriseAddressEntity;
import com.taisf.services.user.vo.IndexBaseVO;
import com.taisf.services.user.vo.IndexVO;

import java.util.List;

/**
 * <p>获取首页信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/7.
 * @version 1.0
 * @since 1.0
 */
public interface IndexService {


    /**
     * 获取用户的地址信息列表
     * @author afi
     * @param userUid
     * @return
     */
    DataTransferObject<List<EnterpriseAddressEntity>> getUserAddressList(String userUid);
    /**
     * 获取用户的基本信息
     * @author afi
     * @param userUid
     * @return
     */
    DataTransferObject<IndexBaseVO> getUserBaseInfo(String userUid);

    /**
     * 获取基本信息
     * @param userUid
     * @return
     */
    DataTransferObject<IndexBaseVO> getBaseIndex(String userUid);

    /**
     * 获取首页信息
     * @param userUid
     * @return
     */
    DataTransferObject<IndexVO> getIndex(String userUid);


    /**
     * 根据当前的企业获取当前的订单信息
     * @param enterpriseCode
     * @return
     */
    DataTransferObject<OrderTypeEnum> getOrderType(String enterpriseCode);
}
