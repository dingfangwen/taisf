package com.taisf.api.user.controller;


import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.head.Header;
import com.jk.framework.base.rst.ResponseDto;
import com.jk.framework.base.utils.Check;
import com.taisf.api.common.abs.AbstractController;
import com.taisf.services.device.api.PushService;
import com.taisf.services.device.entity.DeviceEntity;
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
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/4/26.
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("push")
public class PushController extends AbstractController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PushController.class);
    @Autowired
    private PushService pushService;


    @RequestMapping(value = "/registDevice", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseDto registDevice(HttpServletRequest request, HttpServletResponse response) {
        ResponseDto dto = null;
        Header header = getHeader(request);
        DeviceEntity device = getEntity(request, DeviceEntity.class);
        if ((Check.NuNObj(device))) {
            dto = new ResponseDto("参数异常");
            return dto;
        }
        String userId = getUserId(request);
        if(Check.NuNStr(userId)){
            dto = new ResponseDto("请登录");
            return dto;
        }
        device.setDeviceType(String.valueOf(header.getDeviceType()));
        device.setUserId(userId);
        if ((Check.NuNObj(device.getRegId()))) {
            dto = new ResponseDto("参数异常");
            return dto;
        }
        if(Check.NuNObjs(device.getDeviceToken(),device.getPushType(), device.getDeviceType())){
            dto = new ResponseDto("参数异常");
            return dto;
        }
        DataTransferObject<Void> result = pushService.registDevice(device);
        return result.trans2Res();
    }

    @RequestMapping(value = "/logoutDevice", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseDto logoutDevice(HttpServletRequest request, HttpServletResponse response) {
        ResponseDto dto = null;
        DeviceEntity device = getEntity(request, DeviceEntity.class);
        if ((Check.NuNObj(device))) {
            dto = new ResponseDto("参数异常");
            return dto;
        }
        String userId = getUserId(request);
        device.setUserId(userId);
        if ((Check.NuNObjs(device.getRegId(), device.getUserId()))) {
            dto = new ResponseDto("参数异常");
            return dto;
        }
        DataTransferObject<Void> result = pushService.logoutDevice(device);
        return result.trans2Res();
    }
}