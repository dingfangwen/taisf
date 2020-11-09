package com.taisf.services.user.dao;

import com.jk.framework.base.exception.BusinessException;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.UUIDGenerator;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.order.entity.OrderEntity;
import com.taisf.services.user.entity.UserAddressEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * <p> 订单的主表操作 </p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2016/3/12.
 * @version 1.0
 * @since 1.0
 */
@Repository("user.userAddressDao")
public class UserAddressDao extends BaseDao{
	
	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory.getLogger(UserAddressDao.class);

	private String SQLID = "user.userAddressDao.";


    /**
     * 获取当前收货的信息
     * @author afi
     * @param fid
     * @return
     */
    public UserAddressEntity getUserAddressByFid(String fid){
        if(Check.NuNStr(fid)){
        	return null;
        }
        return mybatisDaoContext.findOne(SQLID + "getUserAddressByFid", UserAddressEntity.class, fid);
    }

    /**
     * 保存收货地址
     * @author afi
     * @param record
     * @return
     */
    public int saveUserAddress(UserAddressEntity record){
        if (Check.NuNStr(record.getFid())){
            record.setFid(UUIDGenerator.hexUUID());
        }
        return mybatisDaoContext.save(SQLID + "saveUserAddress", record);
    }


    /**
     * 更新收货地址
     * @author afi
     * @param record
     * @return
     */
    public int updateUserAddress(UserAddressEntity record){

        if(Check.NuNStr(record.getFid())){
        	LogUtil.error(logger,"fid is null on updateUserAddress");
            throw new BusinessException("fid is null on updateUserAddress");
        }
        return mybatisDaoContext.update(SQLID + "updateUserAddress", record);
    }
 
    
    



}
