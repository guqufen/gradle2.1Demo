package net.fnsco.bigdata.service.modules.helper;

import java.util.Random;

public class MerchantHelper {
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
}
