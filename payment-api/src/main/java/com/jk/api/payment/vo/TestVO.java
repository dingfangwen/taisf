package com.jk.api.payment.vo;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/5/8.
 * @version 1.0
 * @since 1.0
 */
public class TestVO extends BaseEntity{

    private static final long serialVersionUID = 30122246703L;

    /**
     * 字段描述信息
     */
    private String aa;


    /**
     * 字段bb描述信息
     */
    private Integer bb;


    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }

    public Integer getBb() {
        return bb;
    }

    public void setBb(Integer bb) {
        this.bb = bb;
    }
}
