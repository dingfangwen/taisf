package com.taisf.services.enterprise.entity;


import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>
 * 企联系人
 * </p>
 * <p/>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2017/9/20.
 * @version 1.0
 * @since 1.0
 */
public class EnterpriseContactsEntity extends BaseEntity {

    /** 序列化id */
    private static final long serialVersionUID = 7380668097871543955L;
    private Integer id;

    private String fid;

    private String enterpriseCode;

    private String conName;

    private String conMail;

    private String conTel;

    private String conMobile;

    private String qqNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid == null ? null : fid.trim();
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode == null ? null : enterpriseCode.trim();
    }

    public String getConName() {
        return conName;
    }

    public void setConName(String conName) {
        this.conName = conName == null ? null : conName.trim();
    }

    public String getConMail() {
        return conMail;
    }

    public void setConMail(String conMail) {
        this.conMail = conMail == null ? null : conMail.trim();
    }

    public String getConTel() {
        return conTel;
    }

    public void setConTel(String conTel) {
        this.conTel = conTel == null ? null : conTel.trim();
    }

    public String getConMobile() {
        return conMobile;
    }

    public void setConMobile(String conMobile) {
        this.conMobile = conMobile == null ? null : conMobile.trim();
    }

    public String getQqNo() {
        return qqNo;
    }

    public void setQqNo(String qqNo) {
        this.qqNo = qqNo == null ? null : qqNo.trim();
    }
}