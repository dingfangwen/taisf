package com.taisf.services.stock.dao;

import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.DateUtil;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.stock.entity.StockWeekEntity;
import com.taisf.services.stock.vo.StockHasVO;
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
@Repository("stock.stockWeekDao")
public class StockWeekDao extends BaseDao {

    private String SQLID = "stock.stockWeekDao.";

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(StockWeekDao.class);

    /**
     * 获取当前药品的库存限制情况
     * @param week
     * @param supplierProductType
     * @param orderType
     * @param supplierCode
     * @param productIds
     * @return
     */
    public List<StockHasVO> getStockWeekByList(Integer week, Integer supplierProductType, Integer orderType, String supplierCode, List productIds){
        Map<String,Object> par = new HashMap<>();
        par.put("week",week);
        par.put("weekSn",DateUtil.getFirstDayOfWeek(new Date()));
        par.put("supplierProductType",supplierProductType);
        par.put("orderType",orderType);
        par.put("supplierCode",supplierCode);
        par.put("productIds",productIds);
        return mybatisDaoContext.findAll(SQLID+"getStockWeekByList", StockHasVO.class, par);
    }

    /**
     * 批量保存当前的库存情况
     * @author afi
     * @param list
     * @return
     */
    public int batchSaveStockWeek(List<StockWeekEntity> list){
        if (Check.NuNCollection(list)){
            return 0;
        }
        for (StockWeekEntity record : list) {
            if (Check.NuNObj(record.getCreateTime())){
                record.setCreateTime(new Date());
            }
            if (Check.NuNStr(record.getWeekSn())){
                record.setWeekSn(DateUtil.getFirstDayOfWeek(new Date()));
            }
        }
        return mybatisDaoContext.batchSave(SQLID + "saveStockWeek", list);
    }


    /**
     * 保存当前的库存情况
     * @author afi
     * @param record
     * @return
     */
    public int saveStockWeek(StockWeekEntity record){
        if (Check.NuNObj(record)){
            return 0;
        }
        if (Check.NuNObj(record.getCreateTime())){
            record.setCreateTime(new Date());
        }
        if (Check.NuNStr(record.getWeekSn())){
            record.setWeekSn(DateUtil.getFirstDayOfWeek(new Date()));
        }
        return mybatisDaoContext.save(SQLID + "saveStockWeek", record);
    }

}
