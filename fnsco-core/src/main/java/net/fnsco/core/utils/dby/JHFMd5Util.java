package net.fnsco.core.utils.dby;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JHFMd5Util {
    public static String byteArrayToHex(byte[] byteArray) {
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

        char[] resultCharArray = new char[byteArray.length * 2];

        int index = 0;
        byte[] arrayOfByte = byteArray;
        int j = byteArray.length;
        for (int i = 0; i < j; ++i) {
            byte b = arrayOfByte[i];
            resultCharArray[(index++)] = hexDigits[(b >>> 4 & 0xF)];
            resultCharArray[(index++)] = hexDigits[(b & 0xF)];
        }

        return new String(resultCharArray);
    }

    public static String encode32(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            byte[] inputByteArray = str.getBytes();

            messageDigest.update(inputByteArray);

            byte[] resultByteArray = messageDigest.digest();

            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static String encode16(String str) {
        return encode32(str).substring(8, 24).toString();
    }
}