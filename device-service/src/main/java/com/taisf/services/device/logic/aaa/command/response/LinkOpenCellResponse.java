package com.taisf.services.device.logic.aaa.command.response;

import com.taisf.services.device.logic.aaa.command.base.LinkResponse;
import com.taisf.services.device.logic.aaa.command.model.LinkCellModel;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * <p>打开单个格子结果</p>
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
public class LinkOpenCellResponse extends LinkResponse {

    @JsonProperty("Data")
    private LinkOpenCellResponse.Data data;

    public LinkOpenCellResponse() {
    }

    public LinkOpenCellResponse.Data getData() {
        return this.data;
    }

    public void setData(LinkOpenCellResponse.Data data) {
        this.data = data;
    }

    public class Data {
        @JsonProperty("AppId")
        private String appId;
        @JsonProperty("DeviceId")
        private String deviceId;
        @JsonProperty("CellNos")
        private List<LinkCellModel> cells;

        public Data() {
        }

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
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
