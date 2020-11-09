package com.taisf.services.base.manager;

import com.jk.framework.base.head.Header;
import com.taisf.services.base.dao.UpgradeDao;
import com.taisf.services.base.entity.UpgradeEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("basedata.versionManagerImpl")
public class VersionManagerImpl {

	@Resource(name = "basedata.upgradeDao")
	private UpgradeDao upgradeDao;

	public UpgradeEntity getVersion(Header header) {
		UpgradeEntity upgrade = upgradeDao.getVersion(header);
		return upgrade;
	}

}
