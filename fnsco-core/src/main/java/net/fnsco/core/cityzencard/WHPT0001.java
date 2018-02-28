package net.fnsco.core.cityzencard;

/**
 * @desc
 * @author tangliang
 * @version 0.0.1-SNAPSHOT
 * @since Ver 1.1
 * @Date 2018年2月27日 上午10:36:05
 */

public class WHPT0001 extends BaseWH {
	private String merCustId;
	private String name;
	private String certType;
	private String certNo;
	private String cardType;
	private String cardNo;
	private String validDate;
	private String cvv2;

	public String getMerCustId() {
		return this.merCustId;
	}

	public void setMerCustId(String merCustId) {
		this.merCustId = merCustId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCertType() {
		return this.certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return this.certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getValidDate() {
		return this.validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getCvv2() {
		return this.cvv2;
	}

	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}
}