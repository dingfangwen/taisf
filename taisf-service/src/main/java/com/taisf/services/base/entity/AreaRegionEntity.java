package com.taisf.services.base.entity;

import com.jk.framework.base.entity.BaseEntity;


/**
 * <p> 城市 </p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @version 1.0
 * @since 1.0
 */
public class AreaRegionEntity extends BaseEntity{


	private static final long serialVersionUID = -5951195560340448554L;

	/**
     * 区域代码
     */
    private Integer code;

    /**
     * 区域名称
     */
    private String name;

    /**
     * 父级代码
     */
    private Integer parentCode;
    
    /**
     * 层级 国家：1；省份：2；城市：3；区域：4 最顶层显示根节点为：0
     */
    private Short level;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentCode() {
        return parentCode;
    }

    public void setParentCode(Integer parentCode) {
        this.parentCode = parentCode;
    }

	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}
    
}