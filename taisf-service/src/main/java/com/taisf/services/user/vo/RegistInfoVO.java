package com.taisf.services.user.vo;

import com.jk.framework.base.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>登录成功返回信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/6.
 * @version 1.0
 * @since 1.0
 */
public class RegistInfoVO extends BaseEntity {

    private static final long serialVersionUID = 301231231201446703L;

    /**
     * 用户token
     */
    private String userToken;

    /**
     * 手机号
     */
    private String userPhone;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 员工号
     */
    private String userCode;

    /**
     * 企业编码
     */
    private String enterpriseCode;

    /**
     * 企业名称
     */
    private String enterpriseName;

    /**
     * 供餐信息
     */
    private String userRoleName;

    /**
     * 余额
     */
    private Integer drawBalance = 0;


    /**
     * 送餐地址
     */
    private List<String>  addrList = new ArrayList<>();


    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public Integer getDrawBalance() {
        return drawBalance;
    }

    public void setDrawBalance(Integer drawBalance) {
        this.drawBalance = drawBalance;
    }

    public List<String> getAddrList() {
        return addrList;
    }

    public void setAddrList(List<String> addrList) {
        this.addrList = addrList;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
