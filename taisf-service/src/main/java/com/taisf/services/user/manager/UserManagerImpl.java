package com.taisf.services.user.manager;

import com.jk.framework.base.constant.YesNoEnum;
import com.jk.framework.base.exception.BusinessException;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.MD5Util;
import com.taisf.services.common.valenum.AccountStatusEnum;
import com.taisf.services.common.valenum.UserTypeEnum;
import com.taisf.services.enterprise.entity.EnterpriseEntity;
import com.taisf.services.enterprise.vo.EnterpriseAccountVO;
import com.taisf.services.ups.dao.EmployeeDao;
import com.taisf.services.user.dao.*;
import com.taisf.services.user.dto.AccountLogRequest;
import com.taisf.services.user.dto.UserAccountRequest;
import com.taisf.services.user.dto.UserMoneyRequest;
import com.taisf.services.user.entity.*;
import com.taisf.services.user.vo.AccountUserLogVO;
import com.taisf.services.user.vo.UserAccountVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>用户信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/29.
 * @version 1.0
 * @since 1.0
 */
@Service("user.userManagerImpl")
public class UserManagerImpl {

    @Resource(name = "user.userDao")
    private UserDao userDao;

    @Resource(name = "user.userAddressDao")
    private UserAddressDao userAddressDao;


    @Resource(name = "user.accountUserDao")
    private UserAccountDao userAccountDao;


    @Resource(name = "user.accountLogDao")
    private AccountLogDao accountLogDao;

    @Resource(name = "user.loginTokenDao")
    private LoginTokenDao loginTokenDao;

    @Resource(name="ups.employeeDao")
    private EmployeeDao employeeDao;



    /**
     * 获取供应商用户的充值记录
     * @author afi
     * @param userMoneyRequest
     * @return
     */
    public  List<AccountUserLogVO> rechargeMoneyLogAll(UserMoneyRequest userMoneyRequest){
        return accountLogDao.rechargeMoneyLogAll(userMoneyRequest);

    }

    /**
     * 获取供应商用户的充值记录
     * @author afi
     * @param userMoneyRequest
     * @return
     */
    public PagingResult<AccountUserLogVO> rechargeMoneyLogByPage(UserMoneyRequest userMoneyRequest){
        return accountLogDao.rechargeMoneyLogByPage(userMoneyRequest);
    }

    /**
     * 获取企业的余额的汇总
     * @author afi
     * @param enterpriseCodeList
     * @return
     */
    public List<EnterpriseAccountVO> getEnterpriseAccountByList(List<String> enterpriseCodeList){
        if (Check.NuNCollection(enterpriseCodeList)){
            return null;
        }
        return userAccountDao.getEnterpriseAccountByList(enterpriseCodeList);
    }


    /**
     * 获取企业的余额的汇总
     * @author afi
     * @param enterpriseCodeList
     * @return
     */
    public Map<String,EnterpriseAccountVO> getEnterpriseAccountMapByList(List<String> enterpriseCodeList){
        Map<String,EnterpriseAccountVO> map = new HashMap<>();
        List<EnterpriseAccountVO>  list =  this.getEnterpriseAccountByList(enterpriseCodeList);
        if (!Check.NuNCollection(list)){
            for (EnterpriseAccountVO entity : list) {
                map.put(entity.getEnterpriseCode(),entity);
            }
        }
        return map;
    }


    /**
     * 获取用户账户信息列表
     * @author afi
     * @param userIdList
     * @return
     */
    public List<UserAccountEntity> getUserAccountByList(List<String> userIdList){
        if (Check.NuNCollection(userIdList)){
            return null;
        }
        return userAccountDao.getUserAccountByList(userIdList);
    }


    /**
     * 获取用户账户信息列表
     * @author afi
     * @param userIdList
     * @return
     */
    public Map<String,UserAccountEntity> getUserAccountMapByList(List<String> userIdList){
        Map<String,UserAccountEntity> map = new HashMap<>();
        List<UserAccountEntity>  list =  this.getUserAccountByList(userIdList);
        if (!Check.NuNCollection(list)){
            for (UserAccountEntity entity : list) {
                map.put(entity.getUserId(),entity);
            }
        }
        return map;
    }


    /**
     * 获取当前的账户信息
     * @param userAccountRequest
     * @return
     */
    public PagingResult<UserAccountVO> getUserAccountPage(UserAccountRequest userAccountRequest){
        return userDao.getUserAccountPage(userAccountRequest);
    }



