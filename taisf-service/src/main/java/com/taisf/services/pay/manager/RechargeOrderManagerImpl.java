package com.taisf.services.pay.manager;

import com.jk.framework.base.constant.YesNoEnum;
import com.jk.framework.base.exception.BusinessException;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.taisf.services.common.valenum.AccountTypeEnum;
import com.taisf.services.common.valenum.OrdersStatusEnum;
import com.taisf.services.enterprise.vo.EnterpriseRechargeStatsVO;
import com.taisf.services.enterprise.vo.SupRechargeStatsVO;
import com.taisf.services.order.dao.OrderBaseDao;
import com.taisf.services.order.dto.EnterpriseStatsRequest;
import com.taisf.services.order.dto.SupStatsRequest;
import com.taisf.services.pay.dao.PayRecordDao;
import com.taisf.services.pay.dao.RechargeOrderDao;
import com.taisf.services.pay.dto.RechargeOrderListRequest;
import com.taisf.services.pay.entity.PayRecordEntity;
import com.taisf.services.pay.entity.RechargeOrderEntity;
import com.taisf.services.pay.vo.RechargeOrderVO;
import com.taisf.services.user.dao.AccountLogDao;
import com.taisf.services.user.dao.UserAccountDao;
import com.taisf.services.user.entity.AccountLogEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service("pay.rechargeOrderManagerImpl")
public class RechargeOrderManagerImpl {

	@Resource(name = "pay.rechargeOrderDao")
	private RechargeOrderDao rechargeOrderDao;

	@Resource(name = "user.accountLogDao")
	private AccountLogDao accountLogDao;

	@Resource(name = "user.accountUserDao")
	private UserAccountDao userAccountDao;






	/**
	 * 分页获取充值信息
	 * @author afi
	 * @param rechargeOrderListRequest
	 * @return
	 */
	public List<RechargeOrderVO> findRechargeOrderAll(RechargeOrderListRequest rechargeOrderListRequest){
		return rechargeOrderDao.findRechargeOrderAll(rechargeOrderListRequest);
	}



	/**
	 * 分页获取充值信息
	 * @author afi
	 * @param rechargeOrderListRequest
	 * @return
	 */
	public PagingResult<RechargeOrderVO> findRechargeOrderByPage(RechargeOrderListRequest rechargeOrderListRequest){
		return rechargeOrderDao.findRechargeOrderByPage(rechargeOrderListRequest);
	}


	/**
	 * 获取当前的充值信息
	 * @author afi
	 * @param orderSn
	 * @return
	 */
	public RechargeOrderEntity getRechargeOrderByOrderSn(String orderSn){
		return rechargeOrderDao.getRechargeOrderByOrderSn(orderSn);
	}


	/**
	 * 保存支付回调信息
	 * @author afi
	 * @param recordEntity
	 * @return
	 */
	public int saveRechargeOrder(RechargeOrderEntity  recordEntity){
		if (Check.NuNObj(recordEntity.getCreateTime())){
			recordEntity.setCreateTime(new Date());
		}
		return rechargeOrderDao.saveRechargeOrder(recordEntity);
	}

	/**
	 * 保存支付回调信息
	 * @author afi
	 * @param recordEntity
	 * @return
	 */
	public int updateOrderPayAndAccount(RechargeOrderEntity  recordEntity){
		if (Check.NuNObj(recordEntity.getCreateTime())){
			recordEntity.setCreateTime(new Date());
		}
		recordEntity.setPayStatus(YesNoEnum.YES.getCode());

		int num =   rechargeOrderDao.updateRechargeOrder(recordEntity);
		if (num == 1){
			int money = recordEntity.getTotalFee();
			//消费当前的余额信息
			int change = userAccountDao.changeUserBalance(recordEntity.getUserUid(),money);
			if (change != 1){
				throw new BusinessException("更新用户金额失败");
			}
			//记录当前的消费记录
			AccountLogEntity log = new AccountLogEntity();
			log.setAccountType(AccountTypeEnum.FILL.getCode());
			log.setBizMoney(money);
			log.setBizSn(recordEntity.getOrderSn());
			log.setUserId(recordEntity.getUserUid());
			log.setTitle("个人充值");
			accountLogDao.saveAccountLog(log);
		}
		return num;
	}


	/**
	 * 充值
	 * @param userUid
	 * @param money
	 * @param bizSn
	 */
	private void fillUserAccount(String userUid,int money,String bizSn){

	}


	/**
	 * 获取企业充值统计信息
	 * @author afi
	 * @param request
	 * @return
	 */
	public List<EnterpriseRechargeStatsVO> getSelfRechargeStats(EnterpriseStatsRequest request){
		return rechargeOrderDao.getSelfRechargeStats(request);
	}

	/**
	 * 获取企业充值统计信息
	 * @author afi
	 * @param request
	 * @return
	 */
	public List<SupRechargeStatsVO> getSelfRechargeSupStats(SupStatsRequest request){
		return rechargeOrderDao.getSelfRechargeSupStats(request);
	}
}
