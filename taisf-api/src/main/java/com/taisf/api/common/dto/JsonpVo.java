package com.taisf.api.common.dto;


import com.jk.framework.base.entity.BaseEntity;
import com.jk.framework.base.entity.DataTransferObject;

/**
 * <p>TODO</p>
 * <p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi
 * @version 1.0
 * @since 1.0
 */
public class JsonpVo extends BaseEntity {

    private String callBack;

    private DataTransferObject dto = new DataTransferObject();

    public String getCallBack() {
        return callBack;
    }

    public void setCallBack(String callBack) {
        this.callBack = callBack;
    }

    public DataTransferObject getDto() {
        return dto;
    }

    public void setDto(DataTransferObject dto) {
        this.dto = dto;
    }
}
