package net.fnsco.core.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Strings;

/**
 * 
		* <p>Title: 模板工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:48:04</p>
 */
public class StringUtil {
    public static String[] merCodes = { "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
                                        "Z" };

    public static String getMerCode() {
        StringBuffer result = new StringBuffer();
        Random random = new Random();// 定义随机类
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(merCodes.length - 1);
            result.append(merCodes[index]);
        }
        return result.toString();
    }

    /**
     * 
    		*@name 字符串变量替换
    		*@Description 相关说明 
    		*@Time 创建时间:2015年6月29日下午3:28:19
    		*@param paramMap 包含变量名称的字典
    		*@param template 变量名为"${name}"
     */
    public static String process(String template, Map<String, Object> paramMap) {
        if (paramMap == null || paramMap.isEmpty()) {
            return template;
        }

        //生成匹配模式的正则表达式 
        //	    String patternStr = "\\$\\{(" + StringUtils.join(paramMap.keySet(), "|") + ")\\}"; 
        String patternStr = "\\{(" + StringUtils.join(paramMap.keySet(), "|") + ")\\}";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(template);
        StringBuffer result = new StringBuffer();
        Object replaceValue = null;
        while (matcher.find()) {
            replaceValue = paramMap.get(matcher.group(1));
            if (replaceValue != null) {
                matcher.appendReplacement(result, replaceValue.toString());
            } else {
                matcher.appendReplacement(result, "");
            }
        }
        matcher.appendTail(result);

        return result.toString();
    }

 // @描述：是否是2003的excel，返回true是2003 
    public static boolean isExcel2003(String filePath)  {  
         return filePath.matches("^.+\\.(?i)(xls)$");  
     }  
   
    //@描述：是否是2007的excel，返回true是2007 
    public static boolean isExcel2007(String filePath)  {  
         return filePath.matches("^.+\\.(?i)(xlsx)$");  
     }  
    
    public static void main(String[] args) {
        //被替换关键字的的数据源 
        Map<String, Object> tokens = new HashMap<String, Object>();
        tokens.put("firstName", "bill");
        tokens.put("lastName", "gate");

        //匹配类似velocity规则的字符串 
        String template = "hello,this is ${firstName} ${lastName}";

        System.out.println(process(template, tokens));
    }
    public static String valueOf(Object obj){
        if(null ==obj){
            return "";
        }
        return String.valueOf(obj);
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
	public static String formatRMBNumber(String str) {
		if(Strings.isNullOrEmpty(str)) {
			return String.format("%.2f", 0);
		}else {
			return String.format("%.2f", new BigDecimal(str).divide(new BigDecimal(100)).doubleValue());
		}
	}
	
	/**
	 * 固定字符串长度，右补字串
	 * @param str：待修补字符串
	 * @param size：固定总大小长度
	 * @param padChar：修补的字串(右补)
	 * @return
	 */
	public static String formatFixLenRight(String str, int size, String padChar){
		if(str != null){
			if(str.length() <= size){
				return StringUtils.rightPad(str, size, padChar);
			}else{
				return StringUtils.rightPad(str.substring(0, size), size, padChar);
			}
		}
		return null;
	}
	
	/**
	 * 固定字符串长度，左补字串
	 * @param inte：待修补Integer
	 * @param size：固定总大小长度
	 * @param padChar:修补的字串，左补
	 * @return
	 */
	public static String formatFixLenLeft(Integer inte, int size, String padChar){
		if(inte >= 0){
			String str = inte.toString();
			if(str.length() <= size){
				return StringUtils.leftPad(str, size, padChar);
			}else{
				return StringUtils.leftPad(str.substring(0, size), size, padChar);
			}
		}
		return null;
	}
}
