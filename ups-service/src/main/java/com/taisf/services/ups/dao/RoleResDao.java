package com.taisf.services.ups.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taisf.services.common.UpsBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jk.framework.base.exception.BusinessException;
import com.jk.framework.base.utils.Check;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.ups.dto.RoleResourceRequest;
import com.taisf.services.ups.entity.RoleResourceEntity;

/**
 * 
 * <p>角色资源Dao</p>
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
@Repository("ups.roleResDao")
public class RoleResDao extends UpsBaseDao {
	
	private String SQLID="ups.roleResDao.";
	

	/**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(RoleResDao.class);
	
	/**
	 * 根据角色逻辑id查询角色资源列表
	 *
	 * @author liujun
	 * @param roleResRequest 
	 * @created 2016-3-11 下午9:20:57
	 *
	 * @return
	 */
	public List<RoleResourceEntity> findRoleResources(RoleResourceRequest roleResRequest) {
		return mybatisDaoContext.findAll(SQLID+"selectByRoleFid", RoleResourceEntity.class, roleResRequest);
	}


	/**
	 * 删除角色id下的所有权限
	 * @param roleFid
	 */
	public int delRoleResourcesByFid(String roleFid){
		if(Check.NuNObj(roleFid)){
			throw  new BusinessException("on del the relatin the roleFid is empty");
		}
		Map<String,Object> delPar = new HashMap<String,Object>();
		delPar.put("roleFid", roleFid);
		return mybatisDaoContext.delete(SQLID + "delRoleResourcesByFid", delPar);
	}


	/**
	 * 重新定义当前角色下的所有资源
	 * @param roleFid
	 */
	public void saveRoleResources(String roleFid,String[] resFidArray){
		if(Check.NuNObj(roleFid)){
			LogUtil.info(logger,"on del the relatin the roleFid is empty");
			throw  new BusinessException("on del the relatin the roleFid is empty");
		}
		if(Check.NuNObj(resFidArray) || resFidArray == null || resFidArray.length == 0){
			return;
		}
		RoleResourceEntity roleResourceEntity = new RoleResourceEntity();
		roleResourceEntity.setRoleFid(roleFid);
		for(String res:resFidArray){
			roleResourceEntity.setResourceFid(res);
			mybatisDaoContext.save(SQLID + "saveRoleResource", roleResourceEntity);
		}
	}
}
