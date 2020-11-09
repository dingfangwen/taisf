package com.taisf.services.stock.manager;

import com.jk.framework.base.exception.BusinessException;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.ValueUtil;
import com.taisf.services.stock.dao.StockProductDao;
import com.taisf.services.stock.entity.StockProductEntity;
import com.taisf.services.stock.vo.StockCheckVO;
import com.taisf.services.stock.vo.StockHasVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>用户信息</p>
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
@Service("stock.stockProductManagerImpl")
public class StockProductManagerImpl {

    @Resource(name="stock.stockWeekManagerImpl")
    private StockWeekManagerImpl stockWeekManager;

    @Resource(name="stock.stockProductDao")
    private StockProductDao stockProductDao;

    /**
     * 校验当前的库存信息
     * @param week
     * @param supplierProductType
     * @param orderType
     * @param supplierCode
     * @param productIds
     * @return
     */
    public Map<String,StockCheckVO>  checkStockLimit(Integer week, Integer supplierProductType, Integer orderType, String supplierCode, List productIds){
        Map<String,StockCheckVO> map = new HashMap<>();
        if (Check.NuNCollection(productIds)){
           return map;
        }
        if (Check.NuNObjs(week,supplierProductType,orderType,supplierCode)){
            throw  new BusinessException("参数为空");
        }

        //获取当前的设置map
        Map<String, StockProductEntity> stockLimitMap = this.getStockLimitMap(week, supplierProductType, orderType, supplierCode, productIds);

        //当前商品的库存情况
        Map<String, StockHasVO> stockMap = stockWeekManager.getStockLimitMap(week, supplierProductType, orderType, supplierCode, productIds);
        Map<String,StockCheckVO> stocksMap = new HashMap<>();
        for (Object productId : productIds) {
            String key = ValueUtil.getStrValue(productId);
            StockCheckVO check = new StockCheckVO();
            check.setProductCode(ValueUtil.getintValue(productId));

            if (stockLimitMap.containsKey(key)){
                StockProductEntity pro = stockLimitMap.get(key);
                if (!Check.NuNObj(pro)){
                    check.setProductLimit(pro.getProductLimit());
                    check.setLimitId(pro.getId());
                }
            }
            if (Check.NuNObj(check.getProductLimit())){
                check.setProductLimit(999);
            }

            if (stockMap.containsKey(key)){
                StockHasVO has = stockMap.get(key);
                if (!Check.NuNObj(has)){
                    check.setProductNum(has.getProductNum());
                }
            }

            if (Check.NuNObj(check.getProductNum())){
                check.setProductNum(0);
            }
            stocksMap.put(key,check);
        }
        return stocksMap;
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
    public Map<String,StockProductEntity> getStockLimitMap(Integer week, Integer supplierProductType, Integer orderType, String supplierCode, List productIds) {
        Map<String,StockProductEntity> map = new HashMap<>();
        List<StockProductEntity> stockList =  this.getStockLimitByList(week,supplierProductType,orderType,supplierCode,productIds);
        if (!Check.NuNCollection(stockList)){
            for (StockProductEntity stockProductEntity : stockList) {
                map.put(ValueUtil.getStrValue(stockProductEntity.getProductCode()),stockProductEntity);
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
    public List<StockProductEntity> getStockLimitByList(Integer week, Integer supplierProductType, Integer orderType, String supplierCode, List productIds) {
        List<StockProductEntity> list = new ArrayList<>();
        if (Check.NuNCollection(productIds)){
            return list;
        }
        if (Check.NuNObjs(week,supplierProductType,orderType,supplierCode)){
            return list;
        }
        return stockProductDao.getStockLimitByList(week,supplierProductType,orderType,supplierCode,productIds);
    }

    /**
     * 保存库存限制信息
     * @author afi
     * @param record
     * @return
     */
    public int saveStockProduct(StockProductEntity record){
        if (Check.NuNObj(record)){
            return 0;
        }
        return stockProductDao.saveStockProduct(record);
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/3/26
     * @description:修改
     **/
    public int updateStockProduct(StockProductEntity record){
        return stockProductDao.updateStockProduct(record);
    }


}
