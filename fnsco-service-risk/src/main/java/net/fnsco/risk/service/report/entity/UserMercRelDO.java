package net.fnsco.risk.service.report.entity;

public class UserMercRelDO {

	/**
	 * 
	 */
	private Integer id;

	/**
	 * 
	 */
	private Integer agentId;

	/**
	 * 
	 */
//	private String innerCode;

	private String entityInnerCode;// 实体商户号

	public String getEntityInnerCode() {
		return entityInnerCode;
	}

	public void setEntityInnerCode(String entityInnerCode) {
		this.entityInnerCode = entityInnerCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", agentId=" + agentId + ", entityInnerCode=" + entityInnerCode + "]";
	}
}