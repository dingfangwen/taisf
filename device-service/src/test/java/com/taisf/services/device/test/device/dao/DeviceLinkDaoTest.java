package com.taisf.services.device.test.device.dao;

import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.device.dao.DeviceLinkDao;
import com.taisf.services.device.entity.DeviceLinkEntity;
import com.taisf.services.device.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
public class DeviceLinkDaoTest extends BaseTest {


    @Resource(name = "device.deviceLinkDao")
    private DeviceLinkDao deviceLinkDao;

    @Test
    public void saveDeviceLinkTest(){
        DeviceLinkEntity entity = new DeviceLinkEntity();
        entity.setCellSn("11");
        entity.setOrderSn("orderSn");
        entity.setBuffetFid("bufferFid");
        entity.setDeviceId("deviceId");
        entity.setLinkStatus(1);

        entity.setCreateTime(new Date());
        deviceLinkDao.saveDeviceLink(entity);
    }

    @Test
    public void findOneDeviceLinkTest(){

        DeviceLinkEntity buffetByFid = deviceLinkDao.findOneDeviceLink(1);
        System.out.println(JsonEntityTransform.Object2Json(buffetByFid));
    }



    @Test
    public void findDeviceLinkByDeviceIdTest(){

        List<DeviceLinkEntity> buffetByFid = deviceLinkDao.findDeviceLinkOccupyByDeviceId("deviceId");
        System.out.println(JsonEntityTransform.Object2Json(buffetByFid));
    }


}
