package net.fnsco.bigdata.api.dto;

import net.fnsco.core.base.DTO;

public class PermissionsDTO extends DTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;//编号
	
	private String vipLevel;//等级
	
	private String privName;//特权
	
	private Integer integral;//最低积分
	
	private String icoUrl;//图标地址
	
	private String icoUrlGray;//灰色图标地址
	
	private Integer status;//状态是否点亮1亮/0暗

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(String vipLevel) {
		this.vipLevel = vipLevel;
	}

	public String getPrivName() {
		return privName;
	}

	public void setPrivName(String privName) {
		this.privName = privName;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public String getIcoUrl() {
		return icoUrl;
	}

	public void setIcoUrl(String icoUrl) {
		this.icoUrl = icoUrl;
	}

	public String getIcoUrlGray() {
		return icoUrlGray;
	}

	public void setIcoUrlGray(String icoUrlGray) {
		this.icoUrlGray = icoUrlGray;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
