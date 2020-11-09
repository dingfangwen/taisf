package com.taisf.services.test.buffet.dao;

import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.buffet.dao.BuffetDao;
import com.taisf.services.buffet.dto.BuffetInfoRequest;
import com.taisf.services.buffet.entity.BuffetEntity;
import com.taisf.services.order.dao.OrderBuffetDao;
import com.taisf.services.order.entity.OrderBuffetEntity;
import com.taisf.services.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
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
 * @author afi on on 2019/4/4.
 * @version 1.0
 * @since 1.0
 */
public class BuffetDaoTest extends BaseTest {


    @Resource(name = "buffet.buffetDao")
    private BuffetDao buffetDao;


    @Test
    public void saveBuffetTest(){
        BuffetEntity entity = new BuffetEntity();
        entity.setFid("fid");
        entity.setSupplierCode("supplierCode");
        entity.setProvinceCode("provinceCode");
        entity.setCountyCode("countyCode");
        entity.setCityCode("cityCode");
        entity.setAddress("address");
        entity.setCreateTime(new Date());
        buffetDao.saveBuffet(entity);
    }

    @Test
    public void getBuffetByFidTest(){
        BuffetInfoRequest entity = new BuffetInfoRequest();
        entity.setBuffetFid("CD-200131-6666");
        BuffetEntity buffetByFid = buffetDao.getBuffetByFid(entity);

        System.out.println(JsonEntityTransform.Object2Json(buffetByFid));
    }

}
