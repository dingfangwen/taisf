package com.taisf.services.device.logic.aaa.command.response;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.Check;
import com.taisf.services.device.logic.aaa.command.base.LinkResponse;
import org.codehaus.jackson.annotate.JsonProperty;

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
public class LinkApplyCellResponse  extends LinkResponse {


    @JsonProperty("Data")
    private LinkApplyCellResponse.Data data;

    public LinkApplyCellResponse() {
    }

    public LinkApplyCellResponse.Data getData() {
        return this.data;
    }

    public void setData(LinkApplyCellResponse.Data data) {
        this.data = data;
    }


    @Override
    public  DataTransferObject<String>  transCd(){
        DataTransferObject<String> dto = new DataTransferObject<>();
        if (!getSuccess()){
            dto.setErrorMsg(getMsg());
            return dto;
        }
        String cellNo = null;
        if (!Check.NuNCollection(this.data.cellNos)){
            cellNo = this.data.cellNos.get(0);
        }
        dto.setData(cellNo);

        return dto;
    }


    /**
     * 申请格子的类型
     */
    public class Data {

        @JsonProperty("DeviceId")
        private String deviceId;
        @JsonProperty("OutOrderId")
        private String outOrderId;
        @JsonProperty("CellNos")
        private List<String> cellNos;

        public Data() {
        }

        public String getOutOrderId() {
            return this.outOrderId;
        }

        public void setOutOrderId(String outOrderId) {
            this.outOrderId = outOrderId;
        }

        public String getDeviceId() {
            return this.deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public List<String> getCellNos() {
            return this.cellNos;
        }

        public void setCellNos(List<String> cellNos) {
            this.cellNos = cellNos;
        }
    }
}
