package com.taisf.services.user.proxy;

import com.jk.framework.base.constant.YesNoEnum;
import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.head.Header;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.*;
import com.jk.framework.cache.redis.api.RedisOperations;
import com.jk.framework.cache.redis.constant.RedisConstant;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.common.constant.PathConstant;
import com.taisf.services.common.valenum.*;
import com.taisf.services.enterprise.entity.EnterpriseAddressEntity;
import com.taisf.services.enterprise.entity.EnterpriseEntity;
import com.taisf.services.enterprise.manager.EnterpriseManagerImpl;
import com.taisf.services.ups.dao.EmployeeDao;
import com.taisf.services.ups.entity.EmployeeEntity;
import com.taisf.services.recharge.manager.RechargeManagerImpl;
import com.taisf.services.user.api.UserService;
import com.taisf.services.user.dto.*;
import com.taisf.services.user.entity.AccountLogEntity;
import com.taisf.services.user.entity.LoginTokenEntity;
import com.taisf.services.user.entity.UserAccountEntity;
import com.taisf.services.user.entity.UserEntity;
import com.taisf.services.user.manager.UserManagerImpl;
import com.taisf.services.user.vo.AccountUserLogVO;
import com.taisf.services.user.vo.RegistInfoVO;
import com.taisf.services.user.vo.UserAccountVO;
import com.taisf.services.user.vo.UserModelVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * <p>用户中心接口实现</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/6.
 * @version 1.0
 * @since 1.0
 */
