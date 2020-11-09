package com.taisf.services.order.job;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.rst.ResponseDto;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.SignUtils;
import com.jk.framework.base.utils.ValueUtil;
import com.jk.framework.common.utils.CloseableHttpUtil;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.common.valenum.RecordPayTypeEnum;
import com.taisf.services.order.dto.RefundOrderRequest;
import com.taisf.services.order.entity.OrderEntity;
import com.taisf.services.order.manager.OrderManagerImpl;
import com.taisf.services.order.proxy.OrderServiceProxy;
import com.taisf.services.recharge.manager.RechargeManagerImpl;
import com.taisf.services.refund.constants.PayConstant;
import com.taisf.services.refund.constants.RefundStatusEnum;
import com.taisf.services.refund.dto.RefundJobRequest;
import com.taisf.services.refund.dto.RefundRequest;
import com.taisf.services.refund.entity.RefundEntity;
import com.taisf.services.refund.manager.RefundManagerImpl;
import com.taisf.services.user.entity.AccountLogEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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
 * @author afi on on 2018/1/2.
 * @version 1.0
 * @since 1.0
 */
@Service("order.cancelOrderJob")
public class CancelOrderJob {


    private static final Logger LOGGER = LoggerFactory.getLogger(CancelOrderJob.class);


    @Resource(name = "order.orderManagerImpl")
    private OrderManagerImpl orderManager;


    @Resource(name = "order.orderServiceProxy")
    private OrderServiceProxy orderServiceProxy;
    /**
     * 处理取消的逻辑
     * @return
     */
    public DataTransferObject<Void> dealCancelAll() {
        DataTransferObject dto =new DataTransferObject();
        List<OrderEntity> list = orderManager.getOrder2Canceled();
        if ( Check.NuNCollection(list)){
            return dto;
        }
        for (OrderEntity orderEntity : list) {
            RefundOrderRequest refundOrderRequest = new RefundOrderRequest();
            refundOrderRequest.setOrderSn(orderEntity.getOrderSn());
            refundOrderRequest.setOpId("systerm");
            refundOrderRequest.setOpName("系统自动取消");
            DataTransferObject<String> cancelDto = orderServiceProxy.cancelOrder(refundOrderRequest);
            if(!cancelDto.checkSuccess()){
                LogUtil.error(LOGGER,"取消订单失败,par:{},返回结果:{}",JsonEntityTransform.Object2Json(refundOrderRequest),cancelDto.toJsonString());
            }
        }
        return dto;
    }



}
