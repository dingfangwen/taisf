package com.jk.api.payment.controller;

import com.jk.api.dependency.common.abs.AbstractController;
import com.jk.framework.base.rst.ResponseDto;
import com.jk.framework.base.utils.Check;
import com.jk.framework.log.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@RequestMapping("/test")
public class TestController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value ="/aaa")
    public @ResponseBody
    ResponseDto aaa(HttpServletRequest request, HttpServletResponse response) {
        //获取当前参数
        String  json = getPar(request);
        if (Check.NuNStr(json)){
            return new ResponseDto("参数异常");
        }
        LogUtil.info(LOGGER,"参数:{}",json);

        return new ResponseDto();
    }

}
