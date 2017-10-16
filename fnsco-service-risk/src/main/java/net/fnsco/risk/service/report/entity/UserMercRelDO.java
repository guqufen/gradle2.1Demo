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
    private String innerCode;



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

	public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", agentId="+ agentId + ", innerCode="+ innerCode + "]";
    }
}