package com.taisf.api.common.interceptor;

import com.jk.framework.base.head.Header;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.ValueUtil;
import org.apache.http.HeaderIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;

/**
 * <p>
 * 请求头的拦截器信息
 * </p>
 * <p/>
 * 
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
public class HeadersInterceptor extends HandlerInterceptorAdapter {

	private final static Logger logger = LoggerFactory.getLogger(HeadersInterceptor.class);

	/** 请求头属性名 */
	public final static String HEADER = HeaderIterator.class.getName() + ".Header";

	/** 请求头属性名 */
	public final static String USERID = HeaderIterator.class.getName() + ".UserId";

	/** 请求头属性名 */
	public final static String USER = HeaderIterator.class.getName() + ".User";

	/** Token值属性名. */
	public final static String TOKEN =HeadersInterceptor.class.getName() + ".Token";

	/** 是否输出请求头信息. */
	private boolean isPrint;

	public void setPrint(final boolean isPrint) {
		this.isPrint = isPrint;
	}

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception {
		final String traceinfo = request.getHeader("traceInfo");
		final Header header = matchTraceInfoToHeader(traceinfo);
		final String token = request.getHeader("token");


		System.out.println(header.getUserToken());

		request.setAttribute(HEADER, header);
		if (Check.NuNStr(token)){
			request.setAttribute(TOKEN, header.getUserToken());
		}else {
			request.setAttribute(TOKEN, token);
		}
		if (isPrint) {
			logger.info("header: {}, token: {}.", header, token);
		}

		return true;
	}

	/**
	 * 解析请求头, 构建Header信息.
	 * 
	 * @param traceinfo
	 * @return
	 */
	private Header matchTraceInfoToHeader(final String traceinfo) {
		final Header header = new Header();
		if (Check.NuNStrStrict(traceinfo)) {
			return header;
		}

		final String[] traceInfos = traceinfo.split(";");
		for (final String info : traceInfos) {
			if (Check.NuNStr(info)) {
				continue;
			}
			final int indexOf = info.indexOf('=');
			if (indexOf == -1) {
				continue;
			}

			setParameters(header, info, indexOf);
		}

		return header;
	}

	/**
	 * 为 Header 赋值, 通过 Field 反射实现.
	 * 
	 * @param header
	 * @param info
	 * @param indexOf
	 */
	private void setParameters(final Header header, final String info, final int indexOf) {
		final String paramName = info.substring(0, indexOf);
		final String paramValue = info.substring(indexOf + 1);
		if (Check.NuNObjs(paramName, paramValue)) {
			return;
		}
		try {
			final Field field = header.getClass().getDeclaredField(paramName);
			field.setAccessible(true);

			String type = field.getType().toString();// 得到field此属性的类型
			if (type.endsWith("String")) {
				field.set(header, paramValue);
			} else if (type.endsWith("int") || type.endsWith("Integer")) {
				field.set(header, Integer.parseInt(paramValue));
			}else if(type.equals("float")){
				field.set(header, (float)ValueUtil.getdoubleValue(paramValue));
			}

		} catch (final Exception ignore) {
			// Ignore all exception.
			//ignore.printStackTrace();
		}
	}

}
