package com.taisf.web.oms.common.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.MD5Util;
import com.taisf.services.ups.entity.EmployeeEntity;
import com.taisf.services.ups.service.EmployeeServiceImpl;
import com.taisf.web.oms.common.constant.LoginConstant;

/**
 * <p>登录</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Resource(name="ups.employeeServiceImpl")
    private EmployeeServiceImpl employeeService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public
    String  login(HttpServletRequest request,String key,String json) {
        return "login/login";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public
    String  logout(HttpServletRequest request,HttpServletResponse response,String key,String json) {
        HttpSession session = request.getSession();
        session.removeAttribute(LoginConstant.SESSION_KEY);
        return "login/login";
    }


    @RequestMapping(value = "dealLogin")
    @ResponseBody
    public DataTransferObject dealLogin(HttpServletRequest request,HttpServletResponse response,String empMail, String password,String requestUrl) {
        DataTransferObject dto = new DataTransferObject();
        if (Check.NuNObjs(empMail,password)){
            dto.setErrorMsg("参数异常");
            return dto;
        }
        List<EmployeeEntity> list = employeeService.findEmployeeByMail(empMail);
        if (Check.NuNCollection(list)){
            dto.setErrorMsg("当前用户不存在");
            return dto;
        }

        EmployeeEntity has = list.get(0);
        if (Check.NuNObj(has)){
            dto.setErrorMsg("当前用户不存在");
            return dto;
        }
        if (!MD5Util.MD5Encode(password).equals(has.getUserPwd())){
            dto.setErrorMsg("密码错误");
            return dto;
        }
        HttpSession session = request.getSession();
        session.setAttribute(LoginConstant.SESSION_KEY,has);
        return dto;
    }


}
