package com.taisf.api.supplier.controller;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.rst.ResponseDto;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.api.common.abs.AbstractController;
import com.taisf.services.common.util.WeekUtil;
import com.taisf.services.supplier.api.SupplierProductService;
import com.taisf.services.supplier.api.SupplierService;
import com.taisf.services.supplier.dto.SupplierProductRequest;
import com.taisf.services.supplier.vo.ProductClassifyInfo;
import com.taisf.services.supplier.vo.ProductClassifyVO;
import com.taisf.services.supplier.vo.SelectInfo4Week;
import com.taisf.services.supplier.vo.SupplierProductVO;
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
import java.util.List;

/**
 * <p>供应商上官接口信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/9.
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierController.class);

    @Autowired
    private SupplierProductService supplierProductService;



    @Autowired
    private SupplierService supplierService;


    /**
     * 获取商品分类
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto info(HttpServletRequest request, HttpServletResponse response,String supplierCode,String payCode) {
        LogUtil.info(LOGGER, "传入参数: supplierCode:{},payCode:{}", supplierCode,payCode);
        try {
            if (!Check.NuNStr(supplierCode)){
                return supplierService.getSupplierInfo(supplierCode).trans2Res();
            }else if (!Check.NuNStr(payCode)){
                return supplierService.getPayInfo4payCode(payCode).trans2Res();
            }else {
                return new ResponseDto("参数异常");
            }
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【获取详情】错误,par:{}, e={}",JsonEntityTransform.Object2Json(supplierCode), e);
            return new ResponseDto("未知错误");
        }
    }

    /**
     * 获取商品分类
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/classify", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto lassify(HttpServletRequest request, HttpServletResponse response,String supplierCode) {
        if (Check.NuNStr(supplierCode)) {
            return new ResponseDto("参数异常");
        }

        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(supplierCode));
        try {

            DataTransferObject<List<ProductClassifyVO>> dto =supplierProductService.getSupplierProductClassify(supplierCode);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【获取商品分类】错误,par:{}, e={}",JsonEntityTransform.Object2Json(supplierCode), e);
            return new ResponseDto("未知错误");
        }

    }



//    /**
//     * 获取商品分类
//     * @author afi
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "/productList", method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseDto productList(HttpServletRequest request, HttpServletResponse response,String supplierCode,Integer productClassify) {
//
//
//        if (Check.NuNStr(supplierCode)) {
//            return new ResponseDto("参数异常");
//        }
//
//        if (Check.NuNObj(productClassify)) {
//            return new ResponseDto("参数异常");
//        }
//        SupplierProductRequest paramRequest =new SupplierProductRequest();
//        paramRequest.setProductClassify(productClassify);
//        paramRequest.setSupplierCode(supplierCode);
//
//        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
//        try {
//
//            DataTransferObject<List<SupplierProductVO>> dto =supplierProductService.getSupplierProductList(paramRequest);
//            return dto.trans2Res();
//        } catch (Exception e) {
//            LogUtil.error(LOGGER, "【获取商品列表】错误,par:{}, e={}",JsonEntityTransform.Object2Json(supplierCode), e);
//            return new ResponseDto("未知错误");
//        }
//
//    }

    /**
     * 获取商品分类
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/classifyProductWeek", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto classifyProduct(HttpServletRequest request, HttpServletResponse response,String supplierCode,Integer week) {

        UserModelVO user = getUser(request);
        if (Check.NuNObj(user)){
            return new ResponseDto("请登录");
        }
        if (Check.NuNStr(user.getEnterpriseCode())){
            return new ResponseDto("请重新登录");
        }
        if (Check.NuNStr(supplierCode)) {
            return new ResponseDto("参数异常");
        }
        if (Check.NuNObj(week)){
            week = WeekUtil.getWeek();
        }
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(supplierCode));
        try {
            DataTransferObject<SelectInfo4Week> dto =supplierProductService.getSupplierClassifyProductByWeek(user.getEnterpriseCode(),supplierCode,week);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【获取分类商品】错误,par:{}, e={}",JsonEntityTransform.Object2Json(supplierCode), e);
            return new ResponseDto("未知错误");
        }
    }



    /**
     * 获取商品分类
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/classifyProduct", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto classifyProduct(HttpServletRequest request, HttpServletResponse response,String supplierCode) {

        UserModelVO user = getUser(request);
        if (Check.NuNObj(user)){
            return new ResponseDto("请登录");
        }
        if (Check.NuNStr(user.getEnterpriseCode())){
            return new ResponseDto("请重新登录");
        }
        if (Check.NuNStr(supplierCode)) {
            return new ResponseDto("参数异常");
        }
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(supplierCode));
        try {
            DataTransferObject<List<ProductClassifyInfo>> dto =supplierProductService.getSupplierClassifyProduct(supplierCode,user.getEnterpriseCode());
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【获取分类商品】错误,par:{}, e={}",JsonEntityTransform.Object2Json(supplierCode), e);
            return new ResponseDto("未知错误");
        }
    }




}


