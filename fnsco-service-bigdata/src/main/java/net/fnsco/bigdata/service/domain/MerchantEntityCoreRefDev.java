package net.fnsco.bigdata.service.domain;

import java.util.Date;

/**
 * @desc  店铺跟商户关系表
 * @author   tangliang
 * @since    Ver 1.1
 * @Date	 2017年10月26日 下午2:55:53
 *
 */
public class MerchantEntityCoreRefDev {
    private Integer id;

    private String entityInnerCode;

    private String innerCode;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEntityInnerCode() {
        return entityInnerCode;
    }

    public void setEntityInnerCode(String entityInnerCode) {
        this.entityInnerCode = entityInnerCode == null ? null : entityInnerCode.trim();
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode == null ? null : innerCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}