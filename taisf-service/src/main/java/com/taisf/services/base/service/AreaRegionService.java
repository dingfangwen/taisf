package com.taisf.services.base.service;

import java.util.List;

import com.taisf.services.base.entity.AreaRegionEntity;

public interface AreaRegionService {

	/**
     * 根据类型 查询出所有 区域 省/ 市  /县 集合
     * @param type
     * @return
     */
    public List<AreaRegionEntity> findAllAreaRegion(Integer level);
    /**
     * 根据主键code查询 区域信息
     * @param request
     * @return
     */
    public AreaRegionEntity findOneByPrimaryKey(Integer code);
    /**
     * 根据parentCode查询子区域
     * @param type
     * @return 
     */
    public List<AreaRegionEntity> findAllByParentCode(Integer parentCode);
}
