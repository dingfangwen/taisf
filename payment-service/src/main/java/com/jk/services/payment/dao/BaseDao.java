package com.jk.services.payment.dao;

import com.jk.framework.dao.base.MybatisDaoContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/3/15.
 * @version 1.0
 * @since 1.0
 */
public class BaseDao {

    @Autowired
    @Qualifier("payment.MybatisDaoContext")
    protected MybatisDaoContext mybatisDaoContext;

}
