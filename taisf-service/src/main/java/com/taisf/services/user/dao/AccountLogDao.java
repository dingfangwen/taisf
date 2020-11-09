package com.taisf.services.user.dao;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.dao.page.PageBounds;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.user.dto.AccountLogRequest;
import com.taisf.services.user.dto.UserMoneyRequest;
import com.taisf.services.user.entity.AccountLogEntity;
import com.taisf.services.user.vo.AccountUserLogVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p>用户的账户记录信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/3/21.
 * @version 1.0
 * @since 1.0
 */
@Repository("user.accountLogDao")
public class AccountLogDao extends BaseDao {


    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(AccountLogDao.class);

    private String SQLID = "user.accountLogDao.";





    /**
     * 获取供应商用户的充值记录
     * @author afi
     * @param userMoneyRequest
     * @return
     */
    public List<AccountUserLogVO> rechargeMoneyLogAll(UserMoneyRequest userMoneyRequest){
        return mybatisDaoContext.findAll(SQLID + "rechargeMoneyLogByPage",AccountUserLogVO.class,userMoneyRequest);
    }

    /**
     * 获取供应商用户的充值记录
     * @author afi
     * @param userMoneyRequest
     * @return
     */
    public PagingResult<AccountUserLogVO> rechargeMoneyLogByPage(UserMoneyRequest userMoneyRequest){
        PageBounds pageBounds = new PageBounds();
        pageBounds.setLimit(userMoneyRequest.getLimit());
        pageBounds.setPage(userMoneyRequest.getPage());
        return mybatisDaoContext.findForPage(SQLID + "rechargeMoneyLogByPage",AccountUserLogVO.class,userMoneyRequest,pageBounds);
    }


    /**
     * 获取当前的账户的操作记录
     * @author afi
     * @param accountLogRequest
     * @return
     */
    public PagingResult<AccountLogEntity> getAccountLogByPage(AccountLogRequest accountLogRequest){
        PageBounds pageBounds = new PageBounds();
        pageBounds.setLimit(accountLogRequest.getLimit());
        pageBounds.setPage(accountLogRequest.getPage());
        return mybatisDaoContext.findForPage(SQLID + "getAccountLogByPage",AccountLogEntity.class,accountLogRequest,pageBounds);
    }

    /**
     * 获取当前的账户的操作记录
     * @author afi
     * @param accountLogRequest
     * @return
     */
    public PagingResult<AccountLogEntity> getIncomeLogByPage(AccountLogRequest accountLogRequest){
        PageBounds pageBounds = new PageBounds();
        pageBounds.setLimit(accountLogRequest.getLimit());
        pageBounds.setPage(accountLogRequest.getPage());
        return mybatisDaoContext.findForPage(SQLID + "getIncomeLogByPage",AccountLogEntity.class,accountLogRequest,pageBounds);

    }




    /**
     * 保存当前的操作记录
     * @author afi
     * @param accountLogEntity
     * @return
     */
    public int saveAccountLog(AccountLogEntity  accountLogEntity){
        if (Check.NuNObj(accountLogEntity.getCreateTime())){
            accountLogEntity.setCreateTime(new Date());
        }
        return mybatisDaoContext.save(SQLID + "saveAccountLog",accountLogEntity);
    }



    /**
     * 获取当前是否存在
     * @author afi
     * @param bizSn
     * @return
     */
    public AccountLogEntity getAccountLogByBizSn(String bizSn){
        return mybatisDaoContext.findOne(SQLID + "getAccountLogByBizSn",AccountLogEntity.class,bizSn);
    }


}
