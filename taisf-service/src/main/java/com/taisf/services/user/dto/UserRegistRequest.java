package com.taisf.services.user.dto;

import com.jk.framework.base.entity.BaseEntity;
import com.jk.framework.base.head.Header;

/**
 * <p>用户注册的请求参数</p>
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
public class UserRegistRequest extends BaseEntity{

    private static final long serialVersionUID = 301231231201446703L;

    /**
     * 头信息
     */
    private Header header;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 密码
     */
    private String pwd;



    /**
     * 密码
     */
    private String msgCode;


    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }
}
