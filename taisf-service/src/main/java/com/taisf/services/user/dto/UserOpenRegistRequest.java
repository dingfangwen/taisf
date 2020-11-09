package com.taisf.services.user.dto;



/**
 * <p>新用户的注册场景</p>
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
public class UserOpenRegistRequest extends UserRegistRequest{

    private static final long serialVersionUID = 301231231201446703L;


    /**
     * 用户的姓名
     */
    private String userName;

    /**
     * 企业编码
     */
    private String enterpriseCode;


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
}
