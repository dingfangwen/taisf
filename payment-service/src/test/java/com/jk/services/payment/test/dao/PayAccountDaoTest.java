package com.jk.services.payment.test.dao;

import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.services.payment.dao.PayAccountDao;
import com.jk.services.payment.entity.PayAccountEntity;
import com.jk.services.payment.test.base.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>TODO</p>
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
public class PayAccountDaoTest extends BaseTest {

    @Resource(name = "payment.payAccountDao")
    private PayAccountDao payAccountDao;



    @Test
    public void insertTest() {
        PayAccountEntity entity = new PayAccountEntity();
        entity.setName("name");
        entity.setProductCode("pcode");
        entity.setPartner("par");
        entity.setPartner("artnerKey");
        entity.setPartner("assword");
        entity.setAccount("account");
        entity.setAccountName("accountName");
        entity.setCode("ode");
        entity.setCodeKey("odeKey");
        entity.setRemark("remark");
        entity.setCaFileName("caFileName");
        entity.setCaPassword("caPassword");
        entity.setBalance(new BigDecimal("11.11"));
        int row = payAccountDao.insert(entity);
        System.out.println(JsonEntityTransform.Object2Json(row));
    }

}
