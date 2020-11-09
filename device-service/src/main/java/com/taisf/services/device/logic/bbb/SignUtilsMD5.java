package com.taisf.services.device.logic.bbb;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * <p></p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2020/4/14 22:37
 * @version 1.0
 */
public class SignUtilsMD5 {



    /**
     * 生成ms5签名
     * @author afi
     * @param parameterMap 参数
     * @return 签名
     */
    public static String md5Sign(String key, Map<String, Object> parameterMap) {
        String sign = HMacMD5Util.getHmacMd5Bytes(key,JoinUtils.joinKeyValue(new TreeMap<String, Object>(parameterMap), null, null, "&", true, "paySign","sign_type", "sign"));
//        if (!Check.NuNStr(sign)){
//            sign = sign.toUpperCase();
//        }
        return sign;
    }







    public static void main(String[] args) {

        String text = "appId=66491&bizData={\"opList\":[{\"cellNo\":\"1\",\"ops\":[\"DOOR_OPEN\"]}],\"deviceId\":\"TestAndroid_3\"}&method=device.cells.ops&timestamp=2019-12-17 21:00:00&version=1.0";

        String encodeStr= DigestUtils.md5Hex(text + "682dd101cc64b05d0acb43ad96834ae3");
        System.err.println(("=======================D5加密后的字符串为:encodeStr=" + encodeStr));

    }

}
