package com.taisf.services.device.logic.aaa.command.request;

import com.taisf.services.device.logic.aaa.command.base.LinkRequest;
import com.taisf.services.device.logic.aaa.command.response.LinkResetCellResponse;
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
public class LinkResetCellRequest extends LinkBaseRequest implements LinkRequest<LinkResetCellResponse> {

    @JsonProperty("DeviceId")
    private String deviceId;
    @JsonProperty("CellNo")
    private String cellNo;
    @JsonProperty("OutOrderId")
    private String outOrderId;
    @JsonProperty("FrontDoor")
    private Boolean frontDoor;

    public LinkResetCellRequest() {
    }

    @Override
    public String getUri() {
        return "/api/open/reset/cell";
    }

    @Override
    public Class getResponseClass() {
        return LinkResetCellResponse.class;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCellNo() {
        return this.cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }

    public String getOutOrderId() {
        return this.outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    public Boolean getFrontDoor() {
        return this.frontDoor;
    }

    public void setFrontDoor(Boolean frontDoor) {
        this.frontDoor = frontDoor;
    }
}
