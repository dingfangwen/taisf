package com.jk.api.payment.base;

import com.jk.framework.base.rst.ResponseDto;
import com.jk.framework.base.utils.DesUtils;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.SignUtils;
import com.jk.framework.base.utils.ValueUtil;
import com.jk.services.payment.constant.PayConstants;
import com.jk.services.payment.entity.PayEntity;
import com.jk.services.payment.entity.PayInfo;
import com.jk.services.payment.entity.PayLogEntity;
import com.jk.services.payment.handle.PaymentHandle;
import com.jk.services.payment.service.PaymentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.ui.Model;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>基础层</p>
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
public class BaseController extends BaseContext{


    @Autowired
    protected PaymentService paymentService;


    /**
     * 获取异常信息
     * @param msg
     * @return
     */
    protected String getExceptionRst(String msg){
        ResponseDto res = new ResponseDto(ValueUtil.getStrValue(msg),PayConstants.PayStatus.STATUS_40000.getCode());
        return JsonEntityTransform.Object2Json(res);
    }

    /**
     * request 获取参数转为json
     * @param request
     * @return
     */
    public String getJson(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        Map<String, Object> map = new HashMap<String, Object>();
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            if(values.length == 1){
                map.put(key, values[0]);
            }else{
                String value = values[0];
                for (int i = 1; i < values.length; i++) {
                    value =value+","+values[i];
                }
                map.put(key, value);
            }
        }
        return JsonEntityTransform.Object2Json(map);
    }

    /**
     * 创建返回的url 编码 参数
     * @param model
     * @param payInfo
     * @param request
     * @return
     */
    public Map<String, Object> createAttribute(Model model, PayInfo payInfo, HttpServletRequest request){
        PaymentHandle paymentHandle = getPaymentHandleByCode(payInfo.getCode());
        Map<String, Object> parameterMap = paymentHandle.getParameterMap(payInfo, request);
        model.addAttribute("parameterMap", parameterMap);
        model.addAttribute("requestUrl", paymentHandle.getRequestUrl());
        model.addAttribute("requestCharset", paymentHandle.getRequestCharset());
        model.addAttribute("requestMethod", paymentHandle.getRequestMethod());
        this.savePayAndLog(payInfo.getPayId(), PayConstants.PayStatus.STATUS_20000.getCodeStr(),PayConstants.PayStatus.STATUS_20000.getName(),PayConstants.REQUEST, JsonEntityTransform.Object2Json(parameterMap));
        return parameterMap;
    }


    /**
     *  保存请求信息
     * @param payId
     * @param status
     * @param statusDesc
     * @param httpType
     * @param parameter
     */
    public void savePayAndLog(int payId,String status,String statusDesc,String httpType,String parameter) {
        PayLogEntity payLog = new PayLogEntity();
        payLog.setPayId(payId);
        payLog.setParameter(parameter);
        payLog.setStatus(status);
        payLog.setStatusDesc(statusDesc);
        payLog.setType(httpType);
        PayEntity pay = new PayEntity();
        pay.setId(payId);
        pay.setStatus(status);
        pay.setStatusDesc(statusDesc);
        pay.setBizTime(new Date());
        paymentService.savePayAndLog(pay,payLog);
    }
    /**
     * 获取请求的stream 内容
     * @param inputStream
     * @return
     */
    public String getContentByInputStream(ServletInputStream inputStream) throws Exception{
        String content = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            content = content + line;
        }
        bufferedReader.close();
        inputStream.close();
        return content;
    }

    public PaymentHandle getPaymentHandleByCode(String code){
        return (PaymentHandle) applicationContext.getBean(code);
    }


}
