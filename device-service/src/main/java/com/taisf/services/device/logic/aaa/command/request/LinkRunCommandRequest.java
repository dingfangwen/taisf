package com.taisf.services.device.logic.aaa.command.request;

import com.taisf.services.device.logic.aaa.command.base.LinkRequest;
import com.taisf.services.device.logic.aaa.command.response.LinkRunCommandResponse;
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
public class LinkRunCommandRequest extends LinkBaseRequest implements LinkRequest<LinkRunCommandResponse> {

    @JsonProperty("DeviceId")
    private String deviceId;
    @JsonProperty("CellNos")
    private String cellNos;
    @JsonProperty("CommandCode")
    private String commandCode;

    public LinkRunCommandRequest() {
    }

    @Override
    public String getUri() {
        return "/api/open/runcommand";
    }

    @Override
    public Class getResponseClass() {
        return LinkRunCommandResponse.class;
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

    public String getCommandCode() {
        return this.commandCode;
    }

    public void setCommandCode(String commandCode) {
        this.commandCode = commandCode;
    }
}
