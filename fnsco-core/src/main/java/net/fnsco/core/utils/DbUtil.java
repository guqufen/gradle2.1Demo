package net.fnsco.core.utils;

import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;

import com.google.common.base.Strings;

public class DbUtil {
    public static String getUuid() {
        UUID uuid = UUID.randomUUID();
        return MD5S(uuid.toString());
        // String uuidStr = uuid.toString();
        // uuidStr = uuidStr.toUpperCase();
        // // 替换 -
        // uuidStr = uuidStr.replaceAll("-", "");
        // return uuidStr;
    }

    public static String getRandomStr(int strLength) {
        Random rm = new Random();
        // 获得随机数  
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);
        // 将获得的获得随机数转化为字符串  
        String fixLenthString = String.valueOf(pross);
        // 返回固定的长度的随机数  
        return fixLenthString.substring(1, strLength + 1);
    }

    public final static String MD5S(String arg) {
        if (Strings.isNullOrEmpty(arg)) {
            arg = "";
        }
        arg += "1.断断续续的努力~=白努力。";
        return MD5(arg);
    }

    public final static String MD5(String arg) {
        if (Strings.isNullOrEmpty(arg)) {
            arg = "";
        }
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte[] strTemp = arg.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {

            return null;
        }
    }
    public static void main(String[] args){
        System.out.println(UUID.randomUUID());
    }
}
