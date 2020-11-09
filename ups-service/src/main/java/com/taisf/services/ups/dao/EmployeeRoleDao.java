package com.taisf.services.ups.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taisf.services.common.UpsBaseDao;
import org.springframework.stereotype.Repository;
import com.taisf.services.ups.entity.EmployeeRoleEntity;
import com.taisf.services.ups.vo.EmployeeRoleVo;

/**
 * 
 * <p>用户角色测试</p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 * 
 * @author zll
 * @since 1.0
 * @version 1.0
 */
@Repository("ups.employeeRoleDao")
public class EmployeeRoleDao extends UpsBaseDao {
	
	private String SQLID="ups.employeeRoleDao.";

	/**
	 * 根据用户id获取员工角色
	 * @author afi
	 * @param uid
	 * @return
	 */
	/*public List<EmployeeRoleVo> findEmployeeRoleByUid(String uid) {
		return mybatisDaoContext.findAll(SQLID+"selectByUserId", EmployeeRoleVo.class, uid);
	}*/

	/**
	 * 查询角色列表
	 * @author afi
	 * @param uid
	 * @return
	 */
	public List<EmployeeRoleVo> findEmployeeRoleByUid(String uid) {
		return mybatisDaoContext.findAll(SQLID+"selectByUserId", EmployeeRoleVo.class, uid);
	}
	
	/**
	 * 插入后台用户角色
	 * @param entity
	 * @return
	 */
	public int insertEmployeeRole(EmployeeRoleEntity entity) {
		return mybatisDaoContext.save(SQLID + "insertEmployeeRole", entity);
	}
	
	/**
	 * 删除后台用户角色
	 * @param entity
	 * @return
	 */
	public int deleteEmployeeRole(String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		return mybatisDaoContext.delete(SQLID + "deleteByUid", param);
	}
	
	/**
	 * 根据roleFid   查询中间表 角色列表
	 * @author afi
	 * @param uid
	 * @return
	 */
	public List<EmployeeRoleEntity> selectByRoleFid(String roleFid) {
		return mybatisDaoContext.findAll(SQLID+"selectByRoleFid", EmployeeRoleEntity.class, roleFid);
	}
}
