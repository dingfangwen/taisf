package com.taisf.services.device.logic.aaa.command.request;

import com.taisf.services.device.logic.aaa.command.base.LinkRequest;
import com.taisf.services.device.logic.aaa.command.response.LinkOpenCellResponse;
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
public class LinkOpenCellRequest  extends LinkBaseRequest implements LinkRequest<LinkOpenCellResponse> {
    @JsonProperty("DeviceId")
    private String deviceId;
    @JsonProperty("CellNos")
    private String cellNos;
    @JsonProperty("OutOrderId")
    private String outOrderId;

    public LinkOpenCellRequest() {
    }

    @Override
    public String getUri() {
        return "/api/open/opencell";
    }

    @Override
    public Class getResponseClass() {
        return LinkOpenCellResponse.class;
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

    public String getOutOrderId() {
        return this.outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }


}
