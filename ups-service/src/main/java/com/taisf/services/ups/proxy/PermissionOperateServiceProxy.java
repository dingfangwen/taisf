package com.taisf.services.ups.proxy;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.UUIDGenerator;
import com.jk.framework.cache.redis.api.RedisOperations;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.ups.api.PermissionOperateService;
import com.taisf.services.ups.dto.CurrentuserRequest;
import com.taisf.services.ups.dto.EmployeeRequest;
import com.taisf.services.ups.dto.RoleRequest;
import com.taisf.services.ups.entity.CurrentuserEntity;
import com.taisf.services.ups.entity.EmployeeEntity;
import com.taisf.services.ups.entity.EmployeeRoleEntity;
import com.taisf.services.ups.entity.RoleEntity;
import com.taisf.services.ups.entity.RoleResourceEntity;
import com.taisf.services.ups.service.EmployeeServiceImpl;
import com.taisf.services.ups.service.ResourceServiceImpl;
import com.taisf.services.ups.service.UserPermissionServiceImpl;
import com.taisf.services.ups.vo.CurrentuserVo;
import com.taisf.services.ups.vo.RoleVo;
import com.taisf.services.ups.vo.TreeNodeVo;

/**
 * <p>权限管理操作</p>
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
@Component("ups.upsOperateServiceProxy")
public class PermissionOperateServiceProxy implements PermissionOperateService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PermissionOperateServiceProxy.class);

	@Resource(name="ups.userPermissionServiceImpl")
	private UserPermissionServiceImpl userPermissionServiceImpl;

	@Resource(name="ups.resourceServiceImpl")
	private ResourceServiceImpl resourceServiceImpl;
	
	@Resource(name="ups.employeeServiceImpl")
	private EmployeeServiceImpl employeeServiceImpl;
	
	@Autowired
	private RedisOperations redis;


	/**
	 * 修改用户信息
	 * @param paramJson
	 * @return
	 */
	public String saveUserInfo(String paramJson){
		DataTransferObject dto = new DataTransferObject();
		//json参数转换
		CurrentuserVo currentuserVo=JsonEntityTransform.json2Object(paramJson, CurrentuserVo.class);
		if(Check.NuNObj(currentuserVo)){
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
			return dto.toJsonString();
		}
		if(Check.NuNObj(currentuserVo.getFid())){
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
			return dto.toJsonString();
		}
		try{
			userPermissionServiceImpl.saveUserInfo(currentuserVo);
		}catch (Exception e) {
			LogUtil.error(LOGGER, "error:{}", e);
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
			return dto.toJsonString();
		}
		return dto.toJsonString();

	}


	/**
	 * 初始化当前的用户的 修改信息
	 * @param userFid
	 * @return
	 */
	public String initSaveUserInfo(String userFid){
		DataTransferObject<CurrentuserVo> dto = new DataTransferObject<CurrentuserVo>();
		if (Check.NuNStr(userFid)) {
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
			return dto.toJsonString();
		}
		try{
			CurrentuserVo currentuserVo=userPermissionServiceImpl.getCurrentuserVoByfid(userFid);
			if(Check.NuNObj(currentuserVo)) {
				dto.setErrCode(DataTransferObject.ERROR);
				dto.setMsg("系统异常");
				return dto.toJsonString();
			}
			currentuserVo.setRoles(userPermissionServiceImpl.getRoleListByUserFid(userFid));
			dto.setData(currentuserVo);
		}catch (Exception e) {
			LogUtil.error(LOGGER, "error:{}", e);
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
			return dto.toJsonString();
		}
		return dto.toJsonString();

	}

	@Override
	public String searchCurrentuserList(String paramJson) {
		DataTransferObject<PagingResult<CurrentuserVo>> dto = null;
		try{
			CurrentuserRequest currentuserRequest=JsonEntityTransform.json2Object(paramJson, CurrentuserRequest.class);
            //条件查询后台用户
			PagingResult<CurrentuserVo> pr=userPermissionServiceImpl.findCurrentuserPageList(currentuserRequest);
            dto = new DataTransferObject<PagingResult<CurrentuserVo>>();
            dto.setData(pr);
		}catch (Exception e) {
			LogUtil.error(LOGGER, "error:{}", e);
            dto = new DataTransferObject();
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
            return dto.toJsonString();
		}
		return dto.toJsonString();
	}


	@Override
	public DataTransferObject<PagingResult<RoleVo>> searchRoles(String paramJson) {
		DataTransferObject<PagingResult<RoleVo>> dto = new DataTransferObject<PagingResult<RoleVo>>();
		try {
			RoleRequest roleRequest = JsonEntityTransform.json2Object(paramJson, RoleRequest.class);
			// 条件查询后台用户
			PagingResult<RoleVo> pr = userPermissionServiceImpl.findRolePageList(roleRequest);
			dto.setData(pr);
		} catch (Exception e) {
			LogUtil.error(LOGGER, "error:{}", e);
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
		}
		return dto;
	}


	@Override
	public DataTransferObject<RoleEntity> searchRoleByFid(String roleFid) {
		DataTransferObject<RoleEntity> dto = new DataTransferObject<RoleEntity>();
		if (Check.NuNStr(roleFid)) {
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
			return dto;
		}
		try {
			RoleEntity role = userPermissionServiceImpl.findRoleByFid(roleFid);
			if(role == null){
				dto.setErrCode(DataTransferObject.ERROR);
				dto.setMsg("系统异常");
				return dto;
			}
			dto.setData(role);
		} catch (Exception e) {
			LogUtil.error(LOGGER, "error:{}", e);
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
		}
		return dto;
	}

	@Override
	public String editRoleStatus(String paramJson) {
		DataTransferObject dto = new DataTransferObject();
		RoleEntity role = JsonEntityTransform.json2Object(paramJson, RoleEntity.class);
		if(role.getIsDel() == null) {
			role.setIsDel(1);
		}
		role.setIsDel(1^role.getIsDel());
		role.setModifyDate(new Date());

		try {
			userPermissionServiceImpl.updateRole(role);
		} catch (Exception e) {
			LogUtil.error(LOGGER, "error:{}", e);
			dto = new DataTransferObject();
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
		}
		return dto.toJsonString();
	}


	@Override
	public String employeePageList(String paramJson) {
		DataTransferObject<PagingResult<EmployeeEntity>> dto = null;
		try{
			EmployeeRequest employeeRequest=JsonEntityTransform.json2Object(paramJson, EmployeeRequest.class);
            //条件查询员工列表
			PagingResult<EmployeeEntity> pr=userPermissionServiceImpl.findEmployeeForPage(employeeRequest);
            dto = new DataTransferObject<PagingResult<EmployeeEntity>>();
            dto.setData(pr);
		}catch (Exception e) {
			LogUtil.error(LOGGER, "error:{}", e);
            dto = new DataTransferObject();
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
            return dto.toJsonString();
		}
		return dto.toJsonString();
	}





	/* (non-Javadoc)
	 * @see com.ziroom.minsu.services.basedata.api.inner.PermissionOperateService#insertCurrentuser(java.lang.String)
	 */
	@Override
	public String insertCurrentuser(String paramJson) {
		DataTransferObject dto = null;
		try{
			CurrentuserVo currentuserVo=JsonEntityTransform.json2Object(paramJson, CurrentuserVo.class);
			userPermissionServiceImpl.insertCurrentuser(currentuserVo);
			dto = new DataTransferObject();
//			dto.putValue("fid", currentuserVo.getFid());
		}catch (Exception e) {
			LogUtil.error(LOGGER, "error:{}", e);
			dto = new DataTransferObject();
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
            return dto.toJsonString();
		}
		return dto.toJsonString();
	}


	@Override
	public String searchRoleResources(String roleFid) {
		DataTransferObject<List<TreeNodeVo>> dto = new DataTransferObject<List<TreeNodeVo>>();
		if(Check.NuNStr(roleFid)){
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
            return dto.toJsonString();
        }
		try {
			List<TreeNodeVo> treeNodeList = resourceServiceImpl.findMenuTreeNodeVos();
			List<RoleResourceEntity> roleResList = resourceServiceImpl.findRoleResourceList(roleFid);
			if (!Check.NuNCollection(treeNodeList) && !Check.NuNCollection(roleResList)) {
				recursionSelected(treeNodeList, roleResList);
			}
			dto.setData(treeNodeList);
		} catch (Exception e) {
			LogUtil.error(LOGGER, "error:{}", e);
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
		}
		return dto.toJsonString();
	}

	/**
	 * 递归判断资源列表是否选中
	 *
	 * @author liujun
	 * @created 2016-3-16 下午2:57:50
	 *
	 * @param treeNodeList
	 * @param roleResList
	 */
	private void recursionSelected(List<TreeNodeVo> treeNodeList, List<RoleResourceEntity> roleResList) {
		for (TreeNodeVo treeNodeVo : treeNodeList) {
			List<TreeNodeVo> treeNodeVoList = treeNodeVo.getNodes();
			if(!Check.NuNCollection(treeNodeVoList)){
				recursionSelected(treeNodeVoList,roleResList);
			}
			String id = treeNodeVo.getId();
			if (Check.NuNStr(id)) {
				continue;
			}
			for (RoleResourceEntity roleResource : roleResList) {
				String resFid = roleResource.getResourceFid();
				if (Check.NuNStr(resFid)) {
					continue;
				}
				if(resFid.equals(id)){
					Map<String, Object> map = treeNodeVo.getState();
					map.put("selected", true);
				}
			}
		}
	}

	@Override
    public String updateRoleResources(String paramJson) {
        DataTransferObject<Map<String, Object>> dto = JsonEntityTransform.json2DataTransferObject(paramJson);
        Map<String, Object> map = dto.getData();
        String roleFid = (String) map.get("roleFid");
        String resFids = (String) map.get("resFids");
        Integer roleType=0;
        if(map.get("roleType")!=null){
        	roleType=Integer.valueOf(map.get("roleType").toString());
        }
        //非空校验
        dto = new DataTransferObject();
        if(Check.NuNStr(roleFid) || Check.NuNStr(resFids)){
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
            return dto.toJsonString();
        }
        try{
        	String[] resFidArray = resFids.split(",");
            //直接更新数据
        	resourceServiceImpl.saveRoleResources(roleFid,resFidArray,roleType);
        	//修改角色,清除拥有当前角色的缓存
        	List<EmployeeRoleEntity> listrole = employeeServiceImpl.selectByRoleFid(roleFid);
        	for (EmployeeRoleEntity vo : listrole) {
        		if(redis.exists(vo.getUserId())){
        			redis.del(vo.getUserId());
        		}
			}
        }catch (Exception e){
        	LogUtil.error(LOGGER, "error:{}", e);
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
        }
        return dto.toJsonString();
    }

	@Override
	public String addRoleResources(String paramJson) {
		DataTransferObject<Map<String, Object>> dto = JsonEntityTransform.json2DataTransferObject(paramJson);
		Map<String, Object> map = dto.getData();
		String userFid = (String) map.get("userFid");
		String roleName = (String) map.get("roleName");
		String resFids = (String) map.get("resFids");
		Integer roleType=0;
		if(map.get("roleType")!=null){
			roleType=Integer.valueOf(map.get("roleType").toString());
		}
		//非空校验
		dto = new DataTransferObject();
		if(Check.NuNStr(roleName) || Check.NuNStr(resFids)){
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
			return dto.toJsonString();
		}
		RoleEntity role = new RoleEntity();
		role.setRoleName(roleName);
		role.setFid(UUIDGenerator.hexUUID());
		role.setCreateFid(userFid);
		role.setRoleType(roleType);
		role.setCreateDate(new Date());
		try {
			resourceServiceImpl.saveRoleAndRoleResources(role, resFids);
		} catch (Exception e) {
			LogUtil.error(LOGGER, "error:{}", e);
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
		}
		return dto.toJsonString();
	}

	@Override
	public String searchCurrentuserByUid(String uid) {
		DataTransferObject<CurrentuserEntity> dto = new DataTransferObject<CurrentuserEntity>();
		if(Check.NuNStr(uid)){
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
			return dto.toJsonString();
		}

		try {
			CurrentuserEntity user = userPermissionServiceImpl.getCurrentuserByFid(uid);
			dto.setData(user);
		} catch (Exception e) {
			LogUtil.error(LOGGER, "error:{}", e);
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
		}
		return dto.toJsonString();
	}

	@Override
	public String editUserStatus(String paramJson) {
		DataTransferObject dto = new DataTransferObject();
		CurrentuserEntity user = JsonEntityTransform.json2Object(paramJson, CurrentuserEntity.class);
		if(user.getAccountStatus() == null) {
			user.setAccountStatus(1);
		}
		user.setAccountStatus(1^user.getAccountStatus());
		user.setLastModifyDate(new Date());

		try {
			int upNum = userPermissionServiceImpl.updateCurrentuserByFid(user);
//			dto.putValue("upNum", upNum);
		} catch (Exception e) {
			LogUtil.error(LOGGER, "error:{}", e);
			dto = new DataTransferObject();
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
		}
		return dto.toJsonString();
	}



	@SuppressWarnings({ "unchecked" })
	@Override
	public String findRoleTypeByMenu(String paramJson) {
		DataTransferObject dto = new DataTransferObject();
		Map<String, String> paramMap=(Map<String, String>) JsonEntityTransform.json2Map(paramJson);
		Integer roleType=0;
		List<Integer> roleTypeList=userPermissionServiceImpl.findRoleTypeByMenu(paramMap.get("resUrl"), paramMap.get("currenuserFid"));
		//判断角色类型
		if(roleTypeList != null && !roleTypeList.isEmpty()){
			if(roleTypeList.contains(0)){
				roleType=0;
			}else if(roleTypeList.contains(1)){
				roleType=1;
			}else if(roleTypeList.contains(2)&&roleTypeList.contains(3)){
				roleType=1;
			}else{
				roleType=roleTypeList.get(0);
			}
		}
//		dto.putValue("roleType", roleType);
		return dto.toJsonString();
	}


}
