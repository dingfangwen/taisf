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
public class UserLoginCodeRequest extends BaseEntity{

    /**
     * 头信息
     */
    private Header header;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 验证码
     */
    private String msgCode;

    /**
     * 放入redis的key
     */
    private String redisKey;

    public String getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(String redisKey) {
        this.redisKey = redisKey;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }
}
