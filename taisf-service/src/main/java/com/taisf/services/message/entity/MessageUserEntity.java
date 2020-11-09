package com.taisf.services.message.entity;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>用户消息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2019/4/24.
 * @version 1.0
 * @since 1.0
 */
public class MessageUserEntity extends BaseEntity {

    private static final long serialVersionUID = -1396734085107067060L;
    /**
     * 消息号
     */
    private String messageFid;



    /**
     * 消息号
     */
    private String userId;


    public String getMessageFid() {
        return messageFid;
    }

    public void setMessageFid(String messageFid) {
        this.messageFid = messageFid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
