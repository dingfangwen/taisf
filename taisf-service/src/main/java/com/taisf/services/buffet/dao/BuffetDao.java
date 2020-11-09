package com.taisf.services.buffet.dao;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.dao.page.PageBounds;
import com.taisf.services.buffet.dto.BuffetInfoRequest;
import com.taisf.services.buffet.entity.BuffetEntity;
import com.taisf.services.common.dao.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * <p>餐柜信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2019/4/4.
 * @version 1.0
 * @since 1.0
 */
@Repository("buffet.buffetDao")
public class BuffetDao extends BaseDao {

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(BuffetDao.class);

    private String SQLID = "buffet.buffetDao.";


    /**
     * 获取当前的餐柜信息
     * @param buffetInfoRequest
     * @return
     */
    public BuffetEntity getBuffetByFid(BuffetInfoRequest buffetInfoRequest){
        return mybatisDaoContext.findOne(SQLID+"getBuffetByFid", BuffetEntity.class, buffetInfoRequest);
    }


    public BuffetEntity getBuffetByDeviceId(BuffetInfoRequest buffetInfoRequest){
        return mybatisDaoContext.findOne(SQLID+"getBuffetByDeviceId", BuffetEntity.class, buffetInfoRequest);
    }



    /**
     * 保存餐柜信息
     * @author afi
     * @param buffetEntity
     * @return
     */
    public int saveBuffet(BuffetEntity buffetEntity){
        if (Check.NuNObj(buffetEntity)){
            buffetEntity.setCreateTime(new Date());
        }
        return mybatisDaoContext.save(SQLID + "saveBuffet", buffetEntity);
    }

    public int updateById(BuffetEntity buffetEntity){
        return mybatisDaoContext.save(SQLID + "updateByPrimaryKeySelective", buffetEntity);
    }

    public PagingResult<BuffetEntity> pageListBuffet(BuffetInfoRequest request){
        PageBounds pageBounds = new PageBounds();
        pageBounds.setLimit(request.getLimit());
        pageBounds.setPage(request.getPage());
        return mybatisDaoContext.findForPage(SQLID+"pageListBuffet",BuffetEntity.class,request,pageBounds);
    }

}
