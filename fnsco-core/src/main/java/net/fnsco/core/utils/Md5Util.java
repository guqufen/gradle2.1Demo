package net.fnsco.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class Md5Util {

	private static Logger logger = Logger.getLogger(Md5Util.class);

	private MessageDigest md5;
	private MessageDigest sh1;
	private static Md5Util util = null;

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

}
