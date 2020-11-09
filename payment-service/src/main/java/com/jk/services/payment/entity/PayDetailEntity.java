package com.jk.services.payment.entity;

import com.jk.framework.base.entity.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>支付的明细</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/5/11.
 * @version 1.0
 * @since 1.0
 */
public class PayDetailEntity extends BaseEntity{

    private static final long serialVersionUID = 301231231201446703L;

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 外键 t_pay id
     */
    private Integer payId;
    // 业务id
    private String bizId;
    /**
     * 业务名称
     */
    private String bizName;
    /**
     * 金额
     */
    private Integer amount;

    /**
     * 转出流水号
     */
    private String outSerialNo;
    /**
     * 转入流水号
     */
    private String inSerialNo;
    /**
     * 状态
     */
    private String status;
    /**
     * 状态描述
     */
    private String statusDesc;

    /**
     * 描述信息
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

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId == null ? null : bizId.trim();
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName == null ? null : bizName.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }



    public String getOutSerialNo() {
        return outSerialNo;
    }

    public void setOutSerialNo(String outSerialNo) {
        this.outSerialNo = outSerialNo == null ? null : outSerialNo.trim();
    }

    public String getInSerialNo() {
        return inSerialNo;
    }

    public void setInSerialNo(String inSerialNo) {
        this.inSerialNo = inSerialNo == null ? null : inSerialNo.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getStatusDesc() {
        return statusDesc;
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


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
