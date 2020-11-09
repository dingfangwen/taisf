package com.taisf.services.order.manager;

import com.google.inject.internal.util.$ObjectArrays;
import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.exception.BusinessException;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.SnUtil;
import com.jk.framework.base.utils.ValueUtil;
import com.taisf.services.common.valenum.AccountTypeEnum;
import com.taisf.services.common.valenum.OrdersStatusEnum;
import com.taisf.services.common.valenum.RecordPayTypeEnum;
import com.taisf.services.enterprise.dto.EnterpriseListRequest;
import com.taisf.services.enterprise.vo.EnterpriseOrderStatsVO;
import com.taisf.services.enterprise.vo.SupOrderStatsVO;
import com.taisf.services.order.dao.*;
import com.taisf.services.order.dto.*;
import com.taisf.services.order.entity.*;
import com.taisf.services.order.vo.*;
import com.taisf.services.pay.dao.PayRecordDao;
import com.taisf.services.pay.entity.PayRecordEntity;
import com.taisf.services.pay.manager.PayManagerImpl;
import com.taisf.services.refund.constants.RefundStatusEnum;
import com.taisf.services.refund.dao.RefundDao;
import com.taisf.services.refund.entity.RefundEntity;
import com.taisf.services.stock.dao.StockWeekDao;
import com.taisf.services.stock.entity.StockWeekEntity;
import com.taisf.services.user.dao.AccountLogDao;
import com.taisf.services.user.dao.UserAccountDao;
import com.taisf.services.user.entity.AccountLogEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


