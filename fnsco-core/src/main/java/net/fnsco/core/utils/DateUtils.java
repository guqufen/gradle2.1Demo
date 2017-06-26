package net.fnsco.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
