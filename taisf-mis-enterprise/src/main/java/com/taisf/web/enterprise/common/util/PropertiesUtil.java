
package com.taisf.web.enterprise.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.*;

/**
 * <p>properties工具类</p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 * 
 * @author afi
 * @since 1.0
 * @version 1.0
 */
public class PropertiesUtil extends PropertyPlaceholderConfigurer implements Map<String, String>{

	private static Map<String, String> ctxPropertiesMap;

	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		if (ctxPropertiesMap != null) {
			logger.warn("The property map will be override!");
		}
		ctxPropertiesMap = new HashMap<String, String>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			ctxPropertiesMap.put(keyStr, value);
		}
	}

	public static String getString(String name) {
		if (ctxPropertiesMap == null) {
			ctxPropertiesMap = new HashMap<String, String>();
		}
		return (String) ctxPropertiesMap.get(name);
	}



	public int size() {
		return ctxPropertiesMap.size();
	}

	public boolean isEmpty() {
		return ctxPropertiesMap.isEmpty();
	}

	public boolean containsKey(Object key) {
		return ctxPropertiesMap.containsKey(key);
	}

	public boolean containsValue(Object value) {
		throw new UnsupportedOperationException();
	}

	public String put(String key, String value) {
		throw new UnsupportedOperationException();
	}

	public String remove(Object key) {
		throw new UnsupportedOperationException();
	}

	public void putAll(Map<? extends String, ? extends String> m) {
		throw new UnsupportedOperationException();
	}

	public void clear() {
		throw new UnsupportedOperationException();
	}

	public Set<String> keySet() {
		throw new UnsupportedOperationException();
	}

	public Collection<String> values() {
		throw new UnsupportedOperationException();
	}

	public Set<Entry<String, String>> entrySet() {
		throw new UnsupportedOperationException();
	}

	public String get(Object key) {
		return (String) ctxPropertiesMap.get(key);
	}
}
