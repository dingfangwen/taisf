package com.taisf.services.recharge.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.*;
import com.taisf.services.common.valenum.UserRoleEnum;
import com.taisf.services.enterprise.entity.EnterpriseConfigEntity;
import com.taisf.services.enterprise.manager.EnterpriseManagerImpl;
import com.taisf.services.enterprise.vo.EnterpriseInfoVO;
import com.taisf.services.enterprise.vo.EnterpriseOrderStatsVO;
import com.taisf.services.enterprise.vo.EnterpriseRechargeStatsVO;
import com.taisf.services.enterprise.vo.SupRechargeStatsVO;
import com.taisf.services.order.dto.CartCleanRequest;
import com.taisf.services.order.dto.EnterpriseStatsRequest;
import com.taisf.services.order.dto.SupStatsRequest;
import com.taisf.services.pay.manager.RechargeOrderManagerImpl;
import com.taisf.services.recharge.api.RechargeService;
import com.taisf.services.recharge.dto.*;
import com.taisf.services.recharge.entity.RechargeEntity;
import com.taisf.services.recharge.manager.RechargeManagerImpl;
import com.taisf.services.recharge.vo.EnterpriseStatsNumber;
import com.taisf.services.supplier.manager.SupplierManagerImpl;
import com.taisf.services.user.dto.UserAccounFillRequest;
import com.taisf.services.user.entity.UserAccountEntity;
import com.taisf.services.user.entity.UserEntity;
import com.taisf.services.user.manager.UserManagerImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>充值相关</p>
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
@Component("recharge.rechargeServiceProxy")
public class RechargeServiceProxy implements RechargeService {


    @Resource(name = "enterprise.enterpriseManagerImpl")
    private EnterpriseManagerImpl enterpriseManager;


    @Resource(name = "user.userManagerImpl")
    private UserManagerImpl userManager;


    @Resource(name = "recharge.rechargeManagerImpl")
    private RechargeManagerImpl rechargeManager;

    @Resource(name = "pay.rechargeOrderManagerImpl")
    private RechargeOrderManagerImpl rechargeOrderManager;


    /**
     * 获取企业充值统计信息
     * @author afi
     * @param request
     * @return
     */
    @Override
    public Map<String,SupRechargeStatsVO> getSupRechargeStatsMap(SupStatsRequest request){
        Map<String,SupRechargeStatsVO> map = new HashMap<>();
        if (Check.NuNObj(request)){
            return map;
        }
        List<SupRechargeStatsVO> supRechargeStats = rechargeManager.getSupRechargeStats(request);
        if (!Check.NuNCollection(supRechargeStats)){
            for (SupRechargeStatsVO supRechargeStat : supRechargeStats) {
                map.put(supRechargeStat.getSupplierCode(),supRechargeStat);
            }
        }
        return map;
    }

