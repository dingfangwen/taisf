package com.taisf.web.oms.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>出库单的生成</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/3/16.
 * @version 1.0
 * @since 1.0
 */
public class CodeGeneratorOutStockUtil {

    private static final  String pre = "O";

    private static long oldOrder = 0;

    private CodeGeneratorOutStockUtil() {
    }

    /**
     * 生成一个新的顺序号编码值
     *
     * @return 返回顺序号值
     */
    public synchronized static long newID() {
        String s = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
        long order = Long.parseLong(s)*1000;
        if (order <= oldOrder)
            order = oldOrder + 1;
        oldOrder = order;
        return order;
    }

    public static String getCode(){
        return pre+ newID();
    }

    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();
        for(int i=0; i<2000; i++) {
            System.out.println(newID());
            //newID();
        }
        System.out.println((System.currentTimeMillis()-l1)/1000);
    }
}