    /**
     * 根据用户entrpriseCode 查询当前的用户
     * @param entrpriseCode
     * @return
     */
    public UserEntity getOkUserByEntrpriseCodePhone(String entrpriseCode,String userPhone){
        if (Check.NuNStr(entrpriseCode)){
            return null;
        }
        return userDao.getOkUserByEntrpriseCodePhone(entrpriseCode,userPhone);
    }


    /**
     * 根据用户entrpriseCode 查询当前的用户
     * @param entrpriseCode
     * @return
     */
    public List<UserEntity> getOkUserByEntrpriseCode(String entrpriseCode){
        if (Check.NuNStr(entrpriseCode)){
            return null;
        }
        return userDao.getOkUserByEntrpriseCode(entrpriseCode);
    }

    /**
     * 获取当前的账户的操作记录
     * @author afi
     * @param accountLogRequest
     * @return
     */
    public PagingResult<AccountLogEntity> getIncomeLogByPage(AccountLogRequest accountLogRequest){
        return accountLogDao.getIncomeLogByPage(accountLogRequest);
    }


    /**
     * 获取当前的账户的操作记录
     * @author afi
     * @param accountLogRequest
     * @return
     */
    public PagingResult<AccountLogEntity> getAccountLogByPage(AccountLogRequest accountLogRequest){
        return accountLogDao.getAccountLogByPage(accountLogRequest);
    }

    /**
     * 保存登录信息
     * @author afi
     * @param loginTokenEntity
     * @return
     */
    public int saveLoginToken(LoginTokenEntity loginTokenEntity){
        return loginTokenDao.saveLoginToken(loginTokenEntity);
    }

    /**
     * 查询token信息
     * @param id
     * @return
     */
    public int deleteById(Integer id){
        return loginTokenDao.deleteById(id);
    }

    /**
     * 查询token信息
     * @param token
     * @return
     */
    public LoginTokenEntity getTokenByToken(String token){
        return loginTokenDao.getTokenByToken(token);
    }

    /**
     * 查询token信息
     * @param userId
     * @param deviceUuid
     * @return
     */
    public LoginTokenEntity getToken(String userId, String deviceUuid,int loginSource){
        return loginTokenDao.getToken(userId,deviceUuid,loginSource);
    }


    /**
     * 幂等获取企业用户信息
     * @author afi
     * @param enterpriseEntity
     * @return
     */
    public UserEntity fillAndGetEnterpriseUser(EnterpriseEntity enterpriseEntity){
        /**
         * 当前的账户信息是否存在
         */
        UserEntity has =  userDao.getUserByUid(enterpriseEntity.getEnterpriseCode());
        if (!Check.NuNObj(has)){
            if(has.getUserType() != UserTypeEnum.QIYE.getCode()){
                throw new BusinessException("企业编号和用户编号重复");
            }
            //当前用户存在,直接返回
            return has;
        }

        has = new UserEntity();
        has.setUserUid(enterpriseEntity.getEnterpriseCode());
        has.setUserPhone(enterpriseEntity.getEnterpriseCode());
        has.setUserType(UserTypeEnum.QIYE.getCode());
        has.setEnterpriseCode(enterpriseEntity.getEnterpriseCode());
        has.setEnterpriseName(enterpriseEntity.getEnterpriseName());
        userDao.add(has);
        return has;
    }







    /**
     * 获取当前的账户信息,如果当前账户信息不存在直接创建账户信息
     * @author afi
     * @param userId
     * @return
     */
    public UserAccountEntity fillAndGetAccountUser(String userId){
        /**
         * 当前的账户信息是否存在
         */
        UserAccountEntity has =  userAccountDao.getAccountUserByUid(userId);

        if (Check.NuNObj(has)){
            has = new UserAccountEntity();
            has.setUserId(userId);
            has.setAccountStatus(AccountStatusEnum.AVAILABLE.getCode());
            userAccountDao.saveAccountUser(has);
        }
        return has;
    }


    /**
     * 获取当前收货的信息
     * @author afi
     * @param fid
     * @return
     */
    public UserAddressEntity getUserAddressByFid(String fid){
        if(Check.NuNStr(fid)){
            return null;
        }
        return userAddressDao.getUserAddressByFid(fid);
    }

    /**
     * 根据用户id查询用户
     * @param id
     * @return
     */
    public UserEntity getUserById(Integer id){
        if (Check.NuNObj(id)){
            return null;
        }
        return userDao.getUserById(id);
    }


