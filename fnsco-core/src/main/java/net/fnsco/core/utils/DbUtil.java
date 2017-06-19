package net.fnsco.core.utils;

import java.security.MessageDigest;
import java.util.UUID;

import com.google.common.base.Strings;

public class DbUtil {
	public static String getUuid() {
		UUID uuid = UUID.randomUUID();
		return MD5(uuid.toString());
		// String uuidStr = uuid.toString();
		// uuidStr = uuidStr.toUpperCase();
		// // 替换 -
		// uuidStr = uuidStr.replaceAll("-", "");
		// return uuidStr;
	}

	public final static String MD5(String arg) {
		if (Strings.isNullOrEmpty(arg)) {
		    arg="";
		}
		arg += "1.断断续续的努力~=白努力。";
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
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
}
