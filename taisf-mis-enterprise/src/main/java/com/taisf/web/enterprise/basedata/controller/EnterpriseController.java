package com.taisf.web.enterprise.basedata.controller;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.UUIDGenerator;
import com.jk.framework.base.utils.ValueUtil;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.base.entity.AreaRegionEntity;
import com.taisf.services.base.service.AreaRegionService;
import com.taisf.services.common.valenum.ProductSourceEnum;
import com.taisf.services.common.valenum.UserStatusEnum;
import com.taisf.services.common.valenum.UserTypeEnum;
import com.taisf.services.enterprise.api.EnterpriseService;
import com.taisf.services.enterprise.dto.EnterpriseListRequest;
import com.taisf.services.enterprise.dto.EnterpriseUpdateRequest;
import com.taisf.services.enterprise.entity.EnterpriseEntity;
import com.taisf.services.enterprise.entity.EnterpriseModel;
import com.taisf.services.enterprise.vo.EnterpriseExtVO;
import com.taisf.services.recharge.dto.BalanceMoneyRequest;
import com.taisf.services.supplier.api.SupplierService;
import com.taisf.services.supplier.entity.SupplierEntity;
import com.taisf.services.ups.api.EmployeeService;
import com.taisf.services.ups.entity.EmployeeEntity;
import com.taisf.services.user.api.UserService;
import com.taisf.services.user.entity.UserEntity;
import com.taisf.services.user.manager.UserManagerImpl;
import com.taisf.web.enterprise.common.constant.LoginConstant;
import com.taisf.web.enterprise.common.page.PageResult;
import com.taisf.web.enterprise.utils.ExcelRead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("base/enterprise")
public class EnterpriseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnterpriseController.class);

	@Resource(name = "ups.employeeServiceProxy")
	private EmployeeService employeeService;
	
	@Resource(name = "user.userServiceProxy")
	private UserService userService;
	
	@Resource(name = "base.areaRegionServiceProxy")
	private AreaRegionService areaRegionService;
	
	@Resource(name = "supplier.supplierServiceProxy")
	private SupplierService supplierService;
	
	@Resource(name = "enterprise.enterpriseServiceProxy")
	private EnterpriseService enterpriseService;

	@Resource(name = "user.userManagerImpl")
	private UserManagerImpl userManager;

	@RequestMapping("list")
	public String list(HttpServletRequest request) {
		// 员工列表
		DataTransferObject<List<UserEntity>> userDto = userService.getUserByType(2);
		List<UserEntity> users = userDto.getData();
		request.setAttribute("users", users);

		return "enterprise/enterpriseList";
	}
	
	@RequestMapping("pageList")
    @ResponseBody
	public PageResult pageList(HttpServletRequest request, EnterpriseListRequest enterpriseRequest) {
		LogUtil.debug(LOGGER, "分页查询企业列表请求参数:{}", JsonEntityTransform.Object2Json(enterpriseRequest));
        PageResult result = new PageResult();
        try{

            if(Check.NuNObj(enterpriseRequest)){
                enterpriseRequest = new EnterpriseListRequest();
            }
			EmployeeEntity emp = (EmployeeEntity)request.getSession().getAttribute(LoginConstant.SESSION_KEY);
			enterpriseRequest.setSupplierCode(emp.getEmpBiz());
            DataTransferObject<PagingResult<EnterpriseExtVO>> resultDto = enterpriseService.getEnterpriseExtByPage(enterpriseRequest);
            if(resultDto.getCode()==DataTransferObject.ERROR){
                return result;
            }
            PagingResult<EnterpriseExtVO> page = resultDto.getData();
            result.setRows(page.getList());
            result.setTotal(page.getTotal());
        }catch (Exception e){
            LogUtil.error(LOGGER, "分页查询企业列表异常:{}",e);
            return result;
        }
        return result;
	}

	@RequestMapping("findAll")
    @ResponseBody
	public DataTransferObject<List<EnterpriseEntity>> findAllEnterprise(HttpServletRequest request) {
		DataTransferObject<List<EnterpriseEntity>> dto = new DataTransferObject<>();
        try{
	        EnterpriseListRequest enterpriseListRequest = new EnterpriseListRequest();
	        EmployeeEntity emp = (EmployeeEntity)request.getSession().getAttribute(LoginConstant.SESSION_KEY);
	        enterpriseListRequest.setSupplierCode(emp.getEmpBiz());
			dto = enterpriseService.findAllEnterprise(enterpriseListRequest);
        }catch (Exception e){
            LogUtil.error(LOGGER, "分页查询企业列表异常:{}",e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setErrorMsg("查询企业列表错误");
            return dto;
        }
        return dto;
	}
	
	@RequestMapping("operate")
	@ResponseBody
	public DataTransferObject<Void> operateEnterprise(EnterpriseUpdateRequest request){
		DataTransferObject<Void> dto = new DataTransferObject<>();
        try {
        	return enterpriseService.operateEnterprise(request);
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params :{}", JsonEntityTransform.Object2Json(request));
            LogUtil.error(LOGGER, "error :{}", e);
            dto.setErrorMsg("操作企业信息异常");
        }
        return dto;
	}
	
	@RequestMapping("changeStatus")
	@ResponseBody
	public DataTransferObject<Void> changeEnterpriseStatus(Integer id, Integer enterpriseStatus) {
		LogUtil.info(LOGGER, "停止企业合作请求参数:{}", id, enterpriseStatus);
		
		DataTransferObject<Void> dto = new DataTransferObject<>();
        try {
        	EnterpriseEntity entity = new EnterpriseEntity();
        	entity.setId(id);
        	entity.setEnterpriseStatus(enterpriseStatus);
        	return enterpriseService.updateEnterprise(entity);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "error :{}", e);
            dto.setErrorMsg("处理异常");
        }
        
        return dto;
	}

	/**
	 * 跳转企业信息页面(编辑或查看)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("operatePage")
	public String operatePage(HttpServletRequest request) {
		// operate=3 添加 operate=2 编辑 operate=1 查看详情
		String operate = request.getParameter("operate");
		String id = request.getParameter("id");
		LogUtil.info(LOGGER, "跳转商品信息页面请求参数:{}", id, operate);

		if (Check.NuNStr(operate)) {
			return "redirect:/base/enterprise/list";
		}
		
		request.setAttribute("operate", operate);
		
		//所有省份 集合
		List<AreaRegionEntity> provinceList = areaRegionService.findAllAreaRegion(2);
		request.setAttribute("provinceList", provinceList);
		
		if(!("3").equals(operate) && !Check.NuNObj(id)) {
			
			DataTransferObject<EnterpriseModel> resultDto = enterpriseService.getEnterpriseModelById(ValueUtil.getintValue(id));
            if(resultDto.getCode()==DataTransferObject.ERROR){
            	return "redirect:/base/enterprise/list";
            }
            EnterpriseModel model = resultDto.getData();
            request.setAttribute("model",model);
             
			EnterpriseEntity entity = model.getEnterpriseEntity();
			if(!Check.NuNStr(entity.getProvinceCode())){
				List<AreaRegionEntity> citylist = areaRegionService.findAllByParentCode(Integer.parseInt(entity.getProvinceCode()));
				request.setAttribute("citylist", citylist);
			}
			if(!Check.NuNStr(entity.getCityCode())){
				List<AreaRegionEntity> countylist = areaRegionService.findAllByParentCode(Integer.parseInt(entity.getCityCode()));
				request.setAttribute("countylist", countylist);
			}
		}

		//销售用户
		DataTransferObject<List<UserEntity>> userDto = userService.getUserByType(2);
		List<UserEntity> users = userDto.getData();
		request.setAttribute("users", users);
		
		
		// 供应商列表
		DataTransferObject<List<SupplierEntity>> supplierDto = supplierService.getAllSupplierList();
		List<SupplierEntity> suppliers = supplierDto.getData();
		request.setAttribute("suppliers", suppliers);

		return "enterprise/enterpriseOperate";
	}
	/**
	 * @author:zhangzhengguang
	 * @date:2019/11/28
	 * @description:批量导入员工
	 **/
	@RequestMapping(value="readExcel")
	@ResponseBody
	public DataTransferObject<Void> readExcel(MultipartFile file, HttpServletRequest request, HttpSession session, String enterpriseCode,String enterpriseName) throws IOException {
		DataTransferObject<Void> dto = new DataTransferObject();
		LogUtil.info(LOGGER, " EnterpriseController readExcel params enterpriseCode :{}", enterpriseCode);
		try {
			//1.判断文件是否为空
			if(Check.NuNObj(file)){
				dto.setErrCode(DataTransferObject.ERROR);
				dto.setMsg("上传文件错误");
				return dto;
			}
			//2.读取Excel数据到List中
			List<ArrayList<String>> list = new ExcelRead().readExcel(file);
			ArrayList<BalanceMoneyRequest> liseLimit = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			EmployeeEntity emp = (EmployeeEntity)request.getSession().getAttribute(LoginConstant.SESSION_KEY);
			List<UserEntity> userEntityList = new ArrayList<>();
			int successNum = 0;
			for (int i = 0; i < list.size(); i++) {
				if(Check.NuNObjs(list.get(i).get(0),list.get(i).get(1))){
					dto.setMsg("第"+i+"行数据异常,不能为空");
					return dto;
				}
				String userPhone = list.get(i).get(1);
				UserEntity userEntity = userManager.getByUserPhone(userPhone);
				if(!Check.NuNObj(userEntity)){
					sb.append(userEntity.getUserPhone()).append(",\r\n");
					continue;
				}
				    userEntity = new UserEntity();
				userEntity.setUserPhone(userPhone);
			     	userEntity.setUserCode(userPhone);
				    userEntity.setUserName(list.get(i).get(0));
					userEntity.setBizCode(emp.getEmpBiz());
					String uuid = UUIDGenerator.hexUUID();
					userEntity.setUserPassword(userPhone);
					userEntity.setUserStatus(UserStatusEnum.ACTIVITY.getCode());
					userEntity.setUserUid(uuid);
				    userEntity.setEnterpriseCode(enterpriseCode);
				    userEntity.setEnterpriseName(enterpriseName);
				    userEntity.setProductSource(ProductSourceEnum.COMMON.getCode());
					userEntity.setUserType(UserTypeEnum.YONGHU.getCode());
					userEntity.setCreateTime(new Date());

				    userEntityList.add(userEntity);
				   successNum++;
			}
			if(!Check.NuNCollection(userEntityList)){
				userService.saveBatchUser(userEntityList);
			}
			String msg = sb.toString().length() == 0 ? "" : "条数据,重复已存在用户有:"+sb.toString();
			dto.setMsg("操作成功,\r\n成功导入"+successNum + msg);
		}catch (Exception e) {
			LogUtil.error(LOGGER, "readExcel error:{}", e);
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统错误");
		}
		return dto;
	}

}
