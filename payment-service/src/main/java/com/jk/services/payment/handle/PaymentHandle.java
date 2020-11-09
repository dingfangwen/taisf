package com.jk.services.payment.handle;

import com.jk.services.payment.entity.PayInfo;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>抽象的信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/5/11.
 * @version 1.0
 * @since 1.0
 */
public abstract class PaymentHandle extends BaseHandle{



    /**
     * 请求方法
     */
    public enum RequestMethod {

        /** POST */
        post,

        /** GET */
        get
    }

    /**
     * 通知方法
     */
    public enum NotifyMethod {

        /** 通用 */
        general,

        /** 同步 */
        sync,

        /** 异步 */
        async
    }

    /**
     * 获取请求URL
     *
     * @return 请求URL
     */
    public abstract String getRequestUrl();

    /**
     * 获取请求方法
     *
     * @return 请求方法
     */
    public abstract RequestMethod getRequestMethod();

    /**
     * 获取请求字符编码
     *
     * @return 请求字符编码
     */
    public abstract String getRequestCharset();

    /**
     * 获取请求参数
     *
     * @param request
     *            httpServletRequest
     * @return 请求参数
     */
    public abstract Map<String, Object> getParameterMap(PayInfo payInfo, HttpServletRequest request);

    /**
     * 同步处理返回的json
     * @param json
     * @return
     */
    public abstract String returnHandle(PayInfo payInfo,String json);
    /**
     * 异步处理返回的json
     * @param json
     * @return
     */
    public abstract String notifyHandle(PayInfo payInfo,String json);

    /**
     * 验证通知是否合法
     *
     */
    public abstract void verifyNotify(PayInfo payInfo,String json);



}
