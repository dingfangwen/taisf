package com.taisf.services.supplier.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.supplier.api.SupplierPackageService;
import com.taisf.services.supplier.dao.SupplierPackageDao;
import com.taisf.services.supplier.dto.SupplierProductRequest;
import com.taisf.services.supplier.entity.SupplierPackageEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
@Component("supplier.supplierPackageServiceProxy")
public class SupplierPackageServiceProxy  {


    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierPackageServiceProxy.class);


    @Resource(name = "supplier.supplierPackageDao")
    private SupplierPackageDao supplierPackageDao;

//    /**
//     * @author:zhangzhengguang
//     * @date:2017/10/13
//     * @description:分页查询套餐信息
//     **/
//    @Override
//    public DataTransferObject<PagingResult<SupplierPackageEntity>> pageListSupplierProduct(SupplierProductRequest supplierProductRequest) {
//        DataTransferObject<PagingResult<SupplierPackageEntity>> dto = new DataTransferObject();
//        try {
//            PagingResult<SupplierPackageEntity> pagingResult = supplierPackageDao.pageListSupplierProduct(supplierProductRequest);
//            dto.setData(pagingResult);
//        } catch (Exception e) {
//            LogUtil.error(LOGGER, "【分页查询套餐信息失败】par:{},error:{}", JsonEntityTransform.Object2Json(supplierProductRequest), e);
//            dto.setErrCode(DataTransferObject.ERROR);
//            dto.setMsg("分页查询套餐信息失败");
//            return dto;
//        }
//        return dto;
//    }
//
//    /**
//     * @author:zhangzhengguang
//     * @date:2017/10/13
//     * @description:保存组合套餐信息
//     **/
//    @Override
//    public DataTransferObject<Void> saveSupplierPackage(SupplierPackageEntity supplierPackageEntity) {
//        DataTransferObject<Void> dto = new DataTransferObject();
//        try {
//            int num = supplierPackageDao.saveSupplierPackage(supplierPackageEntity);
//            if (num != 1) {
//                dto.setErrCode(DataTransferObject.ERROR);
//                dto.setErrorMsg("保存组合套餐信息失败");
//                return dto;
//            }
//        } catch (Exception e) {
//            LogUtil.error(LOGGER, "【保存组合套餐信息失败】par:{},error:{}", JsonEntityTransform.Object2Json(supplierPackageEntity), e);
//            dto.setErrCode(DataTransferObject.ERROR);
//            dto.setMsg("保存组合套餐信息失败");
//            return dto;
//        }
//        return dto;
//    }

//    /**
//     * @author:zhangzhengguang
//     * @date:2017/10/13
//     * @description:删除组合套餐
//     **/
//    @Override
//    public DataTransferObject<Void> deleteByPrimaryKey(Integer id) {
//        DataTransferObject<Void> dto = new DataTransferObject();
//        if (Check.NuNObj(id)) {
//            dto.setErrCode(DataTransferObject.ERROR);
//            dto.setErrorMsg("参数错误");
//            return dto;
//        }
//        try {
//            int num = supplierPackageDao.deleteByPrimaryKey(id);
//            if (num != 1) {
//                dto.setErrCode(DataTransferObject.ERROR);
//                dto.setErrorMsg("删除组合套餐失败");
//                return dto;
//            }
//        } catch (Exception e) {
//            LogUtil.error(LOGGER, "【删除组合套餐失败】par:{},error:{}", id, e);
//            dto.setErrCode(DataTransferObject.ERROR);
//            dto.setMsg("删除组合套餐失败");
//            return dto;
//        }
//        return dto;
//    }

//    /**
//     * @author:zhangzhengguang
//     * @date:2017/10/13
//     * @description:查询组合套餐信息根据ID
//     **/
//    @Override
//    public DataTransferObject<SupplierPackageEntity> getSupplierPackageById(Integer id) {
//        DataTransferObject<SupplierPackageEntity> dto = new DataTransferObject();
//        if (Check.NuNObj(id)) {
//            dto.setErrCode(DataTransferObject.ERROR);
//            dto.setErrorMsg("参数错误");
//            return dto;
//        }
//        try {
//            SupplierPackageEntity packageEntity = supplierPackageDao.getSupplierPackageById(id);
//            if (Check.NuNObj(packageEntity)) {
//                dto.setErrCode(DataTransferObject.ERROR);
//                dto.setErrorMsg("查询组合套餐信息根据ID失败");
//                return dto;
//            }
//            dto.setData(packageEntity);
//        } catch (Exception e) {
//            LogUtil.error(LOGGER, "【查询组合套餐信息根据ID失败】par:{},error:{}", id, e);
//            dto.setErrCode(DataTransferObject.ERROR);
//            dto.setMsg("查询组合套餐信息根据ID失败");
//            return dto;
//        }
//        return dto;
//    }

//    /**
//     * @author:zhangzhengguang
//     * @date:2017/10/13
//     * @description:修改组合套餐信息根据ID
//     **/
//    @Override
//    public DataTransferObject<Void> updateSupplierPackage(SupplierPackageEntity supplierPackageEntity) {
//        DataTransferObject<Void> dto = new DataTransferObject();
//        if (Check.NuNObj(supplierPackageEntity)) {
//            dto.setErrCode(DataTransferObject.ERROR);
//            dto.setErrorMsg("参数错误");
//            return dto;
//        }
//        try {
//            int num = supplierPackageDao.updateSupplierPackageById(supplierPackageEntity);
//            if (num != 1) {
//                dto.setErrCode(DataTransferObject.ERROR);
//                dto.setErrorMsg("修改组合套餐信息根据ID失败");
//                return dto;
//            }
//        } catch (Exception e) {
//            LogUtil.error(LOGGER, "【修改组合套餐信息根据ID失败】par:{},error:{}", JsonEntityTransform.Object2Json(supplierPackageEntity), e);
//            dto.setErrCode(DataTransferObject.ERROR);
//            dto.setMsg("修改组合套餐信息根据ID失败");
//            return dto;
//        }
//        return dto;
//    }
}
