package com.taisf.web.enterprise.buffet;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.base.entity.AreaRegionEntity;
import com.taisf.services.base.service.AreaRegionService;
import com.taisf.services.buffet.api.BuffetService;
import com.taisf.services.buffet.dto.BuffetInfoRequest;
import com.taisf.services.buffet.entity.BuffetEntity;
import com.taisf.services.device.api.LinkServoice;
import com.taisf.services.device.common.BaseContext;
import com.taisf.services.device.logic.aaa.command.response.LinkOpenCellsResponse;
import com.taisf.services.device.vo.CellModelCDVO;
import com.taisf.services.ups.entity.EmployeeEntity;
import com.taisf.web.enterprise.buffet.vo.BuffetForceOpenRequest;
import com.taisf.web.enterprise.common.constant.LoginConstant;
import com.taisf.web.enterprise.common.page.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("buffet/")
public class BuffetController  extends BaseContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuffetController.class);

    @Autowired
    private BuffetService buffetService;
    @Autowired
    private AreaRegionService areaRegionService;

    /**
     * @author:zhangzhengguang
     * @date:2017/10/11
     * @description:菜单录入列表
     **/
    @RequestMapping("buffetList")
    public void list(HttpServletRequest request) {
        List<AreaRegionEntity> provinceList = areaRegionService.findAllAreaRegion(2);
        request.setAttribute("provinceList", provinceList);
    }

    /**
     * @author:zhangzhengguang
     * @date:2019/4/9
     * @description:餐柜列表
     **/
    @RequestMapping("pageList")
    @ResponseBody
    public PageResult pageList(HttpServletRequest request, BuffetInfoRequest buffetInfoRequest) {
        LogUtil.info(LOGGER, "params:{}", JsonEntityTransform.Object2Json(buffetInfoRequest));
        PageResult pageResult = new PageResult();
        try {
            HttpSession session = request.getSession();
            EmployeeEntity emp = (EmployeeEntity)session.getAttribute(LoginConstant.SESSION_KEY);
            buffetInfoRequest.setSupplierCode(emp.getEmpBiz());
            PagingResult<BuffetEntity> pagingResult = buffetService.pageListBuffet(buffetInfoRequest).getData();
            if (!Check.NuNObj(pagingResult)) {
                pageResult.setRows(pagingResult.getList());
                pageResult.setTotal(pagingResult.getTotal());
            }
        } catch (Exception e) {
            LogUtil.error(LOGGER, "BuffetController - pageList error:{}", e);
            return new PageResult();
        }
        return pageResult;
    }

    @RequestMapping("pageListCell")
    @ResponseBody
    public PageResult pageListCell(HttpServletRequest request, BuffetInfoRequest buffetInfoRequest) {
        LogUtil.info(LOGGER, "params:{}", JsonEntityTransform.Object2Json(buffetInfoRequest));
        PageResult pageResult = new PageResult();
        try {
            PagingResult<CellModelCDVO> data = buffetService.pageListCell(buffetInfoRequest.getConfigCode(),buffetInfoRequest.getDeviceId()).getData();
            if (!Check.NuNObj(data)) {
                pageResult.setRows(data.getList());
                pageResult.setTotal(data.getTotal());
            }else{
                pageResult.setRows(new ArrayList<>());
                pageResult.setTotal(1L);
            }
        } catch (Exception e) {
            LogUtil.error(LOGGER, "BuffetController - pageList error:{}", e);
            return new PageResult();
        }
        return pageResult;
    }

    /**
     * @author:zhangzhengguang
     * @date:2019/4/9
     * @description:新增餐柜
     **/
    @RequestMapping("addBuffet")
    @ResponseBody
    public DataTransferObject<Void> addBuffet(HttpServletRequest request, BuffetEntity buffetEntity) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(buffetEntity)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }
        BuffetInfoRequest buffetInfoRequest = new BuffetInfoRequest();
        buffetInfoRequest.setBuffetFid(buffetEntity.getFid());
        BuffetEntity buffetByFid = buffetService.getBuffetByFid(buffetInfoRequest);
        if(!Check.NuNObj(buffetByFid)){
            dto.setErrorMsg("餐柜ID已存在");
            return dto;
        }

        buffetInfoRequest.setDeviceId(buffetEntity.getDeviceId());
        BuffetEntity BuffetByDeviceId = buffetService.getBuffetByDeviceId(buffetInfoRequest);
        if(!Check.NuNObj(BuffetByDeviceId)){
            dto.setErrorMsg("餐柜Key值已存在");
            return dto;
        }

        try {
            HttpSession session = request.getSession();
            EmployeeEntity emp = (EmployeeEntity)session.getAttribute(LoginConstant.SESSION_KEY);
            buffetEntity.setSupplierCode(emp.getEmpBiz());
            buffetEntity.setCreateTime(new Date());
            buffetEntity.setUpdateTime(new Date());
            dto = buffetService.saveBuffet(buffetEntity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "BuffetController-addBuffet  error:{}", e);
            dto.setErrorMsg("系统错误");
            return dto;
        }
        return dto;
    }

    /**
     * @author:zhangzhengguang
     * @date:2019/4/9
     * @description:更新餐柜
     **/
    @RequestMapping("updateBuffet")
    @ResponseBody
    public DataTransferObject<Void> updateBuffet(HttpServletRequest request, BuffetEntity buffetEntity) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(buffetEntity)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        BuffetInfoRequest buffetInfoRequest = new BuffetInfoRequest();
        buffetInfoRequest.setBuffetFid(buffetEntity.getFid());
        BuffetEntity buffetByFid = buffetService.getBuffetByFid(buffetInfoRequest);
        if(!Check.NuNObj(buffetByFid) && !buffetByFid.getId().equals(buffetEntity.getId())){
            dto.setErrorMsg("餐柜ID已存在");
            return dto;
        }

        buffetInfoRequest.setDeviceId(buffetEntity.getDeviceId());
        BuffetEntity BuffetByDeviceId = buffetService.getBuffetByDeviceId(buffetInfoRequest);
        if(!Check.NuNObj(BuffetByDeviceId) && !(BuffetByDeviceId.getId().equals(buffetEntity.getId()))){
            dto.setErrorMsg("餐柜Key值已存在");
            return dto;
        }
        try {
            dto = buffetService.updateById(buffetEntity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "BuffetController-updateBuffet error:{}", e);
            dto.setErrorMsg("系统错误");
            return dto;
        }
        return dto;
    }




    /**
     * @author:zhangzhengguang
     * @date:2019/4/9
     * @description:强制开启
     **/
    @RequestMapping("forceOpenCell")
    @ResponseBody
    public DataTransferObject<Void> forceOpenCell(HttpServletRequest request, BuffetForceOpenRequest buffetEntity) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if (Check.NuNObj(buffetEntity)) {
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(buffetEntity.getDeviceId())){
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(buffetEntity.getConfigCode())){
            dto.setErrorMsg("异常的设备配置信息");
            return dto;
        }
        if (Check.NuNStr(buffetEntity.getCellNum())) {
            dto.setErrorMsg("参数异常");
        }
        DataTransferObject link = getLinkService(buffetEntity.getConfigCode()).forceOpenCell(buffetEntity.getDeviceId(),buffetEntity.getCellNum());
        return link;
    }


}
