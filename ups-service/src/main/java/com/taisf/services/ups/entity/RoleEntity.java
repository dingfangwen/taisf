package com.taisf.services.ups.entity;


import com.jk.framework.base.entity.BaseEntity;
import com.taisf.services.ups.entity.CurrentuserEntity;

import java.util.Date;

/**
 * 
 * <p>后台角色实体</p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 * 
 * @author liujun
 * @since 1.0
 * @version 1.0
 */
public class RoleEntity extends BaseEntity {
    /**
	 * 序列化id
	 */
	private static final long serialVersionUID = 2266821857352379718L;

	/**
	 * 主键
	 */
    private Integer id;

    /**
     * 逻辑主键
     */
    private String fid;

    /**
     * 系统FID
     */
    private String systemsFid;

    /**
     * 角色名称
     */
    private String roleName;
    
    /**
     * 角色类型，0:普通角色，1：数据区域角色，2：区域角色，3：数据角色
     */
    private Integer roleType;

	/**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 是否删除
     */
    private Integer isDel;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 修改日期
     */
    private Date modifyDate;

    /**
     * 创建人fid
     * @see {@value CurrentuserEntity#fid}
     */
    private String createFid;

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

    public String getSystemsFid() {
        return systemsFid;
    }

    public void setSystemsFid(String systemsFid) {
        this.systemsFid = systemsFid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getCreateFid() {
        return createFid;
    }

    public void setCreateFid(String createFid) {
        this.createFid = createFid == null ? null : createFid.trim();
    }
    
    /**
	 * @return the roleType
	 */
	public Integer getRoleType() {
		return roleType;
	}

	/**
	 * @param roleType the roleType to set
	 */
	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}
}