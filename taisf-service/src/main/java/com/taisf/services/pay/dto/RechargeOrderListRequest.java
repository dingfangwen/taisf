package com.taisf.services.pay.dto;

import com.jk.framework.base.page.PageRequest;

import java.util.Date;

/**
 * <p>充值记录</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/5/31.
 * @version 1.0
 * @since 1.0
 */
public class RechargeOrderListRequest extends PageRequest {

    private static final long serialVersionUID = 564564654564545403L;

    /**
     * 电话
     */
    private String userPhone;


    /**
     * 企业编号
     */
    private String supplierCode;

    private String startStr;

    private String endStr;


    private Date start;

    private Date end;


    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getStartStr() {
        return startStr;
    }

    public void setStartStr(String startStr) {
        this.startStr = startStr;
    }

    public String getEndStr() {
        return endStr;
    }

    public void setEndStr(String endStr) {
        this.endStr = endStr;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
