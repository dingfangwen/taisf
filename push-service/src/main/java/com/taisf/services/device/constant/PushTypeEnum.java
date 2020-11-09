package com.taisf.services.device.constant;

import com.taisf.services.push.constant.PushHandleConstant;

/**
 * <p>是否</p>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi
 * @since 1.0
 * @version 1.0
 */
public enum PushTypeEnum {
	APPLE(1, "苹果"){
		@Override
		public String getHandle() {
			return PushHandleConstant.HANDLE_APPLE;
		}
	},
	XIAOMI(2, "小米"){
		@Override
		public String getHandle() {
			return PushHandleConstant.HANDLE_XIAOMI;
		}
	},
	HUAWEI(3, "华为"){
		@Override
		public String getHandle() {
			return PushHandleConstant.HANDLE_HUAWEI;
		}
	},
	JIGUANG(4, "极光"){
		@Override
		public String getHandle() {
			return PushHandleConstant.HANDLE_JIGUANG;
		}
	},
	APPLE_USER(11, "苹果-用户"){
		@Override
		public String getHandle() {
			return PushHandleConstant.HANDLE_APPLE_USER;
		}
	},
	JIGUANG_USER(14, "极光-用户"){
		@Override
		public String getHandle() {
			return PushHandleConstant.HANDLE_JIGUANG_USER;
		}
	},



	;

	PushTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}



	public String getHandle(){
		return null;
	}

	/** code */
	private int code;

	/** 名称 */
	private String name;

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}


	/**
	 * 通过code获取推送类型
	 * @param code
	 * @return
	 */
	public static PushTypeEnum getTypeByCode(int code) {
		PushTypeEnum[] enums = PushTypeEnum.values();
		for(PushTypeEnum enumtype:enums) {
			if(enumtype.getCode() == code) {
				return enumtype;
			}
		}
		return null;
	}


}
