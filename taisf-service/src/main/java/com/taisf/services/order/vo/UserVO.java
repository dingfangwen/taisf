package com.taisf.services.order.vo;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>用户</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/11.
 * @version 1.0
 * @since 1.0
 */
public class UserVO extends BaseEntity {


    private static final long serialVersionUID = 301231231201446703L;


    /**
     * 用户id
     */
    private String userUid;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 企业编码
     */
    private String enterpriseCode;

    /**
     * 企业名称
     */
    private String enterpriseName;


    /**
     * 手机号
     */
    private String userPhone;



    /**
     * 用户角色
     */
    private Integer userRole;

    /**
     * 员工号
     */
    private String userCode;


    /**
     * 餐属性
     */
    private Integer productSource;

    /**
     * 默认设置了密码
     */
    private Boolean pwdFlag = true;

    /**
     * 是否免密
     */
    private Boolean freePwd = false;


    public Boolean getFreePwd() {
        return freePwd;
    }

    public void setFreePwd(Boolean freePwd) {
        this.freePwd = freePwd;
    }


    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
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

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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
        this.productSource = productSource;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Boolean getPwdFlag() {
        return pwdFlag;
    }

    public void setPwdFlag(Boolean pwdFlag) {
        this.pwdFlag = pwdFlag;
    }
}
