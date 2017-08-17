package net.fnsco.core.utils;

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
    
}
