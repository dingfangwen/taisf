package com.taisf.web.enterprise.controller;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.BigDecimalUtil;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.order.api.OrderService;
import com.taisf.services.order.dto.BackRefundOrderRequest;
import com.taisf.services.refund.api.RefundService;
import com.taisf.services.refund.dto.RefundQueryRequest;
import com.taisf.services.refund.entity.RefundEntity;
import com.taisf.services.refund.vo.RefundVo;
import com.taisf.services.ups.entity.EmployeeEntity;
import com.taisf.web.enterprise.common.constant.LoginConstant;
import com.taisf.web.enterprise.common.page.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:zhangzhengguang
 * @date:2017/12/21
 * @description:退款
 **/
@Controller
@RequestMapping("refund/")
public class RefundController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RefundController.class);

    @Autowired
    private RefundService refundService;


    @Autowired
    private OrderService orderService;
    /**
     * @author:zhangzhengguang
     * @date:2017/12/21
     * @description:去退款列表页面
     **/
    @RequestMapping("list")
    public String list(HttpServletRequest request) {
        return "refund/refundList";
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/12/21
     * @description:分页查询退款列表数据
     **/
    @RequestMapping("pageList")
    @ResponseBody
    public PageResult pageList(HttpServletRequest request, RefundQueryRequest refundQueryRequest) {
        PageResult pageResult = new PageResult();
        try {
            EmployeeEntity emp = (EmployeeEntity)request.getSession().getAttribute(LoginConstant.SESSION_KEY);
            refundQueryRequest.setSupplierCode(emp.getEmpBiz());
            DataTransferObject<PagingResult<RefundVo>> dto = refundService.refundPageList(refundQueryRequest);
            if (!Check.NuNObj(dto.getData())) {
                pageResult.setRows(dto.getData().getList());
                pageResult.setTotal(dto.getData().getTotal());
            }
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params:{}", JsonEntityTransform.Object2Json(pageResult));
            LogUtil.error(LOGGER, "error:{}", e);
            return new PageResult();
        }
        return pageResult;
    }


    /**
     * @author:zhangzhengguang
     * @date:2017/12/21
     * @description:查询详情
     **/
    @RequestMapping("toedit")
    @ResponseBody
    public DataTransferObject<RefundEntity> toedit(HttpServletRequest request, Integer id) {
        DataTransferObject<RefundEntity> dto = new DataTransferObject<>();
        if (Check.NuNObj(id)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            dto = refundService.findRefundById(id);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("系统错误");
            return dto;
        }
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/12/21
     * @description:修改
     **/
    @RequestMapping("updateRefund")
    @ResponseBody
    public DataTransferObject<Void> updateRefund(HttpServletRequest request, RefundEntity refundEntity,Double fee) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(refundEntity)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if(Check.NuNObj(fee)){
            dto.setErrorMsg("无效的退款金额");
            return dto;
        }else {
            Double mul = BigDecimalUtil.mul(fee, 100);
            refundEntity.setRefundFee(mul.intValue());
        }
        try {
            dto = refundService.updateRefund(refundEntity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("系统错误");
            return dto;
        }
        return dto;
    }

    @RequestMapping("onLineRefund")
    @ResponseBody
    public DataTransferObject<String> onLineRefund(HttpServletRequest request, String orderSn,Integer refundMoney) {
        DataTransferObject<String> dto = new DataTransferObject<>();
        if (Check.NuNObjs(orderSn,refundMoney)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }

        try {
            BackRefundOrderRequest backRefundOrderRequest = new BackRefundOrderRequest();
            backRefundOrderRequest.setRefundMoney(refundMoney);
            backRefundOrderRequest.setOrderSn(orderSn);
            dto = orderService.backCancelOrder(backRefundOrderRequest);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "error:{}", e);
            dto.setErrorMsg("系统错误");
            return dto;
        }
        return dto;
    }
}
