package com.jk.services.payment.task;


import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.DateUtil;
import com.jk.framework.base.utils.DesUtils;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.services.payment.constant.PayConstants;
import com.jk.services.payment.dao.PayDao;
import com.jk.services.payment.entity.PayLogEntity;
import com.jk.services.payment.entity.ReturnPay;
import com.jk.services.payment.exception.PaymentException;
import com.jk.services.payment.service.PaymentService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PayJobTask extends TaskJob{
	private static final Logger LOGGER = LoggerFactory.getLogger(PayJobTask.class);

	@Autowired
	private PayDao payDao;

	@Autowired
	private PaymentService paymentService;

	@Override
	public void doTask() {
		try {
			LOGGER.info("通知定时任务开始：{}", DateUtil.timestampFormat(new Date()));
			//查询未通知的URL
			List<String> urlList = payDao.queryNotifyUrlList();
			for(String notifyUrl : urlList){
				if(StringUtils.isNotEmpty(notifyUrl)){
					try {
						LOGGER.info("通知URL：{}",notifyUrl);
						//查询未通知成功的记录
						Map<String, Object> queryMap = new HashMap<String, Object>();
						queryMap.put("notifyUrl", notifyUrl);
						List<ReturnPay> returnPayList = payDao.queryNotifyList(queryMap);
						if(CollectionUtils.isNotEmpty(returnPayList)){
							String content = paymentService.notifyListByUrl(notifyUrl, returnPayList);
							String rst = null;
							try {
								rst = DesUtils.decrypt(content);
							}catch (Exception e){
							}
							if(!Check.NuNStr(rst) && rst.length() < 512){
							// 发送请求参数
								saveNotifyLog(returnPayList, PayConstants.PayStatus.STATUS_20000.getCodeStr(), "接收通知url："+notifyUrl, PayConstants.RESPONSE, content);
							}
							LOGGER.info("返回通知的URL ：{}  ， 内容：{}", notifyUrl,content);
							paymentService.saveNotifyResult(content);
						}
					} catch (Exception e) {
						LOGGER.error("通知URL：{}"+notifyUrl+"定时通知发生发成异常:",e);
					}
				}
			}
		} catch (PaymentException e){
			LOGGER.error("定时通知发生发成异常:"+e.getMessage(),e);
		}catch (Exception e) {
			LOGGER.error("定时通知发生发成异常:",e);
		}
	}

	/**
	 * 保存请求信息
	 * @param returnPayList
	 * @param status
	 * @param statusDesc
	 * @param httpType
	 * @param parameter
	 */
	private void saveNotifyLog(List<ReturnPay> returnPayList,String status,String statusDesc,String httpType,String parameter) {
		for(ReturnPay returnPay : returnPayList){
			PayLogEntity payLog = new PayLogEntity();
			payLog.setPayId(returnPay.getId());
			payLog.setParameter(parameter);
			if(StringUtils.isNotEmpty(status))payLog.setStatus(status);
			if(StringUtils.isNotEmpty(statusDesc))payLog.setStatusDesc(statusDesc);
			payLog.setType(httpType);
			LOGGER_PAY_LOG.error("落地支付日志:"+ JsonEntityTransform.Object2Json(payLog));
		}
	}
}
