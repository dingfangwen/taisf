package com.taisf.services.enterprise.vo;

import com.taisf.services.enterprise.entity.EnterpriseDayEntity;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/19.
 * @version 1.0
 * @since 1.0
 */
public class EnterpriseDayVO extends EnterpriseDayEntity {

    /**
     * 是否可修改
     */
    private  Boolean changeFlag = false;

    public Boolean getChangeFlag() {
        return changeFlag;
    }

    public void setChangeFlag(Boolean changeFlag) {
        this.changeFlag = changeFlag;
    }
}
