package net.fnsco.core.cityzencard;

/**
 * @desc
 * @author tangliang
 * @version 0.0.1-SNAPSHOT
 * @since Ver 1.1
 * @Date 2018年2月27日 上午10:33:43
 */

public class WHP0002 extends BaseWH {
	private String merCustId;
	private String shortCardNo;
	private String bankId;
	private String verCode;

	public String getMerCustId() {
		return this.merCustId;
	}

	public void setMerCustId(String merCustId) {
		this.merCustId = merCustId;
	}

	public String getShortCardNo() {
		return this.shortCardNo;
	}

	public void setShortCardNo(String shortCardNo) {
		this.shortCardNo = shortCardNo;
	}

	public String getBankId() {
		return this.bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getVerCode() {
		return this.verCode;
	}

	public void setVerCode(String verCode) {
		this.verCode = verCode;
	}
}
