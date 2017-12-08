package net.fnsco.car.service.city.entity;


public class DicCityDO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 上级地址id
     */
    private Integer supperId;

    /**
     * 级别
     */
    private Integer level;



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

    public Integer getSupperId() {
        return supperId;
    }

    public void setSupperId(Integer supperId) {
        this.supperId = supperId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", name="+ name + ", supperId="+ supperId + ", level="+ level + "]";
    }
}