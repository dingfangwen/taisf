package com.taisf.api.my.controller;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.head.Header;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.rst.ResponseDto;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.api.common.abs.AbstractController;
import com.taisf.services.enterprise.entity.EnterpriseAddressEntity;
import com.taisf.services.order.api.OrderService;
import com.taisf.services.order.dto.OrderInfoRequest;
import com.taisf.services.order.vo.OrderInfoVO;
import com.taisf.services.order.vo.UserSimpleVO;
import com.taisf.services.user.api.IndexService;
import com.taisf.services.user.api.UserService;
import com.taisf.services.user.dto.AccountLogRequest;
import com.taisf.services.user.dto.UserLoginRequest;
import com.taisf.services.user.dto.UserLogoutRequest;
import com.taisf.services.user.dto.UserRegistRequest;
import com.taisf.services.user.entity.AccountLogEntity;
import com.taisf.services.user.vo.IndexBaseVO;
import com.taisf.services.user.vo.IndexVO;
import com.taisf.services.user.vo.RegistInfoVO;
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
 * <p>用户先关接口</p>
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
@RequestMapping("/my")
public class MyController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyController.class);


    @Autowired
    private UserService userService;

    @Autowired
    private IndexService indexService;

    @Autowired
    private OrderService ordersService;


    /**
     * 获取用户的二维码
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value ="card")
    public @ResponseBody
    ResponseDto card(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        //获取当前参数
        String userUid = getUserId(request);
        if (Check.NuNStr(userUid)) {
            return new ResponseDto("参数异常");
        }
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(userUid));
        try {
            DataTransferObject<String> dto =userService.getQRcode(userUid);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【用户信息】错误,par:{}, e={}",JsonEntityTransform.Object2Json(userUid), e);
            return new ResponseDto("未知错误");
        }

    }


    /**
     * 获取用户的地址列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value ="address")
    public @ResponseBody
    ResponseDto address(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        //获取当前参数
        String userUid = getUserId(request);
        if (Check.NuNStr(userUid)) {
            return new ResponseDto("参数异常");
        }
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(userUid));
        try {
            DataTransferObject<List<EnterpriseAddressEntity>> dto =indexService.getUserAddressList(userUid);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【获取用户的地址列表】错误,par:{}, e={}",JsonEntityTransform.Object2Json(userUid), e);
            return new ResponseDto("未知错误");
        }
    }



    /**
     * 获取用户的基本信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value ="simple")
    public @ResponseBody
    ResponseDto simple(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        //获取当前参数
        String userUid = getUserId(request);
        if (Check.NuNStr(userUid)) {
            return new ResponseDto("参数异常");
        }
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(userUid));
        try {
            DataTransferObject<UserSimpleVO> dto =ordersService.getUserSimpleInfo(userUid);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【用户信息】错误,par:{}, e={}",JsonEntityTransform.Object2Json(userUid), e);
            return new ResponseDto("未知错误");
        }
    }



    /**
     * 获取用户的基本信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value ="user")
    public @ResponseBody
    ResponseDto index(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        //获取当前参数
        String userUid = getUserId(request);
        if (Check.NuNStr(userUid)) {
            return new ResponseDto("参数异常");
        }
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(userUid));
        try {
            DataTransferObject<IndexBaseVO> dto =indexService.getUserBaseInfo(userUid);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【用户信息】错误,par:{}, e={}",JsonEntityTransform.Object2Json(userUid), e);
            return new ResponseDto("未知错误");
        }

    }

    /**
     * 充值记录
     * @author afi
      * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/rechargeHistory", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto rechargeHistory(HttpServletRequest request, HttpServletResponse response) {

        //获取当前参数
        AccountLogRequest paramRequest = getEntity(request, AccountLogRequest.class);
        if (Check.NuNObj(paramRequest)) {
            paramRequest =new AccountLogRequest();
        }
        paramRequest.setUserId(getUserId(request));
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {
            DataTransferObject<PagingResult<AccountLogEntity>> dto =userService.inconmeLog(paramRequest);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【充值历史记录】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }
    }


    /**
     * 消费记录
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/consumeHistory", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto consumeHistory(HttpServletRequest request, HttpServletResponse response) {

        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        //获取当前参数
        OrderInfoRequest paramRequest = getEntity(request, OrderInfoRequest.class);
        if (Check.NuNObj(paramRequest)){
            paramRequest = new OrderInfoRequest();
        }
        paramRequest.setUserUid(getUserId(request));
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {

            DataTransferObject<PagingResult<OrderInfoVO>> dto =ordersService.getOrderInfoPageCurrent(paramRequest);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【消费记录】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }
    }

}
