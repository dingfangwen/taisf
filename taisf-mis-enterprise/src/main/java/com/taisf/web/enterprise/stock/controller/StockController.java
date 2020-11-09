package com.taisf.web.enterprise.stock.controller;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.ValueUtil;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.classify.api.ProductClassifyService;
import com.taisf.services.classify.entity.ProductClassifyEntity;
import com.taisf.services.classify.req.ProductClassifyListRequest;
import com.taisf.services.common.valenum.OrderTypeEnum;
import com.taisf.services.common.valenum.SupplierProductTypeEnum;
import com.taisf.services.product.ProductVO;
import com.taisf.services.product.api.ProductService;
import com.taisf.services.product.dto.ProductListRequest;
import com.taisf.services.product.entity.ProductEntity;
import com.taisf.services.stock.entity.StockProductEntity;
import com.taisf.services.stock.manager.StockProductManagerImpl;
import com.taisf.services.stock.request.ProductStockReq;
import com.taisf.services.stock.vo.ProductStockVO;
import com.taisf.services.stock.vo.StockCheckVO;
import com.taisf.services.supplier.api.SupplierPackageService;
import com.taisf.services.supplier.api.SupplierProductService;
import com.taisf.services.supplier.dto.SupplierProductRequest;
import com.taisf.services.supplier.entity.SupplierPackageEntity;
import com.taisf.services.ups.entity.EmployeeEntity;
import com.taisf.web.enterprise.common.constant.LoginConstant;
import com.taisf.web.enterprise.common.page.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("stock/")
public class StockController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private SupplierProductService supplierProductService;

    @Autowired
    private StockProductManagerImpl stockProductManager;

