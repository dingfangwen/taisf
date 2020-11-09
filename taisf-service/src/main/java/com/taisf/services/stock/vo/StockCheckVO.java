package com.taisf.services.stock.vo;

import com.jk.framework.base.utils.ValueUtil;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/3/26.
 * @version 1.0
 * @since 1.0
 */
public class StockCheckVO  extends StockHasVO {

    private static final long serialVersionUID = 301231231201446703L;


    /**
     * 限制的id
     */
    private Integer limitId;

    /**
     * 商品数量限制
     */
    private Integer productLimit;

    public Integer getProductLimit() {
        return productLimit;
    }

    public void setProductLimit(Integer productLimit) {
        this.productLimit = productLimit;
    }

    public Integer getLimitId() {
        return limitId;
    }

    public void setLimitId(Integer limitId) {
        this.limitId = limitId;
    }


    /**
     * 获取当前的数量限制
     * @return
     */
    public int checkLast(){
        Integer last = ValueUtil.getintValue(productLimit) - ValueUtil.getintValue(getProductNum());
        if (last <= 0){
            last = 0;
        }
        return last;
    }
}
