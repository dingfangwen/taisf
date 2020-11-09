package com.taisf.services.supplier.proxy;

import com.jk.framework.base.constant.YesNoEnum;
import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.ValueUtil;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.classify.entity.ProductClassifyEntity;
import com.taisf.services.classify.manager.ProductClassifyManagerImpl;
import com.taisf.services.common.constant.PathConstant;
import com.taisf.services.common.util.WeekUtil;
import com.taisf.services.common.valenum.OrderTypeEnum;
import com.taisf.services.common.valenum.ProductClassifyEnum;
import com.taisf.services.common.valenum.SupplierProductTypeEnum;
import com.taisf.services.enterprise.entity.EnterpriseConfigEntity;
import com.taisf.services.enterprise.manager.EnterpriseManagerImpl;
import com.taisf.services.enterprise.vo.EnterpriseInfoVO;
import com.taisf.services.product.ProductVO;
import com.taisf.services.product.dto.ProductListRequest;
import com.taisf.services.product.entity.ProductEntity;
import com.taisf.services.supplier.api.SupplierProductService;
import com.taisf.services.supplier.dao.SupplierProductDao;
import com.taisf.services.supplier.dto.SupplierProductRequest;
import com.taisf.services.supplier.entity.SupplierPackageEntity;
import com.taisf.services.supplier.entity.SupplierProductEntity;
import com.taisf.services.supplier.manager.SupplierManagerImpl;
import com.taisf.services.supplier.manager.SupplierPackageManagerImpl;
import com.taisf.services.supplier.vo.*;
import com.taisf.services.user.proxy.IndexServiceProxy;
import com.taisf.services.user.vo.FanVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>获取版本更新信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/3/27.
 * @version 1.0
 * @since 1.0
 */
