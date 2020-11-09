package com.taisf.services.device.manager;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.ValueUtil;
import com.taisf.services.device.dao.DeviceDao;
import com.taisf.services.device.entity.DeviceEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Component("push.deviceServiceImpl")
public class DeviceServiceImpl {

	@Resource(name = "push.deviceDao")
	private DeviceDao deviceDao;


	public DeviceEntity getDeviceByRegId(String regId){
		return deviceDao.getDeviceByRegId(regId);
	}

	/**
	 * 更新或增加device
	 * @param entity
	 */
	public void updateOrSaveDevice(DeviceEntity entity) {
		deviceDao.replaceDevice(entity);
	}

	/**
	 * 用户退出时解除用户和设备的绑定
	 * @param entity
	 * @return
	 */
	public int updateUserLogout(DeviceEntity entity){
		return deviceDao.unBindUserId(entity);
	}


	public DeviceEntity getDeviceByUserId(String userId){
		return deviceDao.getDeviceByUserId(userId);
	}

	public List<DeviceEntity> listDeviceByUserId(List<String> userIds){
		if(Check.NuNCollection(userIds)){
			return null;
		}
		return deviceDao.listDeviceByUserId(userIds);
	}

	public PagingResult<DeviceEntity> listDeviceByPushType(Map<String,Object> param){
		return deviceDao.listDeviceByPushType(param);
	}


}
