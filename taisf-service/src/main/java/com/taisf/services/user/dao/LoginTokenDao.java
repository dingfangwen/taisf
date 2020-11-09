package com.taisf.services.user.dao;

import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.user.entity.LoginTokenEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


/**
 * <p> 用户登录token操作 </p>
 * <PRE>
 * <BR>	修改记录∫
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2016/3/12.
 * @version 1.0
 * @since 1.0
 */
@Repository("user.loginTokenDao")
public class LoginTokenDao extends BaseDao {

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(LoginTokenDao.class);

    private String SQLID = "user.loginTokenDao.";

    
    public int saveLoginToken(LoginTokenEntity loginTokenEntity){
        Map<String,Object> par = new HashMap<>();
        return mybatisDaoContext.save(SQLID + "saveLoginToken", loginTokenEntity);
    }

    
    /**
     * 查询用户token是否失效
     * @param loginTokenEntity
     * @return
     */
    public Integer queryCountExpireToken(LoginTokenEntity loginTokenEntity){
        return mybatisDaoContext.findOne(SQLID + "selectExpireTokenCount", Integer.class, loginTokenEntity);
    }




    /**
     * 删除token信息
     * @return
     */
    public int deleteById(Integer id){
        Map<String,Object> par = new HashMap<>();
        par.put("id",id);
        return mybatisDaoContext.delete(SQLID + "deleteById", par);
    }


    /**
     * 查询token信息
     * @param userId
     * @param deviceUuid
     * @return
     */
    public LoginTokenEntity getToken(String userId,String deviceUuid,Integer loginSource){
        Map<String,Object> par = new HashMap<>();
        par.put("userId",userId);
        par.put("deviceUuid",deviceUuid);
        par.put("loginSource",loginSource);
        return mybatisDaoContext.findOne(SQLID + "getToken", LoginTokenEntity.class, par);
    }


    /**
     * 查询token信息
     * @param token
     * @return
     */
    public LoginTokenEntity getTokenByToken(String token){
        return mybatisDaoContext.findOne(SQLID + "getTokenByToken", LoginTokenEntity.class, token);
    }


    /**
     * 查询token信息
     * @param loginTokenEntity
     * @return
     */
    public int upateTokenById(LoginTokenEntity loginTokenEntity){
        return mybatisDaoContext.update(SQLID + "updateByPrimaryKeySelective", loginTokenEntity);
    }
    
    
    
    
    
    
    
}
