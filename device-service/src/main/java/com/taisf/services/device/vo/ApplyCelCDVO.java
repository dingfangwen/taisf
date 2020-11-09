package com.taisf.services.device.vo;

import com.jk.framework.base.entity.BaseEntity;

import java.util.List;

/**
 * <p>TODO</p>
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
public class ApplyCelCDVO extends BaseEntity {



    public ApplyCelCDVO() {
    }

    private String deviceId;
    private String outOrderId;
    private List<String> cellNos;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    public List<String> getCellNos() {
        return cellNos;
    }

    public void setCellNos(List<String> cellNos) {
        this.cellNos = cellNos;
    }
}
