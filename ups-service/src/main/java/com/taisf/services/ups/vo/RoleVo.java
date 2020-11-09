package com.taisf.services.ups.vo;

import com.taisf.services.ups.entity.RoleEntity;

/**
 * 
 * <p>后台角色vo</p>
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
public class RoleVo extends RoleEntity {
	
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -3998156582202798405L;
	
	/**
	 * 创建人姓名
	 */
	private String createrName;

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

}
