package com.jk.services.payment.constant;


/**
 * <p>微信app支付
 *  参考：https://pay.weixin.qq.com/wiki/doc/api/app.php?chapter=9_5&index=7
 * </p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/6/13.
 * @version 1.0
 * @since 1.0
 */
public enum TenRefundEnum {

    FAIL("FAIL","FAIL—退款失败"),
    CHANGE("CHANGE","转入代发，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干预，通过线下或者财付通转账的方式进行退款"),
    NOTSURE("NOTSURE","NOTSURE—未确定，需要商户原退款单号重新发起"),
    PROCESSING("PROCESSING","PROCESSING—退款处理中");

    private String key;
    private String value;

    TenRefundEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(String key){
        for (TenRefundEnum t : TenRefundEnum.values()){
            if(t.key == key){
                return t.value;
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
