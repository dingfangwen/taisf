package com.taisf.services.device.test.aaa;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.device.api.LinkServoice;
import com.taisf.services.device.common.constant.LinkConstant;
import com.taisf.services.device.common.valenum.DeviceStatusShowEnum;
import com.taisf.services.device.test.common.BaseTest;
import com.taisf.services.device.vo.DeviceCellCDVO;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * <p></p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2020/4/20 23:41
 * @version 1.0
 */
public class LinkServoiceImplAAATest extends BaseTest {


    /**
     * 接口文档地址
     * https://www.showdoc.cc/auvapi2?page_id=3932363990389519
     */

    @Resource(name = LinkConstant.factoryA)
    LinkServoice linkServoice;

    @Test
    public void getDeviceCellTest(){

        DataTransferObject<DeviceCellCDVO> deviceStatus = linkServoice.getDeviceCell("62f62a635a8b0b34f2599bff08095654");


        System.out.println(JsonEntityTransform.Object2Json(deviceStatus));

    }

    @Test
    public void getDeviceStatusByDeviceIdTest(){

        DeviceStatusShowEnum deviceStatus = linkServoice.getDeviceStatusByDeviceId("62f62a635a8b0b34f2599bff08095654");


        System.out.println(JsonEntityTransform.Object2Json(deviceStatus));

    }






    @Test
    public void applyCellByOrderSnTest(){

        DataTransferObject<String> sn = linkServoice.applyCellByOrderSn("deviceFid11","6652201156","TA2004219920T97S182341");

        System.out.println(JsonEntityTransform.Object2Json(sn));
    }




}
