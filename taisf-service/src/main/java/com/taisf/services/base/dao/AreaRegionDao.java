package com.taisf.services.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.taisf.services.base.entity.AreaRegionEntity;
import com.taisf.services.common.dao.BaseDao;


/**
 * <p> 地区主表操作 </p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @version 1.0
 * @since 1.0
 */
@Repository("base.areaRegionDao")
public class AreaRegionDao extends BaseDao {

	private String SQLID="base.areaRegionDao.";
	
    /**
     * 根据主键code查询 区域信息
     * @param request
     * @return
     */
    public AreaRegionEntity findOneByPrimaryKey(Integer code) {    	
    	return mybatisDaoContext.findOneSlave(SQLID + "findAreaRegionBycode", AreaRegionEntity.class, code);
    }
    /**
     * 根据类型 查询出所有 区域 省/ 市  /县 集合
     * @param type
     * @return 
     */
    public List<AreaRegionEntity> findAllAreaRegion(Integer level) {  
    	Map<String, Object> params = new HashMap<>();
    	params.put("level", level);
    	return mybatisDaoContext.findAll(SQLID + "findAllAreaRegion", AreaRegionEntity.class, params);
    }
    /**
     * 根据parentCode查询子区域
     * @param type
     * @return 
     */
    public List<AreaRegionEntity> findAllByParentCode(Integer parentCode) {  
    	Map<String, Object> params = new HashMap<>();
    	params.put("parentCode", parentCode);
    	return mybatisDaoContext.findAll(SQLID + "findAllByparentcode", AreaRegionEntity.class, params);
    }
   
}
