package com.jk.services.payment.test.http;

import com.jk.framework.base.utils.SignUtils;
import com.jk.framework.base.utils.XmlUtils;
import com.jk.framework.common.utils.CloseableHttpsUtil;
import com.jk.services.payment.utils.PayUtil;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <p>退款的测试类</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/6/15.
 * @version 1.0
 * @since 1.0
 */
public class TenpayRefundHandleTest {

    public static void main(String[] args){

//        dealQuery();

        dealRefund();
    }


    private static void  dealQuery(){

        String url = "https://api.mch.weixin.qq.com/pay/refundquery";

        String key = "u7QRdSXsPtyNlFL7obL2GzhI9XosBcb2";
        String  appId = "wxfa8d0eebed514017";
        String mch_id = "1480376882";

        try {
            SortedMap<String,Object> parameters = new TreeMap<String,Object>();
            // 公众账号ID
            parameters.put("appid", appId);
            // 商户号
            parameters.put("mch_id", mch_id);
            // 随机字符串
            parameters.put("nonce_str", "tenpay1502354220545");
            // 微信订单号
            parameters.put("transaction_id", "4009682001201708105501068547");
            // 商户订单号
            parameters.put("out_trade_no", "2632");
            // 商户退款单号
            parameters.put("out_refund_no", "2632");
            // 微信退款单号
//            parameters.put("refund_id", "50000403482017061501244154954") ;
            parameters.put("sign", SignUtils.md5Sign("&key="+key,parameters).toUpperCase());

            String content = XmlUtils.getRequestXml(parameters);
            //调用退款
            String result = CloseableHttpsUtil.sendPost(null,url,content,null);
            System.out.println(result);
        }catch (Exception e){
           e.printStackTrace();
        }

    }

    /**
     * 退款
     */
    private static void  dealRefund(){
        try {

            String url = "https://api.mch.weixin.qq.com/secapi/pay/refund";


            String key = "Ce6UAlxIWB8m8EaUD2liSmrUg8h1mgfH";
            String  appId = "wxfa8d0eebed514017";
            String mch_id = "1480376882";



            String transaction_id = "4009682001201708105501068547";

            String biz = "2632";

            SortedMap<String,Object> parameterMap = new TreeMap<String,Object>();
            // 公众账号ID
            parameterMap.put("appid", appId);
            // 商户号
            parameterMap.put("mch_id", mch_id);
            // 随机字符串
            parameterMap.put("nonce_str", "tenpay1502354220545");
            // 微信订单号
            parameterMap.put("transaction_id", transaction_id);
            // 商户订单号
            parameterMap.put("out_trade_no", biz);
            // 商户退款单号
            parameterMap.put("out_refund_no", biz);
            // 总金额
            parameterMap.put("total_fee", 100);
            // 退款金额
            parameterMap.put("refund_fee", 100);
            // 操作员:操作员帐号, 默认为商户号
            parameterMap.put("op_user_id", 1480376882);
            // 签名
            parameterMap.put("sign", SignUtils.md5Sign("&key="+key,parameterMap).toUpperCase());

            String content = XmlUtils.getRequestXml(parameterMap);

            //获取证书
            SSLConnectionSocketFactory sslsf = PayUtil.getSslsf("/Users/fangwending/git/bj/bj_config/product/jk-api/jk-payment-api/apiclient_cert.p12","1480376882");

            //调用退款
            String result = CloseableHttpsUtil.sendPost(sslsf,url,content,null);

            System.out.println(result);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
