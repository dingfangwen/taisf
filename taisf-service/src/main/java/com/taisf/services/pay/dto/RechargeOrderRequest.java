package com.taisf.services.pay.dto;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>充值的参数</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/4/16.
 * @version 1.0
 * @since 1.0
 */
public class RechargeOrderRequest  extends BaseEntity {

    private static final long serialVersionUID = 301222341446703L;

    /**
     * userID
     */
    private String userUid;

    /**
     * 需要支付金额
     */
    private Integer needMoney;

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public Integer getNeedMoney() {
        return needMoney;
    }

    public void setNeedMoney(Integer needMoney) {
        this.needMoney = needMoney;
    }
}
