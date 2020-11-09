package com.taisf.services.stock.dao;

import com.jk.framework.base.utils.Check;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.stock.entity.StockProductEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>充值记录信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/20.
 * @version 1.0
 * @since 1.0
 */
@Repository("stock.stockProductDao")
public class StockProductDao extends BaseDao {

    private String SQLID = "stock.stockProductDao.";

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(StockProductDao.class);

    /**
     * 获取当前药品的库存限制情况
     * @param week
     * @param supplierProductType
     * @param orderType
     * @param supplierCode
     * @param productIds
     * @return
     */
    public List<StockProductEntity> getStockLimitByList(Integer week,Integer supplierProductType,Integer orderType,String supplierCode, List productIds){
        Map<String,Object> par = new HashMap<>();
        par.put("week",week);
        par.put("supplierProductType",supplierProductType);
        par.put("orderType",orderType);
        par.put("supplierCode",supplierCode);
        par.put("productIds",productIds);
        return mybatisDaoContext.findAll(SQLID+"getStockByList", StockProductEntity.class, par);
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
        if (Check.NuNObj(record.getCreateTime())){
            record.setCreateTime(new Date());
        }
        return mybatisDaoContext.save(SQLID + "saveStockProduct", record);
    }

    public int updateStockProduct(StockProductEntity record){
        if (Check.NuNObj(record)){
            return 0;
        }
        return mybatisDaoContext.update(SQLID + "updateByPrimaryKeySelective", record);
    }

}
