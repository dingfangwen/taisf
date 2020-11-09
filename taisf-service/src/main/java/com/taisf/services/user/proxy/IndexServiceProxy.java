package com.taisf.services.user.proxy;

import com.jk.framework.base.constant.YesNoEnum;
import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.DateUtil;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.ValueUtil;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.common.util.WeekUtil;
import com.taisf.services.common.valenum.*;
import com.taisf.services.enterprise.entity.EnterpriseAddressEntity;
import com.taisf.services.enterprise.entity.EnterpriseConfigEntity;
import com.taisf.services.enterprise.entity.EnterpriseDayEntity;
import com.taisf.services.enterprise.manager.EnterpriseManagerImpl;
import com.taisf.services.enterprise.vo.EnterpriseInfoVO;
import com.taisf.services.supplier.entity.SupplierEntity;
import com.taisf.services.supplier.manager.SupplierManagerImpl;
import com.taisf.services.user.api.IndexService;
import com.taisf.services.user.entity.UserAccountEntity;
import com.taisf.services.user.entity.UserEntity;
import com.taisf.services.user.manager.UserManagerImpl;
import com.taisf.services.user.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>首页接口实现</p>
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
@Component("user.indexServiceProxy")
public class IndexServiceProxy implements IndexService {


    private static final Logger LOGGER = LoggerFactory.getLogger(IndexServiceProxy.class);

    @Resource(name = "supplier.supplierManagerImpl")
    private SupplierManagerImpl supplierManager;

    @Resource(name = "user.userManagerImpl")
    private UserManagerImpl userManager;

    @Resource(name = "enterprise.enterpriseManagerImpl")
    private EnterpriseManagerImpl enterpriseManager;


    /**
     * 获取用户的地址信息
     * @author afi
     * @param userUid
     * @return
     */
    @Override
    public DataTransferObject<List<EnterpriseAddressEntity>> getUserAddressList(String userUid) {
        DataTransferObject<List<EnterpriseAddressEntity>> dto = new DataTransferObject<>();
        UserEntity userEntity = userManager.getUserByUid(userUid);
        if (Check.NuNObj(userEntity)) {
            dto.setErrorMsg("当前用户不存在");
            return dto;
        }
        if(Check.NuNStr(userEntity.getEnterpriseCode())){
            dto.setErrorMsg("未绑定企业信息");
            return dto;
        }
        List<EnterpriseAddressEntity> list = enterpriseManager.getEnterpriseAddressByCode(userEntity.getEnterpriseCode());
        if (!Check.NuNCollection(list)){
            for (EnterpriseAddressEntity entity : list) {
                if (!Check.NuNObj(entity.getIsSelf())
                        && entity.getIsSelf() == YesNoEnum.YES.getCode()){
                    continue;
                    //如果是自取点,直接返回不做处理
                }
                entity.setConTel(userEntity.getUserPhone());
                entity.setConName(userEntity.getUserName());
            }
        }
        dto.setData(list);
        return dto;
    }

