package com.jk.services.payment.test.local;

import com.jk.framework.base.utils.SignUtils;
import com.jk.framework.common.utils.CloseableHttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class RefundTest {


    public static void main(String[] args) {

        //将参数转化成map
        final Map payMap = new HashMap();
        payMap.put("code","tenpayRefundHandle" );
        payMap.put("productCode", "102");
        payMap.put("amount", "1");
        payMap.put("returnUrl", "https://www.jianke.com/");
        payMap.put("notifyUrl", "https://www.jianke.com/");

        payMap.put("bizId","12312312");

        payMap.put("orgBizId","999");
        payMap.put("content","content");
        payMap.put("orgInSerialNo","4000622001201706166014248030");
        payMap.put("amount",1);




        final String out = SignUtils.md5Sign("test001", payMap);
        payMap.put("paySign", out);


        String jsonRst = CloseableHttpUtil.sendPost("http://localhost:8080/refund",payMap);

        System.out.println(jsonRst);
    }

}
