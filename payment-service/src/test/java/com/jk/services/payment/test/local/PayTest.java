package com.jk.services.payment.test.local;

import com.jk.framework.base.utils.SignUtils;
import com.jk.framework.common.utils.CloseableHttpUtil;

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
 * @author afi on on 2017/5/18.
 * @version 1.0
 * @since 1.0
 */
public class PayTest {


    public static void main(String[] args) {

        //将参数转化成map
        final Map payMap = new HashMap();
        payMap.put("bizId", "222222");
        payMap.put("code","weChatWapHandle" );
        payMap.put("productCode", "101");
        payMap.put("content","健客支付");
        payMap.put("amount", "1000");
        payMap.put("returnUrl", "https://www.jianke.com/");
        payMap.put("notifyUrl", "https://www.jianke.com/");
        payMap.put("openid", "omF2ws3As8lWLqX3BwpKQ1qHUYw8");
        final String out = SignUtils.md5Sign("test001", payMap);
        payMap.put("paySign", out);


        String jsonRst = CloseableHttpUtil.sendFormPost("http://localhost:8080/create",payMap);

        System.out.println(jsonRst);
    }

}
