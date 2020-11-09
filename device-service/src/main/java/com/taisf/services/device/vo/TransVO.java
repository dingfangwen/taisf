package com.taisf.services.device.vo;

import com.jk.framework.base.entity.BaseEntity;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * <p>数据的返回结果</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2019/3/26.
 * @version 1.0
 * @since 1.0
 */
public class TransVO extends BaseEntity {

    /**
     * 消息
     */
    @JsonProperty(value = "Msg")
    private String msg;


    /**
     * 是否乘车
     */
    @JsonProperty(value = "Success")
    private Boolean success;

    /**
     * 数据
     */
    @JsonProperty(value = "Data")
    private Object data;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