    /**
     * 获取企业充值的统计信息
     * @author afi
     * @param request
     * @return
     */
    @Override
    public DataTransferObject<List<EnterpriseRechargeStatsVO>> getSelfRechargeStats(EnterpriseStatsRequest request){
        DataTransferObject<List<EnterpriseRechargeStatsVO>> dto = new DataTransferObject<>();
        if (Check.NuNObj(request)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        List<EnterpriseRechargeStatsVO> list = rechargeOrderManager.getSelfRechargeStats(request);
        dto.setData(list);
        return dto;
    }



    /**
     * 获取企业充值的统计信息
     * @author afi
     * @param request
     * @return
     */
    @Override
    public DataTransferObject<List<EnterpriseRechargeStatsVO>> getEnterpriseRechargeStats(EnterpriseStatsRequest request){
        DataTransferObject<List<EnterpriseRechargeStatsVO>> dto = new DataTransferObject<>();
        if (Check.NuNObj(request)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        List<EnterpriseRechargeStatsVO> list = rechargeManager.getEnterpriseRechargeStats(request);
        dto.setData(list);
        return dto;
    }


    /**
     * 校验当前的企业状态
     * @param dto
     * @param infoVO
     */
    private void checkEnterpriseInfo(DataTransferObject dto,EnterpriseInfoVO infoVO){
        if (Check.NuNObj(dto)){
            return;
        }
        if (!dto.checkSuccess()){
            return;
        }

        if (Check.NuNObj(infoVO)){
            dto.setErrorMsg("异常的企业信息");
            return ;
        }
        if (Check.NuNObj(infoVO.getEnterpriseEntity().getTillTime())){
            dto.setErrorMsg("异常的企业截止时间");
            return;
        }
        if (infoVO.getEnterpriseEntity().getTillTime().before(new Date())){
            dto.setErrorMsg("加盟时间已经失效,请联系企业管理人员");
            return;
        }
        EnterpriseConfigEntity config =infoVO.getEnterpriseConfigEntity();
        if(Check.NuNObj(config)){
            dto.setErrorMsg("异常的企业配置信息");
            return;
        }
    }


    /**
     * 分页查询充值记录
     * @author afi
     * @param chargeHisRequest
     * @return
     */
    @Override
    public DataTransferObject<PagingResult<RechargeEntity>> getRechargeByPage(ChargeHisRequest chargeHisRequest){
        DataTransferObject<PagingResult<RechargeEntity>> dto = new DataTransferObject<>();
        if (Check.NuNObj(chargeHisRequest)){
            chargeHisRequest = new ChargeHisRequest();
        }
        PagingResult<RechargeEntity> page = rechargeManager.getRechargeByPage(chargeHisRequest);
        dto.setData(page);
        return dto;
    }


    /**
     * 获取企业下的员工数量和金额
     * @author afi
     * @param enterpriseCode
     * @return
     */
    @Override
    public DataTransferObject<EnterpriseStatsNumber>  getEnterpriseStatsNumber(String enterpriseCode){

        DataTransferObject<EnterpriseStatsNumber> dto = new DataTransferObject<>();
        if (Check.NuNStr(enterpriseCode)){
            dto.setErrorMsg("参数异常");
            return dto;
        }
        EnterpriseInfoVO infoVO = enterpriseManager.getEnterpriseInfoByCode(enterpriseCode);
        //校验当前的企业状态
        this.checkEnterpriseInfo(dto,infoVO);
        if (!dto.checkSuccess()){
            return dto;
        }
        //当前企业的限制信息
        EnterpriseStatsNumber number = new EnterpriseStatsNumber();
        dto.setData(number);
        // 获取企业下的所有用户
        List<UserEntity> all =userManager.getOkUserByEntrpriseCode(enterpriseCode);
        if (Check.NuNCollection(all)){
            return dto;
        }
        UserEntity user = userManager.fillAndGetEnterpriseUser(infoVO.getEnterpriseEntity());
        if (Check.NuNObj(user)){
            dto.setErrorMsg("创建企业账号信息");
            return dto;
        }

        UserAccountEntity userAccountEntity =userManager.fillAndGetAccountUser(enterpriseCode);
        if (Check.NuNObj(userAccountEntity)){
            dto.setErrorMsg("创建企业账号信息");
            return dto;
        }
        //设置余额
        number.setDrawBalance(ValueUtil.getintValue(userAccountEntity.getDrawBalance()));

        List<UserEntity>  bossList = new ArrayList<>();
        List<UserEntity>  empList = new ArrayList<>();
        if (!Check.NuNCollection(all)){
            for (UserEntity userEntity : all) {
                UserRoleEnum userRoleEnum = UserRoleEnum.getTypeByCode(userEntity.getUserRole());
                if (Check.NuNObj(userRoleEnum)){
                    userRoleEnum = UserRoleEnum.USER;
                }
                if (userRoleEnum.getCode() == UserRoleEnum.BOSS.getCode()){
                    bossList.add(userEntity);
                }else {
                    empList.add(userEntity);
                }
            }
        }
        number.setBossNum(bossList.size());
        number.setEmpNum(empList.size());
        return dto;
    }


    /**
     * 校验当前的逻辑
     * @param dto
     * @param listBalance
     */
    private Integer balanceMoneyListCheck(DataTransferObject dto,List<BalanceMoneyRequest> listBalance, String enterpriseCode){
        Integer allPrice = 0;
        if (!dto.checkSuccess()){
            return allPrice;
        }
        if (Check.NuNCollection(listBalance)){
            return allPrice;
        }
        EnterpriseInfoVO infoVO = enterpriseManager.getEnterpriseInfoByCode(enterpriseCode);
        //校验当前的企业状态
        this.checkEnterpriseInfo(dto,infoVO);
        if (!dto.checkSuccess()){
            return allPrice;
        }
        UserAccountEntity userAccountEntity =userManager.fillAndGetAccountUser(enterpriseCode);
        if (Check.NuNObj(userAccountEntity)){
            dto.setErrorMsg("创建企业账号信息");
            return allPrice;
        }

        //获取当前的不存在的用户
        List<String> listNo = new ArrayList<>();

        for (BalanceMoneyRequest request : listBalance) {
            if (Check.NuNStr(request.getUserPhone())){
                dto.setErrorMsg("请填写用户手机号");
                return allPrice;
            }
            if (Check.NuNObj(request.getMoneyYuan())){
                dto.setErrorMsg("异常的金额");
                return allPrice;
            }
            if (request.getMoneyYuan() <= 0){
                dto.setErrorMsg("异常的金额");
                return allPrice;
            }
            // 获取企业下的所有用户
            UserEntity has =userManager.getOkUserByEntrpriseCodePhone(enterpriseCode,request.getUserPhone());
            if (Check.NuNObj(has)){
                listNo.add(request.getUserPhone());
            }else {
                //幂等创建当前的账户信息
                userManager.fillAndGetAccountUser(has.getUserUid());
                request.setUserUid(has.getUserUid());
            }
            Double moneyPen = BigDecimalUtil.mul(request.getMoneyYuan(),100);

            //转化当前的分的逻辑
            request.setMoneyPen(moneyPen.intValue());
            //累加 金额
            allPrice = allPrice + moneyPen.intValue();
        }
        if (allPrice > ValueUtil.getintValue(userAccountEntity.getDrawBalance())){
            dto.setErrorMsg("当前企业余额不足,请确定余额信息");
            return allPrice;
        }
        if (!Check.NuNCollection(listNo)){
            dto.setErrorMsg("当前患者未注册:"+ JsonEntityTransform.Object2Json(listNo));
            return allPrice;
        }
        return allPrice;
    }





    /**
     * 批量分配
     * @param listBalance
     * @return
     */
    @Override
    public  DataTransferObject<Void> balanceMoneyList(List<BalanceMoneyRequest> listBalance, String enterpriseCode){
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(listBalance)){
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(enterpriseCode)){
            dto.setErrorMsg("企业code异常");
            return dto;
        }
        // 校验当前的逻辑
        Integer allCost = this.balanceMoneyListCheck(dto, listBalance, enterpriseCode);
        if (!dto.checkSuccess()){
            return dto;
        }
        // 直接填充信息
        rechargeManager.fillUserAccountListByEnterprise(enterpriseCode,allCost,listBalance);
        return dto;
    }


    /**
     * 分配金额-对个人
     * @author afi
     * @param request
     * @return
     */
    @Override
    public  DataTransferObject<Void> balanceMoneyOne(BalanceMoneyOneRequest request){
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(request)){
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(request.getEnterpriseCode())){
            dto.setErrorMsg("企业code异常");
            return dto;
        }

        if (Check.NuNStr(request.getUserPhone())){
            dto.setErrorMsg("请填写用户手机号");
            return dto;
        }
        if (Check.NuNObj(request.getMoneyYuan())){
            dto.setErrorMsg("异常的金额");
            return dto;
        }
        if (request.getMoneyYuan() <= 0){
            dto.setErrorMsg("异常的金额");
            return dto;
        }

        EnterpriseInfoVO infoVO = enterpriseManager.getEnterpriseInfoByCode(request.getEnterpriseCode());
        //校验当前的企业状态
        this.checkEnterpriseInfo(dto,infoVO);
        if (!dto.checkSuccess()){
            return dto;
        }
        UserAccountEntity userAccountEntity =userManager.fillAndGetAccountUser(request.getEnterpriseCode());
        if (Check.NuNObj(userAccountEntity)){
            dto.setErrorMsg("创建企业账号信息");
            return dto;
        }

        // 获取企业下的所有用户
        List<UserEntity> all =userManager.getOkUserByEntrpriseCode(request.getEnterpriseCode());
        if (Check.NuNCollection(all)){
            return dto;
        }
        UserEntity has = null;
        for (UserEntity userEntity : all) {
            if (request.getUserPhone().equals(userEntity.getUserPhone())){
                has = userEntity;
                break;
            }
        }
        if (Check.NuNObj(has)){
            dto.setErrorMsg("当前用户不属于当前企业");
            return dto;
        }
        Double moneyPen = BigDecimalUtil.mul(request.getMoneyYuan(),100);
        if (moneyPen.intValue() > ValueUtil.getintValue(userAccountEntity.getDrawBalance())){
            dto.setErrorMsg("当前余额不足,请确定余额信息");
            return dto;
        }
        //幂等创建当前的账户信息
        userManager.fillAndGetAccountUser(has.getUserUid());

        rechargeManager.fillUserAccountOneByEnterprise(request.getEnterpriseCode(),has.getUserUid(),moneyPen.intValue());
        return dto;
    }





