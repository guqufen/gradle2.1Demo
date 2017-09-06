package net.fnsco.bigdata.api.dto;

import net.fnsco.core.base.DTO;

public class TradeDataListResultDTO extends DTO {
    private String amt;
    private String payType;
    private String createTime;
    private String termId;

    /**
     * amt
     *
     * @return  the amt
     * @since   CodingExample Ver 1.0
    */

    public String getAmt() {
        return amt;
    }

    /**
     * amt
     *
     * @param   amt    the amt to set
     * @since   CodingExample Ver 1.0
     */

    public void setAmt(String amt) {
        this.amt = amt;
    }

    /**
     * payType
     *
     * @return  the payType
     * @since   CodingExample Ver 1.0
    */

    public String getPayType() {
        return payType;
    }

    /**
     * payType
     *
     * @param   payType    the payType to set
     * @since   CodingExample Ver 1.0
     */

    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * createTime
     *
     * @return  the createTime
     * @since   CodingExample Ver 1.0
    */

    public String getCreateTime() {
        return createTime;
    }

    /**
     * createTime
     *
     * @param   createTime    the createTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * termId
     *
     * @return  the termId
     * @since   CodingExample Ver 1.0
    */

    public String getTermId() {
        return termId;
    }

    /**
     * termId
     *
     * @param   termId    the termId to set
     * @since   CodingExample Ver 1.0
     */

    public void setTermId(String termId) {
        this.termId = termId;
    }

}
