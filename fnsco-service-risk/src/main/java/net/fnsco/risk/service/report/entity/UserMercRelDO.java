package net.fnsco.risk.service.report.entity;


public class UserMercRelDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer webUserOuterId;

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

    public Integer getWebUserOuterId() {
        return webUserOuterId;
    }

    public void setWebUserOuterId(Integer webUserOuterId) {
        this.webUserOuterId = webUserOuterId;
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", webUserOuterId="+ webUserOuterId + ", innerCode="+ innerCode + "]";
    }
}