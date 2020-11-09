package com.taisf.services.recharge.api;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.taisf.services.enterprise.vo.EnterpriseRechargeStatsVO;
import com.taisf.services.enterprise.vo.SupRechargeStatsVO;
import com.taisf.services.order.dto.EnterpriseStatsRequest;
import com.taisf.services.order.dto.SupStatsRequest;
import com.taisf.services.recharge.dto.*;
import com.taisf.services.recharge.entity.RechargeEntity;
import com.taisf.services.recharge.vo.EnterpriseStatsNumber;

import java.util.List;
import java.util.Map;

/**
 * <p>充值</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/12.
 * @version 1.0
 * @since 1.0
 */
public interface RechargeService {


    /**
     * 获取企业充值统计信息
     * @author afi
     * @param request
     * @return
     */
    Map<String,SupRechargeStatsVO> getSupRechargeStatsMap(SupStatsRequest request);

    /**
     * 获取企业充值的统计信息
     * @author afi
     * @param request
     * @return
     */
    DataTransferObject<List<EnterpriseRechargeStatsVO>> getSelfRechargeStats(EnterpriseStatsRequest request);

    /**
     * 获取企业充值的统计信息
     * @author afi
     * @param request
     * @return
     */
    DataTransferObject<List<EnterpriseRechargeStatsVO>> getEnterpriseRechargeStats(EnterpriseStatsRequest request);


    /**
     * 分配金额-对个人
     * @author afi
     * @param request
     * @return
     */
    DataTransferObject<Void> balanceMoneyOne(BalanceMoneyOneRequest request);


    /**
     * 批量分配
     * @param listBalance
     * @return
     */
    DataTransferObject<Void> balanceMoneyList(List<BalanceMoneyRequest> listBalance,String enterpriseCode);


    /**
     * 分配金额-平均
     * @author afi
     * @param request
     * @return
     */
    DataTransferObject<Void> balanceMoneyAvg(BalanceMoneyAvgRequest request);

    /**
     * 获取企业下的员工数量和金额
     * @author afi
     * @param enterpriseCode
     * @return
     */
    DataTransferObject<EnterpriseStatsNumber>  getEnterpriseStatsNumber(String enterpriseCode);

    /**
     * 分页查询充值记录
     * @author afi
     * @param chargeHisRequest
     * @return
     */
    DataTransferObject<PagingResult<RechargeEntity>> getRechargeByPage(ChargeHisRequest chargeHisRequest);


    /**
     * 企业充值
     * @author afi
     * @param chargeRequest
     * @return
     */
    DataTransferObject<Void> chargeMoney(ChargeRequest chargeRequest);



}
