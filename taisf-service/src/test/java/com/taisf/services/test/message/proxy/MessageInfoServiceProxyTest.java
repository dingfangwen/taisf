package com.taisf.services.test.message.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.message.entity.MessageInfoEntity;
import com.taisf.services.message.proxy.MessageInfoServiceProxy;
import com.taisf.services.message.req.MessageInfoReq;
import com.taisf.services.order.api.OrderOpService;
import com.taisf.services.order.dto.FinishOrderRequest;
import com.taisf.services.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
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
 * @author afi on on 2019/4/24.
 * @version 1.0
 * @since 1.0
 */
public class MessageInfoServiceProxyTest  extends BaseTest {


    @Resource(name = "basedata.messageInfoServiceProxy")
    private MessageInfoServiceProxy messageInfoServiceProxy;


    @Test
    public void sendMessage4ApplyCellTest() {
        String uid = "2c91340c61088e880161089f5563000a";
        String address = "地址";
        String buffetFid = "100";
        String cellNum = "07";

        DataTransferObject<Void> classify = messageInfoServiceProxy.sendMessage4ApplyCell(uid,address,buffetFid,cellNum);
        System.out.println(JsonEntityTransform.Object2Json(classify));

    }





    @Test
    public void getMessageInfoListTest() {

        String uid = "2c91340c61088e880161089f5563000a";
        String address = "地址";
        String buffetFid = "100";
        String cellNum = "07";

        MessageInfoReq request = new MessageInfoReq();
        request.setUserId(uid);
        request.setApplicationCode("user");
        request.setSubject("100");

        DataTransferObject<List<MessageInfoEntity>> classify = messageInfoServiceProxy.getMessageInfoList(request);
        System.out.println(JsonEntityTransform.Object2Json(classify));

    }


}
