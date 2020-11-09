package com.taisf.web.enterprise.window.controller;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.GeneratorIdUtil;
import com.jk.framework.log.utils.LogUtil;
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


@Controller
@RequestMapping("window/")
public class WindowController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WindowController.class);


    @Autowired
    private SupplierWindowService supplierWindowService;

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:
     **/
    @RequestMapping("windowList")
    public void MessageList(HttpServletRequest request) {
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/9/19
     * @description:
     **/
    @RequestMapping("showWindowList")
    @ResponseBody
    public PageResult showMessageList(HttpServletRequest request, SupplierWindowListRequest supplierWindowListRequest) {
        PageResult pageResult = new PageResult();
        try {
            HttpSession session = request.getSession();
            EmployeeEntity emp = (EmployeeEntity)session.getAttribute(LoginConstant.SESSION_KEY);
            supplierWindowListRequest.setSupplierCode(emp.getEmpBiz());
            PagingResult<SupplierWindowEntity> result = supplierWindowService.pageListSupplierWindow(supplierWindowListRequest).getData();
            if (!Check.NuNObj(result)) {
                pageResult.setTotal(result.getTotal());
                pageResult.setRows(result.getList());
            }
        } catch (Exception e) {
            LogUtil.error(LOGGER, "加载主题列表数据异常:{}", e);
            return pageResult;
        }
        return pageResult;
    }

    /**
     * @author:zhangzhengguang
     * @date:2018/9/20
     * @description:保存
     **/
    @RequestMapping("saveWindow")
    @ResponseBody
    public DataTransferObject<Void> saveWindow(HttpServletRequest request, SupplierWindowEntity entity) {
        DataTransferObject<Void> dto = new DataTransferObject();
        if (Check.NuNObj(entity)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            HttpSession session = request.getSession();
            EmployeeEntity emp = (EmployeeEntity)session.getAttribute(LoginConstant.SESSION_KEY);
            entity.setSupplierCode(emp.getEmpBiz());
            entity.setWindowCode(GeneratorIdUtil.getOrderCode(""));
            entity.setCreateTime(new Date());
            dto = supplierWindowService.saveSupplierWindow(entity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "saveWindow error:{}", e);
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
    @RequestMapping("updateWindow")
    @ResponseBody
    public DataTransferObject<Void> updateWindow(HttpServletRequest request, SupplierWindowEntity entity) {
        DataTransferObject<Void> dto = new DataTransferObject();
        if (Check.NuNObj(entity)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            dto = supplierWindowService.updateSupplierWindow(entity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "updateWindow error:{}", e);
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
    @RequestMapping("deleteWindow")
    @ResponseBody
    public DataTransferObject<Void> deleteWindow(HttpServletRequest request,Integer id) {
        DataTransferObject<Void> dto = new DataTransferObject();
        if (Check.NuNObj(id)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            dto = supplierWindowService.deleteSupplierWindow(id);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "deleteWindow error:{}", e);
            dto.setErrorMsg("系统异常");
            return dto;
        }
        return dto;
    }


}
