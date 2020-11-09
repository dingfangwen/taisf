package com.taisf.services.push.core;


import com.jk.framework.base.entity.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>极光的推送参数细信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/1.
 * @version 1.0
 * @since 1.0
 */
public class PushPar extends BaseEntity {

    private static final long serialVersionUID = 3011321446703L;


    /**
     * 标题
     */
    private String title = "";


    /**
     * 内容
     */
    private String content;


    /**
     * 极光的token信息
     */
    private List<String> token;

    private Map<String,String> extra;

    private Integer sendType;





    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getToken() {
        return token;
    }

    public void setToken(List<String> token) {
        this.token = token;
    }

    public Map<String, String> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, String> extra) {
        this.extra = extra;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }
}
