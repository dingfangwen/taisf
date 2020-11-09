package com.taisf.services.refund.dao;


import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.refund.entity.RefundLogEntity;
import org.springframework.stereotype.Repository;

/**
 * 退款
 */
@Repository("refund.refundLogDao")
public class RefundLogDao extends BaseDao {
	private final String SQLID = "refund.refundLogDao.";


	/**
	 * 保存退款操作记录
	 * @author afi
	 * @param entity
	 * @return
	 */
	public int saveRefundLog(RefundLogEntity entity) {
		return mybatisDaoContext.save(SQLID+"saveRefundLog", entity);
	}



}
