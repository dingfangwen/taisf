package com.taisf.services.pay.manager;

import com.jk.framework.base.utils.Check;
import com.taisf.services.common.valenum.OrdersStatusEnum;
import com.taisf.services.order.dao.OrderBaseDao;
import com.taisf.services.order.dao.OrderMoneyDao;
import com.taisf.services.order.entity.OrderMoneyEntity;
import com.taisf.services.pay.dao.PayRecordDao;
import com.taisf.services.pay.entity.PayRecordEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service("pay.payManagerImpl")
public class PayManagerImpl {

	@Resource(name = "pay.payRecordDao")
	private PayRecordDao payRecordDao;

	@Resource(name = "order.orderBaseDao")
	private OrderBaseDao orderBaseDao;

	@Resource(name = "order.orderMoneyDao")
	private OrderMoneyDao orderMoneyDao;


	/**
	 * 获取当前的用户当前的金额
	 * @param userId
	 * @return
	 */
	public Long getUserCostToday(String userId){
		if (Check.NuNStr(userId)){
			return 0L;
		}
		return payRecordDao.getUserCostToday(userId);
	}

	/**
	 * 获取当前的支付记录信息
	 * @author afi
	 * @param orderSn
	 * @return
	 */
	public List<PayRecordEntity> getPayRecordByOrderSn(String orderSn){
		return payRecordDao.getPayRecordByOrderSn(orderSn);
	}
	public List<PayRecordEntity> getPayRecordByOrderSnList(List<String> orderSnList){
		return payRecordDao.getPayRecordByOrderSnList(orderSnList);
	}


	/**
	 * 保存支付回调信息
	 * @author afi
	 * @param recordEntity
	 * @return
	 */
	public int savePayRecord(PayRecordEntity  recordEntity){
		if (Check.NuNObj(recordEntity.getCreateTime())){
			recordEntity.setCreateTime(new Date());
		}
		return payRecordDao.savePayRecord(recordEntity);
	}

	/**
	 * 保存支付回调信息
	 * @author afi
	 * @param recordEntity
	 * @return
	 */
	public int updateOrderPay(PayRecordEntity  recordEntity,boolean face){
		if (Check.NuNObj(recordEntity.getCreateTime())){
			recordEntity.setCreateTime(new Date());
		}
		//添加记录
		int num = payRecordDao.savePayRecord(recordEntity);
		if (num ==1){
			orderBaseDao.payOrder(recordEntity.getOrderSn(), OrdersStatusEnum.NO_PAY.getCode(),face);
			//处理订单支付金额
			OrderMoneyEntity orderMoneyEntity = new OrderMoneyEntity();
			orderMoneyEntity.setOrderSn(recordEntity.getOrderSn());
			orderMoneyEntity.setPayMoney(recordEntity.getTotalFee());
			orderMoneyDao.updateOrderMoney(orderMoneyEntity);
		}
		return num;
	}

}
