package com.taisf.api.job;

import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.refund.job.CreateRefundJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/1/3.
 * @version 1.0
 * @since 1.0
 */
@Service
@EnableScheduling
public class RefundJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(RefundJob.class);

    @Resource(name = "refund.createRefundJob")
    private CreateRefundJob createRefundJob;


    @Scheduled(cron="0 0/1 * * * ?")
    public void createRefund() {
        LogUtil.info(LOGGER,"创建退款 开始执行");
        createRefundJob.createRefund(null);
        LogUtil.info(LOGGER,"创建退款 执行结束");
    }

    @Scheduled(cron="0 0/1 * * * ?")
    public void breakRefund() {
        LogUtil.info(LOGGER,"失败发起退款 开始执行");
        createRefundJob.breakRefund(null);
        LogUtil.info(LOGGER,"失败发起退款 执行结束");
    }

}
