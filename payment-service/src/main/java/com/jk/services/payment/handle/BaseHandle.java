package com.jk.services.payment.handle;

import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.services.payment.constant.PaymentConstant;
import com.jk.services.payment.dao.PayDao;
import com.jk.services.payment.dao.PayDetailDao;
import com.jk.services.payment.entity.PayEntity;
import com.jk.services.payment.entity.PayLogEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * <p>基础类</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/5/13.
 * @version 1.0
 * @since 1.0
 */
public class BaseHandle extends PaymentConstant{

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseHandle.class);

    @Autowired
    protected PayDao payDao;
    @Autowired
    protected PayDetailDao payDetailDao;



    /**
     * 保存请求信息
     * @param payId
     * @param status
     * @param statusDesc
     * @param httpType
     * @param parameter
     */
    public void savePayAndLog(int payId,String status,String statusDesc,String httpType,String parameter) {
        PayLogEntity payLog = new PayLogEntity();
        payLog.setPayId(payId);
        payLog.setParameter(parameter);
        payLog.setStatus(status);
        payLog.setStatusDesc(statusDesc);
        payLog.setType(httpType);

        LOGGER_PAY_LOG.error("落地支付日志:"+JsonEntityTransform.Object2Json(payLog));


        PayEntity pay = new PayEntity();
        pay.setId(payId);
        pay.setStatus(status);
        pay.setStatusDesc(statusDesc);
        pay.setBizTime(new Date());
        this.payDao.update(pay);



    }

    /**
     * 保存请求信息
     * @param payId
     * @param status
     * @param statusDesc
     * @param httpType
     * @param parameter
     * @param resId
     */
    public void savePayAndLog(int payId,String status,String statusDesc,String httpType,String parameter,String resId) {
        PayLogEntity payLog = new PayLogEntity();
        payLog.setPayId(payId);
        payLog.setParameter(parameter);
        payLog.setStatus(status);
        payLog.setStatusDesc(statusDesc);
        payLog.setType(httpType);
        LOGGER_PAY_LOG.error("落地支付日志:"+JsonEntityTransform.Object2Json(payLog));

        PayEntity pay = new PayEntity();
        pay.setId(payId);
        pay.setStatus(status);
        pay.setStatusDesc(statusDesc);
        pay.setBizTime(new Date());
//        pay.setReqId(payId + "");
        pay.setResId(resId);
        pay.setBizTime(new Date());
        this.payDao.update(pay);
    }

}