    /**
     * 根据code去支付
     * @param payCode
     * @return
     */
    public UserEntity getByPayCode(String payCode){
        if (Check.NuNObj(payCode)){
            return null;
        }
        return userDao.getByPayCode(payCode);
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/11/3
     * @description:根据手机号查询
     **/
    public UserEntity getByUserPhone(String userPhone){
        if (Check.NuNObj(userPhone)){
            return null;
        }
        return userDao.getByUserPhone(userPhone);
    }



    /**
     * 根据用户userid查询用户
     * @param userId
     * @return
     */
    public UserEntity getUserByUid(String userId){
        if (Check.NuNStr(userId)){
            return null;
        }
        return userDao.getUserByUid(userId);
    }


    /**
     * 根据用户userid查询用户
     * @param userId
     * @return
     */
    public UserEntity getUserByUid4Supply(String userId){
        if (Check.NuNStr(userId)){
            return null;
        }
        return userDao.getUserByUidAndType(userId,UserTypeEnum.SONGCAN.getCode());
    }


    /**
     * 根据用户payCode查询用户
     * @param payCode
     * @return
     */
    public UserEntity getUserByPayCode(String payCode) {
        if (Check.NuNStr(payCode)) {
            return null;
        }
        return userDao.getUserByPayCode(payCode);
    }


    /**
     * 根据用户电话查询用户
     * @param userPhone
     * @return
     */
    public UserEntity getUserByUserPhone(String userPhone,int userType){
        if (Check.NuNStr(userPhone)){
            return null;
        }
        return userDao.getUserByUserPhone(userPhone,userType);
    }



    /**
     * 激活用户信息
     * @param userId
     * @return
     */
    public int updateUser2Activity(String userId,String pwd){
        return userDao.updateUser2Activity(userId,pwd);
    }



    /**
     * 新增加用户
     * @param userEntity
     * @return
     */
    public int addUser(UserEntity userEntity){
        return userDao.add(userEntity);
    }


    /**
     * 更新用户
     * @param userEntity
     * @return
     */
    public int updateUser(UserEntity userEntity){
        if(!Check.NuNObj(userEntity.getUserPassword())){
            employeeDao.changePwd(userEntity.getUserUid(),userEntity.getUserPassword());
            userEntity.setUserPassword(MD5Util.MD5Encode(userEntity.getUserPassword()));
        }
        return userDao.updateUser(userEntity);
    }


    /**
     * 更新用户
     * @param userId
     * @param isAdmin
     * @return
     */
    public int updateUserAdmin(String userId,Integer isAdmin){
        return userDao.updateUserAdmin(userId,isAdmin);
    }


    /**
     * 更新用户的免密设置
     * @param userId
     * @param isPwd
     * @return
     */
    public int updateIsPwd(String userId,Integer isPwd){
        return userDao.updateIsPwd(userId,isPwd);
    }


    /**
     * 修改当前的支付密码
     * @author afi
     * @param userId
     * @param accountPassword
     * @return
     */
    public int updateAccountPasswordAndPwd(String userId,String accountPassword ,boolean isPwd){
        int num = userAccountDao.updateAccountPassword(userId,accountPassword);
        if (num > 0 && isPwd){
            userDao.updateIsPwd(userId, YesNoEnum.YES.getCode());
        }
        return num;
    }

    /**
     * 修改当前的支付密码
     * @author afi
     * @param userId
     * @param accountPassword
     * @return
     */
    public int updateAccountPassword(String userId,String accountPassword){
        return userAccountDao.updateAccountPassword(userId,accountPassword);
    }

    /**
     * 修改用户登录密码
     * @param userId
     * @param userPassword
     * @return
     */
    public int updateUserPwd(String userId,String userPassword){
        return userDao.updateUserPwd(userId,userPassword);
    }
    public void add(List<UserEntity> userEntityList){
        for (UserEntity userEntity : userEntityList) {
            if(!Check.NuNObj(userEntity.getUserPassword())){
                userEntity.setUserPassword(MD5Util.MD5Encode(userEntity.getUserPassword()));
            }else{
                userEntity.setUserPassword(MD5Util.MD5Encode(userEntity.getUserPhone()));
            }
            UserEntity byUserPhone = userDao.getByUserPhone(userEntity.getUserPhone());
            if (Check.NuNObj(byUserPhone)){
                userDao.add(userEntity);
            }
        }
    }

}
