package com.taisf.services.recharge.vo;

/**
 * <p>企业角色数量</p>
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
public class EnterpriseStatsNumber {


    /**
     * 提现金额
     */
    private Integer drawBalance;

    /**
     * 老板数量
     */
    private Integer bossNum = 0;


    /**
     * 普通数量
     */
    private Integer empNum = 0;

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

    public Integer getDrawBalance() {
        return drawBalance;
    }

    public void setDrawBalance(Integer drawBalance) {
        this.drawBalance = drawBalance;
    }
}
