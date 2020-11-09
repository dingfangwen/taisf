package com.taisf.services.ups.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taisf.services.common.UpsBaseDao;
import org.springframework.stereotype.Repository;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.MD5Util;
import com.jk.framework.dao.page.PageBounds;
import com.taisf.services.ups.dto.EmployeeRequest;
import com.taisf.services.ups.entity.EmployeeEntity;
import com.taisf.services.ups.vo.EmployeeVo;

/**
 * 
 * <p>用户测试</p>
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
@Repository("ups.employeeDao")
public class EmployeeDao extends UpsBaseDao {
	
	private String SQLID="ups.employeeDao.";
	
	/**
	 * 
	 * 员工分页查询
	 *
	 * @author afi
	 * @created 2016年3月12日 下午3:35:20
	 *
	 * @param employeeRequest
	 * @return
	 */
	public PagingResult<EmployeeEntity> getEmployeeForPage(EmployeeRequest employeeRequest){
		PageBounds pageBounds=new PageBounds();
		pageBounds.setLimit(employeeRequest.getLimit());
		pageBounds.setPage(employeeRequest.getPage());
		return mybatisDaoContext.findForPage(SQLID+"findEmployeeList", EmployeeEntity.class, employeeRequest, pageBounds);
	}


	/**
	 * 通过医生姓名查询医生列表
	 * @author afi
	 * @param empName
	 * @return
	 */
	public List<EmployeeEntity> findEmployeeByName (String empName){
		return mybatisDaoContext.findAll(SQLID+"findEmployeeByName", EmployeeEntity.class, empName);
	}
	/**
	 * 修改密码
	 * @author afi
	 * @param uid
	 * @param pwd
	 * @return
	 */
	public int changePwd(String uid,String pwd){
		Map<String,Object> par = new HashMap<String,Object>();

		par.put("userId",uid);
		par.put("pwd",MD5Util.MD5Encode(pwd));

		return mybatisDaoContext.update(SQLID+"changePwd", par);
	}

	
	/**
	 * 插入数据
	 * @author afi
	 * @param syncEntity
	 */
	public int insertEmployeeSysc(EmployeeEntity syncEntity){
		if(Check.NuNObj(syncEntity)){
			return 0;
		}
		if (!Check.NuNStr(syncEntity.getUserPwd())){
			syncEntity.setUserPwd(MD5Util.MD5Encode(syncEntity.getUserPwd()));
		}
		if (Check.NuNObj(syncEntity.getCreateDate())){
			syncEntity.setCreateDate(new Date());
		}
		return mybatisDaoContext.save(SQLID+"insertSelective", syncEntity);
	}
	
	/**
	 * 更新同步员工数据
	 * @author afi
	 * @param syncEntity
	 * @return
	 */
	public int updateEmployeeEntity(EmployeeEntity syncEntity){
		return mybatisDaoContext.update(SQLID+"updateByfid", syncEntity);
	}

	/**
	 * 根据查询条件查询员工信息
	 * @author afi
	 * @param empRequest
	 * @return
	 */
	public List<EmployeeEntity> findEmployeeByCondition(EmployeeRequest empRequest) {
		return mybatisDaoContext.findAll(SQLID+"findEmployeeList", EmployeeEntity.class, empRequest);
	}

	/**
	 * 查询所有员工信息
	 * @author zyf
	 * @return list
	 */
	public List<EmployeeEntity> findAllDoctor() {
		return mybatisDaoContext.findAll(SQLID+"findAllDoctor", EmployeeEntity.class);
	}

	/**
	 * 根据查询条件查询员工信息
	 * @author afi
	 * @param mail
	 * @return
	 */
	public List<EmployeeEntity> findEmployeeByMail(String mail) {
		return mybatisDaoContext.findAll(SQLID+"findEmployeeByMail", EmployeeEntity.class, mail);
	}

	/**
	 * 根据科室id查询员工信息
	 * @param departmentId
	 * @return
	 */
	public List<EmployeeEntity> findEmployeeByDepartId(String departmentId) {
		return mybatisDaoContext.findAll(SQLID+"findEmployeeByDepartId", EmployeeEntity.class, departmentId);
	}

	/**
	 * 根据uid获取员工信息
	 * @author afi
	 * @param uid
	 * @return
	 */
	public EmployeeEntity findEmployeeByUid(String uid) {
		return mybatisDaoContext.findOneSlave(SQLID+"findEmployeeByUid", EmployeeEntity.class, uid);
	}
	
	/**
	 * 根据uid获取员工信息包含角色信息
	 * @author afi
	 * @param uid
	 * @return
	 */
	public EmployeeVo findEmployeeVoByUid(String uid) {
		return mybatisDaoContext.findOneSlave(SQLID+"findEmployeeVoByUid", EmployeeVo.class, uid);
	}


}
