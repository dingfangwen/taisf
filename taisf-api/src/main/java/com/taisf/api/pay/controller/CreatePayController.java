package com.taisf.api.pay.controller;

import com.jk.framework.base.constant.YesNoEnum;
import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.head.Header;
import com.jk.framework.base.rst.ResponseDto;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.SignUtils;
import com.jk.framework.common.utils.CloseableHttpUtil;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.api.common.abs.AbstractController;
import com.taisf.api.pay.constants.EmPayType;
import com.taisf.api.pay.constants.PayConstant;
import com.taisf.api.pay.dto.BasePayRequest;
import com.taisf.services.common.valenum.OrdersStatusEnum;
import com.taisf.services.order.api.OrderService;
import com.taisf.services.order.vo.OrderInfoVO;
import com.taisf.services.pay.api.RechargeOrderService;
import com.taisf.services.pay.entity.RechargeOrderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>测试</p>
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
@RequestMapping("/create")
public class CreatePayController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreatePayController.class);

    @Autowired
    private PayConstant payConstant;

    @Autowired
    private OrderService orderService;


    @Autowired
    private RechargeOrderService rechargeOrderService;

    /**
     * 充值支付
     * @author afi
     * @param request
     * @param
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/rechargePay")
    public @ResponseBody
    ResponseDto rechargePay(HttpServletRequest request, HttpServletResponse response) throws Exception{
        try{
            Header header = getHeader(request);
            if (Check.NuNObj(header)) {
                return new ResponseDto("头信息为空");
            }
            //获取当前参数
            String  json = getPar(request);
            LogUtil.info(LOGGER,"订单去支付,入参:{}",JsonEntityTransform.Object2Json(json));
            if (Check.NuNStr(json)){
                return new ResponseDto("参数异常");
            }
            BasePayRequest recordReques = JsonEntityTransform.json2Object(json, BasePayRequest.class);
            if (Check.NuNStr(recordReques.getOrderSn())){
                return new ResponseDto("异常的订单编号");
            }
            recordReques.setUid(getUserId(request));
            if (Check.NuNStr(recordReques.getUid())){
                return new ResponseDto("请登录");
            }
            EmPayType payType = EmPayType.getEmPayTypeByType(recordReques.getPayType());
            if (Check.NuNObj(payType)){
                return new ResponseDto("异常的支付类型");
            }

            //1. 获取当前的订单信息
            DataTransferObject<RechargeOrderEntity> dto = rechargeOrderService.getRechargeOrderByOrderSn(recordReques.getOrderSn());
            if(dto.getCode() != DataTransferObject.SUCCESS){
                return dto.trans2Res();
            }
            RechargeOrderEntity data = dto.getData();
            if(Check.NuNObj(data)){
                return new ResponseDto("订单不存在");
            }
            //重复支付的code
            if(data.getPayStatus() == YesNoEnum.YES.getCode()){
                return new ResponseDto("已经支付",222);
            }

            //3. 直接请求当前的订单金额
            int money = data.getNeedMoney().intValue();
            //拼接当前的参数信息
            Map<String, String> payPar = getPayPar(recordReques, money,"/payReturn/noticeRecharge");
            String jsonRst = CloseableHttpUtil.sendPost(payConstant.PAYMENT_PLATFORM_URL,payPar);
            LogUtil.info(LOGGER,"支付平台返回结果:{}",jsonRst);
            if (Check.NuNStr(jsonRst)){
                return new ResponseDto("调用支付平台失败");
            }
            ResponseDto responseDto = JsonEntityTransform.json2Entity(jsonRst,ResponseDto.class);
            return responseDto;
        }catch (Exception e){
            LogUtil.error(LOGGER,"【订单支付接口错误】:{}",e);
            return new ResponseDto("服务错误");
        }
    }




    /**
     * 订单的支付
     * @author afi
     * @param request
     * @param
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/orderPay")
    public @ResponseBody
    ResponseDto orderPay(HttpServletRequest request, HttpServletResponse response) throws Exception{
        try{
            Header header = getHeader(request);
            if (Check.NuNObj(header)) {
                return new ResponseDto("头信息为空");
            }
            //获取当前参数
            String  json = getPar(request);
            LogUtil.info(LOGGER,"订单去支付,入参:{}",JsonEntityTransform.Object2Json(json));
            if (Check.NuNStr(json)){
                return new ResponseDto("参数异常");
            }
            BasePayRequest recordReques = JsonEntityTransform.json2Object(json, BasePayRequest.class);
            if (Check.NuNStr(recordReques.getOrderSn())){
                return new ResponseDto("异常的订单编号");
            }
            recordReques.setUid(getUserId(request));
            if (Check.NuNStr(recordReques.getUid())){
                return new ResponseDto("请登录");
            }
            EmPayType payType = EmPayType.getEmPayTypeByType(recordReques.getPayType());
            if (Check.NuNObj(payType)){
                return new ResponseDto("异常的支付类型");
            }


            //1. 获取当前的订单信息
            DataTransferObject<OrderInfoVO> dto = orderService.getOrderInfoByOrderSn(recordReques.getOrderSn());
            if(dto.getCode() != DataTransferObject.SUCCESS){
                return dto.trans2Res();
            }
            OrderInfoVO data = dto.getData();
            if(Check.NuNObj(data)){
                return new ResponseDto("订单不存在");
            }
            OrdersStatusEnum statusEnum = OrdersStatusEnum.getByCode(data.getOrderStatus());
            if(Check.NuNObj(statusEnum)){
                return new ResponseDto("异常的订单状态");
            }
            //重复支付的code
            if(data.getOrderStatus() == OrdersStatusEnum.HAS_PAY.getCode()){
                return new ResponseDto("已经支付",222);
            }

            if(data.getOrderStatus() != OrdersStatusEnum.NO_PAY.getCode()){
                return new ResponseDto("此状态下不可支付");
            }
            //3. 直接请求当前的订单金额
            int money = data.getNeedPay().intValue();
            //拼接当前的参数信息
            Map<String, String> payPar = getPayPar(recordReques, money,"/payReturn/noticeOrder");
            String jsonRst = CloseableHttpUtil.sendPost(payConstant.PAYMENT_PLATFORM_URL,payPar);
            LogUtil.info(LOGGER,"支付平台返回结果:{}",jsonRst);
            if (Check.NuNStr(jsonRst)){
                return new ResponseDto("调用支付平台失败");
            }
            ResponseDto responseDto = JsonEntityTransform.json2Entity(jsonRst,ResponseDto.class);
            return responseDto;
        }catch (Exception e){
            LogUtil.error(LOGGER,"【订单支付接口错误】:{}",e);
            return new ResponseDto("服务错误");
        }
    }


    /**
     * 拼装支付的参数
     *
     * @param request
     * @return
     */
    private Map getPayPar(BasePayRequest request,int money,String urlSuffix) {
        //将参数转化成map
        final Map payMap = new HashMap();
        payMap.put("bizId", request.getOrderSn());

        EmPayType payType = EmPayType.getEmPayTypeByType(request.getPayType());

        payMap.put("code", payType.getCode());
        payMap.put("productCode", request.getProductCode());
        payMap.put("content",request.getContent());
        payMap.put("amount", money+"");
        payMap.put("returnUrl", payConstant.PAY_API_URL + urlSuffix);
        payMap.put("openid", request.getOpenId());
        payMap.put("notifyUrl", payConstant.PAY_API_URL + urlSuffix);
        final String out = SignUtils.md5Sign(payConstant.PAY_KEY, payMap);
        payMap.put("paySign", out);

        return  payMap;
    }
}
