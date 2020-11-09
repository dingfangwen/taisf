package com.taisf.services.device.api;

import com.jk.framework.base.entity.DataTransferObject;
import com.taisf.services.device.dto.PushMessage;
import com.taisf.services.device.entity.DeviceEntity;
import com.taisf.services.push.request.CellApplyRequest;
import com.taisf.services.push.request.CellCloseRequest;
import com.taisf.services.push.request.MoneySendRequest;

import java.util.List;

/**
 * <p>推送相关接口</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/8/29.
 * @version 1.0
 * @since 1.0
 */
public interface PushService {


    /**
     * 取餐成功
     * @param cellCloseRequest
     * @return
     */
    DataTransferObject<Void> sendCloseCellSuccess(CellCloseRequest cellCloseRequest);


    /**
     * 申请格子成功
     * @param cellApplyRequest
     * @return
     */
    DataTransferObject<Void> sendApplyCellSuccess(CellApplyRequest cellApplyRequest);


    /**
     * 发送消息
     * @param moneySendRequest
     * @return
     */
    DataTransferObject<Void> sendMoneySuccess(MoneySendRequest moneySendRequest);

    /**
     * @Description 登记和更新设备
     * @param deviceEnity
     * @return
     */
    DataTransferObject<Void> registDevice(DeviceEntity deviceEnity);

    /**
     * @Description 用户退出时注销设备令牌
     * @param deviceEnity
     * @return
     */
    DataTransferObject<Void>  logoutDevice(DeviceEntity deviceEnity);


    /**
     * 推送消息到指定用户
     * @param userIds
     * @param pushMessage
     * @return
     */
    DataTransferObject<Void> pushMessage(List<String> userIds, PushMessage pushMessage);


}
