package com.taisf.services.ups.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.UUIDGenerator;
import com.taisf.services.ups.dao.CurrentuserDao;
import com.taisf.services.ups.dao.CurrentuserRoleDao;
import com.taisf.services.ups.dao.EmployeeDao;
import com.taisf.services.ups.dao.ResourceDao;
import com.taisf.services.ups.dao.RoleDao;
import com.taisf.services.ups.dao.RoleResDao;
import com.taisf.services.ups.dto.CurrentuserRequest;
import com.taisf.services.ups.dto.EmployeeRequest;
import com.taisf.services.ups.dto.RoleRequest;
import com.taisf.services.ups.entity.CurrentuserEntity;
import com.taisf.services.ups.entity.CurrentuserRoleEntity;
import com.taisf.services.ups.entity.EmployeeEntity;
import com.taisf.services.ups.entity.RoleEntity;
import com.taisf.services.ups.vo.CurrentuserVo;
import com.taisf.services.ups.vo.RoleVo;

/**
 * <p>后台用户权限操作业务层</p>
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
@Service("ups.userPermissionServiceImpl")
public class UserPermissionServiceImpl {
	
	@Resource(name="ups.currentuserDao")
	private CurrentuserDao currentuserDao;

	@Resource(name = "ups.currentuserRoleDao")
	private CurrentuserRoleDao currentuserRoleDao;

	@Resource(name="ups.roleDao")
	private RoleDao roleDao;
	
	@Resource(name="ups.roleResDao")
	private RoleResDao roleResDao;
	
	@Resource(name="ups.resourceDao")
	private ResourceDao resourceDao;

	@Resource(name="ups.employeeDao")
	private EmployeeDao employeeDao;





	/**
	 * 修改用户信息
	 * @param userInfo
	 * @return
	 */
	public void saveUserInfo(CurrentuserVo userInfo){

		CurrentuserEntity user = new CurrentuserEntity();
		BeanUtils.copyProperties(userInfo, user);
		//更新用户的信息
		currentuserDao.saveCurrentuserById(user);
		//删除用户的角色信息
		currentuserRoleDao.delCurrentuserRoleByUserFid(userInfo.getFid());
		//重新赋值用户的角色信息
		for(String roleId:userInfo.getRoleFidList()){
			if(!Check.NuNObj(roleId)){
				CurrentuserRoleEntity currentuserRoleEntity = new CurrentuserRoleEntity();
				currentuserRoleEntity.setRoleFid(roleId);
				currentuserRoleEntity.setCurrenuserFid(user.getFid());
				currentuserRoleDao.insertCurrentuserRole(currentuserRoleEntity);
			}
		}

	}

	/**
	 * 获取当前用户的权限列表
	 * @author afi
	 * @created 2016年3月12日
	 *
	 */
	public List<RoleEntity> getRoleListByUserFid(String userFid){
		return roleDao.findRoleListByUserFid(userFid);
	}






	/**
	 * 通过userFid 获取当前的用户信息
	 * @param userFid
	 * @return
	 */
	public CurrentuserEntity getCurrentuserByFid(String userFid){
		return currentuserDao.getCurrentuserByFid(userFid);
	}
	/**
	 * 后台用户列表
	 *
	 * @author bushujie
	 * @created 2016年3月9日 上午11:20:09
	 *
	 * @param currentuserRequest
	 * @return
	 */
	public PagingResult<CurrentuserVo> findCurrentuserPageList(CurrentuserRequest currentuserRequest){
		return currentuserDao.findCurrentuserPageList(currentuserRequest);
	}

	/**
	 * 后台角色列表
	 *
	 * @author liujun
	 * @created 2016-3-10 下午7:38:44
	 *
	 * @param roleRequest
	 * @return
	 */
	public PagingResult<RoleVo> findRolePageList(RoleRequest roleRequest) {
		return roleDao.findRolePageList(roleRequest);
	}

	/**
	 * 根据角色逻辑id查询角色
	 *
	 * @author liujun
	 * @created 2016-3-12 下午4:38:13
	 *
	 * @param roleFid
	 * @return
	 */
	public RoleEntity findRoleByFid(String roleFid) {
		return roleDao.findRoleByFid(roleFid);
	}
	
	/**
	 * 
	 * 分页查询员工列表
	 *
	 * @author bushujie
	 * @created 2016年3月12日 下午4:05:02
	 *
	 * @param employeeRequest
	 * @return
	 */
	public PagingResult<EmployeeEntity> findEmployeeForPage (EmployeeRequest employeeRequest){
		return employeeDao.getEmployeeForPage(employeeRequest);
	}
	
	/**
	 * 
	 * 插入后台用户信息
	 *
	 * @author bushujie
	 * @created 2016年3月12日 下午4:33:44
	 *
	 * @param currentuserVo
	 */
	public void insertCurrentuser(CurrentuserVo currentuserVo){
		currentuserVo.setFid(UUIDGenerator.hexUUID());
		//插入用户
		currentuserDao.insertCurrentuser(currentuserVo);
		//插入用户角色关系
		for(String roleFid:currentuserVo.getRoleFidList()){
			Map<String, Object> paramMap=new HashMap<String, Object>();
			paramMap.put("currenuserFid", currentuserVo.getFid());
			paramMap.put("roleFid", roleFid);
			currentuserDao.insertCurrentuserRole(paramMap);
		}

	}

	/**
	 * 根据角色逻辑id更新角色
	 *
	 * @author liujun
	 * @created 2016-3-12 下午5:08:22
	 *
	 * @param role
	 */
	public void updateRole(RoleEntity role) {
		roleDao.updateRole(role);
	}

	/**
	 * 
	 * 查询后台用户信息
	 *
	 * @author bushujie
	 * @created 2016年3月16日 上午11:39:22
	 *
	 * @return
	 */
	public CurrentuserVo getCurrentuserVoByfid(String fid){
		return currentuserDao.getCurrentuserVoByfid(fid);
	}
	/**
	 * 
	 * 用户名查询用户信息
	 *
	 * @author bushujie
	 * @created 2016年3月16日 下午8:08:57
	 *
	 * @param userAccount
	 * @return
	 */
	public CurrentuserVo getCurrentuserByAccount(String userAccount){
		return currentuserDao.getCurrentuserEntityByAccount(userAccount);
	}

	/**
	 * 更新用户信息
	 *
	 * @author liujun
	 * @created 2016年5月16日
	 *
	 * @param user
	 * @return
	 */
	public int updateCurrentuserByFid(CurrentuserEntity user) {
		return currentuserDao.updateCurrentuserByFid(user);
	}
	
	/**
	 * 
	 * 查询用户权限类型
	 *
	 * @author bushujie
	 * @created 2016年10月26日 上午11:26:58
	 *
	 * @param resUrl
	 * @param currentuserFid
	 * @return
	 */
	public List<Integer> findRoleTypeByMenu(String resUrl,String currentuserFid){
		Map<String, Object> paramMap=new HashMap<String,Object>();
		paramMap.put("resUrl", resUrl);
		paramMap.put("currentuserFid", currentuserFid);
		return currentuserDao.findRoleTypeByMenu(paramMap);
	}

}
