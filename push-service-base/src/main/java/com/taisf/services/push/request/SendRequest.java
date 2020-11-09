package com.taisf.services.push.request;

import com.jk.framework.base.entity.BaseEntity;
import com.taisf.services.push.core.PushPar;


/**
 * <p>发送消息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/4/26.
 * @version 1.0
 * @since 1.0
 */
public abstract class SendRequest extends BaseEntity{

    /**
     * 用户id
     */
    private String userId;

    /**
     * token
     */
    private String token;
    /**
     * 消息类型
     */
    private String messageType;

    /**
     * 推送类型
     */
    private Integer pushType;

    public Integer getPushType() {
        return pushType;
    }

    public void setPushType(Integer pushType) {
        this.pushType = pushType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }


    protected abstract PushPar transPushPar();


}
