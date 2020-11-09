package com.taisf.services.user.entity;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

/**
 * <p>账户信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/3/21.
 * @version 1.0
 * @since 1.0
 */
public class UserAccountEntity extends BaseEntity {

    private static final long serialVersionUID = 30122342446703L;


    private Integer id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 交易密码
     */
    private String accountPassword;

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


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除 0:未删除 1:已经删除
     */
    private Short isDel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

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


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Short getIsDel() {
        return isDel;
    }

    public void setIsDel(Short isDel) {
        this.isDel = isDel;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }
}
