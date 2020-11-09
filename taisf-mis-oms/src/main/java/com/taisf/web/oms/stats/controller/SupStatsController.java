package com.taisf.web.oms.stats.controller;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.DateUtil;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.ValueUtil;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.enterprise.vo.*;
import com.taisf.services.order.api.OrderService;
import com.taisf.services.order.dto.EnterpriseStatsRequest;
import com.taisf.services.order.dto.SupStatsRequest;
import com.taisf.services.pay.api.RechargeOrderService;
import com.taisf.services.recharge.api.RechargeService;
import com.taisf.services.supplier.api.SupplierService;
import com.taisf.services.supplier.entity.SupplierEntity;
import com.taisf.web.oms.common.page.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
public class SupStatsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SupStatsController.class);

    @Autowired
    private RechargeService rechargeService;


    @Autowired
    private RechargeOrderService rechargeOrderService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SupplierService supplierService;



    /**
     * 企业的订单统计
     * @param request
     * @return
     */
    @RequestMapping("/supStatsList")
    public String supStatsList(HttpServletRequest request) {
        return "stats/supStatsList";
    }


    /**
     * 订单统计
     * @author afi
     * @param request
     * @param supStatsRequest
     * @return
     */
    @RequestMapping("supStatsListPage")
    @ResponseBody
    public PageResult orderStatsListPage(HttpServletRequest request, SupStatsRequest supStatsRequest) {
        PageResult pageResult = new PageResult();
        try {

            this.dealTime(supStatsRequest);


            DataTransferObject<List<SupplierEntity>> allSupplierList = supplierService.getSupplierList(supStatsRequest.getSupplierCode());
            List<SupplierEntity> listAll = allSupplierList.getData();
            if (Check.NuNCollection(listAll)){
                return pageResult;
            }
            //统计的list
            List<SupStatsVO>  supStatsVOList  = new ArrayList<>();
            //填充供应商信息
            for (SupplierEntity supplierEntity : listAll) {
                SupStatsVO supStatsVO = new SupStatsVO();
                supStatsVO.setSupplierCode(supplierEntity.getSupplierCode());
                supStatsVO.setSupplierName(supplierEntity.getSupplierName());
                supStatsVO.setTime(getTime(supStatsRequest));
                supStatsVOList.add(supStatsVO);
            }

            //统计订单信息
            this.dealOrder(supStatsRequest,supStatsVOList);

            //统计充值信息
            this.dealSupRecharge(supStatsRequest,supStatsVOList);

            //统计个人信息
            this.dealSelfSupRecharge(supStatsRequest,supStatsVOList);

            pageResult.setRows(supStatsVOList);
            pageResult.setTotal(ValueUtil.getlongValue(supStatsVOList.size()));
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params :{}", JsonEntityTransform.Object2Json(supStatsRequest));
            LogUtil.error(LOGGER, "error :{}", e);
            return new PageResult();
        }
        return pageResult;
    }



    /**
     * 处理订单相关
     * @param supStatsRequest
     * @param listAll
     */
    private void dealSelfSupRecharge(SupStatsRequest supStatsRequest,List<SupStatsVO> listAll){
        if (Check.NuNCollection(listAll)){
            return;
        }
        //统计个人充值
        Map<String, SupRechargeStatsVO> selfRechargeSupStatsMap = rechargeOrderService.getSelfRechargeSupStatsMap(supStatsRequest);

        for (SupStatsVO supStatsVO : listAll) {
            String key = supStatsVO.getSupplierCode();
            if (selfRechargeSupStatsMap.containsKey(key)){
                SupRechargeStatsVO self = selfRechargeSupStatsMap.get(key);
                supStatsVO.setOrderRechargeNum(self.getNum());
                supStatsVO.setOrderRechargePrice(self.getPrice());
            }
        }
    }


    /**
     * 处理订单相关
     * @param supStatsRequest
     * @param listAll
     */
    private void dealSupRecharge(SupStatsRequest supStatsRequest,List<SupStatsVO> listAll){
        if (Check.NuNCollection(listAll)){
            return;
        }
        //统计订单信息
        Map<String, SupRechargeStatsVO> supRechargeStatsMap = rechargeService.getSupRechargeStatsMap(supStatsRequest);
        for (SupStatsVO supStatsVO : listAll) {
            String key = supStatsVO.getSupplierCode();
            if (supRechargeStatsMap.containsKey(key)){
                SupRechargeStatsVO supRechargeStatsVO = supRechargeStatsMap.get(key);
                supStatsVO.setRechargeNum(supRechargeStatsVO.getNum());
                supStatsVO.setRechargePrice(supRechargeStatsVO.getPrice());

            }
        }
    }


    /**
     * 处理订单相关
     * @param supStatsRequest
     * @param listAll
     */
    private void dealOrder(SupStatsRequest supStatsRequest,List<SupStatsVO> listAll){
        if (Check.NuNCollection(listAll)){
            return;
        }
        //统计订单信息
        Map<String, SupOrderStatsVO> supOrderStatsMap = orderService.getSupOrderStatsMap(supStatsRequest);
        for (SupStatsVO supStatsVO : listAll) {
            String key = supStatsVO.getSupplierCode();
            if (supOrderStatsMap.containsKey(key)){
                SupOrderStatsVO supOrderStatsVO = supOrderStatsMap.get(key);
                supStatsVO.setPayBalance(supOrderStatsVO.getPayBalance());
                supStatsVO.setPayMoney(supOrderStatsVO.getPayMoney());
            }

        }
    }


    /**
     * 处理时间
     * @author afi
     * @param supStatsRequest
     */
    private String  getTime(SupStatsRequest supStatsRequest) {
        //处理时间
        dealTime(supStatsRequest);
        String time = supStatsRequest.getStartStr()  + " 至 "+ supStatsRequest.getEndStr();
        return time;
    }


    /**
     * 处理时间
     * @author afi
     * @param enterpriseStatsRequest
     */
    private void dealTime(SupStatsRequest enterpriseStatsRequest) {
        if (Check.NuNStr(enterpriseStatsRequest.getStartStr())){
            enterpriseStatsRequest.setStartStr(DateUtil.timestampFormat(DateUtil.connectDate(DateUtil.jumpMonth(new Date(),-6),"00:00:00")));
        }
        if (Check.NuNStr(enterpriseStatsRequest.getEndStr())){
            enterpriseStatsRequest.setEndStr(DateUtil.timestampFormat(DateUtil.connectDate(new Date(),"23:59:59")));
        }
    }


}
