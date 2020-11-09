package com.jk.services.payment.handle.tenpay;


import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.SignUtils;
import com.jk.services.payment.constant.PayConstants;
import com.jk.services.payment.entity.PayInfo;
import com.jk.services.payment.exception.PaymentException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>微信公共号支付</p>
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
public class WeChatWapHandle extends TenpayAppHandle {
	private static final Logger LOGGER = LoggerFactory.getLogger(WeChatWapHandle.class);
	@Override
	public Map<String, Object> getParameterMap(PayInfo payInfo, HttpServletRequest request) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("appid",payInfo.getAccount());
		parameterMap.put("mch_id", payInfo.getPartner());
		parameterMap.put("nonce_str", "tenpay"+System.currentTimeMillis());
		parameterMap.put("body", payInfo.getContent());
		parameterMap.put("out_trade_no", payInfo.getPayId()+"");
		parameterMap.put("total_fee", payInfo.getAmount());
		parameterMap.put("spbill_create_ip", request.getLocalAddr());
		parameterMap.put("notify_url", notifyUrl);
		parameterMap.put("trade_type", "JSAPI");
		parameterMap.put("openid", payInfo.getOpenid());
		parameterMap.put("sign", SignUtils.md5Sign("&key="+payInfo.getPartnerKey(),parameterMap).toUpperCase());
		return this.changeParameter(payInfo,parameterMap);
	}
	
	/**
	 * 第一次请求获取prepayid
	 * @param payInfo
	 * @param parameterMap
	 */
	@Override
	public Map<String, Object> changeParameter(PayInfo payInfo,Map<String, Object> parameterMap){
		String prepayid = "";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			LOGGER.info("{}  请求微信APP支付获取Token参数：{}",payInfo.getPayId(), JsonEntityTransform.Object2Json(parameterMap));
			prepayid = this.getPrepayid(parameterMap);
			map.put("appId", payInfo.getAccount());
			map.put("timeStamp", System.currentTimeMillis()/1000+"");
			map.put("nonceStr", "tenpay"+System.currentTimeMillis());
			map.put("package", "prepay_id="+prepayid);
			map.put("signType", "MD5");
			map.put("paySign", SignUtils.md5Sign("&key="+payInfo.getPartnerKey(),map).toUpperCase());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw  new PaymentException(PayConstants.PayStatus.STATUS_40000.getCodeStr(), e.getMessage());
		}
		if(StringUtils.isEmpty(prepayid)){
			throw  new PaymentException(PayConstants.PayStatus.STATUS_40000.getCodeStr(), "获取prepayid失败");
		}
		return map;
	}
}
