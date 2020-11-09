package com.taisf.services.buffet.entity;

import com.jk.framework.base.entity.BaseEntity;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.ValueUtil;
import com.taisf.services.buffet.vo.BuffetVO;
import org.springframework.beans.BeanUtils;

import java.util.Date;


/**
 * <p>餐柜</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/14.
 * @version 1.0
 * @since 1.0
 */
public class BuffetEntity  extends BaseEntity {


    private Integer id;

    /**
     * 设备编号
     */
    private String deviceId;

    /**
     * 配置编码
     */
    private String configCode;

    /**
     * 餐柜编码
     */
    private String fid;


    /**
     * 供应商code
     */
    private String supplierCode;



    /**
     * 格子数量
     */
    private Integer cellNum;

    /**
     * 地址
     */
    private String address;

    /**
     * 省code
     */
    private String provinceCode;

    /**
     * 城市code
     */
    private String cityCode;

    /**
     * 县城/区code
     */
    private String countyCode;

    /**
     * 状态
     */
    private Integer status;


    /**
     * 在线状态
     */
    private Integer linkStatus;


    /**
     * 在线状态描述
     */
    private String linkStatusDes;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date updateTime;

    private String provinceName;

    private String cityName;

    private String countyName;


    public Integer getLinkStatus() {
        return linkStatus;
    }

    public void setLinkStatus(Integer linkStatus) {
        this.linkStatus = linkStatus;
    }

    public String getLinkStatusDes() {
        return linkStatusDes;
    }

    public void setLinkStatusDes(String linkStatusDes) {
        this.linkStatusDes = linkStatusDes;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid == null ? null : fid.trim();
    }

    public Integer getCellNum() {
        return cellNum;
    }

    public void setCellNum(Integer cellNum) {
        this.cellNum = cellNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode == null ? null : countyCode.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    /**
     * 转化当前的餐柜信息
     * @author afi
     * @return
     */
    public BuffetVO tranBuffet2Vo(){

        BuffetVO vo = new BuffetVO();

        BeanUtils.copyProperties(this,vo);
        //设置全地址
        vo.setAddressFull(tranAddressFull());
        return vo;
    }


    /**
     * 拼接参数地址
     * @return
     */
    public String tranAddressFull(){
        String addressFull = ValueUtil.getStrValue(provinceName)
                + ValueUtil.getStrValue(cityName)
                + ValueUtil.getStrValue(countyName)
                + ValueUtil.getStrValue(address);
        return address;
    }
}