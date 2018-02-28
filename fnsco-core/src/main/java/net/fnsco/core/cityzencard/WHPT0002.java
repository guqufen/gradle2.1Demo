package net.fnsco.core.cityzencard;

/**
 * @desc
 * @author tangliang
 * @version 0.0.1-SNAPSHOT
 * @since Ver 1.1
 * @Date 2018年2月27日 上午10:36:41
 */

public class WHPT0002 extends BaseWH {
	private String merCustId;
	private String serialNo;
	private String orderNo;
	private String shortCardNo;
	private String dateTime;
	private String amount;
	private String goods;
	private String subMerCode;
	private String subMerName;

	public String getMerCustId() {
		return this.merCustId;
	}

	public void setMerCustId(String merCustId) {
		this.merCustId = merCustId;
	}

	public String getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getShortCardNo() {
		return this.shortCardNo;
	}

	public void setShortCardNo(String shortCardNo) {
		this.shortCardNo = shortCardNo;
	}

	public String getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getGoods() {
		return this.goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public String getSubMerCode() {
		return this.subMerCode;
	}

	public void setSubMerCode(String subMerCode) {
		this.subMerCode = subMerCode;
	}

	public String getSubMerName() {
		return this.subMerName;
	}

	public void setSubMerName(String subMerName) {
		this.subMerName = subMerName;
	}
}