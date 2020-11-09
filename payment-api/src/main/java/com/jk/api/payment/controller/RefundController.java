package com.jk.api.payment.controller;


import com.jk.api.payment.base.BaseController;
import com.jk.api.payment.vo.RefundVO;
import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.rst.ResponseDto;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.SignUtils;
import com.jk.framework.base.utils.ValueUtil;
import com.jk.framework.log.utils.LogUtil;
import com.jk.services.payment.constant.PayConstants;
import com.jk.services.payment.entity.PayInfo;
import com.jk.services.payment.exception.PaymentException;
import com.jk.services.payment.handle.tenpay.TenpayRefundHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * <p>退款</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/25.
 * @version 1.0
 * @since 1.0
 */
@Controller
public class RefundController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RefundController.class);

	@Autowired
	private TenpayRefundHandle tenpayRefundHandle;




	/**
	 * 支付退款,所有的退款都这个逻辑
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/refund")
	public @ResponseBody
	ResponseDto refund(Model model, HttpServletRequest request, HttpServletResponse response){
		DataTransferObject<String> dto = new DataTransferObject();

		PayInfo payInfo = new PayInfo();
		String content ="";
		try {
			content = getPar(request);
			LOGGER.info("支付平台接收退款参数：content:{}",content);
			RefundVO refundVO = JsonEntityTransform.json2Object(content, RefundVO.class);
			BeanUtils.copyProperties(refundVO,payInfo);
			//校验参数
			String code = paymentService.dealRefundParameter(payInfo,dto);
			if (!dto.checkSuccess()){
				return  dto.trans2Res();
			}
			//校验签名
			if(!SignUtils.checkSign(payInfo.getCodeKey(),content)){
				throw new PaymentException(PayConstants.EXCEPTION_STATUS, "验证sign失败！");
			}
			LOGGER.info("请求支付：{}，请求内容：{},金额:{}",payInfo.getBizId(),code,payInfo.getContent(),payInfo.getAmount());
			//暂时不考虑退款

			String  rst ;
			if(PayConstants.HandleType.Handle_tenpay_App.getCode().equals(code)
					|| PayConstants.HandleType.Handle_weChat_Wap.getCode().equals(code)){
				//微信退款
				rst = tenpayRefundHandle.doRefundBusiness(payInfo);
			}else {
				//当前业务还未提供退款支持
				dto.setErrorMsg("当前支付未提供退款支持");
				return dto.trans2Res();
			}
			//切面通知
			if (Check.NuNStr(payInfo.getCode())){
				getPaymentHandleByCode(payInfo.getCode()).notifyHandle(payInfo,rst);
			}
		} catch (PaymentException e){
			if(payInfo!=null && payInfo.getPayId() !=null){
				this.savePayAndLog(payInfo.getPayId(), ValueUtil.getStrValue(PayConstants.PayStatus.STATUS_40000.getCode()),e.getMessage(), PayConstants.REQUEST, getJson(request));
			}
			LOGGER.error("支付ID:"+payInfo.getPayId()+"，发成异常:",e);
			dto.setErrCode(PayConstants.PayStatus.STATUS_40000.getCode());
			dto.setErrorMsg(e.getMessage());
			dto.setData(content);
		}catch (Exception e) {
			LogUtil.error(LOGGER,"e:{}",e);
			if(payInfo!=null && payInfo.getPayId() !=null){
				this.savePayAndLog(payInfo.getPayId(), ValueUtil.getStrValue(PayConstants.PayStatus.STATUS_40000.getCode()),e.getMessage(), PayConstants.REQUEST, getJson(request));
			}
			LOGGER.error("支付ID:"+payInfo.getPayId()+"，发成异常:",e);
			dto.setErrCode(PayConstants.PayStatus.STATUS_40000.getCode());
			dto.setErrorMsg(e.getMessage());
			dto.setData(content);
		}
		return  dto.trans2Res();
	}


}
