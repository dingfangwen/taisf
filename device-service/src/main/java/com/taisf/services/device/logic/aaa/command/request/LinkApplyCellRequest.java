package com.taisf.services.device.logic.aaa.command.request;


import com.taisf.services.device.logic.aaa.command.base.LinkRequest;
import com.taisf.services.device.logic.aaa.command.response.LinkApplyCellResponse;
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
public class LinkApplyCellRequest extends LinkBaseRequest implements LinkRequest<LinkApplyCellResponse> {

    @JsonProperty("DeviceId")
    private String deviceId;
    @JsonProperty("ApplyNum")
    private Integer applyNum;
    @JsonProperty("OutOrderId")
    private String outOrderId;
    @JsonProperty("UserId")
    private String userId;
    @JsonProperty("ShopName")
    private String shopName;

    public LinkApplyCellRequest() {
    }

    @Override
    public String getUri() {
        return "/api/open/applycell";
    }

    @Override
    public Class getResponseClass() {
        return LinkApplyCellResponse.class;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getApplyNum() {
        return this.applyNum;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }

    public String getOutOrderId() {
        return this.outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShopName() {
        return this.shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
