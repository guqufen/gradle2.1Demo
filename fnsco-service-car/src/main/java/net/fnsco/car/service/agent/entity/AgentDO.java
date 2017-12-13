package net.fnsco.car.service.agent.entity;


public class AgentDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 代理商名称
     */
    private String name;

    /**
     * 代理商类型 1普通代理商 2特约代理商 3金牌代理商
     */
    private Integer type;

    /**
     * 省ID
     */
    private Integer provinceid;

    /**
     * 省
     */
    private String provincename;

    /**
     * 市id
     */
    private Integer cityid;

    /**
     * 市
     */
    private String cityname;

    /**
     * 区id
     */
    private Integer areaid;

    /**
     * 区
     */
    private String areaname;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 推荐码
     */
    private Integer suggestCode;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(Integer provinceid) {
        this.provinceid = provinceid;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }

    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSuggestCode() {
        return suggestCode;
    }

    public void setSuggestCode(Integer suggestCode) {
        this.suggestCode = suggestCode;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", name="+ name + ", type="+ type + ", provinceid="+ provinceid + ", provincename="+ provincename + ", cityid="+ cityid + ", cityname="+ cityname + ", areaid="+ areaid + ", areaname="+ areaname + ", address="+ address + ", suggestCode="+ suggestCode + "]";
    }
}