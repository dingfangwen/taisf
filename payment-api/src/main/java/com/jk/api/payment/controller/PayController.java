package com.jk.api.payment.controller;


import com.jk.api.payment.base.BaseController;
import com.jk.api.payment.vo.CreateVO;
import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.rst.ResponseDto;
import com.jk.framework.base.rst.SignResponseDto;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.SignUtils;
import com.jk.framework.base.utils.ValueUtil;
import com.jk.framework.base.utils.XmlUtils;
import com.jk.framework.log.utils.LogUtil;
import com.jk.services.payment.constant.PayConstants;
import com.jk.services.payment.entity.PayEntity;
import com.jk.services.payment.entity.PayInfo;
import com.jk.services.payment.exception.PaymentException;
import com.jk.services.payment.handle.PaymentHandle;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>支付相关</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/5/11.
 * @version 1.0
 * @since 1.0
 */
@Controller
public class PayController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PayController.class);


	@RequestMapping("/")
	public @ResponseBody
	SignResponseDto index(Model model, HttpServletRequest request, HttpServletResponse response){
		return new SignResponseDto();
	}

	/**
	 * 去支付
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/create")
	public @ResponseBody
	ResponseDto create(Model model, HttpServletRequest request, HttpServletResponse response){
		DataTransferObject<String> dto = new DataTransferObject();

		PayInfo payInfo = new PayInfo();
		String content ="";
		try {
			content = getPar(request);
			LOGGER.info("支付平台接收请求参数：content:{}",content);

			CreateVO createVO = JsonEntityTransform.json2Object(content, CreateVO.class);
			BeanUtils.copyProperties(createVO,payInfo);
			paymentService.dealPayParameter(payInfo,dto);//校验参数
			if (!dto.checkSuccess()){
				return  dto.trans2Res();
			}
			//校验签名
			if(!SignUtils.checkSign(payInfo.getCodeKey(),content)){
				throw new PaymentException(PayConstants.EXCEPTION_STATUS, "验证sign失败！");
			}
			LOGGER.info("产品编码：{}，业务ID：{}，请求支付：{}，请求内容：{},金额:{}",payInfo.getProductCode(),payInfo.getBizId(),payInfo.getCode(),payInfo.getContent(),payInfo.getAmount());

			//插入支付请求记录
			this.paymentService.insertPay(payInfo);

			//创建参数信息
			Map<String, Object> parameterMap = this.createAttribute(model, payInfo, request);
			LOGGER.info("支付平台参数信息：{}",JsonEntityTransform.Object2Json(parameterMap));
			//当为支付宝钱包支付 、微信支付时，返回拼接的字符串
			if(PayConstants.HandleType.Handle_alipay_App.getCode().equals(payInfo.getCode())
					|| PayConstants.HandleType.Handle_tenpay_App.getCode().equals(payInfo.getCode())
					|| PayConstants.HandleType.Handle_weChat_Wap.getCode().equals(payInfo.getCode())){
				dto.setData(paymentService.getAppString(payInfo.getCode(), parameterMap));}
		} catch (PaymentException e){
			LogUtil.info(LOGGER,"异常的支付类型:par:{}",JsonEntityTransform.Object2Json(content));
			LOGGER.error("支付ID:"+payInfo.getPayId()+"，发成异常:",e);
			dto.setErrCode(PayConstants.PayStatus.STATUS_40000.getCode());
			dto.setErrorMsg(e.getMessage());
			dto.setData(content);
		}catch (Exception e) {
			LogUtil.error(LOGGER,"e:{}",e);
			if(payInfo!=null && payInfo.getPayId() !=null){
				this.savePayAndLog(payInfo.getPayId(), ValueUtil.getStrValue(PayConstants.PayStatus.STATUS_40000.getCode()),e.getMessage(), PayConstants.REQUEST, getJson(request));
			}
			LOGGER.error("支付ID:"+payInfo.getPayId()+"，程序异常:",e);
			dto.setErrCode(PayConstants.PayStatus.STATUS_40000.getCode());
			dto.setErrorMsg(e.getMessage());
			dto.setData(content);
		}
		return  dto.trans2Res();
	}




	/**
	 * 支付平台异步回调
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/notify")
	@ResponseBody
	public String notify(Model model, HttpServletRequest request, HttpServletResponse response){
		Object payId = null;
		String content ="";
		try {
			content = this.getJson(request);
			LOGGER.info("支付平台接收异步返回 request：{}",content);
			String streamContent= this.getContentByInputStream(request.getInputStream());
			LOGGER.info("支付平台接收异步返回 steam：{}",streamContent);
			if(streamContent.indexOf("<xml>")==0){
				LOGGER.info("xml");
				Document document = DocumentHelper.parseText(streamContent);
				payId = Integer.parseInt(XmlUtils.getDocumentNode(document, "/xml/out_trade_no"));
				content = streamContent;
			} else if("batch_refund_notify".equals(request.getParameter("notify_type"))){
				LOGGER.info("batch_refund_notify");
				String batch_no = request.getParameter("batch_no");
				payId = batch_no.substring(8, batch_no.toString().length());
			}else {
				LOGGER.info("other");
				payId = request.getParameter("out_trade_no");
			}

			LOGGER.info("获取异步通知 payId：{}",payId);
			if(payId != null) {
				PayEntity pay = paymentService.selectPayByakeId(payId.toString());
				LOGGER.info("获取异步通知 pay：{}",JsonEntityTransform.Object2Json(pay));
				if( pay != null){
					return getPaymentHandleByCode(pay.getCode()).notifyHandle(paymentService.createPayInfo(pay),content);
				}
			}
		} catch (PaymentException e){
			LOGGER.error(payId+"异步通知发生异常: ",e);
		}catch (Exception e) {
			LOGGER.error(payId+"异步通知发生异常: ",e);
		}
		return "fail";
	}

//	/**
//	 * 支付平台异步回调
//	 * @param model
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping("/alipayNotify")
//	@ResponseBody
//	public String alipayNotify(Model model, HttpServletRequest request, HttpServletResponse response){
//		Map<String,String> params = new HashMap<String,String>();
//		Object payId = null;
//		String content ="";
//		try {
//			content = this.getJson(request);
//			LOGGER.info("支付宝平台接收异步返回 request：{}",content);
//			payId = request.getParameter("out_trade_no");
//			Map requestParams = request.getParameterMap();
//			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
//				String name = (String) iter.next();
//				String[] values = (String[]) requestParams.get(name);
//				String valueStr = "";
//				for (int i = 0; i < values.length; i++) {
//					valueStr = (i == values.length - 1) ? valueStr + values[i]
//							: valueStr + values[i] + ",";
//				}
//				//乱码解决，这段代码在出现乱码时使用。
//				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//				params.put(name, valueStr);
//			}
//			LOGGER.info("获取异步通知 payId：{}",payId);
//			if(payId != null) {
//				PayEntity pay = paymentService.selectPayByakeId(payId.toString());
//				LOGGER.info("获取异步通知 pay：{}",JsonEntityTransform.Object2Json(pay));
//				if( pay == null){
//					return "fail";
//				}
//				PayInfo payInfo = paymentService.createPayInfo(pay);
//				boolean flag = AlipaySignature.rsaCheckV1(params, payInfo.getPartnerKey(), "UTF-8", "RSA");
//				if(!flag){
//					LOGGER.error(payId+"异步通知验签失败");
//					return "fail";
//				}
//				content = JsonEntityTransform.Object2Json(params);
//				return getPaymentHandleByCode(pay.getCode()).notifyHandle(payInfo, content);
//
//			}
//		} catch (PaymentException e){
//			LOGGER.error(payId+"异步通知发生异常: ",e);
//		}catch (Exception e) {
//			LOGGER.error(payId+"异步通知发生异常: ",e);
//		}
//		return "fail";
//	}

	/**
	 * 同步的回调地址
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/return")
	public String returnX(Model model, HttpServletRequest request, HttpServletResponse response){
		Object payId = null;
			try {
			LOGGER.info("支付平台接收同步返回参数：{}",getJson(request));
			payId = request.getParameter("out_trade_no");
			if(payId != null) {
				PayEntity pay = paymentService.selectPayById(Integer.parseInt(payId.toString()));
				if( pay != null){
					PaymentHandle paymentHandle = getPaymentHandleByCode(pay.getCode());
					Map<String, Object> parameterMap = new HashMap<String, Object>();
					parameterMap.put("biz_id", pay.getBizId());
					parameterMap.put("code", paymentHandle.returnHandle(paymentService.createPayInfo(pay),getJson(request)));
					model.addAttribute("parameterMap", parameterMap);
					model.addAttribute("requestUrl", pay.getReturnUrl());
					model.addAttribute("requestCharset", paymentHandle.getRequestCharset());
					model.addAttribute("requestMethod", paymentHandle.getRequestMethod());
				} else{
					return getExceptionRst("未获取到支付信息！");
				}
			}
		} catch (PaymentException e){
			LOGGER.error(payId+"同步通知发生异常: ",e);
			return getExceptionRst("未获取到支付信息！");
		}catch (Exception e) {
			LOGGER.error(payId+"同步通知发生异常: ",e);
			return getExceptionRst("未获取到支付信息！");
		}
		return "index";
	}



}
