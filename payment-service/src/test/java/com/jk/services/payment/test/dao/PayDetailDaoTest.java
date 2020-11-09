package com.jk.services.payment.test.dao;

import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.services.payment.dao.PayDetailDao;
import com.jk.services.payment.entity.PayDetailEntity;
import com.jk.services.payment.test.base.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * <p>明细</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/23.
 * @version 1.0
 * @since 1.0
 */
public class PayDetailDaoTest extends BaseTest {

    @Resource(name = "payment.payDetailDao")
    private PayDetailDao payDetailDao;



    @Test
    public void insertTest() {
        PayDetailEntity entity = new PayDetailEntity();
        entity.setInSerialNo("name");
        entity.setOutSerialNo("pcode");
        entity.setAmount(1);
        entity.setBizName("artnerKey");
        entity.setBizId("assword");
        entity.setPayId(1);
        entity.setStatus("123");
        entity.setStatusDesc("desc");
        entity.setRemark("remark");
        int row = payDetailDao.insert(entity);
        System.out.println(JsonEntityTransform.Object2Json(row));
    }

}
