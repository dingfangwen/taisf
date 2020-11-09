package com.jk.services.payment.test.http;

import com.jk.framework.base.utils.SignUtils;
import com.jk.framework.base.utils.XmlUtils;
import com.jk.services.payment.constant.PayConstants;
import com.jk.services.payment.exception.PaymentException;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/5/15.
 * @version 1.0
 * @since 1.0
 */
public class WeChatWapHandleTest {


    public static void main(String[] args) throws Exception{

//        String key = "Ce6UAlxIWB8m8EaUD2liSmrUg8h1mgfH";
//        String  appId = "wxfa8d0eebed514017";
//        String mch_id = "1480376882";

        String key = "u7QRdSXsPtyNlFL7obL2GzhI9XosBcb2";
        String  appId = "wx76fdf9d33da30928";
        String mch_id = "1482002202";


        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("appid",appId);
        parameterMap.put("mch_id", mch_id);
		parameterMap.put("nonce_str", "tenpay1495156285186");
        parameterMap.put("body", "test");
        parameterMap.put("out_trade_no", "24");
        parameterMap.put("total_fee", "1");
        parameterMap.put("spbill_create_ip", "172.1.1.1");
        parameterMap.put("notify_url", "https://www.jianke.com/");
        parameterMap.put("trade_type", "JSAPI");
//		parameterMap.put("sub_mch_id", "1244790002");
        parameterMap.put("openid", "o-ZYswpsjAGp-lagMHEPcXvrzNi8");
        parameterMap.put("sign", SignUtils.md5Sign("&key="+key,parameterMap).toUpperCase());


        System.out.println(getPrepayid(parameterMap));


    }

    public static String getPrepayid(Map<String, Object> parameterMap) throws Exception{
        URL url = new URL("https://api.mch.weixin.qq.com/pay/unifiedorder");
        URLConnection conn = url.openConnection();
        // 发送POST请求必须设置如下两行
        conn.setDoOutput(true);
        conn.setDoInput(true);
        // 获取URLConnection对象对应的输出流
        OutputStream outputStream = conn.getOutputStream();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outputStream));
        // 发送请求参数
        StringBuffer sb = new StringBuffer("<xml>");//"<?xml version=\"1.0\" encoding=\"GB2312\"?><xml>");
        for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
            sb.append("<").append(entry.getKey()).append(">");
            sb.append(entry.getValue());
            sb.append("</").append(entry.getKey()).append(">");
        }
        sb.append("</xml>");
        out.write(sb.toString());
        // flush输出流的缓冲
        out.flush();
        out.close();
        outputStream.close();
        String result = getContentByInputStream(conn.getInputStream());
        System.out.println("微信APP支付返回Token参数：{}"+result);
        Document document = DocumentHelper.parseText(result);
        if(!"SUCCESS".equalsIgnoreCase(XmlUtils.getDocumentNode(document, "/xml/return_code")) || !"SUCCESS".equalsIgnoreCase(XmlUtils.getDocumentNode(document, "/xml/result_code"))){
            throw  new PaymentException(PayConstants.PayStatus.STATUS_40000.getCodeStr(), "获取prepayid失败"+XmlUtils.getDocumentNode(document, "/xml/err_code")+" "+XmlUtils.getDocumentNode(document, "/xml/err_code_des"));
        }
        String prepay_id = XmlUtils.getDocumentNode(document, "/xml/prepay_id");
        return prepay_id;
    }

    /**
     * 获取请求的stream 内容
     * @param inputStream
     * @return
     */
    public static String getContentByInputStream(InputStream inputStream) throws Exception{
        String content = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf8"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            content = content + line;
        }
        bufferedReader.close();
        inputStream.close();
        if(StringUtils.isEmpty(content)){
            throw new PaymentException(PayConstants.EXCEPTION_STATUS, "获取内容失败");
        }
        return content;
    }
}
