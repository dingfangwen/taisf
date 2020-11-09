package com.taisf.services.order.vo;

import com.jk.framework.base.entity.BaseEntity;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/4/23.
 * @version 1.0
 * @since 1.0
 */
public class InitFaceVO  extends BaseEntity {

    /**
     * 是否需要密码
     */
    private Boolean needPwd = true;

    public Boolean getNeedPwd() {
        return needPwd;
    }

    public void setNeedPwd(Boolean needPwd) {
        this.needPwd = needPwd;
    }
}
