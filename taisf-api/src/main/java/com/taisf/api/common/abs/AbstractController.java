package com.taisf.api.common.abs;


import com.jk.framework.base.entity.BaseEntity;
import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.head.Header;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.common.utils.CloseableHttpUtil;
import com.taisf.api.common.collector.ParamCollector;
import com.taisf.api.common.interceptor.HeadersInterceptor;
import com.taisf.services.user.vo.UserModelVO;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>抽象的controller 基类. 提供各控制器公共方法调用.</p>
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
public abstract class AbstractController {


    /**
     * 获取参数
     * @param request
     * @return
     */
    protected final Map<String,Object> getMap(final HttpServletRequest request) {
        final String params = (String) request.getAttribute(ParamCollector.PARAMS);
        if (Check.NuNStrStrict(params)) {
            return null;
        }
        return (Map<String,Object>)JsonEntityTransform.json2Map(params);
    }



    /**
     * 获取参数
     * @param request
     * @return
     */
    protected final String getPar(final HttpServletRequest request) {
        final String params = (String) request.getAttribute(ParamCollector.PARAMS);
        if (Check.NuNStrStrict(params)) {
            return null;
        }
        return params;
    }


    /**
     * 将接收的参数转化成list
     * @param request
     * @param tClass
     * @return
     */
    protected final <T extends BaseEntity> List<T> getList(final HttpServletRequest request, final Class<T> tClass) {
        final String params = (String) request.getAttribute(ParamCollector.PARAMS);
        if (Check.NuNStrStrict(params)) {
            return null;
        }
        try {
            return JsonEntityTransform.json2List(params, tClass);
        }catch (Exception e){
            return null;
        }
    }


    /**
     * 将接收的参数转换为指定对象.
     * @param request
     * @param tClass
     * @return
     */
    protected final <T extends BaseEntity> T getEntity(final HttpServletRequest request, final Class<T> tClass) {
        final String params = (String) request.getAttribute(ParamCollector.PARAMS);
        if (Check.NuNStrStrict(params)) {
            return null;
        }
        try {
            return JsonEntityTransform.json2Entity(params, tClass);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 获取请求头信息.
     * @param request
     * @return
     */
    protected final Header getHeader(final HttpServletRequest request) {
        return (Header) request.getAttribute(HeadersInterceptor.HEADER);
    }


    /**
     * 获取用户信息
     * @param request
     * @return
     */
    protected final String getToken(final HttpServletRequest request) {
        return (String) request.getAttribute(HeadersInterceptor.TOKEN);
    }

    /**
     * 获取用户信息
     * @param request
     * @return
     */
    protected final String getUserId(final HttpServletRequest request) {
        return (String) request.getAttribute(HeadersInterceptor.USERID);
    }



    /**
     * 获取用户信息
     * @param request
     * @return
     */
    protected final UserModelVO getUser(final HttpServletRequest request) {
        return (UserModelVO) request.getAttribute(HeadersInterceptor.USER);
    }


    /**
     * 将openId转成userId
     * @param request url
     * @return
     */
    protected final String transFerOpenIdToUserId(final HttpServletRequest request,String url,String openId){
        if(!Check.NuNStrStrict(url) && !Check.NuNStrStrict(openId)){
            Map<String, String> parMap = new HashMap<>();
            parMap.put("openId",openId);
            String result = CloseableHttpUtil.sendGet(url, parMap, null);
            DataTransferObject dto = JsonEntityTransform.json2DataTransferObject(result);
            //TODO 转json 返回userId
            return result;
        }else{
            return null;
        }
    }

}
