package com.taisf.services.order.dto;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2020/2/9.
 * @version 1.0
 * @since 1.0
 */
public class BackRefundOrderRequest  extends RefundOrderRequest {

    /**
     * 退款金额
     */
    private Integer refundMoney;


    public Integer getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(Integer refundMoney) {
        this.refundMoney = refundMoney;
    }
}
