package com.taisf.api.index.controller;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.rst.ResponseDto;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.ValueUtil;
import com.jk.framework.cache.redis.api.RedisOperations;
import com.taisf.api.common.abs.AbstractController;
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
 * <p>抽象service</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/09.
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/redis")
public class RedisController extends AbstractController {


    private static final Logger LOGGER = LoggerFactory.getLogger(RedisController.class);


    @Autowired
    private RedisOperations redisOperation;

    @RequestMapping("")
    public @ResponseBody
    ResponseDto getIndex(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseDto("api ok");
    }


    @RequestMapping(value ="/get")
    public @ResponseBody
    ResponseDto get(HttpServletRequest request, HttpServletResponse response,String key) {

        DataTransferObject<String> result = new DataTransferObject();

        if (Check.NuNStr(key)){
            return new ResponseDto("参数异常");
        }
        boolean exist = redisOperation.exists(key);
        if (!exist){
            return new ResponseDto("当前key不存在");
        }
        String rst = redisOperation.get(key);
        result.setData(ValueUtil.getStrValue(rst));
        return result.trans2Res();
    }


    @RequestMapping(value ="/del")
    public @ResponseBody
    ResponseDto del(HttpServletRequest request, HttpServletResponse response,String key) {

        DataTransferObject<Void> result = new DataTransferObject();

        if (Check.NuNStr(key)){
            return new ResponseDto("删除参数异常");
        }
        boolean exist = redisOperation.exists(key);
        if (!exist){
            return new ResponseDto("当前key不存在");
        }
        redisOperation.del(key);
        return result.trans2Res();
    }

    @RequestMapping(value ="/set")
    public @ResponseBody
    ResponseDto set(HttpServletRequest request, HttpServletResponse response,String key,String value) {

        DataTransferObject<Void> result = new DataTransferObject();
        if (Check.NuNStr(key)){
            return new ResponseDto("参数异常");
        }
        redisOperation.setex(key, 24*60*60, value);
        return result.trans2Res();
    }


    @RequestMapping(value = "/put", method = RequestMethod.POST)
    @ResponseBody
    ResponseDto put(HttpServletRequest request, HttpServletResponse response,String key) {
        DataTransferObject<Void> result = new DataTransferObject();
        if (Check.NuNStr(key)){
            return new ResponseDto("参数异常");
        }
        redisOperation.setex(key, 24*60*60, getPar(request));
        return result.trans2Res();
    }
}
