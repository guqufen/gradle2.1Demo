package net.fnsco.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

public class DateUtils {
    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static Date getDayEndTime(int dates) {
        Date result = null;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, dates);
        String tempStr = sf.format(calendar.getTime());
        tempStr += " 23:59:59";
        try {
            result = sf1.parse(tempStr);
        } catch (ParseException e) {
            logger.error("DateUtils.getDayEndTime()日志转换出错", e);
        }
        return result;
    }

    public static String getDateStartTime(String date) {
        String result = "";
        if (Strings.isNullOrEmpty(date)) {
            return result;
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        try {
            Date tempDate = sf.parse(date);
            result = sf1.format(tempDate);
        } catch (Exception e) {
            logger.error("DateUtils.getDayEndTime()日志转换出错", e);
            return "";
        }
        return result + "000000";
    }

    public static String getDateEndTime(String date) {
        String result = "";
        if (Strings.isNullOrEmpty(date)) {
            return result;
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        try {
            Date tempDate = sf.parse(date);
            result = sf1.format(tempDate);
        } catch (Exception e) {
            logger.error("DateUtils.getDayEndTime()日志转换出错", e);
            return "";
        }
        return result + "235959";
    }
    /**
     * compare_date:(这里用一句话描述这个方法的作用) 比较时间
     *
     * @param DATE1
     * @param DATE2
     * @return    设定文件
     * @return int    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static int compare_date(String DATE1, String DATE2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getYear() >= dt2.getYear()) {// 判断年
                if (dt1.getTime() > dt2.getTime()) {
                    return 1;
                } else if (dt1.getTime() < dt2.getTime()) {
                    return -1;
                } else {
                    return 0;
                }

            } else {
                return 2;// 年份小于当前年份
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    /**
     * dateFormatToStr:(这里用一句话描述这个方法的作用)
     *
     * @param date
     * @return    设定文件
     * @return String    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static String dateFormatToStr(Date date){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
    
    /**
     * getTimeByMinute:(这里用一句话描述这个方法的作用)获取当前时间前或后N分钟的时间字符串
     *
     * @param minute
     * @return    设定文件
     * @return String    DOM对象
     * @throws 
     * @author tangliang
     * @since  CodingExample　Ver 1.1
     */
    public static String getTimeByMinuteStr(int minute) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }
    /**
     * getTimeByMinuteDate:(这里用一句话描述这个方法的作用)获取当前时间前或后N分钟的时间Date格式
     *
     * @param minute
     * @return    设定文件
     * @return Date    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static Date getTimeByMinuteDate(int minute) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }
    /**
     * getTimeByDayStr:(这里用一句话描述这个方法的作用)获取当前时间前或后N天的时间字符串
     *
     * @param day
     * @return    设定文件
     * @return String    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static String getTimeByDayStr(int day) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime())+"000000";
    }
}
