package com.taisf.services.device.api.impl;

import com.jk.framework.base.constant.YesNoEnum;
import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.DateUtil;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.cache.redis.api.RedisOperations;
import com.jk.framework.common.utils.CloseableHttpUtil;
import com.taisf.services.device.api.LinkServoice;
import com.taisf.services.device.api.abs.LinkAbstractServoice;
import com.taisf.services.device.common.constant.LinkConstant;
import com.taisf.services.device.common.valenum.CellUserStatus;
import com.taisf.services.device.common.valenum.DeviceStatusShowEnum;
import com.taisf.services.device.common.valenum.LinkStatus;
import com.taisf.services.device.dao.DeviceConfigDao;
import com.taisf.services.device.dao.DeviceLinkDao;
import com.taisf.services.device.entity.DeviceConfigEntity;
import com.taisf.services.device.entity.DeviceLinkEntity;
import com.taisf.services.device.logic.DeviceApplyQueen;
import com.taisf.services.device.logic.bbb.SignUtilsMD5;
import com.taisf.services.device.logic.bbb.vo.BBBQueryResultVO;
import com.taisf.services.device.logic.bbb.vo.BBBResultBaseVO;
import com.taisf.services.device.vo.CellModelCDVO;
import com.taisf.services.device.vo.DeviceCellCDVO;
import com.taisf.services.device.vo.SimpleOp;
import com.taisf.services.device.vo.SimpleOpEle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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

@Service(LinkConstant.factoryB)
public class LinkServoiceImplBBB extends LinkAbstractServoice implements LinkServoice {


    @Resource(name = "device.deviceConfigDao")
    private DeviceConfigDao deviceConfigDao;

    @Resource(name = "deviceApplyQueen")
    private DeviceApplyQueen deviceApplyQueen;



    @Resource(name = "device.deviceLinkDao")
    private DeviceLinkDao deviceLinkDao;

    @Autowired
    private RedisOperations redisOperations;




    /**
     * 获取当前设备状态
     * @param deviceId
     * @return
     */
    @Override
    public DeviceStatusShowEnum getDeviceStatusByDeviceId(String deviceId){
        DeviceStatusShowEnum deviceStatusShowEnum = DeviceStatusShowEnum.UNKNOWN;
        DataTransferObject<DeviceCellCDVO> dto = this.getDeviceCell(deviceId);
        if (!dto.checkSuccess()){
            return deviceStatusShowEnum;
        }
        DeviceCellCDVO deviceStatus = dto.getData();
        deviceStatusShowEnum = DeviceStatusShowEnum.getByCode(deviceStatus.getStatus());
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

        DataTransferObject<DeviceCellCDVO> dto = new DataTransferObject<>();
        if (Check.NuNStr(deviceId)){
            dto.setErrorMsg("参数异常");
            return dto;
        }

        DeviceConfigEntity config = deviceConfigDao.getDeviceConfigByCode(getConfigCode());
        if (Check.NuNObj(config)){
            dto.setErrorMsg("异常的供应商配置信息");
            return dto;
        }
        // 请求的参数地址
        String url =  config.getServerUrl() ;

        Map<String,Object> par = new HashMap<>();
        par.put("appId",config.getAppId());
        par.put("method","device.status.query");
        par.put("version","1.0");

        Map<String,String> real = new HashMap<>();
        real.put("deviceId",deviceId);
        par.put("bizData",real);
        String s = CloseableHttpUtil.sendFormPost(url, signMap(par,config.getAppSecret()));
        BBBQueryResultVO bbbQueryResultVO = JsonEntityTransform.json2Entity(s, BBBQueryResultVO.class);
        if (!bbbQueryResultVO.getSuccess()){
            dto.setErrorMsg(bbbQueryResultVO.getMsg());
            return dto;
        }
        dto.setData(bbbQueryResultVO.trans());
        return dto;
    }


    /**
     * 去掉字符串
     * @param json
     * @return
     */
    private static String cleanStr(String  json){
        return json.replace("\\\"","\"");
    }


    /**
     * 签名
     * @param par
     * @param key
     * @return
     */
    private static Map<String,String>  signMap(Map<String,Object> par,String key){
        par.put("timestamp", DateUtil.timestampFormat(new Date()));
        String digest = SignUtilsMD5.md5Sign(key,par);
        par.put("digest",digest);
        Map<String,String> map = new HashMap<>();
        for (String s : par.keySet()) {
            Object value = par.get(s);
            if (value instanceof String){
                map.put(s,(String) value);
            }else {
                String json = JsonEntityTransform.Object2Json(par.get(s));
                map.put(s,cleanStr(json));
            }
        }
        return map;
    }



