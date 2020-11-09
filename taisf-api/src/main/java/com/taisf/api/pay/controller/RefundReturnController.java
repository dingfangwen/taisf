package com.taisf.api.pay.controller;


import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.DesUtils;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.api.common.abs.AbstractController;
import com.taisf.api.pay.vo.BackInfo;
import com.taisf.services.refund.api.RefundService;
import com.taisf.services.refund.constants.RefundStatusEnum;
import com.taisf.services.refund.dto.RefundRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>退款的回调</p>
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
@RequestMapping("/refund")
public class RefundReturnController extends AbstractController {

	private final Logger logger = LoggerFactory.getLogger(RefundReturnController.class);

	@Autowired
	RefundService refundService;


	/**
	 * 支付成功的code
	 */
	private static String  success_code = "0";

    /**
     * 接受支付的回调通知 问诊
     * @param request
     * @throws Exception
     */
	@RequestMapping(value = "/notice")
	public @ResponseBody
	String notice(final HttpServletRequest request) throws Exception {
		//获取当前参数
		String  json = getPar(request);
		if (Check.NuNStr(json)){
			return DesUtils.encrypt(JsonEntityTransform.Object2Json("fail"));
		}
		LogUtil.info(logger,"退款回调:{},par:{}","退款回调",json);
		String jsonOrg = DesUtils.decrypt(json);
		LogUtil.info(logger,"退款回调:{},par:{}","退款回调",jsonOrg);
		List<BackInfo> returnList = JsonEntityTransform.json2List(jsonOrg, BackInfo.class);
		//拼接多个返回结果
		final List<Map<String, Object>> rstList = new ArrayList<Map<String, Object>>();
		for (final BackInfo ele : returnList) {
			//处理支付返回值
			Map<String, Object> dealMap = dealRefund(ele);
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
	 * 退款状态修改
	 * @author afi
	 * @param returnInfo
	 */
	private Map<String, Object> dealRefund(final BackInfo returnInfo) {
		try {
			final Map<String, Object> rst = new HashMap<>();
			RefundRequest refundRequest = transBackInfo2refund(returnInfo,returnInfo.getStatus());
			//回调
			DataTransferObject dto = refundService.dealRefundBack(refundRequest);
			rst.put("id", returnInfo.getId());
			rst.put("code", dto.getCode());
			return rst;
		} catch (final Exception e) {
			LogUtil.error(logger,"处理退款失败par:{}",JsonEntityTransform.Object2Json(returnInfo));
			return null;
		}
	}

	/**
	 * 转化成退款对象
	 * @param returnInfo
	 * @return
	 */
	private RefundRequest transBackInfo2refund(BackInfo returnInfo, String status){
		int refundStaus = RefundStatusEnum.SUCCESS.getCode();
		if(!success_code.equals(returnInfo.getStatus())){
			refundStaus = RefundStatusEnum.FAIL.getCode();
		}
		RefundRequest refundRequest = new RefundRequest();
		refundRequest.setRefundSn(returnInfo.getBizId());
		refundRequest.setOpName("退款回调");
		refundRequest.setOpUid("001");
		refundRequest.setRefundStatus(refundStaus);
		refundRequest.setRemark(returnInfo.getStatusDesc());
		return refundRequest;
	}

}
