package com.taisf.services.ups.api;

import java.util.List;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.taisf.services.ups.entity.ResourceEntity;
import com.taisf.services.ups.vo.TreeNodeVo;

/**
 * <p>
 * 后台菜单操作接口
 * </p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 * 
 * @author liyingjie
 * @since 1.0
 * @version 1.0
 */
public interface ResourceService {



	/**
	 * 更新菜单信息
	 * @param paramJson
	 * @return
	 */
	String updateMenuByFid(String paramJson);


	/**
	 * 
	 * 条件查询菜单列表
	 *
	 * @author liyingjie
	 * @created 2016年3月9日 上午11:26:09
	 *
	 * @param paramJson
	 * @return
	 */
	public DataTransferObject<PagingResult<ResourceEntity>> searchMenuResList(String paramJson);

	/**
	 * 
	 * 新增 菜单 数据
	 *
	 * @author liyingjie
	 * @created 2016年3月11日 下午8:26:21
	 *
	 * @param paramJson
	 */
	public void insertMenuResource(String paramJson);

	/**
	 * 查询所有的菜单和其子节点
	 *
	 * @author liyingjie
	 * @created 2016年3月11日 下午10:48:13
	 *
	 * @return
	 */
	public String searchAllMenuChildResList();
	
	/**
	 * 
	 * 资源树结构查询
	 *
	 * @author bushujie
	 * @created 2016年3月13日 下午7:11:16
	 *
	 * @return
	 */
	public String menuTreeVo();
	/**
	 * 根据用户id查询  用户资源权限
	 * @param resUrl
	 * @return
	 */
	public String  selectByUserId(String userId);

	/**
	 * @author:zhangzhengguang
	 * @date:2017/7/26
	 * @description:首页加载当前用户拥有的菜单权限
	 **/
	List<TreeNodeVo> findResourceByCurrentUserId(String currentuserId);
	
}
