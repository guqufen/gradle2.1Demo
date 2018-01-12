package net.fnsco.trading.service.withdraw.dto;

import java.math.BigDecimal;
import java.util.Date;

import net.fnsco.core.base.DTO;

public class TradeWithdrawDTO extends DTO{

    private Integer    id;
    /**
     * 订单id
     */
    private String     orderNo;
    /**
     * 用户名
     */
    private String     userName;
    /**
     * 手机号
     */
    private String     mobile;
    /**
     * 提现金额
     */
    private BigDecimal     amount;
    /**
     * 提现卡号
     */
    private String     bankAccountNo;
    /**
     * 提现时间
     */
    private Date       createTime;
    /**
     * 状态 0未执行1执行中2失败3成功
     */
    private Integer    status;
    /**
     * 交易子类型 10充值收入/11新人红包收入20提现/21预约提现//22话费充值23流量充值24火车票购买25提现手续费26收入撤销',
     */
    private Integer    tradeSubType;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * @return the bankAccountNo
	 */
	public String getBankAccountNo() {
		return bankAccountNo;
	}
	/**
	 * @param bankAccountNo the bankAccountNo to set
	 */
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return the tradeSubType
	 */
	public Integer getTradeSubType() {
		return tradeSubType;
	}
	/**
	 * @param tradeSubType the tradeSubType to set
	 */
	public void setTradeSubType(Integer tradeSubType) {
		this.tradeSubType = tradeSubType;
	}
    
}