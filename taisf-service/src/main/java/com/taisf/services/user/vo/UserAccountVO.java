package com.taisf.services.user.vo;

import com.taisf.services.user.entity.UserEntity;

/**
 * <p>用户账户试图</p>
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
public class UserAccountVO  extends UserEntity {

    /**
     * 用户状态 1：可用 2：禁用 3:冻结
     */
    private Integer accountStatus;

    /**
     * 用户可提现金额 单位:分
     */
    private Integer drawBalance;

    /**
     * 用户不可提现金额 单位:分
     */
    private Integer drawNotBalance;

    /**
     * 冻结金 单位:分
     */
    private Integer freezeMoney;

    /**
     * 用户积分
     */
    private Integer userIntegral;


    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Integer getDrawBalance() {
        return drawBalance;
    }

    public void setDrawBalance(Integer drawBalance) {
        this.drawBalance = drawBalance;
    }

    public Integer getDrawNotBalance() {
        return drawNotBalance;
    }

    public void setDrawNotBalance(Integer drawNotBalance) {
        this.drawNotBalance = drawNotBalance;
    }

    public Integer getFreezeMoney() {
        return freezeMoney;
    }

    public void setFreezeMoney(Integer freezeMoney) {
        this.freezeMoney = freezeMoney;
    }

    public Integer getUserIntegral() {
        return userIntegral;
    }

    public void setUserIntegral(Integer userIntegral) {
        this.userIntegral = userIntegral;
    }
}
