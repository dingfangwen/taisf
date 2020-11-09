package com.taisf.api.user.controller;

import com.jk.framework.base.constant.YesNoEnum;
import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.head.Header;
import com.jk.framework.base.rst.ResponseDto;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.ValueUtil;
import com.jk.framework.cache.redis.api.RedisOperations;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.api.common.abs.AbstractController;
import com.taisf.api.user.dto.ChangePwdRequest;
import com.taisf.api.util.HeaderUtil;
import com.taisf.services.common.valenum.SmsTypeEnum;
import com.taisf.services.user.api.IndexService;
import com.taisf.services.user.api.UserService;
import com.taisf.services.user.dto.*;
import com.taisf.services.user.vo.IndexVO;
import com.taisf.services.user.vo.RegistInfoVO;
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
 * <p>用户先关接口</p>
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
@RequestMapping("/user")
public class UserController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;

    @Autowired
    private IndexService indexService;


    @Autowired
    private RedisOperations redisOperation;


    @RequestMapping(value ="index")
    public @ResponseBody
    ResponseDto index(HttpServletRequest request, HttpServletResponse response) {


        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        //获取当前参数
        String userUid = getUserId(request);
        if (Check.NuNStr(userUid)) {
            return new ResponseDto("参数异常");
        }
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(userUid));
        try {
            DataTransferObject<IndexVO> dto =indexService.getIndex(userUid);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【初始化首页】错误,par:{}, e={}",JsonEntityTransform.Object2Json(userUid), e);
            return new ResponseDto("未知错误");
        }

    }


    /**
     * 注册
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/openRegist", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto openRegist(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        //获取当前参数
        UserOpenRegistRequest paramRequest = getEntity(request, UserOpenRegistRequest.class);
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }
        if (Check.NuNObjs(paramRequest.getEnterpriseCode(),paramRequest.getUserName(),paramRequest.getUserPhone(),paramRequest.getPwd())){
            return new ResponseDto("参数异常");
        }

        String  key = HeaderUtil.getCodeStr(header, SmsTypeEnum.OPEN_REGIST.getCode());
        String value= redisOperation.get(key);
        if (!ValueUtil.getStrValue(value).equals(ValueUtil.getStrValue(paramRequest.getMsgCode()))){
            LogUtil.info(LOGGER, "当前验证码为:{},参数验证码:{},传入参数:{}", value,paramRequest.getMsgCode(),JsonEntityTransform.Object2Json(paramRequest));
            return new ResponseDto("验证码错误");
        }
        paramRequest.setHeader(header);
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {

            DataTransferObject<RegistInfoVO> dto =userService.openRegist(paramRequest);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【开放注册】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }

    }




    /**
     * 注册
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto regist(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        //获取当前参数
        UserRegistRequest paramRequest = getEntity(request, UserRegistRequest.class);
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }

        String  key = HeaderUtil.getCodeStr(header, SmsTypeEnum.USER_REGIST.getCode());
        String value= redisOperation.get(key);
        if (!ValueUtil.getStrValue(value).equals(ValueUtil.getStrValue(paramRequest.getMsgCode()))){
            return new ResponseDto("验证码错误");
        }
        paramRequest.setHeader(header);
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {

            DataTransferObject<RegistInfoVO> dto =userService.regist(paramRequest);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【注册】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }

    }



    /**
     * 修改登录密码
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/changeUserPwd", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto changeUserPwd(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        //获取当前参数
        ChangePwdRequest paramRequest = getEntity(request, ChangePwdRequest.class);
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }
        if (Check.NuNStr(paramRequest.getNewPwd())) {
            return new ResponseDto("请输入新密码");
        }
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {

            DataTransferObject<Void> dto =userService.updateUserPwd(getUserId(request),paramRequest.getNewPwd(),paramRequest.getOldPwd());
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【修改登录密码】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }

    }


    /**
     * 关闭免密支付
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/openPwd", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto openPwd(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        String userId = getUserId(request);
        if (Check.NuNStr(userId)){
            return new ResponseDto("异常的用户信息");
        }


        //获取当前参数
        CloseIsPwdRequest paramRequest = getEntity(request, CloseIsPwdRequest.class);
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }
        if (Check.NuNStr(paramRequest.getAccountPwd())) {
            return new ResponseDto("请输入支付密码");
        }
        LogUtil.info(LOGGER, "开通免密服务: user:{},par:{}", userId,JsonEntityTransform.Object2Json(paramRequest));
        try {
            DataTransferObject<Void> dto =userService.openIsPwd(userId,paramRequest.getAccountPwd());
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【关闭免密服务】错误,par:{}, e={}",userId, e);
            return new ResponseDto("未知错误");
        }
    }



    /**
     * 关闭免密支付
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/closePwd", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto closePwd(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        String userId = getUserId(request);
        if (Check.NuNStr(userId)){
            return new ResponseDto("异常的用户信息");
        }
        LogUtil.info(LOGGER, "关闭免密服务:{}", JsonEntityTransform.Object2Json(userId));
        try {
            DataTransferObject<Void> dto =userService.closeIsPwd(userId);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【关闭免密服务】错误,par:{}, e={}",userId, e);
            return new ResponseDto("未知错误");
        }
    }




    /**
     * 修改支付密码
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/changeAccountPwd", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto changeAccountPwd(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        //获取当前参数
        ChangePwdRequest paramRequest = getEntity(request, ChangePwdRequest.class);
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }
        if (Check.NuNStr(paramRequest.getNewPwd())) {
            return new ResponseDto("请输入新密码");
        }

        if (Check.NuNStr(paramRequest.getMsgCode())) {
            return new ResponseDto("请输入验证码");
        }
        //默认不支持免密
        boolean isPWd = false;
        if (ValueUtil.getintValue(paramRequest.getIsPWd()) == YesNoEnum.YES.getCode()){
            isPWd = true;
        }
        String  key = HeaderUtil.getCodeStr(header, SmsTypeEnum.PWD_ACCOUNT.getCode());
        String value= redisOperation.get(key);
        if (!ValueUtil.getStrValue(value).equals(ValueUtil.getStrValue(paramRequest.getMsgCode()))){
            return new ResponseDto("验证码错误");
        }
        LogUtil.info(LOGGER, "changeAccountPwd传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {
            DataTransferObject<Void> dto =userService.updateAccountPasswordAndPwd(getUserId(request),paramRequest.getNewPwd(),isPWd);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【修改登录密码】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }
    }





    /**
     * 验证码登录
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/loginByCode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto loginByCode(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        //获取当前参数
        UserLoginCodeRequest paramRequest = getEntity(request, UserLoginCodeRequest.class);
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }
        paramRequest.setHeader(header);

        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }
        if (Check.NuNStr(paramRequest.getUserPhone())) {
            return new ResponseDto("请输入电话");
        }
        if (Check.NuNStr(paramRequest.getMsgCode())) {
            return new ResponseDto("请输入验证码");
        }

        String  key = HeaderUtil.getCodeStr(header, SmsTypeEnum.USER_LOG.getCode());
        String value= redisOperation.get(key);
        if (!ValueUtil.getStrValue(value).equals(ValueUtil.getStrValue(paramRequest.getMsgCode()))){
            return new ResponseDto("验证码错误");
        }
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {

            DataTransferObject<String> dto =userService.loginByCode(paramRequest);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【登录】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }

    }






    /**
     * 登录
     * @author afi
      * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto login(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        //获取当前参数
        UserLoginRequest paramRequest = getEntity(request, UserLoginRequest.class);
        if (Check.NuNObj(paramRequest)) {
            return new ResponseDto("参数异常");
        }
        paramRequest.setHeader(header);
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {

            DataTransferObject<String> dto =userService.login(paramRequest);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【登录】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }

    }


    /**
     * 退出
     * @author afi
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto logout(HttpServletRequest request, HttpServletResponse response) {
        Header header = getHeader(request);
        if (Check.NuNObj(header)) {
            return new ResponseDto("头信息为空");
        }
        String  token = getToken(request);
        if (Check.NuNStr(token)){
            return new ResponseDto("",DataTransferObject.SUCCESS);
        }
        //获取当前参数
        UserLogoutRequest paramRequest = new UserLogoutRequest();
        paramRequest.setToken(getToken(request));
        paramRequest.setHeader(header);
        LogUtil.info(LOGGER, "传入参数:{}", JsonEntityTransform.Object2Json(paramRequest));
        try {

            DataTransferObject<Void> dto =userService.logout(paramRequest);
            return dto.trans2Res();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【退出】错误,par:{}, e={}",JsonEntityTransform.Object2Json(paramRequest), e);
            return new ResponseDto("未知错误");
        }

    }




}
