package com.taisf.web.enterprise.basedata.controller;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.UUIDGenerator;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.enterprise.api.EnterpriseService;
import com.taisf.services.enterprise.dto.EnterpriseAddressRequest;
import com.taisf.services.enterprise.entity.EnterpriseAddressEntity;
import com.taisf.web.enterprise.common.page.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("base/enterpriseAddress")
public class EnterpriseAddressController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnterpriseAddressController.class);


	
	@Resource(name = "enterprise.enterpriseServiceProxy")
	private EnterpriseService enterpriseService;
	
	@RequestMapping("enterpriseAddressList")
	public String findPageEnterpriseAddressByCode(HttpServletRequest request,String enterpriseCode) {
		request.setAttribute("enterpriseCode",enterpriseCode);
		return "enterprise/enterpriseAddressList";
	}
	
	@RequestMapping("pageList")
    @ResponseBody
	public PageResult pageList(HttpServletRequest request, EnterpriseAddressRequest req) {
		LogUtil.debug(LOGGER, "分页查询企业地址请求参数:{}", JsonEntityTransform.Object2Json(req));
        PageResult result = new PageResult();
        try{
            if(Check.NuNObj(req)){
				req = new EnterpriseAddressRequest();
            }
			DataTransferObject<PagingResult<EnterpriseAddressEntity>> resultDto = enterpriseService.findPageEnterpriseAddressByCode(req);
			PagingResult<EnterpriseAddressEntity> page = resultDto.getData();
            result.setRows(page.getList());
            result.setTotal(page.getTotal());
        }catch (Exception e){
            LogUtil.error(LOGGER, "分页查询企业地址异常:{}",e);
            return result;
        }
        return result;
	}
	/**
	 * @author:zhangzhengguang
	 * @date:2018/1/17
	 * @description:新增
	 **/
	@RequestMapping("saveEnterpriseAddress")
    @ResponseBody
	public DataTransferObject<Void> saveEnterpriseAddress(HttpServletRequest request, EnterpriseAddressEntity record) {
		LogUtil.debug(LOGGER, "新增企业地址请求参数:{}", JsonEntityTransform.Object2Json(record));
		DataTransferObject<Void> dto = new DataTransferObject<>();
		if(Check.NuNObj(record)){
			dto.setErrorMsg("参数异常");
			return dto;
		}
        try{
			record.setFid(UUIDGenerator.hexUUID());
			enterpriseService.saveEnterpriseAddress(record);
        }catch (Exception e){
            LogUtil.error(LOGGER, "修改企业地址异常:{}",e);
            return dto;
        }
        return dto;
	}
	/**
	 * @author:zhangzhengguang
	 * @date:2018/1/17
	 * @description:编辑回显
	 **/
	@RequestMapping("toedit")
	@ResponseBody
	public DataTransferObject<EnterpriseAddressEntity> toedit(HttpServletRequest request, String fid) {
		DataTransferObject<EnterpriseAddressEntity> dto = new DataTransferObject<>();
		if (Check.NuNObj(fid)) {
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setErrorMsg("参数异常");
			return dto;
		}
		try {
			dto = enterpriseService.getEnterpriseAddressByFid(fid);
		} catch (Exception e) {
			LogUtil.error(LOGGER, "error:{}", e);
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setErrorMsg("系统错误");
			return dto;
		}
		return dto;
	}

	@RequestMapping("updateEnterpriseAddress")
	@ResponseBody
	public DataTransferObject<Void> updateEnterpriseAddress(HttpServletRequest request, EnterpriseAddressEntity record) {
		LogUtil.debug(LOGGER, "修改企业地址请求参数:{}", JsonEntityTransform.Object2Json(record));
		DataTransferObject<Void> dto = new DataTransferObject<>();
		if(Check.NuNObj(record)){
			dto.setErrorMsg("参数异常");
			return dto;
		}
		try{
			enterpriseService.updateEnterpriseAddress(record);
		}catch (Exception e){
			LogUtil.error(LOGGER, "修改企业地址异常:{}",e);
			return dto;
		}
		return dto;
	}

	@RequestMapping("deleteByFid")
	@ResponseBody
	public DataTransferObject<Void> deleteByFid(HttpServletRequest request, String fid) {
		LogUtil.debug(LOGGER, "删除企业地址请求参数:{}", JsonEntityTransform.Object2Json(fid));
		DataTransferObject<Void> dto = new DataTransferObject<>();
		if(Check.NuNObj(fid)){
			dto.setErrorMsg("参数异常");
			return dto;
		}
		try{
			enterpriseService.deleteByFid(fid);
		}catch (Exception e){
			LogUtil.error(LOGGER, "删除企业地址异常:{}",e);
			return dto;
		}
		return dto;
	}



}
