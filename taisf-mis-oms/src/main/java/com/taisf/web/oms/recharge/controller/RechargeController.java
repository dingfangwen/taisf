package com.taisf.web.oms.recharge.controller;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.DateUtil;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.recharge.api.RechargeService;
import com.taisf.services.recharge.dto.ChargeHisRequest;
import com.taisf.services.recharge.entity.RechargeEntity;
import com.taisf.web.oms.common.page.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>充值相关</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/16.
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/recharge")
public class RechargeController {


    private static final Logger LOGGER = LoggerFactory.getLogger(RechargeController.class);

    @Autowired
    private RechargeService rechargeService;



    @RequestMapping("/history")
    public String list(HttpServletRequest request) {
        return "recharge/rechargeList";
    }


    /**
     * 充值记录
     * @author afi
     * @param request
     * @param chargeHisRequest
     * @return
     */
    @RequestMapping("historyPage")
    @ResponseBody
    public PageResult historyPage(HttpServletRequest request, ChargeHisRequest chargeHisRequest) {

        PageResult pageResult = new PageResult();
        try {
            if (!Check.NuNStr(chargeHisRequest.getStartStr())){
                Date startTime = DateUtil.parseDate(chargeHisRequest.getStartStr(),DateUtil.dateFormatPattern);
                chargeHisRequest.setStart(startTime);
            }

            if (!Check.NuNStr(chargeHisRequest.getEndStr())){
                Date endTime = DateUtil.parseDate(chargeHisRequest.getEndStr(),DateUtil.dateFormatPattern);
                chargeHisRequest.setEnd(endTime);
            }

            DataTransferObject<PagingResult<RechargeEntity>> dto = rechargeService.getRechargeByPage(chargeHisRequest);
            //查询出当前供餐商下所有菜品信息
            if (!Check.NuNObj(dto.getData())) {
                pageResult.setRows(dto.getData().getList());
                pageResult.setTotal(dto.getData().getTotal());
            }
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params :{}", JsonEntityTransform.Object2Json(chargeHisRequest));
            LogUtil.error(LOGGER, "error :{}", e);
            return new PageResult();
        }
        return pageResult;
    }

}
