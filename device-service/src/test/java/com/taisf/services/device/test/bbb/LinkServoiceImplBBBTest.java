package com.taisf.services.device.test.bbb;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.device.api.impl.LinkServoiceImplBBB;
import com.taisf.services.device.common.constant.LinkConstant;
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
public class LinkServoiceImplBBBTest  extends BaseTest {


    /**
     * 接口文档地址
     * https://www.showdoc.cc/auvapi2?page_id=3932363990389519
     */

    @Resource(name = LinkConstant.factoryB)
    LinkServoiceImplBBB linkServoiceImplBBB;

    @Test
    public void getDeviceStatusTest(){


        DataTransferObject<DeviceCellCDVO> deviceStatus = linkServoiceImplBBB.getDeviceCell("6652201156");
        System.out.println(JsonEntityTransform.Object2Json(deviceStatus));

    }






    @Test
    public void forceOpenCellTest(){

        DataTransferObject<String> sn = linkServoiceImplBBB.forceOpenCell("6652201156","1");

        System.out.println(JsonEntityTransform.Object2Json(sn));
    }

    @Test
    public void applyCellByOrderSnTest(){

        DataTransferObject<String> sn = linkServoiceImplBBB.applyCellByOrderSn("deviceFid11","6652201156","TA2004219920T97S182341");

        System.out.println(JsonEntityTransform.Object2Json(sn));
    }




}
