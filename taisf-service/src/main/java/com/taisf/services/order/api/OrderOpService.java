package com.taisf.services.order.api;

import com.jk.framework.base.entity.DataTransferObject;
import com.taisf.services.order.dto.BuffetInOrderRequest;
import com.taisf.services.order.dto.BuffetOutOrderRequest;
import com.taisf.services.order.dto.FinishOrderRequest;

/**
 * <p>订单相关接口</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/28.
 * @version 1.0
 * @since 1.0
 */
public interface OrderOpService {


    /**
     * 系统签收
     * @author afi
     * @param buffetOutOrderRequest
     * @return
     */
    DataTransferObject<Void> buffetOutOrderBySystemCloseDao(BuffetOutOrderRequest buffetOutOrderRequest,String configCode,String deviceId, String cellNo );

    /**
     * 后台去强制取餐
     * @author afi
     * @param buffetOutOrderRequest
     * @return
     */
    DataTransferObject<Void> forceBuffetOutOrder(BuffetOutOrderRequest buffetOutOrderRequest);

    /**
     * 患者取餐的逻辑
     * @author afi
     * @param buffetOutOrderRequest
     * @return
     */
    DataTransferObject<Void> buffetOutOrder(BuffetOutOrderRequest buffetOutOrderRequest);



    /**
     * 结束订单
     * @author afi
     * @param finishOrderRequest
     * @return
     */
    DataTransferObject<Void>  finishOrder(FinishOrderRequest finishOrderRequest);

    /**
     * 放格子
     * @author afi
     * @param buffetInOrderRequest
     * @return
     */
    DataTransferObject<Void> buffetInOrder(BuffetInOrderRequest buffetInOrderRequest);

}
