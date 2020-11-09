package com.taisf.api.message;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.head.Header;
import com.jk.framework.base.rst.ResponseDto;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.api.common.abs.AbstractController;
import com.taisf.services.message.api.MessageInfoService;
import com.taisf.services.message.entity.MessageInfoEntity;
import com.taisf.services.message.req.MessageInfoReq;
import com.taisf.services.message.vo.MessageInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author:zhangzhengguang
 * @date:2018/9/11
 * @description:
 **/
@Controller
@RequestMapping("messageInfo")
public class MessageController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Resource(name = "basedata.messageInfoServiceProxy")
    private MessageInfoService messageInfoService;


    /**
     * @author:zhangzhengguang
     * @date:2018/9/11
     * @description:拉取消息列表
     **/
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto list(HttpServletRequest request , HttpServletResponse response){
        MessageInfoReq req = getEntity(request, MessageInfoReq.class);
    	if(Check.NuNObj(req)){
            return  new ResponseDto("参数异常");
        }
        Header header = getHeader(request);
    	if (Check.NuNObj(header)){
            return  new ResponseDto("头信息异常");
        }

        String uid = getUserId(request);
    	if (Check.NuNStr(uid)){
            return  new ResponseDto("请登陆");
        }
        req.setUserId(uid);

        req.setApplicationCode(header.getApplicationCode());
        req.setPlatform(header.getSource());
        if(Check.NuNObjs(req.getApplicationCode(),req.getPlatform(),req.getSubject())){
            LogUtil.error(LOGGER,"拉取消息参数异常:{}",JsonEntityTransform.Object2Json(req));
            return  new ResponseDto("参数异常");
        }
        if(Check.NuNObj(req.getLastTime()) ||  differentDaysByMillisecond(req.getLastTime(),new Date()) > 7) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 7);
            req.setLastTime(calendar.getTime());
        }
        DataTransferObject<List<MessageInfoEntity>> dto = messageInfoService.getMessageInfoList(req);
        if(!dto.checkSuccess()){
            return  new ResponseDto(dto.getMsg());
        }
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(new MessageInfoVO(dto.getData(),new Date()));
        return responseDto;
    }



    @RequestMapping(value = "/boxRemind", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto boxRemind(HttpServletRequest request , HttpServletResponse response){
        MessageInfoReq req = getEntity(request, MessageInfoReq.class);
        if(Check.NuNObj(req)){
            return  new ResponseDto("参数异常");
        }
        Header header = getHeader(request);
        if (Check.NuNObj(header)){
            return  new ResponseDto("头信息异常");
        }
        String uid = getUserId(request);
        if (Check.NuNStr(uid)){
            return  new ResponseDto("请登陆");
        }
        req.setUserId(uid);

        req.setApplicationCode(header.getApplicationCode());
        req.setPlatform(header.getSource());
        if(Check.NuNObjs(req.getApplicationCode(),req.getPlatform(),req.getSubject())){
            LogUtil.error(LOGGER,"拉取消息参数异常:{}",JsonEntityTransform.Object2Json(req));
            return  new ResponseDto("参数异常");
        }
        if(Check.NuNObj(req.getLastTime()) ||  differentDaysByMillisecond(req.getLastTime(),new Date()) > 7) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 7);
            req.setLastTime(calendar.getTime());
        }
        DataTransferObject<List<MessageInfoEntity>> dto = messageInfoService.getMessageInfoList(req);
        if(!dto.checkSuccess()){
            return  new ResponseDto(dto.getMsg());
        }
        List<MessageInfoEntity> data = dto.getData();
        Map<String, Object> resultMap = new HashMap<>(2);
        if(!Check.NuNCollection(data)){
            resultMap.put("num",data.size());
        }else{
            resultMap.put("num",0);
        }
        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(resultMap);
        return responseDto;
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    private static int differentDaysByMillisecond(Date date1,Date date2) {
        return (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
    }



}
