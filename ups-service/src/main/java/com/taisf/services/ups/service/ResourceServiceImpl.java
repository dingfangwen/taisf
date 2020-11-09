
package com.taisf.services.ups.service;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.taisf.services.ups.dao.ResourceDao;
import com.taisf.services.ups.dao.RoleDao;
import com.taisf.services.ups.dao.RoleResDao;
import com.taisf.services.ups.dto.ResourceRequest;
import com.taisf.services.ups.dto.RoleResourceRequest;
import com.taisf.services.ups.entity.ResourceEntity;
import com.taisf.services.ups.entity.RoleEntity;
import com.taisf.services.ups.entity.RoleResourceEntity;
import com.taisf.services.ups.vo.ResourceVo;
import com.taisf.services.ups.vo.TreeNodeVo;

/**
 * <p>
 * 后台菜单操作业务层
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
@Service("ups.resourceServiceImpl")
public class ResourceServiceImpl {

	@Resource(name = "ups.resourceDao")
	private ResourceDao resourceDao;
	
	@Resource(name = "ups.roleResDao")
	private RoleResDao roleResDao;
	
	@Resource(name = "ups.roleDao")
	private RoleDao roleDao;

	/**
	 * 更新菜单信息
	 * 
	 * @param resourceEntity
	 */
	public void updateMenuByFid(ResourceEntity resourceEntity) {
		resourceDao.updateMenuByFid(resourceEntity);
	}

	/**
	 * 
	 * 后台菜单列表
	 *
	 * @author liyingjie
	 * @created 2016年3月9日 上午11:20:09
	 *
	 * @param resourceRequest
	 * @return
	 */
	public PagingResult<ResourceEntity> findMenuResList(ResourceRequest resourceRequest) {
		return resourceDao.findMenuOperPageList(resourceRequest);
	}

	/**
	 * 
	 * 保存 菜单
	 *
	 * @author liyingjie
	 * @created 2016年3月11日 下午8:43:58
	 *
	 * @param resourceEntity
	 */
	public void saveMenuResouce(ResourceEntity resourceEntity) {
		resourceDao.insertMenuResource(resourceEntity);
	}

	/**
	 * 查询所有的菜单和与其相关子节点
	 *
	 * @author liyingjie
	 * @created 2016年3月11日 下午10:53:16
	 *
	 * @return
	 */
	public List<ResourceEntity> findAllMenuChildList() {
		return resourceDao.findAllMenuClasterChildren();
	}

	/**
	 * 
	 * 资源树结构查询
	 *
	 * @author bushujie
	 * @created 2016年3月13日 下午7:09:05
	 *
	 * @return
	 */
	public List<TreeNodeVo> findMenuTreeNodeVos() {
		return resourceDao.findTreeNodeVoList();
	}

    /**
     * 重新定义角色的资源系信息
     * @param roleFid
     */
    public void  saveRoleResources(String roleFid,String[] resFidArray,Integer roleType){
    	RoleEntity roleEntity=new RoleEntity();
    	roleEntity.setFid(roleFid);
    	roleEntity.setRoleType(roleType);
    	//更新角色类型
    	roleDao.updateRole(roleEntity);
        //清洗当前的角色资源信息
        roleResDao.delRoleResourcesByFid(roleFid);
        //重新定义当前的资源信息
        roleResDao.saveRoleResources(roleFid,resFidArray);
    }
    
	public void saveRoleAndRoleResources(RoleEntity role, String resFids) {
		//新增保存角色
		roleDao.insertRole(role);
		
		String[] resFidArray = resFids.split(",");
		//保存角色资源关系
		roleResDao.saveRoleResources(role.getFid(), resFidArray);
	}

	/**
	 * 查询角色资源关系
	 *
	 * @author liujun
	 * @created 2016-3-16 上午10:38:42
	 *
	 * @param roleFid
	 * @return
	 */
	public List<RoleResourceEntity> findRoleResourceList(String roleFid) {
		RoleResourceRequest request = new RoleResourceRequest();
		request.setRoleFid(roleFid);
		return roleResDao.findRoleResources(request);
	}
	/**
	 * 
	 * 用户id获取用户权限树
	 *
	 * @author bushujie
	 * @created 2016年3月16日 下午10:41:12
	 *
	 * @param currentuserId
	 * @return
	 */
	public List<ResourceVo> findCurrentuserResList(String currentuserId){
		return resourceDao.findResourceByCurrentuserId(currentuserId);
	}
	
	/**
	 * 根据用户id查询  用户资源权限
	 * @param resUrl
	 * @return
	 */
	public List<ResourceEntity> selectByUserId(String userId){
		return resourceDao.selectByUserId(userId);
	}

	/**
	 * @author:zhangzhengguang
	 * @date:2017/7/26
	 * @description:首页加载当前用户拥有的菜单权限
	 **/
	public List<TreeNodeVo> findResourceByCurrentUserId(String currentuserId){
		List<TreeNodeVo> treeNodeVos = resourceDao.findResourceByCurrentUserId(currentuserId);
		List<TreeNodeVo> vos = new ArrayList<>();
		for (TreeNodeVo parentV : treeNodeVos) {
			if(parentV.getIsLeaf()==0){
				List<TreeNodeVo> nodes = new ArrayList<>();
				for (TreeNodeVo vo : treeNodeVos) {
					if(!Check.NuNObj(parentV.getId())){
						if (parentV.getId().equals(vo.getParentId())){
							nodes.add(vo);
						}
					}
				}
				parentV.setNodes(nodes);
				vos.add(parentV);
			}
		}
		return vos;
	}

}
