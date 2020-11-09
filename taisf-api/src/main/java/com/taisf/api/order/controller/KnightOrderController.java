package com.taisf.api.order.controller;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.head.Header;
import com.jk.framework.base.rst.ResponseDto;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.cache.redis.api.RedisOperations;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.api.common.abs.AbstractController;
import com.taisf.api.common.constants.ReturnEnum;
import com.taisf.services.common.valenum.OrdersStatusShowEnum;
import com.taisf.services.order.constant.OrderConstant;
import com.taisf.services.order.vo.KnightOrderVO;
import com.taisf.services.supplier.api.SupplierService;
import com.taisf.services.supplier.entity.SupplierEntity;
import com.taisf.services.user.api.IndexService;
import com.taisf.services.user.vo.IndexBaseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/knightOrder")
public class KnightOrderController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnightOrderController.class);

    @Autowired
    private IndexService indexService;

    @Autowired
    private SupplierService supplierService;


    @Autowired
    private RedisOperations redisOperation;



    /**
     * 骑手获取当前的用户的基本信息
     * @param request
     * @param response
     * @param userUid
     * @return
     */
    @RequestMapping(value ="userInfo")
    public @ResponseBody
    ResponseDto userInfo(HttpServletRequest request, HttpServletResponse response,String userUid) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        if (Check.NuNStr(userUid)) {
            return new ResponseDto("参数异常");
        }
        LogUtil.info(LOGGER, "userInfo传入参数:{}", JsonEntityTransform.Object2Json(userUid));
        try {
            DataTransferObject<IndexBaseVO> dto =indexService.getBaseIndex(userUid);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【获取用户基本信息】错误,par:{}, e={}",JsonEntityTransform.Object2Json(userUid), e);
            return new ResponseDto("未知错误");
        }

    }


    /**
     * 骑手获取当前的用户的基本信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value ="knightOrderInfo")
    public @ResponseBody
    ResponseDto knightOrderInfo(HttpServletRequest request, HttpServletResponse response,String userUid) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }

        if (Check.NuNStr(userUid)) {
            return new ResponseDto("参数异常");
        }

        DataTransferObject<Void> errDto = new DataTransferObject<>();
        //设置当前的缓存信息
        String key = OrderConstant.trans2Key4KnightOrder(userUid);

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
        //设置当前返回结果信息
        DataTransferObject<KnightOrderVO> rst = new DataTransferObject<>();
        rst.setData(vo);
        return rst.trans2Res();
    }



    /**
     * 骑手获取当前的用户的基本信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value ="createKnightOrder")
    public @ResponseBody
    ResponseDto createKnightOrder(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
         if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        //获取当前参数
        KnightOrderVO paramRequest = getEntity(request, KnightOrderVO.class);
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }
        LogUtil.info(LOGGER, "createKnightOrder传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        String userUid = paramRequest.getUserUid();
        Integer price = paramRequest.getPrice();
        if (price == null || price <= 0){
            return new ResponseDto("异常的金额信息");
        }
        String supplierCode = paramRequest.getSupplierCode();
        if (Check.NuNStr(supplierCode)){
            return new ResponseDto("异常的供应商信息");
        }

        String payCode = paramRequest.getPayCode();
        if (Check.NuNStr(payCode)){
            return new ResponseDto("异常的收款码");
        }


        if (Check.NuNStr(userUid)) {
            return new ResponseDto("参数异常");
        }
        LogUtil.info(LOGGER, "createKnightOrder传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {

            //设置当前返回结果信息
            DataTransferObject<KnightOrderVO> rst = new DataTransferObject<>();

            DataTransferObject<IndexBaseVO> dto =indexService.getBaseIndex(userUid);
            //获取当前的用户信息
            if (!dto.checkSuccess()){
                return dto.trans2Res();
            }
            //拼装当前的用户信息
            fillKnightOrderVO(rst,paramRequest,dto.getData());

            DataTransferObject<SupplierEntity> supplierInfoDto = supplierService.getSupplierInfo(supplierCode);
            if (!supplierInfoDto.checkSuccess()){
                return supplierInfoDto.trans2Res();
            }

            //拼装当前供应商
            fillSupplier(rst,paramRequest,supplierInfoDto.getData());

            String key = OrderConstant.trans2Key4KnightOrder(userUid);
            redisOperation.setex(key,OrderConstant.KNIGHT_ORDER_SENCOND,JsonEntityTransform.Object2Json(paramRequest));
            rst.setData(paramRequest);
            return rst.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【获取用户基本信息】错误,par:{}, e={}",JsonEntityTransform.Object2Json(userUid), e);
            return new ResponseDto("未知错误");
        }

    }


    /**
     * 拼装当前的患者信息
     * @author afi
     * @param paramRequest
     * @param supplierEntity
     */
    private void fillSupplier(DataTransferObject dto,KnightOrderVO paramRequest,SupplierEntity supplierEntity){

        if (!dto.checkSuccess()){
            return;
        }
        if (Check.NuNObjs(paramRequest,supplierEntity)){
            return;
        }
        paramRequest.setStreet(supplierEntity.getStreet());
    }

    /**
     * 拼装当前的患者信息
     * @author afi
     * @param paramRequest
     * @param indexBaseVO
     */
    private void fillKnightOrderVO(DataTransferObject dto,KnightOrderVO paramRequest,IndexBaseVO indexBaseVO){
        if (!dto.checkSuccess()){
            return;
        }
        if (Check.NuNObjs(paramRequest,indexBaseVO)){
            return;
        }
        if (!paramRequest.getSupplierCode().equals(indexBaseVO.getUserInfo().getSupplierCode())){
            dto.setErrorMsg("您没有当前用户的收款权限");
            return;
        }
        paramRequest.setSupplierCode(indexBaseVO.getUserInfo().getSupplierCode());
        paramRequest.setSupplierName(indexBaseVO.getUserInfo().getSupplierName());
        paramRequest.setOrderStatus(OrdersStatusShowEnum.NOPAYED.getCode());
        paramRequest.setUserName(indexBaseVO.getUserInfo().getUserName());
        paramRequest.setUserPhone(indexBaseVO.getUserInfo().getUserPhone());
    }

}
