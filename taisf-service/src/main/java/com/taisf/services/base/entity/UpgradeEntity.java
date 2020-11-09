package com.taisf.services.base.entity;


import com.jk.framework.base.entity.BaseEntity;

/**
 * <p> 版本信息 </p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @version 1.0
 * @since 1.0
 */
public class UpgradeEntity extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6994429764656942130L;

    /**
     * 版本code
     */
    private Integer versionCode;

    /**
     * 版本名称
     */
    private String versionName;


    private String md5file;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 下载地址
     */
    private String url;

    /**
     * 是否强制升级
     */
    private transient Integer isForce;

    /**
     * 指定版本
     */
    private transient Integer specificEdition;
    /**
     * 最低版本
     */
    private transient Integer minVersion;

    private Boolean fourceUpGradeFlag = false;
    
    private Boolean promtUpGradeFlag = false;


    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getMd5file() {
        return md5file;
    }

    public void setMd5file(String md5file) {
        this.md5file = md5file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsForce() {
        return isForce;
    }

    public void setIsForce(Integer isForce) {
        this.isForce = isForce;
    }

    public Integer getSpecificEdition() {
        return specificEdition;
    }

    public void setSpecificEdition(Integer specificEdition) {
        this.specificEdition = specificEdition;
    }

    public Integer getMinVersion() {
        return minVersion;
    }

    public void setMinVersion(Integer minVersion) {
        this.minVersion = minVersion;
    }

    public Boolean getFourceUpGradeFlag() {
        return fourceUpGradeFlag;
    }

    public void setFourceUpGradeFlag(Boolean fourceUpGradeFlag) {
        this.fourceUpGradeFlag = fourceUpGradeFlag;
    }

    public Boolean getPromtUpGradeFlag() {
        return promtUpGradeFlag;
    }

    public void setPromtUpGradeFlag(Boolean promtUpGradeFlag) {
        this.promtUpGradeFlag = promtUpGradeFlag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}