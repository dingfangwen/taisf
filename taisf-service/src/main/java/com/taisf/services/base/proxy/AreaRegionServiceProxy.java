package com.taisf.services.base.proxy;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.taisf.services.base.entity.AreaRegionEntity;
import com.taisf.services.base.manager.AreaRegionManagerImpl;
import com.taisf.services.base.service.AreaRegionService;

@Component("base.areaRegionServiceProxy")
public class AreaRegionServiceProxy implements AreaRegionService {

	@Resource(name = "base.areaRegionManagerImpl")
	private AreaRegionManagerImpl areaRegionManager;

	 /**
     * 根据类型 查询出所有 区域 省/ 市  /县 集合
     * @param type
     * @return
     */
    public List<AreaRegionEntity> findAllAreaRegion(Integer level) {  
    	return areaRegionManager.findAllAreaRegion(level);
    }
    /**
     * 根据主键code查询 区域信息
     * @param request
     * @return
     */
    public AreaRegionEntity findOneByPrimaryKey(Integer code) {    	
    	return areaRegionManager.findOneByPrimaryKey(code);
    }
    /**
     * 根据parentCode查询子区域
     * @param type
     * @return 
     */
    public List<AreaRegionEntity> findAllByParentCode(Integer parentCode) {  
    	return areaRegionManager.findAllByParentCode(parentCode);
    }
	
}
