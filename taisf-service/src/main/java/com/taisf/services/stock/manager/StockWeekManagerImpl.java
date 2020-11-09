package com.taisf.services.stock.manager;

import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.ValueUtil;
import com.taisf.services.stock.dao.StockWeekDao;
import com.taisf.services.stock.entity.StockWeekEntity;
import com.taisf.services.stock.vo.StockHasVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>库存的占用情况</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/29.
 * @version 1.0
 * @since 1.0
 */
@Service("stock.stockWeekManagerImpl")
public class StockWeekManagerImpl {


    @Resource(name="stock.stockWeekDao")
    private StockWeekDao stockWeekDao;



    /**
     * 获取当前药品的库存限制情况
     * @param week
     * @param supplierProductType
     * @param orderType
     * @param supplierCode
     * @param productIds
     * @return
     */
    public Map<String,StockHasVO> getStockLimitMap(Integer week, Integer supplierProductType, Integer orderType, String supplierCode, List productIds) {
        Map<String,StockHasVO> map = new HashMap<>();
        List<StockHasVO> stockList =  getStockWeekByList(week,supplierProductType,orderType,supplierCode,productIds);
        if (!Check.NuNCollection(stockList)){
            for (StockHasVO stockHasVO : stockList) {
                map.put(ValueUtil.getStrValue(stockHasVO.getProductCode()),stockHasVO);
            }
        }
        return map;
    }




    /**
     * 获取当前药品的库存限制情况
     * @param week
     * @param supplierProductType
     * @param orderType
     * @param supplierCode
     * @param productIds
     * @return
     */
    public List<StockHasVO> getStockWeekByList(Integer week, Integer supplierProductType, Integer orderType, String supplierCode, List productIds) {
        List<StockHasVO> list = new ArrayList<>();
        if (Check.NuNCollection(productIds)){
            return list;
        }
        if (Check.NuNObjs(week,supplierProductType,orderType,supplierCode)){
            return list;
        }
        return stockWeekDao.getStockWeekByList(week,supplierProductType,orderType,supplierCode,productIds);
    }

    /**
     * 保存库存限制信息
     * @author afi
     * @param record
     * @return
     */
    public int saveStockWeek(StockWeekEntity record){
        if (Check.NuNObj(record)){
            return 0;
        }
        return stockWeekDao.saveStockWeek(record);
    }


}
