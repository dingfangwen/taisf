package com.taisf.services.ups.entity;


import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

/**
 * 
 * <p>系统员工实体类</p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 * 
 * @author afi
 * @since 1.0
 * @version 1.0
 */
public class EmployeeEntity extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1416134443047939199L;

	private Integer id;

	private String userId;

	private String userPwd;

    private String empCode;

    private String empName;

    private Integer empSex;

    private String empMail;

    private String empMobile;

    private Integer empValid;

	private String departName;

    private Date createDate;

    private String empBiz;

	/**
	 * 医院ID
	 */
	private Integer hospitalId;

	/**
	 * 科室ID
	 */
	private Integer departmentId;

	/**
	 * 医院名称
	 */
	private String hospitalName;

	private int userRole;

	public String getEmpBiz() {
		return empBiz;
	}

	public void setEmpBiz(String empBiz) {
		this.empBiz = empBiz;
	}

	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Integer getEmpSex() {
		return empSex;
	}

	public void setEmpSex(Integer empSex) {
		this.empSex = empSex;
	}

	public String getEmpMail() {
		return empMail;
	}

	public void setEmpMail(String empMail) {
		this.empMail = empMail;
	}

	public String getEmpMobile() {
		return empMobile;
	}

	public void setEmpMobile(String empMobile) {
		this.empMobile = empMobile;
	}

	public Integer getEmpValid() {
		return empValid;
	}

	public void setEmpValid(Integer empValid) {
		this.empValid = empValid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
}