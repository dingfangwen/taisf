package com.taisf.services.user.vo;

import com.jk.framework.excel.annotation.FieldMeta;
import com.taisf.services.user.entity.AccountLogEntity;

/**
 * <p>获取当前的充值记录</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/4/2.
 * @version 1.0
 * @since 1.0
 */
public class AccountUserLogVO  extends AccountLogEntity {


    /**
     * 用户名称
     */
    @FieldMeta(name="姓名",order=4)
    private String userName;

    /**
     * 用户名称
     */
    @FieldMeta(name="企业名称",order=4)
    private String enterpriseName;

    /**
     * 手机号
     */
    @FieldMeta(name="手机号",order=5)
    private String userPhone;

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
