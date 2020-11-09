package com.taisf.services.device.logic.aaa.command.request;

import com.taisf.services.device.logic.aaa.command.base.LinkRequest;
import com.taisf.services.device.logic.aaa.command.response.LinkOpenCellsResponse;
import org.codehaus.jackson.annotate.JsonProperty;

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
public class LinkOpenCellsRequest extends LinkBaseRequest implements LinkRequest<LinkOpenCellsResponse> {

    @JsonProperty("DeviceId")
    private String deviceId;
    @JsonProperty("CellNos")
    private String cellNos;

    public LinkOpenCellsRequest() {
    }

    @Override
    public String getUri() {
        return "/api/open/opencells";
    }

    @Override
    public Class getResponseClass() {
        return LinkOpenCellsResponse.class;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCellNos() {
        return this.cellNos;
    }

    public void setCellNos(String cellNos) {
        this.cellNos = cellNos;
    }
}
