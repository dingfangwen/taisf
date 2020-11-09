package com.taisf.services.device.test.bbb;

import com.jk.framework.base.utils.DateUtil;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.common.utils.CloseableHttpUtil;
import com.taisf.services.device.logic.bbb.SignUtilsMD5;
import com.taisf.services.device.logic.bbb.vo.BBBQueryResultVO;
import com.taisf.services.device.test.common.BaseTest;
import com.taisf.services.device.vo.SimpleOp;
import com.taisf.services.device.vo.SimpleOpEle;
import org.junit.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.util.*;

/**
 * <p></p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2020/4/14 22:33
 * @version 1.0
 */
public class LinkBBBTest extends BaseTest {




    /**
     * @param argv
     * @throws Exception
     */
    public static void main(String[] argv) throws Exception {



        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");


        Map<String,List> map = new HashMap<>();
        map.put("key",list);

        List<String> tmp = list;

        tmp.remove(0);
        tmp.remove(0);
        tmp.remove(0);


        System.out.println(map.get("key").size());



        System.out.println(111);
        // 生成密钥
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
        SecretKey key = keyGen.generateKey();

        // HmacMD5算法
        Mac mac = Mac.getInstance(key.getAlgorithm());
        mac.init(key);

        // 加密内容
        String str = "appId=66491&bizData={\"deviceId\":\"TestAndroid_3\",\"opList\":[{\"cellNo\":\"1\",\"ops\":[\"DOOR_OPEN\"]}]}&method=device.cells.ops&timestamp=2019-12-17 21:00:00&version=1.0682dd101cc64b05d0acb43ad96834ae3";
        byte[] utf8Str = str.getBytes("UTF8");

        // 加密处理
        byte[] digest = mac.doFinal(utf8Str);

        String aa = new String(digest);

        System.out.println(aa);

//            // Base64编码
//            String digestB64 = Base64.encodeBase64String(digest);
//            System.out.println(digestB64);
    }





    private static Map<String,String>  signMap(Map<String,Object> par,String key){
        par.put("timestamp", DateUtil.timestampFormat(new Date()));
//        par.put("timestamp","2019-12-17 21:00:00");

        //682dd101cc64b05d0acb43ad96834ae3

        String digest = SignUtilsMD5.md5Sign(key,par);
        par.put("digest",digest);
        Map<String,String> map = new HashMap<>();
        for (String s : par.keySet()) {
            Object value = par.get(s);
            if (value instanceof String){
                map.put(s,(String) value);
            }else {
                String json = JsonEntityTransform.Object2Json(par.get(s));
                map.put(s,cleanStr(json));
            }
        }
        return map;
    }



    private static String cleanStr(String  json){
        return json.replace("\\\"","\"");
    }


    @Test
    public void getDeviceStatusTest(){

        String url =  "http://openapi.58auv.com/gateway.do";

        Map<String,Object> par = new HashMap<>();
        par.put("appId","66491");
        par.put("method","device.status.query");
        par.put("version","1.0");


        Map<String,String> real = new HashMap<>();
        real.put("deviceId","TestAndroid_3");
        par.put("bizData",real);
        String s = CloseableHttpUtil.sendFormPost(url, signMap(par,"682dd101cc64b05d0acb43ad96834ae3"));


        BBBQueryResultVO bbbQueryResultVO = JsonEntityTransform.json2Entity(s, BBBQueryResultVO.class);
        System.out.println(s);
    }





    @Test
    public void openDeviceTest(){

        String url =  "http://openapi.58auv.com/gateway.do";

        Map<String,Object> par = new HashMap<>();
        par.put("appId","66491");
        par.put("method","device.cells.ops");
        par.put("version","1.0");


        String deviceId = "TestAndroid_3";

        SimpleOp simpleOp = new SimpleOp(deviceId);

        SimpleOpEle  ele = new SimpleOpEle();
        ele.setCellNo("1");
        List<String> ops = new ArrayList<>();
        ops.add("DOOR_OPEN");
        ops.add("LIGHT_OPEN");
        ops.add("WARM_START");
        ele.setOps(ops);
        simpleOp.addOp(ele);
        par.put("bizData",simpleOp);

        String s = CloseableHttpUtil.sendFormPost(url, signMap(par,"682dd101cc64b05d0acb43ad96834ae3"));
        System.out.println(s);
    }


    @Test
    public void onlineOpenDeviceTest(){

        String url =  "http://openapi.58auv.com/gateway.do";

        Map<String,Object> par = new HashMap<>();
        par.put("appId","66522");
        par.put("method","device.cells.ops");
        par.put("version","1.0");


        String deviceId = "6652201156";

        SimpleOp simpleOp = new SimpleOp(deviceId);

        SimpleOpEle  ele = new SimpleOpEle();
        ele.setCellNo("1");
        List<String> ops = new ArrayList<>();
        ops.add("DOOR_OPEN");
        ops.add("LIGHT_OPEN");
        ops.add("WARM_START");
        ele.setOps(ops);
        simpleOp.addOp(ele);
        par.put("bizData",simpleOp);

        String s = CloseableHttpUtil.sendFormPost(url, signMap(par,"9ba3e59a83ebc7247036a579c5401250"));
        System.out.println(s);
    }

}
