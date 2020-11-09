package com.jk.services.payment.dao;

import com.jk.services.payment.entity.PayEntity;
import com.jk.services.payment.entity.PayInfo;
import com.jk.services.payment.entity.ReturnPay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;


/**
 * <p>支付账号</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/5/13.
 * @version 1.0
 * @since 1.0
 */
@Repository("payment.payDao")
public class PayDao extends BaseDao{

	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory.getLogger(PayDao.class);

	private String SQLID = "payment.payDao.";

    public int insert(PayEntity record){
		return mybatisDaoContext.save(SQLID + "insert",record);
	}

	public int update(PayEntity record){
		return mybatisDaoContext.update(SQLID + "update",record);
	}

	public PayEntity selectByInfo(PayInfo payInfo){

		return mybatisDaoContext.findOne(SQLID + "selectByInfo",PayEntity.class,payInfo);
	}

	public PayEntity selectByInfoWithoutMoney(PayInfo payInfo){

		return mybatisDaoContext.findOne(SQLID + "selectByInfoWithoutMoney",PayEntity.class,payInfo);
	}

	/**
	 * 根据流水号获取当前的支付记录
	 * @author afi
	 * @param orgInSerialNo
	 * @return
	 */
	public PayEntity selectByInfoHasPayBySerialNo(String orgInSerialNo){

		return mybatisDaoContext.findOne(SQLID + "selectByInfoHasPayBySerialNo",PayEntity.class,orgInSerialNo);
	}



	public PayEntity selectById(Integer id){
		return mybatisDaoContext.findOne(SQLID + "selectById",PayEntity.class,id);
	}
	/**
	 * 付款流水号业务id获取支付记录
	 * @param put
	 * @return
	 */
	public PayEntity selectByResIdAndBizId(Map<String, Object> put){
		return mybatisDaoContext.findOne(SQLID + "selectByResIdAndBizId",PayEntity.class,put);
	}

	/**
	 * 获取需要通知的列表
	 * @param map
	 * @return
	 */
	public List<ReturnPay> queryNotifyList(Map<String, Object> map){
		return mybatisDaoContext.findAllByMaster(SQLID + "queryNotifyList",ReturnPay.class,map);
	}
	/**
	 * 获取未通知的URL 列表
	 * @return
	 */
	public List<String> queryNotifyUrlList(){
		return mybatisDaoContext.findAll(SQLID + "queryNotifyUrlList",String.class,null);
	}

	/**
	 * 获取银行等待查询的记录
	 * @param map
	 * @return
	 */
	public List<PayInfo> queryBankListByCode(Map<String, Object> map){
		return mybatisDaoContext.findAll(SQLID + "queryBankListByCode",PayInfo.class,map);
	}
}