    /**
     * 分配金额-平均
     * @author afi
     * @param request
     * @return
     */
    @Override
    public  DataTransferObject<Void> balanceMoneyAvg(BalanceMoneyAvgRequest request){
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(request)){
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(request.getEnterpriseCode())){
            dto.setErrorMsg("企业code异常");
            return dto;
        }

        if (Check.NuNObjs(request.getBossNum(),request.getBossMoneyYuan(),request.getEmpNum(),request.getEmpMoneyYuan())){
            dto.setErrorMsg("参数异常");
            return dto;
        }

        EnterpriseInfoVO infoVO = enterpriseManager.getEnterpriseInfoByCode(request.getEnterpriseCode());
        //校验当前的企业状态
        this.checkEnterpriseInfo(dto,infoVO);
        if (!dto.checkSuccess()){
            return dto;
        }
        //当前企业的限制信息
        EnterpriseStatsNumber number = new EnterpriseStatsNumber();
        // 获取企业下的所有用户
        List<UserEntity> all =userManager.getOkUserByEntrpriseCode(request.getEnterpriseCode());
        if (Check.NuNCollection(all)){
            return dto;
        }
        UserEntity user = userManager.fillAndGetEnterpriseUser(infoVO.getEnterpriseEntity());
        if (Check.NuNObj(user)){
            dto.setErrorMsg("创建企业账号信息");
            return dto;
        }

