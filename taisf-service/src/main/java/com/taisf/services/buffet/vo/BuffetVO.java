package com.taisf.services.buffet.vo;

import com.taisf.services.buffet.entity.BuffetEntity;

/**
 * <p>餐柜信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2019/4/24.
 * @version 1.0
 * @since 1.0
 */
public class BuffetVO  extends BuffetEntity {

    /**
     * 详细信息
     */
    private String addressFull;


    public String getAddressFull() {
        return addressFull;
    }

    public void setAddressFull(String addressFull) {
        this.addressFull = addressFull;
    }
}
