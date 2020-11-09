package com.taisf.services.device.logic.aaa.command.base;

import com.jk.framework.base.entity.BaseEntity;
import com.jk.framework.base.entity.DataTransferObject;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * <p>返回结果</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2019/4/1.
 * @version 1.0
 * @since 1.0
 */
public class LinkResponse extends BaseEntity{

    @JsonProperty("Success")
    private Boolean success;

    @JsonProperty("Code")
    private Integer code;

    @JsonProperty("Msg")
    private String msg;

    public LinkResponse() {
    }

    public Boolean getSuccess() {
        if(null == success){
            return false;
        }
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataTransferObject transCd(){
        DataTransferObject dto = new DataTransferObject();

        if (!getSuccess()){
            dto.setErrorMsg(getMsg());
        }
        return dto;
    }
}
