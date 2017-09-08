package net.fnsco.core.utils;

import java.math.BigDecimal;
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
     * formatDateStr:(这里用一句话描述这个方法的作用)转换格式
     *
     * @param dateStr
     * @return    设定文件
     * @return String    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static String formatDateStrOutput(String dateStr) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        try {
            Date temp = sf1.parse(dateStr);
            return sf.format(temp);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public static String formatDateStrInput(String dateStr) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        try {
            Date temp = sf.parse(dateStr);
            return sf1.format(temp);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public static String getNowDateStr() {
        String result = "";
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        result = sf.format(new Date());
        return result;
    }

    public static String getNowDateDayStr() {
        String result = "";
        SimpleDateFormat sf = new SimpleDateFormat("dd");
        result = sf.format(new Date());
        return result;
    }

    public static String getNowYMDStr() {
        String result = "";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        result = sf.format(new Date());
        return result;
    }
    public static String getNextMonthDayStr() {
        String result = "";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        result = sf.format(calendar.getTime());
        return result;
    }
    public static String getAfterDayStr() {
        String result = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        SimpleDateFormat sf1 = new SimpleDateFormat("dd");
        result = sf1.format(calendar.getTime());
        return result;
    }

    public static String getNowDateMonthStr() {
        String result = "";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
        result = sf.format(new Date());
        return result;
    }

    public static String getNowMonthStr() {
        String result = "";
        SimpleDateFormat sf = new SimpleDateFormat("M");
        result = sf.format(new Date());
        return result;
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
    public static String dateFormatToStr(Date date) {
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
        return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime()) + "000000";
    }

    /**
     * getTimeByDayStr:(这里用一句话描述这个方法的作用)获取第N周一日期字符串
     *
     * @param day
     * @return    设定文件
     * @return String    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static String getMondayStr(int weekNum) {

        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.add(Calendar.WEEK_OF_MONTH, weekNum);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }

    /**
     * getLastSundayStr:(这里用一句话描述这个方法的作用)获取N周日日期字符串
     *
     * @return    设定文件
     * @return String    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static String getSundayStr(int weekNum) {

        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.add(Calendar.WEEK_OF_MONTH, weekNum);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }

    /**
     * @return
     */
    public static String getDateStrByDay(int day) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        calendar.add(Calendar.DATE, day);
        return sf.format(calendar.getTime());
    }

    public static String getDateStrByMonth(int month, int day) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DATE, day);
        return sf.format(calendar.getTime());
    }

    /**
     * 
     * getMondayByTimeStr:(这里用一句话描述这个方法的作用)返回传入日期的那周周一日期
     *
     * @param dateStr
     * @return    设定文件
     * @return String    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public static String getMondayByTimeStr(String dateStr) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = sf.parse(dateStr);
            calendar.setTime(date);
            while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
            }
            return strFormatToStr(sf.format(calendar.getTime()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据传入的年月日数据，返回对应格式的日期字符串
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static String getDateStrByInput(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.set(year, month, date);
        return sf.format(calendar.getTime());
    }

    /**
     * 根据传入的日期和增加的月份数据，返回在传入日期加上增加月份后的日期字符串
     * @param dateStr
     * @param month
     * @return
     */
    public static String getDateStrByStrAdd(String dateStr, int month) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, month);
            return sf.format(calendar.getTime());

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String strFormatToStr(String date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTemp = new Date();
        try {
            dateTemp = sf.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sf1.format(dateTemp);

    }

    public static String getYesterdayDateStr() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, -1);
        return sf.format(calendar.getTime());

    }
    
    public static String getMonDateStr(String dateStr) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sf.parse(dateStr));
            while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY ) {
                calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
            }
            
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 7);
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sf.format(calendar.getTime());
    }
    
    public static String getSunDateStr(String dateStr) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sf.parse(dateStr));
            while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY ) {
                calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
            }
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sf.format(calendar.getTime());
    }
    public static String getDateStrYYYYMMDD(Date date) {
        String result = "";
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        result = sf.format(date);
        return result;
    }
}
