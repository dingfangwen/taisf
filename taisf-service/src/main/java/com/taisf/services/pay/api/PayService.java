package com.taisf.services.pay.api;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.head.Header;
import com.taisf.services.pay.dto.PayRecordRequest;

/**
 * <p>订单支付</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/3/27.
 * @version 1.0
 * @since 1.0
 */
public interface PayService {

    /**
     * 支付回调信息
     * @author  afi
     * @param payRecordRequest
     * @return
     */
     DataTransferObject<Void> dealOrderPay(PayRecordRequest payRecordRequest);
}