/**
 * <p>订单先关操作</p>
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
@Service("order.orderManagerImpl")
public class OrderManagerImpl {

	@Resource(name = "order.orderBaseDao")
	private OrderBaseDao orderBaseDao;


	@Resource(name = "order.orderBuffetDao")
	private OrderBuffetDao orderBuffetDao;

	@Resource(name = "refund.refundDao")
	private RefundDao refundDao;


	@Resource(name = "order.orderLogDao")
	private OrderLogDao orderLogDao;


	@Resource(name = "order.orderMoneyDao")
	private OrderMoneyDao orderMoneyDao;


	@Resource(name = "order.payDao")
	private OrderPayDao orderPayDao;

	@Resource(name = "order.orderProductDao")
	private OrderProductDao orderProductDao;


	@Resource(name = "user.accountLogDao")
	private AccountLogDao accountLogDao;

	@Resource(name = "user.accountUserDao")
	private UserAccountDao userAccountDao;

	@Resource(name = "order.orderInfoDao")
	private OrderInfoDao orderInfoDao;

	@Resource(name = "pay.payRecordDao")
	private PayRecordDao payRecordDao;

	@Resource(name = "stock.stockWeekDao")
	private StockWeekDao stockWeekDao;


	@Resource(name = "pay.payManagerImpl")
	private PayManagerImpl payManager;

	/**
	 * 获取当前的待取消的定点列表
	 * @author afi
	 * @return
	 */
	public List<OrderEntity> getOrder2Canceled(){
		return orderBaseDao.getOrder2Canceled();
	}


	/**
	 * 获取企业订单的统计信息
	 * @author afi
	 * @param request
	 * @return
	 */
	public List<EnterpriseOrderStatsVO> getEnterpriseOrderStats(EnterpriseStatsRequest request){

		return orderInfoDao.getEnterpriseOrderStats(request);
	}

	/**
	 * 获取企业订单的统计信息
	 * @author afi
	 * @param request
	 * @return
	 */
	public List<SupOrderStatsVO> getSupOrderStats(SupStatsRequest request){
		return orderInfoDao.getSupOrderStats(request);
	}


	/**
	 * 取消订单
	 * @param order
	 * @param list
	 */
	public  void cancelOrder(OrderEntity order,List<StockWeekEntity> list,List<PayRecordEntity> payRecordList,Integer refundMoney){
		int count = orderBaseDao.cancelOrder(order.getOrderSn(),order.getOrderStatus());
		if (count == 1){

			if (!Check.NuNCollection(payRecordList)){
				dealRefund4part(order, payRecordList,refundMoney);
			}
			if (!Check.NuNCollection(list)){
				stockWeekDao.batchSaveStockWeek(list);
			}
		}
	}

	/**
	 * 退款
	 * @param order
	 * @param payRecordList
	 */
	private void dealRefund4part(OrderEntity order, List<PayRecordEntity> payRecordList,Integer refundMoney) {
		if (Check.NuNObj(refundMoney)){
			dealRefund(order,payRecordList);
			return;
		}
		int last = ValueUtil.getintValue(refundMoney);
		//设置部分退款
		Map<String,PayRecordEntity> payMap = this.trans2Map(payRecordList);
		if (last >0){
			last = this.partRefundEle(order,payMap,RecordPayTypeEnum.YUE.getCode(),last);
		}
		if (last >0){
			last =this.partRefundEle(order,payMap,RecordPayTypeEnum.ZHIFUBAO.getCode(),last);
		}

		if (last >0){
			last =this.partRefundEle(order,payMap,RecordPayTypeEnum.WEIXIN.getCode(),last);
		}
	}

	/**
	 * 处理部分退款
	 * @param payMap
	 * @param recordPayType
	 * @param last
	 * @return
	 */
	private int  partRefundEle(OrderEntity order,Map<String,PayRecordEntity> payMap,int recordPayType,int last ){
		if (last >0){
			PayRecordEntity yue = payMap.get(recordPayType + "");
			if (Check.NuNObj(yue)){
				int payYue = yue.getTotalFee();
				if (last > payYue){
					last = last - payYue;
				}else {
					yue.setTotalFee(last);
					last = 0;
				}
				this.dealRealReFund(order,yue);
			}
		}
		return last;
	}

	/**
	 * 将当前的支付记录转化
	 * @param payRecordList
	 * @return
	 */
	private Map<String,PayRecordEntity> trans2Map(List<PayRecordEntity> payRecordList){
		Map<String,PayRecordEntity> map = new HashMap<>();
		if (Check.NuNCollection(payRecordList)){
			return map;
		}
		for (PayRecordEntity payRecordEntity : payRecordList) {
			map.put(payRecordEntity.getPayType()+"",payRecordEntity);
		}
		return map;

	}



	/**
	 * 退款
	 * @param order
	 * @param payRecordList
	 */
	private void dealRefund(OrderEntity order, List<PayRecordEntity> payRecordList) {
		for (PayRecordEntity payRecord : payRecordList) {
			dealRealReFund(order, payRecord);
		}
	}

	/**
	 * 做真正的退款
	 * @param order
	 * @param payRecord
	 */
	private void dealRealReFund(OrderEntity order, PayRecordEntity payRecord) {
		//生成退款
		RefundEntity entity = new RefundEntity();
		entity.setSourceType(order.getOrderSource());
		entity.setOrderSn(order.getOrderSn());
		entity.setCardNo(payRecord.getTradeNo());
		entity.setCardType(payRecord.getPayType());
		entity.setRecordId(payRecord.getId());
		entity.setRefundFee(payRecord.getTotalFee());
		entity.setPayFee(payRecord.getTotalFee());
		entity.setRefundName(order.getUserName());
		entity.setRefundSn(SnUtil.getRefundSn());
		entity.setRefundUid(order.getUserUid());
		entity.setRefundStatus(RefundStatusEnum.PASS.getCode());
		entity.setSupplierCode(order.getSupplierCode());
		int row =  refundDao.saveRefund(entity);
	}


	/**
	 * 申请退款
	 * @param order
	 * @param payRecordList
	 */
	public void refundOrder(OrderEntity order, List<PayRecordEntity> payRecordList, List<StockWeekEntity> list){
		if (Check.NuNCollection(payRecordList)){
			throw new BusinessException("异常的支付信息:"+order.getOrderSn());
		}
		int count = orderBaseDao.refundOrder(order.getOrderSn(),order.getOrderStatus());
		if (count == 1){
			//退款
			dealRefund(order, payRecordList);
			if (!Check.NuNCollection(list)){
				stockWeekDao.batchSaveStockWeek(list);
			}
		}
	}

	/**
	 * 将当前的订单放入餐格
	 * @param buffetInOrderRequest
	 * @param base
	 */
	public  void buffetInOrder(BuffetInOrderRequest buffetInOrderRequest,OrderEntity base){
		int count = orderBaseDao.buffetInOrder(base.getOrderSn(),base.getOrderStatus(),buffetInOrderRequest.getOpId());
		if (count == 1){

			OrderBuffetEntity entity = new OrderBuffetEntity();
			entity.setOrderSn(base.getOrderSn());
			entity.setBuffetFid(buffetInOrderRequest.getBuffetFid());
			entity.setCellSn(buffetInOrderRequest.getCellSn());
			orderBuffetDao.saveOrderBuffet(entity);

			OrderLogEntity log = new OrderLogEntity();
			log.setOrderSn(base.getOrderSn());
			log.setFromStatus(base.getOrderStatus());
			log.setToStatus(OrdersStatusEnum.SEND_BUFFET.getCode());
			log.setCreateId(buffetInOrderRequest.getOpId());
			log.setCreateDate(new Date());
			log.setTitle("放入餐格");
			orderLogDao.insertOrderLog(log);
		}
	}


	/**
	 * 从餐柜取餐
	 * @param buffetOutOrderRequest
	 * @param base
	 */
	public  int buffetOutOrder(BuffetOutOrderRequest buffetOutOrderRequest,OrderEntity base,String title){
		int count = orderBaseDao.buffetOutOrder(base.getOrderSn(),base.getOrderStatus());
		if (count == 1){
			OrderLogEntity log = new OrderLogEntity();
			log.setOrderSn(base.getOrderSn());
			log.setFromStatus(base.getOrderStatus());
			log.setToStatus(OrdersStatusEnum.RECEIVE.getCode());
			log.setCreateId(buffetOutOrderRequest.getOpId());
			log.setCreateDate(new Date());
			if (Check.NuNStr(title)){
				title = "餐柜取餐";
			}
			log.setTitle(title);
			orderLogDao.insertOrderLog(log);
		}
		return count;
	}




	/**
	 * 结束当前订单
	 * @param finishOrderRequest
	 * @param base
	 */
	public  void finishOrder(FinishOrderRequest finishOrderRequest,OrderEntity base){
		int count = orderBaseDao.finishOrder(base.getOrderSn(),base.getOrderStatus(),finishOrderRequest.getOpId());
		if (count == 1){
			OrderLogEntity log = new OrderLogEntity();
			log.setOrderSn(base.getOrderSn());
			log.setFromStatus(base.getOrderStatus());
			log.setToStatus(OrdersStatusEnum.RECEIVE.getCode());
			log.setCreateId(finishOrderRequest.getOpId());
			log.setCreateDate(new Date());
			log.setTitle("配送完成");
			orderLogDao.insertOrderLog(log);
		}
	}


	/**
	 * 获取当前用户的待完成订单
	 * @param userUid
	 * @return
	 */
	public List<OrderInfoVO> getOrderInfoWaitingList(String userUid){
		//获取列表
		List<OrderInfoVO> waitingList = orderInfoDao.getOrderInfoWaitingList(userUid);
		if (Check.NuNCollection(waitingList)){
			return waitingList;
		}
		for (OrderInfoVO infoVO : waitingList) {
			infoVO.setList(orderProductDao.getOrderProductByOrderSn(infoVO.getOrderSn()));
		}
		return waitingList;
	}




	/**
	 * 获取当前订单的信息
	 * @author afi
	 * @param orderInfoRequest
	 * @return
	 */
	public List<OrderInfoVO> getOrderInfoPageCurrent(OrderInfoRequest orderInfoRequest){
		//分页获取信息
		List<OrderInfoVO> list = orderInfoDao.getOrderInfoPageCurrent(orderInfoRequest);
		if (Check.NuNCollection(list)){
			return list;
		}

		//拼装商品list
		this.fillOrderPro(list);

		return list;
	}


	/**
	 * 填充商品
	 * @param list
	 */
	private void fillOrderPro(List<OrderInfoVO> list){
		if (Check.NuNCollection(list)){
			return;
		}
		List<String> sns = new ArrayList<>();
		for (OrderInfoVO orderInfoVO : list) {
			sns.add(orderInfoVO.getOrderSn());
		}
		Map<String,List<OrderProductEntity>>  map = this.trans2ProMap(orderProductDao.getOrderProductByOrderSnList(sns));
		for (OrderInfoVO orderInfoVO : list) {
			orderInfoVO.setList(map.get(orderInfoVO.getOrderSn()));
		}
	}

	/**
	 * 转化当前的map
	 * @param productByOrderSnList
	 * @return
	 */
	private Map<String,List<OrderProductEntity>> trans2ProMap(List<OrderProductEntity> productByOrderSnList){
		Map<String,List<OrderProductEntity>> map = new HashMap<>();
		if (Check.NuNCollection(productByOrderSnList)){
			return map;
		}
		for (OrderProductEntity orderProductEntity : productByOrderSnList) {
			String key = orderProductEntity.getOrderSn();
			if (!map.containsKey(key)){
				map.put(key,new ArrayList<>());
			}
			map.get(key).add(orderProductEntity);
		}
		return map;
	}


	/**
	 * 获取当前订单的信息
	 * @author afi
	 * @param orderInfoRequest
	 * @return
	 */
	public PagingResult<OrderInfoVO> getOrderInfoPage(OrderInfoRequest orderInfoRequest){
		//分页获取信息
		PagingResult<OrderInfoVO> pagingResult = orderInfoDao.getOrderInfoPage(orderInfoRequest);
		if (Check.NuNObj(pagingResult)){
			pagingResult = new PagingResult<>();
		}
		if (Check.NuNCollection(pagingResult.getList())){
			return pagingResult;
		}

		for (OrderInfoVO infoVO : pagingResult.getList()) {
			infoVO.setList(orderProductDao.getOrderProductByOrderSn(infoVO.getOrderSn()));
		}
		return pagingResult;
	}

	public PagingResult<OrderInfoVO> getOrderListPageByEnterprisCode(OrderInfoRequest orderInfoRequest){
		//分页获取信息
		PagingResult<OrderInfoVO> pagingResult = orderInfoDao.getOrderListPageByEnterprisCode(orderInfoRequest);
		if (Check.NuNObj(pagingResult)){
			pagingResult = new PagingResult<>();
		}
		if (Check.NuNCollection(pagingResult.getList())){
			return pagingResult;
		}
		for (OrderInfoVO infoVO : pagingResult.getList()) {
			infoVO.setList(orderProductDao.getOrderProductByOrderSn(infoVO.getOrderSn()));
		}
		return pagingResult;
	}


	/**
	 * 获取全量的订单列表
	 * @param orderInfoRequest
	 * @return
	 */
	public List<OrderInfoVO> listOrder(OrderInfoRequest orderInfoRequest){
		return orderInfoDao.listOrder(orderInfoRequest);
	}




	/**
	 * @author:zhangzhengguang
	 * @date:2017/10/16
	 * @description:分页查询订单列表
	 **/
	public PagingResult<OrderInfoVO> pageListOrder(OrderInfoRequest orderInfoRequest){
		PagingResult<OrderInfoVO> pagingResult = orderInfoDao.pageListOrder(orderInfoRequest);
		return pagingResult;
	}

	/**
	 * @author:zhangzhengguang
	 * @date:2018/2/28
	 * @description:订单查询导出
	 **/
	public List<OrderExcelVO> listOrderExcel(OrderInfoRequest orderInfoRequest){
		List<OrderExcelVO> orderExcelVOList = orderInfoDao.listOrderExcel(orderInfoRequest);

		List<String> orderSnList = orderExcelVOList.stream().map(OrderExcelVO::getOrderSn).collect(Collectors.toList());

		List<OrderProductEntity> orderProductEntityList = orderProductDao.getOrderProductListByOrderSn(orderSnList);
		Map<String, List<OrderProductEntity>> resultMap = orderProductEntityList.stream().collect(Collectors.groupingBy(OrderProductEntity::getOrderSn));

		List<PayRecordEntity> payRecordByOrderSnList = payManager.getPayRecordByOrderSnList(orderSnList);
		Map<String, List<PayRecordEntity>> payRecordByOrderSnMap = payRecordByOrderSnList.stream().collect(Collectors.groupingBy(PayRecordEntity::getOrderSn));

		for (OrderExcelVO orderExcelVO : orderExcelVOList) {
			List<OrderProductEntity> list = resultMap.get(orderExcelVO.getOrderSn());
			if(!Check.NuNCollection(list)){
				String str = "";
				for (OrderProductEntity orderProductEntity : list) {
						str += orderProductEntity.getProductName()+"*"+orderProductEntity.getProductNum()+",\r\n";
				}
				orderExcelVO.setOrderProduct(str.substring(0,str.length()-3));
			}
			List<PayRecordEntity> payRecordEntities = payRecordByOrderSnMap.get(orderExcelVO.getOrderSn());
			if(!Check.NuNCollection(payRecordEntities)){
				StringBuilder sb = new StringBuilder();
				for (PayRecordEntity payRecordEntity : payRecordEntities) {
					sb.append(RecordPayTypeEnum.getTypeByCode(payRecordEntity.getPayType()).getName());
				}
				orderExcelVO.setPayTypeStr(sb.toString());
			}
		}
		return orderExcelVOList;
	}


	/**
	 * @author:zhangzhengguang
	 * @date:2017/10/18
	 * @description:企业订单配送
	 **/
	public PagingResult<OrderSendStatsVo> finOrderDistributionList(EnterpriseListRequest enterpriseListRequest){
		PagingResult<OrderSendStatsVo> orderListVoPagingResult = orderInfoDao.finOrderDistributionList(enterpriseListRequest);
		return orderListVoPagingResult;
	}

	/**
	 * @author:zhangzhengguang
	 * @date:2017/10/17
	 * @description:分页查询订单详情商品列表
	 **/
	public PagingResult<OrderProductListVO>  getOrderProductPageList(OrderProductListRequest orderProductListRequest){
		PagingResult<OrderProductListVO> orderProductPageList = orderProductDao.getOrderProductPageList(orderProductListRequest);
		return orderProductPageList;
	}

	/**
	 * 获取当前的统计情况
	 * @param request
	 * @return
	 */
	public List<DayTaskVO>  getEverydayTaskPgeList(DayTaskRequest request){
		List<DayTaskVO> list = orderProductDao.getEverydayTaskPgeList(request);
		return list;
	}

	/**
	 * @author:zhangzhengguang
	 * @date:2017/10/18
	 * @description:修改订单状态根据企业编号
	 **/
	public void sendByEnterpriseCode(OrderEntity orderEntity){
		orderInfoDao.sendByEnterpriseCode(orderEntity);
	}

	/**
	 * @author:zhangzhengguang
	 * @date:2017/10/19
	 * @description:据企业code查询企业下所有待配送订单
	 **/
	public PagingResult<OrderEntity>  findListByEnterpriseCode(OrderInfoRequest orderInfoRequest){
		PagingResult<OrderEntity> listByEnterpriseCode = orderInfoDao.findListByEnterpriseCode(orderInfoRequest);
		return listByEnterpriseCode;
	}

	/**
	 * @author:zhangzhengguang
	 * @date:2017/10/20
	 * @description:配送记录
	 **/
	public PagingResult<OrderListVo>  findPageLsit(EnterpriseListRequest enterpriseListRequest){
		PagingResult<OrderListVo> pagingResult = orderInfoDao.findPageLsit(enterpriseListRequest);
		return pagingResult;
	}



	/**
	 * 保存订单待DB
	 * @author afi
	 * @param saveVO
	 */
	public void saveOrderSave(OrderSaveVO saveVO){
		if (Check.NuNObj(saveVO)){
			throw new BusinessException("保存订单参数为空");
		}
		// 保存订单基本信息
		OrderEntity orderBase = saveVO.getOrderBase();
		int num = orderBaseDao.saveOrderBase(orderBase);
		if (num  != 1){
			throw new BusinessException("保存订单基本信息失败");
		}
		// 保存订单金额信息
		OrderMoneyEntity orderMoney = saveVO.getOrderMoney();
		orderMoney.setUid(orderBase.getUserUid());
		int numM=orderMoneyDao.saveOrderMoney(orderMoney);
		if (numM  != 1){
			throw new BusinessException("保存订单金鹅信息失败");
		}
		//保存当前的订单商品信息
		List<OrderProductEntity> list = saveVO.getList();
		orderProductDao.batchSaveOrderProduct(list);

		//保存当前的订单的库存信息
		if (!Check.NuNCollection(saveVO.getStockList())){
			stockWeekDao.batchSaveStockWeek(saveVO.getStockList());
		}
		int balanceCost = ValueUtil.getintValue(orderMoney.getPayBalance());
		if (balanceCost > 0){
			//如果消费余额大于0,直接消费余额
			this.costUserBalanceByOrderSn(saveVO.getUser().getUserUid(),saveVO.getOrderSn(),balanceCost);

			//保存支付记录
			PayRecordEntity record = new PayRecordEntity();
			record.setRecordUid(saveVO.getUser().getUserUid());
			record.setNeedMoney(balanceCost);
			record.setOrderSn(saveVO.getOrderSn());
			record.setPayTime(new Date());
			record.setPayType(RecordPayTypeEnum.YUE.getCode());
			record.setTotalFee(balanceCost);
			record.setTradeNo(saveVO.getOrderSn());
			record.setPayCode(DataTransferObject.SUCCESS +"");
			payRecordDao.savePayRecord(record);
		}


	}

	/**
	 * 消费当前的余额信息
	 * @author afi
	 * @param userUid
	 * @param orderSn
	 * @param money
	 */
	private void costUserBalanceByOrderSn(String userUid,String orderSn,int money){

		//消费当前的余额信息
		userAccountDao.changeUserBalance(userUid,-money);
		//记录当前的消费记录
		AccountLogEntity log = new AccountLogEntity();
		log.setAccountType(AccountTypeEnum.CONSUME.getCode());
		log.setBizMoney(-money);
		log.setBizSn(orderSn);
		log.setUserId(userUid);
		log.setTitle("下单消费");
		accountLogDao.saveAccountLog(log);
	}

	/**
	 * 获取当前的订单金额信息
	 * @author afi
	 * @param orderSn
	 * @return
	 */
	public OrderMoneyEntity getOrderMoneyByOrderSn(String orderSn){
		if (Check.NuNStr(orderSn)){
			return null;
		}
		return orderMoneyDao.getOrderMoneyByOrderSn(orderSn);
	}


	/**
	 * 获取当前的订单基本信息
	 * @author afi
	 * @param orderSn
	 * @return
	 */
	public OrderEntity getOrderBaseBySn(String orderSn){
		if (Check.NuNStr(orderSn)){
			return null;
		}
		return orderBaseDao.getOrderBaseByOrderSn(orderSn);
	}



	/**
	 * 获取当前的订单信息
	 * @author afi
	 * @param orderSn
	 * @return
	 */
	public OrderInfoVO getOrderInfoByOrderSn(String orderSn){
		if (Check.NuNStr(orderSn)){
			return null;
		}
		return orderInfoDao.getOrderInfoByOrderSn(orderSn);
	}


	/**
	 * 获取当前的订单信息
	 * @author afi
	 * @param orderSn
	 * @return
	 */
	public OrderDetailVO getOrderDetailBySn(String orderSn){
		if (Check.NuNStr(orderSn)){
			return null;
		}

		OrderDetailVO orderDetailVO = new OrderDetailVO();
		//订单基本信息
		OrderEntity orderEntity = orderBaseDao.getOrderBaseByOrderSn(orderSn);
		orderDetailVO.setOrderEntity(orderEntity);
		if (Check.NuNObj(orderEntity)){
			return null;
		}
		//订单的支付信息
		OrderPayEntity orderPayEntity = orderPayDao.getOrderPayByOrderSn(orderSn);
		orderDetailVO.setOrderPayEntity(orderPayEntity);

		//订单的金额信息
		OrderMoneyEntity orderMoney = orderMoneyDao.getOrderMoneyByOrderSn(orderSn);
		orderDetailVO.setOrderMoneyEntity(orderMoney);

		//订单的商品信息
		List<OrderProductEntity> list = orderProductDao.getOrderProductByOrderSn(orderSn);
		orderDetailVO.setList(list);

		//支付信息
		List<PayRecordEntity> recordList = payRecordDao.getPayRecordByOrderSn(orderSn);
		if (!Check.NuNCollection(recordList)){

			RecordPayTypeEnum payTypeEnum = null;
			if (recordList.size() > 1){
				payTypeEnum = RecordPayTypeEnum.MIX;
			}else {
				payTypeEnum = RecordPayTypeEnum.getTypeByCode(recordList.get(0).getPayType());
			}
			if (!Check.NuNObj(payTypeEnum)){
				orderDetailVO.setPayInfo(payTypeEnum.getName());
			}
		}
		//返回订单信息
		return orderDetailVO;
	}


	/**
	 * 更新订单的信息
	 * @author afi
	 * @param orderEntity
	 * @return
	 */
	public int updateOrderBaseByOrderSn(OrderEntity orderEntity){
		return orderBaseDao.updateOrderBaseByOrderSn(orderEntity);
	}



}
