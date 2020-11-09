package com.taisf.services.supplier.vo;

import com.jk.framework.base.entity.BaseEntity;
import com.taisf.services.user.vo.FanVO;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/4/17.
 * @version 1.0
 * @since 1.0
 */
public class SelectInfo4Week  extends BaseEntity {


    /**
     * 商品的分类信息
     */
    private List<ProductClassifyInfo> productClassifyList = new ArrayList<>();


    /**
     * 订饭时间
     */
    private List<FanVO> timeList = new ArrayList<>();


    public List<ProductClassifyInfo> getProductClassifyList() {
        return productClassifyList;
    }

    public void setProductClassifyList(List<ProductClassifyInfo> productClassifyList) {
        this.productClassifyList = productClassifyList;
    }

    public List<FanVO> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<FanVO> timeList) {
        this.timeList = timeList;
    }
}
