package com.taisf.services.device.logic.aaa.command.request;


import com.taisf.services.device.logic.aaa.command.base.LinkRequest;
import com.taisf.services.device.logic.aaa.command.response.LinkGetDeviceAllCellStatusResponse;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * <p>获取所有设备状态</p>
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
public class LinkGetDeviceAllCellStatusRequest extends LinkBaseRequest implements LinkRequest<LinkGetDeviceAllCellStatusResponse> {

    @JsonProperty("DeviceId")
    private String deviceId;

    public LinkGetDeviceAllCellStatusRequest() {
    }

    @Override
    public String getUri() {
        return "/api/open/query/all";
    }

    @Override
    public Class getResponseClass() {
        return LinkGetDeviceAllCellStatusResponse.class;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
