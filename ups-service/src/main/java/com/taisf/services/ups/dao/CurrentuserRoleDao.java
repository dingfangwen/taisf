package com.taisf.services.ups.dao;

import java.util.HashMap;
import java.util.Map;

import com.taisf.services.common.UpsBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jk.framework.base.exception.BusinessException;
import com.jk.framework.base.utils.Check;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.ups.entity.CurrentuserRoleEntity;

/**
 * <p>角色测试</p>
 * <p/>
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
@Repository("ups.currentuserRoleDao")
public class CurrentuserRoleDao extends UpsBaseDao {


    private String SQLID="ups.currentuserRoleDao.";


	/**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(CurrentuserRoleDao.class);

    /**
     *
     * 插入用户关系表
     *
     * @author afi
     * @created 2016年3月12日
     *
     * @param currentuserRoleEntity
     */
    public void insertCurrentuserRole(CurrentuserRoleEntity currentuserRoleEntity){
        mybatisDaoContext.save(SQLID+"insertCurrentuserRole", currentuserRoleEntity);
    }

    /**
     *
     * 删除用户角色关系表
     *
     * @author afi
     * @created 2016年3月12日
     *
     * @param userFid
     */
    public void delCurrentuserRoleByUserFid(String userFid){
        if(Check.NuNStr(userFid)){
        	LogUtil.info(logger,"on delCurrentuserRoleByUserFid the userFid is null ");
            throw  new BusinessException("on delCurrentuserRoleByUserFid the userFid is null ");
        }
        Map<String,Object> par = new HashMap<>();
        par.put("userFid",userFid);
        mybatisDaoContext.delete(SQLID + "delCurrentuserRoleByUserFid", par);
    }
}
