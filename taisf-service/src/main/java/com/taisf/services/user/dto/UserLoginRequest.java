package com.taisf.services.user.dto;

import com.jk.framework.base.entity.BaseEntity;
import com.jk.framework.base.head.Header;

/**
 * <p>用户登录请求参数</p>
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
public class UserLoginRequest extends UserLoginCodeRequest{



    /**
     * 密码
     */
    private String pwd;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


}
