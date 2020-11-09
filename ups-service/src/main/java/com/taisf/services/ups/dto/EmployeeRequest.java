package com.taisf.services.ups.dto;


import com.jk.framework.base.page.PageRequest;

/**
 * 
 * <p>请求参数</p>
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
public class EmployeeRequest extends PageRequest {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 4625548985466290939L;
	
	/**
	 * 员工姓名
	 */
	private String empName;
	
	/**
	 * 员工编号
	 */
	private String empMail;
	
	/**
	 * 员工手机号
	 */
	private String empMobile;

	/**
	 * 企业编号
	 */
	private String supplierCode;


	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
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
}
