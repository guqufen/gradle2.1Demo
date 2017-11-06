package net.fnsco.order.service.domain;

import java.util.Date;

import net.fnsco.core.base.DTO;

/**
 * 对应库表：i_integral_rule积分规则表
 * 
 * @author Administrator
 *
 */
public class IntegralRule extends DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;// 主键

	private String code;// 规则代码

	private String name;// 规则名称

	private String description;// 规则描述

	private Integer integral;// 无脚本积分
	
	private Boolean plusFlag;//积分正负号标识

	private Integer status;// 状态：0-无效；1-有效

	private Integer isScript;// 是否有脚本0-无；1-有

	private Date createTime;// 创建时间

	public Boolean getPlusFlag() {
		return plusFlag;
	}

	public void setPlusFlag(Boolean plusFlag) {
		this.plusFlag = plusFlag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		if(integral > 0){
			plusFlag = true;
		}else{
			plusFlag = false;
		}
		this.integral = integral;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsScript() {
		return isScript;
	}

	public void setIsScript(Integer isScript) {
		this.isScript = isScript;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
