package com.taisf.api.buffet.controller;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.rst.ResponseDto;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.api.common.abs.AbstractController;
import com.taisf.services.buffet.api.BuffetService;
import com.taisf.services.buffet.dto.BuffetInfoRequest;
import com.taisf.services.buffet.entity.BuffetEntity;
import com.taisf.services.buffet.vo.BuffetVO;
import com.taisf.services.order.api.OrderService;
import com.taisf.services.order.vo.BuffetOrderVO;
import com.taisf.services.user.api.UserService;
import com.taisf.services.user.entity.UserEntity;
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
@RequestMapping("/buffet")
public class BuffetController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuffetController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private BuffetService buffetService;

    @Autowired
    private OrderService orderService;

    /**
     * 获取餐柜信息
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody   public ResponseDto detail(HttpServletRequest request, HttpServletResponse response,String buffetFid) {
        //获取当前参数
        BuffetInfoRequest paramRequest = new BuffetInfoRequest();
        if (Check.NuNStr(buffetFid)) {
            return new ResponseDto("参数异常");
        }

        //获取当前参数
        String userId = getUserId(request);
        if (Check.NuNStr(userId)) {
            return new ResponseDto("请登录");
        }
        paramRequest.setBuffetFid(buffetFid);
        LogUtil.info(LOGGER, "获取设备信息参数:{}", JsonEntityTransform.Object2Json(paramRequest));



        try {
            DataTransferObject<UserEntity> userDto = userService.getUserByUid(userId);
            if (!userDto.checkSuccess()){
                return userDto.trans2Res();
            }

            UserEntity userEntity = userDto.getData();
            if (Check.NuNObj(userEntity)){
                return new ResponseDto("异常的骑手信息");
            }
            String supplierCode = userEntity.getBizCode();
            if (Check.NuNStr(supplierCode)){
                return new ResponseDto("异常的骑手信息");
            }

            DataTransferObject<BuffetVO> dto =buffetService.getBuffetInfo(paramRequest);
            if (!dto.checkSuccess()){
                return dto.trans2Res();
            }

            BuffetEntity buffetEntity = dto.getData();
            if (Check.NuNObj(buffetEntity)){
                return new ResponseDto("异常的餐柜信息");
            }
            if (!supplierCode.equals(buffetEntity.getSupplierCode())){
                return new ResponseDto("您暂无权限开启此柜门");
            }
            return dto.trans2Res();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.error(LOGGER, "【获取设备信息】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }
    }


    /**
     * 获取餐柜信息
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody    public ResponseDto info(HttpServletRequest request, HttpServletResponse response,String orderSn) {

        //获取当前参数
        String userId = getUserId(request);
        if (Check.NuNStr(userId)) {
            return new ResponseDto("请登录");
        }

        if (Check.NuNStr(orderSn)) {
            return new ResponseDto("参数异常");
        }

        LogUtil.info(LOGGER, "根据订单获取餐柜信息:{}", JsonEntityTransform.Object2Json(orderSn));
        try {
            DataTransferObject<BuffetOrderVO> dto =orderService.getBuffetByOrderSn(orderSn,userId);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【根据订单获取餐柜信息】错误,par:{}, e={}",JsonEntityTransform.Object2Json(orderSn), e);
            return new ResponseDto("未知错误");
        }
    }



}
