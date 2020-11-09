package com.taisf.services.user.dao;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.dao.page.PageBounds;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.user.dto.UserAccountRequest;
import com.taisf.services.user.dto.UserRequest;
import com.taisf.services.user.entity.UserEntity;
import com.taisf.services.user.vo.UserAccountVO;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p> 用户主表操作 </p>
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
@Repository("user.userDao")
public class UserDao extends BaseDao {
    private String SQLID = "user.userDao.";


    public int add(UserEntity userEntity){
        if (Check.NuNObj(userEntity.getCreateTime())){
            userEntity.setCreateTime(new Date());
        }
        return mybatisDaoContext.save(SQLID + "saveUser", userEntity);
    }

    public UserEntity queryBySelective(UserEntity userEntity) {
        return mybatisDaoContext.findOne(SQLID + "selectByPrimaryKeySelective", UserEntity.class, userEntity);
    }

    public Integer queryCountBySelective(UserEntity userEntity) {
        return mybatisDaoContext.findOne(SQLID + "selectCountSelective", Integer.class, userEntity);
    }

    /**
     * 根据用户id更新用户信息
     *
     * @param userEntity
     * @return
     */
    public int updateUser(UserEntity userEntity) {
        return mybatisDaoContext.update(SQLID + "updateUser", userEntity);
    }

    /**
     * 设置免密
     * @param userId
     * @param isPwd
     * @return
     */
    public int updateIsPwd(String userId,Integer isPwd){
        Map<String,Object> par = new HashMap<>();
        par.put("userUid",userId);
        par.put("isPwd",isPwd);
        return mybatisDaoContext.update(SQLID + "updateIsPwd", par);

    }

    /**
     * 更新用户
     * @param userId
     * @param isAdmin
     * @return
     */
    public  int updateUserAdmin(String userId,Integer isAdmin){
        Map<String,Object> par = new HashMap<>();
        par.put("userId",userId);
        par.put("isAdmin",isAdmin);
        return mybatisDaoContext.update(SQLID + "updateUserAdmin", par);

    }


    public int updateUserQrCode(String uid,String qrCode) {
        Map<String,Object> par = new HashMap<>();
        par.put("userUid",uid);
        par.put("qrCode",qrCode);
        return mybatisDaoContext.update(SQLID + "updateUserQrCode", par);
    }

    /**
     * 根据用户entrpriseCode 查询当前的用户
     * @param entrpriseCode
     * @return
     */
    public List<UserEntity> getOkUserByEntrpriseCode(String entrpriseCode){

        return mybatisDaoContext.findAll(SQLID + "getOkUserByEntrpriseCode", UserEntity.class, entrpriseCode);
    }

    /**
     * 根据用户entrpriseCode 查询当前的用户
     * @param enterpriseCode
     * @return
     */
    public UserEntity getOkUserByEntrpriseCodePhone(String enterpriseCode,String userPhone){
        Map<String,Object> map = new HashMap<>();
        map.put("userPhone",userPhone);
        map.put("enterpriseCode",enterpriseCode);
        return mybatisDaoContext.findOne(SQLID + "getOkUserByEntrpriseCodePhone", UserEntity.class, map);
    }


    /**
     * 获取当前的账户信息
     * @param request
     * @return
     */
    public PagingResult<UserAccountVO> getUserAccountPage(UserAccountRequest request){
        PageBounds pageBounds = new PageBounds();
        pageBounds.setLimit(request.getLimit());
        pageBounds.setPage(request.getPage());
        return mybatisDaoContext.findForPage(SQLID + "getUserAccountPage",UserAccountVO.class,request,pageBounds);


    }



    /**
     * 根据用户payCode查询用户
     * @param payCode
     * @return
     */
    public UserEntity getUserByPayCode(String payCode) {

        Map<String,Object> par = new HashMap<>();
        par.put("payCode",payCode);
        return mybatisDaoContext.findOne(SQLID + "getUserByPayCode", UserEntity.class, par);
    }


