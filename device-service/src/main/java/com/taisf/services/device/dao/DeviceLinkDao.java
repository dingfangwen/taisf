package com.taisf.services.device.dao;

import com.taisf.services.device.common.dao.DeviceBaseDao;
import com.taisf.services.device.entity.DeviceLinkEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 设备绑定表(DeviceLinkEntity)表数据库访问层 </p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @version 1.0
 * @since 1.0
 */
@Repository("device.deviceLinkDao")
public class DeviceLinkDao  extends DeviceBaseDao {

     private String SQLID="device.deviceLinkDao.";

    /**
     * 根据主键code查询设备情况
     * @param code
     * @return
     */
    public DeviceLinkEntity findOneDeviceLink(Integer code) {
        return mybatisDaoContext.findOne(SQLID + "findOneDeviceLink", DeviceLinkEntity.class, code);
    }

    /**
     * 根据订单号,获取设备信息
     * @param orderSn
     * @return
     */
    public DeviceLinkEntity findOneDeviceByOrderSn(String orderSn) {
        return mybatisDaoContext.findOne(SQLID + "findOneDeviceByOrderSn", DeviceLinkEntity.class, orderSn);
    }


    /**
     * 根据parentCode查询子区域
     * @param type
     * @return
     */
    public List<DeviceLinkEntity> findDeviceLinkOccupyByDeviceId(String  deviceId) {
        Map<String, Object> params = new HashMap<>();
        params.put("deviceId", deviceId);
        return mybatisDaoContext.findAll(SQLID + "findDeviceLinkOccupyByDeviceId", DeviceLinkEntity.class, params);
    }




    /**
     * 新增数据
     *
     * @param DeviceLinkEntity 实例对象
     * @return 影响行数
     */
    public int saveDeviceLink(DeviceLinkEntity DeviceLinkEntity){
        return mybatisDaoContext.save(SQLID + "saveDeviceLink", DeviceLinkEntity);
    }

    /**
     * 修改数据
     *
     * @param DeviceLinkEntity 实例对象
     * @return 影响行数
     */
    public  int updateDeviceLink(DeviceLinkEntity DeviceLinkEntity){
        return mybatisDaoContext.update(SQLID + "updateDeviceLink", DeviceLinkEntity);
    }


}