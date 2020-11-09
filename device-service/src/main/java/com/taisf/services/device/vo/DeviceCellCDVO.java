package com.taisf.services.device.vo;

import com.jk.framework.base.entity.BaseEntity;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * <p></p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2020/4/12 16:35
 * @version 1.0
 */
public class DeviceCellCDVO extends BaseEntity {


    private Integer status;

    private String statusDesc;

    private String deviceId;

    private List<CellModelCDVO> cells;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public List<CellModelCDVO> getCells() {
        return cells;
    }

    public void setCells(List<CellModelCDVO> cells) {
        this.cells = cells;
    }
}
