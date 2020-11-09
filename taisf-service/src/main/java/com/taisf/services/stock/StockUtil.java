package com.taisf.services.stock;

import com.jk.framework.base.utils.ValueUtil;

/**
 * <p>获取当前的库存信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/3/26.
 * @version 1.0
 * @since 1.0
 */
public class StockUtil  {

    private StockUtil() {
    }


    /**
     * 拼接当前商品的类型信息
     * @author afi
     * @param productCode
     * @param supplierProductType
     * @return
     */
    public static String getAppendString(Integer productCode,Integer supplierProductType){

        return ValueUtil.getStrValue(productCode) + "xxx" + ValueUtil.getStrValue(supplierProductType);
    }
}
