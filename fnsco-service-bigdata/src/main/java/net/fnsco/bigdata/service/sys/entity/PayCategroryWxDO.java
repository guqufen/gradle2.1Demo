package net.fnsco.bigdata.service.sys.entity;


public class PayCategroryWxDO {

    /**
     * 商户性质
     */
    private Integer id;

    /**
     * 
     */
    private String etpsAttr;

    /**
     * 
     */
    private Integer groupId;

    /**
     * 
     */
    private String groupName;

    /**
     * 
     */
    private Integer categroryId;

    /**
     * 
     */
    private String categroryName;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEtpsAttr() {
        return etpsAttr;
    }

    public void setEtpsAttr(String etpsAttr) {
        this.etpsAttr = etpsAttr;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getCategroryId() {
        return categroryId;
    }

    public void setCategroryId(Integer categroryId) {
        this.categroryId = categroryId;
    }

    public String getCategroryName() {
        return categroryName;
    }

    public void setCategroryName(String categroryName) {
        this.categroryName = categroryName;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", etpsAttr="+ etpsAttr + ", groupId="+ groupId + ", groupName="+ groupName + ", categroryId="+ categroryId + ", categroryName="+ categroryName + "]";
    }
}