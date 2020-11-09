package com.jk.services.payment.constant;

/**
 * 支付的常量
 */
public class PayConstants {
	public static final String REQUEST  = "request";
	public static final String RESPONSE = "response";
	public static final String SUCCESS_STATUS = "0";
	public static final String EXCEPTION_STATUS = "40000";
	public static final String SUCCESS = "SUCCESS";
	public static final String FAIL = "fail";

	/**
	 * 支付相关的操作
	 */
	public enum OpType {
		TYPE_PAY("01","支付"),
		TYPE_REFUND("02","退款"),
		TYPE_TOBANK("03","转账"),
		TYPE_WITHDRAW("04","提现"),
		TYPE_FEE("05","手续费")
		;


		private String code;

		private String name;

		OpType(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public String getCode() {
			return code;
		}
		public String getName() {
			return name;
		}
	}



	/**
	 * 支付相关的操作
	 */
	public enum HandleType {
//		Handle_alipay_Refund("aliRefundHandle","支付宝退款"),
		Handle_tenpay_Refund("tenpayRefundHandle","微信退款"),
		Handle_alipay_App("alipayAppHandle","支付宝app支付"),
		Handle_tenpay_App("tenpayAppHandle","微信App支付"),
		Handle_weChat_Wap("weChatWapHandle","公共号支付");


		private String code;

		private String name;

		HandleType(String code, String name) {
			this.code = code;
			this.name = name;
		}
		public String getCode() {
			return code;
		}
		public String getName() {
			return name;
		}
	}


	/**
	 * 支付状态
	 */
	public enum PayStatus {
		STATUS_SUCCESS(0,"0","成功"),
		STATUS_10000(10000,"10000","保存数据"),
		STATUS_20000(20000,"20000","请求"),
		STATUS_30000(30000,"30000","等待"),
		STATUS_40000(40000,"40000","异常"),
		STATUS_50000(50000,"50000","关闭"),
		STATUS_60000(60000,"60000","支付成功"),
		STATUS_70000(70000,"70000","通知成功"),
		STATUS_80000(80000,"80000","异常通知成功")
		;


		private int code;

		private String codeStr;

		private String name;

		PayStatus(int code,String codeStr, String name) {
			this.code = code;
			this.codeStr = codeStr;
			this.name = name;
		}

		public int getCode() {
			return code;
		}
		public String getName() {
			return name;
		}

		public String getCodeStr() {
			return codeStr;
		}
	}

	
}
