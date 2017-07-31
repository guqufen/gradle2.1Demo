package net.fnsco.withhold.service.trade.entity;

import java.math.BigDecimal;
import java.util.Date;

public class WithholdInfoDO {

	/**
	 * 
	 */
	private Integer id;

	/**
	 * 姓名
	 */
	private String userName;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 证件类型01：身份证02：军官证03：护照04：回乡证05：台胞证06：警官证07：士兵证99：其它证件
	 * 
	 */
	private String certifType;

	/**
	 * 证件号码
	 */
	private String certifyId;

	/**
	 * 扣款日
	 */
	private String debitDay;

	/**
	 * 扣款金额/次
	 */
	private BigDecimal amount;

	/**
	 * 扣款总额
	 */
	private BigDecimal amountTotal;

	/**
	 * 银行卡号
	 */
	private String bankCard;

	/**
	 * 状态0终止1进行2已完成
	 */
	private Integer status;

	/**
	 * 最后提交人
	 */
	private Integer modifyUserId;

	/**
	 * 最后提交时间
	 */
	private Date modifyTime;

	/**
	 * 扣款次数
	 */
	private Integer total;

	/**
	 * 开户支行名
	 */
	private String subBankName;

	/**
	 * 爱农银行编号
	 */
	private String anBankId;

	/**
	 * 账户类型00：对公 01：对私
	 */
	private String accountType;

	/**
	 * 账号类型：01：借记卡03：存折04：公司账号
	 */
	private String accType;
	/**
	 * 开户行行号
	 */
	private String openBankNum;
	/**
	 * 本月扣款失败次数
	 */
	private Integer failTotal;
	/**
	 * 开始日期
	 */
	private String startDate;
	/**
	 * 结束日期
	 */
	private String endDate;
	/**
	 * 扣款总额
	 */
	private BigDecimal allTotalAmt;

	/**
	 * 待扣金额
	 */
	private BigDecimal payLeftAmt;

	/**
	 * 提交人姓名
	 */
	private String modifyUserName;

	public BigDecimal getAllTotalAmt() {
		allTotalAmt = amount.multiply(new BigDecimal(total));
		return allTotalAmt;
	}

	public void setAllTotalAmt(BigDecimal allTotalAmt) {
		this.allTotalAmt = allTotalAmt;
	}

	public BigDecimal getPayLeftAmt() {
		payLeftAmt = allTotalAmt.subtract(amountTotal);
		return payLeftAmt;
	}

	public void setPayLeftAmt(BigDecimal payLeftAmt) {
		this.payLeftAmt = payLeftAmt;
	}

	public String getModifyUserName() {
		return modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	/**
	 * startDate
	 *
	 * @return the startDate
	 * @since CodingExample Ver 1.0
	 */

	public String getStartDate() {
		return startDate;
	}

	/**
	 * startDate
	 *
	 * @param startDate
	 *            the startDate to set
	 * @since CodingExample Ver 1.0
	 */

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * endDate
	 *
	 * @return the endDate
	 * @since CodingExample Ver 1.0
	 */

	public String getEndDate() {
		return endDate;
	}

	/**
	 * endDate
	 *
	 * @param endDate
	 *            the endDate to set
	 * @since CodingExample Ver 1.0
	 */

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * failTotal
	 *
	 * @return the failTotal
	 * @since CodingExample Ver 1.0
	 */

	public Integer getFailTotal() {
		return failTotal;
	}

	/**
	 * failTotal
	 *
	 * @param failTotal
	 *            the failTotal to set
	 * @since CodingExample Ver 1.0
	 */

	public void setFailTotal(Integer failTotal) {
		this.failTotal = failTotal;
	}

	public String getOpenBankNum() {
		return openBankNum;
	}

	public void setOpenBankNum(String openBankNum) {
		this.openBankNum = openBankNum;
	}

	/**
	 * accType
	 *
	 * @return the accType
	 * @since CodingExample Ver 1.0
	 */

	public String getAccType() {
		return accType;
	}

	/**
	 * accType
	 *
	 * @param accType
	 *            the accType to set
	 * @since CodingExample Ver 1.0
	 */

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCertifType() {
		return certifType;
	}

	public void setCertifType(String certifType) {
		this.certifType = certifType;
	}

	public String getCertifyId() {
		return certifyId;
	}

	public void setCertifyId(String certifyId) {
		this.certifyId = certifyId;
	}

	public String getDebitDay() {
		return debitDay;
	}

	public void setDebitDay(String debitDay) {
		this.debitDay = debitDay;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Integer modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getSubBankName() {
		return subBankName;
	}

	public void setSubBankName(String subBankName) {
		this.subBankName = subBankName;
	}

	public String getAnBankId() {
		return anBankId;
	}

	public void setAnBankId(String anBankId) {
		this.anBankId = anBankId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", userName=" + userName + ", mobile=" + mobile + ", certifType=" + certifType
				+ ", certifyId=" + certifyId + ", debitDay=" + debitDay + ", amount=" + amount + ", amountTotal="
				+ amountTotal + ", bankCard=" + bankCard + ", status=" + status + ", modifyUserId=" + modifyUserId
				+ ", modifyTime=" + modifyTime + ", total=" + total + ", subBankName=" + subBankName + ", anBankId="
				+ anBankId + ", accountType=" + accountType + "]";
	}
}