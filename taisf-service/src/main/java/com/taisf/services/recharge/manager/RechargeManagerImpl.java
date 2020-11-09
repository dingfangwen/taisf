package com.taisf.services.recharge.manager;

import com.jk.framework.base.exception.BusinessException;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.UUIDGenerator;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.common.valenum.AccountTypeEnum;
import com.taisf.services.common.valenum.UserStatusEnum;
import com.taisf.services.enterprise.vo.EnterpriseRechargeStatsVO;
import com.taisf.services.enterprise.vo.SupRechargeStatsVO;
import com.taisf.services.order.dto.EnterpriseStatsRequest;
import com.taisf.services.order.dto.SupStatsRequest;
import com.taisf.services.recharge.dao.RechargeDao;
import com.taisf.services.recharge.dto.BalanceMoneyRequest;
import com.taisf.services.recharge.dto.ChargeHisRequest;
import com.taisf.services.recharge.entity.RechargeEntity;
import com.taisf.services.user.dao.AccountLogDao;
import com.taisf.services.user.dao.UserAccountDao;
import com.taisf.services.user.dao.UserDao;
import com.taisf.services.user.dto.UserAccounFillRequest;
import com.taisf.services.user.entity.AccountLogEntity;
import com.taisf.services.user.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>充值相关的实现</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/12.
 * @version 1.0
 * @since 1.0
 */
@Service("recharge.rechargeManagerImpl")
public class RechargeManagerImpl {

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(RechargeManagerImpl.class);



    @Resource(name = "recharge.rechargeDao")
    private RechargeDao rechargeDao;

    @Resource(name = "user.accountLogDao")
    private AccountLogDao accountLogDao;

    @Resource(name = "user.accountUserDao")
    private UserAccountDao userAccountDao;


    @Resource(name = "user.userDao")
    private UserDao userDao;


    /**
     * 获取企业充值统计信息
     * @author afi
     * @param request
     * @return
     */
    public List<SupRechargeStatsVO> getSupRechargeStats(SupStatsRequest request){
        return rechargeDao.getSupRechargeStats(request);
    }

    /**
     * 获取企业充值统计信息
     * @author afi
     * @param request
     * @return
     */
    public List<EnterpriseRechargeStatsVO> getEnterpriseRechargeStats(EnterpriseStatsRequest request){
        return rechargeDao.getEnterpriseRechargeStats(request);
    }


    /**
     * 分页查询充值记录
     * @author afi
     * @param chargeHisRequest
     * @return
     */
    public PagingResult<RechargeEntity> getRechargeByPage(ChargeHisRequest chargeHisRequest){
        return rechargeDao.getRechargeByPage(chargeHisRequest);
    }

    /**
     * 新增充值信息
     * @author afi
     * @param record
     * @return
     */
    public int saveRecharge(RechargeEntity record){
        int rst = rechargeDao.saveRecharge(record);
        if (rst == 1){
            this.fillUserAccount(record.getEnterpriseCode(),record.getPayMoney(),record.getRechargeSn());
        }
        return rst;
    }




    /**
     * 企业金额转到用户
     * @param list
     */
    public void fillUserAccountListByEnterprise(List<UserAccounFillRequest> list,String enterpriseCode){
        if(Check.NuNCollection(list)){
            return;
        }
        int total = 0;
        for (UserAccounFillRequest fillRequest : list) {
            this.fillUserAccount(fillRequest.getUserId(),fillRequest.getMoney(),fillRequest.getBizSn());
            total += fillRequest.getMoney();
        }

        //扣除企业月
        this.subEnterpriseAccount(enterpriseCode,-total, UUIDGenerator.hexUUID());
    }


    /**
     * 批量充值
     * @param enterpriseCode
     * @param allCost
     * @param listBalance
     */
    public void fillUserAccountListByEnterprise(String enterpriseCode,Integer allCost,List<BalanceMoneyRequest> listBalance){
        if (Check.NuNCollection(listBalance)){
            return;
        }
        for (BalanceMoneyRequest balanceMoneyRequest : listBalance) {
            LogUtil.error(logger,"批量将企业的钱充值给用户.企业:{} 参数:{}",enterpriseCode, JsonEntityTransform.Object2Json(balanceMoneyRequest));
            //充值用户
            fillUserAccount(balanceMoneyRequest.getUserUid(),balanceMoneyRequest.getMoneyPen(),UUIDGenerator.hexUUID());
        }
        //扣除企业月
        this.subEnterpriseAccount(enterpriseCode,-allCost, UUIDGenerator.hexUUID());
    }



