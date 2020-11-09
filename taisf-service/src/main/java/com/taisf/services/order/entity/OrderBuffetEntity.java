package com.taisf.services.order.entity;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

/**
 * <p>定点杆餐柜信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/14.
 * @version 1.0
 * @since 1.0
 */
public class OrderBuffetEntity  extends BaseEntity{
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 骑手uid
     */
    private String knightUid;

    /**
     * 餐柜fid
     */
    private String buffetFid;

    /**
     * 格子编号
     */
    private String cellSn;

    /**
     * 取餐时间
     */
    private Date takeTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date updateTime;

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
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public String getKnightUid() {
        return knightUid;
    }

    public void setKnightUid(String knightUid) {
        this.knightUid = knightUid == null ? null : knightUid.trim();
    }

    public String getBuffetFid() {
        return buffetFid;
    }

    public void setBuffetFid(String buffetFid) {
        this.buffetFid = buffetFid == null ? null : buffetFid.trim();
    }

    public String getCellSn() {
        return cellSn;
    }

    public void setCellSn(String cellSn) {
        this.cellSn = cellSn;
    }

    public Date getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Date takeTime) {
        this.takeTime = takeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}