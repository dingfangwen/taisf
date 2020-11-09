package com.taisf.web.enterprise.common.page;

import com.jk.framework.base.entity.BaseEntity;

import java.util.ArrayList;
import java.util.Date;
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
 * @author afi on on 2017/8/16.
 * @version 1.0
 * @since 1.0
 */
public class DataInfo extends BaseEntity {

    /**
     * 实际开始时间
     */
    private Date realStartTime;


    /**
     * 实际结束时间
     */
    private Date realEndTime;

    /**
     * 总天数
     */
    private Long total;

    /**
     * 时间key
     */
    private List<String> timeKeyList = new ArrayList<>();


    public Date getRealStartTime() {
        return realStartTime;
    }

    public void setRealStartTime(Date realStartTime) {
        this.realStartTime = realStartTime;
    }

    public Date getRealEndTime() {
        return realEndTime;
    }

    public void setRealEndTime(Date realEndTime) {
        this.realEndTime = realEndTime;
    }

    public List<String> getTimeKeyList() {
        return timeKeyList;
    }

    public void setTimeKeyList(List<String> timeKeyList) {
        this.timeKeyList = timeKeyList;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
