package com.taisf.services.buffet.dto;

import com.jk.framework.base.page.PageRequest;

/**
 * <p>清空购物车</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/9.
 * @version 1.0
 * @since 1.0
 */
public class BuffetInfoRequest extends PageRequest {

    /**
     * 设备编号
     */
    private String buffetFid;
    private String deviceId;
    /**
     * 设备配置
     */
    private  String configCode;
    private Integer Id;
    private Integer status;
    private Integer cellNum;

    /**
     * 省code
     */
    private String provinceCode;

    /**
     * 城市code
     */
    private String cityCode;
    private String supplierCode;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCellNum() {
        return cellNum;
    }

    public void setCellNum(Integer cellNum) {
        this.cellNum = cellNum;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getBuffetFid() {
        return buffetFid;
    }

    public void setBuffetFid(String buffetFid) {
        this.buffetFid = buffetFid;
    }

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }
}
