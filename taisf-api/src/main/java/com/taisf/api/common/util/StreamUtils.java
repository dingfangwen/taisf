package com.taisf.api.common.util;

import com.jk.framework.log.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

public class StreamUtils {
	private final static Logger logger = LoggerFactory.getLogger(StreamUtils.class);

    /** 返回提示信息
    * @param result
    * @param response
    */
    public static void outMessage(String result, HttpServletResponse response){
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            response.setContentType("application/json;charset=UTF-8");
            out.write(result.getBytes("UTF-8"));
            out.close();
        } catch (Exception e) {
        	LogUtil.error(logger, "【illegal request output occur exception】{}", e);
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (Exception e) {
            	LogUtil.error(logger, "【close output occur exception】{}", e);
            }
        }
    }
  
}
