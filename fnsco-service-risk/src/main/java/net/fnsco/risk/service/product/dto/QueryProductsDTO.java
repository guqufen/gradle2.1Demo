package net.fnsco.risk.service.product.dto;

import net.fnsco.core.base.DTO;

public class QueryProductsDTO extends DTO{

    /**
     * 代理商id
     */
    private Integer agentId;

    /**
     * 状态
     */
    private String status;

	/**
	 * @return the agentId
	 */
	public Integer getAgentId() {
		return agentId;
	}

	/**
	 * @param agentId the agentId to set
	 */
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}