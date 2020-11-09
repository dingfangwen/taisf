package com.taisf.services.test.stock.dao;

import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.stock.dao.StockProductDao;
import com.taisf.services.stock.dao.StockWeekDao;
import com.taisf.services.stock.entity.StockProductEntity;
import com.taisf.services.stock.entity.StockWeekEntity;
import com.taisf.services.stock.vo.StockHasVO;
import com.taisf.services.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
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
 * @author afi on on 2018/3/22.
 * @version 1.0
 * @since 1.0
 */
public class StockWeekDaoTest extends BaseTest {

    @Resource(name = "stock.stockWeekDao")
    private StockWeekDao stockWeekDao;




    @Test
    public void getStockWeekByListTest() {
        List<Integer>  list  =new ArrayList<>();
        list.add(1);
        List<StockHasVO> stockLimitByList = stockWeekDao.getStockWeekByList(1,1,1,"b",list);
        System.out.println(JsonEntityTransform.Object2Json(stockLimitByList));
    }



    @Test
    public void saveStockWeekTest() {
        StockWeekEntity entity = new StockWeekEntity();
        entity.setSupplierCode("b");
        entity.setWeek(1);
        entity.setProductCode(1);
        entity.setSupplierProductType(1);
        entity.setOrderSn("sn");
        entity.setProductNum(1);
        stockWeekDao.saveStockWeek(entity);
    }



}
