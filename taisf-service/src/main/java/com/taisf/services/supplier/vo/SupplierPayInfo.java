package com.taisf.services.supplier.vo;

import com.taisf.services.supplier.entity.SupplierEntity;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/4/17.
 * @version 1.0
 * @since 1.0
 */
public class SupplierPayInfo  extends SupplierEntity {


    /**
     * 支付码
     */
    private String payCode;

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }
}
