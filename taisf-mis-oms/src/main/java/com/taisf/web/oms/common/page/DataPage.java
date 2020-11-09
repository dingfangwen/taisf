package com.taisf.web.oms.common.page;

import com.jk.framework.base.exception.BusinessException;
import com.jk.framework.base.page.PageRequest;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.DateUtil;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.ValueUtil;

import java.util.*;

/**
 * <p>处方天数的分页信息</p>
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
public class DataPage {


    public static void main(String[] args) {
        PageRequest request = new PageRequest();
        Date startTime =DateUtil.jumpDate(new Date(),-100);
        Date endTime =new Date();
        DataInfo dataInfo =transDatePage(startTime,endTime,request);

        System.out.println(JsonEntityTransform.Object2Json(dataInfo));
    }
    /**
     * 转化当前的时间成分页信息
     * @param startTime
     * @param endTime
     * @return
     */
    public static DataInfo transDatePage_bak( Date startTime, Date endTime, PageRequest request){
        DataInfo dataInfo =  new DataInfo();

        if (Check.NuNObjs(startTime,endTime,request)){
            new BusinessException("参数异常");
        }
        if (Check.NuNObjs(request.getLimit(),request.getPage())){
            new BusinessException("参数异常");
        }
        int jump = (request.getPage() - 1) * request.getLimit();
        //真正的开始时间
        Date realStartTime = DateUtil.jumpDate(DateUtil.getDayStart(startTime),jump);
        Date realEndTime = DateUtil.getDayEnd(DateUtil.jumpDate(realStartTime,request.getLimit()));
        if (realEndTime.after(DateUtil.getDayEnd(endTime))){
            realEndTime = DateUtil.getDayEnd(endTime);
        }
        List<String> list = dateSplit(realStartTime,realEndTime);
        int total = countDateSplit(DateUtil.getDayStart(startTime),DateUtil.getDayEnd(endTime));
        //设置当前的区间
        dataInfo.setTimeKeyList(list);
        //设置真实的开始时间
        dataInfo.setRealStartTime(realStartTime);
        dataInfo.setRealEndTime(realEndTime);
        //设置当前的总天数
        dataInfo.setTotal(ValueUtil.getlongValue(total));
        return dataInfo;
    }

    public static DataInfo transDatePage( Date startTime, Date endTime, PageRequest request){
        DataInfo dataInfo =  new DataInfo();

        if (Check.NuNObjs(startTime,endTime,request)){
            new BusinessException("参数异常");
        }
        if (Check.NuNObjs(request.getLimit(),request.getPage())){
            new BusinessException("参数异常");
        }
        int jump = request.getPage() * request.getLimit();

        //真正的开始时间
        Date realStartTime = DateUtil.jumpDate(DateUtil.getDayStart(endTime),-jump+1);
        Date realEndTime = DateUtil.getDayEnd(DateUtil.jumpDate(realStartTime,request.getLimit()-1));
        if (startTime.after(DateUtil.getDayStart(realStartTime))){
            realStartTime = DateUtil.getDayStart(startTime);
        }
        List<String> list = dateSplit(realStartTime,realEndTime);
        int total = countDateSplit(DateUtil.getDayStart(startTime),DateUtil.getDayEnd(endTime));
        //设置当前的区间
        Collections.reverse(list);
        dataInfo.setTimeKeyList(list);
        //设置真实的开始时间
        dataInfo.setRealStartTime(realStartTime);
        dataInfo.setRealEndTime(realEndTime);
        //设置当前的总天数
        dataInfo.setTotal(ValueUtil.getlongValue(total));

        return dataInfo;
    }


    /**
     * 获取当前区间内的天数
     * @author afi
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    private static Integer countDateSplit(Date startDate, Date endDate){
        if (endDate.before(startDate)) throw new BusinessException("开始时间应该在结束时间之后");
        int count = 1;
        Date tomorrow = getTomorrow(startDate);
        while (transDateTime2Date(tomorrow).before(transDateTime2Date(endDate))){
            tomorrow = getTomorrow(tomorrow);
            count ++;
        }
        return count;
    }





    /**
     * 获取当前区间内的天数列表
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public static List<String> dateSplit(Date startDate, Date endDate) {
        if (endDate.before(startDate)) throw new BusinessException("开始时间应该在结束时间之后");
        List<Date> dateList = new ArrayList<Date>();
        dateList.add(startDate);
        Date tomorrow = getTomorrow(startDate);
        while (transDateTime2Date(endDate).after(transDateTime2Date(tomorrow))){
            dateList.add(tomorrow);
            tomorrow = getTomorrow(tomorrow);
        }
        if (transDateTime2Date(endDate).getTime() == transDateTime2Date(tomorrow).getTime() ){
            dateList.add(tomorrow);
        }
        List<String> rst = new ArrayList<>();
        for(int i=0;i<dateList.size();i++){
            Date ele = dateList.get(i);
            rst.add(DateUtil.dateFormat(ele));
        }
        return rst;
    }

    /**
     * 将当前时间转化成当前的第一秒
     * @author afi
     * @param date
     * @return
     */
    private static Date transDateTime2Date(Date date){
        String dateStr = DateUtil.dateFormat(date);
        dateStr = dateStr + " 00:00:00";
        try {
            return DateUtil.parseDate(dateStr,DateUtil.timestampPattern);
        }catch (Exception e){
            throw new BusinessException(e);
        }
    }


    /**
     * 获取明天日期
     * @param current
     * @return
     */
    private static Date getTomorrow(Date current) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(current);
        cal.add(Calendar.DATE, 1);    //明天
        return cal.getTime();
    }
}
