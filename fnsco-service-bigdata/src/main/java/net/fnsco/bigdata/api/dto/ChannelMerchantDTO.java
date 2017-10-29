/**
 * 
 */
package net.fnsco.bigdata.api.dto;

import java.util.Date;

import net.fnsco.core.base.DTO;

/**
 * @author tangliang
 *
 */
public class ChannelMerchantDTO extends DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6080720226990391996L;
	
	/**
	 * 渠道商户名称
	 */
	private String merName;
	
	/**
	 * 渠道商户号
	 */
	private String channelMerId;
	
	/**
	 * 渠道类型
	 */
	private String channelType;
	
	/**
	 * 设备数量
	 */
	private Integer posNums;
	
	/**
	 * 增加时间
	 */
	private Date createTime;
	
	/**
	 * 状态
	 */
	private String status;

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public String getChannelMerId() {
		return channelMerId;
	}

	public void setChannelMerId(String channelMerId) {
		this.channelMerId = channelMerId;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public Integer getPosNums() {
		return posNums;
	}

	public void setPosNums(Integer posNums) {
		this.posNums = posNums;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
