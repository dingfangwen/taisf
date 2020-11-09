package com.taisf.services.device.api;

import com.jk.framework.base.entity.DataTransferObject;
import com.taisf.services.device.common.valenum.CellUserStatus;
import com.taisf.services.device.common.valenum.DeviceStatusShowEnum;
import com.taisf.services.device.vo.DeviceCellCDVO;

/**
 * <p>处理逻辑</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2019/3/26.
 * @version 1.0
 * @since 1.0
 */

public interface LinkServoice {






    /**
     * 获取当前设备状态
     * @param deviceId
     * @return
     */
    DeviceStatusShowEnum getDeviceStatusByDeviceId(String deviceId);

    /**
    * 获取设备状态
    *
    * @param deviceId 设备ID
    * @return
    */
    DataTransferObject<DeviceCellCDVO> getDeviceCell(String deviceId);


    /**
     * 获取当前设备的所有格子状态
     * @param deviceId 设备ID
     * @return
     */
    CellUserStatus getDeviceOneCellStatus(String deviceId, String cellNo);



    /**
     * 根据订单号申请单元格
     * @param deviceId
     * @param orderSn
     * @return
     */
   DataTransferObject<String> applyCellByOrderSn(String deviceFid,String deviceId, String orderSn);
    /**
     * 强制开启格子
     * @param deviceId
     * @param cellNos
     * @return
     */
    DataTransferObject forceOpenCell(String deviceId, String cellNos);


    /**
     * 根据订单编号,重置格子
     * @param deviceId
     * @param orderSn
     * @return
     */
    DataTransferObject openCellByOrderSnRelease(String deviceId, String orderSn);

}
