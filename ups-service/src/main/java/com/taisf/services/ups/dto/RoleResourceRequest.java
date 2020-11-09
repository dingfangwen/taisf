package com.taisf.services.ups.dto;


import com.jk.framework.base.page.PageRequest;

/**
 * 
 * <p>
 * 角色资源查询参数
 * </p>
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
public class RoleResourceRequest extends PageRequest {

	private static final long serialVersionUID = 883741615187803437L;

	/**
	 * 角色逻辑id
	 */
	private String roleFid;

	/**
	 * 资源逻辑id
	 */
	private String resFid;

	/**
	 * 资源名称
	 */
	private String resName;

	/**
	 * 资源逻辑id
	 */
	private String parentFid;

	/**
	 * 是否叶子节点
	 */
	private Integer isLeaf;

	public String getRoleFid() {
		return roleFid;
	}

	public void setRoleFid(String roleFid) {
		this.roleFid = roleFid;
	}

	public String getResFid() {
		return resFid;
	}

	public void setResFid(String resFid) {
		this.resFid = resFid;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getParentFid() {
		return parentFid;
	}

	public void setParentFid(String parentFid) {
		this.parentFid = parentFid;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

}
