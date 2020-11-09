package com.taisf.services.order.proxy;

import com.jk.framework.base.constant.YesNoEnum;
import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.thread.pool.SendThreadPool;
import com.jk.framework.base.utils.*;
import com.jk.framework.cache.redis.api.RedisOperations;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.buffet.dto.BuffetInfoRequest;
import com.taisf.services.buffet.entity.BuffetEntity;
import com.taisf.services.buffet.manager.BuffetManagerImpl;
import com.taisf.services.common.util.WeekUtil;
import com.taisf.services.common.valenum.*;
import com.taisf.services.device.api.PushService;
import com.taisf.services.device.common.BaseContext;
import com.taisf.services.device.common.valenum.DeviceStatusShowEnum;
import com.taisf.services.enterprise.entity.EnterpriseAddressEntity;
import com.taisf.services.enterprise.entity.EnterpriseConfigEntity;
import com.taisf.services.enterprise.manager.EnterpriseManagerImpl;
import com.taisf.services.enterprise.vo.EnterpriseInfoVO;
import com.taisf.services.enterprise.vo.EnterpriseOrderStatsVO;
import com.taisf.services.enterprise.vo.SupOrderStatsVO;
import com.taisf.services.order.api.CartService;
import com.taisf.services.order.api.OrderService;
import com.taisf.services.order.dto.*;
import com.taisf.services.order.entity.OrderEntity;
import com.taisf.services.order.entity.OrderMoneyEntity;
import com.taisf.services.order.entity.OrderProductEntity;
import com.taisf.services.order.manager.OrderManagerImpl;
import com.taisf.services.order.vo.*;
import com.taisf.services.pay.constant.PayConstant;
import com.taisf.services.pay.entity.PayRecordEntity;
import com.taisf.services.pay.manager.PayManagerImpl;
import com.taisf.services.push.request.MoneySendRequest;
import com.taisf.services.stock.StockUtil;
import com.taisf.services.stock.entity.StockWeekEntity;
import com.taisf.services.stock.manager.StockProductManagerImpl;
import com.taisf.services.stock.vo.StockCheckVO;
import com.taisf.services.stock.vo.StockDbVO;
import com.taisf.services.supplier.entity.SupplierEntity;
import com.taisf.services.supplier.manager.SupplierManagerImpl;
import com.taisf.services.supplier.manager.SupplierPackageManagerImpl;
import com.taisf.services.user.entity.UserAccountEntity;
import com.taisf.services.user.entity.UserEntity;
import com.taisf.services.user.manager.UserManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>订单相关</p>
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
@Component("order.orderServiceProxy")
public class OrderServiceProxy  extends BaseContext  implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceProxy.class);


    @Resource(name = "order.cartServiceProxy")
    private CartService cartService;

    @Resource(name = "order.orderManagerImpl")
    private OrderManagerImpl orderManager;

    @Resource(name = "pay.payManagerImpl")
    private PayManagerImpl payManager;


    @Resource(name = "buffet.buffetManagerImpl")
    private BuffetManagerImpl buffetManager;


    @Resource(name = "stock.stockProductManagerImpl")
    private StockProductManagerImpl stockProductManager;


    @Resource(name = "supplier.supplierPackageManagerImpl")
    private SupplierPackageManagerImpl supplierPackageManager;


    @Resource(name = "supplier.supplierManagerImpl")
    private SupplierManagerImpl supplierManager;

    @Resource(name = "user.userManagerImpl")
    private UserManagerImpl userManager;

    @Resource(name = "enterprise.enterpriseManagerImpl")
    private EnterpriseManagerImpl enterpriseManager;


    @Resource(name = "push.pushServiceProxy")
    private PushService pushServiceProxy;

    @Autowired
    private RedisOperations redisOperations;


    /**
     * 获取订单的餐柜信息
     * @param orderSn
     * @return
     */
    @Override
    public DataTransferObject<BuffetOrderVO>  getBuffetByOrderSn(String orderSn, String userId){
        DataTransferObject<BuffetOrderVO> dto = new DataTransferObject<>();
        if (Check.NuNStr(orderSn)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(userId)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        BuffetOrderVO vo = new BuffetOrderVO();
        //获取当前的购物车
        OrderInfoVO orderDetail = orderManager.getOrderInfoByOrderSn(orderSn);
        if (Check.NuNObj(orderDetail)){
            return dto;
        }
        if (Check.NuNObj(orderDetail)){
            dto.setErrorMsg("当前订单不存在");
            return dto;
        }

        if (!userId.equals(orderDetail.getUserUid())){
            dto.setErrorMsg("当前订单不存在");
            return dto;
        }
        //处理订单状态
        dealOrderStatus(orderDetail);
        //
        vo.setOrderStatus(orderDetail.getOrderStatus());
        vo.setBuffetFid(orderDetail.getBuffetFid());
        vo.setCellSn(orderDetail.getCellSn());

        //查看当前的餐柜状态
        if (Check.NuNStr(orderDetail.getBuffetFid())){
            dto.setErrorMsg("未查询到餐柜信息");
            return dto;
        }
        BuffetInfoRequest buffetInfoRequest =new BuffetInfoRequest();
        buffetInfoRequest.setBuffetFid(orderDetail.getBuffetFid());
        BuffetEntity buffetEntity = buffetManager.getBuffetByFid(buffetInfoRequest);
        //查看当前的餐柜状态
        if (Check.NuNObj(buffetEntity)){
            dto.setErrorMsg("未查询到餐柜信息");
            return dto;
        }
        BeanUtils.copyProperties(buffetEntity,vo);
        vo.setAddressFull(buffetEntity.tranAddressFull());
        //获取状态信息
        DeviceStatusShowEnum deviceStatus = getLinkService(buffetEntity.getConfigCode()).getDeviceStatusByDeviceId(buffetEntity.getDeviceId());
        vo.setLinkStatus(deviceStatus.getCode());
        vo.setLinkStatusDes(deviceStatus.getName());
        dto.setData(vo);
        return dto;
    }




    /**
     * 获取当前的统计情况
     * @param request
     * @return
     */
    @Override
    public DataTransferObject<PagingResult<DayTaskVO>> getEverydayTaskPgeList(DayTaskRequest request){
        DataTransferObject<PagingResult<DayTaskVO>> dto = new DataTransferObject<>();
        PagingResult page = null;
        List<DayTaskVO> list = orderManager.getEverydayTaskPgeList(request);
        if (Check.NuNCollection(list)){
            page = new PagingResult();
        }else {
            page = new PagingResult(ValueUtil.getlongValue(list.size()),list);
        }
        dto.setData(page);
        return dto;
    }

    @Override
    public DataTransferObject<List<DayTaskVO>> findaAllEverydayTask(DayTaskRequest request){
        DataTransferObject<List<DayTaskVO>> dto = new DataTransferObject<>();
        try {
            List<DayTaskVO> list = orderManager.getEverydayTaskPgeList(request);
            dto.setData(list);
        }catch (Exception e){
            dto.setErrorMsg("系统异常");
            LogUtil.error(LOGGER,"findaAllEverydayTask 查询异常e:{}",e);
        }
        return dto;
    }



    /**
     * 获取企业订单的统计信息
     * @author afi
     * @param request
     * @return
     */
    @Override
    public Map<String,SupOrderStatsVO> getSupOrderStatsMap(SupStatsRequest request) {
        Map<String,SupOrderStatsVO> map = new HashMap<>();
        if (Check.NuNObj(request)){
            return map;
        }

        List<SupOrderStatsVO> supOrderStats = orderManager.getSupOrderStats(request);
        if (!Check.NuNCollection(supOrderStats)){
            for (SupOrderStatsVO supOrderStat : supOrderStats) {
                map.put(supOrderStat.getSupplierCode(),supOrderStat);
            }
        }
        return map;
    }

    /**
     * 获取企业订单的统计信息
     * @author afi
     * @param request
     * @return
     */
    @Override
    public DataTransferObject<List<EnterpriseOrderStatsVO>> getEnterpriseOrderStats(EnterpriseStatsRequest request){
        DataTransferObject<List<EnterpriseOrderStatsVO>> dto = new DataTransferObject<>();
        if (Check.NuNObj(request)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        List<EnterpriseOrderStatsVO> list = orderManager.getEnterpriseOrderStats(request);
        dto.setData(list);
        return dto;
    }

    /**
     * 后台取消订单
     * @author afi
     * @param refundOrderRequest
     * @return
     */
    @Override
    public DataTransferObject<String>  backCancelOrder(BackRefundOrderRequest refundOrderRequest){

        DataTransferObject<String> dto = new DataTransferObject<>();
        if (Check.NuNObj(refundOrderRequest)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(refundOrderRequest.getOrderSn())){
            dto.setErrorMsg("异常的订单号");
            return dto;
        }

        //获取订单信息
        OrderEntity base = orderManager.getOrderBaseBySn(refundOrderRequest.getOrderSn());
        if (Check.NuNObj(base)){
            dto.setErrorMsg("当前订单不存在");
            return dto;
        }

        OrdersStatusEnum ordersStatusEnum = OrdersStatusEnum.getByCode(base.getOrderStatus());
        if (Check.NuNObj(ordersStatusEnum)){
            dto.setErrorMsg("异常的订单状态");
            return dto;
        }
        if (!ordersStatusEnum.checkCancel()){
            dto.setErrorMsg("当前订单状态不能取消");
            return dto;
        }

        OrderMoneyEntity money = orderManager.getOrderMoneyByOrderSn(refundOrderRequest.getOrderSn());
        if (Check.NuNObj(money)){
            dto.setErrorMsg("异常的订单支付信息");
            return dto;
        }
        // 已经支付金额
        int hasPay = ValueUtil.getintValue(money.getPayMoney()) + ValueUtil.getintValue(money.getPayBalance());
        if(hasPay <= 0){
            dto.setErrorMsg("未查寻到当前订单的支付信息");
            return dto;
        }
        if (Check.NuNObj(refundOrderRequest.getRefundMoney())){
            refundOrderRequest.setRefundMoney(hasPay);
        }
        if (ValueUtil.getintValue(refundOrderRequest.getRefundMoney()) > hasPay){
            dto.setErrorMsg("不能大于当前订单支付金额:" + BigDecimalUtil.div(hasPay,100) + "元");
            return dto;
        }

        // 获取当前订单的支付信息
        List<PayRecordEntity> payRecordList = payManager.getPayRecordByOrderSn(base.getOrderSn());
        if (Check.NuNCollection(payRecordList)){
            dto.setErrorMsg("未获取支付信息");
            return dto;
        }

        //取消订单释放库存
        if (!Check.NuNStr(base.getOrderJson())){
            List<StockDbVO>  dbs = JsonEntityTransform.json2List(base.getOrderJson(),StockDbVO.class);
            if (!Check.NuNCollection(dbs)){
                //还原库存
                this.cutProductStock4RedisReset(base.getOrderType(),dbs);
            }
        }
        //处理当前的库存信息
        List<StockWeekEntity> list = this.trans2Stock(base);
        //退款
        orderManager.cancelOrder(base,list,payRecordList,refundOrderRequest.getRefundMoney());
        //取消之后释放限购数量
        this.releaseOrderLimit(base);
        return dto;
    }



    /**
     * 取消订单
     * @author afi
     * @param refundOrderRequest
     * @return
     */
    @Override
    public DataTransferObject<String>  cancelOrder(RefundOrderRequest refundOrderRequest){

        DataTransferObject<String> dto = new DataTransferObject<>();
        if (Check.NuNObj(refundOrderRequest)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(refundOrderRequest.getOrderSn())){
            dto.setErrorMsg("异常的订单号");
            return dto;
        }

        //获取订单信息
        OrderEntity base = orderManager.getOrderBaseBySn(refundOrderRequest.getOrderSn());
        if (Check.NuNObj(base)){
            dto.setErrorMsg("当前订单不存在");
            return dto;
        }

        OrdersStatusEnum ordersStatusEnum = OrdersStatusEnum.getByCode(base.getOrderStatus());
        if (Check.NuNObj(ordersStatusEnum)){
            dto.setErrorMsg("异常的订单状态");
            return dto;
        }
        if (!ordersStatusEnum.checkCancel()){
            dto.setErrorMsg("当前订单状态不能取消");
            return dto;
        }

        //取消订单释放库存
        if (!Check.NuNStr(base.getOrderJson())){
            List<StockDbVO>  dbs = JsonEntityTransform.json2List(base.getOrderJson(),StockDbVO.class);
            if (!Check.NuNCollection(dbs)){
                //还原库存
                this.cutProductStock4RedisReset(base.getOrderType(),dbs);
            }
        }

        // 获取当前订单的支付信息
        List<PayRecordEntity> payRecordList = payManager.getPayRecordByOrderSn(base.getOrderSn());

        List<StockWeekEntity> list = this.trans2Stock(base);
        //退款
        orderManager.cancelOrder(base,list,payRecordList,null);
        //取消之后释放限购数量
        this.releaseOrderLimit(base);
        return dto;
    }

    /**
     * 取消之后释放限制数量
     * @author afi
     * @param orderBase
     */
    private void releaseOrderLimit(OrderEntity orderBase){
        //获取当前的key
        String key = this.getLimitCellKey(orderBase);
        if (Check.NuNStr(key)){
            return;
        }
        //设置当前的步长,取消-1
        int step = -1;
        Long has = redisOperations.incrByStep(key, step);
        LogUtil.info(LOGGER,"[取消限购] : supplierCode:{},orderSn:{},取消之后限购:{}",orderBase.getSupplierCode(),orderBase.getOrderSn(),has);
    }


    /**
     * 申请退款
     * @author afi
     * @param refundOrderRequest
     * @return
     */
    @Override
    public DataTransferObject<Void>  refundOrder(RefundOrderRequest refundOrderRequest){
        Date now = new Date();

        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(refundOrderRequest)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(refundOrderRequest.getOrderSn())){
            dto.setErrorMsg("异常的订单号");
            return dto;
        }

        if (Check.NuNStr(refundOrderRequest.getOpId())){
            dto.setErrorMsg("异常的操作人");
            return dto;
        }
        //获取订单信息
        OrderEntity base = orderManager.getOrderBaseBySn(refundOrderRequest.getOrderSn());
        if (Check.NuNObj(base)){
            dto.setErrorMsg("当前订单不存在");
            return dto;
        }
        if (!ValueUtil.getStrValue(base.getUserUid()).equals(refundOrderRequest.getOpId())){
            dto.setErrorMsg("权限不足");
            return dto;
        }
        OrdersStatusEnum ordersStatusEnum = OrdersStatusEnum.getByCode(base.getOrderStatus());
        if (Check.NuNObj(ordersStatusEnum)){
            dto.setErrorMsg("异常的订单状态");
            return dto;
        }
        if (!ordersStatusEnum.checkRefund()){
            dto.setErrorMsg("当前订单状态不能申请退款");
            return dto;
        }
        // 获取当前订单的支付信息
        List<PayRecordEntity> payRecordList = payManager.getPayRecordByOrderSn(base.getOrderSn());
        if (Check.NuNCollection(payRecordList)){
            dto.setErrorMsg("异常的支付信息");
            return dto;
        }

        //获取当前的订餐类型
        OrderTypeEnum orderTypeEnum = OrderTypeEnum.getTypeByCode(base.getOrderType());
        if (Check.NuNObj(orderTypeEnum)){
            dto.setErrorMsg("异常的订餐类型");
            return dto;
        }

        EnterpriseInfoVO infoVO = enterpriseManager.getEnterpriseInfoByCode(base.getEnterpriseCode());
        if (Check.NuNObj(infoVO)){
            dto.setErrorMsg("异常的企业信息");
            return dto;
        }

        if (Check.NuNObj(infoVO.getEnterpriseEntity().getTillTime())){
            dto.setErrorMsg("异常的企业截止时间");
            return dto;
        }
        if (infoVO.getEnterpriseEntity().getTillTime().before(now)){
            dto.setErrorMsg("加盟时间已经失效,请联系企业管理人员");
            return dto;
        }

        EnterpriseConfigEntity config =infoVO.getEnterpriseConfigEntity();
        if(Check.NuNObj(config)){
            dto.setErrorMsg("异常的企业配置信息");
            return dto;
        }

        //校验当前时间是否有效
        this.checkTimeOk(dto,orderTypeEnum,config,now,"已过退款时间");

        if (dto.checkSuccess()){
            if (!Check.NuNStr(base.getOrderJson())){
                List<StockDbVO>  dbs = JsonEntityTransform.json2List(base.getOrderJson(),StockDbVO.class);
                if (!Check.NuNCollection(dbs)){
                    //还原库存
                    this.cutProductStock4RedisReset(base.getOrderType(),dbs);
                }
            }

            List<StockWeekEntity> list = this.trans2Stock(base);
            //退款
            orderManager.refundOrder(base, payRecordList,list);
        }
        return dto;

    }


    /**
     * 处理当前的库存信息
     * @author afi
     * @param base
     * @return
     */
    private List<StockWeekEntity>  trans2Stock(OrderEntity base){
        String json = base.getOrderJson();
        List<StockWeekEntity> list = new ArrayList<>();
        if (Check.NuNStr(json)){
            return list;
        }
        List<StockDbVO>  dbs = JsonEntityTransform.json2List(json,StockDbVO.class);
        if (Check.NuNCollection(dbs)){
            return list;
        }
        for (StockDbVO db : dbs) {
            StockWeekEntity entity = new StockWeekEntity();
            entity.setSupplierCode(base.getSupplierCode());
            entity.setWeek(WeekUtil.getWeek(base.getCreateTime()));
            entity.setOrderType(base.getOrderType());
            entity.setProductCode(db.getP());
            entity.setSupplierProductType(db.getT());
            entity.setOrderSn(base.getOrderSn());
            entity.setStockType(StockTypeEnum.OUT.getCode());
            entity.setProductNum(-db.getN());
            entity.setWeekSn(DateUtil.getFirstDayOfWeek(base.getCreateTime()));

            list.add(entity);
        }
        return list;
    }


    /**
     * 获取当前用户的带完成的订单
     * @author afi
     * @param userUid
     * @return
     */
    @Override
    public DataTransferObject<List<OrderInfoVO>> getOrderInfoWaitingList(String userUid){
        DataTransferObject<List<OrderInfoVO>> dto = new DataTransferObject<>();
        if (Check.NuNObj(userUid)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        //分页获取订单列表
        List<OrderInfoVO> list = orderManager.getOrderInfoWaitingList(userUid);

        this.dealOrderStatus(list);
        dto.setData(list);
        return dto;
    }

    /**
     * 获取当前用户的带完成的订单
     * @author afi
     * @param userPhone
     * @return
     */
    @Override
    public DataTransferObject<List<OrderInfoVO>> getOrderInfoWaitingListByPhone(String userPhone){
        DataTransferObject<List<OrderInfoVO>> dto = new DataTransferObject<>();
        if (Check.NuNObj(userPhone)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }

        UserEntity userEntity = userManager.getByUserPhone(userPhone);
        if (Check.NuNObj(userEntity)){
            dto.setErrorMsg("当前用户不存在");
            return dto;
        }
        return getOrderInfoWaitingList(userEntity.getUserUid());
    }



    /**
     * 处理订单状态
     * @param list
     */
    private void dealOrderRefundStatus(List<OrderInfoVO> list){
        if (!Check.NuNCollection(list)){
            for (OrderEntity vo : list) {
                dealOrderStatus(vo);
            }
        }
    }



    /**
     * 处理订单状态
     * @param list
     */
    private void dealOrderStatus(List<? extends OrderEntity> list){
        if (!Check.NuNCollection(list)){
            for (OrderEntity vo : list) {
                dealOrderStatus(vo);
            }
        }
    }

    /**
     * 处理订单状态
     * @param vo
     */
    private void dealOrderStatus(OrderEntity vo){
        OrdersStatusEnum ordersStatusEnum = OrdersStatusEnum.getByCode(vo.getOrderStatus());
        OrdersStatusShowEnum showEnum = null;
        if (Check.NuNObj(ordersStatusEnum)){
            showEnum = OrdersStatusShowEnum.UNKNOWN;
        }else {
            if (vo instanceof OrderInfoVO){
                if (ordersStatusEnum.checkRefund()){
                    ((OrderInfoVO) vo).setCanRefund(YesNoEnum.YES.getCode());
                }
            }
            showEnum = ordersStatusEnum.getForeignType();
        }
        //设置状态
        vo.setOrderStatus(showEnum.getCode());
    }



    /**
     * 获取当前订单的信息
     * @author afi
     * @param orderInfoRequest
     * @return
     */
    @Override
    public DataTransferObject<PagingResult<OrderInfoVO>> getOrderInfoPageCurrent(OrderInfoRequest orderInfoRequest){
        DataTransferObject<PagingResult<OrderInfoVO>> dto = new DataTransferObject<>();
        if (Check.NuNObj(orderInfoRequest)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        orderInfoRequest.setLimit(10);
        //分页获取订单列表
        List<OrderInfoVO> list = orderManager.getOrderInfoPageCurrent(orderInfoRequest);
        if (null == list){
            list = new ArrayList<>();
        }
        this.dealOrderStatus(list);
        PagingResult page = new PagingResult(0L,list);
        dto.setData(page);
        return dto;
    }



    /**
     * 获取当前订单的信息
     * @author afi
     * @param orderInfoRequest
     * @return
     */
    @Override
    public DataTransferObject<PagingResult<OrderInfoVO>> getOrderInfoPage(OrderInfoRequest orderInfoRequest){
        DataTransferObject<PagingResult<OrderInfoVO>> dto = new DataTransferObject<>();
        if (Check.NuNObj(orderInfoRequest)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        //分页获取订单列表
        PagingResult<OrderInfoVO> page = orderManager.getOrderInfoPage(orderInfoRequest);
        this.dealOrderStatus(page.getList());
        dto.setData(page);
        return dto;
    }
    @Override
    public DataTransferObject<PagingResult<OrderInfoVO>> getOrderListPageByEnterprisCode(OrderInfoRequest orderInfoRequest){

        DataTransferObject<PagingResult<OrderInfoVO>> dto = new DataTransferObject<>();
        if (Check.NuNObj(orderInfoRequest)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if(Check.NuNObj(orderInfoRequest.getEnterpriseCode())){
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if(!Check.NuNObj(orderInfoRequest.getOrderStatus())){
            if(EnterpriseOrderStatsStatusShowEnum.REFUND.getCode() == orderInfoRequest.getOrderStatus()){
                orderInfoRequest.setOrderStatus(OrdersStatusEnum.REFUND.getCode());
            }else if(EnterpriseOrderStatsStatusShowEnum.SEND.getCode() == orderInfoRequest.getOrderStatus()){
                orderInfoRequest.setOrderStatus(OrdersStatusEnum.SEND.getCode());
            }
        }
        //分页获取订单列表
        PagingResult<OrderInfoVO> page = orderManager.getOrderListPageByEnterprisCode(orderInfoRequest);
        this.dealOrderStatus(page.getList());
        dto.setData(page);
        return dto;
    }



    /**
     * 获取订单的详细
     * @param orderSn
     * @return
     */
    @Override
    public DataTransferObject<OrderInfoVO>  getOrderInfoByOrderSn(String orderSn){
        DataTransferObject<OrderInfoVO> dto = new DataTransferObject<>();

        if (Check.NuNStr(orderSn)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        //获取当前的购物车
        OrderInfoVO orderDetail = orderManager.getOrderInfoByOrderSn(orderSn);
        if (Check.NuNObj(orderDetail)){
            return dto;
        }
        dto.setData(orderDetail);
        return dto;
    }



    /**
     * 获取订单的详细
     * @param orderSn
     * @return
     */
    @Override
    public DataTransferObject<OrderDetailVO>  getOrderDetailBySn(String orderSn){
        DataTransferObject<OrderDetailVO> dto = new DataTransferObject<>();

        if (Check.NuNStr(orderSn)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        //获取当前的购物车
        OrderDetailVO orderDetail = orderManager.getOrderDetailBySn(orderSn);
        if (Check.NuNObj(orderDetail)){
            return dto;
        }
        dealOrderStatus(orderDetail.getOrderEntity());
        dto.setData(orderDetail);
        return dto;

    }

    /**
     * 下单[补单]
     * @author afi
     * @param createOrderRequest
     * @return
     */
    @Override
    public DataTransferObject<String> createExtOrder(CreateOrderRequest createOrderRequest){
        DataTransferObject<String> dto = new DataTransferObject<>();

        if (Check.NuNStr(createOrderRequest.getBusinessUid())
                || Check.NuNStr(createOrderRequest.getUserUid())
                || Check.NuNObjs(createOrderRequest.getOrderType())) {
            dto.setErrorMsg("参数异常");
            return dto;
        }

        OrderSaveVO orderSaveVO = new OrderSaveVO();
        orderSaveVO.getOrderBase().setOrderType(createOrderRequest.getOrderType());

        //1. 填充订单的信息
        this.fillExtOrderInfo(dto,orderSaveVO, createOrderRequest,true);
        //2. 下单的逻辑
        orderManager.saveOrderSave(orderSaveVO);

        dto.setData(orderSaveVO.getOrderSn());
        return dto;
    }


    /**
     * 面对面收款
     * @author afi
     * @param createOrderRequest
     * @return
     */
    @Override
    public DataTransferObject<FaceVO> faceOrder(CreateOrderRequest createOrderRequest,boolean needPwd){
        return faceOrder(createOrderRequest, needPwd,null);
    }


    /**
     * 面对面收款
     * @author afi
     * @param createOrderRequest
     * @return
     */
    @Override
    public DataTransferObject<FaceVO> faceOrder(CreateOrderRequest createOrderRequest,boolean needPwd,Integer faceType){
        DataTransferObject<FaceVO> dto = new DataTransferObject<>();

        LogUtil.info(LOGGER,"faceOrder :par:{},needPwd:{}",JsonEntityTransform.Object2Json(createOrderRequest),needPwd);

        if (Check.NuNStr(createOrderRequest.getBusinessUid())
            && Check.NuNStr(createOrderRequest.getPayCode())) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(createOrderRequest.getUserUid())) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (ValueUtil.getintValue(createOrderRequest.getPrice()) < 0){
            dto.setErrorMsg("异常的金额");
            return dto;
        }
        OrderSaveVO orderSaveVO = new OrderSaveVO();
        if (needPwd){
            orderSaveVO.getOrderBase().setOrderType(OrderTypeEnum.FACE_FACE.getCode());
            if (!Check.NuNObj(faceType)){
                orderSaveVO.getOrderBase().setFaceType(faceType);
            }else {
                orderSaveVO.getOrderBase().setFaceType(FaceTypeEnum.KNIGHT.getCode());
            }
        }else {
            orderSaveVO.getOrderBase().setOrderType(OrderTypeEnum.FACE.getCode());
            orderSaveVO.getOrderBase().setFaceType(FaceTypeEnum.FACE.getCode());
        }

        //1. 填充面对面收款订单的信息
        this.faceOrderInfo(dto,orderSaveVO, createOrderRequest,needPwd);

        //2. 下单的逻辑
        if (dto.checkSuccess()){
            orderManager.saveOrderSave(orderSaveVO);
            LogUtil.info(LOGGER,"面对面收款完成: orderSaveVO:{}",JsonEntityTransform.Object2Json(orderSaveVO.getOrderBase()));
            //如果是已经支付,直接给骑手发送push消息
            if (orderSaveVO.getOrderBase().getOrderStatus() == OrdersStatusEnum.RECEIVE.getCode()){
                this.dealSend(orderSaveVO.getOrderBase().getKnightUid(),orderSaveVO.getOrderBase().getUserName(),orderSaveVO.getOrderBase().getUserTel(),ValueUtil.getintValue(orderSaveVO.getOrderMoney().getSumMoney()));
            }
            FaceVO vo =new FaceVO();
            vo.setOrderSn(orderSaveVO.getOrderSn());
            vo.setPrice(orderSaveVO.getExtPrice());
            vo.setSupplierCode(orderSaveVO.getOrderBase().getSupplierCode());
            vo.setSupplierName(orderSaveVO.getSupplierName());
            vo.setOrderStatus(OrdersStatusEnum.getByCode(orderSaveVO.getOrderBase().getOrderStatus()).getForeignType().getCode());
            dto.setData(vo);
        }


        return dto;
    }


    /**
     * 发送消息
     * @param userId
     * @param userName
     * @param userTel
     * @param money
     */
    private void dealSend(String userId, String userName,String userTel,Integer money){

        SendThreadPool.execute(()->{
            LogUtil.info(LOGGER,"开始发送推送: userId:{},money:{}",userId,money);
            if (Check.NuNStr(userId)){
                return;
            }
            if (ValueUtil.getintValue(money) <= 0){
                return;
            }
            MoneySendRequest moneySendRequest = new MoneySendRequest();
            moneySendRequest.setUserId(userId);
            moneySendRequest.setMoney(ValueUtil.getStrValue(BigDecimalUtil.div(money,100)));
            moneySendRequest.setPhone(userTel);
            moneySendRequest.setName(userName);
            pushServiceProxy.sendMoneySuccess(moneySendRequest);
        });
    }


    /**
     * 填充面对面收款
     * @author afi
     * @param orderSaveVO
     * @param createOrderRequest
     */
    private void  faceOrderInfo(DataTransferObject dto, OrderSaveVO orderSaveVO,  CreateOrderRequest createOrderRequest,boolean needPwd){
        if (!dto.checkSuccess()){
            return;
        }
        if (Check.NuNObjs(orderSaveVO)){
            return;
        }

        //处理订单的基本信息
        orderSaveVO.getOrderBase().setOrderSource(createOrderRequest.getSource());

        orderSaveVO.setExtPrice(createOrderRequest.getPrice());

        //1. 填充商家信息
        this.dealBusinessInfo(dto,orderSaveVO, createOrderRequest);

        //2. 获取用户信息
        this.dealUserInfo(dto,orderSaveVO, createOrderRequest,true);

        //3. 填充当前订单的收货信息
        this.dealFaceAddressInfo(dto,orderSaveVO);


        //4. 购物车中的商品信息
        this.dealFaceOrderProductInfo(dto,orderSaveVO);

        //5. 处理钱信息
        this.dealFaceOrderMoneyInfo(dto,orderSaveVO, createOrderRequest);

        //6. 处理账户信息
        this.dealBalanceInfoFace(dto,orderSaveVO,createOrderRequest.getPrice(), createOrderRequest,true,needPwd);

    }




    /**
     * 下单
     *
     * @param createOrderRequest
     * @return
     */
    @Override
    public DataTransferObject<CreateOrderVO> createOrder(CreateOrderRequest createOrderRequest){
        DataTransferObject<CreateOrderVO> dto = new DataTransferObject<>();

        if (Check.NuNStr(createOrderRequest.getBusinessUid())
                || Check.NuNStr(createOrderRequest.getUserUid())
                || Check.NuNObjs(createOrderRequest.getOrderType())) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        //获取当前的购物车
        DataTransferObject<CartInfoVO> cartDto=cartService.cartInfo(createOrderRequest.getUserUid(), createOrderRequest.getBusinessUid(),createOrderRequest.getEnterpriseCode());
        if (!cartDto.checkSuccess()){
            dto.setErrorMsg(cartDto.getMsg());
            return dto;
        }
        //获取当前的购物车信息
        CartInfoVO cartInfoVO = cartDto.getData();
        List<CartVO> cartList = cartInfoVO.getList();
        if (Check.NuNCollection(cartList)){
            dto.setErrorMsg("请添加商品");
            return dto;
        }
        OrderSaveVO orderSaveVO = new OrderSaveVO();
        orderSaveVO.getOrderBase().setOrderType(createOrderRequest.getOrderType());

        //1. 填充订单的信息
        this.fillOrderInfo(dto,orderSaveVO,cartInfoVO, createOrderRequest,true);
        //处理当前的处理库存的逻辑
        this.dealProductStock4Redis(dto,orderSaveVO);
        //2. 下单的逻辑
        if (dto.checkSuccess()){
            orderManager.saveOrderSave(orderSaveVO);
        }
        if (dto.checkSuccess()){
            CreateOrderVO vo = new CreateOrderVO();
            vo.setOrderSn(orderSaveVO.getOrderSn());
            vo.setOrderStatus(OrdersStatusEnum.getByCode(orderSaveVO.getOrderBase().getOrderStatus()).getForeignType().getCode());
            dto.setData(vo);
        }
        return dto;
    }

    /**
     * 处理返回结果
     * @param orderSaveVO
     * @param createOrderRequest
     * @return
     */
    private String getOrderRst(OrderSaveVO orderSaveVO,CreateOrderRequest createOrderRequest){
        if (ValueUtil.getintValue(createOrderRequest.getMix()) == YesNoEnum.NO.getCode()){
            return orderSaveVO.getOrderSn();
        }
        Map map = new HashMap();
        map.put("orderSn",orderSaveVO.getOrderSn());
        map.put("orderStatus",orderSaveVO.getOrderBase().getOrderStatus());
        return JsonEntityTransform.Object2Json(map);
    }

    /**
     * 处理当前的库存信息
     * @param dto
     * @param orderSaveVO
     */
    private void dealProductStock4Redis(DataTransferObject dto,OrderSaveVO orderSaveVO){
        if (!dto.checkSuccess()){
            return;
        }
        List<StockDbVO> dbStockList = orderSaveVO.getDbStockList();

        if (Check.NuNCollection(dbStockList)){
            return;
        }

        if (Check.NuNObj(orderSaveVO.getOrderBase().getOrderType())){
            dto.setErrorMsg("异常的处理订单类型");
            return;
        }
        if (cutProductStock4Redis(orderSaveVO.getOrderBase().getOrderType(),dbStockList)){
            LogUtil.info(LOGGER,"[库存]:处理库存并发失败:参数信息:{}",JsonEntityTransform.Object2Json(orderSaveVO));
            dto.setErrorMsg("处理库存异常,请稍后重试");
            cutProductStock4RedisReset(orderSaveVO.getOrderBase().getOrderType(),dbStockList);
        }
    }


    /**
     * 处理当前的库存
     * @param orderType
     * @param dbStockList
     * @return
     */
    private void cutProductStock4RedisReset(Integer orderType,List<StockDbVO> dbStockList){
        for (StockDbVO stockDbVO : dbStockList) {
            String key = getLackRedisKey(orderType,stockDbVO);
            redisOperations.incrByStep(key,-stockDbVO.getN());
        }

    }


    /**
     * 处理当前的库存
     * @param orderType
     * @param dbStockList
     * @return
     */
    private boolean cutProductStock4Redis(Integer orderType,List<StockDbVO> dbStockList){
        //获取不缺货
        boolean lack = false;

        LogUtil.info(LOGGER,"[库存]:开始处理库存,orderType:{},dbStockList:{}",orderType,JsonEntityTransform.Object2Json(dbStockList));
        for (StockDbVO stockDbVO : dbStockList) {
            String key = getLackRedisKey(orderType,stockDbVO);
            Long has = redisOperations.incrByStep(key, stockDbVO.getN());
            LogUtil.info(LOGGER,"[库存]:递归,key:{},has:{},limit:{}",key,has,stockDbVO.getL());
            if (has > stockDbVO.getL()){
                lack = true;
            }
        }
        return lack;
    }


    /**
     * 获取当前的
     * @param orderType
     * @param stock
     * @return
     */
    private String getLackRedisKey(Integer orderType,StockDbVO stock){
        return  DateUtil.dateFormat(new Date()) + "-" +
                ValueUtil.getStrValue(orderType) + "-" +
                ValueUtil.getStrValue(stock.getP()) + "-" +
                ValueUtil.getStrValue(stock.getT());
    }



    /**
     * 初始化补单
     * @author afi
     * @param createOrderRequest
     * @return
     */
    @Override
    public DataTransferObject<OrderSaveInfo> initExtOrder(CreateOrderRequest createOrderRequest){
        DataTransferObject<OrderSaveInfo> dto = new DataTransferObject<>();
        if (Check.NuNStr(createOrderRequest.getBusinessUid())
                || Check.NuNStr(createOrderRequest.getUserUid())
                || Check.NuNObjs(createOrderRequest.getOrderType())) {
            dto.setErrorMsg("参数异常");
            return dto;
        }

        OrderSaveVO orderSaveVO = new OrderSaveVO("");
        orderSaveVO.getOrderBase().setOrderType(createOrderRequest.getOrderType());
        //填充订单的信息
        this.fillExtOrderInfo(dto,orderSaveVO, createOrderRequest,false);
        OrderSaveInfo saveInfo = new OrderSaveInfo();
        BeanUtils.copyProperties(orderSaveVO,saveInfo);
        dto.setData(saveInfo);
        return dto;
    }


    /**
     * 初始化订单
     *
     * @param createOrderRequest
     * @return
     */
    @Override
    public DataTransferObject<OrderSaveInfo> initOrder(CreateOrderRequest createOrderRequest) {
        DataTransferObject<OrderSaveInfo> dto = new DataTransferObject<>();

        if (Check.NuNStr(createOrderRequest.getBusinessUid())
                || Check.NuNStr(createOrderRequest.getUserUid())) {
            dto.setErrorMsg("参数异常");
            return dto;
        }

        if (Check.NuNObjs(createOrderRequest.getOrderType())) {
            dto.setErrorMsg("订单时间已过");
            return dto;
        }

        //获取当前的购物车
        DataTransferObject<CartInfoVO> cartDto=cartService.cartInfo(createOrderRequest.getUserUid(), createOrderRequest.getBusinessUid(),createOrderRequest.getEnterpriseCode());
        if (!cartDto.checkSuccess()){
            dto.setErrorMsg(cartDto.getMsg());
            return dto;
        }
        //获取当前的购物车信息
        CartInfoVO cartInfoVO = cartDto.getData();
        List<CartVO> cartList = cartInfoVO.getList();
        if (Check.NuNCollection(cartList)){
            dto.setErrorMsg("请添加商品");
            return dto;
        }
        OrderSaveVO orderSaveVO = new OrderSaveVO("");
        orderSaveVO.getOrderBase().setOrderType(createOrderRequest.getOrderType());
        //填充订单的信息
        this.fillOrderInfo(dto,orderSaveVO,cartInfoVO, createOrderRequest,false);
        if (dto.checkSuccess()){
            OrderSaveInfo saveInfo = new OrderSaveInfo();
            BeanUtils.copyProperties(orderSaveVO,saveInfo);
            //当前订单是否需要密码
            saveInfo.setNeedPwd(this.checkNeedPwd(createOrderRequest.getUserUid(),saveInfo.getOrderMoney().getSumMoney()));
            dto.setData(saveInfo);
        }
        return dto;
    }


    /**
     * 初始化面对面收款
     * @param userId
     * @param costMoney
     * @return
     */
    @Override
    public DataTransferObject<InitFaceVO> initFace(String userId,Integer costMoney) {
        DataTransferObject<InitFaceVO> dto = new DataTransferObject<>();
        if (Check.NuNStr(userId)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (ValueUtil.getintValue(costMoney) <= 0) {
            dto.setErrorMsg("异常的金额");
            return dto;
        }
        InitFaceVO vo = new InitFaceVO();
        vo.setNeedPwd(this.checkNeedPwd(userId,costMoney));
        dto.setData(vo);
        return dto;
    }



    /**
     * 当前的用户是否需要密码
     * @param userId
     * @param costMoney
     * @return
     */
    private boolean checkNeedPwd(String userId,int costMoney,boolean need){
        if (!need){
            return false;
        }
        if (costMoney == 0){
            return false;
        }
        Boolean needPwd = true;
        UserEntity has =userManager.getUserByUid(userId);
        if (Check.NuNObj(has)){
            return needPwd;
        }

        if (ValueUtil.getintValue(has.getIsPwd()) == YesNoEnum.NO.getCode()){
            return needPwd;
        }
        if (costMoney > PayConstant.FREE_LIMIT_ONE_MONEY){
            return needPwd;
        }
        Long cost = payManager.getUserCostToday(userId);
        if ((ValueUtil.getintValue(cost) +costMoney ) >  PayConstant.FREE_LIMIT_DAY_MONEY){
            return needPwd;
        }
        return false;
    }


    /**
     * 当前的用户是否需要密码
     * @param userId
     * @param costMoney
     * @return
     */
    private boolean checkNeedPwd(String userId,int costMoney){
        return checkNeedPwd(userId,costMoney,true);
    }




    /**
     * 填充补单信息
     * @param dto
     * @param orderSaveVO
     * @param createOrderRequest
     * @param createFlag
     */
    private void  fillExtOrderInfo(DataTransferObject dto, OrderSaveVO orderSaveVO, CreateOrderRequest createOrderRequest, boolean createFlag){
        if (!dto.checkSuccess()){
            return;
        }
        if (Check.NuNObjs(orderSaveVO)){
            return;
        }

        if(Check.NuNObj(createOrderRequest.getSource())){
            dto.setErrorMsg("请填写渠道来源");
            return;
        }
        //处理订单的基本信息
        orderSaveVO.getOrderBase().setOrderSource(createOrderRequest.getSource());

        //1. 填充商家信息
        this.dealBusinessInfo(dto, orderSaveVO,createOrderRequest);

        //2. 获取用户信息
        this.dealUserInfo(dto,orderSaveVO, createOrderRequest);

        //3. 填充当前订单的收货信息
        this.dealUserAddressInfo(dto,orderSaveVO, createOrderRequest,createFlag);

        //4. 购物车中的商品信息
        this.dealExtOrderProductInfo(dto,orderSaveVO);

        //5. 处理钱信息
        this.dealExtOrderMoneyInfo(dto,orderSaveVO, createOrderRequest);

        //6. 处理账户信息
        this.dealBalanceInfo(dto,orderSaveVO,orderSaveVO.getExtPrice(), createOrderRequest,createFlag);

    }



    /**
     * 将当前购物车中的信息转化成订单相关的信息
     * @author afi
     * @param orderSaveVO
     * @param cartInfoVO
     */
    private void  fillOrderInfo(DataTransferObject dto, OrderSaveVO orderSaveVO, CartInfoVO cartInfoVO, CreateOrderRequest createOrderRequest, boolean createFlag){
        if (!dto.checkSuccess()){
            return;
        }
        if (Check.NuNObjs(orderSaveVO,cartInfoVO)){
            return;
        }

        List<CartVO> cartList = cartInfoVO.getList();
        if (Check.NuNCollection(cartList)){
            return;
        }
        if(Check.NuNObj(createOrderRequest.getSource())){
            dto.setErrorMsg("请填写渠道来源");
            return;
        }
        //处理订单的基本信息
        orderSaveVO.getOrderBase().setOrderSource(createOrderRequest.getSource());

        //1. 填充商家信息
        this.dealBusinessInfo(dto,orderSaveVO, createOrderRequest);

        //2. 获取用户信息
        this.dealUserInfo(dto,orderSaveVO, createOrderRequest);

        //3. 填充当前订单的收货信息
        this.dealUserAddressInfo(dto,orderSaveVO, createOrderRequest,createFlag);

        //4. 购物车中的商品信息
        this.dealProductInfo(dto,orderSaveVO,cartInfoVO);

        //5. 处理钱信息
        this.dealMoneyInfo(dto,orderSaveVO,cartInfoVO, createOrderRequest);

        //6. 处理账户信息
        this.dealBalanceInfo(dto,orderSaveVO,cartInfoVO.getPrice(), createOrderRequest,createFlag);

        //7. 处理当前餐厅的限购能力
        this.dealSupplierLimitCell(dto,orderSaveVO, createOrderRequest,createFlag);

    }


    /**
     * 获取当前的每天的供应商的限制key
     * @param orderBase
     * @return
     */
    private String getLimitCellKey(OrderEntity orderBase){
        if (Check.NuNObj(orderBase)){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        OrderTypeEnum typeByCode = OrderTypeEnum.getTypeByCode(orderBase.getOrderType());
        if (Check.NuNObj(typeByCode)){
            return "";
        }
        if (typeByCode.getCode() == OrderTypeEnum.LUNCH_COMMON.getCode()
                || typeByCode.getCode() == OrderTypeEnum.DINNER_COMMON.getCode() ){

            sb.append(orderBase.getSupplierCode());
            sb.append("_");
            sb.append(typeByCode.getCode());
            sb.append("_");
            sb.append(DateUtil.dateFormat(new Date()));
            return sb.toString();
        }
        return "";
    }

    /**
     * 校验当前的限购逻辑
     * @param dto
     * @param orderSaveVO
     * @param createOrderRequest
     */
    private void dealSupplierLimitCell(DataTransferObject dto, OrderSaveVO orderSaveVO, CreateOrderRequest createOrderRequest,boolean createFlag){
        if (!dto.checkSuccess()){
            return;
        }
        OrderEntity orderBase = orderSaveVO.getOrderBase();
        String orderSn = orderBase.getOrderSn();

        Integer limitCell = ValueUtil.getintValue(orderSaveVO.getLimitCell());
        if (ValueUtil.getintValue(limitCell) <=  0){
            LogUtil.info(LOGGER,"[限购处理] : supplierCode:{},orderSn:{}, limit:{},当前不限购",orderBase.getSupplierCode(),orderSn,limitCell);
            //当前格子不做限制,直接返回
            return;
        }
        //获取当前的key
        String key = this.getLimitCellKey(orderBase);
        if (Check.NuNStr(key)){
            return;
        }
        //设置当前的步长,下单1 查询0
        int step = 0;
        if (createFlag){
            step = 1;
        }
        Long has = redisOperations.incrByStep(key, step);
        int hasLimit  = ValueUtil.getintValue(has);
        if (createFlag){
            LogUtil.info(LOGGER,"[限购处理] : supplierCode:{},orderSn:{}, limit:{},算上这次已购:{}",orderBase.getSupplierCode(),orderSn,limitCell,hasLimit);
        }else {
            LogUtil.info(LOGGER,"[限购校验] : supplierCode:{},orderSn:{}, limit:{},当前:{}",orderBase.getSupplierCode(),orderSn,limitCell,hasLimit);
        }
        if (hasLimit  > limitCell){
            LogUtil.info(LOGGER,"[限购校验未通过] : supplierCode:{},orderSn:{}, limit:{},当前:{}",orderBase.getSupplierCode(),orderSn,limitCell,hasLimit);
            dto.setErrorMsg("餐厅火爆,今日菜品已售完");
            return;
        }

    }

    /**
     * 处理当前的余额信息
     * @author  afi
     * @param dto
     * @param orderSaveVO
     * @param cost
     * @param createOrderRequest
     * @param createFlag
     */
    private void dealBalanceInfoFace(DataTransferObject dto, OrderSaveVO orderSaveVO, int cost, CreateOrderRequest createOrderRequest, boolean createFlag,boolean pwd) {

        if (!dto.checkSuccess()) {
            return;
        }
        OrderMoneyEntity money =  orderSaveVO.getOrderMoney();

        UserAccountEntity accountEntity =userManager.fillAndGetAccountUser(createOrderRequest.getUserUid());

        //校验当前的账户状态
        AccountStatusEnum accountStatusEnum = AccountStatusEnum.getTypeByCode(accountEntity.getAccountStatus());
        if (Check.NuNObj(accountStatusEnum)){
            dto.setErrorMsg("异常的账户状态");
            return;
        }
        if (!accountStatusEnum.checkOk()){
            dto.setErrorMsg(accountStatusEnum.getDesc());
            return;
        }
        //获取当前的余额
        int drawBalance = ValueUtil.getintValue(accountEntity.getDrawBalance());
        if (drawBalance < 0){
            dto.setErrorMsg("异常的账户信息");
            return;
        }
        orderSaveVO.setDrawBalance(drawBalance);

        if (drawBalance >= cost){
            //全部用余额支付
            money.setPayBalance(cost);
            orderSaveVO.getOrderBase().setOrderStatus(OrdersStatusEnum.RECEIVE.getCode());
            money.setNeedPay(0);
        }else{
            if (ValueUtil.getintValue(createOrderRequest.getMix()) == YesNoEnum.NO.getCode()){
                dto.setErrorMsg("余额不足");
                return;
            }
            money.setPayBalance(drawBalance);
            orderSaveVO.getOrderBase().setOrderStatus(OrdersStatusEnum.NO_PAY.getCode());
            money.setNeedPay(cost-drawBalance);
        }

        if (!createFlag){
            //非创建订单,直接返回
            return;
        }
        //不需要余额支付
        if (ValueUtil.getintValue(money.getPayBalance()) <= 0){
            return;
        }

        if (!pwd){
            return;
        }
        if (Check.NuNStr(accountEntity.getAccountPassword())){
            dto.setErrorMsg("余额支付下单,需要先设置支付密码");
            return;
        }
        if (!this.checkNeedPwd(createOrderRequest.getUserUid(),money.getPayBalance(),pwd)){
            //不需要输入密码
            return;
        }
        if (Check.NuNStr(createOrderRequest.getPwd())){
            dto.setErrorMsg("请输入交易密码");
            return;
        }
        if (!createOrderRequest.getPwd().equals(accountEntity.getAccountPassword())){
            dto.setErrorMsg("支付密码错误");
            return;
        }
    }








    /**
     * 获取当前的用户简版信息
     * @param userUid
     * @return
     */
    @Override
    public DataTransferObject<UserSimpleVO> getUserSimpleInfo(String userUid){
        DataTransferObject<UserSimpleVO> dto = new DataTransferObject<>();

        if (Check.NuNStr(userUid)){
            dto.setErrorMsg("参数异常");
            return dto;
        }

        UserSimpleVO simple = new UserSimpleVO();
        simple.setUserUid(userUid);
        UserAccountEntity accountEntity =userManager.fillAndGetAccountUser(userUid);
        //校验当前的账户状态
        AccountStatusEnum accountStatusEnum = AccountStatusEnum.getTypeByCode(accountEntity.getAccountStatus());
        if (Check.NuNObj(accountStatusEnum)){
            dto.setErrorMsg("异常的账户状态");
            return dto;
        }
        if (!accountStatusEnum.checkOk()){
            dto.setErrorMsg(accountStatusEnum.getDesc());
            return dto;
        }
        //获取当前的余额
        int drawBalance = ValueUtil.getintValue(accountEntity.getDrawBalance());
        if (drawBalance < 0){
            dto.setErrorMsg("异常的账户信息");
            return dto;
        }
        simple.setDrawBalance(drawBalance);
        if (Check.NuNStr(accountEntity.getAccountPassword())){
            simple.setPwdFlag(false);
        }
        dto.setData(simple);
        return dto;
    }

    /**
     * 处理当前的余额信息
     * @author  afi
     * @param dto
     * @param orderSaveVO
     * @param cost
     * @param createOrderRequest
     * @param createFlag
     */
    private void dealBalanceInfo(DataTransferObject dto, OrderSaveVO orderSaveVO, int cost, CreateOrderRequest createOrderRequest, boolean createFlag) {

        if (!dto.checkSuccess()) {
            return;
        }
        OrderMoneyEntity money =  orderSaveVO.getOrderMoney();

        UserAccountEntity accountEntity =userManager.fillAndGetAccountUser(createOrderRequest.getUserUid());

        //校验当前的账户状态
        AccountStatusEnum accountStatusEnum = AccountStatusEnum.getTypeByCode(accountEntity.getAccountStatus());
        if (Check.NuNObj(accountStatusEnum)){
            dto.setErrorMsg("异常的账户状态");
            return;
        }
        if (!accountStatusEnum.checkOk()){
            dto.setErrorMsg(accountStatusEnum.getDesc());
            return;
        }
        //获取当前的余额
        int drawBalance = ValueUtil.getintValue(accountEntity.getDrawBalance());
        if (drawBalance < 0){
            dto.setErrorMsg("异常的账户信息");
            return;
        }
        orderSaveVO.setDrawBalance(drawBalance);

        if (drawBalance >= cost){
            //全部用余额支付
            money.setPayBalance(cost);
            orderSaveVO.getOrderBase().setOrderStatus(OrdersStatusEnum.HAS_PAY.getCode());
            money.setNeedPay(0);
        }else{
            if (ValueUtil.getintValue(createOrderRequest.getMix()) == YesNoEnum.NO.getCode()){
                //余额不足 - 历史版本兼容
                money.setPayBalance(0);
                orderSaveVO.getOrderBase().setOrderStatus(OrdersStatusEnum.NO_PAY.getCode());
                money.setNeedPay(cost);
            }else {
                int needPay = cost - drawBalance;
                //余额不足
                money.setPayBalance(drawBalance);
                orderSaveVO.getOrderBase().setOrderStatus(OrdersStatusEnum.NO_PAY.getCode());
                money.setNeedPay(needPay);
            }
        }
        if (!createFlag){
            //非创建订单,直接返回
            return;
        }
        if (ValueUtil.getintValue(money.getPayBalance()) <= 0){
            return;
        }

        if (Check.NuNStr(accountEntity.getAccountPassword())){
            dto.setErrorMsg("余额支付下单,需要先设置支付密码");
            return;
        }

        //当前订单是否需要密码
        boolean needPwd = this.checkNeedPwd(createOrderRequest.getUserUid(),cost);
        if (!needPwd){
            //不需要支付密码,直接下单
            return;
        }
        if (Check.NuNStr(createOrderRequest.getPwd())){
            dto.setErrorMsg("请输入交易密码");
            return;
        }
        if (!createOrderRequest.getPwd().equals(accountEntity.getAccountPassword())){
            dto.setErrorMsg("支付密码错误");
            return;
        }
    }


    /**
     * 校验是否匹配
     * @param org
     * @param md
     * @return
     */
    private boolean checkMD5(String org,String md){
       return MD5Util.MD5Encode(org).equals(md);
    }


    /**
     * 处理当前的补单的商品价格信息
     * @param dto
     * @param orderSaveVO
     * @param createOrderRequest
     */
    private void dealFaceOrderMoneyInfo(DataTransferObject dto,OrderSaveVO orderSaveVO,CreateOrderRequest createOrderRequest) {
        if (!dto.checkSuccess()) {
            return;
        }
        OrderMoneyEntity money =  orderSaveVO.getOrderMoney();
        money.setSumMoney(createOrderRequest.getPrice());
        dealOrderMoneyBase(money);
    }



    /**
     * 处理当前的补单的商品价格信息
     * @param dto
     * @param orderSaveVO
     * @param createOrderRequest
     */
    private void dealExtOrderMoneyInfo(DataTransferObject dto,OrderSaveVO orderSaveVO,CreateOrderRequest createOrderRequest) {
        if (!dto.checkSuccess()) {
            return;
        }
        OrderMoneyEntity money =  orderSaveVO.getOrderMoney();
        money.setSumMoney(orderSaveVO.getExtPrice());
        dealOrderMoneyBase(money);
    }



    /**
     * 处理当前的商品信息
     * @param dto
     * @param orderSaveVO
     * @param cartInfoVO
     */
    private void dealMoneyInfo(DataTransferObject dto,OrderSaveVO orderSaveVO,CartInfoVO cartInfoVO,CreateOrderRequest createOrderRequest) {

        if (!dto.checkSuccess()) {
            return;
        }
        OrderMoneyEntity money =  orderSaveVO.getOrderMoney();
        money.setSumMoney(cartInfoVO.getPrice());
        dealOrderMoneyBase(money);
    }

    /**
     * 初始化当前的金额信息
     * @param money
     */
    private void dealOrderMoneyBase(OrderMoneyEntity money) {
        money.setCarryMoney(0);
        money.setRedMoney(0);
        money.setCouponMoney(0);
        money.setDiscountMoney(0);
    }

    /**
     * 处理当前的补单商品信息
     * @param dto
     * @param orderSaveVO
     */
    private void dealExtOrderProductInfo(DataTransferObject dto,OrderSaveVO orderSaveVO) {
        if (!dto.checkSuccess()) {
            return;
        }
        OrderProductEntity product = new OrderProductEntity();
        product.setOrderSn(orderSaveVO.getOrderSn());
        product.setProductCode(0);
        product.setProductType(SupplierProductTypeEnum.EXR_PRODUCT.getCode());
        product.setProductPrice(orderSaveVO.getExtPrice());
        product.setProductNum(1);
        product.setProductName("补餐");
        //添加商品信息
        orderSaveVO.getList().add(product);
        orderSaveVO.getOrderBase().setTitle("补单由供餐商随即分配菜品");
    }


    /**
     * 处理面对面收款的订单
     * @param dto
     * @param orderSaveVO
     */
    private void dealFaceOrderProductInfo(DataTransferObject dto,OrderSaveVO orderSaveVO) {
        if (!dto.checkSuccess()) {
            return;
        }
        OrderTypeEnum orderTypeEnum = OrderTypeEnum.getTypeByCode(orderSaveVO.getOrderBase().getOrderType());
        if (Check.NuNObj(orderTypeEnum)){
            dto.setErrorMsg("异常的订单类型");
            return;
        }
        OrderProductEntity product = new OrderProductEntity();
        product.setOrderSn(orderSaveVO.getOrderSn());
        product.setProductCode(0);
        product.setProductType(SupplierProductTypeEnum.EXR_PRODUCT.getCode());
        product.setProductPrice(orderSaveVO.getExtPrice());
        product.setProductNum(1);
        product.setProductName(orderTypeEnum.getName());
        //添加商品信息
        orderSaveVO.getList().add(product);
        orderSaveVO.getOrderBase().setTitle(orderTypeEnum.getName());
    }

    /**
     * 将当前的购物车 中的商品信息
     * @auhor afi
     * @param dto
     * @param orderSaveVO
     * @param cartList
     * @return
     */
    private void transAndCheckCart2Stock(DataTransferObject dto,OrderSaveVO orderSaveVO,List<CartVO> cartList){
        if (!dto.checkSuccess()) {
            return;
        }
        if (Check.NuNCollection(cartList)){
            dto.setErrorMsg("请选择商品");
            return;
        }

        // 去重当前的商品信息
        Map<String,StockWeekEntity>  stockMap = new HashMap<>();
        for (CartVO cartVO : cartList) {
            String key = StockUtil.getAppendString(cartVO.getProductCode(),cartVO.getSupplierProductType());
            if (stockMap.containsKey(key)){
                //当前的已经占用情况
                StockWeekEntity has = stockMap.get(key);
                has.setProductNum(has.getProductNum() + ValueUtil.getintValue(cartVO.getProductNum()));
            }else {
                StockWeekEntity stock = new StockWeekEntity();
                stock.setProductNum(ValueUtil.getintValue(cartVO.getProductNum()));
                stock.setProductCode(cartVO.getProductCode());
                stock.setSupplierProductType(cartVO.getSupplierProductType());
                stock.setProductName(cartVO.getProductName());
                stockMap.put(key,stock);
            }
        }
        // 当前订单的库存情况
        List<StockDbVO> list =new ArrayList<>();

        //商品列表
        List<Integer> listProduct =new ArrayList<>();

        //礼包列表
        List<Integer> listPackage =new ArrayList<>();

        List<StockWeekEntity> stockList =new ArrayList<>();
        //拼接当前的商品的库存信息
        for (StockWeekEntity stockWeekEntity : stockMap.values()) {
            StockDbVO vo = new StockDbVO();
            vo.setT(stockWeekEntity.getSupplierProductType());
            vo.setP(stockWeekEntity.getProductCode());
            vo.setN(stockWeekEntity.getProductNum());
            list.add(vo);
            stockWeekEntity.setSupplierCode(orderSaveVO.getOrderBase().getSupplierCode());
            stockWeekEntity.setWeek(WeekUtil.getWeek());
            stockWeekEntity.setOrderSn(orderSaveVO.getOrderBase().getOrderSn());
            stockWeekEntity.setOrderType(orderSaveVO.getOrderBase().getOrderType());
            stockList.add(stockWeekEntity);

            if (SupplierProductTypeEnum.PACKAGE.getCode() == stockWeekEntity.getSupplierProductType()){
                //礼包
                listPackage.add(stockWeekEntity.getProductCode());
            }else if (SupplierProductTypeEnum.PRODUCT.getCode() == stockWeekEntity.getSupplierProductType()){
                //礼包
                listProduct.add(stockWeekEntity.getProductCode());
            }
        }

        Map<String, StockCheckVO> mapAll = new HashMap<>();

        //获取商品库存
        if (!Check.NuNCollection(listProduct)){
            Map<String, StockCheckVO> map = stockProductManager.checkStockLimit(WeekUtil.getWeek(), SupplierProductTypeEnum.PRODUCT.getCode(), orderSaveVO.getOrderBase().getOrderType(), orderSaveVO.getOrderBase().getSupplierCode(), listProduct);
            for (String s : map.keySet()) {
                mapAll.put(StockUtil.getAppendString(ValueUtil.getintValue(s),SupplierProductTypeEnum.PRODUCT.getCode()),map.get(s));
            }
        }

        //获取礼包库存
        if (!Check.NuNCollection(listPackage)){
            Map<String, StockCheckVO> map = stockProductManager.checkStockLimit(WeekUtil.getWeek(), SupplierProductTypeEnum.PACKAGE.getCode(), orderSaveVO.getOrderBase().getOrderType(), orderSaveVO.getOrderBase().getSupplierCode(), listPackage);
            for (String s : map.keySet()) {
                mapAll.put(StockUtil.getAppendString(ValueUtil.getintValue(s),SupplierProductTypeEnum.PACKAGE.getCode()),map.get(s));
            }
        }

        //存储
        orderSaveVO.getOrderBase().setOrderJson(JsonEntityTransform.Object2JsonWithOutNULL(list));



        //获取当前的库存情况
        for (StockDbVO db : list) {
            String key = StockUtil.getAppendString(db.getP(),db.getT());
            if (!mapAll.containsKey(key)){
                dto.setErrorMsg("异常的库存信息");
                return;
            }
            StockCheckVO stock = mapAll.get(key);
            db.setL(stock.getProductLimit());
        }


        //提示信息
        StringBuffer sb = new StringBuffer();
        //获取当前的库存情况
        for (StockWeekEntity stockWeekEntity : stockList) {
            String key = StockUtil.getAppendString(stockWeekEntity.getProductCode(),stockWeekEntity.getSupplierProductType());
            if (!mapAll.containsKey(key)){
             dto.setErrorMsg("异常的库存信息");
             return;
            }
            StockCheckVO stock = mapAll.get(key);
            int last = stock.checkLast();
            int num = ValueUtil.getintValue(stockWeekEntity.getProductNum());
            if (num > last){
                if (Check.NuNStr(sb.toString())){
                    sb.append(stockWeekEntity.getProductName());
                }else {
                    sb.append(",");
                    sb.append(stockWeekEntity.getProductName());
                }
                sb.append("库存不足");
                sb.append(num);
                sb.append("份");
            }
        }

        if (!Check.NuNStr(sb.toString())){
            dto.setErrorMsg(sb.toString());
            return;
        }
        orderSaveVO.setStockList(stockList);
        orderSaveVO.setDbStockList(list);

    }






    /**
     * 处理当前的商品信息
     * @param dto
     * @param orderSaveVO
     * @param cartInfoVO
     */
    private void dealProductInfo(DataTransferObject dto,OrderSaveVO orderSaveVO,CartInfoVO cartInfoVO) {
        if (!dto.checkSuccess()) {
            return;
        }
        List<CartVO> cartList = cartInfoVO.getList();
        if (Check.NuNCollection(cartList)){
            dto.setErrorMsg("请选择商品");
            return;
        }

        //当前不能购买的商品
        List<OrderProductEntity> listOut = new ArrayList<>();

        Map<String,OrderProductVO> titleMap = new HashMap<>();

        //购物车中的商品
        for (CartVO vo : cartList) {
            String key = ValueUtil.getStrValue(vo.getProductCode());
            OrderProductEntity product = new OrderProductEntity();
            product.setOrderSn(orderSaveVO.getOrderSn());
            product.setProductCode(vo.getProductCode());
            product.setProductType(vo.getSupplierProductType());
            product.setProductPrice(vo.getProductPrice());
            product.setProductNum(vo.getProductNum());
            String name =vo.getProductName();
            if (vo.getSupplierProductType().equals(SupplierProductTypeEnum.PACKAGE.getCode())){
                //处理礼包
                name = supplierPackageManager.dealPackageTitle(name,vo.getProductCode(),titleMap,vo.getProductNum());
            }else {
                //处理普通商品
                if (titleMap.containsKey(key)){
                    OrderProductVO has = titleMap.get(key);
                    has.setProductNum(has.getProductNum() + vo.getProductNum());
                }else {
                    OrderProductVO has = new OrderProductVO();
                    has.setProductNum(vo.getProductNum());
                    has.setProductCode(vo.getProductCode());
                    has.setProductName(vo.getProductName());
                    has.setProductPrice(vo.getProductPrice());
                    titleMap.put(key,has);
                }
            }
            product.setProductName(name);
            //添加商品信息
            orderSaveVO.getList().add(product);

            if (vo.getOutFlag() == YesNoEnum.YES.getCode()){
                //当前的限制列表
                listOut.add(product);
            }
        }
        StringBuffer title = new StringBuffer();
        List<OrderProductVO> list = new ArrayList(titleMap.values());
        Collections.sort(list);
        int i = 0;
        if (!Check.NuNCollection(list)){
            for (OrderProductVO vo : list) {
                if (i>0){
                    title.append(",");
                }
                title.append(vo.getProductName());
                title.append("x");
                title.append(vo.getProductNum());
                i++;
            }
        }
        String titleStr = title.toString();
        if (titleStr.length()>256){
            titleStr = titleStr.substring(0,252) +"等";
        }
        orderSaveVO.getOrderBase().setTitle(titleStr);
        if (!Check.NuNCollection(listOut)){
            dto.setErrorMsg("购物车中存在过期菜品");
            return;
        }
        //设置当前的购物车的库存
        this.transAndCheckCart2Stock(dto,orderSaveVO,cartList);
    }


    /**
     * 处理当前的的下单的收货地址信息
     * @param dto
     * @param orderSaveVO
     */
    private void dealFaceAddressInfo(DataTransferObject dto, OrderSaveVO orderSaveVO){
        if (!dto.checkSuccess()){
            return;
        }

        //基本的订单信息
        OrderEntity order =orderSaveVO.getOrderBase();
        //收货地址
        order.setAddress("");
        order.setIsSelf(YesNoEnum.YES.getCode());
    }


    /**
     * 处理当前的的下单的收货地址信息
     * @param dto
     * @param orderSaveVO
     * @param createOrderRequest
     */
    private void dealUserAddressInfo(DataTransferObject dto, OrderSaveVO orderSaveVO, CreateOrderRequest createOrderRequest, boolean createFlag){
        if (!dto.checkSuccess()){
            return;
        }

        if (createFlag && Check.NuNStr(createOrderRequest.getAddressFid())){
            dto.setErrorMsg("请选择收货地址");
            return;
        }
        if (Check.NuNStr(createOrderRequest.getAddressFid())){
            return;
        }

        EnterpriseAddressEntity addressEntity = enterpriseManager.getEnterpriseAddressByFid(createOrderRequest.getAddressFid());
        if (Check.NuNObj(addressEntity)){
            dto.setErrorMsg("当前收货地址不存在");
            return;
        }
        //基本的订单信息
        OrderEntity order =orderSaveVO.getOrderBase();
        //收货地址
        order.setAddressFid(createOrderRequest.getAddressFid());
        order.setAddress(addressEntity.getAddress());
        order.setIsSelf(addressEntity.getIsSelf());
    }






    /**
     * 处理当前的的下单用户信息
     * @param dto
     * @param createOrderRequest
     */
    private void dealBusinessInfo(DataTransferObject dto,OrderSaveVO orderSaveVO,CreateOrderRequest createOrderRequest){
        if (!dto.checkSuccess()){
            return;
        }
        String tmpSupplierCode = null;
        if (!Check.NuNStr(createOrderRequest.getPayCode())){
            UserEntity userEntity = userManager.getByPayCode(createOrderRequest.getPayCode());
            if (Check.NuNObj(userEntity)){
                dto.setErrorMsg("异常的收款码");
                return;
            }
            orderSaveVO.getOrderBase().setKnightUid(userEntity.getUserUid());
            tmpSupplierCode = userEntity.getBizCode();
        }else {
            tmpSupplierCode = createOrderRequest.getBusinessUid();
            AppScanTypeEnum typeEnum =AppScanTypeEnum.transScanType(tmpSupplierCode);
            if (Check.NuNObj(typeEnum)){
                dto.setErrorMsg("异常的收款码");
                return;
            }
            if (typeEnum.getCode().equals(AppScanTypeEnum.QISHOU_PAY.getCode())){
                UserEntity userEntity = userManager.getByPayCode(typeEnum.parseScanCode(createOrderRequest.getBusinessUid()));
                if (Check.NuNObj(userEntity)){
                    dto.setErrorMsg("异常的收款码");
                    return;
                }
                orderSaveVO.getOrderBase().setKnightUid(userEntity.getUserUid());
                tmpSupplierCode = userEntity.getBizCode();
            }
        }

        if (Check.NuNStr(tmpSupplierCode)){
            tmpSupplierCode = ValueUtil.getStrValue(tmpSupplierCode);
        }

        SupplierEntity supplier = supplierManager.getSupplierByCode(tmpSupplierCode);
        if (Check.NuNObj(supplier)){
            dto.setErrorMsg("当前商家不存在");
            return;
        }
        SupplierStatusEnum statusEnum = SupplierStatusEnum.getTypeByCode(supplier.getSupplierStatus());
        if (Check.NuNObj(statusEnum)){
            dto.setErrorMsg("异常的用户状态");
            return;
        }
        if (statusEnum.getCode() != UserStatusEnum.AVAILABLE.getCode()){
            dto.setErrorMsg("当前上架已经下架,请联系平台");
            return;
        }
        //设置当前的供应商code
        orderSaveVO.getOrderBase().setSupplierCode(supplier.getSupplierCode());
        //设置商家信息
        orderSaveVO.getOrderBase().setBusinessUid(supplier.getSupplierCode());
        orderSaveVO.setSupplierName(supplier.getSupplierName());

        //设置当前供应商的限制数量
        orderSaveVO.setLimitCell(ValueUtil.getintValue(supplier.getLimitCell()));
    }


    /**
     * 处理当前的的下单用户信息
     * @param dto
     * @param createOrderRequest
     */
    private void dealUserInfo(DataTransferObject dto,OrderSaveVO orderSaveVO,CreateOrderRequest createOrderRequest){
        dealUserInfo(dto,orderSaveVO,createOrderRequest,false);
    }

    /**
     * 处理当前的的下单用户信息
     * @param dto
     * @param createOrderRequest
     */
    private void dealUserInfo(DataTransferObject dto,OrderSaveVO orderSaveVO,CreateOrderRequest createOrderRequest,boolean face){
        if (!dto.checkSuccess()){
            return;
        }
        UserEntity userEntity = userManager.getUserByUid(createOrderRequest.getUserUid());
        if (Check.NuNObj(userEntity)){
            dto.setErrorMsg("当前用户不存在");
            return;
        }
        UserStatusEnum statusEnum = UserStatusEnum.getTypeByCode(userEntity.getUserStatus());
        if (Check.NuNObj(statusEnum)){
            dto.setErrorMsg("异常的用户状态");
            return;
        }


        if (statusEnum.getCode() == UserStatusEnum.AVAILABLE.getCode()){
            dto.setErrorMsg("当前用户未激活,请联系注册激活");
            return;
        }
        if (statusEnum.getCode() != UserStatusEnum.ACTIVITY.getCode()){
            dto.setErrorMsg("当前用户已经被冻结,请联系平台处理");
            return;
        }
        UserVO user =new UserVO();
        BeanUtils.copyProperties(userEntity,user);
        //当前的用户信息
        orderSaveVO.setUser(user);
        orderSaveVO.getOrderBase().setUserName(userEntity.getUserName());
        orderSaveVO.getOrderBase().setUserTel(userEntity.getUserPhone());
        orderSaveVO.getOrderBase().setUserUid(userEntity.getUserUid());
        orderSaveVO.getOrderBase().setUserCode(userEntity.getUserCode());
        UserAccountEntity accountEntity = userManager.fillAndGetAccountUser(createOrderRequest.getUserUid());
        if (Check.NuNObj(accountEntity)
                || Check.NuNStr(accountEntity.getAccountPassword())){
            //默认设置了密码,当前为设置密码
            user.setPwdFlag(false);
        }

        //设置当前的免密属性
        if (ValueUtil.getintValue(userEntity.getIsPwd()) == YesNoEnum.YES.getCode()){
            user.setFreePwd(true);
        }
        //设置当前的用户关联的企业的用餐信息
        this.dealEnterpriseInfo( dto, orderSaveVO,userEntity, createOrderRequest,face);
    }


    /**
     * 处理企业信息
     * @author afi
     * @param dto
     * @param orderSaveVO
     * @param userEntity
     * @param createOrderRequest
     */
    private void dealEnterpriseInfo(DataTransferObject dto,OrderSaveVO orderSaveVO,UserEntity userEntity,CreateOrderRequest createOrderRequest,boolean face){
        if (!dto.checkSuccess()){
            return;
        }
        EnterpriseInfoVO infoVO = enterpriseManager.getEnterpriseInfoByCode(userEntity.getEnterpriseCode());
        if (Check.NuNObj(infoVO)){
            dto.setErrorMsg("异常的企业信息");
            return;
        }

        if (Check.NuNObj(infoVO.getEnterpriseEntity().getTillTime())){
            dto.setErrorMsg("异常的企业截止时间");
            return;
        }
        if (infoVO.getEnterpriseEntity().getTillTime().before(orderSaveVO.getNow())){
            dto.setErrorMsg("加盟时间已经失效,请联系企业管理人员");
            return;
        }

        //设置城市code
        orderSaveVO.getOrderBase().setProvinceCode(infoVO.getEnterpriseEntity().getProvinceCode());
        orderSaveVO.getOrderBase().setCityCode(infoVO.getEnterpriseEntity().getCityCode());
        orderSaveVO.getOrderBase().setAreaCode(infoVO.getEnterpriseEntity().getAreaCode());
        orderSaveVO.getOrderBase().setEnterpriseCode(infoVO.getEnterpriseEntity().getEnterpriseCode());

        if (face){
            //面对面收款直接返回
            return;
        }

        EnterpriseConfigEntity config =infoVO.getEnterpriseConfigEntity();
        if(Check.NuNObj(config)){
            dto.setErrorMsg("异常的企业配置信息");
            return;
        }
        //获取当前的订餐类型
        OrderTypeEnum orderTypeEnum = OrderTypeEnum.getTypeByCode(createOrderRequest.getOrderType());
        if (Check.NuNObj(orderTypeEnum)){
            dto.setErrorMsg("异常的订餐类型");
            return;
        }

        //地址
        List<EnterpriseAddressEntity> list = enterpriseManager.getEnterpriseAddressByCode(userEntity.getEnterpriseCode());
        if (!Check.NuNCollection(list)){
            for (EnterpriseAddressEntity entity : list) {
                entity.setConTel(userEntity.getUserPhone());
                entity.setConName(userEntity.getUserName());
            }
            orderSaveVO.setAddressList(list);
        }
        if (orderTypeEnum.isExt()){
            //补餐
            int extPrice = this.getExtprice(dto,userEntity,orderTypeEnum,config);
            orderSaveVO.setExtPrice(extPrice);
            return;
        }
        //校验当前时间
        this.checkTimeOk(dto,orderTypeEnum,config,orderSaveVO.getNow());
    }

    /**
     * 校验当前的时间是否有效
     * @param dto
     * @param orderTypeEnum
     * @param config
     */
    private void checkTimeOk(DataTransferObject dto,OrderTypeEnum orderTypeEnum,EnterpriseConfigEntity config ,Date time){
        checkTimeOk(dto, orderTypeEnum, config, time,null);
    }

    /**
     * 校验当前的时间是否有效
     * @param dto
     * @param orderTypeEnum
     * @param config
     */
    private void checkTimeOk(DataTransferObject dto,OrderTypeEnum orderTypeEnum,EnterpriseConfigEntity config ,Date time,String notice){
        if (!dto.checkSuccess()){
            return;
        }
        // 一下是正常的订单时间的限制
        if (Check.NuNObj(time)){
            time = new Date();
        }
        String limtStart = null;
        String limtEnd = null;
        if (orderTypeEnum.getCode() == OrderTypeEnum.LUNCH_COMMON.getCode()){
            //正常午餐
            limtStart = config.getLunchStart();
            limtEnd = config.getLunchEnd();
        }else if (orderTypeEnum.getCode() == OrderTypeEnum.DINNER_COMMON.getCode()){
            //正常晚餐
            limtStart = config.getDinnerStart();
            limtEnd = config.getDinnerEnd();
        }
        if (Check.NuNStr(limtStart)
                || Check.NuNStr(limtEnd)){
            dto.setErrorMsg("异常的供餐时间");
            return;
        }

        Date start = DateUtil.connectDate(time,limtStart);
        Date end = DateUtil.connectDate(time,limtEnd);
        if (time.after(start) && time.before(end)){
            //时间正常
        }else {
            if (Check.NuNStr(notice)){
                notice = "请在" + DateUtil.timestampFormat(start) +" 至 " + DateUtil.timestampFormat(end) +" 时间内完成订餐";
            }
            dto.setErrorMsg(notice);
        }
    }



    /**
     * 获取当前的补餐价格
     * @author afi
     * @param orderTypeEnum
     * @param config
     * @return
     */
    private int getExtprice(DataTransferObject dto,UserEntity userEntity,OrderTypeEnum orderTypeEnum,EnterpriseConfigEntity config){
        int extPrice = 0;
        if (!dto.checkSuccess()){
            return extPrice;
        }
        UserRoleEnum userRoleEnum =UserRoleEnum.getTypeByCode(userEntity.getUserRole());
        if (Check.NuNObj(userRoleEnum)){
            dto.setErrorMsg("异常的用户类型");
            return extPrice;
        }

        if (orderTypeEnum.getCode() == OrderTypeEnum.LUNCH_EXT.getCode()){
            //午餐补单
            extPrice = this.getRealPrice(userRoleEnum,ValueUtil.getintValue(config.getBossPrice()),ValueUtil.getintValue(config.getEmpPrice()));
        }else if (orderTypeEnum.getCode() == OrderTypeEnum.DINNER_EXT.getCode()){
            //晚餐补单
            extPrice = this.getRealPrice(userRoleEnum,ValueUtil.getintValue(config.getBossPrice()),ValueUtil.getintValue(config.getEmpPrice()));
        }else {
            dto.setErrorMsg("异常的补单类型");
        }
        //返回补餐金额
        return extPrice;
    }


    /**
     * 获取当前用户的费用
     * @author afi
     * @param userRoleEnum
     * @param bossPrice
     * @param empPrice
     * @return
     */
    private int getRealPrice(UserRoleEnum userRoleEnum,int bossPrice,int empPrice){
        int price = 0;
        if (userRoleEnum.getCode() == UserRoleEnum.BOSS.getCode()){
            price = bossPrice;
        }else {
            price = empPrice;
        }
        return price;
    }

    @Override
    public DataTransferObject<OrderEntity> getOrderBaseBySn(String orderSn){
        DataTransferObject<OrderEntity> dto = new DataTransferObject<>();
        if (Check.NuNStr(orderSn)){
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try{
            OrderEntity orderBaseBySn = orderManager.getOrderBaseBySn(orderSn);
            dto.setData(orderBaseBySn);
        }catch (Exception e){
            LogUtil.error(LOGGER,"根据订单号查询详情异常param{}",orderSn);
            dto.setErrorMsg("根据订单号查询详情异常");
            return dto;
        }
        return dto;
    }
}
