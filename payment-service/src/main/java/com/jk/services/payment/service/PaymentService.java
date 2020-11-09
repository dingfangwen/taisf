package com.jk.services.payment.service;


import com.jk.framework.base.entity.DataTransferObject;
import com.jk.services.payment.entity.*;

import java.util.List;
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
public interface PaymentService {
    int update(PayEntity pay);
	void insertPay(PayInfo payInfo);
	void savePayAndLog(PayEntity pay, PayLogEntity payLog);

	/**
	 * 通过加工之后的id获取真实的id信息
	 * @author afi
	 * @param id
	 * @return
	 */
	PayEntity selectPayByakeId(String id);


	PayEntity selectPayById(Integer id);

	/**
	 * 验证参数合法,并处理去支付的参数
	 * @param payInfo
	 * @param dto
	 */
	void dealPayParameter(PayInfo payInfo,DataTransferObject dto);

	/**
	 * 验证参数合法,并处理退款的逻辑
	 * @param payInfo
	 * @param dto
	 */
	String dealRefundParameter(PayInfo payInfo,DataTransferObject dto);

	/**
	 * 获取账号信息
	 * @param payInfo
	 * @return
	 */
	PayAccountEntity getPayAccount(PayInfo payInfo);
	/**
	 * 创建支付信息
	 * @param pay
	 * @return
	 */
	PayInfo createPayInfo(PayEntity pay);

	/**
	 * 获取app需要的字符串
	 * @param code
	 * @param map
	 * @return
	 */
	String getAppString(String code, Map<String, Object> map) throws Exception;

	/**
	 * 保存通知结果
	 * @param content
	 */
	void saveNotifyResult(String content);
	/**
	 * 发送通知并返回通知结果
	 * @param returnUrl
	 * @param returnPayList
	 * @return
	 */
	String notifyListByUrl(String returnUrl, List<ReturnPay> returnPayList) throws Exception;

}
