package com.taisf.services.push.test.jiguang.handle;

import com.jk.framework.base.utils.DateUtil;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.push.core.PushPar;
import com.taisf.services.push.handle.PushHandle;
import com.taisf.services.push.test.jiguang.base.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/1.
 * @version 1.0
 * @since 1.0
 */
public class JiguangPushHandleTest extends BaseTest {


    @Resource(name = "push.jiguangPushHandle")
    private PushHandle pushHandle;



//    @Test
//    public void sendPushTes1t() {
//
////        "alert": "支付宝到账一亿元",
////                "apnsType": "1",
////                "apnsParam": {
////            "money": "15.90",
////                    "name": "张1",
////                    "phone": "17600802935"
////        },
//
//        PushPar par = new PushPar();
//        par.setTitle("title");
//        par.setContent("content");
//        List<String> tokenList = new ArrayList<String>();
//        tokenList.add("18171adc03202503d99");
//        par.setToken(tokenList);
//        Map<String,String> param = new HashMap<String,String>();
//        param.put("messageType","1");
//        param.put("alert", "支付宝到账一亿元");
//        param.put("apnsType","1");
//        param.put("money","money");
//        param.put("name","name");
//        param.put("phone","phone");
//
//        par.setExtra(param);
//        Object rst = pushHandle.sendPush(par);
//        System.out.println(JsonEntityTransform.Object2Json(rst));
//        System.out.println(JsonEntityTransform.Object2Json(par));
//    }

    // ios Chandi0420
    @Test
    public void sendPushTest() {
        PushPar par = new PushPar();
        par.setTitle("title");
        par.setContent("content");
        List<String> tokenList = new ArrayList<String>();
//        tokenList.add("1104a89792e00c9c9b3");

        tokenList.add("13065ffa4e5bf721c6b");

        par.setToken(tokenList);

        Map<String,String> param = new HashMap<String,String>();
        param.put("messageType","1");
        param.put("voiceAlert", "支付宝到账2亿元");
        param.put("money","money");
        param.put("name","name");
        param.put("phone","phone");

        par.setExtra(param);
        Object rst = pushHandle.sendPush(par);
        System.out.println(JsonEntityTransform.Object2Json(rst));
        System.out.println(JsonEntityTransform.Object2Json(par));
    }
}
