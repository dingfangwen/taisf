package com.taisf.services.ups.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.taisf.services.ups.entity.CurrentuserEntity;
import com.taisf.services.ups.entity.RoleEntity;

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
public class CurrentuserVo extends CurrentuserEntity {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -2337422553697590673L;
	
	/**
	 * 员工姓名
	 */
	private String fullName;
	/**
	 * 岗位名称
	 */
	private String postName;
	/**
	 * 部门名称
	 */
	private String departmentName;
	/**
	 * 国家名称
	 */
	private String nationName;
	/**
	 * 省名称
	 */
	private String provinceName;
	/**
	 * 区域名称
	 */
	private String areaName;
	/**
	 * 城市名称
	 */
	private String cityName;
	
	/**
	 * 用户角色
	 */
	private List<String> roleFidList=new ArrayList<String>();
	
	/**
     * 角色列表
     */
    private List<RoleEntity>  roles ;
    
    /**
     * 功能菜单列表
     */
    private Set<String> resUrlSet;
    
    /**
     * 员工编号
     */
    private String empCode;
    
    /**
     * 负责区域code集合
     */
    private List<String> areaCodeList=new ArrayList<String>();


	/**
	 * @return the empCode
	 */
	public String getEmpCode() {
		return empCode;
	}

	/**
	 * @return the areaCodeList
	 */
	public List<String> getAreaCodeList() {
		return areaCodeList;
	}

	/**
	 * @param areaCodeList the areaCodeList to set
	 */
	public void setAreaCodeList(List<String> areaCodeList) {
		this.areaCodeList = areaCodeList;
	}

	/**
	 * @param empCode the empCode to set
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

	public List<String> getRoleFidList() {
		return roleFidList;
	}

	public void setRoleFidList(List<String> roleFidList) {
		this.roleFidList = roleFidList;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public Set<String> getResUrlSet() {
		return resUrlSet;
	}
	
	public void setResUrlSet(Set<String> resUrlSet) {
		this.resUrlSet = resUrlSet;
	}

	public String getNationName() {
		return nationName;
	}

	public void setNationName(String nationName) {
		this.nationName = nationName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
}
