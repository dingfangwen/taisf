package com.taisf.services.recharge.dto;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>充值参数</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/13.
 * @version 1.0
 * @since 1.0
 */
public class ChargeRequest extends BaseEntity {

    private static final long serialVersionUID = 301231231201446703L;

    /**
     * 企业code
     */
    private String enterpriseCode;

    /**
     * 金额 元
     */
    private Double moneyYuan;

    /**
     * 操作人名称
     */
    private String opName;

    /**
     * 操作人id
     */
    private String opId;

    /**
     * 充值类型
     */
    private Integer rechargeType;

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

    public Double getMoneyYuan() {
        return moneyYuan;
    }

    public void setMoneyYuan(Double moneyYuan) {
        this.moneyYuan = moneyYuan;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public String getOpId() {
        return opId;
    }

    public void setOpId(String opId) {
        this.opId = opId;
    }
}
