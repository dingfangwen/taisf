package com.taisf.services.user.entity;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

/**
 * <p>用户的收货地址</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/15.
 * @version 1.0
 * @since 1.0
 */
public class UserAddressEntity  extends BaseEntity{


    /** id */
    private Integer id;

    /**
     * fid
     */
    private String fid;

    /** 用户Uid */
    private String userUid;

    /** 用户电话 */
    private String userTel;

    /** 用户名称 */
    private String userName;

    /** 用户性别 */
    private Integer userSex;

    /** 标签 1:公司 */
    private Integer addressLabel;


    /**  省code */
    private String provinceCode;

    /**  省名称*/
    private String provinceName;


    /** 城市code */
    private String cityCode;

    /** 区域code */
    private String areaCode;

    /** 城市名称 */
    private String cityName;

    /** 区域名称 */
    private String areaName;

    /**
     * 门牌号
     */
    private String doorNo;

    /** 街道地址 */
    private String street;

    /** 创建时间 */
    private Date createTime;

    /** 最后修改时间 */
    private Date lastModifyDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public Integer getAddressLabel() {
        return addressLabel;
    }

    public void setAddressLabel(Integer addressLabel) {
        this.addressLabel = addressLabel;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }
}


