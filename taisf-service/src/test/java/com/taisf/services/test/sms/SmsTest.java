package com.taisf.services.test.sms;

import com.jk.framework.base.utils.SnUtil;
import com.jk.framework.base.utils.XmlUtils;
import com.jk.framework.common.utils.CloseableHttpUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/23.
 * @version 1.0
 * @since 1.0
 */
public class SmsTest {


    public static void main(String[] args) throws Exception {

       String aa = getRandom(6);


        System.out.println(aa);

//        spid=106570&password=106570&ac=106570 01&mobiles=13512345678,13612345678&content=你的验证码是:257531【滴滴打车】

        String url = "http://112.90.92.102:16655/smsgwhttp/sms/submit";

        Map<String, String> par = new HashMap<>();
        par.put("spid","80012");
        par.put("password","Xg@2017chandi");
        par.put("ac","10691306077");
        par.put("mobiles","18911123545");
        par.put("content","验证码为xxx");

        String rst = CloseableHttpUtil.sendFormPost(url,par);

        Document document = DocumentHelper.parseText(rst);
        Integer result = Integer.parseInt(XmlUtils.getDocumentNode(document, "/returnsms/result"));
        System.out.println(result);
        System.out.println(rst);

    }


    private  static  String getRandom(int length){
        String val = "";
        Random random = new Random();
        for(int i = 0; i < length; ++i) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }
}