    /**
     * 获取用户的基本信息
     * @author afi
     * @param userUid
     * @return
     */
    @Override
    public DataTransferObject<IndexBaseVO> getUserBaseInfo(String userUid) {

        DataTransferObject<IndexBaseVO> dto = new DataTransferObject<>();
        IndexBaseVO indexVO = new IndexBaseVO();
        //设置属性
        dto.setData(indexVO);
        if (Check.NuNObj(userUid)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        UserEntity userEntity = userManager.getUserByUid(userUid);
        if (Check.NuNObj(userEntity)) {
            dto.setErrorMsg("当前用户不存在");
            return dto;
        }
        //1. 2
        // 处理用户的基本信息
        this.dealUserInfo(dto, userEntity, indexVO);
        if (!dto.checkSuccess()) {
            return dto;
        }
        dto.setData(indexVO);
        return dto;
    }


    /**
     * 处理当前的用户基本信息
     * @param dto
     * @param userEntity
     * @param indexVO
     */
    private void dealUserInfo(DataTransferObject dto, UserEntity userEntity, IndexBaseVO indexVO){
        if (Check.NuNObjs(dto,userEntity,indexVO)){
            return;
        }
        if (!dto.checkSuccess()){
            return;
        }
        IndexUserVO  userVO = new IndexUserVO();
        BeanUtils.copyProperties(userEntity,userVO);

        userVO.setPayCode(AppScanTypeEnum.QISHOU_PAY.transScanCode(userVO.getPayCode()));
        // 1. 设置基本属性
        indexVO.setUserInfo(userVO);
        UserStatusEnum statusEnum = UserStatusEnum.getTypeByCode(userEntity.getUserStatus());
        if (Check.NuNObj(statusEnum)){
            dto.setErrorMsg("异常的用户状态");
            return ;
        }

        if (statusEnum.getCode() == UserStatusEnum.AVAILABLE.getCode()){
            dto.setErrorMsg("当前用户未激活,请联系注册激活");
            return ;
        }
        if (statusEnum.getCode() != UserStatusEnum.ACTIVITY.getCode()){
            dto.setErrorMsg("当前用户已经被冻结,请联系平台处理");
            return ;
        }

        // 2.设置账户信息
        UserAccountEntity accountEntity =userManager.fillAndGetAccountUser(userEntity.getUserUid());

        //校验当前的账户状态
        AccountStatusEnum accountStatusEnum = AccountStatusEnum.getTypeByCode(accountEntity.getAccountStatus());
        if (Check.NuNObj(accountStatusEnum)){
            accountStatusEnum = AccountStatusEnum.AVAILABLE;
        }
        if (!accountStatusEnum.checkOk()){
            dto.setErrorMsg(accountStatusEnum.getDesc());
            return ;
        }
        if (Check.NuNStr(accountEntity.getAccountPassword())){
            //未设置密码
            userVO.setIsAccount(YesNoEnum.NO.getCode());
        }else {
            userVO.setIsAccount(YesNoEnum.YES.getCode());
        }
        //获取当前的余额
        int drawBalance = ValueUtil.getintValue(accountEntity.getDrawBalance());
        if (drawBalance < 0){
            dto.setErrorMsg("异常的账户信息");
            return ;
        }


        //2. 设置供应商信息
        SupplierEntity supplier = supplierManager.getSupplierByCode(userEntity.getBizCode());
        if (Check.NuNObj(supplier)){
            dto.setErrorMsg("异常的供应商信息");
            return;
        }else {
            indexVO.getUserInfo().setSupplierCode(supplier.getSupplierCode());
            indexVO.getUserInfo().setSupplierName(supplier.getSupplierName());
        }
        indexVO.setDrawBalance(drawBalance);
    }


    /**
     * 获取首页信息
     * @param userUid
     * @return
     */
    @Override
    public DataTransferObject<IndexBaseVO> getBaseIndex(String userUid) {
        DataTransferObject<IndexBaseVO> dto = new DataTransferObject<>();
        IndexBaseVO indexVO = new IndexBaseVO();
        //设置属性
        dto.setData(indexVO);
        if (Check.NuNObj(userUid)){
            dto.setErrorMsg("参数异常");
            return dto;
        }
        UserEntity userEntity = userManager.getUserByUid(userUid);
        if (Check.NuNObj(userEntity)){
            dto.setErrorMsg("当前用户不存在");
            return dto;
        }

        //1.处理用户的基本信息
        this.dealUserInfo(dto,userEntity,indexVO);
        if (!dto.checkSuccess()){
            return dto;
        }

        //2. 设置供应商信息
        SupplierEntity supplier = supplierManager.getSupplierByCode(userEntity.getBizCode());
        if (Check.NuNObj(supplier)){
            dto.setErrorMsg("异常的供应商信息");
            return dto;
        }else {
            indexVO.getUserInfo().setSupplierCode(supplier.getSupplierCode());
            indexVO.getUserInfo().setSupplierName(supplier.getSupplierName());
        }

        // 3.设置企业信息
        EnterpriseInfoVO infoVO = enterpriseManager.getEnterpriseInfoByCode(userEntity.getEnterpriseCode());

        if (Check.NuNObj(infoVO)){
            dto.setErrorMsg("异常的企业信息");
            return dto;
        }
        if (Check.NuNObj(infoVO.getEnterpriseEntity().getTillTime())){
            dto.setErrorMsg("异常的企业截止时间");
            return dto;
        }

        //当前时间
        Date now = new Date();
        if (infoVO.getEnterpriseEntity().getTillTime().before(now)){
            dto.setErrorMsg("加盟时间已经失效,请联系企业管理人员");
            return dto;
        }
        EnterpriseConfigEntity config =infoVO.getEnterpriseConfigEntity();
        if(Check.NuNObj(config)){
            dto.setErrorMsg("异常的企业配置信息");
            return dto;
        }
        return dto;
    }

        /**
         * 获取首页信息
         * @param userUid
         * @return
         */
    @Override
    public DataTransferObject<IndexVO> getIndex(String userUid) {

        DataTransferObject<IndexVO> dto = new DataTransferObject<>();
        IndexVO indexVO = new IndexVO();
        //设置属性
        dto.setData(indexVO);
        if (Check.NuNObj(userUid)){
            dto.setErrorMsg("参数异常");
            return dto;
        }
        UserEntity userEntity = userManager.getUserByUid(userUid);
        if (Check.NuNObj(userEntity)){
            dto.setErrorMsg("当前用户不存在");
            return dto;
        }

        //1. 2
        // 处理用户的基本信息
        this.dealUserInfo(dto,userEntity,indexVO);
        if (!dto.checkSuccess()){
            return dto;
        }


        // 3.设置企业信息
        EnterpriseInfoVO infoVO = enterpriseManager.getEnterpriseInfoByCode(userEntity.getEnterpriseCode());

        if (Check.NuNObj(infoVO)){
            dto.setErrorMsg("异常的企业信息");
            return dto;
        }
        if (Check.NuNObj(infoVO.getEnterpriseEntity().getTillTime())){
            dto.setErrorMsg("异常的企业截止时间");
            return dto;
        }

        //当前时间
        Date now = new Date();
        if (infoVO.getEnterpriseEntity().getTillTime().before(now)){
            dto.setErrorMsg("加盟时间已经失效,请联系企业管理人员");
            return dto;
        }
        EnterpriseConfigEntity config =infoVO.getEnterpriseConfigEntity();
        if(Check.NuNObj(config)){
            dto.setErrorMsg("异常的企业配置信息");
            return dto;
        }
        //是否支持补单
        boolean isExt = false;
        Integer isOpen = infoVO.getEnterpriseEntity().getIsOpen();
        if (!Check.NuNObj(isOpen) && isOpen == YesNoEnum.NO.getCode()){
            isExt = true;
        }
        //设置供应商的code
        indexVO.setSupplierCode(infoVO.getEnterpriseEntity().getSupplierCode());

        DayVO today = null;
        //获取当前周的配送时间
        List<DayVO>  dayList = enterpriseManager.getCurrentWeek(userEntity.getEnterpriseCode());
        if (!Check.NuNCollection(dayList)){
            for (DayVO dayVO : dayList) {
                if (ValueUtil.getintValue(dayVO.getWeek()) == WeekUtil.getWeek()){
                    today = dayVO;
                }
            }
            indexVO.setDayList(dayList);
        }
        if (Check.NuNObj(today)){
            //设置
            dealNoSend(indexVO);
        }
        //获取当前的订餐类型
        OrderTypeEnum orderTypeEnum = this.dealTime4Lunch(config,now,indexVO,isExt);
        if (Check.NuNObj(orderTypeEnum)){
            orderTypeEnum = this.dealTime4Dinner(config,now,indexVO,isExt);
        }
        if (Check.NuNObj(orderTypeEnum)){
            indexVO.setTimeMsg("订餐倒计时:");
        }else {
            indexVO.setOrderFlag(true);
            indexVO.setOrderType(orderTypeEnum.getCode());
        }

        if (ValueUtil.getintValue(indexVO.getTimeLast()) <= 0){
            indexVO.setOrderType(null);
            indexVO.setOrderFlag(false);
        }
        return dto;
    }

    /**
     * 设置当前不配送的信息
     * @param indexVO
     */
    private void dealNoSend(IndexVO indexVO){
        if (Check.NuNObj(indexVO)){
            return;
        }
        indexVO.setTimeTitle("点餐（今日不配送）");
        indexVO.setTimeMsg("当天不配送");

    }

    /**
     * 处理当前的订单时间信息
     * @param config
     */
    public List<FanVO> dealTimeInfo(EnterpriseConfigEntity config){
        List<FanVO> timeList = new ArrayList<>();
        if (Check.NuNObjs(config)){
            return timeList;
        }

        //设置午饭时间
        if (ValueUtil.getintValue(config.getForLunch()) == YesNoEnum.YES.getCode()){
            FanVO time = new FanVO();
            time.setName("午餐");
            time.setStart(getFanTime(config.getLunchStart()));
            time.setEnd(getFanTime(config.getLunchEnd()));
            timeList.add(time);
        }
        //设置晚饭
        if (ValueUtil.getintValue(config.getForDinner()) == YesNoEnum.YES.getCode()){
            FanVO time = new FanVO();
            time.setName("晚餐");
            time.setStart(getFanTime(config.getDinnerStart()));
            time.setEnd(getFanTime(config.getDinnerEnd()));
            timeList.add(time);
        }
        return timeList;
    }

    /**
     * 获取当前时间展示逻辑
     * @param fanTime
     * @return
     */
    private static String getFanTime(String fanTime){
        if (Check.NuNStr(fanTime)){
            return "";
        }
        String[] fan = fanTime.split(":",3);
        if (fan.length < 3){
            return fanTime;
        }
        return fan[0] + ":" +fan[1];
    }

    public static void main(String[] args) {


        String fanTime = getFanTime("10:00:00");
        System.out.println(fanTime);
    }

    /**
     * 获取当前企业的当前的订单类型
     * @param enterpriseCode
     * @return
     */
    @Override
    public DataTransferObject<OrderTypeEnum> getOrderType(String enterpriseCode){
        DataTransferObject<OrderTypeEnum> dto = new DataTransferObject<>();
        if (Check.NuNStr(enterpriseCode)){
            dto.setErrorMsg("参数异常");
        }
        //获取当前的企业信息
        EnterpriseInfoVO infoVO = enterpriseManager.getEnterpriseInfoByCode(enterpriseCode);
        if (Check.NuNObj(infoVO)){
            dto.setErrorMsg("异常的企业信息");
            return dto;
        }
        if (Check.NuNObj(infoVO.getEnterpriseEntity().getTillTime())){
            dto.setErrorMsg("异常的企业截止时间");
            return dto;
        }

        //当前时间
        Date now = new Date();
        if (infoVO.getEnterpriseEntity().getTillTime().before(now)){
            dto.setErrorMsg("加盟时间已经失效,请联系企业管理人员");
            return dto;
        }
        EnterpriseConfigEntity config =infoVO.getEnterpriseConfigEntity();
        if(Check.NuNObj(config)){
            dto.setErrorMsg("异常的企业配置信息");
            return dto;
        }

        EnterpriseDayEntity day = enterpriseManager.getCurrentDay(enterpriseCode);
        if (Check.NuNObj(day)){
            dto.setErrorMsg("当天不配送");
            return dto;
        }
        if (ValueUtil.getintValue(day.getDayType()) == DayTypeEnum.NO.getCode()){
            dto.setErrorMsg("当天不配送");
            return dto;
        }

        IndexVO indexVO = new IndexVO();
        //获取当前的订餐类型
        OrderTypeEnum orderTypeEnum = this.dealTime4Lunch(config,now,indexVO,false);
        if (Check.NuNObj(orderTypeEnum)){
            orderTypeEnum = this.dealTime4Dinner(config,now,indexVO,false);
        }
        if (Check.NuNObj(orderTypeEnum)){
            dto.setErrorMsg("当前时间点暂时不能订饭");
        }else {
            dto.setData(orderTypeEnum);
        }
        return dto;
    }


    /**
     * 校验当前
     * @return
     */
    private OrderTypeEnum dealTime4Lunch( EnterpriseConfigEntity config ,Date now,IndexVO indexVO, boolean isExt){
        OrderTypeEnum orderTypeEnum = null;
        if (Check.NuNObj(config)){
            return orderTypeEnum;
        }
        if (ValueUtil.getintValue(config.getForLunch()) != 1){
            return orderTypeEnum;
        }
        try {
            Date start = DateUtil.connectDate(now,config.getLunchStart());
            Date end = DateUtil.connectDate(now,config.getLunchEnd());
            if (now.after(start) && now.before(end)){
                //时间正常
                orderTypeEnum = OrderTypeEnum.LUNCH_COMMON;
                indexVO.setTimeMsg("午餐订餐倒计时:");
                indexVO.setTimeLast(end.getTime() - now.getTime());
            }else if (now.after(end) && now.before(DateUtil.jumpMinute(end,30)) && isExt){
                indexVO.setTimeMsg("午餐订餐倒计时:");
                //时间正常
                orderTypeEnum = OrderTypeEnum.LUNCH_EXT;
            }
        }catch (Exception e){
            LogUtil.error(LOGGER,"处理时间错误:e:{}",e);
            return  orderTypeEnum;
        }
        return orderTypeEnum;
    }



    /**
     * 校验当前
     * @return
     */
    private OrderTypeEnum dealTime4Dinner(EnterpriseConfigEntity config ,Date now,IndexVO indexVO, boolean isExt){
        OrderTypeEnum orderTypeEnum = null;
        if (Check.NuNObj(config)){
            return orderTypeEnum;
        }
        if (ValueUtil.getintValue(config.getForLunch()) != 1){
            return orderTypeEnum;
        }
        try {
            Date start = DateUtil.connectDate(now,config.getDinnerStart());
            Date end = DateUtil.connectDate(now,config.getDinnerEnd());
            if (now.after(start) && now.before(end)){
                //时间正常
                indexVO.setTimeMsg("晚餐订餐倒计时:");
                indexVO.setTimeLast(end.getTime() - now.getTime());
                orderTypeEnum = OrderTypeEnum.DINNER_COMMON;
            }else if (now.after(end) && now.before(DateUtil.jumpMinute(end,30)) && isExt){
                indexVO.setTimeMsg("晚餐订餐倒计时:");
                //补单
                orderTypeEnum = OrderTypeEnum.DINNER_EXT;
            }
        }catch (Exception e){
            LogUtil.error(LOGGER,"处理时间错误:e:{}",e);
            return  orderTypeEnum;
        }
        return orderTypeEnum;
    }



}
