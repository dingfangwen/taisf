package com.taisf.services.refund.entity;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

/**
 * <p>退款的操作记录</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/3/30.
 * @version 1.0
 * @since 1.0
 */
public class RefundLogEntity extends BaseEntity {

    private static final long serialVersionUID = 301234223120146703L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 支付业务单号
     */
    private String refundSn;

    /**
     * 状态从
     */
    private Integer statusFrom;

    /**
     * 状态到
     */
    private Integer statusTo;

    /**
     * 操作人
     */
    private String opName;

    /**
     * 操作人uid
     */
    private String opUid;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRefundSn() {
        return refundSn;
    }

    public void setRefundSn(String refundSn) {
        this.refundSn = refundSn == null ? null : refundSn.trim();
    }

    public Integer getStatusFrom() {
        return statusFrom;
    }

    public void setStatusFrom(Integer statusFrom) {
        this.statusFrom = statusFrom;
    }

    public Integer getStatusTo() {
        return statusTo;
    }

    public void setStatusTo(Integer statusTo) {
        this.statusTo = statusTo;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName == null ? null : opName.trim();
    }

    public String getOpUid() {
        return opUid;
    }

    public void setOpUid(String opUid) {
        this.opUid = opUid == null ? null : opUid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
