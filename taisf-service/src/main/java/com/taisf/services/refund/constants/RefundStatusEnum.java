package com.taisf.services.refund.constants;


import com.jk.framework.base.utils.Check;

/**
 * <p>退款状态</p>
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
public enum RefundStatusEnum {



    WAIT(1,"待审核"),
    NO_PASS(2,"审核失败"),
    PASS(3,"审核成功"),
    SUCCESS(4,"打款成功"),
    FAIL(5,"打款失败"),
    SENDING(6,"打款中"),
    BREAK(7,"调用支付平台失败")


            ;
    private int code;
    private String name;

    private RefundStatusEnum(int code, String name) {
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

    /**
     * 获取code
     * @param code
     * @return
     */
    public static RefundStatusEnum getTypeByCode(Integer code) {
        if (Check.NuNObj(code)){
            return null;
        }
        RefundStatusEnum[] enums = RefundStatusEnum.values();
        for(RefundStatusEnum enumtype:enums) {
            if(enumtype.getCode() == code) {
                return enumtype;
            }
        }
        return null;
    }

}
