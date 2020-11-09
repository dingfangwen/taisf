package com.taisf.services.device.logic;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.Check;
import com.jk.framework.cache.redis.api.RedisOperations;
import com.taisf.services.device.dao.DeviceLinkDao;
import com.taisf.services.device.entity.DeviceLinkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * <p></p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2020/4/24 15:14
 * @version 1.0
 */
@Component("deviceApplyQueen")
public class DeviceApplyQueen {


    @Resource(name = "device.deviceLinkDao")
    private DeviceLinkDao deviceLinkDao;

    @Autowired
    private RedisOperations redisOperations;


    /**
     * 同步处理当前的初始化方法
     * @param dto
     * @param deviceId
     */
    public synchronized void  synCell2Queen(DataTransferObject<String> dto, String deviceId, String queen, Set<String> setCell){

        if (!dto.checkSuccess()){
            return;
        }
        if (Check.NuNStr(deviceId)){
            dto.setErrorMsg("申请格子参数异常");
            return;
        }
        if (Check.NuNCollection(setCell)){
            dto.setErrorMsg("当前设备已满");
            return;
        }
        List<DeviceLinkEntity> deviceIdList = deviceLinkDao.findDeviceLinkOccupyByDeviceId(deviceId);
        if (!Check.NuNCollection(deviceIdList)){
            for (DeviceLinkEntity deviceLinkEntity : deviceIdList) {
                String hasSn = deviceLinkEntity.getCellSn();
                setCell.remove(hasSn);
            }
        }
        if (Check.NuNCollection(setCell)){
            dto.setErrorMsg("当前设备已满");
            return;
        }
        String queenKey = queen + deviceId;
        for (String ce : setCell) {
            redisOperations.lpush(queenKey,ce);
        }
        String cellSn = redisOperations.rpop(queenKey);
        if(!Check.NuNStr(cellSn)){
            dto.setData(cellSn);
            return;
        }

        //处理这里已经没有格子可用了
        dto.setErrorMsg("当前设备已满");
        return;
    }



}
