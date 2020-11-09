package com.taisf.services.enterprise.proxy;

import com.jk.framework.base.constant.YesNoEnum;
import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.DateUtil;
import com.jk.framework.base.utils.ValueUtil;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.enterprise.api.EnterpriseService;
import com.taisf.services.enterprise.dao.EnterpriseDao;
import com.taisf.services.enterprise.dto.*;
import com.taisf.services.enterprise.entity.*;
import com.taisf.services.enterprise.manager.EnterpriseManagerImpl;
import com.taisf.services.enterprise.vo.*;
import com.taisf.services.user.entity.UserAccountEntity;
import com.taisf.services.user.manager.UserManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>企业接口实现</p>
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
@Component("enterprise.enterpriseServiceProxy")
public class EnterpriseServiceProxy implements EnterpriseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnterpriseServiceProxy.class);

    @Resource(name = "enterprise.enterpriseManagerImpl")
    private EnterpriseManagerImpl enterpriseManager;

    @Autowired
    private EnterpriseDao enterpriseDao;

    @Resource(name = "user.userManagerImpl")
    private UserManagerImpl userManager;


    /**
     * 改变配送信息
     *
     * @param enterpriseDayRequest
     * @return
     */
    @Override
    public DataTransferObject<Void> changeEnterpriseDay(EnterpriseDayRequest enterpriseDayRequest) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(enterpriseDayRequest)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNObj(enterpriseDayRequest.getDayTime())) {
            dto.setErrorMsg("请确认时间");
            return dto;
        }

        if (Check.NuNObj(enterpriseDayRequest.getDayType())) {
            dto.setErrorMsg("类型");
            return dto;
        }
        if (Check.NuNStr(enterpriseDayRequest.getEnterpriseCode())) {
            dto.setErrorMsg("企业code为空");
            return dto;
        }
        enterpriseDayRequest.setDayTimeStr(DateUtil.intFormat(enterpriseDayRequest.getDayTime()) + "");
        boolean delFlag;
        if (DateUtil.isWorkDay(enterpriseDayRequest.getDayTime())) {
            //工作日
            if (enterpriseDayRequest.getDayType() == YesNoEnum.YES.getCode()) {
                delFlag = true;
            } else {
                delFlag = false;
            }
        } else {
            //非工作日
            if (enterpriseDayRequest.getDayType() == YesNoEnum.YES.getCode()) {
                delFlag = false;
            } else {
                delFlag = true;
            }
        }
        if (delFlag) {
            enterpriseManager.delEnterpriseDays(enterpriseDayRequest);
        } else {
            EnterpriseDayEntity has = enterpriseManager.getEnterpriseDays(enterpriseDayRequest);
            if (Check.NuNObj(has)) {
                EnterpriseDayEntity record = new EnterpriseDayEntity();
                record.setEnterpriseCode(enterpriseDayRequest.getEnterpriseCode());
                record.setDayTime(enterpriseDayRequest.getDayTimeStr());
                record.setDayType(enterpriseDayRequest.getDayType());
                record.setOpCode(enterpriseDayRequest.getOpCode());
                record.setOpName(enterpriseDayRequest.getOpName());
                enterpriseManager.saveEnterpriseDay(record);
            }
        }
        return dto;
    }

    /**
     * 获取企业配送的信息
     *
     * @param enterpriseCode
     * @return
     */
    @Override
    public DataTransferObject<List<EnterpriseListDay>> getEnterpriseListDay(String enterpriseCode) {
        DataTransferObject<List<EnterpriseListDay>> dto = new DataTransferObject<>();
        if (Check.NuNStr(enterpriseCode)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        List<EnterpriseDayEntity> list = enterpriseManager.dealEnterpriseDays(enterpriseCode);
        if (Check.NuNCollection(list)) {
            dto.setErrorMsg("异常的配送信息");
            return dto;
        }
        if (list.size() != 42) {
            dto.setErrorMsg("异常的配送信息");
            return dto;
        }
        Integer now = DateUtil.intFormat(new Date());
        List<EnterpriseListDay> listDayList = new ArrayList<>();
        Map<Integer, EnterpriseListDay> has = new HashMap<>();
        int key = 0;
        for (int k = 0; k < list.size(); k++) {
            EnterpriseDayEntity ele = list.get(k);
            EnterpriseDayVO vo = new EnterpriseDayVO();
            BeanUtils.copyProperties(ele, vo);
            if (ValueUtil.getintValue(ele.getDayTime()) >= now) {
                vo.setChangeFlag(true);
            }
            int last = k % 7;
            if (last == 0) {
                key++;
                EnterpriseListDay weekList = new EnterpriseListDay();
                weekList.getList().add(vo);
                has.put(key, weekList);
                listDayList.add(weekList);
            } else {
                EnterpriseListDay weekList = has.get(key);
                weekList.getList().add(vo);
            }
        }
        dto.setData(listDayList);
        return dto;
    }

    /**
     * 获取企业配送的信息
     *
     * @param request
     * @return
     */
    @Override
    public DataTransferObject<PagingResult<EnterpriseDispatchVO>> getEnterpriseDispatchByPage(EnterprisePageRequest request) {
        DataTransferObject<PagingResult<EnterpriseDispatchVO>> dto = new DataTransferObject<>();
        if (Check.NuNObj(request)) {
            request = new EnterprisePageRequest();
        }
        PagingResult<EnterpriseEntity> pagingResult = enterpriseManager.getEnterpriseByPage(request);
        PagingResult<EnterpriseDispatchVO> paging = new PagingResult(pagingResult.getTotal(), trans2Dispatch(pagingResult.getList()));
        dto.setData(paging);
        return dto;
    }

    /**
     * 转化当前对象信息
     *
     * @param list
     * @return
     */
    private List<EnterpriseDispatchVO> trans2Dispatch(List<EnterpriseEntity> list) {
        List<EnterpriseDispatchVO> volist = new ArrayList<>();
        if (Check.NuNCollection(list)) {
            return volist;
        }
        for (EnterpriseEntity entity : list) {
            EnterpriseDispatchVO vo = new EnterpriseDispatchVO();
            BeanUtils.copyProperties(entity, vo);
            fillEnterpriseDispatchVO(vo);
            volist.add(vo);
        }
        return volist;
    }

    /**
     * 填充当前的配送信息
     *
     * @param vo
     */
    private void fillEnterpriseDispatchVO(EnterpriseDispatchVO vo) {
        if (Check.NuNObj(vo)) {
            return;
        }
        //获取当前的配送信息
        Map<String, EnterpriseDayEntity> map = enterpriseManager.dealEnterpriseMapDays(vo.getEnterpriseCode());
        Date first = DateUtil.getFirstDayOfWeekDay(new Date());
        vo.setDay1(map.get(DateUtil.intFormat(first) + ""));
        first = DateUtil.jumpDate(first, 1);
        vo.setDay2(map.get(DateUtil.intFormat(first) + ""));
        first = DateUtil.jumpDate(first, 1);
        vo.setDay3(map.get(DateUtil.intFormat(first) + ""));
        first = DateUtil.jumpDate(first, 1);
        vo.setDay4(map.get(DateUtil.intFormat(first) + ""));
        first = DateUtil.jumpDate(first, 1);
        vo.setDay5(map.get(DateUtil.intFormat(first) + ""));
        first = DateUtil.jumpDate(first, 1);
        vo.setDay6(map.get(DateUtil.intFormat(first) + ""));
        first = DateUtil.jumpDate(first, 1);
        vo.setDay7(map.get(DateUtil.intFormat(first) + ""));
    }


    /**
     * 获取月内时间节点信息
     *
     * @param enterpriseCode
     * @author afi
     */
    public DataTransferObject<List<EnterpriseDayEntity>> dealEnterpriseDays(String enterpriseCode) {
        DataTransferObject<List<EnterpriseDayEntity>> dto = new DataTransferObject<>();
        if (Check.NuNStr(enterpriseCode)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        List<EnterpriseDayEntity> list = enterpriseManager.dealEnterpriseDays(enterpriseCode);
        dto.setData(list);
        return dto;
    }

    /**
     * 获取企业的信息
     *
     * @param request
     * @return
     */
    @Override
    public DataTransferObject<PagingResult<EnterpriseEntity>> getEnterpriseByPage(EnterprisePageRequest request) {

        DataTransferObject<PagingResult<EnterpriseEntity>> dto = new DataTransferObject<>();

        if (Check.NuNObj(request)) {
            request = new EnterprisePageRequest();
        }
        PagingResult<EnterpriseEntity> pagingResult = enterpriseManager.getEnterpriseByPage(request);
        dto.setData(pagingResult);
        return dto;
    }


    /**
     * 获取企业的账户统计信息
     *
     * @param request
     * @return
     */
    @Override
    public DataTransferObject<PagingResult<EnterpriseAccountVO>> getEnterpriseAccountByPage(EnterprisePageRequest request) {

        DataTransferObject<PagingResult<EnterpriseAccountVO>> dto = new DataTransferObject<>();

        if (Check.NuNObj(request)) {
            request = new EnterprisePageRequest();
        }
        PagingResult<EnterpriseEntity> pagingResult = enterpriseManager.getEnterpriseByPage(request);
        //创建新的分页试图
        PagingResult<EnterpriseAccountVO> page = new PagingResult<>(pagingResult.getTotal(), transBase2Account(pagingResult.getList()));
        dto.setData(page);
        return dto;
    }

    /**
     * 将企业基本对象转化成企业的账户统计信息
     *
     * @param list
     * @return
     * @author afi
     */
    private List<EnterpriseAccountVO> transBase2Account(List<EnterpriseEntity> list) {
        List<EnterpriseAccountVO> listAccount = new ArrayList<>();
        if (Check.NuNCollection(list)) {
            return listAccount;
        }
        List<String> listPar = new ArrayList<>();
        for (EnterpriseEntity entity : list) {
            listPar.add(entity.getEnterpriseCode());
        }
        //获取企业统计
        Map<String, EnterpriseAccountVO> codeMap = userManager.getEnterpriseAccountMapByList(listPar);

        //获取账户信息
        Map<String, UserAccountEntity> userMap = userManager.getUserAccountMapByList(listPar);
        for (EnterpriseEntity entity : list) {
            String key = entity.getEnterpriseCode();
            EnterpriseAccountVO vo;
            if (codeMap.containsKey(key)) {
                vo = codeMap.get(key);
            } else {
                vo = new EnterpriseAccountVO();
                vo.setEnterpriseCode(key);
            }
            vo.setEnterpriseName(entity.getEnterpriseName());

            UserAccountEntity user = null;
            if (userMap.containsKey(key)) {
                user = userMap.get(key);
            }
            if (!Check.NuNObj(user)) {
                vo.setDrawBalance(user.getDrawBalance());
            }
            listAccount.add(vo);
        }
        return listAccount;
    }

    /**
     * 获取企业基本信息
     *
     * @param enterpriseCode
     * @return
     */
    @Override
    public DataTransferObject<EnterpriseEntity> getEnterpriseByCode(String enterpriseCode) {
        DataTransferObject<EnterpriseEntity> dto = new DataTransferObject<>();

        if (Check.NuNStr(enterpriseCode)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        EnterpriseEntity entity = enterpriseManager.getEnterpriseByCode(enterpriseCode);
        if (entity == null) {
            dto.setErrorMsg("当前企业不存在");
        }
        dto.setData(entity);
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/14
     * @description:查询当前销售员工下维护的企业
     **/
    @Override
    public DataTransferObject<PagingResult<EnterpriseEntity>> pageListAndManger(EnterpriseListRequest request) {
        DataTransferObject<PagingResult<EnterpriseEntity>> dto = new DataTransferObject<>();
        if (Check.NuNStr(request.getManger())) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        PagingResult<EnterpriseEntity> pagingResult = enterpriseDao.pageListAndManger(request);
        dto.setData(pagingResult);
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/14
     * @description:根据enterpriseCode修改
     **/
    @Override
    public DataTransferObject<Void> updateEnterprise(EnterpriseEntity enterpriseEntity) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(enterpriseEntity)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        enterpriseDao.updateEnterprise(enterpriseEntity);
        return dto;
    }

    /**
     * @author:afi
     * @description:根据订单列表聚合企业列表
     * @param supplierCode
     * @return
     */
    @Override
    public DataTransferObject<List<EnterpriseEntity>> taskByOrder(String supplierCode){
        DataTransferObject<List<EnterpriseEntity>> dto = new DataTransferObject<>();
        if (Check.NuNStr(supplierCode)){
            return dto;
        }
        List<EnterpriseEntity> entityList = enterpriseDao.taskByOrder(supplierCode);
        dto.setData(entityList);
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/18
     * @description:查询存在订单的企业列表
     **/
    @Override
    public DataTransferObject<List<EnterpriseEntity>> findAll() {
        DataTransferObject<List<EnterpriseEntity>> dto = new DataTransferObject<>();
        List<EnterpriseEntity> entityList = enterpriseDao.findAll();
        dto.setData(entityList);
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/31
     * @description:查询所有企业列表
     **/
    @Override
    public DataTransferObject<List<EnterpriseEntity>> findAllEnterprise(EnterpriseListRequest enterpriseListRequest) {
        DataTransferObject<List<EnterpriseEntity>> dto = new DataTransferObject<>();
        List<EnterpriseEntity> entityList = enterpriseDao.findAllEnterprise(enterpriseListRequest);
        dto.setData(entityList);
        return dto;
    }

    @Override
    public DataTransferObject<PagingResult<EnterpriseAddressEntity>> findPageEnterpriseAddressByCode(EnterpriseAddressRequest enterpriseAddressRequest) {
        DataTransferObject<PagingResult<EnterpriseAddressEntity>> dto = new DataTransferObject<>();
        if (Check.NuNObj(enterpriseAddressRequest)) {
            dto.setErrorMsg("参数错误");
            return dto;
        }
        try {
            PagingResult<EnterpriseAddressEntity> pagingResult = enterpriseManager.findPageEnterpriseAddressByCode(enterpriseAddressRequest);
            dto.setData(pagingResult);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【分页查询企业地址异常】error:{}", e);
            dto.setErrorMsg("分页查询企业地址异常");
            return dto;
        }
        return dto;
    }

    @Override
    public DataTransferObject<Void> saveEnterpriseAddress(EnterpriseAddressEntity record) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(record)) {
            dto.setErrorMsg("参数错误");
            return dto;
        }
        try {
            enterpriseManager.saveEnterpriseAddress(record);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【新增企业地址异常】error:{}", e);
            dto.setErrorMsg("新增企业地址异常");
            return dto;
        }
        return dto;
    }

    @Override
    public DataTransferObject<Void> updateEnterpriseAddress(EnterpriseAddressEntity record) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(record)) {
            dto.setErrorMsg("参数错误");
            return dto;
        }
        try {
            enterpriseManager.updateEnterpriseAddress(record);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【修改企业地址异常】error:{}", e);
            dto.setErrorMsg("修改企业地址异常");
            return dto;
        }
        return dto;
    }

    @Override
    public DataTransferObject<Void> deleteByFid(String fid) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(fid)) {
            dto.setErrorMsg("参数错误");
            return dto;
        }
        try {
            enterpriseManager.deleteByFid(fid);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【删除企业地址异常】error:{}", e);
            dto.setErrorMsg("删除企业地址异常");
            return dto;
        }
        return dto;
    }

    @Override
    public DataTransferObject<EnterpriseAddressEntity> getEnterpriseAddressByFid(String fid) {
        DataTransferObject<EnterpriseAddressEntity> dto = new DataTransferObject<>();
        if (Check.NuNObj(fid)) {
            dto.setErrorMsg("参数错误");
            return dto;
        }
        try {
            EnterpriseAddressEntity enterpriseAddressEntity = enterpriseManager.getEnterpriseAddressByFid(fid);
            dto.setData(enterpriseAddressEntity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【查询企业地址异常】error:{}", e);
            dto.setErrorMsg("查询企业地址异常");
            return dto;
        }
        return dto;
    }

    @Override
    public DataTransferObject<Void> operateEnterprise(EnterpriseUpdateRequest request) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        try {
            boolean flag = checkRequest(request);
            if (flag == false) {
                dto.setErrCode(DataTransferObject.ERROR);
                dto.setMsg("参数错误");
                return dto;
            }

            EnterpriseConfigEntity configEntity = new EnterpriseConfigEntity();
            BeanUtils.copyProperties(request, configEntity);

            EnterpriseFinanceEntity financeEntity = new EnterpriseFinanceEntity();
            BeanUtils.copyProperties(request, financeEntity);

            List<EnterpriseAddressEntity> addressEntityList = dealAddressEntityList(request);
            EnterpriseModel enterpriseModel = new EnterpriseModel();
            enterpriseModel.setEnterpriseEntity(request);
            enterpriseModel.setConfigEntity(configEntity);
            enterpriseModel.setFinanceEntity(financeEntity);
            enterpriseModel.setAddressEntityList(addressEntityList);

            if (request.getOperateType() == YesNoEnum.YES.getCode()) {
                EnterpriseEntity _entity = enterpriseManager.getEnterpriseByCode(request.getEnterpriseCode());
                if (!Check.NuNObj(_entity)) {
                    dto.setErrCode(DataTransferObject.ERROR);
                    dto.setMsg("存在重名企业编号");
                    return dto;
                }

                enterpriseManager.saveEnterpriseModel(enterpriseModel);
            }
            if (request.getOperateType() == YesNoEnum.NO.getCode()) {
                EnterpriseEntity _entity = enterpriseManager.getEnterpriseByCode(request.getEnterpriseCode());
                if (!Check.NuNObj(_entity) && _entity.getId() != request.getId()) {
                    dto.setErrCode(DataTransferObject.ERROR);
                    dto.setMsg("存在重名企业编号");
                    return dto;
                }
                enterpriseManager.updateEnterpriseModel(enterpriseModel);
            }

        } catch (Exception e) {
            LogUtil.error(LOGGER, "【企业】error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("操作企业信息异常");
            return dto;
        }
        return dto;
    }

    private List<EnterpriseAddressEntity> dealAddressEntityList(EnterpriseUpdateRequest request) {
        List<EnterpriseAddressEntity> addressEntityList = new ArrayList<>();
        EnterpriseAddressEntity addressEntity = null;

        addressEntity = new EnterpriseAddressEntity();
        addressEntity.setEnterpriseCode(request.getEnterpriseCode());
        addressEntity.setFid(request.getEnterpriseCode() + "#mainAddress");
        if (!Check.NuNObj(request.getMainAddress())) {
            addressEntity.setAddress(request.getMainAddress());
        }
        addressEntityList.add(addressEntity);

        addressEntity = new EnterpriseAddressEntity();
        addressEntity.setEnterpriseCode(request.getEnterpriseCode());
        addressEntity.setFid(request.getEnterpriseCode() + "#otherAddress");
        if (!Check.NuNObj(request.getOtherAddress())) {
            addressEntity.setAddress(request.getOtherAddress());
        }
        addressEntityList.add(addressEntity);

        return addressEntityList;

    }

    private boolean checkRequest(EnterpriseUpdateRequest request) {
        if (Check.NuNObjs(request.getEnterpriseCode(), request.getEnterpriseName(), request.getEnterpriseType(),
                request.getBossPrice(), request.getEmpPrice())) {
            return false;
        }
        return true;
    }

    /**
     * 查询企业信息列表
     */
    @Override
    public DataTransferObject<PagingResult<EnterpriseExtVO>> getEnterpriseExtByPage(EnterpriseListRequest request) {
        DataTransferObject<PagingResult<EnterpriseExtVO>> dto = new DataTransferObject<PagingResult<EnterpriseExtVO>>();
        try {
            PagingResult<EnterpriseExtVO> vos = enterpriseManager.getEnterpriseExtByPage(request);
            dto.setData(vos);

        } catch (Exception e) {
            LogUtil.error(LOGGER, "error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("系统异常");
            return dto;
        }
        return dto;
    }

    @Override
    public DataTransferObject<EnterpriseModel> getEnterpriseModelById(Integer id) {
        LogUtil.info(LOGGER, "查询企业信息入参:{}", id);
        DataTransferObject<EnterpriseModel> dto = new DataTransferObject<>();
        try {
            EnterpriseModel model = enterpriseManager.getEnterpriseModelById(id);
            if (Check.NuNObj(model)) {
                dto.setErrCode(DataTransferObject.ERROR);
                dto.setMsg("不存在此编号企业");
                return dto;
            }
            dto.setData(model);
        } catch (Exception e) {
            dto.setErrCode(DataTransferObject.ERROR);
            LogUtil.error(LOGGER, "查询企业信息异常:{}", e);
        }
        return dto;
    }

}
