package net.fnsco.car.customer.entity;

import java.util.Date;

public class CustomerDO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 顾客名称
     */
    private String name;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 创建时间
     */
    private Date createTime;



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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", name="+ name + ", mobile="+ mobile + ", createTime="+ createTime + "]";
    }
}