package com.taisf.services.order.vo;

import com.jk.framework.base.entity.BaseEntity;
import com.jk.framework.excel.annotation.FieldMeta;

/**
 * <p>每日任务</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/11/8.
 * @version 1.0
 * @since 1.0
 */
public class DayTaskVO extends BaseEntity {

    private static final long serialVersionUID = 301231231201446703L;

    /**
     * 名称
     */
    @FieldMeta(name="名称",order=4)
    private String productName;

    /**
     * 数量
     */
    @FieldMeta(name="数量",order=4)
    private Integer  productNum;

    /**
     * 分类
     */
    @FieldMeta(name="分类",order=4)
    private String  productClassify;

    /**
     * 窗口
     */
    @FieldMeta(name="窗口",order=4)
    private String windowName;

    public String getWindowName() {
        return windowName;
    }

    public void setWindowName(String windowName) {
        this.windowName = windowName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public String getProductClassify() {
        return productClassify;
    }

    public void setProductClassify(String productClassify) {
        this.productClassify = productClassify;
    }
}
