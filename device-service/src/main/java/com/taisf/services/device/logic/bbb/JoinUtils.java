package com.taisf.services.device.logic.bbb;


import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2020/3/1 11:27
 * @version 1.0
 */
public class JoinUtils {


    /**
     * 连接Map键值对
     * @author afi
     * @param map Map
     * @param prefix 前缀
     * @param suffix 后缀
     * @param separator 连接符
     * @param ignoreEmptyValue 忽略空值
     * @param ignoreKeys 忽略Key
     * @return
     */
    public static String joinKeyValue(Map<String, Object> map, String prefix, String suffix, String separator, boolean ignoreEmptyValue, String... ignoreKeys) {
        List<String> list = new ArrayList<String>();
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();

                String value = null;
                Object ele =entry.getValue();
                if (ele instanceof String){
                    value = (String) ele;
                }else {
                    value = JsonEntityTransform.Object2Json(ele);
                }
                if (!Check.NuNStr(key) && !StringUtil.contains(ignoreKeys, key) && (!ignoreEmptyValue || !Check.NuNStr(value))) {
                    list.add(key + "=" + (value != null ? value : ""));
                }
            }
        }
        String join =  (prefix != null ? prefix : "") + StringUtil.join(list, (separator != null ? separator : "")) + (suffix != null ? suffix : "");
        System.out.println(join);
        return join;
    }

}