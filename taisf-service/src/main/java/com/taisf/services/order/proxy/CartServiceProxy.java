package com.taisf.services.order.proxy;

import com.jk.framework.base.constant.YesNoEnum;
import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.ValueUtil;
import com.taisf.services.common.util.MoneyDealUtil;
import com.taisf.services.common.util.WeekUtil;
import com.taisf.services.common.valenum.OrderTypeEnum;
import com.taisf.services.common.valenum.SupplierProductTypeEnum;
import com.taisf.services.order.api.CartService;
import com.taisf.services.order.dto.CartAddRequest;
import com.taisf.services.order.dto.CartBaseRequest;
import com.taisf.services.order.dto.CartCleanRequest;
import com.taisf.services.order.entity.CartEntity;
import com.taisf.services.order.manager.CartManagerImpl;
import com.taisf.services.order.vo.CartEleVO;
import com.taisf.services.order.vo.CartVO;
import com.taisf.services.order.vo.CartInfoVO;
import com.taisf.services.product.entity.ProductEntity;
import com.taisf.services.product.manager.ProductManagerImpl;
import com.taisf.services.stock.manager.StockProductManagerImpl;
import com.taisf.services.stock.vo.StockCheckVO;
import com.taisf.services.supplier.dto.SupplierProductRequest;
import com.taisf.services.supplier.entity.SupplierPackageEntity;
import com.taisf.services.supplier.manager.SupplierManagerImpl;
import com.taisf.services.user.proxy.IndexServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/28.
 * @version 1.0
 * @since 1.0
 */
@Component("order.cartServiceProxy")
public class CartServiceProxy implements CartService{

