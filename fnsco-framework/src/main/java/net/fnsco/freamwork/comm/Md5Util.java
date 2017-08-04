package net.fnsco.freamwork.comm;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class Md5Util {

    private static Logger  logger = Logger.getLogger(Md5Util.class);

    private MessageDigest  md5;
    private MessageDigest  sh1;
    private static Md5Util util   = null;

    private Md5Util() {
        if (md5 == null) {
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                logger.error("Md5Util.getInstance NoSuchAlgorithmException", e);
            }
        }
        if (sh1 == null) {
            try {
                sh1 = MessageDigest.getInstance("SHA-1");
            } catch (NoSuchAlgorithmException e) {
                logger.error("Md5Util.getInstance NoSuchAlgorithmException", e);
            }
        }
    }

    public static Md5Util getInstance() {
        if (util == null) {
            util = new Md5Util();
        }
        return util;
    }

    public String md5(String str) {

        byte[] array = md5.digest(str.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    public String sha1(String decript) {
        sh1.update(decript.getBytes());
        byte messageDigest[] = sh1.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < messageDigest.length; i++) {
            String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexString.append(0);
            }
            hexString.append(shaHex);
        }
        return hexString.toString();
    }

    public static void main(String[] args) {
        String a = "jsapi_ticket=kgt8ON7yVITDhtdwci0qeRMfbY5dbP3E81RMkmUUSEIkEggc7hV7kD6BFdOnFPYJqyciGzMLQVStz65D_joBZw&noncestr=JBZom2b8Bx4D4i31PO0f&timestamp=1445231783&url=http://wxsc.zuixiandao.cn/llz/cmnt/weixin/orderWeixinPay.htm";
        System.out.println(Md5Util.getInstance().sha1(a));
    }

    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr) {

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }

    public final static String MD5(String s) {
        byte[] btInput = new byte[0];
        try {
            btInput = s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encode(btInput);
    }

    public static String encode(byte[] source) {
        String s = null;
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

        byte tmp[] = encodebyte(source);
        char str[] = new char[16 * 2];
        int k = 0;
        for (int i = 0; i < 16; i++) {
            byte byte0 = tmp[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        s = new String(str);

        return s;
    }

    public static byte[] encodebyte(byte[] source) {
        java.security.MessageDigest md = null;
        try {
            md = java.security.MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (md == null) {
            return null;
        }
        md.update(source);
        return md.digest();
    }
}
