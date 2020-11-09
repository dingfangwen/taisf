package com.taisf.web.oms.stats.controller;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.DateUtil;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.ValueUtil;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.common.valenum.EnterpriseTypeEnum;
import com.taisf.services.enterprise.vo.EnterpriseOrderStatsVO;
import com.taisf.services.enterprise.vo.EnterpriseRechargeStatsVO;
import com.taisf.services.order.api.OrderService;
import com.taisf.services.order.dto.EnterpriseStatsRequest;
import com.taisf.services.recharge.api.RechargeService;
import com.taisf.web.oms.common.page.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>企业的统计</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/20.
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/stats")
public class EnterpriseStatsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnterpriseStatsController.class);

    @Autowired
    private RechargeService rechargeService;


    @Autowired
    private OrderService orderService;


    /**
     * 企业的订单统计
     * @param request
     * @return
     */
    @RequestMapping("/orderStatsList")
    public String orderStatsList(HttpServletRequest request) {
        return "stats/orderStatsList";
    }


    /**
     * 订单统计
     * @author afi
     * @param request
     * @param enterpriseStatsRequest
     * @return
     */
    @RequestMapping("orderStatsListPage")
    @ResponseBody
    public PageResult orderStatsListPage(HttpServletRequest request, EnterpriseStatsRequest enterpriseStatsRequest) {
        PageResult pageResult = new PageResult();
        try {

            this.dealTime(enterpriseStatsRequest);
            String time = enterpriseStatsRequest.getStartStr()  + " 至 "+ enterpriseStatsRequest.getEndStr();
            DataTransferObject<List<EnterpriseOrderStatsVO>> dto = orderService.getEnterpriseOrderStats(enterpriseStatsRequest);
            List<EnterpriseOrderStatsVO> list = dto.getData();
            //查询出当前供餐商下所有菜品信息
            if (!Check.NuNCollection(list)) {
                for (EnterpriseOrderStatsVO statsVO : list) {
                    //设置区间
                    statsVO.setTime(time);
                    EnterpriseTypeEnum enterpriseTypeEnum =   EnterpriseTypeEnum.getTypeByCode(statsVO.getEnterpriseType());
                    if (enterpriseTypeEnum != null) {
                        statsVO.setEnterpriseTypeName(enterpriseTypeEnum.getName());
                    }
                }
                pageResult.setRows(dto.getData());
                pageResult.setTotal(ValueUtil.getlongValue(dto.getData().size()));
            }
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params :{}", JsonEntityTransform.Object2Json(enterpriseStatsRequest));
            LogUtil.error(LOGGER, "error :{}", e);
            return new PageResult();
        }
        return pageResult;
    }



    /**
     * 企业充值统计
     * @param request
     * @return
     */
    @RequestMapping("/rechargeStatsList")
    public String rechargeStatsList(HttpServletRequest request) {
        return "stats/rechargeStatsList";
    }


    /**
     * 企业充值统计
     * @author afi
     * @param request
     * @param enterpriseStatsRequest
     * @return
     */
    @RequestMapping("rechargeStatsListPage")
    @ResponseBody
    public PageResult rechargeStatsListPage(HttpServletRequest request, EnterpriseStatsRequest enterpriseStatsRequest) {
        PageResult pageResult = new PageResult();
        try {
            dealTime(enterpriseStatsRequest);
            String time = enterpriseStatsRequest.getStartStr()  + " 至 "+ enterpriseStatsRequest.getEndStr();
            DataTransferObject<List<EnterpriseRechargeStatsVO>> dto = rechargeService.getEnterpriseRechargeStats(enterpriseStatsRequest);
            List<EnterpriseRechargeStatsVO> list = dto.getData();
            //查询出当前供餐商下所有菜品信息
            if (!Check.NuNCollection(list)) {
                for (EnterpriseRechargeStatsVO statsVO : list) {
                    //设置区间
                    statsVO.setTime(time);
                }
                pageResult.setRows(dto.getData());
                pageResult.setTotal(ValueUtil.getlongValue(dto.getData().size()));
            }
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params :{}", JsonEntityTransform.Object2Json(enterpriseStatsRequest));
            LogUtil.error(LOGGER, "error :{}", e);
            return new PageResult();
        }
        return pageResult;
    }


    /**
     * 企业充值统计
     * @param request
     * @return
     */
    @RequestMapping("/selfStatsList")
    public String selfStatsList(HttpServletRequest request) {
        return "stats/selfStatsList";
    }


    /**
     * 企业充值统计
     * @author afi
     * @param request
     * @param enterpriseStatsRequest
     * @return
     */
    @RequestMapping("selfStatsListPage")
    @ResponseBody
    public PageResult selfStatsListPage(HttpServletRequest request, EnterpriseStatsRequest enterpriseStatsRequest) {
        PageResult pageResult = new PageResult();
        try {
            dealTime(enterpriseStatsRequest);
            String time = enterpriseStatsRequest.getStartStr()  + " 至 "+ enterpriseStatsRequest.getEndStr();
            DataTransferObject<List<EnterpriseRechargeStatsVO>> dto = rechargeService.getSelfRechargeStats(enterpriseStatsRequest);
            List<EnterpriseRechargeStatsVO> list = dto.getData();
            //查询出当前供餐商下所有菜品信息
            if (!Check.NuNCollection(list)) {
                for (EnterpriseRechargeStatsVO statsVO : list) {
                    //设置区间
                    statsVO.setTime(time);
                }
                pageResult.setRows(dto.getData());
                pageResult.setTotal(ValueUtil.getlongValue(dto.getData().size()));
            }
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params :{}", JsonEntityTransform.Object2Json(enterpriseStatsRequest));
            LogUtil.error(LOGGER, "error :{}", e);
            return new PageResult();
        }
        return pageResult;
    }


    /**
     * 处理时间
     * @author afi
     * @param enterpriseStatsRequest
     */
    private void dealTime(EnterpriseStatsRequest enterpriseStatsRequest) {
        if (Check.NuNStr(enterpriseStatsRequest.getStartStr())){
            enterpriseStatsRequest.setStartStr(DateUtil.timestampFormat(DateUtil.connectDate(DateUtil.jumpMonth(new Date(),-6),"00:00:00")));
        }
        if (Check.NuNStr(enterpriseStatsRequest.getEndStr())){
            enterpriseStatsRequest.setEndStr(DateUtil.timestampFormat(DateUtil.connectDate(new Date(),"23:59:59")));
        }
    }


}
