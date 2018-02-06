package net.fnsco.core.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.common.base.Strings;

/**
 * @desc 数字工具类
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月16日 下午5:16:57
 */

public class NumberUtil {
    
    public static final String TER_TITLE_NAME = "终端";
 // 将数字转化为大写    
    public static String numToUpper(int num) {    
        // String u[] = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};    
        String u[] = { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九" };    
        char[] str = String.valueOf(num).toCharArray();    
        String rstr = "";    
        for (int i = 0; i < str.length; i++) {    
            rstr = rstr + u[Integer.parseInt(str[i] + "")];    
        }    
        if(num == 10){
            rstr = "十";
        }else if(num == 11){
            rstr = "十一";
        }else if(num == 12){
            rstr = "十二";
        }
        return rstr;    
    }   
    
    /**
     * multiplication:(这里用一句话描述这个方法的作用)乘法运算
     * @param bds
     * @param bds1
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月30日 下午3:09:54
     * @return BigDecimal    DOM对象
     */
    public static BigDecimal multiplication(String bds,String bds1){
        BigDecimal bd1 = new BigDecimal(bds);
        BigDecimal bd2 = new BigDecimal(bds1);
        
        return bd1.multiply(bd2);
    }
    
    public static BigDecimal divide(String turnover, Integer orderNum) {
        BigDecimal bd1 = new BigDecimal(turnover);
        BigDecimal bd2 = new BigDecimal(orderNum);
        return bd1.divide(bd2, 2, BigDecimal.ROUND_HALF_UP);
    }
    
    public static BigDecimal subtract(String turnover, String orderNum) {
        if(Strings.isNullOrEmpty(turnover)||Strings.isNullOrEmpty(orderNum)){
            return new BigDecimal(0);
        }
        BigDecimal bd1 = new BigDecimal(turnover);
        BigDecimal bd2 = new BigDecimal(orderNum);
        return bd1.subtract(bd2).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    
    public static String format(BigDecimal bd,int scale) {
        DecimalFormat df1 = new DecimalFormat("0.00"); 
        return df1.format(bd.doubleValue());
        
    }
    
    /**
	 * formatYYYYMMDate:(获取这种格式的日期字符串yyyy-MM)
	 *
	 * @param  @param date
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @author tangliang
	 * @date   2017年12月7日 下午1:59:41
	 */
	public static  String formatYYYYMMDate(Date date) {
		if(null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
		return sdf.format(date);
	}
	
	/**
	 * formatRMBNumber:(改为保留两位小数 RMB显示)
	 *
	 * @param  @param str
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @author tangliang
	 * @date   2017年12月7日 下午6:09:41
	 */
	public static  String formatRMBNumber(BigDecimal str) {
		if(str == null) {
			return String.format("%.2f", 0);
		}else {
			return String.format("%.2f", str.divide(new BigDecimal(100)).doubleValue());
		}
	}
	
	public static String formatRMBNumber(String str) {
		if(str == null) {
			return String.format("%.2f", 0);
		}else {
			return String.format("%.2f", new BigDecimal(str).divide(new BigDecimal(100)).doubleValue());
		}
	}
	public static String addRMBNumber(String str,String str2) {
		if(Strings.isNullOrEmpty(str)) {
			str = "0";
		}
		if(Strings.isNullOrEmpty(str2)) {
			str2 = "0";
		}
		
		BigDecimal result  = new BigDecimal(str).add(new BigDecimal(str2));
		
		return String.format("%.2f", result.doubleValue());
	}
}
