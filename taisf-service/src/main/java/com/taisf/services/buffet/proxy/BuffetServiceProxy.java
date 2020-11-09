package com.taisf.services.buffet.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.buffet.api.BuffetService;
import com.taisf.services.buffet.dto.BuffetInfoRequest;
import com.taisf.services.buffet.entity.BuffetEntity;
import com.taisf.services.buffet.manager.BuffetManagerImpl;
import com.taisf.services.buffet.vo.BuffetVO;
import com.taisf.services.device.common.BaseContext;
import com.taisf.services.device.common.valenum.DeviceStatusShowEnum;
import com.taisf.services.device.vo.CellModelCDVO;
import com.taisf.services.device.vo.DeviceCellCDVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2019/4/8.
 * @version 1.0
 * @since 1.0
 */
@Component("buffet.buffetServiceProxy")
public class BuffetServiceProxy extends BaseContext implements BuffetService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BuffetServiceProxy.class);

    @Resource(name = "buffet.buffetManagerImpl")
    private BuffetManagerImpl buffetManagerImpl;



    @Override
    public DataTransferObject<BuffetEntity> getBuffetLocal(BuffetInfoRequest buffetInfoRequest) {
        DataTransferObject<BuffetEntity> dto = new DataTransferObject<>();
        if (Check.NuNStr(buffetInfoRequest.getBuffetFid())){
            dto.setErrorMsg("参数异常");
            return dto;
        }
        BuffetEntity buffet = buffetManagerImpl.getBuffetByFid(buffetInfoRequest);
        if (Check.NuNObj(buffet)){
            dto.setErrorMsg("非法餐柜码");
            return dto;
        }
        dto.setData(buffet);
        return dto;
    }



    @Override
    public DataTransferObject<BuffetVO> getBuffetInfo(BuffetInfoRequest buffetInfoRequest) {
        DataTransferObject<BuffetVO> dto = new DataTransferObject<>();
        if (Check.NuNStr(buffetInfoRequest.getBuffetFid())){
            dto.setErrorMsg("参数异常");
            return dto;
        }
        BuffetEntity buffet = buffetManagerImpl.getBuffetByFid(buffetInfoRequest);
        if (Check.NuNObj(buffet)){
            dto.setErrorMsg("非法餐柜码");
            return dto;
        }
        //拼装当前的详细地址
        BuffetVO vo = buffet.tranBuffet2Vo();

        //处理当前的设备状态
        DeviceStatusShowEnum deviceStatusShowEnum = getLinkService(buffet.getConfigCode()).getDeviceStatusByDeviceId(buffet.getDeviceId());
        vo.setLinkStatus(deviceStatusShowEnum.getCode());
        vo.setLinkStatusDes(deviceStatusShowEnum.getName());

        dto.setData(vo);
        return dto;
    }


    @Override
    public BuffetEntity getBuffetByFid(BuffetInfoRequest buffetInfoRequest) {
        return buffetManagerImpl.getBuffetByFid(buffetInfoRequest);
    }
    @Override
    public BuffetEntity getBuffetByDeviceId(BuffetInfoRequest buffetInfoRequest) {
        return buffetManagerImpl.getBuffetByDeviceId(buffetInfoRequest);
    }



    @Override
    public DataTransferObject<Void> saveBuffet(BuffetEntity buffetEntity) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if(Check.NuNObj(buffetEntity)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            buffetManagerImpl.saveBuffet(buffetEntity);
        } catch(Exception e) {
            LogUtil.error(LOGGER, "【saveBuffet 】error:{}", e);
            dto.setErrorMsg("系统异常");
            return dto;
        }
        return dto;
    }

    @Override
    public DataTransferObject<Void> updateById(BuffetEntity buffetEntity) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        if(Check.NuNObj(buffetEntity)) {
            dto.setErrorMsg("参数异常");
            return dto;
        }
        try {
            buffetManagerImpl.updateById(buffetEntity);
        } catch(Exception e) {
            LogUtil.error(LOGGER, "【updateById 】error:{}", e);
            dto.setErrorMsg("系统异常");
            return dto;
        }
        return dto;
    }



    /**
     * 填充当前的设备在线状态
     * @param buffetEntity
     */
    private void fillDeviceStatus(BuffetEntity buffetEntity){
        if (Check.NuNObj(buffetEntity)){
            return;
        }
        String deviceId = buffetEntity.getDeviceId();
        if (Check.NuNStr(deviceId)){
            return;
        }
        DeviceStatusShowEnum deviceStatus = getLinkService(buffetEntity.getConfigCode()).getDeviceStatusByDeviceId(deviceId);
        buffetEntity.setLinkStatus(deviceStatus.getCode());
        buffetEntity.setLinkStatusDes(deviceStatus.getName());
    }



    /**
     * 填充当前的设备在线状态
     * @param list
     */
    private void fillDeviceStatus4List(List<BuffetEntity> list){
        if (Check.NuNCollection(list)){
            return;
        }
        for (BuffetEntity buffetEntity : list) {
            this.fillDeviceStatus(buffetEntity);
        }
    }

    /**
     * 获取设备列表
     * @param request
     * @return
     */
    @Override
    public DataTransferObject<PagingResult<BuffetEntity>> pageListBuffet(BuffetInfoRequest request){
        DataTransferObject<PagingResult<BuffetEntity>> dto = new DataTransferObject<>();
        try {
            PagingResult<BuffetEntity> pagingResult = buffetManagerImpl.pageListBuffet(request);
            fillDeviceStatus4List(pagingResult.getList());
            dto.setData(pagingResult);
        } catch(Exception e) {
            LogUtil.error(LOGGER, "【pageListBuffet 】error:{}", e);
            dto.setErrorMsg("系统异常");
            return dto;
        }
        return dto;
    }

    /**
     * 获取当前设备的格子状态
     * @param configCode
     * @param deviceId
     * @return
     */
    @Override
    public DataTransferObject<PagingResult<CellModelCDVO>> pageListCell(String configCode,String deviceId){
        DataTransferObject<PagingResult<CellModelCDVO>> dto = new DataTransferObject<>();
        if (Check.NuNStr(deviceId)){
            dto.setErrorMsg("参数异常");
            return dto;
        }
        if (Check.NuNStr(configCode)){
            dto.setErrorMsg("异常的设备配置信息");
            return dto;
        }

        //获取当前的设备状态
        DataTransferObject<DeviceCellCDVO> deviceStatusDto  = getLinkService(configCode).getDeviceCell(deviceId);;
        if (!deviceStatusDto.checkSuccess()){
            dto.setErrorMsg(deviceStatusDto.getMsg());
            return dto;
        }
        DeviceCellCDVO data =  deviceStatusDto.getData();
        if (Check.NuNObj(data)){
            dto.setErrorMsg("获取设备异常");
            return dto;
        }
        List<CellModelCDVO> cells = data.getCells();
        PagingResult<CellModelCDVO> page = new PagingResult<>();
        page.setList(cells);
        page.setTotal(cells.size());
        dto.setData(page);
        return dto;
    }


}
