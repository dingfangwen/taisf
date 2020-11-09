package com.taisf.services.order.dto;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>清空购物车</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/9.
 * @version 1.0
 * @since 1.0
 */
public class CartCleanRequest extends BaseEntity {

    /**
     * 用户id
     */
    private String userUid;

    /**
     * 商家id
     */
    private String businessUid;

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getBusinessUid() {
        return businessUid;
    }

    public void setBusinessUid(String businessUid) {
        this.businessUid = businessUid;
    }
}
