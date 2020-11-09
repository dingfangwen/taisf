package com.taisf.services.order.api;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.taisf.services.enterprise.vo.EnterpriseOrderStatsVO;
import com.taisf.services.enterprise.vo.SupOrderStatsVO;
import com.taisf.services.order.dto.*;
import com.taisf.services.order.entity.OrderEntity;
import com.taisf.services.order.vo.*;

import java.util.List;
import java.util.Map;

/**
 * <p>订单相关接口</p>
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
public interface OrderService {

    /**
     * 获取订单的餐柜信息
     * @param orderSn
     * @return
     */
    DataTransferObject<BuffetOrderVO>  getBuffetByOrderSn(String orderSn, String userId);


    /**
     * 获取企业订单的统计信息
     * @author afi
     * @param request
     * @return
     */
    Map<String,SupOrderStatsVO> getSupOrderStatsMap(SupStatsRequest request);

    /**
     * 取消订单
     * @author afi
     * @param refundOrderRequest
     * @return
     */
    DataTransferObject<String>  cancelOrder(RefundOrderRequest refundOrderRequest);


    /**
     * 后台取消订单
     * @author afi
     * @param refundOrderRequest
     * @return
     */
    DataTransferObject<String>  backCancelOrder(BackRefundOrderRequest refundOrderRequest);



    /**
     * 获取当前的用户简版信息
     * @param userUid
     * @return
     */
    DataTransferObject<UserSimpleVO> getUserSimpleInfo(String userUid);

    /**
     * 面对面收款
     * @author afi
     * @param createOrderRequest
     * @return
     */
    DataTransferObject<FaceVO> faceOrder(CreateOrderRequest createOrderRequest, boolean needPwd);


    /**
     * 面对面收款
     * @author afi
     * @param createOrderRequest
     * @return
     */
    DataTransferObject<FaceVO> faceOrder(CreateOrderRequest createOrderRequest,boolean needPwd,Integer faceType);

    /**
     * 初始化面对面收款
     * @param userId
     * @param costMoney
     * @return
     */
    DataTransferObject<InitFaceVO> initFace(String userId,Integer costMoney) ;


    /**
     * 申请退款
     * @author afi
     * @param refundOrderRequest
     * @return
     */
    DataTransferObject<Void>  refundOrder(RefundOrderRequest refundOrderRequest);


    /**
     * 获取当前用户的带完成的订单
     * @author afi
     * @param userPhone
     * @return
     */
    DataTransferObject<List<OrderInfoVO>> getOrderInfoWaitingListByPhone(String userPhone);

    /**
     * 获取当前的统计情况
     * @param request
     * @return
     */
    DataTransferObject<PagingResult<DayTaskVO>> getEverydayTaskPgeList(DayTaskRequest request);


    DataTransferObject<List<DayTaskVO>> findaAllEverydayTask(DayTaskRequest request);
    /**
     * 获取企业订单的统计信息
     * @author afi
     * @param request
     * @return
     */
    DataTransferObject<List<EnterpriseOrderStatsVO>> getEnterpriseOrderStats(EnterpriseStatsRequest request);


    /**
     * 初始化下单
     * @param createOrderRequest
     * @return
     */
    DataTransferObject<OrderSaveInfo> initOrder(CreateOrderRequest createOrderRequest);


    /**
     * 初始化补单
     * @param createOrderRequest
     * @return
     */
    DataTransferObject<OrderSaveInfo> initExtOrder(CreateOrderRequest createOrderRequest);


    /**
     * 下单
     *
     * @param createOrderRequest
     * @return
     */
    DataTransferObject<CreateOrderVO> createOrder(CreateOrderRequest createOrderRequest);


    /**
     * 下单[补单]
     * @author afi
     * @param createOrderRequest
     * @return
     */
    DataTransferObject<String> createExtOrder(CreateOrderRequest createOrderRequest);



    /**
     * 获取订单的详细
     * @param orderSn
     * @return
     */
    DataTransferObject<OrderDetailVO>  getOrderDetailBySn(String orderSn);


    /**
     * 获取订单详情
     * @param orderSn
     * @return
     */
    DataTransferObject<OrderInfoVO>  getOrderInfoByOrderSn(String orderSn);

    /**
     * 获取当前订单的信息
     * @author afi
     * @param orderInfoRequest
     * @return
     */
    DataTransferObject<PagingResult<OrderInfoVO>> getOrderInfoPage(OrderInfoRequest orderInfoRequest);


    /**
     * 获取当前订单的信息
     * @author afi
     * @param orderInfoRequest
     * @return
     */
    DataTransferObject<PagingResult<OrderInfoVO>> getOrderInfoPageCurrent(OrderInfoRequest orderInfoRequest);


    /**
     * 获取当前用户的带完成的订单
     * @author afi
     * @param userUid
     * @return
     */
    DataTransferObject<List<OrderInfoVO>> getOrderInfoWaitingList(String userUid);


    DataTransferObject<PagingResult<OrderInfoVO>> getOrderListPageByEnterprisCode(OrderInfoRequest orderInfoRequest);

    DataTransferObject<OrderEntity> getOrderBaseBySn(String orderSn);

}
