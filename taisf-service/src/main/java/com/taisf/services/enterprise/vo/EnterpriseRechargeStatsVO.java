package com.taisf.services.enterprise.vo;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>企业订单充值统计新</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/20.
 * @version 1.0
 * @since 1.0
 */
public class EnterpriseRechargeStatsVO extends BaseEntity{

    private static final long serialVersionUID = 301231231201446703L;

    private String time;

    private String enterpriseCode;

    private String enterpriseName;

    /**
     * 充值次数
     */
    private Integer num;

    /**
     * 充值金额
     */
    private Integer price;


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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
