package com.taisf.services.ups.dto;

import com.jk.framework.base.page.PageRequest;

/**
 * <p>
 * 后台菜单查询参数
 * </p>
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
public class ResourceRequest extends PageRequest {

	private static final long serialVersionUID = -3844034830675663909L;
	/**
	 * 父类id查询
	 */
	private String parent_fid;

	public String getParent_fid() {
		return parent_fid;
	}

	public void setParent_fid(String parent_fid) {
		this.parent_fid = parent_fid;
	}

}
