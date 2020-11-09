package com.taisf.services.stock.vo;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>存储在DB层的统计信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/3/23.
 * @version 1.0
 * @since 1.0
 */
public class StockDbVO extends BaseEntity {

    private static final long serialVersionUID = 301231231201446703L;


    /**
     * 商品的code
     */
    private  Integer p;


    /**
     * 商品类型
     */
    private  Integer t;


    /**
     * 商品的占用数
     */
    private  Integer n;


    /**
     * 限制数量
     */
    private Integer l;


    public Integer getL() {
        return l;
    }

    public void setL(Integer l) {
        this.l = l;
    }

    public Integer getP() {
        return p;
    }

    public void setP(Integer p) {
        this.p = p;
    }

    public Integer getT() {
        return t;
    }

    public void setT(Integer t) {
        this.t = t;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }
}
