package com.taisf.services.pay.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.BigDecimalUtil;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.ValueUtil;
import com.jk.framework.cache.redis.api.RedisOperations;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.common.valenum.FaceTypeEnum;
import com.taisf.services.common.valenum.OrdersStatusEnum;
import com.taisf.services.common.valenum.OrdersStatusShowEnum;
import com.taisf.services.device.api.PushService;
import com.taisf.services.order.constant.OrderConstant;
import com.taisf.services.order.entity.OrderEntity;
import com.taisf.services.order.entity.OrderMoneyEntity;
import com.taisf.services.order.manager.OrderManagerImpl;
import com.taisf.services.order.vo.KnightOrderVO;
import com.taisf.services.pay.api.PayService;
import com.taisf.services.pay.dto.PayRecordRequest;
import com.taisf.services.pay.entity.PayRecordEntity;
import com.taisf.services.pay.manager.PayManagerImpl;
import com.taisf.services.push.request.MoneySendRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>支付相关的</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/3/27.
 * @version 1.0
 * @since 1.0
 */
@Component("pay.payServiceProxy")
public class PayServiceProxy implements PayService {


    private static final Logger LOGGER = LoggerFactory.getLogger(PayServiceProxy.class);


    @Resource(name = "push.pushServiceProxy")
    private PushService pushServiceProxy;

    @Resource(name = "order.orderManagerImpl")
    private OrderManagerImpl orderManager;


    @Resource(name = "pay.payManagerImpl")
    private PayManagerImpl payManager;

    @Autowired
    private RedisOperations redisOperation;


    /**
     * 支付回调信息
     * @author  afi
     * @param payRecordRequest
     * @return
     */
    @Override
    public DataTransferObject<Void> dealOrderPay(PayRecordRequest payRecordRequest) {
        DataTransferObject dto = new DataTransferObject();

        if (Check.NuNObj(payRecordRequest)){
            dto.setErrorMsg("参数异常");
            return dto;
        }

        if (Check.NuNStr(payRecordRequest.getOrderSn())){
            dto.setErrorMsg("参数异常");
            return dto;
        }
        Date payTime;
        if (Check.NuNObj(payRecordRequest.getPaytime())){
            dto.setErrorMsg("支付时间为空");
            return dto;
        }else {
            Double time = BigDecimalUtil.mul(payRecordRequest.getPaytime(),1000);
            payTime = new Date(time.longValue());
            payRecordRequest.setPayTime(payTime);
        }

        try {
            //
            OrderEntity orderEntity = orderManager.getOrderBaseBySn(payRecordRequest.getOrderSn());



            //

            if (Check.NuNObj(orderEntity)){
                dto.setErrorMsg("当前订单不存在");
                return dto;
            }

            FaceTypeEnum faceTypeEnum = FaceTypeEnum.getTypeByCode(orderEntity.getFaceType());
            Boolean face = false;
            if(!Check.NuNObj(faceTypeEnum)
                    && faceTypeEnum.getCode() > FaceTypeEnum.NULL.getCode()){
                face = true;
            }

            Integer orderStatus =  orderEntity.getOrderStatus();
            OrdersStatusEnum statusEnum = OrdersStatusEnum.getByCode(orderStatus);
            if (Check.NuNObj(statusEnum)){
                dto.setErrorMsg("异常的支付状态");
                return dto;
            }
            if (statusEnum.getCode() >= OrdersStatusEnum.HAS_PAY.getCode()){
                //幂等返回支付状态
                return dto;
            }else if (statusEnum.getCode() == OrdersStatusEnum.NO_PAY.getCode()){

                //获取当前用的用户信息
                String key = OrderConstant.trans2Key4KnightOrder(orderEntity.getUserUid());
                String json = redisOperation.get(key);
                if (!Check.NuNStr(json)){
                    KnightOrderVO vo= JsonEntityTransform.json2Object(json, KnightOrderVO.class);
                    if (!Check.NuNObj(vo)){
                        // 订单编号
                        String orderSn =  vo.getOrderSn();
                        if (payRecordRequest.getOrderSn().equals(orderSn)){
                            vo.setOrderStatus(OrdersStatusShowEnum.HAS_PAYED.getCode());
                            vo.setPayTime(payTime);
                            //更新缓存信息
                            redisOperation.setex(key,OrderConstant.KNIGHT_ORDER_SENCOND,JsonEntityTransform.Object2Json(vo));
                        }
                    }
                }

                OrderMoneyEntity orderMoney = orderManager.getOrderMoneyByOrderSn(payRecordRequest.getOrderSn());
                int needPay = ValueUtil.getintValue(orderMoney.getNeedPay());
                if (needPay != payRecordRequest.getTotalFee()){
                    dto.setErrorMsg("异常的支付金额");
                    return dto;
                }
                //保存当前的订单为已支付
                PayRecordEntity save = new PayRecordEntity();
                save.setNeedMoney(needPay);
                save.setRecordUid(orderEntity.getUserUid());
                BeanUtils.copyProperties(payRecordRequest,save);
                if (Check.NuNObj(save.getCreateTime())){
                    save.setCreateTime(new Date());
                }
                payManager.updateOrderPay(save,face);

                this.dealSend(orderEntity,orderMoney.getSumMoney());
            }else {
                dto.setErrorMsg("异常的支付状态");
                return dto;
            }
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【订单支付记录】error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("订单支付失败");
            return dto;
        }
        return dto;
    }

    /**
     * 发送消息
     * @param orderEntity
     */
    private void dealSend(OrderEntity orderEntity,Integer money){
        if (ValueUtil.getintValue(money) <= 0){
            return;
        }
        MoneySendRequest moneySendRequest = new MoneySendRequest();
        moneySendRequest.setUserId(orderEntity.getKnightUid());
        moneySendRequest.setMoney(ValueUtil.getStrValue(BigDecimalUtil.div(money,100)));
        moneySendRequest.setPhone(orderEntity.getUserTel());
        moneySendRequest.setName(orderEntity.getUserName());
        pushServiceProxy.sendMoneySuccess(moneySendRequest);
    }

}
