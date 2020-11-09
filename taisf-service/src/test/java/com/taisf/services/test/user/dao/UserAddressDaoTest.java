package com.taisf.services.test.user.dao;


import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.UUIDGenerator;
import com.taisf.services.order.dao.OrderBaseDao;
import com.taisf.services.order.entity.OrderEntity;
import com.taisf.services.test.common.BaseTest;
import com.taisf.services.user.dao.UserAddressDao;
import com.taisf.services.user.entity.UserAddressEntity;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;


/**
 * <p>订单类测测试</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2016/3/31.
 * @version 1.0
 * @since 1.0
 */
public class UserAddressDaoTest extends BaseTest {


    @Resource(name = "user.userAddressDao")
    private UserAddressDao userAddressDao;




    @Test
    public void getUserAddressByFidTest() {
        UserAddressEntity orderEntity = userAddressDao.getUserAddressByFid("11");

        System.out.println(JsonEntityTransform.Object2Json(orderEntity));
    }


    @Test
    public void saveUserAddressTest(){
        UserAddressEntity orderEntity = new UserAddressEntity();
        orderEntity.setProvinceCode("BJ");
        orderEntity.setCityCode("BJ");
        orderEntity.setAreaCode("东城区");
        orderEntity.setProvinceName("BJ");
        orderEntity.setCityName("BJ");
        orderEntity.setAreaName("东城区");

        orderEntity.setStreet("11");
        orderEntity.setUserUid(UUIDGenerator.hexUUID());
        orderEntity.setUserTel("18301315875");
        orderEntity.setUserName("小草");
        orderEntity.setUserSex(1);
        orderEntity.setDoorNo("no");
        orderEntity.setAddressLabel(0);

        orderEntity.setCreateTime(new Date());

        userAddressDao.saveUserAddress(orderEntity);
    }




    @Test
    public void updateUserAddressTest(){
        UserAddressEntity orderEntity = new UserAddressEntity();
        orderEntity.setFid("ff8080815e92a7c1015e92a7c1110001");
        orderEntity.setProvinceCode("BJ");
        orderEntity.setCityCode("BJ");
        orderEntity.setAreaCode("东城区");
        orderEntity.setProvinceName("BJ");
        orderEntity.setCityName("BJ");
        orderEntity.setAreaName("东城区");

        orderEntity.setStreet("11");
        orderEntity.setUserUid(UUIDGenerator.hexUUID());
        orderEntity.setUserTel("18301315875");
        orderEntity.setUserName("小草");
        orderEntity.setUserSex(1);
        orderEntity.setDoorNo("no");
        orderEntity.setAddressLabel(0);

        orderEntity.setCreateTime(new Date());

        userAddressDao.updateUserAddress(orderEntity);
    }



}

