package com.taisf.services.ups.entity;

import com.jk.framework.base.entity.BaseEntity;

public class EmployeeRoleEntity extends BaseEntity{
    /**
	 * 序列化id
	 */
	private static final long serialVersionUID = 8773923836544735753L;

	/**
     * 主键id
     */
    private Integer id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色逻辑id
     */
    private String roleFid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRoleFid() {
        return roleFid;
    }

    public void setRoleFid(String roleFid) {
        this.roleFid = roleFid == null ? null : roleFid.trim();
    }
}