package com.taisf.services.user.entity;

import com.jk.framework.base.entity.BaseEntity;
import com.jk.framework.excel.annotation.FieldMeta;
import com.jk.framework.excel.annotation.MoneyPenny2Yuan;
import com.jk.framework.excel.annotation.TimeFormatPattern;

import java.util.Date;

/**
 * <p>账户信息变更记录</p>
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
public class AccountLogEntity extends BaseEntity {

    private static final long serialVersionUID = 30122342342346703L;


    @FieldMeta(skip = true)
    private Integer id;

    /**
     * 用户id
     */
    @FieldMeta(skip = true)
    private String userId;


    /**
     * 账户变更类型 1:收入 2:消费 3:提现 4:充值
     */
    @FieldMeta(skip = true)
    private Integer accountType;

    /**
     * 操作金额 单位:分
     */
    @MoneyPenny2Yuan
    @FieldMeta(name="金额",order=3)
    private Integer bizMoney;

    /**
     * 状态:1:处理完成  暂时现在所有的状态都是完成
     */
    @FieldMeta(skip = true)
    private Integer bizStatus;

    /**
     * 操作业务的编号
     */
    @FieldMeta(name="流水号",order=1)
    private String bizSn;

    /**
     * 创建时间
     */
    /**
     * 操作业务的编号
     */
    @TimeFormatPattern
    @FieldMeta(name="分配时间",order=2)
    private Date createTime;


    /**
     * 显示标题
     */
    @FieldMeta(skip = true)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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


    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }
    public Integer getBizMoney() {
        return bizMoney;
    }

    public void setBizMoney(Integer bizMoney) {
        this.bizMoney = bizMoney;
    }

    public Integer getBizStatus() {
        return bizStatus;
    }

    public void setBizStatus(Integer bizStatus) {
        this.bizStatus = bizStatus;
    }

    public String getBizSn() {
        return bizSn;
    }

    public void setBizSn(String bizSn) {
        this.bizSn = bizSn == null ? null : bizSn.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



}
