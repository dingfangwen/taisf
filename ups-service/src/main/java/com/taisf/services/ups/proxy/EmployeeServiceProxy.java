package com.taisf.services.ups.proxy;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.utils.Check;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.ups.api.EmployeeService;
import com.taisf.services.ups.entity.EmployeeEntity;
import com.taisf.services.ups.service.EmployeeServiceImpl;

@Component("ups.employeeServiceProxy")
public class EmployeeServiceProxy implements EmployeeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceProxy.class);



	@Resource(name="ups.employeeServiceImpl")
	private EmployeeServiceImpl employeeService;



	/**
	 * 通过医生姓名查询医生列表
	 * @author afi
	 * @param name
	 * @return
	 */
	@Override
	public DataTransferObject<List<EmployeeEntity>> findEmployeeByName(String name) {
		DataTransferObject<List<EmployeeEntity>> dto = new DataTransferObject<List<EmployeeEntity>>();
		if(Check.NuNObj(name)){
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("参数异常");
			return dto;
		}
		try {
			List<EmployeeEntity> employeeEntityList = employeeService.findEmployeeByName(name);
			dto.setData(employeeEntityList);
		} catch (Exception e) {
			LogUtil.error(LOGGER, "error:{}", e);
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");

		}
		return dto;
	}


	@Override
	public DataTransferObject<List<EmployeeEntity>> listEmployeeByDepartId(String departmentId) {
		DataTransferObject<List<EmployeeEntity>> dto = new DataTransferObject<List<EmployeeEntity>>();
		if(Check.NuNObj(departmentId)){
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("参数异常");
			return dto;
		}
		try {
			List<EmployeeEntity> employeeEntityList = employeeService.findEmployeeByDepartId(departmentId);
			dto.setData(employeeEntityList);
		} catch (Exception e) {
			LogUtil.error(LOGGER, "error:{}", e);
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");

		}
		return dto;
	}

	@Override
	public DataTransferObject<List<EmployeeEntity>> findAllDoctor(){
		DataTransferObject<List<EmployeeEntity>> dto = new DataTransferObject<List<EmployeeEntity>>();
		try {
			List<EmployeeEntity> employeeEntityList = employeeService.findAllDoctor();
			dto.setData(employeeEntityList);
		} catch (Exception e) {
			LogUtil.error(LOGGER, "error:{}", e);
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");

		}
		return dto;
	}
	@Override
	public DataTransferObject<List<EmployeeEntity>> findAllEmployee() {
		DataTransferObject<List<EmployeeEntity>> dto = new DataTransferObject<>();
		try {
			List<EmployeeEntity> list = employeeService.findAllDoctor();
			dto.setData(list);
		} catch (Exception e) {
			LogUtil.error(LOGGER, "error:{}", e);
			dto.setErrCode(DataTransferObject.ERROR);
			dto.setMsg("系统异常");
		}
		return dto;
	}
}
