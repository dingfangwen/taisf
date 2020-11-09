package com.taisf.services.base.manager;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taisf.services.base.dao.AreaRegionDao;
import com.taisf.services.base.entity.AreaRegionEntity;

@Service("base.areaRegionManagerImpl")
public class AreaRegionManagerImpl {

	@Resource(name = "base.areaRegionDao")
	private AreaRegionDao areaRegionDao;
	
	 /**
     * 根据类型 查询出所有 区域 省/ 市  /县 集合
     * @param type
     * @return
     */
    public List<AreaRegionEntity> findAllAreaRegion(Integer level) {  
    	return areaRegionDao.findAllAreaRegion(level);
    }
    /**
     * 根据主键code查询 区域信息
     * @param request
     * @return
     */
    public AreaRegionEntity findOneByPrimaryKey(Integer code) {    	
    	return areaRegionDao.findOneByPrimaryKey(code);
    }
    /**
     * 根据parentCode查询子区域
     * @param type
     * @return 
     */
    public List<AreaRegionEntity> findAllByParentCode(Integer parentCode) {  
    	return areaRegionDao.findAllByParentCode(parentCode);
    }
}
