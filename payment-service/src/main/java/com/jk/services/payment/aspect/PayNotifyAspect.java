package com.jk.services.payment.aspect;


import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.services.payment.constant.PayConstants;
import com.jk.services.payment.dao.PayDao;
import com.jk.services.payment.entity.PayInfo;
import com.jk.services.payment.entity.ReturnPay;
import com.jk.services.payment.service.PaymentService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 支付通知Aop
 * @author afi
 *
 */
@Aspect
@Order(1)
@Service
public class PayNotifyAspect {
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private PayDao payDao;
	private static final Logger LOGGER = LoggerFactory.getLogger(PayNotifyAspect.class);

	/**
	 * 微信支付切面
	 */
	private static final String TENPAYAPP_NOTIFY  = "target(com.jk.services.payment.handle.tenpay.TenpayAppHandle)";

	/**
	 * 微信退款切面
	 */
	private static final String TENREFUND_NOTIFY  = "target(com.jk.services.payment.handle.tenpay.TenpayRefundHandle)";

//	/**
//	 * 支付宝退款切面
//	 */
//	private static final String ALIEFUND_NOTIFY  = "target(com.jk.services.payment.handle.alipay.AliRefundHandle)";
//

	/**
	 * 方法切入点
	 */
	@Pointcut(value = "execution(* notifyHandle(..))")
    private void notifyHandle() {

	}

//
//	/**
//	 * 支付宝退款切面
//	 * @param point
//	 * @param returnStr
//	 * @throws Throwable
//	 */
//	@AfterReturning(value=ALIEFUND_NOTIFY+" && notifyHandle()",returning="returnStr")
//	public void alipayRefundResult(JoinPoint point, String returnStr) throws Throwable {
//		LOGGER.info("支付宝退款通知结果：{}", returnStr);
//		if(PayConstants.SUCCESS.equalsIgnoreCase(returnStr)){
//			LOGGER.info("开始切面同步,同步参数:{}", JsonEntityTransform.Object2Json(point.getArgs()[0]));
//			this.notify(point.getArgs()[0]);
//		}
//	}


	/**
	 * 微信退款切面
	 * @param point
	 * @param returnStr
	 * @throws Throwable
	 */
	@AfterReturning(value=TENREFUND_NOTIFY+" && notifyHandle()",returning="returnStr")
	public void tenpayRefundResult(JoinPoint point, String returnStr) throws Throwable {
		LOGGER.info("微信退款通知结果：{}", returnStr);
		if(PayConstants.SUCCESS.equalsIgnoreCase(returnStr)){
			LOGGER.info("开始切面同步,同步参数:{}", JsonEntityTransform.Object2Json(point.getArgs()[0]));
			this.notify(point.getArgs()[0]);
		}
	}


	/**
	 * 微信支付切面
	 * @param point
	 * @param returnStr
	 * @throws Throwable
	 */
	@AfterReturning(value=TENPAYAPP_NOTIFY+" && notifyHandle()",returning="returnStr")
	public void tenpayAppResult(JoinPoint point, String returnStr) throws Throwable {
		LOGGER.info("微信支付异步通知结果：{}", returnStr);
		if(("<xml><return_code>"+ PayConstants.SUCCESS+"</return_code></xml>").equalsIgnoreCase(returnStr)){
			LOGGER.info("开始切面同步,同步参数:{}", JsonEntityTransform.Object2Json(point.getArgs()[0]));
			this.notify(point.getArgs()[0]);
		}
	}


	/**
	 * 切面的回调信息
	 * @param object
	 */
	private void notify(Object object){
		try {
			LOGGER.info("同步通知参数：{}", JsonEntityTransform.Object2Json(object));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("payId", ((PayInfo)object).getPayId());
			LOGGER.info("同步通知查询参数：{}", JsonEntityTransform.Object2Json(map));
			List<ReturnPay> returnPayList = payDao.queryNotifyList(map);
			LOGGER.info("准备通知结果：{}", JsonEntityTransform.Object2Json(returnPayList));
			if(CollectionUtils.isNotEmpty(returnPayList)){
				String notifyUrl = returnPayList.get(0).getNotifyUrl();
				String content = paymentService.notifyListByUrl(notifyUrl, returnPayList);
				// 发送请求参数
				LOGGER.info("返回请求的URL ：{}  ， 内容：{}", notifyUrl,content);
				paymentService.saveNotifyResult(content);
				LOGGER.info("返回通知结果操作成功！");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
