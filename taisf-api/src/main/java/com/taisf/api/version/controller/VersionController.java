package com.taisf.api.version.controller;


import com.jk.framework.base.head.Header;
import com.jk.framework.base.rst.ResponseDto;
import com.jk.framework.base.utils.Check;
import com.taisf.api.common.abs.AbstractController;
import com.taisf.services.base.api.VersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>升级</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/4/4.
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("version")
public class VersionController extends AbstractController {


    private static final Logger LOGGER = LoggerFactory.getLogger(VersionController.class);

    @Autowired
    private VersionService versionService;

    /**
     * 获取当前的头信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public @ResponseBody
    ResponseDto location(HttpServletRequest request, HttpServletResponse response){

        Header header = getHeader(request);
        if (Check.NuNObj(header)){
            return new ResponseDto("异常的请求头信息");
        }
        //获取城市信息
        return  versionService.getVersion(header).trans2Res();
    }

}
