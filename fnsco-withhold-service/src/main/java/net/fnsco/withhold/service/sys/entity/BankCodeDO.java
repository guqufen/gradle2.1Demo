package net.fnsco.withhold.service.sys.entity;


public class BankCodeDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 机构代码
     */
    private String code;

    /**
     * 发卡行名称
     */
    private String bankName;

    /**
     * 卡名
     */
    private String cardName;

    /**
     * 主账号取值
     */
    private String cardTrimValue;

    /**
     * 卡种(DC:借记卡、CC：贷记卡、 SCC：准贷记卡、 PC：预付费卡、unK：其他)
     */
    private String type;

    /**
     * 长度
     */
    private String cardTrimLength;

    /**
     * 总卡长度
     */
    private String cardTotalLength;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardTrimValue() {
        return cardTrimValue;
    }

    public void setCardTrimValue(String cardTrimValue) {
        this.cardTrimValue = cardTrimValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardTrimLength() {
        return cardTrimLength;
    }

    public void setCardTrimLength(String cardTrimLength) {
        this.cardTrimLength = cardTrimLength;
    }

    public String getCardTotalLength() {
        return cardTotalLength;
    }

    public void setCardTotalLength(String cardTotalLength) {
        this.cardTotalLength = cardTotalLength;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", code="+ code + ", bankName="+ bankName + ", cardName="+ cardName + ", cardTrimValue="+ cardTrimValue + ", type="+ type + ", cardTrimLength="+ cardTrimLength + ", cardTotalLength="+ cardTotalLength + "]";
    }
}