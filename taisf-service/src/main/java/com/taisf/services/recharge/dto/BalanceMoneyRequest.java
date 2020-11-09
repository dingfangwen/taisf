package com.taisf.services.recharge.dto;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>单独充值</p>
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
public class BalanceMoneyRequest extends BaseEntity {


    private static final long serialVersionUID = 301231231201446703L;


    /**
     * 电话
     */
    private String userPhone;


    /**
     * 金额
     */
    private Double moneyYuan = 0.0;

    /**
     * 金额 分
     */
    private Integer moneyPen = 0;

    /**
     * 用户id
     */
    private String userUid;

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public Integer getMoneyPen() {
        return moneyPen;
    }

    public void setMoneyPen(Integer moneyPen) {
        this.moneyPen = moneyPen;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Double getMoneyYuan() {
        return moneyYuan;
    }

    public void setMoneyYuan(Double moneyYuan) {
        this.moneyYuan = moneyYuan;
    }
}
