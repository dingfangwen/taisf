package com.taisf.services.device.logic.aaa.command.base;


import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * <p>请求信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2019/4/1.
 * @version 1.0
 * @since 1.0
 */
public interface LinkRequest <T extends LinkResponse> {

    /**
     * 获取连接
     * @return
     */
    @JsonIgnore
    String getUri();

    /**
     * 获取返回结果
     * @return
     */
    @JsonIgnore
    Class getResponseClass();




}
