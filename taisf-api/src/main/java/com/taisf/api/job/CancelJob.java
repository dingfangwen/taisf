package com.taisf.api.job;

import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.order.job.CancelOrderJob;
import com.taisf.services.refund.job.CreateRefundJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>取消订单</p>
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
public class CancelJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(CancelJob.class);

    @Resource(name = "order.cancelOrderJob")
    private CancelOrderJob cancelOrderJob;

    @Scheduled(cron="0 0/1 * * * ?")
    public void createCancel() {
        LogUtil.info(LOGGER,"取消订单 开始执行");
        cancelOrderJob.dealCancelAll();
        LogUtil.info(LOGGER,"取消订单 执行结束");
    }

}
