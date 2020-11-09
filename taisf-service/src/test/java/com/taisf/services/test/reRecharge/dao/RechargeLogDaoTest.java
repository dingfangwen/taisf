package com.taisf.services.test.reRecharge.dao;

import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.recharge.dao.RechargeDao;
import com.taisf.services.recharge.dao.RechargeLogDao;
import com.taisf.services.recharge.entity.RechargeEntity;
import com.taisf.services.recharge.entity.RechargeLogEntity;
import com.taisf.services.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/25.
 * @version 1.0
 * @since 1.0
 */
public class RechargeLogDaoTest extends BaseTest {


    @Resource(name = "recharge.rechargeLogDao")
    private RechargeLogDao rechargeLogDao;


    @Test
    public void saveRechargeLogTest() {
        RechargeLogEntity record = new RechargeLogEntity();
        record.setRechargeSn("sv");
        record.setFromStatus(11);
        record.setToStatus(1);
        record.setTitle("title");
        record.setRemark("mark");
        record.setCreateId("creatid");
        rechargeLogDao.saveRechargeLog(record);
    }



    @Test
    public void getRechargeLogBySnTest() {
        List<RechargeLogEntity> list = rechargeLogDao.getRechargeLogBySn("sv");
        System.out.println(JsonEntityTransform.Object2Json(list));
    }

}
