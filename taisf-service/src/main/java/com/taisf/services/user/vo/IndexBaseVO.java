package com.taisf.services.user.vo;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>首页</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/12.
 * @version 1.0
 * @since 1.0
 */
public class IndexBaseVO extends BaseEntity {
    /**
     * 用户信息
     */
    private IndexUserVO userInfo;


    /**
     * 余额
     */
    private Integer drawBalance;

    public IndexUserVO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(IndexUserVO userInfo) {
        this.userInfo = userInfo;
    }

    public Integer getDrawBalance() {
        return drawBalance;
    }

    public void setDrawBalance(Integer drawBalance) {
        this.drawBalance = drawBalance;
    }
}
