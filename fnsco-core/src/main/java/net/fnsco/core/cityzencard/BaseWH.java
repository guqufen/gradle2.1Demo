package net.fnsco.core.cityzencard;

import java.io.Serializable;

/**
 * @desc
 * @author tangliang
 * @version 0.0.1-SNAPSHOT
 * @since Ver 1.1
 * @Date 2018年2月27日 上午10:31:09
 */
public class BaseWH implements Serializable {
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = 4440838134186387636L;
	private String version;
	private String transCode;
	private String reqSeq;
	private String merCode;
	private String chainNo;
	private String sign;

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTransCode() {
		return this.transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getReqSeq() {
		return this.reqSeq;
	}

	public void setReqSeq(String reqSeq) {
		this.reqSeq = reqSeq;
	}

	public String getMerCode() {
		return this.merCode;
	}

	public void setMerCode(String merCode) {
		this.merCode = merCode;
	}

	public String getChainNo() {
		return this.chainNo;
	}

	public void setChainNo(String chainNo) {
		this.chainNo = chainNo;
	}

	public String getSign() {
		return this.sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}