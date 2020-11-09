package com.taisf.services.enterprise.entity;


import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>
 * 企业的财务相关
 * </p>
 * <p/>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2017/9/20.
 * @version 1.0
 * @since 1.0
 */
public class EnterpriseFinanceEntity extends BaseEntity {

    /** 序列化id */
    private static final long serialVersionUID = 7380668097871543955L;
    private Integer id;

    private String enterpriseCode;

    /**
     * 供餐单位名称
     */
    private String financeName;

    /**
     * 账期 T + x
     */
    private Integer feeDay;

    /**
     * 结算方式 1.月结算
     */
    private Integer checkType;

    /**
     * 发票抬头
     */
    private String invoiceTitle;

    /**
     * 账号
     */
    private String enterpriseAccount;

    /**
     * 税号
     */
    private String enterpriseTax;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode == null ? null : enterpriseCode.trim();
    }

    public String getFinanceName() {
        return financeName;
    }

    public void setFinanceName(String financeName) {
        this.financeName = financeName == null ? null : financeName.trim();
    }

    public Integer getFeeDay() {
        return feeDay;
    }

    public void setFeeDay(Integer feeDay) {
        this.feeDay = feeDay;
    }

    public Integer getCheckType() {
        return checkType;
    }

    public void setCheckType(Integer checkType) {
        this.checkType = checkType;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
    }

    public String getEnterpriseAccount() {
        return enterpriseAccount;
    }

    public void setEnterpriseAccount(String enterpriseAccount) {
        this.enterpriseAccount = enterpriseAccount == null ? null : enterpriseAccount.trim();
    }

    public String getEnterpriseTax() {
        return enterpriseTax;
    }

    public void setEnterpriseTax(String enterpriseTax) {
        this.enterpriseTax = enterpriseTax == null ? null : enterpriseTax.trim();
    }
}