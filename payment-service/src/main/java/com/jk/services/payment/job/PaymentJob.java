package com.jk.services.payment.job;

import com.jk.framework.log.utils.LogUtil;
import com.jk.services.payment.task.PayJobTask;
import com.jk.services.payment.task.TaskJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
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
@EnableScheduling
public class PaymentJob extends TaskJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentJob.class);

    @Autowired
    private PayJobTask payJobTask;


    @Scheduled(cron="0 0/1 * * * ?")
    @Override
    public void doTask() {
        LogUtil.info(LOGGER,"同步通知 开始执行");
        payJobTask.doTask();
        LogUtil.info(LOGGER,"同步通知 执行结束");
    }
}
