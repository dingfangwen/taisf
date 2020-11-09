package com.jk.services.payment.job;

import com.jk.framework.log.utils.LogUtil;
import com.jk.services.payment.task.RetryRefundJobTask;
import com.jk.services.payment.task.TaskJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * <p>定时任务</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author lunan  on 2017/6/15.
 * @version 1.0
 * @since 1.0
 */
@Service
public class RefundJob extends TaskJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(RefundJob.class);

    @Autowired
    private RetryRefundJobTask retryRefundJobTask;


    @Scheduled(cron="0 0/2 * * * ?")
    @Override
    public void doTask() {

        LogUtil.info(LOGGER,"退款 开始执行");
        retryRefundJobTask.doTask();
        LogUtil.info(LOGGER,"退款 执行结束");

    }
}