@Component("user.userServiceProxy")
public class UserServiceProxy implements UserService {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceProxy.class);

    @Resource(name = "user.userManagerImpl")
    private UserManagerImpl userManager;

    @Resource(name = "recharge.rechargeManagerImpl")
    private RechargeManagerImpl rechargeManager;

    @Resource(name = "user.userDao")
    private com.taisf.services.user.dao.UserDao userDao;

    @Resource(name = "user.accountUserDao")
    private com.taisf.services.user.dao.UserAccountDao userAccountDao;


    @Resource(name = "enterprise.enterpriseManagerImpl")
    private EnterpriseManagerImpl enterpriseManager;

    @Autowired
    private RedisOperations redisOperations;


    @Resource(name = "ups.employeeDao")
    private EmployeeDao employeeDao;

    @Autowired
    private PathConstant pathConstant;


    /**
     * 获取当前的账户信息
     *
     * @param userAccountRequest
     * @return
     */
    @Override
    public DataTransferObject<PagingResult<UserAccountVO>> getUserAccountPage(UserAccountRequest userAccountRequest) {
        DataTransferObject<PagingResult<UserAccountVO>> dto = new DataTransferObject<>();
        if (Check.NuNObj(userAccountRequest)) {
            userAccountRequest = new UserAccountRequest();
        }
        PagingResult<UserAccountVO> page = userManager.getUserAccountPage(userAccountRequest);
        dto.setData(page);
        return dto;

    }


    /**
     * 校验电话是否注册
     *
     * @param phone
     * @return
     */
    @Override
    public DataTransferObject<Void> checkRegist(String phone) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNStr(phone)) {
            dto.setErrorMsg("请输入正确的手机号");
            return dto;
        }
        //1. 验证手机号信息
        UserEntity userEntity = userManager.getUserByUserPhone(phone,UserTypeEnum.YONGHU.getCode());
        if (Check.NuNObj(userEntity)) {
            dto.setErrorMsg("该账户不存在");
            return dto;
        }
        checkUserStats(dto, userEntity);
        return dto;
    }

    /**
     * 校验用户状态
     *
     * @param dto
     * @param userEntity
     * @return
     */
    private void checkUserStats(DataTransferObject dto, UserEntity userEntity) {
        if (!dto.checkSuccess()) {
            return;
        }
        //2. 判断用户状态
        UserStatusEnum userStatusEnum = UserStatusEnum.getTypeByCode(userEntity.getUserStatus());
        if (Check.NuNObj(userStatusEnum)) {
            dto.setErrorMsg("异常的用户状态");
            return;
        }
        if (userStatusEnum.getCode() == UserStatusEnum.FORBIDDEN.getCode()) {
            dto.setErrorMsg("该帐户已注销");
            return;
        } else if (userStatusEnum.getCode() == UserStatusEnum.FREEZE.getCode()) {
            dto.setErrorMsg("该帐户已冻结");
            return;
        } else if (userStatusEnum.getCode() == UserStatusEnum.ACTIVITY.getCode()) {
            dto.setErrorMsg("该账户已注册");
            return;
        }

    }




    /**
     * 开放用户注册
     * @param userRegistRequest
     * @return
     */
    @Override
    public DataTransferObject<RegistInfoVO> openRegist(UserOpenRegistRequest userRegistRequest){
        DataTransferObject<RegistInfoVO> dto = new DataTransferObject<>();
        if (Check.NuNObj(userRegistRequest)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(userRegistRequest.getUserPhone())) {
            dto.setErrorMsg("请输入正确的账户");
            return dto;
        }
        if (Check.NuNStr(userRegistRequest.getUserName())) {
            dto.setErrorMsg("请输入姓名");
            return dto;
        }

        if (Check.NuNStr(userRegistRequest.getEnterpriseCode())) {
            dto.setErrorMsg("请输入企业信息");
            return dto;
        }

        if (Check.NuNStr(userRegistRequest.getPwd())) {
            dto.setErrorMsg("请输入密码");
            return dto;
        }
        //校验头信息
        this.checkHeaderMust(dto, userRegistRequest.getHeader());
        if (!dto.checkSuccess()) {
            return dto;
        }

        ApplicationCodeEnum applicationCodeEnum = ApplicationCodeEnum.getTypeByApplicationCode(userRegistRequest.getHeader().getApplicationCode());
        if (Check.NuNObj(applicationCodeEnum)) {
            dto.setErrorMsg("异常的应用名称");
            return dto;
        }
        if (applicationCodeEnum.getCode() != ApplicationCodeEnum.H5.getCode()){
            dto.setErrorMsg("异常的注册渠道");
            return dto;
        }
        //1. 验证账户信息
        UserEntity userEntity = userManager.getUserByUserPhone(userRegistRequest.getUserPhone(),UserTypeEnum.YONGHU.getCode());
        if (!Check.NuNObj(userEntity)) {
            dto.setErrorMsg("该账户已经注册过");
            return dto;
        }

        //2. 获取企业信息
        EnterpriseEntity infoVO = enterpriseManager.getEnterpriseByCode(userRegistRequest.getEnterpriseCode());
        if (Check.NuNObj(infoVO)) {
            dto.setErrorMsg("异常的企业信息");
            return dto;
        }
        if (infoVO.getIsOpen() == YesNoEnum.NO.getCode()){
            dto.setErrorMsg("当前企业未开发注册");
            return dto;
        }

        //3. 获取合作企业状态
        EnterpriseStatusEnum statusEnum = EnterpriseStatusEnum.getTypeByCode(infoVO.getEnterpriseStatus());
        if (Check.NuNObj(statusEnum)) {
            dto.setErrorMsg("异常的企业状态信息");
            return dto;
        }
        if (!statusEnum.checkOk()) {
            dto.setErrorMsg(statusEnum.getDes());
            return dto;
        }
        if (Check.NuNObj(infoVO.getTillTime())) {
            dto.setErrorMsg("异常的企业合作信息,请联系管理员");
            return dto;
        }
        if (infoVO.getTillTime().before(new Date())) {
            dto.setErrorMsg("该企业合作已过期");
            return dto;
        }

        //4. 新增用户
        userEntity =new UserEntity();
        userEntity.setUserUid(UUIDGenerator.hexUUID());
        userEntity.setUserName(userRegistRequest.getUserName());
        userEntity.setUserPhone(userRegistRequest.getUserPhone());
        userEntity.setEnterpriseCode(infoVO.getEnterpriseCode());
        userEntity.setEnterpriseName(infoVO.getEnterpriseName());
        userEntity.setUserPassword(userRegistRequest.getPwd());
        userEntity.setUserStatus(UserStatusEnum.ACTIVITY.getCode());
        userEntity.setBizCode(infoVO.getSupplierCode());

        userManager.addUser(userEntity);
        return dto;
    }

    @Override
    public DataTransferObject<RegistInfoVO> regist(UserRegistRequest userRegistRequest) {
        DataTransferObject<RegistInfoVO> dto = new DataTransferObject<>();
        if (Check.NuNObj(userRegistRequest)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(userRegistRequest.getUserPhone())) {
            dto.setErrorMsg("请输入正确的账户");
            return dto;
        }

        if (Check.NuNStr(userRegistRequest.getPwd())) {
            dto.setErrorMsg("请输入密码");
            return dto;
        }
        //校验头信息
        this.checkHeaderMust(dto, userRegistRequest.getHeader());
        if (!dto.checkSuccess()) {
            return dto;
        }

        ApplicationCodeEnum applicationCodeEnum = ApplicationCodeEnum.getTypeByApplicationCode(userRegistRequest.getHeader().getApplicationCode());
        if (Check.NuNObj(applicationCodeEnum)) {
            dto.setErrorMsg("异常的应用名称");
            return dto;
        }
        //1. 验证账户信息
        UserEntity userEntity = userManager.getUserByUserPhone(userRegistRequest.getUserPhone(),UserTypeEnum.YONGHU.getCode());
        if (Check.NuNObj(userEntity)) {
            dto.setErrorMsg("该账户不存在");
            return dto;
        }
        //2. 判断用户状态
        UserStatusEnum userStatusEnum = UserStatusEnum.getTypeByCode(userEntity.getUserStatus());
        if (Check.NuNObj(userStatusEnum)) {
            dto.setErrorMsg("异常的用户状态");
            return dto;
        }
        if (userStatusEnum.getCode() == UserStatusEnum.FORBIDDEN.getCode()) {
            dto.setErrorMsg("该帐户已注销");
            return dto;
        } else if (userStatusEnum.getCode() == UserStatusEnum.FREEZE.getCode()) {
            dto.setErrorMsg("该帐户已冻结");
            return dto;
        } else if (userStatusEnum.getCode() == UserStatusEnum.ACTIVITY.getCode()) {
            dto.setErrorMsg("该账户已注册");
            return dto;
        }

        //2. 获取企业信息
        EnterpriseEntity infoVO = enterpriseManager.getEnterpriseByCode(userEntity.getEnterpriseCode());
        if (Check.NuNObj(infoVO)) {
            dto.setErrorMsg("异常的企业信息");
            return dto;
        }

        //3. 获取合作企业状态
        EnterpriseStatusEnum statusEnum = EnterpriseStatusEnum.getTypeByCode(infoVO.getEnterpriseStatus());
        if (Check.NuNObj(statusEnum)) {
            dto.setErrorMsg("异常的企业状态信息");
            return dto;
        }
        if (!statusEnum.checkOk()) {
            dto.setErrorMsg(statusEnum.getDes());
            return dto;
        }
        if (Check.NuNObj(infoVO.getTillTime())) {
            dto.setErrorMsg("异常的企业合作信息,请联系管理员");
            return dto;
        }
        if (infoVO.getTillTime().before(new Date())) {
            dto.setErrorMsg("该企业合作已过期");
            return dto;
        }

        //4. 修改用户状态
        userManager.updateUser2Activity(userEntity.getUserUid(), userRegistRequest.getPwd());

        //5. 获取企业的信息并封装企业返回信息
        this.fillEnterpriseInfo(dto, userEntity.getEnterpriseCode(), userEntity);

        //6. 登录 获取token
        if (dto.checkSuccess()) {
            UserLoginCodeRequest userLoginCodeRequest = new UserLoginCodeRequest();
            userLoginCodeRequest.setHeader(userRegistRequest.getHeader());
            userLoginCodeRequest.setUserPhone(userRegistRequest.getUserPhone());
            DataTransferObject<String> loginDto = loginByCode(userLoginCodeRequest);
            if (loginDto.checkSuccess()) {
                dto.getData().setUserToken(loginDto.getData());
            } else {
                dto.setErrorMsg("注册成功,单登录失败,请重新登录");
            }
        }

        return dto;
    }

    /**
     * 拼装当前用户的激活信息
     *
     * @param dto
     * @param enterpriseCode
     * @param userEntity
     */
    private void fillEnterpriseInfo(DataTransferObject<RegistInfoVO> dto, String enterpriseCode, UserEntity userEntity) {
        if (Check.NuNObj(dto)) {
            return;
        }
        if (Check.NuNObj(userEntity)) {
            return;
        }

        if (!dto.checkSuccess()) {
            return;
        }
        RegistInfoVO vo = new RegistInfoVO();
        BeanUtils.copyProperties(userEntity, vo);

        UserRoleEnum userRoleEnum = UserRoleEnum.getTypeByCode(userEntity.getUserRole());
        if (Check.NuNObj(userRoleEnum)) {
            dto.setErrorMsg("异常的用户套餐信息");
            return;
        }
        vo.setUserRoleName(userRoleEnum.getName());
        List<EnterpriseAddressEntity> list = enterpriseManager.getEnterpriseAddressByCode(enterpriseCode);
        if (!Check.NuNCollection(list)) {
            for (EnterpriseAddressEntity enterpriseAddressEntity : list) {
                vo.getAddrList().add(enterpriseAddressEntity.getAddress());
            }
        }

        UserAccountEntity accountEntity = userManager.fillAndGetAccountUser(userEntity.getUserUid());
        if (Check.NuNObj(accountEntity)) {
            dto.setErrorMsg("异常的账户信息");
            return;
        }
        vo.setDrawBalance(accountEntity.getDrawBalance());
        //设置属性
        dto.setData(vo);
    }


    @Override
    public DataTransferObject<String> login(UserLoginRequest userLoginRequest) {
        DataTransferObject<String> dto = new DataTransferObject<>();
        if (Check.NuNObj(userLoginRequest)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(userLoginRequest.getUserPhone())) {
            dto.setErrorMsg("请输入正确的账户");
            return dto;
        }

        if (Check.NuNStr(userLoginRequest.getPwd())) {
            dto.setErrorMsg("请输入密码");
            return dto;
        }

        //校验头信息
        this.checkHeaderMust(dto, userLoginRequest.getHeader());
        if (!dto.checkSuccess()) {
            return dto;
        }

        ApplicationCodeEnum applicationCodeEnum = ApplicationCodeEnum.getTypeByApplicationCode(userLoginRequest.getHeader().getApplicationCode());
        if (Check.NuNObj(applicationCodeEnum)) {
            dto.setErrorMsg("异常的应用名称");
            return dto;
        }

        UserTypeEnum userType = applicationCodeEnum.getUserType();
        if (Check.NuNObj(userType)){
            dto.setErrorMsg("异常的用户类型");
            return dto;
        }
        //1. 验证账户信息
        UserEntity userEntity = userManager.getUserByUserPhone(userLoginRequest.getUserPhone(),userType.getCode());
        if (Check.NuNObj(userEntity)) {
            dto.setErrorMsg("该账户不存在");
            return dto;
        }
        if (!userEntity.getUserPassword().equals(userLoginRequest.getPwd())) {
            dto.setErrorMsg("请输入正确的密码");
            return dto;
        }
        //2. 判断用户状态
        UserStatusEnum userStatusEnum = UserStatusEnum.getTypeByCode(userEntity.getUserStatus());
        if (Check.NuNObj(userStatusEnum)) {
            dto.setErrorMsg("异常的用户状态");
            return dto;
        }
        if (userStatusEnum.getCode() == UserStatusEnum.AVAILABLE.getCode()) {
            dto.setErrorMsg("请先注册");
            return dto;
        } else if (userStatusEnum.getCode() == UserStatusEnum.FORBIDDEN.getCode()) {
            dto.setErrorMsg("该帐户已注销");
            return dto;
        } else if (userStatusEnum.getCode() == UserStatusEnum.FREEZE.getCode()) {
            dto.setErrorMsg("该帐户已冻结");
            return dto;
        }


        //处理当前的企业验证信息
        this.dealUserEntCheck(dto, userEntity,userType.getCode());

        //3. 获取token信息
        this.dealToken(userLoginRequest, dto, applicationCodeEnum, userEntity);
        return dto;
    }

    /**
     * 转化token信息
     *
     * @param header
     * @param loginTokenEntity
     * @return
     */
    private LoginTokenEntity transHeader2Token(Header header, LoginTokenEntity loginTokenEntity) {
        Date now = new Date();
        loginTokenEntity.setDeviceUuid(header.getDeviceUuid());
        loginTokenEntity.setCreateTime(now);
        loginTokenEntity.setExpireTime(DateUtil.jumpDate(now, 365));
        loginTokenEntity.setUserToken(UUIDGenerator.hexUUID());
        loginTokenEntity.setVersionCode(header.getVersionCode());
        ApplicationCodeEnum applicationCodeEnum = ApplicationCodeEnum.getTypeByApplicationCode(header.getApplicationCode());
        loginTokenEntity.setLoginSource(applicationCodeEnum.getCode());
        loginTokenEntity.setSourceType(header.getSource());
        return loginTokenEntity;
    }

    /**
     * 校验当前的head必填信息
     *
     * @param dto
     * @param header
     */
    private void checkHeaderMust(DataTransferObject dto, Header header) {
        if (Check.NuNObj(dto)) {
            return;
        }
        if (!dto.checkSuccess()) {
            return;
        }

        if (Check.NuNObj(header)) {
            dto.setErrorMsg("异常的头信息");
            return;
        }

        if (Check.NuNStr(header.getDeviceUuid())
                || Check.NuNStr(header.getApplicationCode())
                || Check.NuNStr(header.getVersionCode())
                || Check.NuNObj(header.getSource())) {
            dto.setErrorMsg("异常的头信息");
            return;
        }

        ApplicationCodeEnum applicationCodeEnum = ApplicationCodeEnum.getTypeByApplicationCode(header.getApplicationCode());
        if (Check.NuNObj(applicationCodeEnum)) {
            dto.setErrorMsg("异常的应用名称");
            return;
        }
    }

    /**
     * 用户登录 [验证码登录]
     *
     * @param userLoginRequest
     * @return
     */
    @Override
    public DataTransferObject<String> loginByCode(UserLoginCodeRequest userLoginRequest) {

        DataTransferObject<String> dto = new DataTransferObject<>();
        if (Check.NuNObj(userLoginRequest)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(userLoginRequest.getUserPhone())) {
            dto.setErrorMsg("请输入正确的账户");
            return dto;
        }

        //校验头信息
        this.checkHeaderMust(dto, userLoginRequest.getHeader());
        if (!dto.checkSuccess()) {
            return dto;
        }

        ApplicationCodeEnum applicationCodeEnum = ApplicationCodeEnum.getTypeByApplicationCode(userLoginRequest.getHeader().getApplicationCode());
        if (Check.NuNObj(applicationCodeEnum)) {
            dto.setErrorMsg("异常的应用名称");
            return dto;
        }

        UserTypeEnum userType = applicationCodeEnum.getUserType();
        if (Check.NuNObj(userType)){
            dto.setErrorMsg("异常的用户类型");
            return dto;
        }

        //1. 验证账户信息
        UserEntity userEntity = userManager.getUserByUserPhone(userLoginRequest.getUserPhone(),userType.getCode());
        if (Check.NuNObj(userEntity)) {
            dto.setErrorMsg("该账户不存在");
            return dto;
        }

        //2. 判断用户状态
        UserStatusEnum userStatusEnum = UserStatusEnum.getTypeByCode(userEntity.getUserStatus());
        if (Check.NuNObj(userStatusEnum)) {
            dto.setErrorMsg("异常的用户状态");
            return dto;
        }
        if (userStatusEnum.getCode() == UserStatusEnum.AVAILABLE.getCode()) {
            dto.setErrorMsg("请先注册");
            return dto;
        } else if (userStatusEnum.getCode() == UserStatusEnum.FORBIDDEN.getCode()) {
            dto.setErrorMsg("该帐户已注销");
            return dto;
        } else if (userStatusEnum.getCode() == UserStatusEnum.FREEZE.getCode()) {
            dto.setErrorMsg("该帐户已冻结");
            return dto;
        }

        //处理当前的企业验证信息
        this.dealUserEntCheck(dto, userEntity,userType.getCode());

        //处理token信息
        this.dealToken(userLoginRequest, dto, applicationCodeEnum, userEntity);

        return dto;

    }

    /**
     * 处理当前的企业信息
     * @param dto
     * @param userEntity
     * @return
     */
    private void dealUserEntCheck(DataTransferObject<String> dto, UserEntity userEntity,int userType) {

        if (userType != UserTypeEnum.YONGHU.getCode()){
            return ;
        }
        //2. 获取企业信息
        EnterpriseEntity infoVO = enterpriseManager.getEnterpriseByCode(userEntity.getEnterpriseCode());
        if (Check.NuNObj(infoVO)) {
            dto.setErrorMsg("异常的企业信息");
            return ;
        }

        //获取合作企业状态
        EnterpriseStatusEnum statusEnum = EnterpriseStatusEnum.getTypeByCode(infoVO.getEnterpriseStatus());
        if (Check.NuNObj(statusEnum)) {
            dto.setErrorMsg("异常的企业状态信息");
            return;
        }
        if (!statusEnum.checkOk()) {
            dto.setErrorMsg(statusEnum.getDes());
            return ;
        }
        if (Check.NuNObj(infoVO.getTillTime())) {
            dto.setErrorMsg("异常的企业合作信息,请联系管理员");
            return ;
        }
        if (infoVO.getTillTime().before(new Date())) {
            dto.setErrorMsg("该企业合作已过期");
            return ;
        }
    }

    /**
     * 处理用户的登录信息
     *
     * @param userLoginRequest
     * @param dto
     * @param applicationCodeEnum
     * @param userEntity
     */
    private void dealToken(UserLoginCodeRequest userLoginRequest, DataTransferObject<String> dto, ApplicationCodeEnum applicationCodeEnum, UserEntity userEntity) {
        if (!dto.checkSuccess()) {
            return;
        }
        //2. 获取token信息
        LoginTokenEntity token = userManager.getToken(userEntity.getUserUid(), userLoginRequest.getHeader().getDeviceUuid(), applicationCodeEnum.getCode());
        if (!Check.NuNObj(token)) {
            dto.setData(token.getUserToken());
        } else {

            token = new LoginTokenEntity();
            token.setUserId(userEntity.getUserUid());
            token = transHeader2Token(userLoginRequest.getHeader(), token);
            userManager.saveLoginToken(token);
            dto.setData(token.getUserToken());
        }
        if (Check.NuNObj(token)) {
            dto.setErrorMsg("获取token异常");
            return;


        }
        //设置redis信息
        String loginKey = RedisConstant.LOGIN_KEY + token.getUserToken();
        UserModelVO userModel = new UserModelVO();
        userModel.setDeviceUuid(token.getDeviceUuid());
        userModel.setUserToken(token.getUserToken());
        userModel.setUserId(token.getUserId());
        BeanUtils.copyProperties(userEntity, userModel);
        //设置缓存信息
        redisOperations.setex(loginKey, 360 * 24 * 60 * 60, JsonEntityTransform.Object2Json(userModel));
    }


    /**
     * 登出
     *
     * @param userLogoutRequest
     * @return
     */
    @Override
    public DataTransferObject<Void> logout(UserLogoutRequest userLogoutRequest) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(userLogoutRequest)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(userLogoutRequest.getToken())) {
            dto.setErrorMsg("参数异常");
            return dto;
        }

        //校验头信息
        this.checkHeaderMust(dto, userLogoutRequest.getHeader());
        if (!dto.checkSuccess()) {
            return dto;
        }
        ApplicationCodeEnum applicationCodeEnum = ApplicationCodeEnum.getTypeByApplicationCode(userLogoutRequest.getHeader().getApplicationCode());
        if (Check.NuNObj(applicationCodeEnum)) {
            dto.setErrorMsg("异常的应用名称");
            return dto;
        }
        //2. 获取token信息
        LoginTokenEntity token = userManager.getTokenByToken(userLogoutRequest.getToken());
        if (Check.NuNObj(token)) {
            //幂等返回
            return dto;
        }
        //3. 校验匹配
        this.checkHead2Token(dto, userLogoutRequest.getHeader(), token, applicationCodeEnum);
        if (dto.checkSuccess()) {
            userManager.deleteById(token.getId());
        }

        //设置redis信息
        String loginKey = RedisConstant.LOGIN_KEY + token.getUserToken();
        //设置缓存信息
        redisOperations.del(loginKey);
        return dto;
    }


    /**
     * 校验参数异常
     *
     * @param dto
     * @param header
     * @param token
     */
    private void checkHead2Token(DataTransferObject dto, Header header, LoginTokenEntity token, ApplicationCodeEnum applicationCodeEnum) {
        if (Check.NuNObj(dto)) {
            return;
        }
        if (Check.NuNObjs(header, token, applicationCodeEnum)) {
            dto.setErrorMsg("参数异常");
            return;
        }
        if (!header.getDeviceUuid().equals(token.getDeviceUuid())) {
            dto.setErrorMsg("退出失败,参数错误");
            return;
        }
        if (applicationCodeEnum.getCode() != token.getLoginSource()) {
            dto.setErrorMsg("退出失败,参数错误");
            return;
        }
    }


    /**
     * 入账记录
     *
     * @param accountLogRequest
     * @return
     * @author afi
     */
    @Override
    public DataTransferObject<PagingResult<AccountLogEntity>> inconmeLog(AccountLogRequest accountLogRequest) {
        DataTransferObject<PagingResult<AccountLogEntity>> dto = new DataTransferObject<>();
        if (Check.NuNObj(accountLogRequest)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(accountLogRequest.getUserId())) {
            dto.setErrorMsg("参数异常");
            return dto;
        }

        //获取当前的信息
        PagingResult<AccountLogEntity> page = userManager.getIncomeLogByPage(accountLogRequest);
        if (page == null) {
            page = new PagingResult();
        }

        dto.setData(page);
        return dto;
    }

    /**
     * 获取所有查询条件
     * @author afi
     * @param userMoneyRequest
     * @return
     */
    @Override
    public DataTransferObject<List<AccountUserLogVO>> rechargeMoneyLogAll(UserMoneyRequest userMoneyRequest){

        DataTransferObject<List<AccountUserLogVO>> dto = new DataTransferObject<>();
        if (Check.NuNObj(userMoneyRequest)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(userMoneyRequest.getSupplierCode())) {
            dto.setErrorMsg("参数异常");
            return dto;
        }

        //获取当前的信息
        List<AccountUserLogVO> all = userManager.rechargeMoneyLogAll(userMoneyRequest);
        dto.setData(all);
        return dto;


    }

    @Override
    public DataTransferObject<PagingResult<AccountUserLogVO>> rechargeMoneyLog(UserMoneyRequest userMoneyRequest) {
        DataTransferObject<PagingResult<AccountUserLogVO>> dto = new DataTransferObject<>();
        if (Check.NuNObj(userMoneyRequest)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(userMoneyRequest.getSupplierCode())) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        //获取当前的信息
        PagingResult<AccountUserLogVO> page = userManager.rechargeMoneyLogByPage(userMoneyRequest);
        if (page == null) {
            page = new PagingResult();
        }

        dto.setData(page);
        return dto;
    }


    /**
     * 充值记录
     *
     * @param accountLogRequest
     * @return
     * @author afi
     */
    @Override
    public DataTransferObject<PagingResult<AccountLogEntity>> rechargeLog(AccountLogRequest accountLogRequest) {

        DataTransferObject<PagingResult<AccountLogEntity>> dto = new DataTransferObject<>();
        if (Check.NuNObj(accountLogRequest)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(accountLogRequest.getUserId())) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        accountLogRequest.setAccountType(AccountTypeEnum.FILL.getCode());
        //获取当前的信息
        PagingResult<AccountLogEntity> page = userManager.getAccountLogByPage(accountLogRequest);
        if (page == null) {
            page = new PagingResult();
        }
        dto.setData(page);
        return dto;
    }


    /**
     * 关闭免密服务
     * @param userId
     * @return
     */
    @Override
    public DataTransferObject<Void> closeIsPwd(String userId){
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(userId)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        userManager.updateIsPwd(userId, YesNoEnum.NO.getCode());
        return dto;
    }


    /**
     * 开通免密服务
     * @param userId
     * @return
     */
    @Override
    public DataTransferObject<Void> openIsPwd(String userId,String accountPwd){
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNStr(userId)|| Check.NuNStr(accountPwd)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        //1. 验证账户信息
        UserEntity userEntity = userManager.getUserByUid(userId);
        if (Check.NuNObj(userEntity)) {
            dto.setErrorMsg("当前用户不存在");
            return dto;
        }
        //幂等创建当前的用户信息
        UserAccountEntity userAccountEntity = userManager.fillAndGetAccountUser(userId);
        if (Check.NuNObj(userAccountEntity)) {
            dto.setErrorMsg("获取用户账户信息失败");
            return dto;
        }
        if (!ValueUtil.getStrValue(userAccountEntity.getAccountPassword()).equals(accountPwd)){
            dto.setErrorMsg("密码错误");
            return dto;
        }
        userManager.updateIsPwd(userId, YesNoEnum.YES.getCode());
        return dto;
    }



    /**
     * 修改支付密码,并设置免密
     * @param userId
     * @param accountPassword
     * @return
     */
    @Override
    public DataTransferObject<Void> updateAccountPasswordAndPwd(String userId,String accountPassword ,boolean isPwd){
        LogUtil.error(LOGGER, "【updateAccountPasswordAndPwd】userId:{}, accountPassword{},isPwd:{}" ,userId,accountPassword,isPwd);
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(userId)
                || Check.NuNObj(accountPassword)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        userManager.updateAccountPasswordAndPwd(userId, accountPassword,isPwd);
        return dto;
    }

    /**
     * 修改支付密码
     *
     * @param userId
     * @param accountPassword
     * @return
     */
    @Override
    public DataTransferObject<Void> updateAccountPassword(String userId, String accountPassword) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(userId)
                || Check.NuNObj(accountPassword)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        userManager.updateAccountPassword(userId, accountPassword);
        return dto;
    }


    /**
     * 修改登录密码
     *
     * @param userId
     * @param userPassword
     * @return
     */
    @Override
    public DataTransferObject<Void> updateUserPwd(String userId, String userPassword, String oldUserPassword) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(userId)
                || Check.NuNStr(userPassword)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        UserEntity has = userManager.getUserByUid(userId);
        if (Check.NuNObj(has)) {
            dto.setErrorMsg("当前用户不存在");
            return dto;
        }
        if (!ValueUtil.getStrValue(has.getUserPassword()).equals(ValueUtil.getStrValue(oldUserPassword))) {
            dto.setErrorMsg("原始密码错误");
            return dto;
        }
        userManager.updateUserPwd(userId, userPassword);
        return dto;
    }

    /**
     * 销售列表
     *
     * @param request
     * @return
     */
    @Override
    public DataTransferObject<PagingResult<UserEntity>> pageKnightListUser(UserRequest request) {
        DataTransferObject<PagingResult<UserEntity>> dto = new DataTransferObject<>();
        PagingResult<UserEntity> userEntityPagingResult = userDao.pageKnightListUser(request);
        dto.setData(userEntityPagingResult);
        return dto;
    }


    /**
     * @author:zhangzhengguang
     * @date:2017/10/14
     * @description:销售管理列表
     **/
    @Override
    public DataTransferObject<PagingResult<UserEntity>> pageListUser(UserRequest request) {
        DataTransferObject<PagingResult<UserEntity>> dto = new DataTransferObject<>();
        PagingResult<UserEntity> userEntityPagingResult = userDao.pageListUser(request);
        dto.setData(userEntityPagingResult);
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/16
     * @description:企业员工管理列表
     **/
    @Override
    public DataTransferObject<PagingResult<UserEntity>> pageListCompanyUser(UserRequest request) {
        DataTransferObject<PagingResult<UserEntity>> dto = new DataTransferObject<>();
        PagingResult<UserEntity> userEntityPagingResult = userDao.pageListCompanyUser(request);
        dto.setData(userEntityPagingResult);
        return dto;
    }

    @Override
    public DataTransferObject<List<UserEntity> > findAllCompanyUser(UserRequest request) {
        DataTransferObject<List<UserEntity> > dto = new DataTransferObject<>();
        List<UserEntity> list = userDao.findAllCompanyUser(request);
        dto.setData(list);
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/14
     * @description:销售管理列表
     **/
    @Override
    public DataTransferObject<UserEntity> getUserById(Integer id) {
        DataTransferObject<UserEntity> dto = new DataTransferObject<>();
        UserEntity userEntity = userDao.getUserById(id);
        dto.setData(userEntity);
        return dto;
    }


    /**
     * @author:zhangzhengguang
     * @date:2017/10/14
     * @description:修改员工信息
     **/
    @Override
    public DataTransferObject<Void> updateUser(UserEntity userEntity) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        userManager.updateUser(userEntity);
        return dto;
    }

    /**
     * @author:afi
     * @date:2017/10/14
     * @description:修改管理员
     **/
    @Override
    public DataTransferObject<Void> updateUserAdmin(String userId, Integer isAdmin) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        userManager.updateUserAdmin(userId, isAdmin);
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/14
     * @description:修改员工信息
     **/
    @Override
    public DataTransferObject<List<UserEntity>> getUserByType(Integer type) {
        DataTransferObject<List<UserEntity>> dto = new DataTransferObject<>();
        List<UserEntity> userEntityList = userDao.getUserByType(type);
        dto.setData(userEntityList);
        return dto;
    }

    /**
     * 添加用户信息
     * @author afi
     * @param userEntity
     */
    @Override
    public void saveUserOnly(UserEntity userEntity){
        if(!Check.NuNObj(userEntity.getUserPassword())){
            userEntity.setUserPassword(MD5Util.MD5Encode(userEntity.getUserPassword()));
        }else{
            userEntity.setUserPassword(MD5Util.MD5Encode(userEntity.getUserPhone()));
        }
        userDao.add(userEntity);
    }

    @Override
    public void saveBatchUser(List<UserEntity> userEntityList){
        userManager.add(userEntityList);
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/14
     * @description:修改员工信息
     **/
    @Override
    public void saveUser(UserEntity userEntity) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        if(!Check.NuNObj(userEntity.getUserPassword())){
            userEntity.setUserPassword(MD5Util.MD5Encode(userEntity.getUserPassword()));
            employeeEntity.setUserPwd(userEntity.getUserPassword());
        }else{
            userEntity.setUserPassword(MD5Util.MD5Encode(userEntity.getUserPhone()));
            employeeEntity.setUserPwd(userEntity.getUserPhone());
        }

        userDao.add(userEntity);
        employeeEntity.setUserId(userEntity.getUserUid());
        employeeEntity.setEmpName(userEntity.getUserName());

        employeeEntity.setEmpValid(1);
        employeeEntity.setUserRole(2);
        employeeEntity.setEmpValid(0);
        employeeEntity.setEmpMobile(userEntity.getUserPhone());
        employeeEntity.setEmpMail(userEntity.getEmpMail());
        employeeEntity.setEmpBiz(userEntity.getBizCode());
        employeeDao.insertEmployeeSysc(employeeEntity);
    }


    /**
     * 禁用员工
     *
     * @param userUid
     * @return
     */
    @Override
    public DataTransferObject<Void> forbiddenUser(String userUid) {
        DataTransferObject dto = new DataTransferObject();
        if (Check.NuNStr(userUid)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }

        //1. 验证账户信息
        UserEntity userEntity = userManager.getUserByUid(userUid);
        if (Check.NuNObj(userEntity)) {
            dto.setErrorMsg("当前用户不存在");
            return dto;
        }

        //2. 判断用户状态
        UserStatusEnum userStatusEnum = UserStatusEnum.getTypeByCode(userEntity.getUserStatus());
        if (Check.NuNObj(userStatusEnum)) {
            dto.setErrorMsg("异常的用户状态");
            return dto;
        }
        if (userStatusEnum.getCode() == UserStatusEnum.FORBIDDEN.getCode()) {
            return dto;
        }

        UserAccountEntity has = userManager.fillAndGetAccountUser(userUid);
        if (Check.NuNObj(has)) {
            dto.setErrorMsg("创建用户账号失败");
            return dto;
        }


        //2. 获取企业信息
        EnterpriseEntity infoVO = enterpriseManager.getEnterpriseByCode(userEntity.getEnterpriseCode());
        if (Check.NuNObj(infoVO)) {
            dto.setErrorMsg("异常的企业信息");
            return dto;
        }

        UserAccountEntity userAccountEntity = userManager.fillAndGetAccountUser(infoVO.getEnterpriseCode());
        if (Check.NuNObj(userAccountEntity)) {
            dto.setErrorMsg("创建企业账号信息");
            return dto;
        }
        rechargeManager.forbiddenUserAccountOneByEnterprise(infoVO.getEnterpriseCode(), userUid, has.getDrawBalance());
        return dto;
    }


    /**
     * @author:zhangzhengguang
     * @date:2017/10/16
     * @description:修改账户信息
     **/
    @Override
    public void updateAccountUser(UserAccountEntity accountUserEntity) {
        userAccountDao.updateAccountUser(accountUserEntity);
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/20
     * @description:生成二维码
     **/
    @Override
    public DataTransferObject<String> getQRcode(String uid) {
        DataTransferObject<String> dto = new DataTransferObject<>();
        if (Check.NuNStr(uid)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        String dbPath = null;
        try {
            //1.根据uid查询user表
            UserEntity userEntity = userDao.getUserByUid(uid);
            if (Check.NuNObj(userEntity)) {
                dto.setErrorMsg("当前用户不存在");
                return dto;
            }
            //2.判断二维码是否为空
            if (Check.NuNObjs(userEntity, userEntity.getQrCode())) {
                dbPath = File.separator + "card" + File.separator;
                File dest = new File(pathConstant.FILE_PATH + dbPath);
                if (!dest.exists()) {
                    dest.mkdirs();
                }
                //1.生成二维码 并上传
                dbPath += uid + ".jpg";
                String fullPath = pathConstant.FILE_PATH + dbPath;
                BufferedImage image = QRCodeUtils.createImage(uid, false);
                ImageIO.write(image, "jpg", new File(fullPath));
                //2.修改user二维码路径
                userDao.updateUserQrCode(uid, dbPath);
            } else {
                //4.不为空,返回路径
                dbPath = userEntity.getQrCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbPath = pathConstant.PIC_URL + dbPath;
        dto.setData(dbPath);
        return dto;
    }

    @Override
    public DataTransferObject<UserEntity> getUserByUid(String userId) {
        if (Check.NuNStr(userId)) {
            return null;
        }
        DataTransferObject<UserEntity> dto = new DataTransferObject<>();
        try {
            UserEntity userEntity = userDao.getUserByUid(userId);
            dto.setData(userEntity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【根据用户userid查询用户异常】error:{}, param{}", e,userId);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("根据用户userid查询用户异常");
            return dto;
        }
        return dto;
    }


}
