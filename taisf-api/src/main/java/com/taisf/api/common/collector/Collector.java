package com.taisf.api.common.collector;

/**
 * <p>收集器</p>
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
public interface Collector<T, V, R> {

    /**
     * 收集信息.
     * @param t
     * @param v
     * @return
     */
    R collector(T t, V v);
}
