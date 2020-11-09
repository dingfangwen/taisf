package com.taisf.api.order.controller;

import com.jk.framework.base.constant.YesNoEnum;
import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.head.Header;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.rst.ResponseDto;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.api.common.abs.AbstractController;
import com.taisf.services.order.api.OrderOpService;
import com.taisf.services.order.api.OrderService;
import com.taisf.services.order.dto.BuffetInOrderRequest;
import com.taisf.services.order.dto.FinishOrderRequest;
import com.taisf.services.order.dto.OrderInfoRequest;
import com.taisf.services.order.vo.OrderInfoVO;
import com.taisf.services.supplier.api.SupplierService;
import com.taisf.services.supplier.vo.SupplierPayInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>订单相关</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/knight")
public class KnightController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnightController.class);

    @Autowired
    private OrderService ordersService;

    @Autowired
    private OrderOpService orderOpService;

    @Autowired
    private SupplierService supplierService;


    /**
     * 结束订单
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/payCode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto payCode(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        String userId = getUserId(request);
        if (Check.NuNStr(userId)){
            return new ResponseDto("请登录");
        }

        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(userId));
        try {

            DataTransferObject<SupplierPayInfo> dto =supplierService.getPayInfo4knight(userId);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【结束订单】错误,par:{}, e={}",JsonEntityTransform.Object2Json(userId), e);
            return new ResponseDto("未知错误");
        }

    }


    /**
     * 结束订单
     * @author afi
      * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/finishOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto initOrder(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        //获取当前参数
        FinishOrderRequest paramRequest = getEntity(request, FinishOrderRequest.class);
        paramRequest.setOpId(getUserId(request));
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }

        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {

            DataTransferObject<Void> dto =orderOpService.finishOrder(paramRequest);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【结束订单】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }

    }


    /**
     * 骑手放餐
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/buffetIn", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto buffetIn(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        //获取当前参数
        BuffetInOrderRequest paramRequest = getEntity(request, BuffetInOrderRequest.class);
        paramRequest.setOpId(getUserId(request));
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }

        LogUtil.info(LOGGER, "放餐到餐柜参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {
            DataTransferObject<Void> dto =orderOpService.buffetInOrder(paramRequest);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【放餐】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }

    }




    /**
     * 待签收
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto list(HttpServletRequest request, HttpServletResponse response,String userUid,String userPhone) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        if (!Check.NuNStr(userUid)) {
            return getOrderInfoWaitingListByUid(userUid).trans2Res();
        }else {
            return getOrderInfoWaitingListByPhone(userPhone).trans2Res();
        }
    }


    /**
     * 获取当前的待签收列表
     * @auth afi
     * @param userPhone
     * @return
     */
    private DataTransferObject<List<OrderInfoVO>> getOrderInfoWaitingListByPhone(String userPhone){
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(userPhone));
        DataTransferObject dto = new DataTransferObject<>();
        if (Check.NuNStr(userPhone)){
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            DataTransferObject<List<OrderInfoVO>> dtoRpc =ordersService.getOrderInfoWaitingListByPhone(userPhone);
            return dtoRpc;
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【待签收列表】错误,par:{}, e={}",JsonEntityTransform.Object2Json(userPhone), e);

            dto.setErrorMsg("调用失败");
            return dto;
        }
    }




    /**
     * 获取当前的待签收列表
     * @auth afi
     * @param userUid
     * @return
     */
    private DataTransferObject<List<OrderInfoVO>> getOrderInfoWaitingListByUid(String userUid){
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(userUid));
        DataTransferObject dto = new DataTransferObject<>();
        if (Check.NuNStr(userUid)){
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            DataTransferObject<List<OrderInfoVO>> dtoRpc =ordersService.getOrderInfoWaitingList(userUid);
            return dtoRpc;
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【待签收列表】错误,par:{}, e={}",JsonEntityTransform.Object2Json(userUid), e);

            dto.setErrorMsg("调用失败");
            return dto;
        }
    }


    /**
     * 配送记录
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/history", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto history(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        //获取当前参数
        OrderInfoRequest paramRequest = getEntity(request, OrderInfoRequest.class);
        paramRequest.setKnightUid(getUserId(request));
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("异常参数");
        }
        paramRequest.setKnightType(YesNoEnum.NO.getCode());
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {
            DataTransferObject<PagingResult<OrderInfoVO>> dto =ordersService.getOrderInfoPageCurrent(paramRequest);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【查询配送记录】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }
    }



    /**
     * 收款记录
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/moneyLog", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto moneyLog(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        //获取当前参数
        OrderInfoRequest paramRequest = getEntity(request, OrderInfoRequest.class);
        paramRequest.setKnightUid(getUserId(request));
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("异常参数");
        }
        paramRequest.setKnightType(YesNoEnum.YES.getCode());
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {
            DataTransferObject<PagingResult<OrderInfoVO>> dto =ordersService.getOrderInfoPage(paramRequest);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【查询收款记录】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }
    }

}
