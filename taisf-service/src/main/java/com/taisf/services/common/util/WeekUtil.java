package com.taisf.services.common.util;

import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.DateUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/4/12.
 * @version 1.0
 * @since 1.0
 */
public class WeekUtil {

    /**
     * 获取今天周几
     * @return
     */
    public static int getWeek() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取今天周几
     * @return
     */
    public static int getWeek(Date time) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        return c.get(Calendar.DAY_OF_WEEK);
    }


    /**
     * 获取当前的周几
     * @param week
     * @return
     */
    public static String getWeekName(Integer week){
        String weekName = "";
        if (Check.NuNObj(week)){
            return weekName;
        }
        switch (week){
            case 1:
                weekName = "周日";
                break;
            case 2:
                weekName = "周一";
                break;
            case 3:
                weekName = "周二";
                break;
            case 4:
                weekName = "周三";
                break;
            case 5:
                weekName = "周四";
                break;
            case 6:
                weekName = "周五";
                break;
            case 7:
                weekName= "周六";
                break;
        }
        return weekName;
    }

    public static void main(String[] args) {

        Date first = DateUtil.getFirstDayOfWeekDay(new Date());

        System.out.println(first);
        System.out.println(getWeek(DateUtil.getFirstDayOfWeekDay(new Date())));
    }
}
