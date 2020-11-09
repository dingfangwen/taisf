package com.taisf.services.device.dao;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.dao.page.PageBounds;
import com.taisf.services.device.entity.DeviceEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("push.deviceDao")
public class DeviceDao extends BaseDao{
	 /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(DeviceDao.class);

    private String SQLID = "push.deviceDao.";
    
    public DeviceEntity getDeviceByRegId(String regId){
        return mybatisDaoContext.findOne(SQLID + "findDeviceByRegId", DeviceEntity.class, regId);
    }

    public DeviceEntity getDeviceByUserId(String userId){
        return mybatisDaoContext.findOne(SQLID + "findDeviceByUserId", DeviceEntity.class, userId);
    }

    public PagingResult<DeviceEntity> listDeviceByPushType(Map<String,Object> param){
        PageBounds pageBounds = new PageBounds();
        pageBounds.setLimit((Integer) param.get("limit"));
        pageBounds.setPage((Integer)param.get("page"));
        return mybatisDaoContext.findForPage(SQLID + "listDeviceByPushType", DeviceEntity.class, String.valueOf(param.get("pushType")), pageBounds);
    }



    public List<DeviceEntity> listDeviceByUserId(List<String> userIds){
        if(Check.NuNCollection(userIds)){
            return null;
        }
        return mybatisDaoContext.findAll(SQLID + "listDeviceByUserId", DeviceEntity.class, userIds);
    }




    public int replaceDevice(DeviceEntity entity){
        return mybatisDaoContext.save(SQLID + "replaceDevice", entity);
    }


    public int addDevice(DeviceEntity entity){
        return mybatisDaoContext.save(SQLID + "addDevice", entity);
    }

    public int updateDevice(DeviceEntity entity){
        return mybatisDaoContext.update(SQLID + "updateByRegId", entity);
    }

    /**
     * 解除用户和设备的绑定
     * @param entity
     * @return
     */
    public int unBindUserId(DeviceEntity entity){
        return mybatisDaoContext.update(SQLID + "unBindUserId", entity);
    }


    
}