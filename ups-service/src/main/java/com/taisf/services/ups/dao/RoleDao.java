package com.taisf.services.ups.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taisf.services.common.UpsBaseDao;
import org.springframework.stereotype.Repository;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.dao.page.PageBounds;
import com.taisf.services.ups.dto.RoleRequest;
import com.taisf.services.ups.entity.RoleEntity;
import com.taisf.services.ups.vo.RoleVo;

/**
 * 
 * <p>后台角色数据库操作类</p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 * 
 * @author liujun
 * @since 1.0
 * @version 1.0
 */
@Repository("ups.roleDao")
public class RoleDao extends UpsBaseDao {
	
	private String SQLID="ups.roleDao.";

	
	public void insertRole(RoleEntity roleEntity) {
		mybatisDaoContext.save(SQLID+"insertRole", roleEntity);
	}
	
	/**
	 * 分页查询角色列表
	 *
	 * @author liujun
	 * @created 2016-3-10 下午7:44:43
	 *
	 * @param roleRequest
	 * @return
	 */
	public PagingResult<RoleVo> findRolePageList(RoleRequest roleRequest) {
		PageBounds pageBounds=new PageBounds();
		pageBounds.setLimit(roleRequest.getLimit());
		pageBounds.setPage(roleRequest.getPage());
		return mybatisDaoContext.findForPage(SQLID+"findRoleListByPage", RoleVo.class, roleRequest, pageBounds);
	}


	/**
	 * 获取当前用户的角色信息
	 *
	 * @author afi
	 * @created 2016-3-12 下午4:42:24
	 *
	 * @param userFid
	 * @return
	 */
	public List<RoleEntity> findRoleListByUserFid(String userFid) {
		Map<String,Object> par = new HashMap<>();
		par.put("userFid",userFid);
		return mybatisDaoContext.findAll(SQLID + "findRoleListByUserFid", RoleEntity.class, par);
	}


	/**
	 * 根据角色逻辑id查询角色
	 *
	 * @author liujun
	 * @created 2016-3-12 下午4:42:24
	 *
	 * @param roleFid
	 * @return
	 */
	public RoleEntity findRoleByFid(String roleFid) {
		return mybatisDaoContext.findOneSlave(SQLID+"findRoleByFid", RoleEntity.class, roleFid);
	}

	/**
	 * 根据角色逻辑id更新角色
	 *
	 * @author liujun
	 * @created 2016-3-12 下午5:09:57
	 *
	 * @param role
	 */
	public void updateRole(RoleEntity role) {
		mybatisDaoContext.update(SQLID+"updateRoleByFid", role);
	}

}
