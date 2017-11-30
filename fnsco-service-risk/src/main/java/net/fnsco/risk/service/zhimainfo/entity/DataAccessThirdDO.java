package net.fnsco.risk.service.zhimainfo.entity;

import java.util.Date;

public class DataAccessThirdDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 类型0企业涉诉信息1企业涉诉详情2企业工商信息
     */
    private Integer type;

    /**
     * 对应数据主键信息
     */
    private String key;

    /**
     * 芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账。
     */
    private String bizNo;

    /**
     * 渠道0芝麻信用
     */
    private Integer channel;

    /**
     * 
     */
    private Date createTime;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", type="+ type + ", key="+ key + ", bizNo="+ bizNo + ", channel="+ channel + ", createTime="+ createTime + "]";
    }
}