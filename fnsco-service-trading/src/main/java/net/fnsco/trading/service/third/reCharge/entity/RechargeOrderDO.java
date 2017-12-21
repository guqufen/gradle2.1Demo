package net.fnsco.trading.service.third.reCharge.entity;

import java.util.Date;

public class RechargeOrderDO {

	/**
	 * 
	 */
	private Integer id;

	/**
	 * app用户ID
	 */
	private String appUserId;

	/**
	 * 充值类型0-话费1-流量
	 */
	private String type;

	/**
	 * 订单ID
	 */
	private String orderNo;

	/**
	 * 渠道订单id
	 */
	private String payOrderNo;

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 充值名称
	 */
	private String name;

	/**
	 * 交易金额
	 */
	private String amt;

	/**
	 * 交易状态 0进行中1成功2失败
	 */
	private Integer status;

	/**
	 * 应答码
	 */
	private String respCode;

	/**
	 * 应答信息
	 */
	private String respMsg;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(String appUserId) {
		this.appUserId = appUserId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", appUserId=" + appUserId + ", type=" + type + ", orderNo=" + orderNo + ", payOrderNo="
				+ payOrderNo + ", mobile=" + mobile + ", name=" + name + ", amt=" + amt + ", status="
				+ status + ", respCode=" + respCode + ", respMsg=" + respMsg + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
}