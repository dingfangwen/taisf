package com.taisf.services.enterprise.dao;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.dao.page.PageBounds;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.enterprise.dto.EnterpriseAddressRequest;
import com.taisf.services.enterprise.entity.EnterpriseAddressEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 企业地址
 * </p>
 * <p/>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/13.
 * @version 1.0
 * @since 1.0
 */
@Repository("enterprise.enterpriseAddressDao")
public class EnterpriseAddressDao extends BaseDao {

	private String SQLID = "enterprise.enterpriseAddressDao.";

	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory.getLogger(EnterpriseAddressDao.class);

	/**
	 * 根据fid获取企业的信息
	 * 
	 * @author afi
	 * @param fid
	 * @return
	 */
	public EnterpriseAddressEntity getEnterpriseAddressByFid(String fid) {
		return mybatisDaoContext.findOne(SQLID + "getEnterpriseAddressByFid", EnterpriseAddressEntity.class, fid);
	}

	/**
	 * 获取当前的企业地址列表
	 * 
	 * @author afi
	 * @param enterpriseCode
	 * @return
	 */
	public List<EnterpriseAddressEntity> getEnterpriseAddressByCode(String enterpriseCode) {
		return mybatisDaoContext.findAll(SQLID + "getEnterpriseAddressByCode", EnterpriseAddressEntity.class,
				enterpriseCode);
	}
	public PagingResult<EnterpriseAddressEntity> findPageEnterpriseAddressByCode(EnterpriseAddressRequest enterpriseAddressRequest) {
		PageBounds pageBounds = new PageBounds();
		pageBounds.setLimit(enterpriseAddressRequest.getLimit());
		pageBounds.setPage(enterpriseAddressRequest.getPage());
		return mybatisDaoContext.findForPage(SQLID + "findPageEnterpriseAddressByCode", EnterpriseAddressEntity.class,
				enterpriseAddressRequest,pageBounds);
	}

	/**
	 * 增加地址
	 * 
	 * @author afi
	 * @param record
	 * @return
	 */
	public int saveEnterpriseAddress(EnterpriseAddressEntity record) {

		return mybatisDaoContext.save(SQLID + "saveEnterpriseAddress", record);
	}

	/**
	 * 修改地址信息
	 * 
	 * @author afi
	 * @param record
	 * @return
	 */
	public int updateEnterpriseAddress(EnterpriseAddressEntity record) {
		return mybatisDaoContext.update(SQLID + "updateEnterpriseAddress", record);
	}

	/**
	 * 删除地址
	 * 
	 * @return
	 */
	public int deleteByFid(String fid) {
		Map<String, Object> map = new HashMap<>();
		map.put("fid", fid);
		return mybatisDaoContext.delete(SQLID + "deleteByFid", map);
	}

	public int deleteByEnterpriseCode(String enterpriseCode) {
		Map<String, Object> map = new HashMap<>();
		map.put("enterpriseCode", enterpriseCode);
		return mybatisDaoContext.delete(SQLID + "deleteByEnterpriseCode", map);
	}
}
