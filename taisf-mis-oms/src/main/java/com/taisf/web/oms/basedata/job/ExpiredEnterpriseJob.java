package com.taisf.web.oms.basedata.job;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taisf.services.enterprise.dao.EnterpriseDao;

@Service("basedata.expiredEnterpriseJob")
public class ExpiredEnterpriseJob {
	
	@Resource(name = "enterprise.enterpriseDao")
	private EnterpriseDao enterpriseDao;
	
	public void expiredEnterprise() {
		enterpriseDao.expiredEnterprise();
	}

}
