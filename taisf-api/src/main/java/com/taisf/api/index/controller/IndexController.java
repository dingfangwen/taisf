package com.taisf.api.index.controller;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.rst.ResponseDto;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.ValueUtil;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.api.common.abs.AbstractController;
import com.taisf.services.device.common.constant.LinkConstant;
import com.taisf.services.order.api.OrderOpService;
import com.taisf.services.order.dto.BuffetOutOrderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>抽象service</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/09.
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/")
public class IndexController extends AbstractController {


    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);


    @Autowired
    private OrderOpService orderOpService;



    /**
     * 回调
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("notice")
    public @ResponseBody
    ResponseDto notice(HttpServletRequest request, HttpServletResponse response) {
       Map<String,Object> map = getMap(request);
        DataTransferObject dto = new DataTransferObject<>();
        LogUtil.error(LOGGER,"notice参数:{}",JsonEntityTransform.Object2Json(map));
        if (Check.NuNObj(map)){
            String  DeviceId = ValueUtil.getStrValue(map.get("DeviceId"));
            String  CellNo = ValueUtil.getStrValue(map.get("CellNo"));
            String  OutOrderId = ValueUtil.getStrValue(map.get("OutOrderId"));
            String  NotifyCode = ValueUtil.getStrValue(map.get("NotifyCode"));
            if ("1003".equals(NotifyCode)){
                //关门

                BuffetOutOrderRequest buffetOutOrderRequest = new BuffetOutOrderRequest();
                buffetOutOrderRequest.setOpId("system");
                buffetOutOrderRequest.setOrderSn(OutOrderId);
                orderOpService.buffetOutOrderBySystemCloseDao(buffetOutOrderRequest, LinkConstant.factoryA,DeviceId,CellNo);
            }
        }
        dto.setData(map);
        return dto.trans2Res();
    }


    @RequestMapping("")
    public @ResponseBody
    ResponseDto getIndex(HttpServletRequest request, HttpServletResponse response) {

        DataTransferObject dto = new DataTransferObject<>();
        return dto.trans2Res();
    }



}
