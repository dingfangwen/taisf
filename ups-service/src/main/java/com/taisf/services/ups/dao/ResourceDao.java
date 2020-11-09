package com.taisf.services.ups.dao;

import java.util.List;

import com.taisf.services.common.UpsBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jk.framework.base.exception.BusinessException;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.dao.page.PageBounds;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.ups.dto.ResourceRequest;
import com.taisf.services.ups.entity.ResourceEntity;
import com.taisf.services.ups.vo.ResourceVo;
import com.taisf.services.ups.vo.TreeNodeVo;

/**
 *
 * <p>
 * 后台菜单操作类
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
@Repository("ups.resourceDao")
public class ResourceDao extends UpsBaseDao {

	private String SQLID = "ups.resourceDao.";

	/**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(ResourceDao.class);

	/**
	 * 更新菜单信息
	 * 
	 * @param resourceEntity
	 */
	public void updateMenuByFid(ResourceEntity resourceEntity) {
		if (Check.NuNObj(resourceEntity)) {
			return;
		}
		if (Check.NuNStr(resourceEntity.getFid())) {
			LogUtil.info(logger,"on updateMenuByFid the fid is null ");
			throw new BusinessException("on updateMenuByFid the fid is null ");
		}
		mybatisDaoContext.save(SQLID + "updateMenuByFid", resourceEntity);
	}

	/**
	 *
	 * 插入菜单表记录
	 *
	 * @author afi
	 * @created 2016年3月8日 下午4:10:16
	 *
	 * @param resourceEntity
	 */
	public void insertMenuResource(ResourceEntity resourceEntity) {
		mybatisDaoContext.save(SQLID + "insertMenuResource", resourceEntity);
	}

	/**
	 *
	 * 分页查询菜单列表
	 *
	 * @author afi
	 * @created 2016年3月9日 上午10:54:08
	 *
	 * @param resourceRequest
	 * @return
	 */
	public PagingResult<ResourceEntity> findMenuOperPageList(ResourceRequest resourceRequest) {
		PageBounds pageBounds = new PageBounds();
		pageBounds.setLimit(resourceRequest.getLimit());
		pageBounds.setPage(resourceRequest.getPage());
		return mybatisDaoContext.findForPage(SQLID + "findMenuOperByCondition", ResourceEntity.class, resourceRequest,
				pageBounds);
	}

	/**
	 *
	 * 查询所有菜单
	 *
	 * @author afi
	 * @created 2016年3月11日 下午10:21:41
	 *
	 * @return
	 */
	public List<ResourceEntity> findAllMenuClasterChildren() {
		return mybatisDaoContext.findAll(SQLID + "findMenuClasterChildren");
	}

	/**
	 *
	 * 资源树查询
	 *
	 * @author afi
	 * @created 2016年3月13日 下午7:00:37
	 *
	 * @return
	 */
	public List<TreeNodeVo> findTreeNodeVoList() {
		return mybatisDaoContext.findAll(SQLID + "findMenuTreeVo", TreeNodeVo.class);
	}
	
	/**
	 * 
	 * 用户权限树
	 *
	 * @author afi
	 * @created 2016年3月16日 下午9:56:57
	 *
	 * @param currentuserId
	 * @return
	 */
	public List<ResourceVo> findResourceByCurrentuserId(String currentuserId){
		return mybatisDaoContext.findAll(SQLID+"findResouresByUser", ResourceVo.class, currentuserId);
	}
	
	/**
	 * 
	 * 根据url 查询资源
	 * 说明：
	 * 1.理论上 一个url对应一个菜单 
	 * 2. 对于存在一个url对应多个菜单的情况，给出日志
	 *
	 * @author yd
	 * @created 2016年10月31日 下午6:58:26
	 *
	 * @param url
	 * @return
	 */
	public List<ResourceEntity> findResourceByUrl(String resUrl){
		return mybatisDaoContext.findAll(SQLID+"findResourceByUrl", ResourceEntity.class, resUrl);
	}
	/**
	 * 根据用户id查询  用户资源权限
	 * @param resUrl
	 * @return
	 */
	public List<ResourceEntity> selectByUserId(String userId){
		return mybatisDaoContext.findAll(SQLID+"selectByUserId", ResourceEntity.class, userId);
	}

	/**
	 * @author:zhangzhengguang
	 * @date:2017/7/26
	 * @description:首页加载当前用户拥有的菜单权限
	 **/
	public List<TreeNodeVo> findResourceByCurrentUserId(String currentUserId){
		return mybatisDaoContext.findAll(SQLID+"findResourceByCurrentUserId", TreeNodeVo.class, currentUserId);
	}

}
