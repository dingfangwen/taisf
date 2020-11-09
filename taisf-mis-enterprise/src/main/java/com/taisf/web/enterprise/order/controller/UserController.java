package com.taisf.web.enterprise.order.controller;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.MD5Util;
import com.jk.framework.base.utils.UUIDGenerator;
import com.jk.framework.excel.utils.ExcelUtil;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.common.valenum.UserStatusEnum;
import com.taisf.services.common.valenum.UserTypeEnum;
import com.taisf.services.enterprise.api.EnterpriseService;
import com.taisf.services.enterprise.dto.EnterpriseListRequest;
import com.taisf.services.enterprise.entity.EnterpriseEntity;
import com.taisf.services.supplier.api.SupplierService;
import com.taisf.services.supplier.dto.SupplierRequest;
import com.taisf.services.supplier.entity.SupplierEntity;
import com.taisf.services.supplier.manager.SupplierManagerImpl;
import com.taisf.services.ups.entity.EmployeeEntity;
import com.taisf.services.user.api.UserService;
import com.taisf.services.user.dto.UserRequest;
import com.taisf.services.user.entity.UserAccountEntity;
import com.taisf.services.user.entity.UserEntity;
import com.taisf.services.user.manager.UserManagerImpl;
import com.taisf.web.enterprise.common.constant.LoginConstant;
import com.taisf.web.enterprise.common.page.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("user/")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private SupplierService supplierService;

    @Resource(name = "user.userManagerImpl")
    private UserManagerImpl userManager;

    @Resource(name = "supplier.supplierManagerImpl")
    private SupplierManagerImpl supplierManagerImpl;






    /**
     * @author:zhangzhengguang
     * @date:2017/10/11
     * @description:销售管理列表
     **/
    @RequestMapping("list")
    public String list(HttpServletRequest request) {
        return "user/userList";
    }


    /**
     * 销售列表
     * @param request
     * @param userRequest
     * @return
     */
    @RequestMapping("pageKnightList")
    @ResponseBody
    public PageResult pageKnightList(HttpServletRequest request, UserRequest userRequest) {
        PageResult pageResult = new PageResult();
        try {
            EmployeeEntity emp = (EmployeeEntity)request.getSession().getAttribute(LoginConstant.SESSION_KEY);
            userRequest.setBizCode(emp.getEmpBiz());
            DataTransferObject<PagingResult<UserEntity>> dto = userService.pageKnightListUser(userRequest);
            if (!Check.NuNObj(dto.getData().getList())) {
                pageResult.setRows(dto.getData().getList());
                pageResult.setTotal(dto.getData().getTotal());
            }
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params:{}", JsonEntityTransform.Object2Json(userRequest));
            LogUtil.error(LOGGER, "error:{}", e);
            return new PageResult();
        }
        return pageResult;
    }


    /**
     * @author:zhangzhengguang
     * @date:2017/10/11
     * @description:销售管理列表数据
     **/
    @RequestMapping("pageList")
    @ResponseBody
    public PageResult pageList(HttpServletRequest request, UserRequest userRequest) {
        PageResult pageResult = new PageResult();
        try {
            DataTransferObject<PagingResult<UserEntity>> dto = userService.pageListUser(userRequest);
            if (!Check.NuNObj(dto.getData().getList())) {
                pageResult.setRows(dto.getData().getList());
                pageResult.setTotal(dto.getData().getTotal());
            }
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params:{}", JsonEntityTransform.Object2Json(userRequest));
            LogUtil.error(LOGGER, "error:{}", e);
            return new PageResult();
        }
        return pageResult;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/11
     * @description:新增销售员工
     **/
    @RequestMapping("addUser")
    @ResponseBody
    public DataTransferObject<Void> addUser(HttpServletRequest request, UserEntity userEntity) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(userEntity)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if(!Check.NuNObj(userEntity.getUserPhone())){
            UserEntity byUserPhone =userManager.getUserByUserPhone(userEntity.getUserPhone(),UserTypeEnum.SONGCAN.getCode());
            if(!Check.NuNObj(byUserPhone)){
                dto.setErrorMsg("手机号已存在");
                dto.setErrCode(DataTransferObject.ERROR);
                return dto;
            }
        }
        //判断payCode 如果不为空 查询是非已存在
        if(!Check.NuNObj(userEntity.getPayCode())){
            UserEntity user = userManager.getByPayCode(userEntity.getPayCode());
            if(!Check.NuNObj(user)){
                dto.setErrorMsg("支付码已存在");
                dto.setErrCode(DataTransferObject.ERROR);
                return dto;
            }
        }
        //获取当前登录员工
        EmployeeEntity employeeEntity = (EmployeeEntity)request.getSession().getAttribute(LoginConstant.SESSION_KEY);

        //根据UserID查询 supplier 表 得到code
        SupplierEntity supplier = supplierManagerImpl.getSupplierByEmp(employeeEntity.getEmpBiz());
        if (Check.NuNObj(supplier)){
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("当前登录用户不是供应商,不能创建员工");
            return dto;
        }
        try {
            String uuid = UUIDGenerator.hexUUID();
            userEntity.setUserPassword(MD5Util.MD5Encode("123456"));
            userEntity.setUserStatus(UserStatusEnum.ACTIVITY.getCode());
            userEntity.setUserUid(uuid);
            userEntity.setCreateTime(new Date());
            userEntity.setUserType(UserTypeEnum.SONGCAN.getCode());
            userEntity.setBizCode(employeeEntity.getEmpBiz());
            userService.saveUser(userEntity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("系统错误");
            return dto;
        }
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/11
     * @description:编辑销售员工
     **/
    @RequestMapping("toedit")
    @ResponseBody
    public DataTransferObject<UserEntity> toedit(HttpServletRequest request, Integer id) {
        DataTransferObject<UserEntity> dto = new DataTransferObject<>();
        if (Check.NuNObj(id)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            dto = userService.getUserById(id);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("系统错误");
            return dto;
        }
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/11
     * @description:编辑保存销售员工
     **/
    @RequestMapping("editUser")
    @ResponseBody
    public DataTransferObject<Void> editUser(HttpServletRequest request, UserEntity userEntity) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(userEntity)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            dto = userService.updateUser(userEntity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("系统错误");
            return dto;
        }
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/11
     * @description:离职
     **/
    @RequestMapping("updateUserStatus")
    @ResponseBody
    public DataTransferObject<Void> updateUserStatus(HttpServletRequest request,UserEntity userEntity) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObjs(userEntity,userEntity.getUserUid(),userEntity.getUserStatus())) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }
        //查询企业表和供餐商表 判断是否有维护数据,如果有不允许离职
        EnterpriseListRequest enterpriseListRequest = new EnterpriseListRequest();
        enterpriseListRequest.setManger(userEntity.getUserUid());
        SupplierRequest supplierRequest = new SupplierRequest();
        supplierRequest.setManger(userEntity.getUserUid());
        PagingResult<EnterpriseEntity> entityPagingResult = enterpriseService.pageListAndManger(enterpriseListRequest).getData();
        PagingResult<SupplierEntity> supplierEntityPagingResult = supplierService.supplierPageList(supplierRequest).getData();
        if(!Check.NuNCollection(supplierEntityPagingResult.getList()) || !Check.NuNCollection(entityPagingResult.getList())){
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("请先将该员工下维护信息做变更后方可做离职变更");
            return dto;
        }
        try {
            userService.updateUser(userEntity);
            //离职后账号自动解除?
        } catch (Exception e) {
            LogUtil.error(LOGGER, "error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("系统错误");
            return dto;
        }
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/14
     * @description:去企业列表页面
     **/
    @RequestMapping("toEnterprisePageList")
    public String toEnterprisePageList(HttpServletRequest request, String manger, String userName) {
        request.setAttribute("manger", manger);
        request.setAttribute("userName", userName);

        //查询所有用户

        DataTransferObject<List<UserEntity>> dto = userService.getUserByType(UserTypeEnum.YONGHU.getCode());
        request.setAttribute("userList", dto.getData());
        return "user/enterpriseList";
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/14
     * @description:去供餐商列表页面
     **/
    @RequestMapping("toSupplierPageList")
    public String toSupplierPageList(HttpServletRequest request, String manger, String userName) {
        request.setAttribute("manger", manger);
        request.setAttribute("userName", userName);
        return "user/supplierPageList";
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/14
     * @description:企业列表
     **/
    @RequestMapping("enterprisePageList")
    @ResponseBody
    public PageResult enterprisePageList(HttpServletRequest request, EnterpriseListRequest req) {
        PageResult pageResult = new PageResult();
        try {
            PagingResult<EnterpriseEntity> data = enterpriseService.pageListAndManger(req).getData();
            if (!Check.NuNObj(data.getList())) {
                pageResult.setRows(data.getList());
                pageResult.setTotal(data.getTotal());
            }
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params:{}", JsonEntityTransform.Object2Json(req));
            LogUtil.error(LOGGER, "error:{}", e);
            return new PageResult();
        }
        return pageResult;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/14
     * @description:供餐商列表
     **/
    @RequestMapping("supplierPageList")
    @ResponseBody
    public PageResult supplierPageList(HttpServletRequest request, SupplierRequest req) {
        PageResult pageResult = new PageResult();
        try {
            PagingResult<SupplierEntity> data = supplierService.supplierPageList(req).getData();
            if (!Check.NuNObj(data.getList())) {
                pageResult.setRows(data.getList());
                pageResult.setTotal(data.getTotal());
            }
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params:{}", JsonEntityTransform.Object2Json(req));
            LogUtil.error(LOGGER, "error:{}", e);
            return new PageResult();
        }
        return pageResult;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/14
     * @description:转让
     **/
    @RequestMapping("transfer")
    @ResponseBody
    public DataTransferObject<Void> transfer(HttpServletRequest request, EnterpriseEntity enterpriseEntity) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(enterpriseEntity)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            dto = enterpriseService.updateEnterprise(enterpriseEntity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("系统错误");
            return dto;
        }
        return dto;
    }


    /**
     * @author:zhangzhengguang
     * @date:2017/10/16
     * @description:企业员工管理列表
     **/
    @RequestMapping("companyUserlist")
    public String companyUserlist(HttpServletRequest request) {
        return "user/companyUserlist";
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/11
     * @description:销售管理列表数据
     **/
    @RequestMapping("pageListCompanyUser")
    @ResponseBody
    public PageResult pageListCompanyUser(HttpServletRequest request, UserRequest userRequest) {
        PageResult pageResult = new PageResult();
        try {
            EmployeeEntity emp = (EmployeeEntity)request.getSession().getAttribute(LoginConstant.SESSION_KEY);
            userRequest.setBizCode(emp.getEmpBiz());
            DataTransferObject<PagingResult<UserEntity>> dto = userService.pageListCompanyUser(userRequest);
            if (!Check.NuNObj(dto.getData().getList())) {
                List<String> ids = dto.getData().getList().stream().map(UserEntity::getUserUid).collect(Collectors.toList());
                Map<String, UserAccountEntity> map = userManager.getUserAccountMapByList(ids);
                dto.getData().getList().stream().forEach((x) ->{
                    if(!Check.NuNObj(map.get(x.getUserUid()))){
                        x.setAmount(map.get(x.getUserUid()).getDrawBalance());
                        x.setAccountStatus(map.get(x.getUserUid()).getAccountStatus());
                    }
                });
                pageResult.setRows(dto.getData().getList());
                pageResult.setTotal(dto.getData().getTotal());
            }
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params:{}", JsonEntityTransform.Object2Json(userRequest));
            LogUtil.error(LOGGER, "error:{}", e);
            return new PageResult();
        }
        return pageResult;
    }

    @RequestMapping("userListExcel")
    public void moneyListExcel(HttpServletRequest request, HttpServletResponse response, UserRequest userRequest) throws UnsupportedEncodingException {
        response.setContentType("octets/stream");
        String fileName = "企业员工列表";
        response.addHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("GB2312"),"ISO8859-1") +".xls");

        try{
            EmployeeEntity emp = (EmployeeEntity)request.getSession().getAttribute(LoginConstant.SESSION_KEY);
            userRequest.setBizCode(emp.getEmpBiz());
            DataTransferObject<List<UserEntity>> dto = userService.findAllCompanyUser(userRequest);
            if (!Check.NuNObj(dto.getData())) {
                List<String> ids = dto.getData().stream().map(UserEntity::getUserUid).collect(Collectors.toList());
                Map<String, UserAccountEntity> map = userManager.getUserAccountMapByList(ids);
                dto.getData().stream().forEach((x) ->{
                    if(!Check.NuNObj(map.get(x.getUserUid()))){
                        x.setAmount(map.get(x.getUserUid()).getDrawBalance());
                        x.setAccountStatus(map.get(x.getUserUid()).getAccountStatus());
                    }
                });
                ExcelUtil.exportExcel(response.getOutputStream(),dto.getData());
            }
        }catch (Exception e){
            LogUtil.error(LOGGER, "企业员工列表导出excel异常:{}",e);
        }
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/16
     * @description:新增企业员工
     **/
    @RequestMapping("addCompanyUser")
    @ResponseBody
    public DataTransferObject<Void> addCompanyUser(HttpServletRequest request, UserEntity userEntity) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(userEntity)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if(!Check.NuNObj(userEntity.getUserPhone())){
            UserEntity byUserPhone = userManager.getByUserPhone(userEntity.getUserPhone());
            if(!Check.NuNObj(byUserPhone)){
                dto.setErrorMsg("手机号已存在");
                dto.setErrCode(DataTransferObject.ERROR);
                return dto;
            }
        }
        try {
            EmployeeEntity emp = (EmployeeEntity)request.getSession().getAttribute(LoginConstant.SESSION_KEY);
            userEntity   .setBizCode(emp.getEmpBiz());
            String uuid = UUIDGenerator.hexUUID();
            userEntity.setUserPassword(userEntity.getUserPassword());
            userEntity.setUserStatus(UserStatusEnum.ACTIVITY.getCode());
            userEntity.setUserUid(uuid);
            userEntity.setUserType(UserTypeEnum.YONGHU.getCode());
            userEntity.setCreateTime(new Date());
            userService.saveUserOnly(userEntity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("系统错误");
            return dto;
        }
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/16
     * @description:编辑保存企业员工
     **/
    @RequestMapping("editCompanyUser")
    @ResponseBody
    public DataTransferObject<Void> editCompanyUser(HttpServletRequest request, UserEntity userEntity) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(userEntity)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            dto = userService.updateUser(userEntity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("系统错误");
            return dto;
        }
        return dto;
    }




    /**
     * @author:afi
     * @date:2017/10/16
     * @description:编辑保存企业员工
     **/
    @RequestMapping("updateAdminUser")
    @ResponseBody
    public DataTransferObject<Void> updateAdminUser(HttpServletRequest request,String userUid,Integer isAdmin) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(userUid)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNObj(isAdmin)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            userService.updateUserAdmin(userUid,isAdmin);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("系统错误");
            return dto;
        }
        return dto;
    }


    /**
     * @author:zhangzhengguang
     * @date:2017/10/16
     * @description:编辑保存企业员工
     **/
    @RequestMapping("updateAccountUser")
    @ResponseBody
    public DataTransferObject<Void> updateAccountUser(HttpServletRequest request,UserEntity userEntity) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(userEntity)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            //如果是注销
            if(userEntity.getUserStatus() == UserStatusEnum.FORBIDDEN.getCode()){
                userService.forbiddenUser(userEntity.getUserUid());
            }else{
                userService.updateUser(userEntity);
            }
        } catch (Exception e) {
            LogUtil.error(LOGGER, "error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("系统错误");
            return dto;
        }
        return dto;
    }
}
