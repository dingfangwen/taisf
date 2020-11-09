package com.jk.services.payment.handle.tenpay;


import com.jk.framework.base.utils.DateUtil;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.SignUtils;
import com.jk.framework.base.utils.XmlUtils;
import com.jk.services.payment.constant.PayConstants;
import com.jk.services.payment.entity.PayDetailEntity;
import com.jk.services.payment.entity.PayEntity;
import com.jk.services.payment.entity.PayInfo;
import com.jk.services.payment.exception.PaymentException;
import com.jk.services.payment.handle.PaymentHandle;
import com.jk.services.payment.utils.PayKeyUtil;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>微信app支付</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/6/13.
 * @version 1.0
 * @since 1.0
 */
@Service
public class TenpayAppHandle extends PaymentHandle {
	private static final Logger LOGGER = LoggerFactory.getLogger(TenpayAppHandle.class);


	@Override
	public String getRequestUrl() {
		return "https://api.mch.weixin.qq.com/pay/unifiedorder";
	}

	@Override
	public RequestMethod getRequestMethod() {
		return RequestMethod.post;
	}

	@Override
	public String getRequestCharset() {
		return "utf-8";
	}

	@Override
	public Map<String, Object> getParameterMap(PayInfo payInfo, HttpServletRequest request) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("appid",payInfo.getAccount());
		parameterMap.put("mch_id", payInfo.getPartner());
		parameterMap.put("nonce_str", "tenpay"+System.currentTimeMillis());
		parameterMap.put("body", payInfo.getContent());
		parameterMap.put("out_trade_no", PayKeyUtil.getFakeKey(payPreKey,payInfo.getPayId()));
		parameterMap.put("total_fee", payInfo.getAmount());
		parameterMap.put("spbill_create_ip", request.getLocalAddr());
		parameterMap.put("notify_url", notifyUrl);
		parameterMap.put("trade_type", "APP");
		parameterMap.put("sign", SignUtils.md5Sign("&key="+payInfo.getPartnerKey(),parameterMap).toUpperCase());
		return this.changeParameter(payInfo,parameterMap);
	}

	@Override
	public String notifyHandle(PayInfo payInfo,String json){
		LOGGER.info("获取微信APP支付同步通知参数：payInfo:{},json:{}",JsonEntityTransform.Object2Json(payInfo),json);
		try {
			PayEntity pay = new PayEntity();
			Integer payId = null;
			Document document = DocumentHelper.parseText(json);
			if("SUCCESS".equalsIgnoreCase(XmlUtils.getDocumentNode(document, "/xml/return_code"))){
				payId = Integer.parseInt(XmlUtils.getDocumentNode(document, "/xml/out_trade_no"));
				pay.setId(payId);
				pay.setReqId(payId.toString());
				pay.setResId(XmlUtils.getDocumentNode(document, "/xml/transaction_id"));
				String bizTimeString = XmlUtils.getDocumentNode(document, "/xml/time_end");
				if(StringUtils.isNotEmpty(bizTimeString)){
					pay.setBizTime(DateUtil.parseDate(bizTimeString,DateUtil.intTimestampPattern));
				}else{
					pay.setBizTime(new Date());
				}
				if("SUCCESS".equalsIgnoreCase(XmlUtils.getDocumentNode(document, "/xml/result_code"))){
					pay.setStatus(PayConstants.PayStatus.STATUS_SUCCESS.getCodeStr());
					pay.setStatusDesc(PayConstants.PayStatus.STATUS_SUCCESS.getName());
				}else{
					pay.setStatus(PayConstants.PayStatus.STATUS_40000.getCodeStr());
					pay.setStatusDesc(XmlUtils.getDocumentNode(document, "/xml/err_code")+" : " +XmlUtils.getDocumentNode(document, "/xml/err_code_des"));
				}
				PayDetailEntity payDetail  = new PayDetailEntity();
				payDetail.setPayId(payId);
				payDetail.setOutSerialNo(payId.toString());
				payDetail.setInSerialNo(XmlUtils.getDocumentNode(document, "/xml/transaction_id"));
				payDetail.setStatus(pay.getStatus());
				payDetail.setStatusDesc(pay.getStatusDesc());
				payDao.update(pay);
				payDetailDao.update(payDetail);
				LOGGER.info("支付ID:{} 异步通知操作成功 ：微信APP支付参数：{}  ",payId,json);
				return "<xml><return_code>"+PayConstants.SUCCESS+"</return_code></xml>";
			}else{
				LOGGER.info(" 异步通知操作失败：微信APP支付参数：{}  ",json);
			}
		} catch (Exception e) {
			LOGGER.error(" 异步通知异常 ",e);
		}
		return "<xml><return_code>fail</return_code></xml>";
	}

	@Override
	public String returnHandle(PayInfo payInfo,String json) {
		return PayConstants.SUCCESS_STATUS;
	}

	@Override
	public void verifyNotify(PayInfo payInfo, String json){
	}

	
	
	/**
	 * 第一次请求获取prepayid
	 * @param payInfo
	 * @param parameterMap
	 */
	public Map<String, Object> changeParameter(PayInfo payInfo,Map<String, Object> parameterMap){
		String prepayid = "";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			LOGGER.info("{}  请求微信APP支付获取Token参数：{}",payInfo.getPayId(),JsonEntityTransform.Object2Json(parameterMap));
			prepayid = this.getPrepayid(parameterMap);
			map.put("prepayid", prepayid);
			map.put("appid", payInfo.getAccount());
			map.put("partnerid", payInfo.getPartner());
			map.put("package", "Sign=WXPay");
			map.put("noncestr", "tenpay"+System.currentTimeMillis());
			map.put("timestamp", System.currentTimeMillis()/1000+"");
			map.put("sign", SignUtils.md5Sign("&key="+payInfo.getPartnerKey(),map).toUpperCase());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw  new PaymentException(PayConstants.PayStatus.STATUS_40000.getCodeStr(), e.getMessage());
		}
		if(StringUtils.isEmpty(prepayid)){
			throw  new PaymentException(PayConstants.PayStatus.STATUS_40000.getCodeStr(), "获取prepayid失败");
		}
		return map;
	}

	public String getPrepayid(Map<String, Object> parameterMap) throws Exception{
		LOGGER.info("微信APP支付发送请求参数：{}",JsonEntityTransform.Object2Json(parameterMap));
		URL url = new URL(this.getRequestUrl());
		URLConnection conn = url.openConnection();
		// 发送POST请求必须设置如下两行
		conn.setDoOutput(true);
		conn.setDoInput(true);
		// 获取URLConnection对象对应的输出流
		OutputStream outputStream = conn.getOutputStream();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outputStream));
		// 发送请求参数
		StringBuffer sb = new StringBuffer("<xml>");//"<?xml version=\"1.0\" encoding=\"GB2312\"?><xml>");
		for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {  
			sb.append("<").append(entry.getKey()).append(">");
			sb.append(entry.getValue());
			sb.append("</").append(entry.getKey()).append(">");
		} 
		sb.append("</xml>");
		LOGGER.info("微信APP支付发送请求参数：{}",sb.toString());
		out.write(sb.toString());
		// flush输出流的缓冲
		out.flush();
		out.close();
		outputStream.close();
		String result = getContentByInputStream(conn.getInputStream());
		LOGGER.info("微信APP支付返回Token参数：{}",result);
		Document document = DocumentHelper.parseText(result);
		if(!"SUCCESS".equalsIgnoreCase(XmlUtils.getDocumentNode(document, "/xml/return_code")) || !"SUCCESS".equalsIgnoreCase(XmlUtils.getDocumentNode(document, "/xml/result_code"))){
			throw  new PaymentException(PayConstants.PayStatus.STATUS_40000.getCodeStr(), "获取prepayid失败"+XmlUtils.getDocumentNode(document, "/xml/err_code")+" "+XmlUtils.getDocumentNode(document, "/xml/err_code_des"));
		}
		String prepay_id = XmlUtils.getDocumentNode(document, "/xml/prepay_id");
		return prepay_id;
	}
	
	/**
	 * 获取请求的stream 内容
	 * @param inputStream
	 * @return
	 */
	public String getContentByInputStream(InputStream inputStream) throws Exception{
		String content = "";
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,this.getRequestCharset()));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			content = content + line;
		}
		bufferedReader.close();
		inputStream.close();
		if(StringUtils.isEmpty(content)){
			throw new PaymentException(PayConstants.EXCEPTION_STATUS, "获取内容失败");
		}
		return content;
	}
}
