package com.taisf.services.enterprise.vo;

import com.jk.framework.base.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>企业的信息</p>
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
public class EnterpriseListDay extends BaseEntity {


    /**
     * 周天数
     */
    private List<EnterpriseDayVO>  list = new ArrayList<>();

    public List<EnterpriseDayVO> getList() {
        return list;
    }

    public void setList(List<EnterpriseDayVO> list) {
        this.list = list;
    }
}
