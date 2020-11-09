package com.taisf.api.pay.controller;


import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.thread.pool.SendThreadPool;
import com.jk.framework.base.utils.*;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.api.common.abs.AbstractController;
import com.taisf.api.pay.constants.EmPayType;
import com.taisf.api.pay.vo.BackInfo;
import com.taisf.services.order.api.OrderService;
import com.taisf.services.pay.api.PayService;
import com.taisf.services.pay.api.RechargeOrderService;
import com.taisf.services.pay.dto.PayRecordRequest;
import com.taisf.services.pay.entity.RechargeOrderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>支付的回调</p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi  on 2015/6/3 0003.
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/payReturn")
public class PayReturnController  extends AbstractController {

	private final Logger logger = LoggerFactory.getLogger(PayReturnController.class);

	@Autowired
	PayService payService;


	@Autowired
	RechargeOrderService rechargeOrderService;

	/**
	 * 支付成功的code
	 */
	private static String  success_code = "0";



	/**
	 * 充值的支付回调
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/noticeRecharge")
	public @ResponseBody
	String noticeRecharge(final HttpServletRequest request) throws Exception {
		//获取当前参数
		String  json = getPar(request);
		if (Check.NuNStr(json)){
			return "fail";
		}
		LogUtil.info(logger,"支付回调:{},par:{}","订单的支付回调",json);
		String jsonOrg = DesUtils.decrypt(json);
		LogUtil.info(logger,"支付回调:{},par:{}","订单的支付回调",jsonOrg);
		List<BackInfo> returnList = JsonEntityTransform.json2List(jsonOrg, BackInfo.class);
		//拼接多个返回结果
		final List<Map<String, Object>> rstList = new ArrayList<>();
		for (final BackInfo ele : returnList) {
			//处理支付返回值
			Map<String, Object> dealMap = dealReturnPay4Recharge(ele);
			if (dealMap == null) {
				dealMap = new HashMap<String, Object>();
				dealMap.put("id", ele.getId());
				dealMap.put("code", DataTransferObject.ERROR);
			}
			rstList.add(dealMap);
		}
		return DesUtils.encrypt(JsonEntityTransform.Object2Json(rstList));
	}




	/**
	 * 接受支付的回调通知 问诊
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/noticeOrder")
	public @ResponseBody
	String noticeOrder(final HttpServletRequest request) throws Exception {
		//获取当前参数
		String  json = getPar(request);
		if (Check.NuNStr(json)){
			return "fail";
		}
		LogUtil.info(logger,"支付回调:{},par:{}","订单的支付回调",json);
		String jsonOrg = DesUtils.decrypt(json);
		LogUtil.info(logger,"支付回调:{},par:{}","订单的支付回调",jsonOrg);
		List<BackInfo> returnList = JsonEntityTransform.json2List(jsonOrg, BackInfo.class);
		//拼接多个返回结果
		final List<Map<String, Object>> rstList = new ArrayList<>();
		for (final BackInfo ele : returnList) {
			//处理支付返回值
			Map<String, Object> dealMap = dealReturnPay4Order(ele);
			if (dealMap == null) {
				dealMap = new HashMap<String, Object>();
				dealMap.put("id", ele.getId());
				dealMap.put("code", DataTransferObject.ERROR);
			}
			rstList.add(dealMap);
		}
		return DesUtils.encrypt(JsonEntityTransform.Object2Json(rstList));
	}


	/**
	 * 付款的回调函数
	 * 订单支付回调
	 * @author afi
	 * @param returnInfo
	 */
	private Map<String, Object> dealReturnPay4Recharge(final BackInfo returnInfo) {
		try {
			final Map<String, Object> rst = new HashMap<>();
			//如果前端返回的状态为支付失败，直接返回，不做处理
			if(!success_code.equals(returnInfo.getStatus())){
				rst.put("id", returnInfo.getId());
				rst.put("code", success_code);
				return rst;
			}
			RechargeOrderEntity recordRequest = transBackInfo2Recharge(returnInfo);
			DataTransferObject dto = rechargeOrderService.dealRechargeOrder4PayReturn(recordRequest);
			int code = dto.getCode();
			rst.put("id", returnInfo.getId());
			rst.put("code",code );
			return rst;
		} catch (final Exception e) {
			LogUtil.error(logger,"订单支付处理失败par:{},e:{}",JsonEntityTransform.Object2Json(returnInfo),e);
			return null;
		}
	}



	/**
	 * 付款的回调函数
	 * 订单支付回调
	 * @author afi
	 * @param returnInfo
	 */
	private Map<String, Object> dealReturnPay4Order(final BackInfo returnInfo) {
		try {
			final Map<String, Object> rst = new HashMap<>();
			//如果前端返回的状态为支付失败，直接返回，不做处理
			if(!success_code.equals(returnInfo.getStatus())){
				rst.put("id", returnInfo.getId());
				rst.put("code", success_code);
				return rst;
			}
			PayRecordRequest recordRequest = transBackInfo2Record(returnInfo);
			DataTransferObject dto = payService.dealOrderPay(recordRequest);
			int code = dto.getCode();
			rst.put("id", returnInfo.getId());
			rst.put("code",code );
            return rst;
		} catch (final Exception e) {
			LogUtil.error(logger,"订单支付处理失败par:{},e:{}",JsonEntityTransform.Object2Json(returnInfo),e);
			return null;
		}
	}

	/**
	 * 转化回调参数
	 * @author afi
	 * @param returnInfo
	 * @return
	 */
	private PayRecordRequest transBackInfo2Record(BackInfo returnInfo){
		if (Check.NuNObj(returnInfo)){
			return null;
		}
		PayRecordRequest recordRequest = new PayRecordRequest();
		recordRequest.setOrderSn(returnInfo.getBizId());
		recordRequest.setTotalFee(returnInfo.getAmount());

		EmPayType handle = EmPayType.getEmPayTypeByCode(returnInfo.getCode());
		if (!Check.NuNObj(handle)){
			recordRequest.setPayType(handle.getPayType());
		}
		recordRequest.setPayCode(returnInfo.getStatus());
		Double time = BigDecimalUtil.div(returnInfo.getBizTime(),1000);
		recordRequest.setPaytime(time.intValue());
		recordRequest.setTradeNo(returnInfo.getSerialNo());
		return recordRequest;
	}


	/**
	 * 转化回调参数
	 * @author afi
	 * @param returnInfo
	 * @return
	 */
	private RechargeOrderEntity transBackInfo2Recharge(BackInfo returnInfo){
		if (Check.NuNObj(returnInfo)){
			return null;
		}
		RechargeOrderEntity recordRequest = new RechargeOrderEntity();
		recordRequest.setOrderSn(returnInfo.getBizId());
		recordRequest.setTotalFee(returnInfo.getAmount());
		EmPayType handle = EmPayType.getEmPayTypeByCode(returnInfo.getCode());
		if (!Check.NuNObj(handle)){
			recordRequest.setPayType(handle.getPayType());
		}
		recordRequest.setPayTime(DateUtil.getTime(ValueUtil.getintValue(returnInfo.getBizTime())));
		recordRequest.setTradeNo(returnInfo.getSerialNo());
		return recordRequest;
	}


}
