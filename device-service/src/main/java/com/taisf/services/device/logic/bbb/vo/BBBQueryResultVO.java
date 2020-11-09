package com.taisf.services.device.logic.bbb.vo;

import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.device.common.valenum.DeviceStatusShowEnum;
import com.taisf.services.device.vo.CellModelCDVO;
import com.taisf.services.device.vo.DeviceCellCDVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2020/4/20 23:14
 * @version 1.0
 */
public class BBBQueryResultVO extends BBBResultBaseVO {


    private DeviceBBBVO resultObject ;


    public DeviceBBBVO getResultObject() {
        return resultObject;
    }

    public void setResultObject(DeviceBBBVO resultObject) {
        this.resultObject = resultObject;
    }


    public DeviceCellCDVO trans(){
        if (Check.NuNObj(resultObject)){
            return null;
        }

        DeviceCellCDVO cd = new DeviceCellCDVO();

        cd.setDeviceId(resultObject.getDeviceId());
        DeviceStatusShowEnum status =DeviceStatusShowEnum.UNKNOWN;
        String deviceStatus = resultObject.getDeviceStatus();
        if ("ONLINE".equals(deviceStatus)){
            status =DeviceStatusShowEnum.ON_LINE;
        }else if ("OFFLINE".equals(deviceStatus)){
            status =DeviceStatusShowEnum.OFF_LINE;
        }
        //设置状态
        cd.setStatus(status.getCode());
        cd.setStatusDesc(status.getName());

        List<CellBBBVO> cells = resultObject.getCells();
        if (!Check.NuNCollection(cells)){
            List<CellModelCDVO> list = new ArrayList<>();

            for (CellBBBVO cell : cells) {
                CellModelCDVO cellModelCDVO = new CellModelCDVO();
                cellModelCDVO.setCellNo(cell.getCellNo());

                String cellInfo = cell.getCellInfo();
                if (!Check.NuNStr(cellInfo)){
                    cellInfo = cleanStr(cellInfo);
                }
                Map<String,String> map = ( Map<String,String>)JsonEntityTransform.json2Map(cellInfo);
                //opening/closed/opened/closing
                String door = map.get("door");
                String warm = map.get("warm");
                String light = map.get("light");
                String disinfect = map.get("disinfect");

                // 1. 已开门 2.正在开门 3.已关门 4.正在关门门
                if (  "opened".equals(door) ){
                    cellModelCDVO.setFrontDoor(1);
                }else if ("opening".equals(door)){
                    cellModelCDVO.setFrontDoor(2);
                }else{
                    cellModelCDVO.setFrontDoor(3);
                }

                // 开灯状态 1.开灯成功 2.开灯失败 3.关灯成功 4.关灯失败
                if ("opening".equals(light) ||  "opened".equals(light) ){
                    cellModelCDVO.setFrontLight(1);
                }else{
                    cellModelCDVO.setFrontLight(3);
                }

                // 加热状态（1.正在加热 2.加热失败）
                //starting/stopping/started/stopped
                if ("starting".equals(warm) ||  "started".equals(warm) ){
                    cellModelCDVO.setWarm(1);
                }else{
                    cellModelCDVO.setWarm(2);
                }
                list.add(cellModelCDVO);
            }
            cd.setCells(list);

        }
        return cd;

    }

    /**
     * 去掉字符串
     * @param json
     * @return
     */
    private static String cleanStr(String  json){
        return json.replace("\\\"","\"");
    }
}
