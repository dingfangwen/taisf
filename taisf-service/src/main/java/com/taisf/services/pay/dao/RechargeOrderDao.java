package com.taisf.services.pay.dao;

import com.jk.framework.base.exception.BusinessException;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.dao.page.PageBounds;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.enterprise.dto.EnterpriseAddressRequest;
import com.taisf.services.enterprise.entity.EnterpriseAddressEntity;
import com.taisf.services.enterprise.vo.EnterpriseRechargeStatsVO;
import com.taisf.services.enterprise.vo.SupRechargeStatsVO;
import com.taisf.services.order.dto.EnterpriseStatsRequest;
import com.taisf.services.order.dto.SupStatsRequest;
import com.taisf.services.pay.dto.RechargeOrderListRequest;
import com.taisf.services.pay.entity.RechargeOrderEntity;
import com.taisf.services.pay.vo.RechargeOrderVO;
import com.taisf.services.user.vo.AccountUserLogVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>用户的充值</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/3/25.
 * @version 1.0
 * @since 1.0
 */
@Repository("pay.rechargeOrderDao")
public class RechargeOrderDao extends BaseDao {

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(RechargeOrderDao.class);

    private String SQLID = "pay.rechargeOrderDao.";


    /**
     * 获取全量
     * @param rechargeOrderListRequest
     * @return
     */
    public List<RechargeOrderVO> findRechargeOrderAll(RechargeOrderListRequest rechargeOrderListRequest) {
        return mybatisDaoContext.findAll(SQLID + "findRechargeOrderList", RechargeOrderVO.class,rechargeOrderListRequest);
    }



    public PagingResult<RechargeOrderVO> findRechargeOrderByPage(RechargeOrderListRequest rechargeOrderListRequest) {
        PageBounds pageBounds = new PageBounds();
        pageBounds.setLimit(rechargeOrderListRequest.getLimit());
        pageBounds.setPage(rechargeOrderListRequest.getPage());
        return mybatisDaoContext.findForPage(SQLID + "findRechargeOrderList", RechargeOrderVO.class,rechargeOrderListRequest,pageBounds);

    }



    /**
     * 获取当前的配置信息
     * @author afi
     * @param orderSn
     * @return
     */
    public RechargeOrderEntity getRechargeOrderByOrderSn(String orderSn){
        Map<String,Object> par = new HashMap<>();
        par.put("orderSn",orderSn);
        return mybatisDaoContext.findOne(SQLID + "getRechargeOrderByOrderSn",RechargeOrderEntity.class,par);
    }



    /**
     * 保存支付回调信息
     * @author afi
     * @param rechargeOrderEntity
     * @return
     */
    public int saveRechargeOrder(RechargeOrderEntity  rechargeOrderEntity){
        if (Check.NuNObj(rechargeOrderEntity.getCreateTime())){
            rechargeOrderEntity.setCreateTime(new Date());
        }
        return mybatisDaoContext.save(SQLID + "saveRechargeOrder",rechargeOrderEntity);
    }


    /**
     * 修改支付记录
     * @author afi
     * @param recordEntity
     * @return
     */
    public int updateRechargeOrder(RechargeOrderEntity  recordEntity){
        if (Check.NuNObjs(recordEntity.getOrderSn())){
            throw new BusinessException("更新主键判断为空");
        }
        return mybatisDaoContext.update(SQLID + "updateRechargeOrder",recordEntity);
    }

    /**
     * 获取企业充值统计信息
     * @author afi
     * @param request
     * @return
     */
    public List<EnterpriseRechargeStatsVO> getSelfRechargeStats(EnterpriseStatsRequest request){
        return mybatisDaoContext.findAll(SQLID + "getSelfRechargeStats", EnterpriseRechargeStatsVO.class, request);
    }




    /**
     * 获取企业充值统计信息
     * @author afi
     * @param request
     * @return
     */
    public List<SupRechargeStatsVO> getSelfRechargeSupStats(SupStatsRequest request){
        return mybatisDaoContext.findAll(SQLID + "getSelfRechargeSupStats", SupRechargeStatsVO.class, request);
    }

}
