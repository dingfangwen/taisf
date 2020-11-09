package com.taisf.services.device.logic.aaa.command.response;


import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.Check;
import com.taisf.services.device.logic.aaa.command.base.LinkResponse;
import com.taisf.services.device.logic.aaa.command.model.LinkCellModel;
import com.taisf.services.device.vo.CellModelCDVO;
import com.taisf.services.device.vo.DeviceCellCDVO;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
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
 * @author afi on on 2019/4/1.
 * @version 1.0
 * @since 1.0
 */
public class LinkGetDeviceAllCellStatusResponse extends LinkResponse {
    @JsonProperty("Data")
    private LinkGetDeviceAllCellStatusResponse.Data data;

    public LinkGetDeviceAllCellStatusResponse() {
    }

    public LinkGetDeviceAllCellStatusResponse.Data getData() {
        return this.data;
    }

    public void setData(LinkGetDeviceAllCellStatusResponse.Data data) {
        this.data = data;
    }


    /**
     * 转化返回信息
     * @return
     */
    @Override
    public DataTransferObject<DeviceCellCDVO> transCd(){
        DataTransferObject<DeviceCellCDVO> dto = new DataTransferObject<>();
        if (!getSuccess()){
            dto.setErrorMsg(getMsg());
            return dto;
        }
        DeviceCellCDVO deviceCellCDVO = new DeviceCellCDVO();
        List<CellModelCDVO> cells = null;
        if (!Check.NuNCollection(data.cells)){
            cells = new ArrayList<>();
            for (LinkCellModel cell : data.cells) {
                CellModelCDVO vo = new CellModelCDVO();
                BeanUtils.copyProperties(cell,vo);
                cells.add(vo);
            }
        }
        deviceCellCDVO.setCells(cells);
        dto.setData(deviceCellCDVO);
        return dto;
    }



    public class Data {

        @JsonProperty("DeviceName")
        private String deviceId;
        @JsonProperty("Cells")
        private List<LinkCellModel> cells;

        public Data() {
        }

        public String getDeviceId() {
            return this.deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public List<LinkCellModel> getCells() {
            return this.cells;
        }

        public void setCells(List<LinkCellModel> cells) {
            this.cells = cells;
        }
    }
}
