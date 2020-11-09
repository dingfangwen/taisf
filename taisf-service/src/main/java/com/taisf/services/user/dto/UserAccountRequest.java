package com.taisf.services.user.dto;

import com.jk.framework.base.page.PageRequest;

/**
 * <p>用户账户的请求参数</p>
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
public class UserAccountRequest extends PageRequest{

    private static final long serialVersionUID = 564564654564545403L;

    /**
     * 企业code
     */
    private String enterpriseCode;
    /**
     * 电话
     */
    private String userPhone;


    /**
     * 员工号
     */
    private String userCode;

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
