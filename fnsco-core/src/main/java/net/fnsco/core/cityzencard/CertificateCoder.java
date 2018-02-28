package net.fnsco.core.cityzencard;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import org.apache.commons.codec.binary.Base64;

/**
 * @desc
 * @author tangliang
 * @version 0.0.1-SNAPSHOT
 * @since Ver 1.1
 * @Date 2018年2月27日 上午10:42:02
 */

public class CertificateCoder {
	public static final String CERT_TYPE = "X.509";

	public static String sign(String sign, String pfxPath, String pfxPwd) {
		try {
			X509Certificate x509 = (X509Certificate) getCertformPfx(pfxPath, pfxPwd);

			Signature sa = Signature.getInstance(x509.getSigAlgName());

			PrivateKey privateKey = getPvkformPfx(pfxPath, pfxPwd);
			sa.initSign(privateKey);
			sa.update(sign.getBytes("GBK"));
			return Base64.encodeBase64String(sa.sign());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean verify(String data, String sign, String pfxPath, String pfxPwd) {
		X509Certificate x509 = (X509Certificate) getCertformPfx(pfxPath, pfxPwd);
		try {
			Signature sa = Signature.getInstance(x509.getSigAlgName());
			sa.initVerify(x509);
			sa.update(data.getBytes("GBK"));
			return sa.verify(Base64.decodeBase64(sign.getBytes("GBK")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static String Byte2String(byte[] b) {
		String hs = "";
		String stmp = "";

		for (int n = 0; n < b.length; ++n) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}

		return hs.toUpperCase();
	}

	public static byte[] StringToByte(int number) {
		int temp = number;
		byte[] b = new byte[4];
		for (int i = b.length - 1; i > -1; --i) {
			b[i] = new Integer(temp & 0xFF).byteValue();
			temp >>= 8;
		}
		return b;
	}

	private static PrivateKey getPvkformPfx(String pfxPath, String pfxPwd) {
		try {
			KeyStore ks = KeyStore.getInstance("PKCS12");
			FileInputStream fis = new FileInputStream(pfxPath);

			char[] nPassword = null;
			if ((pfxPwd == null) || (pfxPwd.trim().equals(""))) {
				nPassword = null;
			} else
				nPassword = pfxPwd.toCharArray();

			ks.load(fis, nPassword);
			fis.close();

			Enumeration enumas = ks.aliases();
			String keyAlias = null;
			if (enumas.hasMoreElements()) {
				keyAlias = (String) enumas.nextElement();
			}

			PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
			return prikey;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Certificate getCertformPfx(String pfxPath, String pfxPwd) {
		try {
			KeyStore ks = KeyStore.getInstance("PKCS12");
			FileInputStream fis = new FileInputStream(pfxPath);

			char[] nPassword = null;
			if ((pfxPwd == null) || (pfxPwd.trim().equals(""))) {
				nPassword = null;
			} else
				nPassword = pfxPwd.toCharArray();

			ks.load(fis, nPassword);
			fis.close();

			Enumeration enumas = ks.aliases();
			String keyAlias = null;
			if (enumas.hasMoreElements()) {
				keyAlias = (String) enumas.nextElement();
			}

			Certificate cert = ks.getCertificate(keyAlias);
			return cert;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
