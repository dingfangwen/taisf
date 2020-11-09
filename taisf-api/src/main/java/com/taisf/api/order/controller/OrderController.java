package com.taisf.api.order.controller;

import com.jk.framework.base.constant.YesNoEnum;
import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.head.Header;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.rst.ResponseDto;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.ValueUtil;
import com.jk.framework.cache.redis.api.RedisOperations;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.api.common.abs.AbstractController;
import com.taisf.api.common.constants.ReturnEnum;
import com.taisf.services.common.valenum.FaceTypeEnum;
import com.taisf.services.common.valenum.OrdersStatusEnum;
import com.taisf.services.common.valenum.OrdersStatusShowEnum;
import com.taisf.services.order.api.OrderOpService;
import com.taisf.services.order.api.OrderService;
import com.taisf.services.order.constant.OrderConstant;
import com.taisf.services.order.dto.BuffetOutOrderRequest;
import com.taisf.services.order.dto.CreateOrderRequest;
import com.taisf.services.order.dto.OrderInfoRequest;
import com.taisf.services.order.vo.*;
import com.taisf.services.pay.api.RechargeOrderService;
import com.taisf.services.pay.dto.RechargeOrderRequest;
import com.taisf.services.user.api.UserService;
import com.taisf.services.user.entity.UserEntity;
import com.taisf.services.user.vo.UserModelVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
@RequestMapping("/order")
public class OrderController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);



    @Autowired
    private OrderService ordersService;

    @Autowired
    private UserService userService;

    @Autowired
    private RechargeOrderService rechargeOrderService;

    @Autowired
    private OrderOpService orderOpService;


    @Autowired
    private RedisOperations redisOperation;


    /**
     * 初始化下单
     * @author afi
      * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/initOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto initOrder(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }

        UserModelVO user = getUser(request);
        if (Check.NuNObj(user)){
            return new ResponseDto("请登录");
        }
        if (Check.NuNStr(user.getEnterpriseCode())){
            return new ResponseDto("请重新登录");
        }
        if (Check.NuNStr(user.getEnterpriseCode())) {
            return new ResponseDto("参数异常");
        }

        //获取当前参数
        CreateOrderRequest paramRequest = getEntity(request, CreateOrderRequest.class);
        paramRequest.setSource(header.getSource());
        paramRequest.setUserUid(getUserId(request));
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }
        paramRequest.setEnterpriseCode(user.getEnterpriseCode());

        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {

            DataTransferObject<OrderSaveInfo> dto =ordersService.initOrder(paramRequest);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【订单初始化】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }

    }

    /**
     * 初始化补单
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/initExtOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto initExtOrder(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        UserModelVO user = getUser(request);
        if (Check.NuNObj(user)){
            return new ResponseDto("请登录");
        }
        if (Check.NuNStr(user.getEnterpriseCode())){
            return new ResponseDto("请重新登录");
        }
        if (Check.NuNStr(user.getEnterpriseCode())) {
            return new ResponseDto("参数异常");
        }

        //获取当前参数
        CreateOrderRequest paramRequest = getEntity(request, CreateOrderRequest.class);
        paramRequest.setSource(header.getSource());
        paramRequest.setUserUid(getUserId(request));
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }

        paramRequest.setEnterpriseCode(user.getEnterpriseCode());

        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {

            DataTransferObject<OrderSaveInfo> dto =ordersService.initExtOrder(paramRequest);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【补单初始化】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }

    }


    /**
     * 下单逻辑
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/createOrderNew", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto createOrderNew(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }

        UserModelVO user = getUser(request);
        if (Check.NuNObj(user)){
            return new ResponseDto("请登录");
        }
        if (Check.NuNStr(user.getEnterpriseCode())){
            return new ResponseDto("请重新登录");
        }
        if (Check.NuNStr(user.getEnterpriseCode())) {
            return new ResponseDto("参数异常");
        }
        //获取当前参数
        CreateOrderRequest paramRequest = getEntity(request, CreateOrderRequest.class);
        paramRequest.setSource(header.getSource());
        paramRequest.setUserUid(getUserId(request));
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }
        paramRequest.setEnterpriseCode(user.getEnterpriseCode());
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        return createOrderLocal(request,paramRequest).trans2Res();

    }

    /**
     * 下单
     * @param request
     * @param paramRequest
     * @return
     */
    private DataTransferObject<CreateOrderVO> createOrderLocal(HttpServletRequest request,CreateOrderRequest paramRequest) {
        try {
            DataTransferObject<CreateOrderVO> dto =ordersService.createOrder(paramRequest);
            return dto;
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【下单】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            DataTransferObject<CreateOrderVO> dto = new DataTransferObject<>();
            dto.setErrorMsg("下单错误");
            return dto;
        }
    }

    /**
     * 下单逻辑
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto createOrder(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }

        UserModelVO user = getUser(request);
        if (Check.NuNObj(user)){
            return new ResponseDto("请登录");
        }
        if (Check.NuNStr(user.getEnterpriseCode())){
            return new ResponseDto("请重新登录");
        }
        if (Check.NuNStr(user.getEnterpriseCode())) {
            return new ResponseDto("参数异常");
        }
        //获取当前参数
        CreateOrderRequest paramRequest = getEntity(request, CreateOrderRequest.class);
        paramRequest.setSource(header.getSource());
        paramRequest.setUserUid(getUserId(request));
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }

        paramRequest.setEnterpriseCode(user.getEnterpriseCode());
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));

        DataTransferObject<CreateOrderVO> dto = createOrderLocal(request,paramRequest);
        if(dto.checkSuccess()){
            DataTransferObject<String> rst = new DataTransferObject<>();
            rst.setData(dto.getData().getOrderSn());
            return rst.trans2Res();
        }
        return dto.trans2Res();

    }



    /**
     * 面对面收款
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/initKnightOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto initKnightOrder(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        UserModelVO user = getUser(request);
        if (Check.NuNObj(user)){
            return new ResponseDto("请登录");
        }
        if (Check.NuNStr(user.getUserId())){
            return new ResponseDto("请重新登录");
        }
        if (Check.NuNStr(user.getEnterpriseCode())) {
            return new ResponseDto("参数异常");
        }

        DataTransferObject<Void> errDto = new DataTransferObject<>();

        String key = OrderConstant.trans2Key4KnightOrder(user.getUserId());

        String json = redisOperation.get(key);
        if (Check.NuNStr(json)){
            errDto.setErrCode(ReturnEnum.ORDER_WAIT.getCode());
            return errDto.trans2Res();
        }
        KnightOrderVO vo= JsonEntityTransform.json2Object(json,KnightOrderVO.class);
        if (Check.NuNObj(vo)){
            errDto.setErrCode(ReturnEnum.ORDER_WAIT.getCode());
            return errDto.trans2Res();
        }

        Integer price = vo.getPrice();
        if (price == null || price <= 0){
            errDto.setErrorMsg("异常的金额信息");
            return errDto.trans2Res();
        }

        LogUtil.info(LOGGER, "initKnightOrder传入参数:{}", JsonEntityTransform.Object2Json(vo));
        try {
            DataTransferObject<InitFaceVO> dto =ordersService.initFace(user.getUserId(), price);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【初始化骑手面对面】错误,par:{}, e={}",JsonEntityTransform.Object2Json(vo), e);
            return new ResponseDto("未知错误");
        }
    }




    /**
     * 面对面收款
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/initFaceOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto initFaceOrder(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        UserModelVO user = getUser(request);
        if (Check.NuNObj(user)){
            return new ResponseDto("请登录");
        }
        if (Check.NuNStr(user.getEnterpriseCode())){
            return new ResponseDto("请重新登录");
        }
        if (Check.NuNStr(user.getEnterpriseCode())) {
            return new ResponseDto("参数异常");
        }
        //获取当前参数
        Map<String,Object> paramRequest = getMap(request);
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }

        LogUtil.info(LOGGER, "initFaceOrder传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {
            DataTransferObject<InitFaceVO> dto =ordersService.initFace(user.getUserId(), ValueUtil.getintValue(paramRequest.get("price")));
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【初始化面对面】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }
    }



    /**
     * 扫码枪收款收款
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/knightOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto knightOrder(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        UserModelVO user = getUser(request);
        if (Check.NuNObj(user)){
            return new ResponseDto("请登录");
        }
        if (Check.NuNStr(user.getEnterpriseCode())) {
            return new ResponseDto("参数异常");
        }

        //异常信息
        DataTransferObject<Void> errDto = new DataTransferObject<>();

        //获取当前用的用户信息
        String key = OrderConstant.trans2Key4KnightOrder(user.getUserId());

        String json = redisOperation.get(key);
        if (Check.NuNStr(json)){
            errDto.setErrCode(ReturnEnum.ORDER_WAIT.getCode());
            return errDto.trans2Res();
        }
        KnightOrderVO vo= JsonEntityTransform.json2Object(json,KnightOrderVO.class);
        if (Check.NuNObj(vo)){
            errDto.setErrCode(ReturnEnum.ORDER_WAIT.getCode());
            return errDto.trans2Res();
        }

        Integer price = vo.getPrice();
        if (price == null || price <= 0){
            errDto.setErrorMsg("异常的金额信息");
            return errDto.trans2Res();
        }

        //获取当前参数
        CreateOrderRequest paramRequest = getEntity(request, CreateOrderRequest.class);
        paramRequest.setSource(header.getSource());
        paramRequest.setUserUid(getUserId(request));
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }
        paramRequest.setEnterpriseCode(user.getEnterpriseCode());
        paramRequest.setPrice(price);
        paramRequest.setPayCode(vo.getPayCode());

        LogUtil.info(LOGGER, "knightOrder传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {
            DataTransferObject<FaceVO> dto =ordersService.faceOrder(paramRequest,true, FaceTypeEnum.SACN_GUN.getCode());
            if (dto.checkSuccess()){
                //下单的逻辑成功
                FaceVO face = dto.getData();
                //下单成功
                vo.setOrderSn(face.getOrderSn());
                OrdersStatusShowEnum showEnum = OrdersStatusShowEnum.getByCode(face.getOrderStatus());
                if(Check.NuNObj(showEnum)){
                    new ResponseDto("异常的订单状态");
                }
                vo.setOrderStatus(showEnum.getCode());
                if (showEnum.getCode() != OrdersStatusShowEnum.NOPAYED.getCode()){
                    vo.setPayTime(face.getPayTime());
                }
            }
            //更新缓存信息
            redisOperation.setex(key, OrderConstant.KNIGHT_ORDER_SENCOND,JsonEntityTransform.Object2Json(vo));
            return dto.trans2Res();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.error(LOGGER, "【面对面收款】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }
    }



    /**
     * 面对面收款
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/faceOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto faceOrder(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        UserModelVO user = getUser(request);
        if (Check.NuNObj(user)){
            return new ResponseDto("请登录");
        }
        if (Check.NuNStr(user.getEnterpriseCode())) {
            return new ResponseDto("参数异常");
        }
        //获取当前参数
        CreateOrderRequest paramRequest = getEntity(request, CreateOrderRequest.class);
        paramRequest.setSource(header.getSource());
        paramRequest.setUserUid(getUserId(request));
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }

        paramRequest.setEnterpriseCode(user.getEnterpriseCode());
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {
            DataTransferObject<FaceVO> dto =ordersService.faceOrder(paramRequest,true);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【面对面收款】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }
    }


    /**
     * 补单逻辑
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/createExtOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto createExtOrder(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        UserModelVO user = getUser(request);
        if (Check.NuNObj(user)){
            return new ResponseDto("请登录");
        }
        if (Check.NuNStr(user.getEnterpriseCode())){
            return new ResponseDto("请重新登录");
        }
        if (Check.NuNStr(user.getEnterpriseCode())) {
            return new ResponseDto("参数异常");
        }
        //获取当前参数
        CreateOrderRequest paramRequest = getEntity(request, CreateOrderRequest.class);
        paramRequest.setSource(header.getSource());
        paramRequest.setUserUid(getUserId(request));
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }

        paramRequest.setEnterpriseCode(user.getEnterpriseCode());
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {

            DataTransferObject<String> dto =ordersService.createExtOrder(paramRequest);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【下单】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }
    }


    /**
     * 分页查询列表信息
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/listPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto listPage(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        //获取当前参数
        OrderInfoRequest paramRequest = getEntity(request, OrderInfoRequest.class);
        paramRequest.setUserUid(getUserId(request));
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }

        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {

            DataTransferObject<PagingResult<OrderInfoVO>> dto =ordersService.getOrderInfoPage(paramRequest);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【下单】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }
    }



    /**
     * 分页查询列表信息
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody    public ResponseDto info(HttpServletRequest request, HttpServletResponse response,String orderSn) {

        if (Check.NuNStr(orderSn)) {
            return new ResponseDto("参数异常");
        }

        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(orderSn));
        try {

            DataTransferObject<OrderDetailVO> dto =ordersService.getOrderDetailBySn(orderSn);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【订单详情】错误,par:{}, e={}",JsonEntityTransform.Object2Json(orderSn), e);
            return new ResponseDto("未知错误");
        }
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/1/3
     * @description:数据统计,根据企业code分页查询订单
     **/
    @RequestMapping(value = "/getEnterpriseOrderStats", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto getEnterpriseOrderStats(HttpServletRequest request, HttpServletResponse response) {
        DataTransferObject<PagingResult<OrderInfoVO>> dto = new DataTransferObject<>();
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        //获取当前参数
        String userId = getUserId(request);
        OrderInfoRequest paramRequest = getEntity(request, OrderInfoRequest.class);
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }
        if (Check.NuNObjs(userId)) {
            return new ResponseDto("参数异常");
        }
        paramRequest.setUserUid(userId);
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {
            //校验当前用户是否是管理员
            UserEntity userEntity = userService.getUserByUid(userId).getData();
            if (Check.NuNObj(userEntity)){
                return new ResponseDto("异常的账户信息");
            }
            if(userEntity.getIsAdmin().equals(YesNoEnum.NO.getCode())){
                //不是管理员 返回空
                return dto.trans2Res();
            }
            paramRequest.setEnterpriseCode(userEntity.getEnterpriseCode());
            if (Check.NuNStr(paramRequest.getEnterpriseCode())){
                dto.setErrorMsg("异常的企业信息");
                return dto.trans2Res();
            }
            return ordersService.getOrderListPageByEnterprisCode(paramRequest).trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "数据统计,根据企业code分页查询订单异常:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }
    }




    /**
     * 去充值的逻辑
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/createRecharge", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto createRecharge(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }

        UserModelVO user = getUser(request);
        if (Check.NuNObj(user)){
            return new ResponseDto("请登录");
        }
        if (Check.NuNStr(user.getEnterpriseCode())){
            return new ResponseDto("请重新登录");
        }
        if (Check.NuNStr(user.getEnterpriseCode())) {
            return new ResponseDto("参数异常");
        }
        //获取当前参数
        RechargeOrderRequest rechargeOrderRequest = getEntity(request, RechargeOrderRequest.class);
        rechargeOrderRequest.setUserUid(getUserId(request));
        if (Check.NuNObj(rechargeOrderRequest)) {
            return new ResponseDto("参数异常");
        }
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(rechargeOrderRequest));
        try {
            DataTransferObject<String> dto =rechargeOrderService.createRechargeOrder(rechargeOrderRequest);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【充值】错误,par:{}, e={}",JsonEntityTransform.Object2Json(rechargeOrderRequest), e);
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
    @RequestMapping(value = "/buffetOut", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto buffetOut(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        //获取当前参数
        BuffetOutOrderRequest paramRequest = getEntity(request, BuffetOutOrderRequest.class);
        paramRequest.setOpId(getUserId(request));
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }

        LogUtil.info(LOGGER, "取餐参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {
            DataTransferObject<Void> dto =orderOpService.buffetOutOrder(paramRequest);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【取餐】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }

    }

}
