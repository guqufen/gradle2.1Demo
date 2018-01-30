package net.fnsco.core.utils.dby;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Key;
import java.util.Date;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import net.fnsco.core.utils.DateUtils;

public class AESUtil {
    private static final String AESTYPE = "AES/ECB/PKCS5Padding";
    private static Logger       logger  = LoggerFactory.getLogger(AESUtil.class);

    public static String encode(String plainText, String keyStr) {
        String ret = "";
        byte[] encrypt = null;
        try {
            Key key = generateKey(keyStr);
            Cipher cipher = Cipher.getInstance(AESTYPE);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encrypt = cipher.doFinal(plainText.getBytes());
        } catch (Exception e) {
            logger.error("aes加密出错", e);
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
            Cipher cipher = Cipher.getInstance(AESTYPE);
            cipher.init(Cipher.DECRYPT_MODE, key);
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

    public static String getReqData(String innerCode, String orderNo, String rechangeAesKey) {
        String reqData = "";
        String transTime = DateUtils.dateFormat1ToStr(new Date());
        Map<String, String> result = Maps.newHashMap();
        result.put("innerCode", innerCode);
        result.put("orderNo", orderNo);
        result.put("transTime", transTime);
        String singDataStr = innerCode + orderNo + transTime;
        logger.error("签名前数据" + singDataStr);
        String singData = JHFMd5Util.encode32(singDataStr);
        result.put("singData", singData);
        String dateTemp = JSON.toJSONString(result);
        try {
            reqData = URLEncoder.encode(AESUtil.encode(dateTemp, rechangeAesKey), "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("生成分期付url时AES加密出错", e);
        }
        return reqData;
    }

    public static void main(String[] args) {

        //String keyStr = "UITN25LMUQC436IM";
        String keyStr = "a8bc3defec6026b469d8c1fa095b5b43";
        // String content = "{\"area_city\":\"杭州\",\"hear_money\":\"50万到100万\",\"other_phone\":\"12345678912\",\"cmobile\":\"13758154074\",\"clientflag\":\"locan_submit\",\"exCode\":\"crm_reg\"}";
        //String content = "{\"rspCode\":\"000000\",\"policyId\":1,\"rspMsg\":\"成功\",\"xb_reqreflex\":\"MsgAppPolicyInfo\"}";
        //String content = "{\"innerCode\":\"110319624699094\",\"orderNo\":\"2018012211370033\",\"payAmount\":\"100\",\"periodNum\":\"12\",\"singData\":\"88B02EFF5C24E6AC5E84955EF646A481\",\"transTime\":\"20180122113700\"}";
        String content = "{\"innerCode\":\"110319624699094\",\"orderNo\":\"2018012317180018\",\"paymentAmount\":\"656.7\",\"periodNum\":\"6\",\"singData\":\"62C1B2EF93BE9D7A8EE64EABEC6EB381\",\"transTime\":\"20180123171800\"}";
        System.out.println("加密前：" + content);
        String xx = encode(content, keyStr);
        System.out.println("加密：" + xx);
        // String sss = "H7hpIdakzJm0kkAtLs6qopqb7u8cpdPMKNMtR82JL4O%2Fn1gblgI1giWGnUnLFnf34c7WHMxhVOonI4vT3NPXEVr5uYyu%2Bsbjun7pNV%2BLlIMVvFkLHx0TaEA9wZ40lWZJW1r7dlXZxQf6JyvuOt%2FUSw5sPX7KMESLmj%2BU81obgZp24znjkbZaZQV5hFpS8D4l9sq2cSzDOFzzh%2FkUf8sRYTCyCAHJwanLuphLMUZ62L4bE6BEaDUntursj1JNdn%2Fb%2BZwXZtDzCXyBta0WOAHEIu1MLVBZaNOxHV7396sOqtATSnPckMs2VZdWhB04%2BLV5oRF5OFaae5dOIH3Atjf5azHAL3XQJWwvpSCMhDuWu%2B513jRcRBisDv9AFIfOEQ%2FWX6LjMoSqNPf0%2B0EHz5EHVg%3D%3D";
        String decString = decode("n3ap/ygk879UR9LwXXJxuLVhtV+JWEc7S6M2GZ9lGioaQGdmBZGbnuBD2+lVH6UnYg4HeadlhIWHI6ktATeU5KuYUVXs8gLXwMlI/ukOJlCP+/evCMKdxXdJRAP3RLdoCdq9UHq+Yo7ZIGphaJPbnR1OMkZbG6+IscmWso04w/71b4dzuqoNO/sq6VuMaS25VvrYY8UCDwCkrCsM9lQ9d2vGoS8vaxXU2WkJovDKFwY=", keyStr);

        System.out.println("解密：" + decString);

    }

}
