package com.taisf.web.oms.permission.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.UUIDGenerator;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.ups.api.EmployeeService;
import com.taisf.services.ups.dto.EmployeeAddRequest;
import com.taisf.services.ups.dto.EmployeeRequest;
import com.taisf.services.ups.entity.EmployeeEntity;
import com.taisf.services.ups.service.EmployeeServiceImpl;
import com.taisf.services.ups.vo.EmployeeVo;
import com.taisf.web.oms.common.page.PageResult;

/**
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/4/8.
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("system/user")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionController.class);


    @Resource(name = "ups.employeeServiceImpl")
    private EmployeeServiceImpl employeeService;

    @Resource(name = "ups.employeeServiceProxy")
    private EmployeeService employeeServiceProxy;


    /**
     * 后台用户列表
     *
     * @param request
     * @author afi
     */
    @RequestMapping("userList")
    public void userList(HttpServletRequest request) {

    }


    /**
     * 后台用户列表
     *
     * @param request
     * @author afi
     */
    @RequestMapping("userListData")
    @ResponseBody
    public PageResult userListData(HttpServletRequest request, EmployeeRequest paramRequest) {
        PageResult rst = new PageResult();
        PagingResult<EmployeeEntity> pr = employeeService.findEmployeeForPage(paramRequest);
        rst.setRows(pr.getList());
        rst.setTotal(pr.getTotal());
        return rst;
    }


    /**
     * 添加用户
     *
     * @author zll
     */
    @RequestMapping("toAddEmployee")
    public void toAddUser(HttpServletRequest request) {
    }

    /**
     * 保存用户
     *
     * @param request
     * @author afi
     */
    @RequestMapping("saveUser")
    @ResponseBody
    public DataTransferObject saveUserData(EmployeeAddRequest request) {
        DataTransferObject dto = new DataTransferObject();
        if (Check.NuNObj(request.getEmpMail())) {
            dto.setErrorMsg("请输入用户名");
            return dto;
        } else {
            List<EmployeeEntity> list = employeeService.findEmployeeByMail(request.getEmpMail());
            if (!Check.NuNCollection(list)) {
                dto.setErrorMsg("用户名已经被占用");
                return dto;
            }
        }
        if (Check.NuNStr(request.getUserId())) {
            request.setUserId(UUIDGenerator.hexUUID());
        }
        employeeService.insertEmployeeSysc(request);
        return dto;
    }

    /**
     * 修改密码
     *
     * @param request
     * @author afi
     */

    @RequestMapping("changePwd")
    @ResponseBody
    public DataTransferObject changePwd(HttpServletRequest request, String userId, String password) {
        DataTransferObject dto = new DataTransferObject();
        if (Check.NuNObj(userId)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }

        if (Check.NuNObj(password)) {
            dto.setErrorMsg("密码为空");
            return dto;
        }

        EmployeeEntity has = employeeService.findEmployeeByUid(userId);
        if (Check.NuNObj(has)) {
            dto.setErrorMsg("当前用户不存在");
            return dto;
        }

        employeeService.changePwd(userId, password);
        return dto;
    }

    @RequestMapping("toEditEmployee")
    public void toEidtEmployee(HttpServletRequest request, String userId) {
        EmployeeVo employeeVo = new EmployeeVo();
        employeeVo = employeeService.findEmployeeVoByUid(userId);
        request.setAttribute("employeeVo", employeeVo);
    }

    @RequestMapping("editEmployee")
    @ResponseBody
    public DataTransferObject eidtEmployee(EmployeeAddRequest request) {
        DataTransferObject dto = new DataTransferObject();
        if (Check.NuNObj(request.getEmpMail())) {
            dto.setErrorMsg("请输入用户名");
            return dto;
        }
        employeeService.updateEmployeeSysc(request);
        return dto;

    }

    /**
     * 根据医生姓名 模糊查询人员信息
     *
     * @param name
     * @return
     */
    @RequestMapping("findEmployeeByName")
    @ResponseBody
    public DataTransferObject<List<EmployeeEntity>> findEmployeeByName(String name) {
        DataTransferObject<List<EmployeeEntity>> dto = new DataTransferObject();
        if (Check.NuNObj(name)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("参数异常");
            return dto;
        }

        try {
            dto = employeeServiceProxy.findEmployeeByName(name);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "查询人员信息异常:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
        }
        return dto;
    }


    /**
     * 根据科室id查询人员信息
     *
     * @param departmentId
     * @return
     */
    @RequestMapping("listEmployeeByDepartId")
    @ResponseBody
    public DataTransferObject<List<EmployeeEntity>> listEmployeeByDepartId(String departmentId) {
        DataTransferObject<List<EmployeeEntity>> dto = new DataTransferObject();
        if (Check.NuNObj(departmentId)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("参数异常");
            return dto;
        }

        try {
            dto = employeeServiceProxy.listEmployeeByDepartId(departmentId);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "查询人员信息异常:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
        }
        return dto;
    }

}
