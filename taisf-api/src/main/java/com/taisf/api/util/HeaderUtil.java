package com.taisf.api.util;

import com.jk.framework.base.utils.ValueUtil;
import com.jk.framework.base.head.Header;

/**
 * <p>头信息的工具</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/14.
 * @version 1.0
 * @since 1.0
 */
public class HeaderUtil {

    /**
     * 获取head的拼接参数
     * @param header
     * @return
     */
    public static String getCodeStr(Header header,Object code){
        return ValueUtil.getStrValue(header.getApplicationCode())
                + ValueUtil.getStrValue(header.getDeviceUuid())
                + ValueUtil.getStrValue(code)
                ;
    }
}
