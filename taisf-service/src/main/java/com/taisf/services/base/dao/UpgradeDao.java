package com.taisf.services.base.dao;

import com.jk.framework.base.head.Header;
import com.taisf.services.base.entity.UpgradeEntity;
import com.taisf.services.common.dao.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/3/27.
 * @version 1.0
 * @since 1.0
 */
@Repository("basedata.upgradeDao")
public class UpgradeDao extends BaseDao {

    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(UpgradeDao.class);

    private String SQLID = "basedata.upgradeDao.";

    /**
     * 验证当前的版本信息
     * @author afi
     * @param header
     * @return
     */
    public UpgradeEntity getVersion(Header header){
        return mybatisDaoContext.findOne(SQLID + "getVersion", UpgradeEntity.class,header);
    }

}
