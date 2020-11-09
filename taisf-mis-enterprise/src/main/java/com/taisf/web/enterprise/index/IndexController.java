package com.taisf.web.enterprise.index;

import com.taisf.services.ups.api.ResourceService;
import com.taisf.services.ups.entity.EmployeeEntity;
import com.taisf.services.ups.vo.TreeNodeVo;
import com.taisf.web.enterprise.common.constant.LoginConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>测试</p>
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
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Resource(name = "ups.resourceServiceProxy")
    private ResourceService resourceService;
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public
    String  inde(HttpServletRequest request,String key,String json) {
        HttpSession session = request.getSession();
        EmployeeEntity employeeEntity = (EmployeeEntity)session.getAttribute(LoginConstant.SESSION_KEY);
        List<TreeNodeVo> menuTreeNodeVos = resourceService.findResourceByCurrentUserId(employeeEntity.getUserId());
        request.setAttribute("menuTreeNodeVos",menuTreeNodeVos);
        return "index/index";
    }

}
