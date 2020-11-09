package com.taisf.services.recharge.dto;

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
 * @author afi on on 2017/9/25.
 * @version 1.0
 * @since 1.0
 */
public class ChargeHisRequest extends PageRequest {

    private static final long serialVersionUID = 301231231201446703L;


    /**
     * 流水号
     */
    private String rechargeSn;

    /**
     * 充值类型
     */
    private Integer rechargeType;


    private String enterpriseCode;

    private String enterpriseName;

    /**
     * 充值电话
     */
    private String rechargeTel;

    private String createId;

    private String startStr;

    private String endStr;


    private Date start;

    private Date end;


    private String supplierCode;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }


    public String getRechargeSn() {
        return rechargeSn;
    }

    public void setRechargeSn(String rechargeSn) {
        this.rechargeSn = rechargeSn;
    }

    public Integer getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(Integer rechargeType) {
        this.rechargeType = rechargeType;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getRechargeTel() {
        return rechargeTel;
    }

    public void setRechargeTel(String rechargeTel) {
        this.rechargeTel = rechargeTel;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
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
