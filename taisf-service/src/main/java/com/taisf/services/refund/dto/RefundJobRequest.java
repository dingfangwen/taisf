package com.taisf.services.refund.dto;

import com.jk.framework.base.page.PageRequest;

/**
 * <p>退款操作</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/3/25.
 * @version 1.0
 * @since 1.0
 */
public class RefundJobRequest extends PageRequest {

    private static final long serialVersionUID = 301222341446703L;

    /**
     * 退款状态
     */
    private Integer refundStatus;

    /**
     * 退款单号
     */
    private String refundSn;

    public String getRefundSn() {
        return refundSn;
    }

    public void setRefundSn(String refundSn) {
        this.refundSn = refundSn;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }
}
