package com.taisf.services.ups.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.cache.redis.api.RedisOperations;
import com.taisf.services.ups.dao.EmployeeDao;
import com.taisf.services.ups.dao.EmployeeRoleDao;
import com.taisf.services.ups.dto.EmployeeAddRequest;
import com.taisf.services.ups.dto.EmployeeRequest;
import com.taisf.services.ups.entity.EmployeeEntity;
import com.taisf.services.ups.entity.EmployeeRoleEntity;
import com.taisf.services.ups.vo.EmployeeRoleVo;
import com.taisf.services.ups.vo.EmployeeVo;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/4/8.
 * @version 1.0
 * @since 1.0
 */
@Service("ups.employeeServiceImpl")
public class EmployeeServiceImpl {


    @Resource(name="ups.employeeDao")
    private EmployeeDao employeeDao;
    
    @Resource(name="ups.employeeRoleDao")
    private EmployeeRoleDao employeeRoleDao;

    @Autowired
	private RedisOperations redis;

    /**
     * 分页查询员工列表
     * @author afi
     * @created 2016年3月12日 下午4:05:02
     *
     * @param employeeRequest
     * @return
     */
    public PagingResult<EmployeeEntity> findEmployeeForPage (EmployeeRequest employeeRequest){
        return employeeDao.getEmployeeForPage(employeeRequest);
    }

	/**
	 * 通过医生姓名查询医生列表
	 * @author afi
	 * @param name
	 * @return
	 */
	public List<EmployeeEntity> findEmployeeByName(String name){
		if (Check.NuNStr(name)){
			return null;
		}
		return employeeDao.findEmployeeByName(name);
	}


    /**
     * 根据查询条件查询员工信息
     * @author afi
     * @param mail
     * @return
     */
    public List<EmployeeEntity> findEmployeeByMail(String mail){
        return employeeDao.findEmployeeByMail(mail);
    }

	/**
	 * 根据科室id查询员工信息
	 * @param departmentId
	 * @return
	 */
	public List<EmployeeEntity> findEmployeeByDepartId(String departmentId){
		return employeeDao.findEmployeeByDepartId(departmentId);
	}

    /**
     * 根据查询条件查询员工信息
     * @author afi
     * @param mail
     * @return
     */
    public EmployeeEntity findEmployeeByUid(String mail){
        return employeeDao.findEmployeeByUid(mail);
    }
    
    /**
     * 根据查询条件查询员工信息包含角色信息
     * @author afi
     * @param mail
     * @return
     */
    public EmployeeVo findEmployeeVoByUid(String userId){
    	EmployeeVo vo = employeeDao.findEmployeeVoByUid(userId);
    	List<EmployeeRoleVo> employeeRoleVo = employeeRoleDao.findEmployeeRoleByUid(userId);
    	vo.setRoles(employeeRoleVo);
        return employeeDao.findEmployeeVoByUid(userId);
    }


    /**
     * 保存用户和用户角色
     * @author zll
     * @param OrderDetailVo request
     */
    public void insertEmployeeSysc(EmployeeAddRequest request){
    	employeeDao.insertEmployeeSysc(request);
    	//插入用户角色关系
		for(String roleFid : request.getRoleFidList()){
			EmployeeRoleEntity entity = new EmployeeRoleEntity();
			entity.setUserId(request.getUserId());
			entity.setRoleFid(roleFid);
			employeeRoleDao.insertEmployeeRole(entity);
		}
    }

    public void saveEmployee(EmployeeEntity request){
    	employeeDao.insertEmployeeSysc(request);
    }
    
    /**
     * 修改用户和用户角色
     * @author zll
     * @param OrderDetailVo request
     */
    public void updateEmployeeSysc(EmployeeAddRequest request){
    	employeeDao.updateEmployeeEntity(request);
    	//插入用户角色关系
    	employeeRoleDao.deleteEmployeeRole(request.getUserId());
    	//删除缓存
    	if(redis.exists(request.getUserId())){
    		redis.del(request.getUserId());
    	}
		for(String roleFid : request.getRoleFidList()){
			EmployeeRoleEntity entity = new EmployeeRoleEntity();
			entity.setUserId(request.getUserId());
			entity.setRoleFid(roleFid);
			employeeRoleDao.insertEmployeeRole(entity);
		}
    }


    /**
     * 修改密码
     * @author afi
     * @param uid
     * @param pwd
     * @return
     */
    public int changePwd(String uid,String pwd){
        return employeeDao.changePwd(uid,pwd);
    }

    /**
	 * 根据用户id获取员工角色关系列表
	 * @author afi
	 * @param uid
	 * @return
	 */
	public List<EmployeeRoleVo> findEmployeeRoleByUid(String uid) {
		return employeeRoleDao.findEmployeeRoleByUid(uid);
	}
	/**
	 * 根据roleFid   查询中间表 角色列表
	 * @author afi
	 * @param uid
	 * @return
	 */
	public List<EmployeeRoleEntity> selectByRoleFid(String roleFid) {
		return employeeRoleDao.selectByRoleFid(roleFid);
	}
	
	/**
	 * 获取所有员工信息
	 * @author zyf
	 * @return
	 */
	public List<EmployeeEntity> findAllDoctor() {
		return employeeDao.findAllDoctor();
	}

	/**
	 * 根据查询条件查询员工信息
	 * @author afi
	 * @param empRequest
	 * @return
	 */
	public List<EmployeeEntity> findEmployeeByCondition(EmployeeRequest empRequest) {
		return employeeDao.findEmployeeByCondition(empRequest);
	}
}
