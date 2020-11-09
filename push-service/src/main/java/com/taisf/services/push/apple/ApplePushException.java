package com.taisf.services.push.apple;

import com.jk.framework.base.exception.BaseException;

/**
 * <p>苹果推送的异常</p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * <PRE>
 * 
 * @author afi
 * @since 1.0
 * @version 1.0
 */
public class ApplePushException extends BaseException {

	private static final long serialVersionUID = 3003915267325733689L;

	/**
	 * 构造器
	 */
	public ApplePushException() {
		super();
	}

	/**
	 * 构造器
	 * @param message	异常详细信息
	 * @param cause	异常原因
	 */
	public ApplePushException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 构造器
	 * @param message	异常详细信息
	 */
	public ApplePushException(String message) {
		super(message);
	}

	/**
	 * 构造器
	 * @param cause	异常原因
	 */
	public ApplePushException(Throwable cause) {
		super(cause);
	}

}
