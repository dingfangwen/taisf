package com.taisf.services.pay.api;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.taisf.services.enterprise.vo.SupRechargeStatsVO;
import com.taisf.services.order.dto.SupStatsRequest;
import com.taisf.services.pay.dto.RechargeOrderListRequest;
import com.taisf.services.pay.dto.RechargeOrderRequest;
import com.taisf.services.pay.entity.RechargeOrderEntity;
import com.taisf.services.pay.vo.RechargeOrderVO;
import com.taisf.services.user.dto.UserMoneyRequest;
import com.taisf.services.user.vo.AccountUserLogVO;

import java.util.List;
import java.util.Map;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/4/16.
 * @version 1.0
 * @since 1.0
 */
public interface RechargeOrderService {



    /**
     * 获取充值记录
     * @author afi
     * @param rechargeOrderListRequest
     * @return
     */
    DataTransferObject<PagingResult<RechargeOrderVO>> findRechargeOrderByPage(RechargeOrderListRequest rechargeOrderListRequest);



    /**
     * 获取充值记录
     * @author afi
     * @param rechargeOrderListRequest
     * @return
     */
    DataTransferObject<List<RechargeOrderVO>> findRechargeOrderAll(RechargeOrderListRequest rechargeOrderListRequest);


    /**
     * 充值的下单逻辑
     * @author  afi
     * @param rechargeOrderRequest
     * @return
     */
    DataTransferObject<String> createRechargeOrder(RechargeOrderRequest rechargeOrderRequest);

    /**
     * 获取充值信息
     * @author  afi
     * @param orderSn
     * @return
     */
    DataTransferObject<RechargeOrderEntity> getRechargeOrderByOrderSn(String orderSn);


    /**
     * 处理回调
     * @author  afi
     * @param rechargeOrderEntity
     * @return
     */
    DataTransferObject<Void> dealRechargeOrder4PayReturn(RechargeOrderEntity rechargeOrderEntity);


    /**
     * 获取企业充值统计信息
     * @author afi
     * @param request
     * @return
     */
    Map<String,SupRechargeStatsVO> getSelfRechargeSupStatsMap(SupStatsRequest request);
}