    /**
     * 企业金额转到用户
     * @param enterpriseCode
     * @param userUid
     * @param money
     */
    public void fillUserAccountOneByEnterprise(String enterpriseCode,String userUid,int money){

        LogUtil.error(logger,"将企业的钱充值给用户.企业:{} 用户:{},金额:{}",enterpriseCode,userUid,money);

        //充值用户
        fillUserAccount(userUid,money,UUIDGenerator.hexUUID());

        //扣除企业月
        this.subEnterpriseAccount(enterpriseCode,-money, UUIDGenerator.hexUUID());
    }


    /**
     * 企业金额转到用户
     * @param enterpriseCode
     * @param userUid
     * @param money
     */
    public void forbiddenUserAccountOneByEnterprise(String enterpriseCode,String userUid,int money){

        //修改状态
        UserEntity userEntity = new UserEntity();
        userEntity.setUserUid(userUid);
        userEntity.setUserStatus(UserStatusEnum.FORBIDDEN.getCode());
        userDao.updateUser(userEntity);
        if (money <= 0){
            return;
        }
        //将账户钱转到企业
        forbiddenUserAccount(userUid,money,UUIDGenerator.hexUUID());

        //还原企业的金额
        this.rollBackEnterpriseAccount(enterpriseCode,money, UUIDGenerator.hexUUID());
    }



    /**
     * 减小
     * @param enterpriseCode
     * @param money
     * @param bizSn
     */
    private void subEnterpriseAccount(String enterpriseCode,int money,String bizSn){
        //消费当前的余额信息
        int num =  userAccountDao.changeUserBalance(enterpriseCode,money);
        if (num != 1){
            throw new BusinessException("更新用户金额失败");
        }

        //记录当前的消费记录
        AccountLogEntity log = new AccountLogEntity();
        log.setAccountType(AccountTypeEnum.CHANGE.getCode());
        log.setBizMoney(money);
        log.setBizSn(bizSn);
        log.setUserId(enterpriseCode);
        log.setTitle("企业余额分配到员工");
        accountLogDao.saveAccountLog(log);
    }


    /**
     * 回滚
     * @param enterpriseCode
     * @param money
     * @param bizSn
     */
    private void rollBackEnterpriseAccount(String enterpriseCode,int money,String bizSn){
        //消费当前的余额信息
        userAccountDao.changeUserBalance(enterpriseCode,money);
        //记录当前的消费记录
        AccountLogEntity log = new AccountLogEntity();
        log.setAccountType(AccountTypeEnum.CHANGE.getCode());
        log.setBizMoney(money);
        log.setBizSn(bizSn);
        log.setUserId(enterpriseCode);
        log.setTitle("员工禁用返还企业");
        accountLogDao.saveAccountLog(log);
    }




    /**
     * 冻结账号
     * @param userUid
     * @param money
     * @param bizSn
     */
    private void forbiddenUserAccount(String userUid,int money,String bizSn){
        //消费当前的余额信息
        userAccountDao.changeUserBalance(userUid,-money);
        //记录当前的消费记录
        AccountLogEntity log = new AccountLogEntity();
        log.setAccountType(AccountTypeEnum.CHANGE.getCode());
        log.setBizMoney(-money);
        log.setBizSn(bizSn);
        log.setUserId(userUid);
        log.setTitle("转到企业账户");
        accountLogDao.saveAccountLog(log);
    }


    /**
     * 充值
     * @param userUid
     * @param money
     * @param bizSn
     */
    private void fillUserAccount(String userUid,int money,String bizSn){
        //消费当前的余额信息
        int num = userAccountDao.changeUserBalance(userUid,money);
        if (num != 1){
            throw new BusinessException("更新用户金额失败");
        }
        //记录当前的消费记录
        AccountLogEntity log = new AccountLogEntity();
        log.setAccountType(AccountTypeEnum.FILL.getCode());
        log.setBizMoney(money);
        log.setBizSn(bizSn);
        log.setUserId(userUid);
        log.setTitle("商家充值");
        accountLogDao.saveAccountLog(log);
    }


    /**
     * 获取当前是否存在
     * @author afi
     * @param bizSn
     * @return
     */
    public AccountLogEntity getAccountLogByBizSn(String bizSn){
        return accountLogDao.getAccountLogByBizSn(bizSn);
    }

    /**
     * 充值
     * @param userUid
     * @param money
     * @param bizSn
     */
    public void refundByOrder(String userUid,int money,String bizSn){
        //消费当前的余额信息
        userAccountDao.changeUserBalance(userUid,money);
        //记录当前的消费记录
        AccountLogEntity log = new AccountLogEntity();
        log.setAccountType(AccountTypeEnum.REFUND.getCode());
        log.setBizMoney(money);
        log.setBizSn(bizSn);
        log.setUserId(userUid);
        log.setTitle("订单退款");
        accountLogDao.saveAccountLog(log);
    }



}
