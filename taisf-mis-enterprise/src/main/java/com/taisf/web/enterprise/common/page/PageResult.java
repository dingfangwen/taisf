
package com.taisf.web.enterprise.common.page;

import java.util.ArrayList;


/**
 * <p>分页查询返回json格式</p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 * 
 * @author bushujie
 * @since 1.0
 * @version 1.0
 */
public class PageResult {

	/**
	 * 分页查询数据
	 */
	private Object rows = new ArrayList<>();
	
	/**
	 * 总记录数
	 */
	private Long total=0L;

	/**
	 * @return the total
	 */
	public Long getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
	}

	/**
	 * @return the rows
	 */
	public Object getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(Object rows) {
		this.rows = rows;
	}
}
