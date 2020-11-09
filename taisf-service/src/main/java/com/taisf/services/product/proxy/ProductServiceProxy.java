package com.taisf.services.product.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.product.api.ProductService;
import com.taisf.services.product.dao.ProductDao;
import com.taisf.services.product.dto.ProductListRequest;
import com.taisf.services.product.entity.ProductEntity;
import com.taisf.services.product.manager.ProductManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

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
@Component("basedata.productServiceProxy")
public class ProductServiceProxy implements ProductService {


    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceProxy.class);

    @Resource(name = "product.productManagerImpl")
    private ProductManagerImpl productManager;

    @Resource(name = "product.productDao")
    private ProductDao productDao;

    /**
     * @author:zhangzhengguang
     * @date:2017/10/11
     * @description:分页查询商品列表
     **/
    @Override
    public DataTransferObject<PagingResult<ProductEntity>> pageListProduct(ProductListRequest request) {
        DataTransferObject<PagingResult<ProductEntity>> dto = new DataTransferObject();
        try {
            PagingResult<ProductEntity> productEntityPagingResult = productDao.pageListProduct(request);
            dto.setData(productEntityPagingResult);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("分页查询商品列表失敗");
            return dto;
        }
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/12
     * @description:新增商品
     **/
    @Override
    public DataTransferObject<Void> saveProduct(ProductEntity productEntity) {
        DataTransferObject<Void> dto = new DataTransferObject();
        if (Check.NuNObj(productEntity)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            int num = productDao.saveProduct(productEntity);
            if (num != 1) {
                dto.setErrCode(DataTransferObject.ERROR);
                dto.setErrorMsg("新增商品失败");
                return dto;
            }
        } catch (Exception e) {
            LogUtil.error(LOGGER, "新增商品失败 error:{}{}", e, productEntity);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("新增商品失败");
            return dto;
        }
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/12
     * @description:根据ID查询菜品
     **/
    @Override
    public DataTransferObject<ProductEntity> getProductById(Integer id) {
        DataTransferObject<ProductEntity> dto = new DataTransferObject();
        if (Check.NuNObj(id)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            ProductEntity entity = productDao.getProductById(id);
            if (Check.NuNObj(entity)) {
                dto.setErrCode(DataTransferObject.ERROR);
                dto.setErrorMsg("查询商品失败");
                dto.setData(new ProductEntity());
                return dto;
            }
            dto.setData(entity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "查询商品失败 error:{}{}", e, id);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("查询商品失败");
            return dto;
        }
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/12
     * @description:根据ID修改菜品
     **/
    @Override
    public DataTransferObject<Void> updateProduct(ProductEntity productEntity) {
        DataTransferObject<Void> dto = new DataTransferObject();
        if (Check.NuNObj(productEntity)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            int num = productDao.updateProduct(productEntity);
            if (num != 1) {
                dto.setErrCode(DataTransferObject.ERROR);
                dto.setErrorMsg("修改商品失败");
                return dto;
            }
        } catch (Exception e) {
            LogUtil.error(LOGGER, "修改商品失败 error:{}{}", e, JsonEntityTransform.Object2Json(productEntity));
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("修改商品失败");
            return dto;
        }
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/13
     * @description:根据分类查询菜品集合
     **/
    @Override
    public DataTransferObject<List<ProductEntity>> getListByClassify(Integer productClassify) {
        DataTransferObject<List<ProductEntity>> dto = new DataTransferObject();
        if (Check.NuNObj(productClassify)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            List<ProductEntity> listByClassify = productDao.getListByClassify(productClassify);
            dto.setData(listByClassify);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "修改商品失败 error:{}{}", e, productClassify);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("修改商品失败");
            return dto;
        }
        return dto;
    }
}
