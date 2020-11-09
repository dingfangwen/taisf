package com.taisf.services.user.vo;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>基本属性</p>
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
public class IndexUserVO  extends BaseEntity {

    private static final long serialVersionUID = 301231231201446703L;


    /**
     * 用户id
     */
    private String userUid;


    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    /**
     * 用户名称
     */
    private String userName = "";

    /**
     * 用户头像
     */
    private String userPic = "";

    /**
     * 用户二维码
     */
    private String userCard = "";

    /**
     * 企业编码
     */
    private String enterpriseCode = "";

    /**
     * 企业名称
     */
    private String enterpriseName = "";



    /**
     * 供应商code
     */
    private String supplierCode;

    /**
     * 供应商name
     */
    private String supplierName;


    /**
     * 手机号
     */
    private String userPhone = "";


    /**
     * 是否管理员
     */
    private Integer isAdmin;

    /**
     * 是否设置免密
     */
    private Integer isPwd;


    /**
     * 是否设置支付密码
     */
    private Integer isAccount;


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
     * 支付码
     */
    private String payCode;

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public Integer getIsAccount() {
        return isAccount;
    }

    public void setIsAccount(Integer isAccount) {
        this.isAccount = isAccount;
    }

    public Integer getIsPwd() {
        return isPwd;
    }

    public void setIsPwd(Integer isPwd) {
        this.isPwd = isPwd;
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


    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
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

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getUserCard() {
        return userCard;
    }

    public void setUserCard(String userCard) {
        this.userCard = userCard;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }


    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
