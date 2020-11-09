package com.taisf.services.device.dao;

import com.taisf.services.device.common.dao.DeviceBaseDao;
import com.taisf.services.device.entity.DeviceConfigEntity;
import com.taisf.services.device.entity.DeviceLinkEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 设备配置信息 </p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @version 1.0
 * @since 1.0
 */
@Repository("device.deviceConfigDao")
public class DeviceConfigDao extends DeviceBaseDao {

     private String SQLID="device.deviceConfigDao.";

    /**
     * 根据主键code查询设备情况
     * @param code
     * @return
     */
    public DeviceConfigEntity getDeviceConfigByCode(String code) {
        return mybatisDaoContext.findOneSlave(SQLID + "getDeviceConfigByCode", DeviceConfigEntity.class, code);
    }



}