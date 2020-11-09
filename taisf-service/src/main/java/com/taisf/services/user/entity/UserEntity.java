package com.taisf.services.user.entity;

import com.jk.framework.base.entity.BaseEntity;
import com.jk.framework.base.utils.Check;
import com.jk.framework.excel.annotation.FieldMeta;
import com.jk.framework.excel.annotation.ForceStr;
import com.jk.framework.excel.annotation.MoneyPenny2Yuan;
import com.taisf.services.common.valenum.ProductSourceEnum;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2378420290094051498L;

	@FieldMeta(name="ID",order=1)
	@ForceStr
	private Integer id;


	/**
	 * 员工号
	 */
	@FieldMeta(name="员工号",order=3)
	private String userCode;

	/**
	 * 用户名称
	 */
	@FieldMeta(name="用户姓名",order=2)
	private String userName;

	@FieldMeta(name="注册时间",order=3)
	@DateTimeFormat
	private Date createTime;


	/**
	 * 手机号
	 */
	@FieldMeta(name="手机号",order=3)
	private String userPhone;

	/**
	 * 企业名称
	 */
	@FieldMeta(name="企业名称",order=4)
	private String enterpriseName;



	/**
	 * 餐属性
	 */
	@FieldMeta(name="餐食标准",order=4)
	private String productSourceStr;




	@FieldMeta(name="账户金额(元)",order=4)
	@MoneyPenny2Yuan
	private Integer amount;

	@FieldMeta(name="账户状态",order=4)
	private String accountStatusStr;

	private Integer accountStatus;

	/**
	 * 用户id
	 */
	private String userUid;




	/**
	 * 企业编码
	 */
	private String enterpriseCode;





	/**
	 * 密码
	 */
	private transient String userPassword;

	/**
	 * 用户状态 1：可用 2：禁用
	 */
	private Integer userStatus;

	/**
	 * 用户类型
	 */
	private Integer userType;

	/**
	 * 用户角色
	 */
	private Integer userRole;


	/**
	 * 餐属性
	 */
	private Integer productSource;

	/**
	 * 是否管理员
	 */
	private Integer isAdmin;


	/**
	 * 是否面密码支付
	 */
	private Integer isPwd;




	/**
	 * 用户状态： 1:注册成功 2：认证审核中 3：认证通过
	 */
	private Integer userBusinessStatus;



	private Date updateTime;


	private String QrCode;

	private String empMail;

	/**
	 * 业务编码
	 */
	private String bizCode;

	/**
	 * 支付码
	 */
	private String payCode;


	public String getProductSourceStr() {
		return productSourceStr;
	}

	public void setProductSourceStr(String productSourceStr) {
		this.productSourceStr = productSourceStr;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public String getEmpMail() {
		return empMail;
	}

	public void setEmpMail(String empMail) {
		this.empMail = empMail;
	}

	public String getQrCode() {
		return QrCode;
	}

	public void setQrCode(String qrCode) {
		QrCode = qrCode;
	}

	public Integer getAccountStatus() {
		return accountStatus;
	}

	public String getAccountStatusStr() {
		return accountStatusStr;
	}

	public void setAccountStatusStr(String accountStatusStr) {
		this.accountStatusStr = accountStatusStr;
	}

	public void setAccountStatus(Integer accountStatus) {

		this.accountStatus = accountStatus;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone == null ? null : userPhone.trim();
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword == null ? null : userPassword.trim();
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		if(!Check.NuNObj(userStatus)){
			if(userStatus == 1){
				accountStatusStr  = "可用";
			}else if(userStatus == 2){
				accountStatusStr  = "激活";
			}else if(userStatus == 3){
				accountStatusStr  = "注销";
			}else if(userStatus == 4){
				accountStatusStr  = "冻结";
			}
		}
		this.userStatus = userStatus;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getUserBusinessStatus() {
		return userBusinessStatus;
	}

	public void setUserBusinessStatus(Integer userBusinessStatus) {
		this.userBusinessStatus = userBusinessStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getUserUid() {
		return userUid;
	}

	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}

	public Integer getUserRole() {
		return userRole;
	}

	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}

	public Integer getProductSource() {
		return productSource;
	}

	public void setProductSource(Integer productSource) {
		if(!Check.NuNObj(productSource)){
			ProductSourceEnum productSourceEnum = ProductSourceEnum.getTypeByCode(productSource);
			if (!Check.NuNObj(productSourceEnum)){
			switch (productSourceEnum){
				case QINGZHEN:
				this.productSourceStr = productSourceEnum.getName();
				break;
				case XICAN:
				this.productSourceStr = productSourceEnum.getName();
				break;
				case COMMON:
				this.productSourceStr = productSourceEnum.getName();
				break;
				default:
			}
			}
		}
		this.productSource = productSource;
	}


	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getIsPwd() {
		return isPwd;
	}

	public void setIsPwd(Integer isPwd) {
		this.isPwd = isPwd;
	}
}