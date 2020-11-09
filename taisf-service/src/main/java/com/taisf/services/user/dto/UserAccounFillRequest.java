package com.taisf.services.user.dto;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>用户的充值</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/16.
 * @version 1.0
 * @since 1.0
 */
public class UserAccounFillRequest  extends BaseEntity {

    private static final long serialVersionUID = 301231231201446703L;

    /**
     * uid
     */
    private String userId;

    private Integer money;


    private String bizSn;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getBizSn() {
        return bizSn;
    }

    public void setBizSn(String bizSn) {
        this.bizSn = bizSn;
    }
}
