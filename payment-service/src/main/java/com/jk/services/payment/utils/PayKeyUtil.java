package com.jk.services.payment.utils;

import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.ValueUtil;

/**
 * <p>支付相关的信息</p>
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
public class PayKeyUtil {


    /**
     * 获取当前的支付的key
     * @author afi
     * @param pre
     * @param real
     * @return
     */
    public static String getFakeKey(String pre,Object real){

       return ValueUtil.getStrValue(pre) + ValueUtil.getStrValue(real);

    }


    /**
     * 获取当前支付的真实的key
     * @author afi
     * @param pre
     * @param key
     * @return
     */
    public static Integer getRealKey(String pre,String key){
       if (Check.NuNStr(pre)
               || Check.NuNStr(key)){
           return ValueUtil.getintValue(key);
       }
       if (!key.startsWith(pre)){
           return ValueUtil.getintValue(key);
       }
        return ValueUtil.getintValue(key.substring(pre.length()));
    }

}
