package com.taisf.services.device.logic.aaa.command.request;


import com.taisf.services.device.logic.aaa.command.base.LinkRequest;
import com.taisf.services.device.logic.aaa.command.response.LinkLockCellResponse;
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
public class LinkLockCellRequest extends LinkBaseRequest implements LinkRequest<LinkLockCellResponse> {

    @JsonProperty("DeviceId")
    private String deviceId;
    @JsonProperty("CellNo")
    private String cellNo;

    public LinkLockCellRequest() {
    }

    @Override
    public String getUri() {
        return "/api/open/lock/cell";
    }

    @Override
    public Class getResponseClass() {
        return LinkLockCellResponse.class;
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
}
