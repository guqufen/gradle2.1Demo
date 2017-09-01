package net.fnsco.core.utils;

import java.math.BigDecimal;

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
    
}
