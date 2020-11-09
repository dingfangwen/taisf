package com.taisf.api.cart.controller;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.head.Header;
import com.jk.framework.base.rst.ResponseDto;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.api.common.abs.AbstractController;
import com.taisf.services.common.valenum.OrderTypeEnum;
import com.taisf.services.order.api.CartService;
import com.taisf.services.order.dto.CartAddRequest;
import com.taisf.services.order.dto.CartCleanRequest;
import com.taisf.services.order.vo.CartInfoVO;
import com.taisf.services.user.api.IndexService;
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

/**
 * <p>购物车相关接口</p>
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
@RequestMapping("/cart")
public class CartController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);


    @Autowired
    private CartService cartService;


    @Autowired
    private IndexService indexService;

    /**
     * 添加购物车
     * @author afi
      * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addCart", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto addCart(HttpServletRequest request, HttpServletResponse response) {
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
        CartAddRequest paramRequest = getEntity(request, CartAddRequest.class);
        paramRequest.setUserUid(getUserId(request));
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }
        paramRequest.setEnterpriseCode(user.getEnterpriseCode());

        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {
            DataTransferObject<OrderTypeEnum> typeDto = indexService.getOrderType(user.getEnterpriseCode());
            if (!typeDto.checkSuccess()){
                return new ResponseDto("获取当前的订饭时间失败");
            }
            //设置当前的订单类型
            paramRequest.setOrderType(typeDto.getData().getCode());
            DataTransferObject<CartInfoVO> dto =cartService.addCart(paramRequest);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【添加购物车】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }
    }



    /**
     * 移除购物车
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/delCart", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto delCart(HttpServletRequest request, HttpServletResponse response) {
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
        CartAddRequest paramRequest = getEntity(request, CartAddRequest.class);
        paramRequest.setUserUid(getUserId(request));
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }
        paramRequest.setEnterpriseCode(user.getEnterpriseCode());

        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {

            DataTransferObject<CartInfoVO> dto =cartService.delCart(paramRequest);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【移除购物车】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }

    }


    /**
     * 清空购物车
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/cartClean", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto cartClean(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        //获取当前参数
        CartCleanRequest paramRequest = getEntity(request, CartCleanRequest.class);
        paramRequest.setUserUid(getUserId(request));
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }

        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {

            DataTransferObject<Void> dto =cartService.cartClean(paramRequest);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【清空购物车】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }

    }



    /**
     * 查看购物车
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto info(HttpServletRequest request, HttpServletResponse response,String businessUid) {
        String userUid = getUserId(request);
        if (Check.NuNStr(userUid)) {
            return new ResponseDto("参数异常");
        }
        if (Check.NuNStr(businessUid)) {
            return new ResponseDto("参数异常");
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

        LogUtil.info(LOGGER, "传入参数:businessUid:{}", JsonEntityTransform.Object2Json(businessUid));
        try {

            DataTransferObject<CartInfoVO> dto =cartService.cartInfo(userUid,businessUid,user.getEnterpriseCode());
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【查看购物车】错误,par:{}, e={}",JsonEntityTransform.Object2Json(businessUid), e);
            return new ResponseDto("未知错误");
        }

    }


}
