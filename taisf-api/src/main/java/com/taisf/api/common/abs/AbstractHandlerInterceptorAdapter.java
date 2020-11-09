package com.taisf.api.common.abs;

import com.jk.framework.base.utils.Check;
import com.jk.framework.log.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>抽象拦截器</p>
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
public class AbstractHandlerInterceptorAdapter extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractHandlerInterceptorAdapter.class);

    protected static final String isStream = "stream";

    /**
     * 检测是否以流的方式传输.
     * @param request
     * @return
     */
    protected boolean checkStream(final HttpServletRequest request) {
        if (Check.NuNStr(request.getContentType())){
            return false;
        }
        return request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE);
    }


    /**
     * 向客户端写入信息.
     * @param request
     * @param response
     * @param msg
     */
    protected void responseMsg(final HttpServletRequest request, final ServletResponse response, final Object msg) {
        if (Check.NuNObj(msg)) {
            return;
        }
        String message = msg.toString();
        try {
            //TODO 这里可以对返回结果的封装 暂时应该没用到
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(message);
            response.flushBuffer();
        } catch (final IOException e) {
            LogUtil.error(LOGGER, " e:{}", e);
        }
    }
}
