package com.taisf.services.device.test.device.dao;

import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.device.dao.DeviceConfigDao;
import com.taisf.services.device.dao.DeviceLinkDao;
import com.taisf.services.device.entity.DeviceConfigEntity;
import com.taisf.services.device.entity.DeviceLinkEntity;
import com.taisf.services.device.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2019/4/4.
 * @version 1.0
 * @since 1.0
 */
public class DeviceConfigDaoTest extends BaseTest {


    @Resource(name = "device.deviceConfigDao")
    private DeviceConfigDao deviceConfigDao;


    @Test
    public void findOneDeviceLinkTest(){

        DeviceConfigEntity buffetByFid = deviceConfigDao.getDeviceConfigByCode("aaa");
        System.out.println(JsonEntityTransform.Object2Json(buffetByFid));
    }

}
