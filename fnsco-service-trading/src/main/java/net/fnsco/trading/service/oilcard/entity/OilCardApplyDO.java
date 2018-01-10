package net.fnsco.trading.service.oilcard.entity;

import java.util.Date;

public class OilCardApplyDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 内部商户号 15位
     */
    private String innerCode;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 手机号
     */
    private String name;

    /**
     * 创建日期
     */
    private Date createTime;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", innerCode="+ innerCode + ", mobile="+ mobile + ", name="+ name + ", createTime="+ createTime + "]";
    }
}