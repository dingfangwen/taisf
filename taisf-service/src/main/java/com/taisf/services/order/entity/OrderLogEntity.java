package com.taisf.services.order.entity;


import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;


/**
 * <p>订单操作记录实体类</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2017/9/12.
 * @version 1.0
 * @since 1.0
 */
public class OrderLogEntity extends BaseEntity {


    /** 序列化id  */
    private static final long serialVersionUID = -12312312325171L;

    /** id */
    private Integer id;

    /** 订单编号 */
    private String orderSn;

    /** 开始状态 */
    private Integer fromStatus;

    /** 结束状态 */
    private Integer toStatus;


    /** 提示名 */
    private String title;

    /** 备注 */
    private String remark;

    /** 创建人 */
    private String createId;

    /** 创建时间 */
    private Date createDate;

    /** 是否删除 0：不显示 1：显示 */
    private Integer isShow;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Integer getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(Integer fromStatus) {
        this.fromStatus = fromStatus;
    }

    public Integer getToStatus() {
        return toStatus;
    }

    public void setToStatus(Integer toStatus) {
        this.toStatus = toStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
}
