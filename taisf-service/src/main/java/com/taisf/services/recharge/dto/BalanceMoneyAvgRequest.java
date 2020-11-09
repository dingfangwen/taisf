package com.taisf.services.recharge.dto;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>平均分配余额</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/16.
 * @version 1.0
 * @since 1.0
 */
public class BalanceMoneyAvgRequest extends BaseEntity{

    private static final long serialVersionUID = 301231231201446703L;


    private String enterpriseCode;


    /**
     * 老板数量
     */
    private Integer bossNum = 0;


    /**
     * 普通数量
     */
    private Integer empNum = 0;



    /**
     * 老板金额
     */
    private Double bossMoneyYuan = 0.0;


    /**
     * 员工金额
     */
    private Double empMoneyYuan = 0.0;


    public Integer getBossNum() {
        return bossNum;
    }

    public void setBossNum(Integer bossNum) {
        this.bossNum = bossNum;
    }

    public Integer getEmpNum() {
        return empNum;
    }

    public void setEmpNum(Integer empNum) {
        this.empNum = empNum;
    }

    public Double getBossMoneyYuan() {
        return bossMoneyYuan;
    }

    public void setBossMoneyYuan(Double bossMoneyYuan) {
        this.bossMoneyYuan = bossMoneyYuan;
    }

    public Double getEmpMoneyYuan() {
        return empMoneyYuan;
    }

    public void setEmpMoneyYuan(Double empMoneyYuan) {
        this.empMoneyYuan = empMoneyYuan;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }
}