        UserAccountEntity userAccountEntity =userManager.fillAndGetAccountUser(request.getEnterpriseCode());
        if (Check.NuNObj(userAccountEntity)){
            dto.setErrorMsg("创建企业账号信息");
            return dto;
        }
        //设置余额
        number.setDrawBalance(ValueUtil.getintValue(userAccountEntity.getDrawBalance()));

        List<UserEntity>  bossList = new ArrayList<>();
        List<UserEntity>  empList = new ArrayList<>();
        if (!Check.NuNCollection(all)){
            for (UserEntity userEntity : all) {
                UserRoleEnum userRoleEnum = UserRoleEnum.getTypeByCode(userEntity.getUserRole());
                if (Check.NuNObj(userRoleEnum)){
                    userRoleEnum = UserRoleEnum.USER;
                }
                if (userRoleEnum.getCode() == UserRoleEnum.BOSS.getCode()){
                    bossList.add(userEntity);
                }else {
                    empList.add(userEntity);
                }
            }
        }
        number.setBossNum(bossList.size());
        number.setEmpNum(empList.size());

        if (ValueUtil.getintValue(request.getBossNum()) > 0
                && ValueUtil.getintValue(request.getBossNum()) !=  ValueUtil.getintValue(number.getBossNum())){
            dto.setErrorMsg("老板数量变化,充值失败");
            return dto;
        }
        if (ValueUtil.getintValue(request.getEmpNum()) > 0
                && ValueUtil.getintValue(request.getEmpNum()) !=  ValueUtil.getintValue(number.getEmpNum())){
            dto.setErrorMsg("员工数据发生变化,充值失败");
            return dto;
        }
        int total = 0;
        Double bossMoneyPen = BigDecimalUtil.mul(request.getBossMoneyYuan(),100);
        Double empMoneyPen = BigDecimalUtil.mul(request.getEmpMoneyYuan(),100);

