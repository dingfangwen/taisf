package com.taisf.services.enterprise.vo;

import com.taisf.services.enterprise.entity.EnterpriseDayEntity;
import com.taisf.services.enterprise.entity.EnterpriseEntity;

/**
 * <p>企业的配送信息</p>
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
public class EnterpriseDispatchVO  extends EnterpriseEntity {


    /**
     * 周一
     */
    private EnterpriseDayEntity day1;

    private EnterpriseDayEntity day2;

    private EnterpriseDayEntity day3;

    private EnterpriseDayEntity day4;

    private EnterpriseDayEntity day5;

    private EnterpriseDayEntity day6;

    private EnterpriseDayEntity day7;

    public EnterpriseDayEntity getDay1() {
        return day1;
    }

    public void setDay1(EnterpriseDayEntity day1) {
        this.day1 = day1;
    }

    public EnterpriseDayEntity getDay2() {
        return day2;
    }

    public void setDay2(EnterpriseDayEntity day2) {
        this.day2 = day2;
    }

    public EnterpriseDayEntity getDay3() {
        return day3;
    }

    public void setDay3(EnterpriseDayEntity day3) {
        this.day3 = day3;
    }

    public EnterpriseDayEntity getDay4() {
        return day4;
    }

    public void setDay4(EnterpriseDayEntity day4) {
        this.day4 = day4;
    }

    public EnterpriseDayEntity getDay5() {
        return day5;
    }

    public void setDay5(EnterpriseDayEntity day5) {
        this.day5 = day5;
    }

    public EnterpriseDayEntity getDay6() {
        return day6;
    }

    public void setDay6(EnterpriseDayEntity day6) {
        this.day6 = day6;
    }

    public EnterpriseDayEntity getDay7() {
        return day7;
    }

    public void setDay7(EnterpriseDayEntity day7) {
        this.day7 = day7;
    }
}
