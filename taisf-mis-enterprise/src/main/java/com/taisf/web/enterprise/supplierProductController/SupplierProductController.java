package com.taisf.web.enterprise.supplierProductController;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.classify.api.ProductClassifyService;
import com.taisf.services.classify.entity.ProductClassifyEntity;
import com.taisf.services.classify.req.ProductClassifyListRequest;
import com.taisf.services.product.ProductVO;
import com.taisf.services.product.api.ProductService;
import com.taisf.services.product.dto.ProductListRequest;
import com.taisf.services.product.entity.ProductEntity;
import com.taisf.services.supplier.api.SupplierProductService;
import com.taisf.services.supplier.entity.SupplierProductEntity;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("supplierProduct/")
public class SupplierProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private SupplierProductService supplierProductService;

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
        return "supplierProduct/supplierProductList";
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
            DataTransferObject<PagingResult<ProductEntity>> dto = productService.pageListProduct(productListRequest);
            //查询出当前供餐商下所有菜品信息
            EmployeeEntity employeeEntity = (EmployeeEntity) session.getAttribute(LoginConstant.SESSION_KEY);
            if (Check.NuNStr(employeeEntity.getEmpBiz())){
                return pageResult;
            }

            if (!Check.NuNObj(dto.getData())) {
                List<SupplierProductEntity> entityList = supplierProductService.getSupplierProductByCodeAndWeek(employeeEntity.getEmpBiz(),productListRequest.getWeek()).getData();
                if (!Check.NuNCollection(entityList)) {
                    List<ProductEntity> list = dto.getData().getList();
                    for (ProductEntity x : list) {
                        for (SupplierProductEntity y : entityList) {
                            if (x.getId().equals(y.getProductCode())) {
                                x.setIsDel(1);
                                x.setForLunch(y.getForLunch());
                                x.setForDinner(y.getForDinner());
                                break;
                            }else {
                                x.setIsDel(0);
                            }
                        }
                    }

                }
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
     * @description:撤回
     **/
    @RequestMapping("revocation")
    @ResponseBody
    public DataTransferObject<Void> revocation(HttpServletRequest request, Integer id,Integer week) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(id)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNObj(week)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            HttpSession session = request.getSession();

            EmployeeEntity employeeEntity = (EmployeeEntity) session.getAttribute(LoginConstant.SESSION_KEY);
            if (Check.NuNStr(employeeEntity.getEmpBiz())){
                dto.setErrorMsg("一查的供应商账户");
                return dto;
            }

            dto = supplierProductService.deleteByUserIdAndProudctIdAndWeek(employeeEntity.getEmpBiz(), id,week);
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
     * @date:2017/10/13
     * @description:添加菜品
     **/
    @RequestMapping("addSupplierProduct")
    @ResponseBody
    public DataTransferObject<Void> addSupplierProduct(HttpServletRequest request, Integer id,Integer week,Integer forLunch,Integer forDinner) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(id)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }

        if (Check.NuNObj(week)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }

        try {
            HttpSession session = request.getSession();
            ProductEntity productEntity = productService.getProductById(id).getData();
            SupplierProductEntity supplierProductEntity = new SupplierProductEntity();

            EmployeeEntity employeeEntity = (EmployeeEntity) session.getAttribute(LoginConstant.SESSION_KEY);
            if (Check.NuNStr(employeeEntity.getEmpBiz())){
                dto.setErrorMsg("一查的供应商账户");
                return dto;
            }

            //1商家code
            supplierProductEntity.setSupplierCode(employeeEntity.getEmpBiz());
            //2商品code
            supplierProductEntity.setProductCode(productEntity.getId());
            //3商品价格
            supplierProductEntity.setProductPrice(productEntity.getPriceSale());
            //4商品类型 product_type
            supplierProductEntity.setProductType(productEntity.getProductType());
            //5创建时间
            supplierProductEntity.setCreateTime(new Date());
            //周几
            supplierProductEntity.setWeek(week);

            supplierProductEntity.setForLunch(forLunch);
            supplierProductEntity.setForDinner(forDinner);
            //6.执行保存
            dto = supplierProductService.saveSupplierProduct(supplierProductEntity);
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
     * @date:2017/10/13
     * @description:查询菜单
     **/
    @RequestMapping("findList")
    public String findList(HttpServletRequest request) {
        ProductClassifyListRequest productClassifyListRequest = new ProductClassifyListRequest();
        HttpSession session = request.getSession();
        EmployeeEntity emp = (EmployeeEntity)session.getAttribute(LoginConstant.SESSION_KEY);
        productClassifyListRequest.setSupplierCode(emp.getEmpBiz());
        List<ProductClassifyEntity> productClassifyEntities = productClassifyService.findListProductClassify(productClassifyListRequest).getData();
        request.setAttribute("productClassifyEntities",productClassifyEntities);
        return "supplierProduct/findSupplierProductList";
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/11
     * @description:分页查询商品列表数据
     **/
    @RequestMapping("findPageList")
    @ResponseBody
    public PageResult findPageList(HttpServletRequest request, ProductListRequest productListRequest) {
        PageResult pageResult = new PageResult();
        try {
            HttpSession session = request.getSession();
            EmployeeEntity employeeEntity = (EmployeeEntity) session.getAttribute(LoginConstant.SESSION_KEY);
            if (Check.NuNStr(employeeEntity.getEmpBiz())){
                return pageResult;
            }
            productListRequest.setSupplierCode(employeeEntity.getEmpBiz());
            DataTransferObject<PagingResult<ProductVO>> dto = supplierProductService.pageListProduct(productListRequest);
            //查询出当前供餐商下所有菜品信息
            if (!Check.NuNObj(dto.getData())) {
                pageResult.setRows(dto.getData().getList());
                pageResult.setTotal(dto.getData().getTotal());
            }
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params :{}", JsonEntityTransform.Object2Json(productListRequest));
            LogUtil.error(LOGGER, "error :{}", e);
            return new PageResult();
        }
        return pageResult;
    }

}
