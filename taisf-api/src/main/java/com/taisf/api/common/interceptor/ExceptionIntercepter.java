package com.taisf.api.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.head.Header;
import com.jk.framework.log.utils.LogUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Resource(name="exceptionIntercepter")
public class ExceptionIntercepter{
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionIntercepter.class);
	
	@SuppressWarnings("rawtypes")
	public Object around(ProceedingJoinPoint joinPoint){
		String methodName  = "";
		String className = "";
		JSONObject paramMap = new JSONObject();
		Object[] args = joinPoint.getArgs();
		int i = 1;
		try {
			methodName = joinPoint.getSignature().getName();
			className = joinPoint.getTarget().getClass().getSimpleName();
			for(Object obj : args){
				if(obj instanceof Header){
					paramMap.put("arg"+(i++), obj);
				}else if(obj instanceof String ){
					paramMap.put("arg"+(i++), obj);
				}else if(obj instanceof HttpServletRequest){
					HttpServletRequest request = (HttpServletRequest)obj;
					Map parameterMap = request.getParameterMap();
					if(parameterMap == null){
						continue;
					}
					paramMap.put("HttpServletRequest", request.getParameterMap());
				}
			}
		} catch (Exception e) {
			LogUtil.error(LOGGER, "【日志参数封装】error:{}", e);
		}
		try {
			LogUtil.info(LOGGER, "【处理】类名{},方法:{},参数:{}", className, methodName, paramMap.toJSONString());
			Object proceed = joinPoint.proceed();
			return proceed;
		} catch (Throwable e) {
			LogUtil.error(LOGGER, "【处理异常】类名{},方法:{},参数:{}, 错误：{}",className,methodName,paramMap.toJSONString(),e);
			DataTransferObject dto = new DataTransferObject();
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
			return dto.trans2Res();
		}
	}
	
}