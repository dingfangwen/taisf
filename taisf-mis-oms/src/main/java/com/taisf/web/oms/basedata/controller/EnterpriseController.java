package com.taisf.web.oms.basedata.controller;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.ValueUtil;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.base.entity.AreaRegionEntity;
import com.taisf.services.base.service.AreaRegionService;
import com.taisf.services.enterprise.api.EnterpriseService;
import com.taisf.services.enterprise.dto.EnterpriseListRequest;
import com.taisf.services.enterprise.dto.EnterpriseUpdateRequest;
import com.taisf.services.enterprise.entity.EnterpriseEntity;
import com.taisf.services.enterprise.entity.EnterpriseModel;
import com.taisf.services.enterprise.vo.EnterpriseExtVO;
import com.taisf.services.supplier.api.SupplierService;
import com.taisf.services.supplier.entity.SupplierEntity;
import com.taisf.services.ups.api.EmployeeService;
import com.taisf.services.user.api.UserService;
import com.taisf.services.user.entity.UserEntity;
import com.taisf.web.oms.common.page.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
			dto = enterpriseService.findAllEnterprise(new EnterpriseListRequest());
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
			return "redirect:/base/oms/list";
		}
		
		request.setAttribute("operate", operate);
		
		//所有省份 集合
		List<AreaRegionEntity> provinceList = areaRegionService.findAllAreaRegion(2);
		request.setAttribute("provinceList", provinceList);
		
		if(!("3").equals(operate) && !Check.NuNObj(id)) {
			
			DataTransferObject<EnterpriseModel> resultDto = enterpriseService.getEnterpriseModelById(ValueUtil.getintValue(id));
            if(resultDto.getCode()==DataTransferObject.ERROR){
            	return "redirect:/base/oms/list";
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
	

}
