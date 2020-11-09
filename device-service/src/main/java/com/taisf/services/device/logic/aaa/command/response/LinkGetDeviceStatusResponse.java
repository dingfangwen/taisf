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
public class LinkGetDeviceStatusResponse extends LinkResponse {

    @JsonProperty("Data")
    private LinkGetDeviceStatusResponse.Data data;

    public LinkGetDeviceStatusResponse() {
    }

    public LinkGetDeviceStatusResponse.Data getData() {
        return this.data;
    }

    public void setData(LinkGetDeviceStatusResponse.Data data) {
        this.data = data;
    }



    public class Data {

        @JsonProperty("Status")
        private Integer status;

        @JsonProperty("StatusDesc")
        private String statusDesc;

        @JsonProperty("DeviceId")
        private String deviceId;

        @JsonProperty("Cells")
        private List<LinkCellModel> cells;


        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public List<LinkCellModel> getCells() {
            return cells;
        }

        public void setCells(List<LinkCellModel> cells) {
            this.cells = cells;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getStatusDesc() {
            return statusDesc;
        }

        public void setStatusDesc(String statusDesc) {
            this.statusDesc = statusDesc;
        }
    }
}
