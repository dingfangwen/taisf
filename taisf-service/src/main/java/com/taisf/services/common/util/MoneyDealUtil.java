package com.taisf.services.common.util;

import com.jk.framework.base.utils.ValueUtil;

/**
 * <p>金额的操作</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/29.
 * @version 1.0
 * @since 1.0
 */
public class MoneyDealUtil {


    /**
     * 获取当前的最新的金额
     * @param oldMoney
     * @param price
     * @param num
     * @return
     */
    public static int overlayMoney(Integer oldMoney,Integer price,Integer num){
        return ValueUtil.getintValue(oldMoney) + ValueUtil.getintValue(price) * ValueUtil.getintValue(num);
    }
}
