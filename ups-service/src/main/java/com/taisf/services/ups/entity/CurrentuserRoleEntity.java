package com.taisf.services.ups.entity;


import com.jk.framework.base.entity.BaseEntity;

/**
 *
 * <p>用户角色关系</p>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi
 * @since 1.0
 * @version 1.0
 */
public class CurrentuserRoleEntity extends BaseEntity {

	private static final long serialVersionUID = 8052882026565009836L;

	private Integer id;

    /**
     * currenuserFid
     */
    private String currenuserFid;

    /**
     * roleFid
     */
    private String roleFid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrenuserFid() {
        return currenuserFid;
    }

    public void setCurrenuserFid(String currenuserFid) {
        this.currenuserFid = currenuserFid;
    }

    public String getRoleFid() {
        return roleFid;
    }

    public void setRoleFid(String roleFid) {
        this.roleFid = roleFid;
    }
}
