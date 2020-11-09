package com.taisf.api.pay.constants;

import com.jk.framework.base.utils.Check;

/**
 * <p>支付类型</p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi  on 2017/5/4 0004.
 * @version 1.0
 * @since 1.0
 */
public enum EmPayType {



	ALIPAYWAPHANDLE(4,"alipayWapHandle", "wap支付"),
    ALIPAYAPPHANDLE(3,"alipayAppHandle", "支付宝支付"),
	
	WECHATWAPHANDLE(2,"weChatWapHandle", "微信号支付"),
    TENPAYAPPHANDLE(1,"tenpayAppHandle", "微信支付");

	private int payType;

	private String code;

	private String msg;

	private EmPayType(final int payType, final String code, final String msg) {
		this.payType = payType;
		this.code = code;
		this.msg = msg;
	}

	public int getPayType() {
		return payType;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}


	/**
	 * 通过code获取支付类型
	 * @param payType
	 * @return
	 */
	public static EmPayType getEmPayTypeByType(final Integer payType) {
		if (Check.NuNObj(payType)){
			return null;
		}
		for (final EmPayType status : EmPayType.values()) {
			if (status.getPayType() == payType) {
				return status;
			}
		}
		return null;
	}

	/**
	 * 通过code获取支付类型
	 * @param code
	 * @return
	 */
	public static EmPayType getEmPayTypeByCode(final String code) {
		if (Check.NuNObj(code)){
			return null;
		}
		for (final EmPayType status : EmPayType.values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		return null;
	}
}