//
//    @Autowired
//    private SupplierPackageService supplierPackageService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductClassifyService productClassifyService;

    /**
     * @author:zhangzhengguang
     * @date:2018/3/26
     * @description:去商品库存列表页
     **/
    @RequestMapping("list")
    public String list(HttpServletRequest request) {
        ProductClassifyListRequest productClassifyListRequest = new ProductClassifyListRequest();
        HttpSession session = request.getSession();
        EmployeeEntity emp = (EmployeeEntity)session.getAttribute(LoginConstant.SESSION_KEY);
        productClassifyListRequest.setSupplierCode(emp.getEmpBiz());
        List<ProductClassifyEntity> productClassifyEntities = productClassifyService.findListProductClassify(productClassifyListRequest).getData();
        request.setAttribute("productClassifyEntities",productClassifyEntities);
        return "stock/productStockList";
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/3/26
     * @description:去套餐库存列表页
     **/
    @RequestMapping("productPackageStockList")
    public String productPackagelist(HttpServletRequest request) {
        return "stock/productPackageStockList";
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/3/26
     * @description:分页数据
     **/
    @RequestMapping("pageList")
    @ResponseBody
    public PageResult pageList(HttpServletRequest request, ProductListRequest productListRequest) {
        PageResult pageResult = new PageResult();
        try {
            HttpSession session = request.getSession();
            EmployeeEntity employeeEntity = (EmployeeEntity) session.getAttribute(LoginConstant.SESSION_KEY);
            if (Check.NuNStr(employeeEntity.getEmpBiz())) {
                return pageResult;
            }
            productListRequest.setSupplierCode(employeeEntity.getEmpBiz());
            DataTransferObject<PagingResult<ProductVO>> dto = supplierProductService.pageListProduct(productListRequest);
            List<ProductVO> productEntities = dto.getData().getList();
            if (Check.NuNCollection(productEntities)) {
                return new PageResult();
            }
            List<Integer> productIds = productEntities.parallelStream().filter(a -> ValueUtil.getintValue(a.getSupplierProductType()) == SupplierProductTypeEnum.PRODUCT.getCode()).map(ProductEntity::getId).collect(Collectors.toList());
            List<Integer> packageIds = productEntities.parallelStream().filter(a -> ValueUtil.getintValue(a.getSupplierProductType()) == SupplierProductTypeEnum.PACKAGE.getCode()).map(ProductEntity::getId).collect(Collectors.toList());

            Map<String, StockCheckVO> lunchMapPro = stockProductManager.checkStockLimit(productListRequest.getWeek(), SupplierProductTypeEnum.PRODUCT.getCode(), OrderTypeEnum.LUNCH_COMMON.getCode(), employeeEntity.getEmpBiz(), productIds);
            Map<String, StockCheckVO> lunchMapPackage = stockProductManager.checkStockLimit(productListRequest.getWeek(), SupplierProductTypeEnum.PACKAGE.getCode(), OrderTypeEnum.LUNCH_COMMON.getCode(), employeeEntity.getEmpBiz(), packageIds);
            lunchMapPro.putAll(lunchMapPackage);

            Map<String, StockCheckVO> dinnerMapPro = stockProductManager.checkStockLimit(productListRequest.getWeek(), SupplierProductTypeEnum.PRODUCT.getCode(), OrderTypeEnum.DINNER_COMMON.getCode(), employeeEntity.getEmpBiz(), productIds);
            Map<String, StockCheckVO> dinnerMapPackage = stockProductManager.checkStockLimit(productListRequest.getWeek(), SupplierProductTypeEnum.PACKAGE.getCode(), OrderTypeEnum.DINNER_COMMON.getCode(), employeeEntity.getEmpBiz(), packageIds);
            dinnerMapPro.putAll(dinnerMapPackage);


            List<ProductStockVO> stockVOList = new ArrayList<>();
            for (ProductEntity productEntity : productEntities) {
                ProductStockVO vo = new ProductStockVO();
                BeanUtils.copyProperties(productEntity, vo);
                StockCheckVO productLunchVO = lunchMapPro.get(String.valueOf(productEntity.getId()));
                if (!Check.NuNObj(productLunchVO)) {
                    vo.setLunchStockId(productLunchVO.getLimitId());
                    vo.setLunchProductLimit(productLunchVO.getProductLimit());
                    vo.setLunchProductNum(productLunchVO.getProductNum());
                }

                StockCheckVO productdinnerVO = dinnerMapPro.get(String.valueOf(productEntity.getId()));
                if (!Check.NuNObj(productdinnerVO)) {
                         vo.setDinnerStockId(productdinnerVO.getLimitId());
                    vo.setDinnerProductLimit(productdinnerVO.getProductLimit());
                    vo.setDinnerProductNum(productdinnerVO.getProductNum());
                }
                stockVOList.add(vo);
            }
            pageResult.setRows(stockVOList);
            pageResult.setTotal(dto.getData().getTotal());
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params :{}", JsonEntityTransform.Object2Json(productListRequest));
            LogUtil.error(LOGGER, "error :{}", e);
            return new PageResult();
        }
        return pageResult;
    }



    /**
     * @author:zhangzhengguang
     * @date:2018/3/26
     * @description:更新
     **/
    @RequestMapping("updateStock")
    @ResponseBody
    public DataTransferObject<Void> updateStock(HttpServletRequest request, ProductStockReq req) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if(Check.NuNObj(req)){
            dto.setErrorMsg("参数异常");
            return dto;
        }
        HttpSession session = request.getSession();
        EmployeeEntity employeeEntity = (EmployeeEntity) session.getAttribute(LoginConstant.SESSION_KEY);
        try {
            if(Check.NuNObj(req.getLunchStockId())){
                StockProductEntity entity = new StockProductEntity();
                entity.setProductCode(req.getId());
                entity.setWeek(req.getWeek());
                entity.setProductLimit(req.getLunchProductLimit());
                entity.setOrderType(OrderTypeEnum.LUNCH_COMMON.getCode());
                entity.setSupplierProductType(req.getSupplierProductType());
                entity.setSupplierCode(employeeEntity.getEmpBiz());
                stockProductManager.saveStockProduct(entity);
            }else{
                StockProductEntity entity = new StockProductEntity();
                if(!Check.NuNObjs(req.getLunchStockId(),req.getLunchProductLimit())){
                    entity.setId(req.getLunchStockId());
                    entity.setProductLimit(req.getLunchProductLimit());
                    stockProductManager.updateStockProduct(entity);
                }
            }

            if(Check.NuNObj(req.getDinnerStockId())){
                StockProductEntity entity = new StockProductEntity();
                entity.setProductCode(req.getId());
                entity.setWeek(req.getWeek());
                entity.setProductLimit(req.getDinnerProductLimit());
                entity.setOrderType(OrderTypeEnum.DINNER_COMMON.getCode());
                entity.setSupplierProductType(req.getSupplierProductType());
                entity.setSupplierCode(employeeEntity.getEmpBiz());
                stockProductManager.saveStockProduct(entity);
            }else{
                if(!Check.NuNObjs(req.getDinnerStockId(),req.getDinnerProductLimit())){
                    StockProductEntity entity = new StockProductEntity();
                    entity.setId(req.getDinnerStockId());
                    entity.setProductLimit(req.getDinnerProductLimit());
                    stockProductManager.updateStockProduct(entity);
                 }
            }
        } catch (Exception e) {
            LogUtil.error(LOGGER, "error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("系统错误");
            return dto;
        }
        return dto;
    }

//    /**
//     * @author:zhangzhengguang
//     * @date:2018/3/27
//     * @description:
//     **/
//    @RequestMapping("productPackagePageList")
//    @ResponseBody
//    public PageResult pageList(HttpServletRequest request, SupplierProductRequest req) {
//        PageResult pageResult = new PageResult();
//        try {
//
//            //查询出当前供餐商下所有菜品信息
//            HttpSession session = request.getSession();
//            EmployeeEntity employeeEntity = (EmployeeEntity) session.getAttribute(LoginConstant.SESSION_KEY);
//            if (Check.NuNStr(employeeEntity.getEmpBiz())){
//                return pageResult;
//            }
//
//            req.setSupplierCode(employeeEntity.getEmpBiz());
//            //
//            DataTransferObject<PagingResult<SupplierPackageEntity>> dto = supplierPackageService.pageListSupplierProduct(req);
//
//            List<SupplierPackageEntity> productEntities = dto.getData().getList();
//            if (Check.NuNCollection(productEntities)) {
//                return new PageResult();
//            }
//            List<Integer> productIds = productEntities.parallelStream().map(SupplierPackageEntity::getId).collect(Collectors.toList());
//            Map<String, StockCheckVO> lunchMap = stockProductManager.checkStockLimit(req.getWeek(), SupplierProductTypeEnum.PACKAGE.getCode(), OrderTypeEnum.LUNCH_COMMON.getCode(), employeeEntity.getEmpBiz(), productIds);
//            Map<String, StockCheckVO> dinnerMap = stockProductManager.checkStockLimit(req.getWeek(), SupplierProductTypeEnum.PACKAGE.getCode(), OrderTypeEnum.DINNER_COMMON.getCode(), employeeEntity.getEmpBiz(), productIds);
//            List<ProductStockVO> stockVOList = new ArrayList<>();
//            for (SupplierPackageEntity entity : productEntities) {
//                ProductStockVO vo = new ProductStockVO();
//                vo.setId(entity.getId());
//                vo.setProductName(entity.getTitle());
//                vo.setPriceSale(entity.getPackagePrice());
//                vo.setForLunch(entity.getForLunch());
//                vo.setForDinner(entity.getForDinner());
//                StockCheckVO productLunchVO = lunchMap.get(String.valueOf(entity.getId()));
//                if (!Check.NuNObj(productLunchVO)) {
//                    vo.setLunchStockId(productLunchVO.getLimitId());
//                    vo.setLunchProductLimit(productLunchVO.getProductLimit());
//                    vo.setLunchProductNum(productLunchVO.getProductNum());
//                }
//
//                StockCheckVO productdinnerVO = dinnerMap.get(String.valueOf(entity.getId()));
//                if (!Check.NuNObj(productdinnerVO)) {
//                    vo.setDinnerStockId(productdinnerVO.getLimitId());
//                    vo.setDinnerProductLimit(productdinnerVO.getProductLimit());
//                    vo.setDinnerProductNum(productdinnerVO.getProductNum());
//                }
//                stockVOList.add(vo);
//            }
//            pageResult.setRows(stockVOList);
//            pageResult.setTotal((long) stockVOList.size());
//        } catch (Exception e) {
//            LogUtil.info(LOGGER, "params:{}", JsonEntityTransform.Object2Json(req));
//            LogUtil.error(LOGGER, "error:{}", e);
//            return new PageResult();
//        }
//        return pageResult;
//    }


}
