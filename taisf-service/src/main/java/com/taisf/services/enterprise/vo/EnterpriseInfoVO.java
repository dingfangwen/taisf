package com.taisf.services.enterprise.vo;

import com.jk.framework.base.entity.BaseEntity;
import com.taisf.services.enterprise.dao.EnterpriseConfigDao;
import com.taisf.services.enterprise.entity.EnterpriseConfigEntity;
import com.taisf.services.enterprise.entity.EnterpriseDayEntity;
import com.taisf.services.enterprise.entity.EnterpriseEntity;

import java.util.List;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/6.
 * @version 1.0
 * @since 1.0
 */
public class EnterpriseInfoVO extends BaseEntity {

    private static final long serialVersionUID = 301231231201446703L;

    /**
     * 企业信息
     */
    private EnterpriseEntity enterpriseEntity;


    /**
     * 企业配置信息信息
     */
    private EnterpriseConfigEntity enterpriseConfigEntity;

    /**
     * 最近的送餐时间
     */
    private List<EnterpriseDayEntity> list;


    public EnterpriseEntity getEnterpriseEntity() {
        return enterpriseEntity;
    }

    public void setEnterpriseEntity(EnterpriseEntity enterpriseEntity) {
        this.enterpriseEntity = enterpriseEntity;
    }

    public EnterpriseConfigEntity getEnterpriseConfigEntity() {
        return enterpriseConfigEntity;
    }

    public void setEnterpriseConfigEntity(EnterpriseConfigEntity enterpriseConfigEntity) {
        this.enterpriseConfigEntity = enterpriseConfigEntity;
    }

    public List<EnterpriseDayEntity> getList() {
        return list;
    }

    public void setList(List<EnterpriseDayEntity> list) {
        this.list = list;
    }
}
