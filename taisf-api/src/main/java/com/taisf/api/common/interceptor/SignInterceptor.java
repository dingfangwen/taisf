package com.taisf.api.common.interceptor;


import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.SignUtils;
import com.taisf.api.common.abs.AbstractHandlerInterceptorAdapter;
import com.taisf.api.common.collector.ParamCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>验签的处理逻辑</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi
 * @version 1.0
 * @since 1.0
 */
public class SignInterceptor extends AbstractHandlerInterceptorAdapter {

    private final static Logger logger = LoggerFactory.getLogger(SignInterceptor.class);

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        String str = (String) request.getAttribute(isStream);
        if (!Check.NuNStr(str)){
            final String json = (String) request.getAttribute(ParamCollector.PARAMS);
            if (!Check.NuNStrStrict(json)) {
                return SignUtils.checkSign("",json);
            }
        }
        return true;
    }


}
