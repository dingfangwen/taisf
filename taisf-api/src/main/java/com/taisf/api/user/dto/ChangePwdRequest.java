package com.taisf.api.user.dto;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>修改密码</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/14.
 * @version 1.0
 * @since 1.0
 */
public class ChangePwdRequest extends BaseEntity {
    /**
     * 原始密码
     */
    private String oldPwd;


    /**
     * 原始密码
     */
    private String newPwd;

    /**
     * 验证码
     */
    private String msgCode;

    /**
     * 是否免密
     */
    private Integer isPWd;

    public Integer getIsPWd() {
        return isPWd;
    }

    public void setIsPWd(Integer isPWd) {
        this.isPWd = isPWd;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }
}