    /**
     * 获取当前设备的所有格子状态
     * @param deviceId 设备ID
     * @return
     */
    @Override
    public CellUserStatus getDeviceOneCellStatus(String deviceId, String cellNo) {

        return null;
    }


    /**
     * 根据订单号申请单元格
     * @param deviceId
     * @param orderSn
     * @return
     */
    @Override
    public DataTransferObject<String> applyCellByOrderSn(String deviceFid,String deviceId, String orderSn) {
        DataTransferObject<String> dto = new DataTransferObject<>();
        if (Check.NuNStr(deviceId)){
            dto.setErrorMsg("异常的设备参数");
            return dto;
        }
        if (Check.NuNStr(orderSn)){
            dto.setErrorMsg("异常的订单号");
            return dto;
        }
        DeviceConfigEntity config = deviceConfigDao.getDeviceConfigByCode(getConfigCode());
        if (Check.NuNObj(config)){
            dto.setErrorMsg("异常的供应商配置信息");
            return dto;
        }
        DeviceLinkEntity has = deviceLinkDao.findOneDeviceByOrderSn(orderSn);
        if (!Check.NuNObj(has)){
            Integer linkStatus = has.getLinkStatus();
            if (linkStatus == YesNoEnum.YES.getCode()){
                dto.setData(has.getCellSn());
            }else {
                dto.setErrorMsg("订单已经释放");
            }
            return dto;
        }
        //处理申请格子的逻辑
        this.dealApplyCell(dto,deviceFid,deviceId,orderSn,config);
        //返回结果
        return dto;
    }


    /**
     * 获取当前的格子,并且打开格子
     * @param dto
     * @param deviceId
     * @param config
     */
    private void   dealApplyCell(DataTransferObject<String> dto,String deviceFid,String deviceId,String orderSn,DeviceConfigEntity config){
        if (!dto.checkSuccess()){
            return;
        }
        if (Check.NuNStr(deviceId)){
            dto.setErrorMsg("申请格子参数异常");
            return;
        }

        if (Check.NuNObj(config)){
            dto.setErrorMsg("异常的配置信息");
            return;
        }
        this.dealApplyCellAndOpen(dto,deviceId,config);
        if (dto.checkSuccess()){
            String cellSn = dto.getData();

            DeviceLinkEntity entity = new DeviceLinkEntity();
            entity.setCellSn(cellSn);
            entity.setOrderSn(orderSn);
            entity.setBuffetFid(deviceFid);
            entity.setDeviceId(deviceId);
            entity.setLinkStatus(LinkStatus.LINK_SUCCESS.getCode());
            deviceLinkDao.saveDeviceLink(entity);
            dto.setData(cellSn);
        }
    }
    /**
     * 获取当前的格子,并且打开格子
     * @param dto
     * @param deviceId
     * @param config
     */
    private void   dealApplyCellAndOpen(DataTransferObject<String> dto,String deviceId,DeviceConfigEntity config){
        if (!dto.checkSuccess()){
            return;
        }
        if (Check.NuNStr(deviceId)){
            dto.setErrorMsg("申请格子参数异常");
            return;
        }
        DataTransferObject<DeviceCellCDVO> deviceInfo = this.getDeviceCell(deviceId);
        DeviceCellCDVO device = deviceInfo.getData();
        DeviceStatusShowEnum deviceStatusShowEnum = DeviceStatusShowEnum.getByCode(device.getStatus());
        if (Check.NuNObj(deviceStatusShowEnum)){
            deviceStatusShowEnum = DeviceStatusShowEnum.UNKNOWN;
        }
        if (deviceStatusShowEnum.getCode() != DeviceStatusShowEnum.ON_LINE.getCode()){
            dto.setErrorMsg("当前设备不在线");
            return;
        }

        String queenKey = getQueenKey() + deviceId;
        String cellSn = redisOperations.rpop(queenKey);
        if(!Check.NuNStr(cellSn)){
           dto.setData(cellSn);
            //打开门的操作
            this.open(dto,config,deviceId,cellSn,true);
           return;
        }

        //如果cellSn 为空,直接走初始
        Set<String> setCell = new HashSet<>();
        List<CellModelCDVO> cells = device.getCells();
        if (Check.NuNCollection(cells)){
            dto.setErrorMsg("未申请到格子");
            return;
        }
        for (CellModelCDVO cell : cells) {
            setCell.add(cell.getCellNo());
        }
        //调用同步的初始化队列的逻辑
        deviceApplyQueen.synCell2Queen(dto,deviceId,getQueenKey(),setCell);
        if (!dto.checkSuccess()){
            return;
        }
        String sn = dto.getData();

        //打开门的操作
        this.open(dto,config,deviceId,sn,true);
    }








