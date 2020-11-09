package com.taisf.services.ups.vo;

import com.taisf.services.ups.entity.EmployeeRoleEntity;

/**
 * <p>后台用户vo</p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 * 
 * @author bushujie
 * @since 1.0
 * @version 1.0
 */
public class EmployeeRoleVo extends EmployeeRoleEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8251043059514665467L;
	/**
	 * 角色名称
	 */
	private String roleName;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
