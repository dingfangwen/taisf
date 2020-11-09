package com.jk.services.payment.task;

import com.jk.services.payment.constant.PaymentConstant;

/**
 * <p>定时任务</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/5/19.
 * @version 1.0
 * @since 1.0
 */
public abstract class TaskJob extends PaymentConstant {

    public abstract void doTask();
}
