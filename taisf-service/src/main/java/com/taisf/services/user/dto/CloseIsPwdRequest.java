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
public class CloseIsPwdRequest extends PageRequest{

    private static final long serialVersionUID = 564564654564545403L;

    /**
     * 密码
     */
    private String accountPwd;

    public String getAccountPwd() {
        return accountPwd;
    }

    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }
}
