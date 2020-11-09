package com.taisf.web.enterprise.buffet.vo;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2019/4/25.
 * @version 1.0
 * @since 1.0
 */
public class BuffetForceOpenRequest  extends BaseEntity {

    /**
     * 格子编号
     */
    private String cellNum;


    /**
     * 设备id
     */
    private String deviceId;


    /**
     * 设备配置
     */
    private  String configCode;


    public String getCellNum() {
        return cellNum;
    }

    public void setCellNum(String cellNum) {
        this.cellNum = cellNum;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }


    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }
}
