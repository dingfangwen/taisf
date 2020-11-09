package com.taisf.api.common.collector;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>参数信息</p>
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
@Service(value = "commonCollector")
public class CommonCollector implements Collector<HttpServletRequest, Void, String> {

    private final static Logger logger = LoggerFactory.getLogger(CommonCollector.class);

    @Override
    @SuppressWarnings("unchecked")
    public String collector(final HttpServletRequest request, final Void v) {
        final Map<String, Object> params = new HashMap<String, Object>();

        final Enumeration<String> paramNames = request.getParameterNames();
        for (; paramNames.hasMoreElements();) {
            final String name = paramNames.nextElement();
            final String value = request.getParameter(name);

            params.put(name, value);
        }

        if (params.isEmpty()) {
            return null;
        }

        final ObjectMapper mapper = new ObjectMapper();
        String value = null;
        try {
            value = mapper.writeValueAsString(params);
        } catch (final IOException ignore) {
            logger.error("request params transfer to json failture.", ignore);
        }
        return value;
    }
}
