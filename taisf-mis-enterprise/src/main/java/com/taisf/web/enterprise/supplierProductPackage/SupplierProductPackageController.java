package com.taisf.web.enterprise.supplierProductPackage;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.BigDecimalUtil;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.common.constant.PathConstant;
import com.taisf.services.common.valenum.ProductClassifyEnum;
import com.taisf.services.product.api.ProductService;
import com.taisf.services.product.entity.ProductEntity;
import com.taisf.services.supplier.api.SupplierPackageService;
import com.taisf.services.supplier.dto.SupplierProductRequest;
import com.taisf.services.supplier.entity.SupplierPackageEntity;
import com.taisf.services.supplier.proxy.SupplierProductServiceProxy;
import com.taisf.services.supplier.vo.SupplierPackageVO;
import com.taisf.services.supplier.vo.SupplierProductVO;
import com.taisf.services.ups.entity.EmployeeEntity;
import com.taisf.web.enterprise.common.constant.LoginConstant;
import com.taisf.web.enterprise.common.page.PageResult;
import com.taisf.web.enterprise.supplierProductController.SupplierProductController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("supplierProductPackage/")
public class SupplierProductPackageController {

//    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierProductController.class);
//
//    @Autowired
//    private SupplierPackageService supplierPackageService;
//
//    @Autowired
//    private ProductService productService;
//
//    @Resource(name = "supplier.supplierProductServiceProxy")
//    private SupplierProductServiceProxy supplierProductServiceProxy;
//
//    @Autowired
//    private PathConstant pathConstant;
//
//    /**
//     * @author:zhangzhengguang
//     * @date:2017/10/11
//     * @description:菜单录入列表
//     **/
//    @RequestMapping("list")
//    public String list(HttpServletRequest request) {
//        return "supplierPackage/supplierPackageList";
//    }
//
//
//    /**
//     * 根据商品id获取商品信息
//     * @param proCode
//     * @return
//     */
//    private String getProNameById(Integer proCode){
//        String name = null;
//        if (Check.NuNObj(proCode)){
//            return  name;
//        }
//
//        DataTransferObject<ProductEntity> has = productService.getProductById(proCode);
//        if (!has.checkSuccess()){
//            return name;
//        }
//        ProductEntity hasPro = has.getData();
//        if (Check.NuNObj(hasPro)){
//            return name;
//        }
//        return  hasPro.getProductName();
//    }
//
//    /**
//     * @author:zhangzhengguang
//     * @date:2017/10/11
//     * @description:分页查询商品列表数据
//     **/
//    @RequestMapping("pageList")
//    @ResponseBody
//    public PageResult pageList(HttpServletRequest request, SupplierProductRequest supplierProductRequest) {
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
//            supplierProductRequest.setSupplierCode(employeeEntity.getEmpBiz());
//            //
//            DataTransferObject<PagingResult<SupplierPackageEntity>> dto = supplierPackageService.pageListSupplierProduct(supplierProductRequest);
//            if (!Check.NuNObj(dto.getData())) {
//                List<SupplierPackageVO> listVo = new ArrayList<>();
//                dto.getData().getList().parallelStream().forEach((x) -> {
//                    SupplierPackageVO vo = new SupplierPackageVO();
//                    BeanUtils.copyProperties(x, vo);
//                    vo.setBigName(getProNameById(x.getBigCode()));
//                    vo.setSmallName(getProNameById(x.getSmallCode()));
//                    vo.setSuName(getProNameById(x.getSuCode()));
//                    vo.setTangName(getProNameById(x.getTangCode()));
//                    vo.setDrinkName(getProNameById(x.getDrinkCode()));
//                    vo.setFoodName(getProNameById(x.getFoodCode()));
//                    vo.setFruitName(getProNameById(x.getFruitCode()));
//                    vo.setPackagePic(pathConstant.PIC_URL+x.getPackagePic());
//                    listVo.add(vo);
//                });
//                pageResult.setRows(listVo);
//                pageResult.setTotal((long) listVo.size());
//            }
//        } catch (Exception e) {
//            LogUtil.info(LOGGER, "params:{}", JsonEntityTransform.Object2Json(supplierProductRequest));
//            LogUtil.error(LOGGER, "error:{}", e);
//            return new PageResult();
//        }
//        return pageResult;
//    }
//
//    /**
//     * @author:zhangzhengguang
//     * @date:2017/10/13
//     * @description:去添加组合套餐页面
//     **/
//    @RequestMapping("toAdd")
//    public String toAdd(HttpServletRequest request,Integer week) {
//        //不同分类集合
//        productCla01sifyList(request);
//        request.setAttribute("week", week);
//        return "supplierPackage/addSupplierPackage";
//    }
//
//    /**
//     * @author:zhangzhengguang
//     * @date:2017/10/13
//     * @description:新增组合套餐
//     **/
//    @RequestMapping("addSupplierProductPackage")
//    @ResponseBody
//    public DataTransferObject<Void> addSupplierProductPackage(HttpServletRequest request, SupplierPackageEntity packageEntity,Double packagePriceDou) {
//        DataTransferObject<Void> dto = new DataTransferObject<>();
//        if (Check.NuNObj(packageEntity)) {
//            dto.setErrCode(DataTransferObject.ERROR);
//            dto.setErrorMsg("参数异常");
//            return dto;
//        }
//        if (Check.NuNObj(packagePriceDou)){
//            dto.setErrorMsg("异常价格");
//            return dto;
//        }
//        Double last = BigDecimalUtil.mul(packagePriceDou,100);
//        packageEntity.setPackagePrice(last.intValue());
//        try {
//            //商家code
//            HttpSession session = request.getSession();
//            EmployeeEntity employeeEntity = (EmployeeEntity) session.getAttribute(LoginConstant.SESSION_KEY);
//            packageEntity.setSupplierCode(employeeEntity.getEmpBiz());
//            dto = supplierPackageService.saveSupplierPackage(packageEntity);
//        } catch (Exception e) {
//            LogUtil.error(LOGGER, "error:{}", e);
//            LogUtil.info(LOGGER, "param:{}", JsonEntityTransform.Object2Json(packageEntity));
//            dto.setErrCode(DataTransferObject.ERROR);
//            dto.setErrorMsg("系统错误");
//            return dto;
//        }
//        return dto;
//    }
//
//
//    /**
//     * @author:zhangzhengguang
//     * @date:2017/10/13
//     * @description:删除组合套餐
//     **/
//    @RequestMapping("deleteSupplierPackage")
//    @ResponseBody
//    public DataTransferObject<Void> deleteSupplierPackage(HttpServletRequest request, Integer id) {
//        DataTransferObject<Void> dto = new DataTransferObject<>();
//        if (Check.NuNObj(id)) {
//            dto.setErrCode(DataTransferObject.ERROR);
//            dto.setErrorMsg("参数异常");
//            return dto;
//        }
//        try {
//            dto = supplierPackageService.deleteByPrimaryKey(id);
//        } catch (Exception e) {
//            LogUtil.error(LOGGER, "error:{}", e);
//            LogUtil.info(LOGGER, "param:{}", id);
//            dto.setErrCode(DataTransferObject.ERROR);
//            dto.setErrorMsg("系统错误");
//            return dto;
//        }
//        return dto;
//    }
//
//    /**
//     * @author:zhangzhengguang
//     * @date:2017/10/13
//     * @description:去编辑组合套餐页面
//     **/
//    @RequestMapping("toedit")
//    public String toedit(HttpServletRequest request, Integer id,Integer week) {
//        //不同分类集合
//        productCla01sifyList(request);
//        //当前编辑的组合套餐
//        SupplierPackageEntity packageEntity = supplierPackageService.getSupplierPackageById(id).getData();
//        if(!Check.NuNObjs(packageEntity.getPackagePic())){
//            packageEntity.setPackagePic(pathConstant.PIC_URL+packageEntity.getPackagePic());
//        }
//        request.setAttribute("packageEntity", packageEntity);
//        if (!Check.NuNObj(packageEntity)){
//            request.setAttribute("packagePriceDou", BigDecimalUtil.div(packageEntity.getPackagePrice(),100));
//        }
//        return "supplierPackage/editSupplierPackage";
//    }
//
//    /**
//     * @author:zhangzhengguang
//     * @date:2017/10/13
//     * @description:修改组合套餐
//     **/
//    @RequestMapping("updateSupplierProductPackage")
//    @ResponseBody
//    public DataTransferObject<Void> updateSupplierProductPackage(HttpServletRequest request, SupplierPackageEntity packageEntity,Double packagePriceDou) {
//        DataTransferObject<Void> dto = new DataTransferObject<>();
//        if (Check.NuNObj(packageEntity)) {
//            dto.setErrCode(DataTransferObject.ERROR);
//            dto.setErrorMsg("参数异常");
//            return dto;
//        }
//
//        if (Check.NuNObj(packagePriceDou)){
//            dto.setErrorMsg("异常价格");
//            return dto;
//        }
//        Double last = BigDecimalUtil.mul(packagePriceDou,100);
//        packageEntity.setPackagePrice(last.intValue());
//
//        try {
//            packageEntity.setPackagePic(packageEntity.getPackagePic().replaceAll(pathConstant.PIC_URL,""));
//            dto = supplierPackageService.updateSupplierPackage(packageEntity);
//        } catch (Exception e) {
//            LogUtil.error(LOGGER, "error:{}", e);
//            LogUtil.info(LOGGER, "param:{}", JsonEntityTransform.Object2Json(packageEntity));
//            dto.setErrCode(DataTransferObject.ERROR);
//            dto.setErrorMsg("系统异常");
//            return dto;
//        }
//        return dto;
//    }
//
//
//    public void productCla01sifyList(HttpServletRequest request) {
//        //不同分类集合
//        List<ProductEntity> dahunList = productService.getListByClassify(ProductClassifyEnum.DAHUN.getCode()).getData();
//        List<ProductEntity> xiaohunList = productService.getListByClassify(ProductClassifyEnum.XIAOHUN.getCode()).getData();
//        List<ProductEntity> suList = productService.getListByClassify(ProductClassifyEnum.SU.getCode()).getData();
//        List<ProductEntity> tangList = productService.getListByClassify(ProductClassifyEnum.TANG.getCode()).getData();
//        List<ProductEntity> yinpinList = productService.getListByClassify(ProductClassifyEnum.YINPIN.getCode()).getData();
//        List<ProductEntity> zhushiList = productService.getListByClassify(ProductClassifyEnum.ZHUSHI.getCode()).getData();
//        List<ProductEntity> shuiguoList = productService.getListByClassify(ProductClassifyEnum.SHUIGUO.getCode()).getData();
//        request.setAttribute("dahunList", dahunList);
//        request.setAttribute("xiaohunList", xiaohunList);
//        request.setAttribute("suList", suList);
//        request.setAttribute("tangList", tangList);
//        request.setAttribute("yinpinList", yinpinList);
//        request.setAttribute("zhushiList", zhushiList);
//        request.setAttribute("shuiguoList", shuiguoList);
//    }
//
////
//    @Deprecated
//    public void productCla01sifyList_week(HttpServletRequest request) {
//
//        HttpSession session = request.getSession();
//        EmployeeEntity employeeEntity = (EmployeeEntity) session.getAttribute(LoginConstant.SESSION_KEY);
//        if (Check.NuNStr(employeeEntity.getEmpBiz())){
//            return;
//        }
//        Map<String, List<SupplierProductVO>> map = supplierProductServiceProxy.getSupplierProductMapOnly(employeeEntity.getEmpBiz());
//
//        //不同分类集合
//        List<SupplierProductVO> dahunList = map.get(ProductClassifyEnum.DAHUN.getCode()+"");
//        List<SupplierProductVO> xiaohunList = map.get(ProductClassifyEnum.XIAOHUN.getCode()+"");
//        List<SupplierProductVO> suList = map.get(ProductClassifyEnum.SU.getCode()+"");
//        List<SupplierProductVO> tangList = map.get(ProductClassifyEnum.TANG.getCode()+"");
//        List<SupplierProductVO> yinpinList = map.get(ProductClassifyEnum.YINPIN.getCode()+"");
//        List<SupplierProductVO> zhushiList = map.get(ProductClassifyEnum.ZHUSHI.getCode()+"");
//        List<SupplierProductVO> shuiguoList = map.get(ProductClassifyEnum.SHUIGUO.getCode()+"");
//        request.setAttribute("dahunList", dahunList);
//        request.setAttribute("xiaohunList", xiaohunList);
//        request.setAttribute("suList", suList);
//        request.setAttribute("tangList", tangList);
//        request.setAttribute("yinpinList", yinpinList);
//        request.setAttribute("zhushiList", zhushiList);
//        request.setAttribute("shuiguoList", shuiguoList);
//    }

}
