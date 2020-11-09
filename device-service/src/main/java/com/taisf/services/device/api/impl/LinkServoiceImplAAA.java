package com.taisf.services.device.api.impl;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.exception.BusinessException;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.ValueUtil;
import com.taisf.services.device.api.LinkServoice;
import com.taisf.services.device.api.abs.LinkAbstractServoice;
import com.taisf.services.device.common.constant.LinkConstant;
import com.taisf.services.device.common.valenum.CellUserStatus;
import com.taisf.services.device.common.valenum.DeviceStatusShowEnum;
import com.taisf.services.device.dao.DeviceConfigDao;
import com.taisf.services.device.entity.DeviceConfigEntity;
import com.taisf.services.device.logic.aaa.command.client.LinkClient;
import com.taisf.services.device.logic.aaa.command.model.LinkCellModel;
import com.taisf.services.device.logic.aaa.command.request.*;
import com.taisf.services.device.logic.aaa.command.response.*;
import com.taisf.services.device.vo.DeviceCellCDVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p></p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2020/4/12 15:13
 * @version 1.0
 */
@Service(LinkConstant.factoryA)
public class LinkServoiceImplAAA  extends LinkAbstractServoice implements LinkServoice {


    @Resource(name = "device.deviceConfigDao")
    private DeviceConfigDao deviceConfigDao;


    /**
     * 获取客户端
     * @return
     */
    private LinkClient client(){
        DeviceConfigEntity config = deviceConfigDao.getDeviceConfigByCode(getConfigCode());
        if (Check.NuNObj(config)){
            throw new BusinessException("获取设备配置信息异常");
        }
        LinkClient linkClient = new LinkClient(config);
        return linkClient;
    }


    /**
     * 获取当前设备状态
     * @param deviceId
     * @return
     */
    @Override
    public DeviceStatusShowEnum getDeviceStatusByDeviceId(String deviceId){
        DeviceStatusShowEnum deviceStatusShowEnum = DeviceStatusShowEnum.UNKNOWN;


        LinkGetDeviceStatusRequest request = new LinkGetDeviceStatusRequest();
        request.setDeviceId(deviceId);
        //处理验签
        client().trans2Sign(request);
        //返回执行结果
        LinkGetDeviceStatusResponse response =  client().execute(request);
        if(response.getSuccess()){
            deviceStatusShowEnum = DeviceStatusShowEnum.getByCode(response.getData().getStatus());
        }
        if (Check.NuNObj(deviceStatusShowEnum)){
            deviceStatusShowEnum = DeviceStatusShowEnum.UNKNOWN;
        }
        return deviceStatusShowEnum;
    }

    /**
     * 获取设备状态
     *
     * @param deviceId 设备ID
     * @return
     */
    @Override
    public DataTransferObject<DeviceCellCDVO> getDeviceCell(String deviceId) {

        LinkGetDeviceAllCellStatusRequest request = new LinkGetDeviceAllCellStatusRequest();
        request.setDeviceId(deviceId);
        //处理验签
        client().trans2Sign(request);
        //返回执行结果
        LinkGetDeviceAllCellStatusResponse response =  client().execute(request);
        //转化成设备状态
        return response.transCd();


//        LinkGetDeviceStatusRequest request = new LinkGetDeviceStatusRequest();
//        request.setDeviceId(deviceId);
//        //处理验签
//        client().trans2Sign(request);
//        //返回执行结果
//        LinkGetDeviceStatusResponse response =  client().execute(request);
//        //转化成设备状态
//        return response.transCd();
    }


    /**
     * 获取当前设备的所有格子状态
     * @param deviceId 设备ID
     * @return
     */
    @Override
    public CellUserStatus getDeviceOneCellStatus(String deviceId, String cellNo) {
        CellUserStatus cellUserStatus = null;
        LinkGetDeviceCellStatusRequest request = new LinkGetDeviceCellStatusRequest();
        request.setDeviceId(deviceId);
        request.setCellNo(cellNo);
        //处理验签
        client().trans2Sign(request);
        //返回执行结果
        LinkGetDeviceAllCellStatusResponse allCellStatusResponse = client().execute(request);
        if (allCellStatusResponse.getSuccess()){
            List<LinkCellModel> cells = allCellStatusResponse.getData().getCells();
            if (Check.NuNCollection(cells)){
                for (LinkCellModel cell : cells) {
                    if (ValueUtil.getStrValue(cellNo).equals(ValueUtil.getStrValue(cell.getCellNo()))){
                        cellUserStatus = CellUserStatus.getByCode(cell.getUseStatus());
                        break;
                    }
                }
            }
        }
        return cellUserStatus;
    }



    /**
     * 根据订单号申请单元格
     * @param deviceId
     * @param orderSn
     * @return
     */
    @Override
    public DataTransferObject<String> applyCellByOrderSn(String deviceFid,String deviceId, String orderSn) {
        LinkApplyCellRequest request = new LinkApplyCellRequest();
        request.setDeviceId(deviceId);
        request.setOutOrderId(orderSn);
        request.setApplyNum(1);
        //处理验签
        client().trans2Sign(request);
        //返回执行结果
        LinkApplyCellResponse response =  client().execute(request);
        //转化成设备状态
        return response.transCd();
    }


    /**
     * 强制开启格子
     * @param deviceId
     * @param cellNos
     * @return
     */
    @Override
    public DataTransferObject forceOpenCell(String deviceId, String cellNos) {
        LinkRunCommandRequest request = new LinkRunCommandRequest();
        request.setDeviceId(deviceId);
        request.setCellNos("["+cellNos+"]");
        //强制开门的逻辑
        request.setCommandCode("1013");
        //处理验签
        client().trans2Sign(request);
        //返回执行结果
        LinkRunCommandResponse response =  client().execute(request);
        //转化成设备状态
        return response.transCd();
    }

    @Override
    public DataTransferObject openCellByOrderSnRelease(String deviceId, String orderSn) {
        LinkOpenCellRequest request = new LinkOpenCellRequest();
        request.setDeviceId(deviceId);
        request.setOutOrderId(orderSn);
        //处理验签
        client().trans2Sign(request);
        //返回执行结果
        LinkOpenCellResponse response = client().execute(request);
        return response.transCd();
    }




    /**
     * 获取code码
     * @return
     */
    @Override
    protected String getConfigCode() {
        return LinkConstant.factoryA;
    }
}
