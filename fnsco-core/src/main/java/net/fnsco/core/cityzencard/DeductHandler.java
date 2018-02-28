package net.fnsco.core.cityzencard;
import com.alibaba.fastjson.JSON;

/**
 * @desc 市民卡代扣业务
 * @author tangliang
 * @version 0.0.1-SNAPSHOT
 * @since Ver 1.1
 * @Date 2018年2月27日 上午10:29:54
 */
public class DeductHandler {
	
	public static String handleRequestMessage(Object obj, String pfxPath, String pfxPwd) {
		StringBuffer msgsignstr;
		String aftSignStr;
		String respStr = null;
		if ((obj == null) || (pfxPath == null) || (pfxPwd == null))
			return respStr;

		if (obj instanceof WHP0001) {
			WHP0001 r = (WHP0001) obj;
			msgsignstr = new StringBuffer();
			msgsignstr.append("version=").append((r.getVersion() == null) ? "" : r.getVersion());
			msgsignstr.append("&transCode=").append((r.getTransCode() == null) ? "" : r.getTransCode());
			msgsignstr.append("&reqSeq=").append((r.getReqSeq() == null) ? "" : r.getReqSeq());
			msgsignstr.append("&merCode=").append((r.getMerCode() == null) ? "" : r.getMerCode());
			msgsignstr.append("&chainNo=").append((r.getChainNo() == null) ? "" : r.getChainNo());

			msgsignstr.append("&merCustId=").append((r.getMerCustId() == null) ? "" : r.getMerCustId());
			msgsignstr.append("&name=").append((r.getName() == null) ? "" : r.getName());
			msgsignstr.append("&certType=").append((r.getCertType() == null) ? "" : r.getCertType());
			msgsignstr.append("&certNo=").append((r.getCertNo() == null) ? "" : r.getCertNo());
			msgsignstr.append("&phone=").append((r.getPhone() == null) ? "" : r.getPhone());
			msgsignstr.append("&cardType=").append((r.getCardType() == null) ? "" : r.getCardType());
			msgsignstr.append("&cardNo=").append((r.getCardNo() == null) ? "" : r.getCardNo());
			msgsignstr.append("&validDate=").append((r.getValidDate() == null) ? "" : r.getValidDate());
			msgsignstr.append("&cvv2=").append((r.getCvv2() == null) ? "" : r.getCvv2());

			aftSignStr = CertificateCoder.sign(msgsignstr.toString(), pfxPath, pfxPwd);
			r.setSign(aftSignStr);
			respStr = JSON.toJSONString(r);
		}
		if (obj instanceof WHP0002) {
			WHP0002 r = (WHP0002) obj;
			msgsignstr = new StringBuffer();
			msgsignstr.append("version=").append((r.getVersion() == null) ? "" : r.getVersion());
			msgsignstr.append("&transCode=").append((r.getTransCode() == null) ? "" : r.getTransCode());
			msgsignstr.append("&reqSeq=").append((r.getReqSeq() == null) ? "" : r.getReqSeq());
			msgsignstr.append("&merCode=").append((r.getMerCode() == null) ? "" : r.getMerCode());
			msgsignstr.append("&chainNo=").append((r.getChainNo() == null) ? "" : r.getChainNo());

			msgsignstr.append("&merCustId=").append((r.getMerCustId() == null) ? "" : r.getMerCustId());
			msgsignstr.append("&shortCardNo=").append((r.getShortCardNo() == null) ? "" : r.getShortCardNo());
			msgsignstr.append("&bankId=").append((r.getBankId() == null) ? "" : r.getBankId());
			msgsignstr.append("&verCode=").append((r.getVerCode() == null) ? "" : r.getVerCode());

			aftSignStr = CertificateCoder.sign(msgsignstr.toString(), pfxPath, pfxPwd);
			r.setSign(aftSignStr);
			respStr = JSON.toJSONString(r);
		}
		if (obj instanceof WHP0003) {
			WHP0003 r = (WHP0003) obj;
			msgsignstr = new StringBuffer();
			msgsignstr.append("version=").append((r.getVersion() == null) ? "" : r.getVersion());
			msgsignstr.append("&transCode=").append((r.getTransCode() == null) ? "" : r.getTransCode());
			msgsignstr.append("&reqSeq=").append((r.getReqSeq() == null) ? "" : r.getReqSeq());
			msgsignstr.append("&merCode=").append((r.getMerCode() == null) ? "" : r.getMerCode());
			msgsignstr.append("&chainNo=").append((r.getChainNo() == null) ? "" : r.getChainNo());

			msgsignstr.append("&merCustId=").append((r.getMerCustId() == null) ? "" : r.getMerCustId());
			msgsignstr.append("&serialNo=").append((r.getSerialNo() == null) ? "" : r.getSerialNo());
			msgsignstr.append("&orderNo=").append((r.getOrderNo() == null) ? "" : r.getOrderNo());
			msgsignstr.append("&shortCardNo=").append((r.getShortCardNo() == null) ? "" : r.getShortCardNo());
			msgsignstr.append("&dateTime=").append((r.getDateTime() == null) ? "" : r.getDateTime());
			msgsignstr.append("&amount=").append((r.getAmount() == null) ? "" : r.getAmount());
			msgsignstr.append("&goods=").append((r.getGoods() == null) ? "" : r.getGoods());

			aftSignStr = CertificateCoder.sign(msgsignstr.toString(), pfxPath, pfxPwd);
			r.setSign(aftSignStr);
			respStr = JSON.toJSONString(r);
		}
		if (obj instanceof WHP0004) {
			WHP0004 r = (WHP0004) obj;
			msgsignstr = new StringBuffer();
			msgsignstr.append("version=").append((r.getVersion() == null) ? "" : r.getVersion());
			msgsignstr.append("&transCode=").append((r.getTransCode() == null) ? "" : r.getTransCode());
			msgsignstr.append("&reqSeq=").append((r.getReqSeq() == null) ? "" : r.getReqSeq());
			msgsignstr.append("&merCode=").append((r.getMerCode() == null) ? "" : r.getMerCode());
			msgsignstr.append("&chainNo=").append((r.getChainNo() == null) ? "" : r.getChainNo());

			msgsignstr.append("&type=").append((r.getType() == null) ? "" : r.getType());
			msgsignstr.append("&serialNo=").append((r.getSerialNo() == null) ? "" : r.getSerialNo());
			msgsignstr.append("&orderNo=").append((r.getOrderNo() == null) ? "" : r.getOrderNo());

			aftSignStr = CertificateCoder.sign(msgsignstr.toString(), pfxPath, pfxPwd);
			r.setSign(aftSignStr);
			respStr = JSON.toJSONString(r);
		}
		if (obj instanceof WHP0005) {
			WHP0005 r = (WHP0005) obj;
			msgsignstr = new StringBuffer();
			msgsignstr.append("version=").append((r.getVersion() == null) ? "" : r.getVersion());
			msgsignstr.append("&transCode=").append((r.getTransCode() == null) ? "" : r.getTransCode());
			msgsignstr.append("&reqSeq=").append((r.getReqSeq() == null) ? "" : r.getReqSeq());
			msgsignstr.append("&merCode=").append((r.getMerCode() == null) ? "" : r.getMerCode());
			msgsignstr.append("&chainNo=").append((r.getChainNo() == null) ? "" : r.getChainNo());

			msgsignstr.append("&date=").append((r.getDate() == null) ? "" : r.getDate());

			aftSignStr = CertificateCoder.sign(msgsignstr.toString(), pfxPath, pfxPwd);
			r.setSign(aftSignStr);
			respStr = JSON.toJSONString(r);
		}
		if (obj instanceof WHPT0001) {
			WHPT0001 r = (WHPT0001) obj;
			msgsignstr = new StringBuffer();
			msgsignstr.append("version=").append((r.getVersion() == null) ? "" : r.getVersion());
			msgsignstr.append("&transCode=").append((r.getTransCode() == null) ? "" : r.getTransCode());
			msgsignstr.append("&reqSeq=").append((r.getReqSeq() == null) ? "" : r.getReqSeq());
			msgsignstr.append("&merCode=").append((r.getMerCode() == null) ? "" : r.getMerCode());
			msgsignstr.append("&chainNo=").append((r.getChainNo() == null) ? "" : r.getChainNo());

			msgsignstr.append("&merCustId=").append((r.getMerCustId() == null) ? "" : r.getMerCustId());
			msgsignstr.append("&name=").append((r.getName() == null) ? "" : r.getName());
			msgsignstr.append("&certType=").append((r.getCertType() == null) ? "" : r.getCertType());
			msgsignstr.append("&certNo=").append((r.getCertNo() == null) ? "" : r.getCertNo());
			msgsignstr.append("&cardType=").append((r.getCardType() == null) ? "" : r.getCardType());
			msgsignstr.append("&cardNo=").append((r.getCardNo() == null) ? "" : r.getCardNo());
			msgsignstr.append("&validDate=").append((r.getValidDate() == null) ? "" : r.getValidDate());
			msgsignstr.append("&cvv2=").append((r.getCvv2() == null) ? "" : r.getCvv2());

			aftSignStr = CertificateCoder.sign(msgsignstr.toString(), pfxPath, pfxPwd);
			r.setSign(aftSignStr);
			respStr = JSON.toJSONString(r);
		}
		if (obj instanceof WHPT0002) {
			WHPT0002 r = (WHPT0002) obj;
			msgsignstr = new StringBuffer();
			msgsignstr.append("version=").append((r.getVersion() == null) ? "" : r.getVersion());
			msgsignstr.append("&transCode=").append((r.getTransCode() == null) ? "" : r.getTransCode());
			msgsignstr.append("&reqSeq=").append((r.getReqSeq() == null) ? "" : r.getReqSeq());
			msgsignstr.append("&merCode=").append((r.getMerCode() == null) ? "" : r.getMerCode());
			msgsignstr.append("&chainNo=").append((r.getChainNo() == null) ? "" : r.getChainNo());

			msgsignstr.append("&merCustId=").append((r.getMerCustId() == null) ? "" : r.getMerCustId());
			msgsignstr.append("&serialNo=").append((r.getSerialNo() == null) ? "" : r.getSerialNo());
			msgsignstr.append("&orderNo=").append((r.getOrderNo() == null) ? "" : r.getOrderNo());
			msgsignstr.append("&shortCardNo=").append((r.getShortCardNo() == null) ? "" : r.getShortCardNo());
			msgsignstr.append("&dateTime=").append((r.getDateTime() == null) ? "" : r.getDateTime());
			msgsignstr.append("&amount=").append((r.getAmount() == null) ? "" : r.getAmount());
			msgsignstr.append("&goods=").append((r.getGoods() == null) ? "" : r.getGoods());
			msgsignstr.append("&subMerCode=").append((r.getSubMerCode() == null) ? "" : r.getSubMerCode());
			msgsignstr.append("&subMerName=").append((r.getSubMerName() == null) ? "" : r.getSubMerName());

			aftSignStr = CertificateCoder.sign(msgsignstr.toString(), pfxPath, pfxPwd);
			r.setSign(aftSignStr);
			respStr = JSON.toJSONString(r);
		}
		if (obj instanceof WHPT0003) {
			WHPT0003 r = (WHPT0003) obj;
			msgsignstr = new StringBuffer();
			msgsignstr.append("version=").append((r.getVersion() == null) ? "" : r.getVersion());
			msgsignstr.append("&transCode=").append((r.getTransCode() == null) ? "" : r.getTransCode());
			msgsignstr.append("&reqSeq=").append((r.getReqSeq() == null) ? "" : r.getReqSeq());
			msgsignstr.append("&merCode=").append((r.getMerCode() == null) ? "" : r.getMerCode());
			msgsignstr.append("&chainNo=").append((r.getChainNo() == null) ? "" : r.getChainNo());

			msgsignstr.append("&type=").append((r.getType() == null) ? "" : r.getType());
			msgsignstr.append("&serialNo=").append((r.getSerialNo() == null) ? "" : r.getSerialNo());
			msgsignstr.append("&orderNo=").append((r.getOrderNo() == null) ? "" : r.getOrderNo());

			aftSignStr = CertificateCoder.sign(msgsignstr.toString(), pfxPath, pfxPwd);
			r.setSign(aftSignStr);
			respStr = JSON.toJSONString(r);
		}
		if (obj instanceof WHPT0004) {
			WHPT0004 r = (WHPT0004) obj;
			msgsignstr = new StringBuffer();
			msgsignstr.append("version=").append((r.getVersion() == null) ? "" : r.getVersion());
			msgsignstr.append("&transCode=").append((r.getTransCode() == null) ? "" : r.getTransCode());
			msgsignstr.append("&reqSeq=").append((r.getReqSeq() == null) ? "" : r.getReqSeq());
			msgsignstr.append("&merCode=").append((r.getMerCode() == null) ? "" : r.getMerCode());
			msgsignstr.append("&chainNo=").append((r.getChainNo() == null) ? "" : r.getChainNo());

			msgsignstr.append("&date=").append((r.getDate() == null) ? "" : r.getDate());

			aftSignStr = CertificateCoder.sign(msgsignstr.toString(), pfxPath, pfxPwd);
			r.setSign(aftSignStr);
			respStr = JSON.toJSONString(r);
		}
		if (obj instanceof WHPTU0001) {
			WHPTU0001 r = (WHPTU0001) obj;
			msgsignstr = new StringBuffer();
			msgsignstr.append("version=").append((r.getVersion() == null) ? "" : r.getVersion());
			msgsignstr.append("&transCode=").append((r.getTransCode() == null) ? "" : r.getTransCode());
			msgsignstr.append("&reqSeq=").append((r.getReqSeq() == null) ? "" : r.getReqSeq());
			msgsignstr.append("&merCode=").append((r.getMerCode() == null) ? "" : r.getMerCode());
			msgsignstr.append("&chainNo=").append((r.getChainNo() == null) ? "" : r.getChainNo());

			msgsignstr.append("&serialNo=").append((r.getSerialNo() == null) ? "" : r.getSerialNo());
			msgsignstr.append("&orderNo=").append((r.getOrderNo() == null) ? "" : r.getOrderNo());
			msgsignstr.append("&merCustId=").append((r.getMerCustId() == null) ? "" : r.getMerCustId());
			msgsignstr.append("&name=").append((r.getName() == null) ? "" : r.getName());
			msgsignstr.append("&certType=").append((r.getCertType() == null) ? "" : r.getCertType());
			msgsignstr.append("&certNo=").append((r.getCertNo() == null) ? "" : r.getCertNo());
			msgsignstr.append("&cardType=").append((r.getCardType() == null) ? "" : r.getCardType());
			msgsignstr.append("&cardNo=").append((r.getCardNo() == null) ? "" : r.getCardNo());
			msgsignstr.append("&validDate=").append((r.getValidDate() == null) ? "" : r.getValidDate());
			msgsignstr.append("&cvv2=").append((r.getCvv2() == null) ? "" : r.getCvv2());
			msgsignstr.append("&dateTime=").append((r.getDateTime() == null) ? "" : r.getDateTime());
			msgsignstr.append("&amount=").append((r.getAmount() == null) ? "" : r.getAmount());
			msgsignstr.append("&goods=").append((r.getGoods() == null) ? "" : r.getGoods());
			msgsignstr.append("&subMerCode=").append((r.getSubMerCode() == null) ? "" : r.getSubMerCode());
			msgsignstr.append("&subMerName=").append((r.getSubMerName() == null) ? "" : r.getSubMerName());

			aftSignStr = CertificateCoder.sign(msgsignstr.toString(), pfxPath, pfxPwd);
			r.setSign(aftSignStr);
			respStr = JSON.toJSONString(r);
		}
		if (obj instanceof WHPTU0002) {
			WHPTU0002 r = (WHPTU0002) obj;
			msgsignstr = new StringBuffer();
			msgsignstr.append("version=").append((r.getVersion() == null) ? "" : r.getVersion());
			msgsignstr.append("&transCode=").append((r.getTransCode() == null) ? "" : r.getTransCode());
			msgsignstr.append("&reqSeq=").append((r.getReqSeq() == null) ? "" : r.getReqSeq());
			msgsignstr.append("&merCode=").append((r.getMerCode() == null) ? "" : r.getMerCode());
			msgsignstr.append("&chainNo=").append((r.getChainNo() == null) ? "" : r.getChainNo());

			msgsignstr.append("&type=").append((r.getType() == null) ? "" : r.getType());
			msgsignstr.append("&serialNo=").append((r.getSerialNo() == null) ? "" : r.getSerialNo());
			msgsignstr.append("&orderNo=").append((r.getOrderNo() == null) ? "" : r.getOrderNo());

			aftSignStr = CertificateCoder.sign(msgsignstr.toString(), pfxPath, pfxPwd);
			r.setSign(aftSignStr);
			respStr = JSON.toJSONString(r);
		}
		if (obj instanceof WHPTU0003) {
			WHPTU0003 r = (WHPTU0003) obj;
			msgsignstr = new StringBuffer();
			msgsignstr.append("version=").append((r.getVersion() == null) ? "" : r.getVersion());
			msgsignstr.append("&transCode=").append((r.getTransCode() == null) ? "" : r.getTransCode());
			msgsignstr.append("&reqSeq=").append((r.getReqSeq() == null) ? "" : r.getReqSeq());
			msgsignstr.append("&merCode=").append((r.getMerCode() == null) ? "" : r.getMerCode());
			msgsignstr.append("&chainNo=").append((r.getChainNo() == null) ? "" : r.getChainNo());

			msgsignstr.append("&date=").append((r.getDate() == null) ? "" : r.getDate());

			aftSignStr = CertificateCoder.sign(msgsignstr.toString(), pfxPath, pfxPwd);
			r.setSign(aftSignStr);
			respStr = JSON.toJSONString(r);
		}
		if (obj instanceof WHPU0001) {
			WHPU0001 r = (WHPU0001) obj;
			msgsignstr = new StringBuffer();
			msgsignstr.append("version=").append((r.getVersion() == null) ? "" : r.getVersion());
			msgsignstr.append("&transCode=").append((r.getTransCode() == null) ? "" : r.getTransCode());
			msgsignstr.append("&reqSeq=").append((r.getReqSeq() == null) ? "" : r.getReqSeq());
			msgsignstr.append("&merCode=").append((r.getMerCode() == null) ? "" : r.getMerCode());
			msgsignstr.append("&chainNo=").append((r.getChainNo() == null) ? "" : r.getChainNo());

			msgsignstr.append("&serialNo=").append((r.getSerialNo() == null) ? "" : r.getSerialNo());
			msgsignstr.append("&orderNo=").append((r.getOrderNo() == null) ? "" : r.getOrderNo());
			msgsignstr.append("&merCustId=").append((r.getMerCustId() == null) ? "" : r.getMerCustId());
			msgsignstr.append("&name=").append((r.getName() == null) ? "" : r.getName());
			msgsignstr.append("&certType=").append((r.getCertType() == null) ? "" : r.getCertType());
			msgsignstr.append("&certNo=").append((r.getCertNo() == null) ? "" : r.getCertNo());
			msgsignstr.append("&phone=").append((r.getPhone() == null) ? "" : r.getPhone());
			msgsignstr.append("&cardType=").append((r.getCardType() == null) ? "" : r.getCardType());
			msgsignstr.append("&cardNo=").append((r.getCardNo() == null) ? "" : r.getCardNo());
			msgsignstr.append("&validDate=").append((r.getValidDate() == null) ? "" : r.getValidDate());
			msgsignstr.append("&cvv2=").append((r.getCvv2() == null) ? "" : r.getCvv2());
			msgsignstr.append("&dateTime=").append((r.getDateTime() == null) ? "" : r.getDateTime());
			msgsignstr.append("&amount=").append((r.getAmount() == null) ? "" : r.getAmount());
			msgsignstr.append("&goods=").append((r.getGoods() == null) ? "" : r.getGoods());
			msgsignstr.append("&subMerCode=").append((r.getSubMerCode() == null) ? "" : r.getSubMerCode());
			msgsignstr.append("&subMerName=").append((r.getSubMerName() == null) ? "" : r.getSubMerName());

			aftSignStr = CertificateCoder.sign(msgsignstr.toString(), pfxPath, pfxPwd);
			r.setSign(aftSignStr);
			respStr = JSON.toJSONString(r);
		}
		if (obj instanceof WHPU0002) {
			WHPU0002 r = (WHPU0002) obj;
			msgsignstr = new StringBuffer();
			msgsignstr.append("version=").append((r.getVersion() == null) ? "" : r.getVersion());
			msgsignstr.append("&transCode=").append((r.getTransCode() == null) ? "" : r.getTransCode());
			msgsignstr.append("&reqSeq=").append((r.getReqSeq() == null) ? "" : r.getReqSeq());
			msgsignstr.append("&merCode=").append((r.getMerCode() == null) ? "" : r.getMerCode());
			msgsignstr.append("&chainNo=").append((r.getChainNo() == null) ? "" : r.getChainNo());

			msgsignstr.append("&type=").append((r.getType() == null) ? "" : r.getType());
			msgsignstr.append("&serialNo=").append((r.getSerialNo() == null) ? "" : r.getSerialNo());
			msgsignstr.append("&orderNo=").append((r.getOrderNo() == null) ? "" : r.getOrderNo());

			aftSignStr = CertificateCoder.sign(msgsignstr.toString(), pfxPath, pfxPwd);
			r.setSign(aftSignStr);
			respStr = JSON.toJSONString(r);
		}
		if (obj instanceof WHPU0003) {
			WHPU0003 r = (WHPU0003) obj;
			msgsignstr = new StringBuffer();
			msgsignstr.append("version=").append((r.getVersion() == null) ? "" : r.getVersion());
			msgsignstr.append("&transCode=").append((r.getTransCode() == null) ? "" : r.getTransCode());
			msgsignstr.append("&reqSeq=").append((r.getReqSeq() == null) ? "" : r.getReqSeq());
			msgsignstr.append("&merCode=").append((r.getMerCode() == null) ? "" : r.getMerCode());
			msgsignstr.append("&chainNo=").append((r.getChainNo() == null) ? "" : r.getChainNo());

			msgsignstr.append("&date=").append((r.getDate() == null) ? "" : r.getDate());

			aftSignStr = CertificateCoder.sign(msgsignstr.toString(), pfxPath, pfxPwd);
			r.setSign(aftSignStr);
			respStr = JSON.toJSONString(r);
		}
		return respStr;
	}
}
