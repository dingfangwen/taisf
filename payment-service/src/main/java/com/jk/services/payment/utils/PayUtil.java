package com.jk.services.payment.utils;


//       org.apache.http.conn.ssl
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;
import org.springframework.web.context.ContextLoader;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

/**
 * <p>支付证书相关</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/6/12.
 * @version 1.0
 * @since 1.0
 */
public class PayUtil {


    /**
     * 获取文件路径
     * @param templateName
     * @return
     */
    public static String getTemplate(String templateName){
        String pathBase = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
        return pathBase+"/WEB-INF/classes/"+templateName;
    }


    /**
     *	获取连接工厂
     *  PKCS12、TLSv1 是固定格式，故不做参数引入
     * @param certPath		E:/apiclient_cert.p12
     * @param pass				1244790002
     * @return
     * @throws Exception
     */
    public static SSLConnectionSocketFactory getSslsf(String certPath, String pass) throws Exception{
        try {
            KeyStore keyStore  = KeyStore.getInstance("PKCS12");
            FileInputStream instream = new FileInputStream(new File(certPath));
            try {
                keyStore.load(instream, pass.toCharArray());
            } finally {
                instream.close();
            }
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, pass.toCharArray()).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[] { "TLSv1" },
                    null,
                    SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            return sslsf;
        }catch (Exception e){
            throw new Exception("获取连接工厂失败,"+e.getMessage());
        }
    }

}
