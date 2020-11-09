package com.taisf.services.order.vo;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>用户</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/11.
 * @version 1.0
 * @since 1.0
 */
public class UserSimpleVO extends BaseEntity {


    private static final long serialVersionUID = 301231231201446703L;

    /**
     * 余额
     */
    private Integer drawBalance;

    /**
     * 用户id
     */
    private String userUid;

    /**
     * 默认设置了密码
     */
    private Boolean pwdFlag = true;


    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }


    public Boolean getPwdFlag() {
        return pwdFlag;
    }

    public void setPwdFlag(Boolean pwdFlag) {
        this.pwdFlag = pwdFlag;
    }

    public Integer getDrawBalance() {
        return drawBalance;
    }

    public void setDrawBalance(Integer drawBalance) {
        this.drawBalance = drawBalance;
    }
}
