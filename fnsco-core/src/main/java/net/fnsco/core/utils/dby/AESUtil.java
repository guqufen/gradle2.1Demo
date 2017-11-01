package net.fnsco.core.utils.dby;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
    private static final String AESTYPE = "AES/ECB/PKCS5Padding";

    public static String encode(String plainText, String keyStr) {
        String ret = "";
        byte[] encrypt = null;
        try {
            Key key = generateKey(keyStr);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(1, key);
            encrypt = cipher.doFinal(plainText.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ret = new String(Base64Util.encode(encrypt));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static String decode(String decryptData, String keyStr) {
        String ret = "";
        byte[] decryptResult = null;
        try {
            byte[] decyptBody = Base64Util.decodeToByte(decryptData);
            Key key = generateKey(keyStr);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(2, key);
            decryptResult = cipher.doFinal(decyptBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ret = new String(decryptResult).trim();
        return ret;
    }

    private static Key generateKey(String key) throws Exception {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            return keySpec;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) {
        String keyStr = "UITN25LMUQC436IM";

        String content = "{\"rspCode\":\"000000\",\"policyId\":1,\"rspMsg\":\"成功\",\"xb_reqreflex\":\"MsgAppPolicyInfo\"}";
        System.out.println("加密前：" + content);
        String xx = encode(content, keyStr);
        System.out.println("加密：" + xx);

        String decString = decode("EdWTGfwobxSfU96Sge2NHc1l0OKhw2tcRgXXeT9f+MhihiB2YcPZMZzQhOKSKg027w/ER5E0RdFGPnKOtaWVokJ5uyIZrCRUNaIzh+U5Q2ok6qWFakIUnk1phAuxAcAp", keyStr);

        System.out.println("解密：" + decString);
    }
}