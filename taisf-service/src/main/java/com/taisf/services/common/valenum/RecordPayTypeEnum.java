package com.taisf.services.common.valenum;

import com.jk.framework.base.utils.Check;

/**
 * <p>支付类型</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2016/4/1.
 * @version 1.0
 * @since 1.0
 */
public enum RecordPayTypeEnum {

    //支付类型 1.微信 2.支付宝支付 3.余额支付

    MIX(-1,"混合支付"),
    WEIXIN(1,"微信支付"),
    ZHIFUBAO(2,"支付宝支付"),
    YUE(3,"余额支付")
    ;
    private int code;
    private String name;

    private RecordPayTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static RecordPayTypeEnum getTypeByCode(Integer code) {
        if(Check.NuNObj(code)){
            return null;
        }
        RecordPayTypeEnum[] enums = RecordPayTypeEnum.values();
        for(RecordPayTypeEnum enumtype:enums) {
            if(enumtype.getCode() == code) {
                return enumtype;
            }
        }
        return null;
    }

}
