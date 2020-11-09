package com.taisf.services.enterprise.dao;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.dao.page.PageBounds;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.enterprise.dto.EnterpriseListRequest;
import com.taisf.services.enterprise.dto.EnterprisePageRequest;
import com.taisf.services.enterprise.entity.EnterpriseEntity;
import com.taisf.services.enterprise.vo.EnterpriseExtVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>企业基本信息</p>
 * <p/>
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
@Repository("enterprise.enterpriseDao")
public class EnterpriseDao extends BaseDao {

    private String SQLID = "enterprise.enterpriseDao.";

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(EnterpriseDao.class);


    /**
     * 获取当前的企业基本信息
     *
     * @param enterpriseCode
     * @return
     * @author afi
     */
    public EnterpriseEntity getEnterpriseByCode(String enterpriseCode) {
        return mybatisDaoContext.findOne(SQLID + "getEnterpriseByCode", EnterpriseEntity.class, enterpriseCode);
    }
    
    /**
     * 根据ID获取企业基本信息
     *
     * @param id
     * @return
     * @author wen
     */
    public EnterpriseEntity getEnterpriseById(Integer id) {
        return mybatisDaoContext.findOne(SQLID + "getEnterpriseById", EnterpriseEntity.class, id);
    }
    
    /**
     * 分页获取企业扩展信息列表
     * @param request
     * @return
     */
    public PagingResult<EnterpriseExtVO> getEnterpriseExtByPage(EnterpriseListRequest request) {
    	PageBounds pageBounds=new PageBounds();
        pageBounds.setLimit(request.getLimit());
        pageBounds.setPage(request.getPage());
    	return mybatisDaoContext.findForPage(SQLID + "getEnterpriseExtByPage", EnterpriseExtVO.class, request, pageBounds);
    }



    /**
     * 获取企业列表信息
     * @author afi
     * @param request
     * @return
     */
    public PagingResult<EnterpriseEntity> getEnterpriseByPage(EnterprisePageRequest request){
        PageBounds pageBounds=new PageBounds();
        pageBounds.setLimit(request.getLimit());
        pageBounds.setPage(request.getPage());

        return mybatisDaoContext.findForPage(SQLID + "getEnterpriseByPage", EnterpriseEntity.class, request,pageBounds);
    }


    /**
     * 增加企业
     *
     * @param record
     * @return
     * @author afi
     */
    public int saveEnterprise(EnterpriseEntity record) {

        return mybatisDaoContext.save(SQLID + "saveEnterprise", record);
    }

    /**
     * 修改企业信息
     *
     * @param record
     * @return
     * @author afi
     */
    public int updateEnterprise(EnterpriseEntity record) {
        return mybatisDaoContext.update(SQLID + "updateEnterprise", record);
    }
    
    /**
     * 设置企业过期状态(正常->过期)
     *
     * @param record
     * @return
     * @author afi
     */
    public void expiredEnterprise() {
        mybatisDaoContext.update(SQLID + "expiredEnterprise");
    }


    /**
     * @author:zhangzhengguang
     * @date:2017/10/14
     * @description:查询当前销售员工下维护的企业
     **/
    public PagingResult<EnterpriseEntity> pageListAndManger(EnterpriseListRequest request) {
        PageBounds pageBounds = new PageBounds();
        pageBounds.setLimit(request.getLimit());
        pageBounds.setPage(request.getPage());
        return mybatisDaoContext.findForPage(SQLID + "pageListAndManger", EnterpriseEntity.class, request,pageBounds);
    }


    /**
     * @author:afi
     * @description:根据订单列表聚合企业列表
     * @param supplierCode
     * @return
     */
    public List<EnterpriseEntity> taskByOrder(String supplierCode){
        return mybatisDaoContext.findAll(SQLID+"taskByOrder",EnterpriseEntity.class,supplierCode);
    }
    /**
     * @author:zhangzhengguang
     * @date:2017/10/18
     * @description:查询存在订单的企业列表
     **/
    public List<EnterpriseEntity> findAll() {
        return mybatisDaoContext.findAll(SQLID+"findAll");
    }
    /**
     * @author:zhangzhengguang
     * @date:2017/10/31
     * @description:查询所有企业列表
     **/
    public List<EnterpriseEntity> findAllEnterprise(EnterpriseListRequest enterpriseListRequest) {
        return mybatisDaoContext.findAll(SQLID+"findAllEnterprise",enterpriseListRequest);
    }
}
