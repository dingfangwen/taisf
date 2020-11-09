package com.taisf.services.test.product.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.GeneratorIdUtil;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.classify.dao.ProductClassifyDao;
import com.taisf.services.classify.entity.ProductClassifyEntity;
import com.taisf.services.product.dao.ProductDao;
import com.taisf.services.product.entity.ProductEntity;
import com.taisf.services.supplier.api.SupplierProductService;
import com.taisf.services.supplier.vo.ProductClassifyInfo;
import com.taisf.services.test.common.BaseTest;
import com.taisf.services.window.dao.SupplierWindowDao;
import com.taisf.services.window.entity.SupplierWindowEntity;
import com.taisf.services.window.req.SupplierWindowListRequest;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2020/11/9 16:46
 * @version 1.0
 */
public class CopyProductProxyTest extends BaseTest {


    @Resource(name = "productClassifyDao")
    private ProductClassifyDao productClassifyDao;

    @Resource(name = "supplierWindowDao")
    private SupplierWindowDao supplierWindowDao;


    @Resource(name = "product.productDao")
    private ProductDao productDao;


    /**
     * 复制药品库,记得删除先关的表
     *
     */
    @Test
    public void copyProductTest() {

        String  oldSupplier = "daozhixiang";

        String  newSupplier = "jst";


        Map<String,String> Classfy = new HashMap<>();

        Map<String,String> window = new HashMap<>();


        //处理分类
        dealClassfy(oldSupplier, newSupplier, Classfy);

        //处理窗口
        dealWindows(oldSupplier, newSupplier, window);



        List<ProductEntity> hasList = productDao.getProductBySupplierCode(oldSupplier);
        if(Check.NuNCollection(hasList)){
            System.out.println("当前没有药品,异常");
        }

        for (ProductEntity productEntity : hasList) {
            ProductEntity record = new ProductEntity();
            // 药品信息
            BeanUtils.copyProperties(productEntity,record);
            record.setProductClassify(Classfy.get(productEntity.getProductClassify()));
            record.setWindowCode(window.get(productEntity.getWindowCode()));
            record.setSupplierCode(newSupplier);
            record.setId(null);
            productDao.saveProduct(record);
        }

        System.out.println("处理完成");
    }


    /**
     * 处理药品分类信息
     * @param oldSupplier
     * @param newSupplier
     * @param map
     */
    private void dealClassfy(String oldSupplier, String newSupplier, Map<String, String> map) {
        List<ProductClassifyEntity> classifyEntityList = productClassifyDao.listProductClassifyBySupplierCode(oldSupplier);

        if (Check.NuNCollection(classifyEntityList)){
            System.out.println("当前没有分类信息,异常");
        }

        for (ProductClassifyEntity productClassifyEntity : classifyEntityList) {
            ProductClassifyEntity newClass = new ProductClassifyEntity();
            BeanUtils.copyProperties(productClassifyEntity,newClass);

            String classifyCodeNew = GeneratorIdUtil.getOrderCode("");
            map.put(productClassifyEntity.getClassifyCode(),classifyCodeNew);
            newClass.setClassifyCode(classifyCodeNew);
            newClass.setCreateTime(new Date());
            newClass.setSupplierCode(newSupplier);
            newClass.setId(null);
            productClassifyDao.saveProductClassify(newClass);
        }
    }

    /**
     * 处理药品分类信息
     * @param oldSupplier
     * @param newSupplier
     * @param map
     */
    private void dealWindows(String oldSupplier, String newSupplier, Map<String, String> map) {
        SupplierWindowListRequest request = new SupplierWindowListRequest();
        request.setSupplierCode(oldSupplier);
        List<SupplierWindowEntity> Windows = supplierWindowDao.findListSupplierWindow(request);

        if (Check.NuNCollection(Windows)){
            System.out.println("当前没有窗口信息,异常");
        }

        for (SupplierWindowEntity window : Windows) {
            SupplierWindowEntity newClass = new SupplierWindowEntity();
            BeanUtils.copyProperties(window,newClass);

            String windowCodeNew = GeneratorIdUtil.getOrderCode("");
            map.put(window.getWindowCode(),windowCodeNew);
            newClass.setWindowCode(windowCodeNew);
            newClass.setCreateTime(new Date());
            newClass.setSupplierCode(newSupplier);
            newClass.setId(null);
            supplierWindowDao.saveSupplierWindow(newClass);
        }
    }



}
