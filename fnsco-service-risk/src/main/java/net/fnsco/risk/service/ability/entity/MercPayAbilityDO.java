package net.fnsco.risk.service.ability.entity;

import java.math.BigDecimal;

public class MercPayAbilityDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 内部商户号
     */
    private String entityInnerCode;

    /**
     * 月份yyyyMM
     */
    private String month;

    /**
     * 还款能力(元)
     */
    private BigDecimal payAbility;



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
        this.entityInnerCode = entityInnerCode;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getPayAbility() {
        return payAbility;
    }

    public void setPayAbility(BigDecimal payAbility) {
        this.payAbility = payAbility;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", entityInnerCode="+ entityInnerCode + ", month="+ month + ", payAbility="+ payAbility + "]";
    }
}