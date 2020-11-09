package com.taisf.services.test.product.dao;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.product.dao.ProductDao;
import com.taisf.services.product.dto.ProductListRequest;
import com.taisf.services.product.entity.ProductEntity;
import com.taisf.services.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * <p>商品的测试</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2016/3/25.
 * @version 1.0
 * @since 1.0
 */
public class ProductDaoTest extends BaseTest {


    @Resource(name = "product.productDao")
    private ProductDao productDao;


    @Test
    public void saveProductTest() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setPriceSale(1);
        productEntity.setProductName("name");
        productEntity.setProductDes("des");
        productEntity.setIsDel(0);
        productEntity.setProductSource(1);
        productEntity.setProductType(2);
        productDao.saveProduct(productEntity);
    }



    @Test
    public void updateProductTest() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1);
        productEntity.setPriceSale(1);
        productEntity.setProductName("name");
        productEntity.setProductDes("des");
        productEntity.setIsDel(0);
        productEntity.setProductSource(1);
        productEntity.setProductType(2);
        productDao.updateProduct(productEntity);
    }



    @Test
    public void getProductByIdTest() {
        ProductEntity productEntity = productDao.getProductById(572);
        System.out.println(JsonEntityTransform.Object2Json(productEntity));
    }

    @Test
    public void pageListProductTest() {
        PagingResult<ProductEntity> productEntityPagingResult = productDao.pageListProduct(new ProductListRequest());
        System.out.println(JsonEntityTransform.Object2Json(productEntityPagingResult));
    }

}
