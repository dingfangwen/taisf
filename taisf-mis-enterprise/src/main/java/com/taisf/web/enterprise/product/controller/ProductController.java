package com.taisf.web.enterprise.product.controller;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.BigDecimalUtil;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.classify.api.ProductClassifyService;
import com.taisf.services.classify.entity.ProductClassifyEntity;
import com.taisf.services.classify.req.ProductClassifyListRequest;
import com.taisf.services.common.constant.PathConstant;
import com.taisf.services.common.valenum.SupplierProductTypeEnum;
import com.taisf.services.product.api.ProductService;
import com.taisf.services.product.dto.ProductListRequest;
import com.taisf.services.product.entity.ProductEntity;
import com.taisf.services.ups.entity.EmployeeEntity;
import com.taisf.services.window.api.SupplierWindowService;
import com.taisf.services.window.entity.SupplierWindowEntity;
import com.taisf.services.window.req.SupplierWindowListRequest;
import com.taisf.web.enterprise.common.constant.LoginConstant;
import com.taisf.web.enterprise.common.page.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("product/")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private PathConstant pathConstant;


    @Autowired
    private SupplierWindowService supplierWindowService;


    @Autowired
    private ProductClassifyService productClassifyService;
    /**
     * @author:zhangzhengguang
     * @date:2017/10/11
     * @description:菜单录入列表
     **/
    @RequestMapping("list")
    public String list(HttpServletRequest request) {
        ProductClassifyListRequest productClassifyListRequest = new ProductClassifyListRequest();
        HttpSession session = request.getSession();
        EmployeeEntity emp = (EmployeeEntity)session.getAttribute(LoginConstant.SESSION_KEY);
        productClassifyListRequest.setSupplierCode(emp.getEmpBiz());
        List<ProductClassifyEntity> productClassifyEntities = productClassifyService.findListProductClassify(productClassifyListRequest).getData();
        request.setAttribute("productClassifyEntities",productClassifyEntities);
        SupplierWindowListRequest supplierWindowListRequest = new SupplierWindowListRequest();
        supplierWindowListRequest.setSupplierCode(emp.getEmpBiz());
        List<SupplierWindowEntity> supplierWindowEntities = supplierWindowService.findListSupplierWindow(supplierWindowListRequest).getData();
        request.setAttribute("supplierWindowEntities",supplierWindowEntities);
        return "product/productList";
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/11
     * @description:分页查询商品列表数据
     **/
    @RequestMapping("pageList")
    @ResponseBody
    public PageResult pageList(HttpServletRequest request, ProductListRequest productListRequest) {
        PageResult pageResult = new PageResult();
        try {
            HttpSession session = request.getSession();
            EmployeeEntity emp = (EmployeeEntity)session.getAttribute(LoginConstant.SESSION_KEY);
            productListRequest.setSupplierCode(emp.getEmpBiz());
            productListRequest.setSupplierProductType(SupplierProductTypeEnum.PRODUCT.getCode());
            DataTransferObject<PagingResult<ProductEntity>> dto = productService.pageListProduct(productListRequest);
            if (!Check.NuNObj(dto.getData())) {
                pageResult.setRows(dto.getData().getList());
                pageResult.setTotal(dto.getData().getTotal());
            }
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params:{}", JsonEntityTransform.Object2Json(productListRequest));
            LogUtil.error(LOGGER, "error:{}", e);
            return new PageResult();
        }
        return pageResult;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/11
     * @description:新增菜品
     **/
    @RequestMapping("addProduct")
    @ResponseBody
    public DataTransferObject<Void> addProduct(HttpServletRequest request, ProductEntity productEntity,Double price,Double priceOrg) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(productEntity)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }

        if (Check.NuNObj(price)){
            dto.setErrorMsg("异常的金额");
            return dto;
        }
        Double priceSale = BigDecimalUtil.mul(price,100);
        productEntity.setPriceSale(priceSale.intValue());

        if (Check.NuNObj(priceOrg)){
            dto.setErrorMsg("异常的金额");
            return dto;
        }
        Double priceMarket = BigDecimalUtil.mul(priceOrg,100);
        productEntity.setPriceMarket(priceMarket.intValue());
        try {
            HttpSession session = request.getSession();
            EmployeeEntity emp = (EmployeeEntity)session.getAttribute(LoginConstant.SESSION_KEY);
            productEntity.setSupplierCode(emp.getEmpBiz());
            dto = productService.saveProduct(productEntity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("系统错误");
            return dto;
        }
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/11
     * @description:编辑菜品回显
     **/
    @RequestMapping("toedit")
    @ResponseBody
    public DataTransferObject<ProductEntity> toedit(HttpServletRequest request, String id) {
        DataTransferObject<ProductEntity> dto = new DataTransferObject<>();
        if (Check.NuNObj(id)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            dto = productService.getProductById(Integer.parseInt(id));
            if(!Check.NuNObjs(dto.getData(),dto.getData().getProductPic())){
                dto.getData().setProductPic(pathConstant.PIC_URL+dto.getData().getProductPic());
            }
        } catch (Exception e) {
            LogUtil.error(LOGGER, "error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("系统错误");
            return dto;
        }
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/11
     * @description:编辑保存菜品
     **/
    @RequestMapping("updateProduct")
    @ResponseBody
    public DataTransferObject<Void> updateProduct(HttpServletRequest request, ProductEntity productEntity,Double price,Double priceOrg) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(productEntity)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNObj(price)){
            dto.setErrorMsg("异常的金额");
            return dto;
        }
        if(!Check.NuNObj(productEntity.getProductPic())){
            productEntity.setProductPic(productEntity.getProductPic().replace(pathConstant.PIC_URL,""));
        }
        Double priceSale = BigDecimalUtil.mul(price,100);
        productEntity.setPriceSale(priceSale.intValue());


        if (Check.NuNObj(priceOrg)){
            dto.setErrorMsg("异常的金额");
            return dto;
        }
        Double priceMarket = BigDecimalUtil.mul(priceOrg,100);
        productEntity.setPriceMarket(priceMarket.intValue());
        try {
            dto = productService.updateProduct(productEntity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("系统错误");
            return dto;
        }
        return dto;
    }
}