    private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceProxy.class);

    @Resource(name = "order.cartManagerImpl")
    private CartManagerImpl cartManager;

    @Resource(name = "product.productManagerImpl")
    private ProductManagerImpl productManager;


    @Resource(name = "supplier.supplierManagerImpl")
    private SupplierManagerImpl supplierProductManager;


    @Resource(name = "user.indexServiceProxy")
    private IndexServiceProxy indexServiceProxy;

    @Resource(name = "stock.stockProductManagerImpl")
    private StockProductManagerImpl stockProductManager;


    /**
     * 获取购物车列表信息
     * @param cartCleanRequest
     * @return
     */
    @Override
    public DataTransferObject<Void> cartClean(CartCleanRequest cartCleanRequest){
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNStr(cartCleanRequest.getBusinessUid())
                || Check.NuNStr(cartCleanRequest.getUserUid())){
            dto.setErrorMsg("参数异常");
            return dto;
        }
        cartManager.delUserCart(cartCleanRequest.getUserUid(),cartCleanRequest.getBusinessUid());
        return dto;
    }





    /**
     * 获取购物车列表信息
     * @param businessUid
     * @param userUid
     * @return
     */
    @Override
    public DataTransferObject<CartInfoVO> cartInfo(String userUid, String businessUid,String enterpriseCode){
        DataTransferObject<CartInfoVO> dto = new DataTransferObject<>();

        if (Check.NuNStr(businessUid)
                || Check.NuNStr(userUid)){
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
        CartInfoVO vo = new CartInfoVO();
        vo.setBusinessUid(businessUid);
        vo.setUserUid(userUid);
        List<CartEntity> list = cartManager.getCartByUserId(userUid,businessUid);
        if (list == null){
            list = new ArrayList<>();
        }
        dto.setData(vo);
        //礼包列表
        List<CartEleVO> proList = new ArrayList<>();
        //药品列表
//        List<CartEleVO> packageList = new ArrayList<>();
        for (CartEntity cartEntity : list) {
            //获取购物车类型
            SupplierProductTypeEnum supplierProductTypeEnum = SupplierProductTypeEnum.getByCode(cartEntity.getSupplierProductType());
            if (Check.NuNObj(supplierProductTypeEnum)){
                dto.setErrorMsg("异常的商品类型");
                return dto;
            }

            CartEleVO ele = new CartEleVO();
            ele.setProductCode(cartEntity.getProductCode());
            ele.setProductNum(cartEntity.getProductNum());
            proList.add(ele);
//            if(supplierProductTypeEnum.getCode() == SupplierProductTypeEnum.PACKAGE.getCode() ){
//                CartEleVO ele = new CartEleVO();
//                ele.setProductCode(cartEntity.getProductCode());
//                ele.setProductNum(cartEntity.getProductNum());
//                packageList.add(ele);
//            }else if(supplierProductTypeEnum.getCode()== SupplierProductTypeEnum.PRODUCT.getCode() ){
//
//
//            }
        }
//        if (!Check.NuNCollection(packageList)){
//            //处理礼包
//            dealCartPackage(dto,packageList,orderTypeEnum);
//        }
        if (!Check.NuNCollection(proList)){
            //处理商品
            dealCartProduct(dto,proList,orderTypeEnum,businessUid);
        }
        dto.setData(vo);
        return dto;
    }

//    /**
//     * 填充礼包的逻辑
//     * @param dto
//     * @param packageList
//     */
//    private void dealCartPackage(DataTransferObject<CartInfoVO> dto,List<CartEleVO> packageList,OrderTypeEnum orderTypeEnum){
//        if (!dto.checkSuccess()){
//            return;
//        }
//        if (Check.NuNCollection(packageList)){
//            return;
//        }
//        if (Check.NuNObj(orderTypeEnum)){
//            return;
//        }
//        Map<String,Integer> numMap = new HashMap<>();
//        List<Integer> pList = new ArrayList<>();
//        for (CartEleVO eleVO : packageList) {
//            pList.add(eleVO.getProductCode());
//            numMap.put(eleVO.getProductCode()+"",eleVO.getProductNum());
//        }
//
//
//        supplierProductManager.getProductListBySupplierAndType()
//        List<SupplierPackageEntity> list = supplierProductManager.getSupplierPackageByList(pList);
//        if (Check.NuNCollection(list)){
//            return;
//        }
//        CartInfoVO vo = dto.getData();
//        for (SupplierPackageEntity productEntity : list) {
//            int num = ValueUtil.getintValue(numMap.get(productEntity.getId()+""));
//            CartVO cartVO = new CartVO();
//            cartVO.setProductName(productEntity.getTitle());
//            cartVO.setBusinessUid(vo.getBusinessUid());
//            cartVO.setUserUid(vo.getUserUid());
//            cartVO.setProductCode(productEntity.getId());
//            cartVO.setProductPrice(productEntity.getPackagePrice());
//            cartVO.setProductNum(num);
//            cartVO.setSupplierProductType(SupplierProductTypeEnum.PACKAGE.getCode());
//            //获取当前的匹配情况
//            if (!orderTypeEnum.checkSuit(ValueUtil.getintValue(productEntity.getForLunch()),ValueUtil.getintValue(productEntity.getForDinner()))){
//                cartVO.setOutDes("不可预定");
//                cartVO.setOutFlag(YesNoEnum.YES.getCode());
//            }
//            vo.getList().add(cartVO);
//            vo.setPrice(MoneyDealUtil.overlayMoney(vo.getPrice(),productEntity.getPackagePrice(),num));
//        }
//    }

//    /**
//     * 获取今天周几
//     * @return
//     */
//    private int getWeek() {
//        Calendar c = Calendar.getInstance();
//        c.setTime(new Date());
//        return c.get(Calendar.DAY_OF_WEEK);
//    }

    /**
     * 填充普通药品逻辑
     * @param dto
     * @param proList
     */
    private void dealCartProduct(DataTransferObject<CartInfoVO> dto,List<CartEleVO> proList,OrderTypeEnum orderTypeEnum,String supplierCode){
        if (!dto.checkSuccess()){
            return;
        }
        if (Check.NuNCollection(proList)){
            return;
        }
        if (Check.NuNObj(orderTypeEnum)){
            return;
        }
        Map<String,Integer> numMap = new HashMap<>();
        List<Integer> pList = new ArrayList<>();
        for (CartEleVO eleVO : proList) {
            pList.add(eleVO.getProductCode());
            numMap.put(eleVO.getProductCode()+"",eleVO.getProductNum());
        }
        SupplierProductRequest supplierProductRequest = new SupplierProductRequest();
        supplierProductRequest.setProductIds(pList);
        supplierProductRequest.setWeek(WeekUtil.getWeek());
        supplierProductRequest.setSupplierCode(supplierCode);
        List<ProductEntity> list = supplierProductManager.getProductListBySupplierAndType(supplierProductRequest);
        if (Check.NuNCollection(list)){
            return;
        }
        CartInfoVO vo = dto.getData();
        for (ProductEntity productEntity : list) {
            int num = ValueUtil.getintValue(numMap.get(productEntity.getId()+""));
            CartVO cartVO = new CartVO();
            cartVO.setProductName(productEntity.getProductName());
            cartVO.setBusinessUid(vo.getBusinessUid());
            cartVO.setUserUid(vo.getUserUid());
            cartVO.setProductCode(productEntity.getId());
            cartVO.setProductPrice(productEntity.getPriceSale());
            cartVO.setSupplierProductType(productEntity.getSupplierProductType());
            cartVO.setProductNum(num);

            //获取当前的匹配情况
            if (!orderTypeEnum.checkSuit(ValueUtil.getintValue(productEntity.getForLunch()),ValueUtil.getintValue(productEntity.getForDinner()))){
                cartVO.setOutDes("不可预定");
                cartVO.setOutFlag(YesNoEnum.YES.getCode());
            }

            vo.getList().add(cartVO);
            vo.setPrice(MoneyDealUtil.overlayMoney(vo.getPrice(), productEntity.getPriceSale(), num));
        }
    }




    /**
     * 添加购物车
     * @param cartAddRequest
     * @return
     */
    @Override
    public DataTransferObject<CartInfoVO> addCart(CartAddRequest cartAddRequest) {
        DataTransferObject<CartInfoVO> dto = new DataTransferObject<>();
        //1. 校验基本参数,≤
        this.checkCartBasePar(dto,cartAddRequest,true);
        if (!dto.checkSuccess()){
            return dto;
        }

        SupplierProductTypeEnum supplierProductTypeEnum = SupplierProductTypeEnum.getByCode(cartAddRequest.getSupplierProductType());
        if (Check.NuNObj(supplierProductTypeEnum)){
            dto.setErrorMsg("异常的商品类型");
            return dto;
        }
        if (Check.NuNObj(cartAddRequest.getProductNum())){
            cartAddRequest.setProductNum(1);
        }
        CartEntity has = cartManager.getCartByProduct(cartAddRequest);

        List<Integer> list = new ArrayList<>();
        list.add(cartAddRequest.getProductCode());
        Map<String, StockCheckVO> map = stockProductManager.checkStockLimit(WeekUtil.getWeek(), cartAddRequest.getSupplierProductType(), cartAddRequest.getOrderType(), cartAddRequest.getBusinessUid(), list);
        StockCheckVO stock = map.get(ValueUtil.getStrValue(cartAddRequest.getProductCode()));
        if (Check.NuNObj(stock)){
            dto.setErrorMsg("异常的库存信息");
            return dto;
        }
        int last = ValueUtil.getintValue(stock.getProductLimit()) - ValueUtil.getintValue(stock.getProductNum());
        if (last <= 0){
            dto.setErrorMsg("当前商品已售完");
            return dto;
        }
        if (Check.NuNObj(has)){
            CartEntity record = new CartEntity();
            BeanUtils.copyProperties(cartAddRequest,record);
            //加入购物车限制
            if (ValueUtil.getintValue(record.getProductNum()) > last ){
                dto.setErrorMsg("当前商品只有" + last + "份");
                return dto;
            }
            //当前购物车中不存在
            cartManager.saveCart(record);
        }else {
            //当前购物车中已经存在
            has.setProductNum(has.getProductNum() + cartAddRequest.getProductNum());

            //加入购物车限制
            if (ValueUtil.getintValue(has.getProductNum()) > last ){
                dto.setErrorMsg("当前商品只有" + last + "份");
                return dto;
            }
            cartManager.updateCart(has);
        }
        return dto;
        //return cartInfo(cartAddRequest.getUserUid(),cartAddRequest.getBusinessUid(),cartAddRequest.getEnterpriseCode());
    }

    /**
     * 移除购物车
     * @param cartBaseRequest
     * @return
     */
    @Override
    public DataTransferObject<CartInfoVO> delCart(CartAddRequest cartBaseRequest) {
        DataTransferObject<CartInfoVO> dto = new DataTransferObject<>();
        //1. 校验基本参数
        this.checkCartBasePar(dto,cartBaseRequest,false);
        if (!dto.checkSuccess()){
            return dto;
        }

        CartEntity has = cartManager.getCartByProduct(cartBaseRequest);
        if (Check.NuNObj(has)){
            dto.setErrorMsg("当前商品不存在,请勿重复删除");
            return dto;
        }else {

            if ( ValueUtil.getintValue(cartBaseRequest.getProductNum()) == 0
                    ||ValueUtil.getintValue(has.getProductNum()) == ValueUtil.getintValue(cartBaseRequest.getProductNum())){
                cartManager.delCart(has.getId());
            }else {
                int last = has.getProductNum() - cartBaseRequest.getProductNum();
                if (last < 0){
                    dto.setErrorMsg("异常的购物车数量");
                    return dto;
                }
                //当前购物车中已经存在
                has.setProductNum(last);
                cartManager.updateCart(has);
            }

        }
        return cartInfo(cartBaseRequest.getUserUid(),cartBaseRequest.getBusinessUid(),cartBaseRequest.getEnterpriseCode());
    }

    /**
     * 校验当前购物的基本参数信息
     * @param dto
     * @param cartBaseRequest
     */
    private void checkCartBasePar(DataTransferObject dto,CartBaseRequest cartBaseRequest,boolean orderTypeNeed){
        if (Check.NuNObj(cartBaseRequest)){
            dto.setErrorMsg("参数异常");
        }
        if (Check.NuNObjs(cartBaseRequest.getBusinessUid(),
                cartBaseRequest.getBusinessUid(),
                cartBaseRequest.getProductCode(),
                cartBaseRequest.getSupplierProductType())){
            dto.setErrorMsg("参数为空");
        }
        if (orderTypeNeed && Check.NuNObj(cartBaseRequest.getOrderType())){
            dto.setErrorMsg("初始化当前的订单类型失败");
        }
    }


}