    /**
     * 强制开启格子
     * @param deviceId
     * @param cellNos
     * @return
     */
    @Override
    public DataTransferObject forceOpenCell(String deviceId, String cellNos) {
        DataTransferObject dto = new DataTransferObject<>();
        if (Check.NuNStr(deviceId)){
            dto.setErrorMsg("异常的设备参数");
            return dto;
        }
        if (Check.NuNStr(cellNos)){
            dto.setErrorMsg("异常的格子号");
            return dto;
        }

        DeviceConfigEntity config = deviceConfigDao.getDeviceConfigByCode(getConfigCode());
        if (Check.NuNObj(config)){
            dto.setErrorMsg("异常的供应商配置信息");
            return dto;
        }
        open(dto,config,deviceId,cellNos,false);
        return dto;
    }

    @Override
    public DataTransferObject openCellByOrderSnRelease(String deviceId, String orderSn) {
        DataTransferObject dto = new DataTransferObject<>();
        if (Check.NuNStr(deviceId)){
            dto.setErrorMsg("异常的设备参数");
            return dto;
        }
        if (Check.NuNStr(orderSn)){
            dto.setErrorMsg("异常的订单号");
            return dto;
        }
        DeviceConfigEntity config = deviceConfigDao.getDeviceConfigByCode(getConfigCode());
        if (Check.NuNObj(config)){
            dto.setErrorMsg("异常的供应商配置信息");
            return dto;
        }
        DeviceLinkEntity has = deviceLinkDao.findOneDeviceByOrderSn(orderSn);
        if (Check.NuNObj(has)){
            dto.setErrorMsg("当前订单不存在");
            return dto;

        }
        Integer linkStatus = has.getLinkStatus();
        if (linkStatus == 2){
            return dto;
        }

        this.releaseCell(dto,config,deviceId,has.getCellSn());
        if (dto.checkSuccess()){
            DeviceLinkEntity entity = new DeviceLinkEntity();
            entity.setOrderSn(orderSn);
            entity.setLinkStatus(LinkStatus.LINK_RELEASE.getCode());
            deviceLinkDao.updateDeviceLink(entity);
        }
        return dto;
    }


    /**
     * 打开格子
     * @param config
     * @param deviceId
     * @param cellSn
     */
    private void releaseCell(DataTransferObject dto,DeviceConfigEntity config,String deviceId,String cellSn){
        if (!dto.checkSuccess()){
            return;
        }

        String url =  config.getServerUrl() ;

        Map<String,Object> par = new HashMap<>();
        par.put("appId",config.getAppId());
        par.put("method","device.cells.ops");
        par.put("version","1.0");
        SimpleOp simpleOp = new SimpleOp(deviceId);

        SimpleOpEle ele = new SimpleOpEle();
        ele.setCellNo(cellSn);
        List<String> ops = new ArrayList<>();
        ops.add("DOOR_OPEN");
        ops.add("LIGHT_CLOSE");
        ops.add("WARM_STOP");
        ele.setOps(ops);
        simpleOp.addOp(ele);
        par.put("bizData",simpleOp);
        String s = CloseableHttpUtil.sendFormPost(url, signMap(par,config.getAppSecret()));
        BBBResultBaseVO resultBaseVO = JsonEntityTransform.json2Entity(s, BBBResultBaseVO.class);
        if (!resultBaseVO.getSuccess()){
            dto.setErrorMsg(resultBaseVO.getMsg());
        }
    }

    /**
     * 打开格子
     * @param config
     * @param deviceId
     * @param cellSn
     */
    private void open(DataTransferObject dto,DeviceConfigEntity config,String deviceId,String cellSn,boolean warm){
        if (!dto.checkSuccess()){
            return;
        }

        String url =  config.getServerUrl() ;

        Map<String,Object> par = new HashMap<>();
        par.put("appId",config.getAppId());
        par.put("method","device.cells.ops");
        par.put("version","1.0");
        SimpleOp simpleOp = new SimpleOp(deviceId);

        SimpleOpEle ele = new SimpleOpEle();
        ele.setCellNo(cellSn);
        List<String> ops = new ArrayList<>();
        ops.add("DOOR_OPEN");
        if (warm){
            ops.add("LIGHT_OPEN");
            ops.add("WARM_START");
        }
        ele.setOps(ops);
        simpleOp.addOp(ele);
        par.put("bizData",simpleOp);
        String s = CloseableHttpUtil.sendFormPost(url, signMap(par,config.getAppSecret()));
        BBBResultBaseVO resultBaseVO = JsonEntityTransform.json2Entity(s, BBBResultBaseVO.class);
        if (!resultBaseVO.getSuccess()){
            dto.setErrorMsg(resultBaseVO.getMsg());
        }
    }


    /**
     * 获取code码
     * @return
     */
    @Override
    protected String getConfigCode() {
        return LinkConstant.factoryB;
    }
}
