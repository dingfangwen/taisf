package com.taisf.services.common.valenum;

import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.ValueUtil;

/**
 * <p>扫描类型</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi
 * @version 1.0
 * @since 1.0
 */
public enum AppScanTypeEnum {

    FACE_PAY("","面对面收款"),

    QISHOU_PAY("5","骑手收款"){
        @Override
        public String transScanCode(String code) {
            if (Check.NuNStr(code)){
                return "";
            }
            return QISHOU_PAY.getCode() + super.transScanCode(code);
        }


        @Override
        public String parseScanCode(String orgCode) {
            if (Check.NuNStr(orgCode)){
                return "";
            }
            return orgCode.substring(1);
        }
    },
    ;
    private String code;
    private String name;

    private AppScanTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    /**
     * 转化当前的code码
     * @param code
     * @return
     */
    public String transScanCode(String code){
        return ValueUtil.getStrValue(code);
    }

    /**
     * 解析当前的code嘛
     * @param orgCode
     * @return
     */
    public String parseScanCode(String orgCode){
        return orgCode;
    }


    /**
     * 转化当前的类型
     * @param code
     * @return
     */
    public static AppScanTypeEnum transScanType(String code) {
        AppScanTypeEnum scanTypeEnum = null;
        if (Check.NuNStr(code)){
            return scanTypeEnum;
        }
        AppScanTypeEnum[] enums = AppScanTypeEnum.values();
        for(AppScanTypeEnum enumtype:enums) {
            String has = enumtype.getCode();
            if (Check.NuNStr(has)){
                continue;
            }
            if(has == code) {
                return enumtype;
            }
        }
        return AppScanTypeEnum.FACE_PAY;
    }

}
