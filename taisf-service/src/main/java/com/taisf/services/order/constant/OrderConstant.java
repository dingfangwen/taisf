package com.taisf.services.order.constant;

import com.jk.framework.base.utils.Check;
import org.springframework.stereotype.Component;

@Component("orderConstant")
public class OrderConstant {

	/**
	 * 骑手扫码
	 */
	public static String KNIGHT_ORDER = "KNIGHT_ORDER_";

	/**
	 * 骑手扫码
	 */
	public static Integer KNIGHT_ORDER_SENCOND = 30 * 60;


	/**
	 * 拼装当前的用户信息
	 * @param uid
	 * @return
	 */
	public static String trans2Key4KnightOrder(String uid){
		if (Check.NuNStr(uid)){
			return null;
		}
		return KNIGHT_ORDER + uid;
	}

}