        total += ValueUtil.getintValue(request.getBossNum()) * bossMoneyPen.intValue();
        total += ValueUtil.getintValue(request.getEmpNum()) * empMoneyPen.intValue();
        if (total > number.getDrawBalance()){
            dto.setErrorMsg("当前余额不足,请确定余额信息");
            return dto;
        }
        List<UserAccounFillRequest> list = new ArrayList<>();
        //老板充值金额
        if ( (ValueUtil.getintValue(request.getBossNum()) * bossMoneyPen) > 0){
            list.addAll(this.transUserFill(dto,bossList,bossMoneyPen.intValue()));
        }
        //员工充值金额
        if (( ValueUtil.getintValue(request.getEmpNum()) * empMoneyPen) > 0){
            list.addAll(this.transUserFill(dto,empList,empMoneyPen.intValue()));
        }
        if (dto.checkSuccess()){
            rechargeManager.fillUserAccountListByEnterprise(list,request.getEnterpriseCode());
        }
        return dto;
    }

    /**
     * 转化充值参数
     * @param list
     * @param money
     * @return
     */
    private List<UserAccounFillRequest> transUserFill(DataTransferObject dto,List<UserEntity>  list,int money){
        List<UserAccounFillRequest> rst = new ArrayList<>();
        if (!dto.checkSuccess()){
            return rst;
        }
        if (Check.NuNCollection(list)){
            return rst;
        }
        for (UserEntity userEntity : list) {
            UserAccountEntity accountEntity = userManager.fillAndGetAccountUser(userEntity.getUserUid());
            if (Check.NuNObj(accountEntity)){
                dto.setErrorMsg("创建用户失败");
                return rst;
            }
            UserAccounFillRequest fillRequest = new UserAccounFillRequest();
            fillRequest.setUserId(userEntity.getUserUid());
            fillRequest.setBizSn(UUIDGenerator.hexUUID());
            fillRequest.setMoney(money);
            rst.add(fillRequest);
        }
        return rst;
    }

    /**
     * 企业充值
     * @author afi
     * @param chargeRequest
     * @return
     */
    @Override
    public  DataTransferObject<Void> chargeMoney(ChargeRequest chargeRequest){
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNStr(chargeRequest.getEnterpriseCode())
                || Check.NuNObj(chargeRequest.getMoneyYuan())){
            dto.setErrorMsg("参数异常");
            return dto;
        }

        if (Check.NuNStr(chargeRequest.getEnterpriseCode())
                || Check.NuNObj(chargeRequest.getMoneyYuan())){
            dto.setErrorMsg("参数异常");
            return dto;
        }
        Double moneyYuan =BigDecimalUtil.mul(chargeRequest.getMoneyYuan(),100);
        int total = moneyYuan.intValue();
        if (total <= 0){
            dto.setErrorMsg("异常的充值金额");
            return dto;
        }

        EnterpriseInfoVO infoVO = enterpriseManager.getEnterpriseInfoByCode(chargeRequest.getEnterpriseCode());
        //校验当前的企业状态
        this.checkEnterpriseInfo(dto,infoVO);
        if (!dto.checkSuccess()){
            return dto;
        }
        // 获取企业下的所有用户
        List<UserEntity> all =userManager.getOkUserByEntrpriseCode(chargeRequest.getEnterpriseCode());
        if (Check.NuNCollection(all)){
            dto.setErrorMsg("当前企业未有员工信息");
            return dto;
        }
        UserEntity user = userManager.fillAndGetEnterpriseUser(infoVO.getEnterpriseEntity());
        if (Check.NuNObj(user)){
            dto.setErrorMsg("创建企业账号信息");
            return dto;
        }

        UserAccountEntity userAccountEntity =userManager.fillAndGetAccountUser(chargeRequest.getEnterpriseCode());
        if (Check.NuNObj(userAccountEntity)){
            dto.setErrorMsg("创建企业账号信息");
            return dto;
        }
        List<UserEntity>  bossList = new ArrayList<>();
        List<UserEntity>  empList = new ArrayList<>();
        if (!Check.NuNCollection(all)){
            for (UserEntity userEntity : all) {
                UserRoleEnum userRoleEnum = UserRoleEnum.getTypeByCode(userEntity.getUserRole());
                if (Check.NuNObj(userRoleEnum)){
                    userRoleEnum = UserRoleEnum.USER;
                }
                if (userRoleEnum.getCode() == UserRoleEnum.BOSS.getCode()){
                    bossList.add(userEntity);
                }else {
                    empList.add(userEntity);
                }
            }
        }
        String rechargeSn = "CH"+SnUtil.getOrderSn();
        RechargeEntity record = new RechargeEntity();
        record.setEnterpriseCode(infoVO.getEnterpriseEntity().getEnterpriseCode());
        record.setEnterpriseName(infoVO.getEnterpriseEntity().getEnterpriseName());
        record.setCreateId(chargeRequest.getOpId());
        record.setCreateName(chargeRequest.getOpName());
        record.setPayMoney(total);
        record.setBossNum(bossList.size());
        record.setCommonNum(empList.size());
        record.setRechargeSn(rechargeSn);
        record.setTotalPrice(total);
        record.setPayMoney(total);
        record.setSupplierCode(infoVO.getEnterpriseEntity().getSupplierCode());
        rechargeManager.saveRecharge(record);
        return dto;

    }



}
