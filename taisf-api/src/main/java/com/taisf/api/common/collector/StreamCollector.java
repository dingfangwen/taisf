package com.taisf.api.common.collector;

import org.springframework.stereotype.Service;

import java.io.OutputStream;

/**
 * <p>流的收集器</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2016/4/15.
 * @version 1.0
 * @since 1.0
 */
@Service(value = "streamCollector")
public class StreamCollector implements Collector<OutputStream, Boolean, String> {

    @Override
    public String collector(final OutputStream t, final Boolean v) {
        if (t == null) {
            return null;
        }
        String body = t.toString();
        //这里做公共的加密的信息处理
        //TODO
        return body;
    }
}
