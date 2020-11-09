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
public class UserLogoutRequest extends BaseEntity{

    private static final long serialVersionUID = 301231231201446703L;

    /**
     * 头信息
     */
    private Header header;

    /**
     *
     */
    private String token;


    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
