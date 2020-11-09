package com.taisf.services.check.proxy;

import com.taisf.services.check.dao.OrderStatsDao;
import com.taisf.services.order.dao.OrderBaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>订单统计相关</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/9/21.
 * @version 1.0
 * @since 1.0
 */
@Service("check.orderStatsManagerImpl")
public class OrderStatsManagerImpl {



    @Resource(name = "check.orderStatsDao")
    private OrderStatsDao orderStatsDao;






}
