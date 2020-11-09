package com.taisf.services.enterprise.vo;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>企业统计信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/14.
 * @version 1.0
 * @since 1.0
 */
public class EnterpriseAccountVO extends BaseEntity{

    private static final long serialVersionUID = 301231231201446703L;


    private String enterpriseCode;

    private String enterpriseName;

    /**
     * 总额
     */
    private Integer allBalance = 0;

    /**
     * 可分配金额
     */
    private Integer drawBalance = 0;


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

    public Integer getAllBalance() {
        return allBalance;
    }

    public void setAllBalance(Integer allBalance) {
        this.allBalance = allBalance;
    }

    public Integer getDrawBalance() {
        return drawBalance;
    }

    public void setDrawBalance(Integer drawBalance) {
        this.drawBalance = drawBalance;
    }
}
