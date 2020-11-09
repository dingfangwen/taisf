package com.taisf.web.enterprise.classify.controller;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.GeneratorIdUtil;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.classify.api.ProductClassifyService;
import com.taisf.services.classify.entity.ProductClassifyEntity;
import com.taisf.services.classify.req.ProductClassifyListRequest;
import com.taisf.services.ups.entity.EmployeeEntity;
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


@Controller
@RequestMapping("classify/")
public class ClassifyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassifyController.class);


    @Autowired
    private ProductClassifyService productClassifyService;

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:
     **/
    @RequestMapping("classifyList")
    public void MessageList(HttpServletRequest request) {
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:
     **/
    @RequestMapping("showClassifyList")
    @ResponseBody
    public PageResult showMessageList(HttpServletRequest request, ProductClassifyListRequest supplierClassifyListRequest) {
        PageResult pageResult = new PageResult();
        try {
            HttpSession session = request.getSession();
            EmployeeEntity emp = (EmployeeEntity)session.getAttribute(LoginConstant.SESSION_KEY);
            supplierClassifyListRequest.setSupplierCode(emp.getEmpBiz());
            PagingResult<ProductClassifyEntity> result = productClassifyService.pageListProductClassify(supplierClassifyListRequest).getData();
            if (!Check.NuNObj(result)) {
                pageResult.setTotal(result.getTotal());
                pageResult.setRows(result.getList());
            }
        } catch (Exception e) {
            LogUtil.error(LOGGER, "加载分类列表数据异常:{}", e);
            return pageResult;
        }
        return pageResult;
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/9/20
     * @description:保存
     **/
    @RequestMapping("saveClassify")
    @ResponseBody
    public DataTransferObject<Void> saveClassify(HttpServletRequest request, ProductClassifyEntity entity) {
        DataTransferObject<Void> dto = new DataTransferObject();
        if (Check.NuNObj(entity)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            HttpSession session = request.getSession();
            EmployeeEntity emp = (EmployeeEntity)session.getAttribute(LoginConstant.SESSION_KEY);
            entity.setSupplierCode(emp.getEmpBiz());
            entity.setClassifyCode(GeneratorIdUtil.getOrderCode(""));
            entity.setCreateTime(new Date());
            dto = productClassifyService.saveProductClassify(entity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "saveClassify error:{}", e);
            dto.setErrorMsg("系统异常");
            return dto;
        }
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/9/20
     * @description:修改
     **/
    @RequestMapping("updateClassify")
    @ResponseBody
    public DataTransferObject<Void> updateClassify(HttpServletRequest request, ProductClassifyEntity entity) {
        DataTransferObject<Void> dto = new DataTransferObject();
        if (Check.NuNObj(entity)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            dto = productClassifyService.updateProductClassify(entity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "updateClassify error:{}", e);
            dto.setErrorMsg("系统异常");
            return dto;
        }
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/9/20
     * @description:删除
     **/
    @RequestMapping("deleteClassify")
    @ResponseBody
    public DataTransferObject<Void> deleteClassify(HttpServletRequest request,Integer id) {
        DataTransferObject<Void> dto = new DataTransferObject();
        if (Check.NuNObj(id)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            dto = productClassifyService.deleteProductClassify(id);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "deleteClassify error:{}", e);
            dto.setErrorMsg("系统异常");
            return dto;
        }
        return dto;
    }


}