@Component("supplier.supplierProductServiceProxy")
public class SupplierProductServiceProxy implements SupplierProductService {


    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierProductServiceProxy.class);

    @Resource(name = "supplier.supplierManagerImpl")
    private SupplierManagerImpl supplierManager;

    @Resource(name = "supplier.supplierProductDao")
    private SupplierProductDao supplierProductDao;


    @Resource(name = "supplier.supplierPackageManagerImpl")
    private SupplierPackageManagerImpl supplierPackageManager;


    @Resource(name = "user.indexServiceProxy")
    private IndexServiceProxy indexServiceProxy;


    @Autowired
    private PathConstant pathConstant;


    @Resource(name = "enterprise.enterpriseManagerImpl")
    private EnterpriseManagerImpl enterpriseManager;

    @Resource(name = "productClassifyManagerImpl")
    private ProductClassifyManagerImpl productClassifyManager;





    /**
     * 获取当前的列表信息
     *
     * @param supplierCode
     * @return
     */
    @Override
    public DataTransferObject<List<ProductClassifyInfo>> getSupplierClassifyProduct(String supplierCode,String enterpriseCode) {
        DataTransferObject dto = new DataTransferObject();
        if (Check.NuNStr(supplierCode)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        //获取当前的订单类型
        DataTransferObject<OrderTypeEnum> orderTypeDto = indexServiceProxy.getOrderType(enterpriseCode);
        if (!orderTypeDto.checkSuccess()){
            dto.setErrorMsg(orderTypeDto.getMsg());
            return dto;
        }
        OrderTypeEnum orderTypeEnum = orderTypeDto.getData();
        return getSupplierClassifyProductBase(supplierCode, WeekUtil.getWeek(), dto,orderTypeEnum);
    }

    /**
     * 获取当前的列表信息
     *
     * @param supplierCode
     * @return
     */
    @Override
    public DataTransferObject<SelectInfo4Week> getSupplierClassifyProductByWeek(String enterpriseCode,String supplierCode, Integer week) {

        SelectInfo4Week selectInfo4Week = new SelectInfo4Week();
        DataTransferObject dto = new DataTransferObject();
        if (Check.NuNStr(supplierCode)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNObj(week)){
            week = WeekUtil.getWeek();
        }

        //1. 设置当前的时间配置
        selectInfo4Week.setTimeList(dealTimeList(enterpriseCode, week, dto));
        if (!dto.checkSuccess()){
            return dto;
        }
        //2. 设置当前的菜单信息
        DataTransferObject<List<ProductClassifyInfo>> classifyProductDto = getSupplierClassifyProductBase(supplierCode, week, dto);
        if (classifyProductDto.checkSuccess()){
            selectInfo4Week.setProductClassifyList(classifyProductDto.getData());
        }
        dto.setData(selectInfo4Week);
        return dto;
    }


    private List<FanVO>  dealTimeList(String enterpriseCode,Integer week,DataTransferObject dto){
        List<FanVO> timeList = new ArrayList<>();

        // 3.设置企业信息
        EnterpriseInfoVO infoVO = enterpriseManager.getEnterpriseInfoByCode(enterpriseCode);

        if (Check.NuNObj(infoVO)){
            dto.setErrorMsg("异常的企业信息");
            return timeList;
        }
        if (Check.NuNObj(infoVO.getEnterpriseEntity().getTillTime())){
            dto.setErrorMsg("异常的企业截止时间");
            return timeList;
        }

        //当前时间
        Date now = new Date();
        if (infoVO.getEnterpriseEntity().getTillTime().before(now)){
            dto.setErrorMsg("加盟时间已经失效,请联系企业管理人员");
            return timeList;
        }
        EnterpriseConfigEntity config =infoVO.getEnterpriseConfigEntity();
        if(Check.NuNObj(config)){
            dto.setErrorMsg("异常的企业配置信息");
            return timeList;
        }
        return this.indexServiceProxy.dealTimeInfo(config);
    }



    /**
     * 处理当前的商品分类的订单匹配信息
     * @author afi
     * @param supplierCode
     * @param week
     * @param dto
     * @return
     */
    private DataTransferObject<List<ProductClassifyInfo>> getSupplierClassifyProductBase(String supplierCode, Integer week, DataTransferObject dto) {
        return getSupplierClassifyProductBase(supplierCode,week,dto,null);
    }

    /**
     * 处理当前的商品分类的订单匹配信息
     * @author afi
     * @param supplierCode
     * @param week
     * @param dto
     * @param orderTypeEnum
     * @return
     */
    private DataTransferObject<List<ProductClassifyInfo>> getSupplierClassifyProductBase(String supplierCode, Integer week, DataTransferObject dto,OrderTypeEnum orderTypeEnum) {
        Map<String, List<SupplierProductVO>> map = this.getSupplierProductMap(supplierCode,week,orderTypeEnum==null?null:orderTypeEnum.getCode());
        List<ProductClassifyVO> list = new ArrayList<>();
        dto.setData(list);
        //获取当前供应商的分类
        List<ProductClassifyEntity> classifyEntityList = productClassifyManager.listProductClassifyBySupplierCode(supplierCode);
        if(Check.NuNCollection(classifyEntityList)){
            return dto;
        }

        try {
            for (ProductClassifyEntity productClassifyEntity : classifyEntityList) {
                //拼接当前的药品
                dealProduct(map, list, productClassifyEntity);
            }
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【获取列表和商品信息】par:{},error:{}", JsonEntityTransform.Object2Json(supplierCode), e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("获取分类信息失败");
            return dto;
        }
        return dto;
    }

    /**
     * 商品图片信息
     * @param pro
     */
    private void dealPic(SupplierProductVO pro){
        if (Check.NuNObj(pro)){
            return;
        }
        pro.setProductPic(pathConstant.PIC_URL + pro.getProductPic());
    }

    /**
     * 处理商品列表
     *
     * @param map
     * @param list
     * @param productClassifyEntity
     */
    private void dealProduct(Map<String, List<SupplierProductVO>> map, List<ProductClassifyVO> list, ProductClassifyEntity productClassifyEntity) {
        if (Check.NuNObjs(map, list, productClassifyEntity)) {
            return;
        }
        ProductClassifyInfo vo = new ProductClassifyInfo();
        String key = productClassifyEntity.getClassifyCode() ;
        vo.setProductClassify(key);
        vo.setProductClassifyName(productClassifyEntity.getClassifyName());
        List<SupplierProductVO> tmp = map.get(key);
        if (!Check.NuNCollection(tmp)) {
            for (SupplierProductVO productVO : tmp) {
                //设置图片
                dealPic(productVO);
                //设置当前的展示列表
                this.fillOrderType(productVO);
            }
            vo.setList(tmp);
            list.add(vo);
        }
    }

//    /**
//     * 处理包
//     *
//     * @param supplierCode
//     */
//    private List<SupplierProductVO> getPackage(String supplierCode,Integer week) {
//        if (Check.NuNStr(supplierCode)) {
//            return null;
//        }
////        xxxx
//        List<SupplierPackageEntity> list = supplierManager.getSupplierPackageByCodeAndWeek(supplierCode,week );
//        if (Check.NuNCollection(list)) {
//            list = new ArrayList<>();
//        }
//        List<SupplierProductVO> voList = new ArrayList<>();
//        for (SupplierPackageEntity packageEntity : list) {
//            SupplierProductVO vo = new SupplierProductVO();
//            vo.setId(packageEntity.getId());
//            vo.setSupplierProductType(SupplierProductTypeEnum.PACKAGE.getCode());
//            vo.setPriceSale(packageEntity.getPackagePrice());
//            //处理套餐标题
//            vo.setProductName(supplierPackageManager.dealPackageTitle(packageEntity.getTitle(),packageEntity.getId(),null,1));
//            vo.setProductPic(packageEntity.getPackagePic());
//            vo.setForLunch(packageEntity.getForLunch());
//            vo.setForDinner(packageEntity.getForDinner());
//            dealPic(vo);
//            voList.add(vo);
//        }
//        return voList;
//    }

//    /**
//     * 处理包
//     *
//     * @param supplierCode
//     */
//    private List<SupplierProductVO> getPackage1(String supplierCode) {
//        return getPackage(supplierCode,WeekUtil.getWeek());
//    }




    /**
     * 获取当前的列表信息
     *
     * @param supplierCode
     * @return
     */
    @Override
    public DataTransferObject<List<ProductClassifyVO>> getSupplierProductClassify(String supplierCode) {
        DataTransferObject dto = new DataTransferObject();
        List<ProductClassifyVO> list = new ArrayList<>();
        try {
            //便利当前的枚举信息
            for (ProductClassifyEnum c : ProductClassifyEnum.values()) {
                ProductClassifyVO vo = new ProductClassifyVO();
                vo.setProductClassify(c.getCode() + "");
                vo.setProductClassifyName(c.getName());
                list.add(vo);
            }
            dto.setData(list);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【获取配置信息】par:{},error:{}", JsonEntityTransform.Object2Json(supplierCode), e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("获取分类信息失败");
            return dto;
        }
        return dto;
    }

//    /**
//     * 获取当前供应商的商品了列表
//     *
//     * @param supplierProductRequest
//     * @return
//     * @author afi
//     */
//    @Override
//    public DataTransferObject<List<SupplierProductVO>> getSupplierProductList(SupplierProductRequest supplierProductRequest) {
//        DataTransferObject dto = new DataTransferObject();
//        try {
//
//            ProductClassifyEnum productClassifyEnum = ProductClassifyEnum.getByCode(supplierProductRequest.getProductClassify());
//            if (Check.NuNObj(productClassifyEnum)) {
//                dto.setErrorMsg("异常的商品分类");
//                return dto;
//            }
//            SupplierProductTypeEnum supplierProductTypeEnum = productClassifyEnum.getSupplierProductTypeEnum();
//            if (supplierProductTypeEnum.getCode() == SupplierProductTypeEnum.PRODUCT.getCode()) {
//                //处理普通的商品信息
//                dealSupplierProduct(dto, supplierProductRequest);
//            } else if (supplierProductTypeEnum.getCode() == SupplierProductTypeEnum.PACKAGE.getCode()) {
//                //处理打包推荐列表
//                dealSupplierPackage(dto, supplierProductRequest);
//            } else {
//                dto.setErrorMsg("异常的分类");
//            }
//        } catch (Exception e) {
//            LogUtil.error(LOGGER, "【获取商品列表】par:{},error:{}", JsonEntityTransform.Object2Json(supplierProductRequest), e);
//            dto.setErrCode(DataTransferObject.ERROR);
//            dto.setMsg("获取商品列表失败");
//            return dto;
//        }
//        return dto;
//    }

//    /**
//     * 处理当前供应商的推荐商品信息
//     *
//     * @param dto
//     * @param supplierProductRequest
//     * @author afi
//     */
//    private void dealSupplierPackage(DataTransferObject<List<SupplierProductVO>> dto, SupplierProductRequest supplierProductRequest) {
//        if (!dto.checkSuccess()) {
//            return;
//        }
//        List<SupplierPackageEntity> list = supplierManager.getSupplierPackageByCode(supplierProductRequest.getSupplierCode());
//        if (Check.NuNCollection(list)) {
//            list = new ArrayList<>();
//        }
//        List<SupplierProductVO> voList = new ArrayList<>();
//        for (SupplierPackageEntity packageEntity : list) {
//            SupplierProductVO vo = new SupplierProductVO();
//            vo.setId(packageEntity.getId());
//            vo.setSupplierProductType(SupplierProductTypeEnum.PACKAGE.getCode());
//            vo.setPriceSale(packageEntity.getPackagePrice());
//            vo.setProductName(packageEntity.getTitle());
//            vo.setProductPic(packageEntity.getPackagePic());
//            voList.add(vo);
//        }
//        dto.setData(voList);
//    }


    /**
     * 处理当前的普通商品列表
     *
     * @param dto
     * @param supplierProductRequest
     */
    private void dealSupplierProduct(DataTransferObject<List<SupplierProductVO>> dto, SupplierProductRequest supplierProductRequest) {
        if (!dto.checkSuccess()) {
            return;
        }
        List<ProductEntity> list = supplierManager.getProductListBySupplierAndType(supplierProductRequest);
        if (Check.NuNCollection(list)) {
            list = new ArrayList<>();
        }
        List<SupplierProductVO> voList = this.transProductList2VO(list);
        dto.setData(voList);
    }


    /**
     * 获取当前的分类
     *
     * @param supplierCode
     * @return
     * @author afi
     */
    public Map<String, List<SupplierProductVO>> getSupplierProductMapOnly(String supplierCode) {
        Map<String, List<SupplierProductVO>> rst = new HashMap<>();
        Map<String, List<ProductEntity>> map = new HashMap<>();
        SupplierProductRequest request = new SupplierProductRequest();
        request.setSupplierCode(supplierCode);
        List<ProductEntity> list = supplierManager.getProductListBySupplierAndType(request);
        if (Check.NuNCollection(list)) {
            return rst;
        }
        for (ProductEntity productEntity : list) {
            String productClassify = productEntity.getProductClassify();
            if (Check.NuNObj(productClassify)) {
                //直接过滤掉异常数据
                continue;
            }
            String key = productClassify + "";
            if (map.containsKey(key)) {
                map.get(key).add(productEntity);
            } else {
                List<ProductEntity> tmp = new ArrayList<>();
                tmp.add(productEntity);
                map.put(key, tmp);
            }
        }
        for (String key : map.keySet()) {
            List<ProductEntity> proList = map.get(key);
            List<SupplierProductVO> voList = this.transProductList2VO(proList);
            rst.put(key, voList);
        }

        return rst;
    }


    /**
     * 获取当前的分类
     *
     * @param supplierCode
     * @return
     * @author afi
     */
    public Map<String, List<SupplierProductVO>> getSupplierProductMap(String supplierCode,Integer week) {
        return getSupplierProductMap(supplierCode,week,null);
    }

    /**
     * 获取当前的分类
     *
     * @param supplierCode
     * @return
     * @author afi
     */
    public Map<String, List<SupplierProductVO>> getSupplierProductMap(String supplierCode,Integer week,Integer orderType) {
        Map<String, List<SupplierProductVO>> rst = new HashMap<>();

        Map<String, List<ProductEntity>> map = new HashMap<>();
        SupplierProductRequest request = new SupplierProductRequest();
        request.setSupplierCode(supplierCode);
        if (Check.NuNObj(week)){
            week = WeekUtil.getWeek();
        }
        request.setWeek(week);
        request.setOrderType(orderType);
        List<ProductEntity> list = supplierManager.getProductListBySupplierAndType(request);
        if (Check.NuNCollection(list)) {
            return rst;
        }
        for (ProductEntity productEntity : list) {
            String productClassify = productEntity.getProductClassify();
            if (Check.NuNObj(productClassify)) {
                //直接过滤掉异常数据
                continue;
            }
            String key = productClassify + "";
            if (map.containsKey(key)) {
                map.get(key).add(productEntity);
            } else {
                List<ProductEntity> tmp = new ArrayList<>();
                tmp.add(productEntity);
                map.put(key, tmp);
            }
        }
        for (String key : map.keySet()) {
            List<ProductEntity> proList = map.get(key);
            List<SupplierProductVO> voList = this.transProductList2VO(proList);
            rst.put(key, voList);
        }

        return rst;
    }

    /**
     * 转化当前的商品为对外展示的商品列表信息
     *
     * @param list
     * @return
     * @author afi
     */
    private List<SupplierProductVO> transProductList2VO(List<ProductEntity> list) {
        List<SupplierProductVO> voList = new ArrayList<>();
        if (Check.NuNCollection(list)) {
            return voList;
        }
        for (ProductEntity entity : list) {
            SupplierProductVO supplier = new SupplierProductVO();
            BeanUtils.copyProperties(entity, supplier);
            voList.add(supplier);
        }
        return voList;

    }

    /**
     * 填充当前的订单类型的订单信息
     * @author afi
     * @param supplierProductVO
     */
    private void fillOrderType(SupplierProductVO supplierProductVO){
        if (Check.NuNObj(supplierProductVO)){
            return;
        }
        //当前的支持的订单类型
        List<OrderTypeVO> list = new ArrayList<>();
        if (ValueUtil.getintValue(supplierProductVO.getForLunch()) == YesNoEnum.YES.getCode()){
            //当前支持中午饭
            OrderTypeVO lunch = new OrderTypeVO();
            lunch.setOrderType(OrderTypeEnum.LUNCH_COMMON.getCode());
            lunch.setOrderTypeName(OrderTypeEnum.LUNCH_COMMON.getName());
            list.add(lunch);
        }

        if (ValueUtil.getintValue(supplierProductVO.getForDinner()) == YesNoEnum.YES.getCode()){
            //当前支持中晚饭
            OrderTypeVO dinner = new OrderTypeVO();
            dinner.setOrderType(OrderTypeEnum.DINNER_COMMON.getCode());
            dinner.setOrderTypeName(OrderTypeEnum.DINNER_COMMON.getName());
            list.add(dinner);
        }
        //设置当前的支持的餐的类型
        supplierProductVO.setOrderList(list);
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/12
     * @description:根据用户id关联用户供餐商中间表得到上架code查询供餐商菜品信息
     **/
    @Override
    public DataTransferObject<List<SupplierProductEntity>> getSupplierProductByUserId(String userId) {
        DataTransferObject<List<SupplierProductEntity>> dto = new DataTransferObject();
        try {
            List<SupplierProductEntity> entityList = supplierProductDao.getSupplierProductByUserId(userId);
            dto.setData(entityList);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【获取商品列表】par:{},error:{}", JsonEntityTransform.Object2Json(userId), e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("获取商品列表失败");
            return dto;
        }
        return dto;
    }

    /**
     * 获取当前周的上架情况
     * @param supplierCode
     * @param week
     * @return
     */
    @Override
    public DataTransferObject<List<SupplierProductEntity>> getSupplierProductByCodeAndWeek(String supplierCode,Integer week){
        DataTransferObject<List<SupplierProductEntity>> dto = new DataTransferObject();
        try {
            List<SupplierProductEntity> entityList = supplierProductDao.getSupplierProductByCodeAndWeek(supplierCode,week);
            dto.setData(entityList);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【获取商品列表】par:{},error:{}", JsonEntityTransform.Object2Json(supplierCode), e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("获取商品列表失败");
            return dto;
        }
        return dto;
    }


    /**
     * @author:zhangzhengguang
     * @date:2017/10/12
     * @description:撤回菜品
     **/
    @Override
    public DataTransferObject<Void> deleteByUserIdAndProudctIdAndWeek(String supplierCode, Integer productId,Integer week){
        DataTransferObject<Void> dto = new DataTransferObject();
        try {
            int num = supplierProductDao.deleteByUserIdAndProudctIdAndWeek(supplierCode, productId,week);
            if (num != 1) {
                dto.setErrCode(DataTransferObject.ERROR);
                dto.setErrorMsg("撤回菜品失败");
                return dto;
            }
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【撤回菜品失败】par:{},error:{}", supplierCode + productId, e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("撤回菜品失败");
            return dto;
        }
        return dto;
    }
    /**
     * @author:zhangzhengguang
     * @date:2017/10/12
     * @description:撤回菜品
     **/
    @Override
    public DataTransferObject<Void> deleteBySupplierCodeAndProudctId(String supplierCode, Integer productId) {
        DataTransferObject<Void> dto = new DataTransferObject();
        try {
            int num = supplierProductDao.deleteBySupplierCodeAndProudctId(supplierCode, productId);
            if (num != 1) {
                dto.setErrCode(DataTransferObject.ERROR);
                dto.setErrorMsg("撤回菜品失败");
                return dto;
            }
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【撤回菜品失败】par:{},error:{}", supplierCode + productId, e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("撤回菜品失败");
            return dto;
        }
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/13
     * @description:商家添加菜品
     **/
    @Override
    public DataTransferObject<Void> saveSupplierProduct(SupplierProductEntity supplierProductEntity) {
        DataTransferObject<Void> dto = new DataTransferObject();
        if (Check.NuNObj(supplierProductEntity)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            int num = supplierProductDao.saveSupplierProduct(supplierProductEntity);
            if (num != 1) {
                dto.setErrCode(DataTransferObject.ERROR);
                dto.setErrorMsg("商家添加菜品失败");
                return dto;
            }
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【商家添加菜品失败】par:{},error:{}", JsonEntityTransform.Object2Json(supplierProductEntity), e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("商家添加菜品失败");
            return dto;
        }
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/13
     * @description:分页查询菜品
     **/
    @Override
    public DataTransferObject<PagingResult<ProductVO>> pageListProduct(ProductListRequest request) {
        DataTransferObject<PagingResult<ProductVO>> dto = new DataTransferObject();
        try {
            PagingResult<ProductVO> pagingResult = supplierProductDao.pageListProduct(request);
            dto.setData(pagingResult);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【分页查询菜品失败】par:{},error:{}", JsonEntityTransform.Object2Json(request), e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("分页查询菜品失败");
            return dto;
        }
        return dto;
    }

}
