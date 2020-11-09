package com.taisf.web.enterprise.cashondelivery.controller;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.BigDecimalUtil;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.common.valenum.OrderTypeEnum;
import com.taisf.services.common.valenum.UserTypeEnum;
import com.taisf.services.order.api.OrderService;
import com.taisf.services.order.dto.CreateOrderRequest;
import com.taisf.services.order.dto.OrderInfoRequest;
import com.taisf.services.order.manager.OrderManagerImpl;
import com.taisf.services.order.vo.FaceVO;
import com.taisf.services.order.vo.OrderInfoVO;
import com.taisf.services.ups.entity.EmployeeEntity;
import com.taisf.services.user.entity.UserEntity;
import com.taisf.services.user.manager.UserManagerImpl;
import com.taisf.web.enterprise.common.constant.LoginConstant;
import com.taisf.web.enterprise.common.page.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("cashOnDelivery/")
public class CashOnDeliveryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CashOnDeliveryController.class);

    @Autowired
    private OrderManagerImpl orderManagerImpl;


    @Autowired
    private OrderService orderService;

    @Resource(name = "user.userManagerImpl")
    private UserManagerImpl userManager;


    /**
     * @author:zhangzhengguang
     * @date:2017/10/16
     * @description:订单列表列表
     **/
    @RequestMapping("list")
    public String list(HttpServletRequest request) {
        return "cashondelivery/orderList";
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/16
     * @description:订单详情商品列表
     **/
    @RequestMapping("showOrderProduct")
    public String showOrderProduct(HttpServletRequest request, String orderSn) {
        request.setAttribute("orderSn", orderSn);
        return "order/orderProductList";
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/16
     * @description:分页查询订单列表数据
     **/
    @RequestMapping("pageList")
    @ResponseBody
    public PageResult pageList(HttpServletRequest request, OrderInfoRequest orderInfoRequest) {
        PageResult pageResult = new PageResult();
        try {
            EmployeeEntity emp = (EmployeeEntity) request.getSession().getAttribute(LoginConstant.SESSION_KEY);
            orderInfoRequest.setBizCode(emp.getEmpBiz());
            orderInfoRequest.setOrderType(OrderTypeEnum.FACE.getCode());
            PagingResult<OrderInfoVO> pagingResult = orderManagerImpl.pageListOrder(orderInfoRequest);
            if (!Check.NuNObj(pagingResult)) {
                pageResult.setRows(pagingResult.getList());
                pageResult.setTotal(pagingResult.getTotal());
            }
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params:{}", JsonEntityTransform.Object2Json(orderInfoRequest));
            LogUtil.error(LOGGER, "error:{}", e);
            return new PageResult();
        }
        return pageResult;
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/2/1
     * @description:确认收款
     **/
    @RequestMapping("confirmation")
    @ResponseBody
    public DataTransferObject<FaceVO> confirmation(HttpServletRequest request, String userTel, Double sumMoney) {
        DataTransferObject<FaceVO> dto = new DataTransferObject<>();
        if (Check.NuNObjs(userTel, sumMoney)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            EmployeeEntity employeeEntity = (EmployeeEntity) request.getSession().getAttribute(LoginConstant.SESSION_KEY);
            String biz = employeeEntity.getEmpBiz();
            CreateOrderRequest createOrderRequest = new CreateOrderRequest();
            UserEntity userEntity = userManager.getUserByUserPhone(userTel, UserTypeEnum.YONGHU.getCode());
            if (Check.NuNObj(userEntity)) {
                dto.setErrorMsg("当前用户不存在");
                return dto;
            }
            createOrderRequest.setUserUid(userEntity.getUserUid());
            createOrderRequest.setBusinessUid(biz);
            createOrderRequest.setSource(3);
            sumMoney = BigDecimalUtil.mul(sumMoney, 100);
            createOrderRequest.setPrice(sumMoney.intValue());
            dto = orderService.faceOrder(createOrderRequest,false);
        } catch (Exception e) {
            LogUtil.info(LOGGER, "确认收款异常params:{}{}", userTel, sumMoney);
            LogUtil.error(LOGGER, "确认收款异常error:{}", e);
            return null;
        }
        return dto;
    }

}
