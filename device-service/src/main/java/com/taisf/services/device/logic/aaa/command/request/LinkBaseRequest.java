package com.taisf.services.device.logic.aaa.command.request;

import com.jk.framework.base.entity.BaseEntity;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * <p>基础的入参</p>
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
public abstract class LinkBaseRequest  extends BaseEntity  {

    @JsonProperty("AppId")
    private String appId;

    @JsonProperty("Timestamp")
    private Long timestamp = System.currentTimeMillis();

    @JsonProperty("Digest")
    private String digest;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }
}
