package com.taisf.services.ups.dto;


import com.jk.framework.base.page.PageRequest;

/**
 * <p>后台用户查询参数</p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期	2016/08/02	修改人		赵龙	  修改内容	添加 roleName查询条件
 * </PRE>
 * 
 * @author bushujie
 * @since 1.0
 * @version 1.0
 */
public class CurrentuserRequest extends PageRequest {
	
	private static final long serialVersionUID = 5209948200306918224L;
	/**
	 * 账户名称查询
	 */
	private String userName;
	/**
	 * 员工名称查询
	 */
	private String fullName;
	/**
	 * 状态查询
	 */
	private Integer accountStatus;
    /**
     * 角色名称查询
     */
    private String roleName;
    /**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * 
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the accountStatus
	 */
	public Integer getAccountStatus() {
		return accountStatus;
	}
	/**
	 * @param accountStatus the accountStatus to set
	 */
	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}
}
