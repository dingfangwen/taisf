package com.taisf.services.device.logic.aaa.command.util;

import com.jk.framework.base.utils.Check;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2019/4/2.
 * @version 1.0
 * @since 1.0
 */
public abstract class CryptoUtil {
    public static final String HMAC_MD5 = "HmacMD5";
    public static final String HMAC_SHA1 = "HmacSHA1";
    public static final String HMAC_SHA256 = "HmacSHA256";
    public static final String HMAC_SHA512 = "HmacSHA512";
    public static final String PARAM_HMAC_APP_ID = "AppId";
    public static final String PARAM_HMAC_TIMESTAMP = "Timestamp";
    public static final String PARAM_HMAC_DIGEST = "Digest";





    public CryptoUtil() {
    }

    public static String hmacDigest(String plaintext, String secretKey, String algName) {
        try {
            Mac mac = Mac.getInstance(algName);
            byte[] secretByte = secretKey.getBytes();
            byte[] dataBytes = plaintext.getBytes();
            SecretKey secret = new SecretKeySpec(secretByte, algName);
            mac.init(secret);
            byte[] doFinal = mac.doFinal(dataBytes);
            return byte2HexStr(doFinal);
        } catch (Exception var8) {
            throw new RuntimeException(var8.getMessage());
        }
    }

    private static String byte2HexStr(byte[] bytes) {
        StringBuilder hs = new StringBuilder();

        for(int n = 0; bytes != null && n < bytes.length; ++n) {
            String tmp = Integer.toHexString(bytes[n] & 255);
            if(tmp.length() == 1) {
                hs.append('0');
            }

            hs.append(tmp);
        }

        return hs.toString().toUpperCase();
    }



    /**
     * 校验是否是本地参数
     * @param name
     * @return
     */
    private static boolean checkLocalParName(String name){
        boolean localPar = false;
        if (Check.NuNStr(name)){
            return localPar;
        }
        if (PARAM_HMAC_APP_ID.equals(name)  || PARAM_HMAC_TIMESTAMP.equals(name)  || PARAM_HMAC_DIGEST.equals(name)){
            localPar = true;
        }
        return localPar;
    }
}