    /**
     * 根据用户userid查询用户
     *
     * @param userId
     * @return
     */
    public UserEntity getUserByUidAndType(String userId,Integer userType) {
        Map<String,Object> par = new HashMap<>();
        par.put("userUid",userId);
        par.put("userType",userType);
        return mybatisDaoContext.findOne(SQLID + "getUserByUidAndType", UserEntity.class, par);
    }
    /**
     * 根据用户userid查询用户
     *
     * @param userId
     * @return
     */
    public UserEntity getUserByUid(String userId) {

        return mybatisDaoContext.findOne(SQLID + "getUserByUid", UserEntity.class, userId);
    }


    public List<UserEntity> getUserByType(Integer type) {
        return mybatisDaoContext.findAll(SQLID + "getUserByType", UserEntity.class, type);
    }


    public PagingResult<UserEntity> pageKnightListUser(UserRequest request) {
        PageBounds pageBounds = new PageBounds();
        pageBounds.setPage(request.getPage());
        pageBounds.setLimit(request.getLimit());
        return mybatisDaoContext.findForPage(SQLID + "pageKnightListUser", UserEntity.class,request, pageBounds);
    }

    public PagingResult<UserEntity> pageListUser(UserRequest request) {
        PageBounds pageBounds = new PageBounds();
        pageBounds.setPage(request.getPage());
        pageBounds.setLimit(request.getLimit());
        return mybatisDaoContext.findForPage(SQLID + "pageListUser", UserEntity.class,request, pageBounds);
    }

    public PagingResult<UserEntity> pageListCompanyUser(UserRequest request) {
        PageBounds pageBounds = new PageBounds();
        pageBounds.setPage(request.getPage());
        pageBounds.setLimit(request.getLimit());
        return mybatisDaoContext.findForPage(SQLID + "pageListCompanyUser", UserEntity.class,request, pageBounds);
    }

    public List<UserEntity> findAllCompanyUser(UserRequest request) {
        return mybatisDaoContext.findAll(SQLID + "pageListCompanyUser", UserEntity.class,request);
    }

    /**
     * 激活用户信息
     *
     * @param userId
     * @return
     */
    public int updateUser2Activity(String userId,String pwd) {
        Map<String, Object> par = new HashMap<>();
        par.put("userUid", userId);
        par.put("userPassword", pwd);
        return mybatisDaoContext.update(SQLID + "updateUser2Activity", par);
    }


    /**
     * 根据用户电话查询用户
     * @param userPhone
     * @return
     */
    public UserEntity getUserByUserPhone(String userPhone,int userType){
        Map<String,Object> par = new HashMap<>();
        par.put("userPhone", userPhone);
        par.put("userType", userType);

        return mybatisDaoContext.findOne(SQLID + "getUserByUserPhone", UserEntity.class, par);
    }
    /**
     * 根据用户id查询用户
     * @param id
     * @return
     */
    public UserEntity getUserById(Integer id){
        return mybatisDaoContext.findOne(SQLID + "selectByPrimaryKey", UserEntity.class, id);
    }

    /**
     * 修改用户登录密码
     * @param userId
     * @param userPassword
     * @return
     */
    public int updateUserPwd(String userId,String userPassword){
        Map<String,Object> par = new HashMap<>();
        par.put("userUid",userId);
        par.put("userPassword",userPassword);
        return mybatisDaoContext.update(SQLID + "updateUserPwd", par );
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/11/3
     * @description:根据手机号查询
     **/
    public UserEntity getByUserPhone(String userPhone){
        return mybatisDaoContext.findOne(SQLID + "selectByUserPhone", UserEntity.class, userPhone);
    }

    /**
     * 根据支付code获取骑手信息
     * @param payCode
     * @return
     */
    public UserEntity getByPayCode(String payCode){
        return mybatisDaoContext.findOne(SQLID + "getByPayCode", UserEntity.class, payCode);
    }

}
