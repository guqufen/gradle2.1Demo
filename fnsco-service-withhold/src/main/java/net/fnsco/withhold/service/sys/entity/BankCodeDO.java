package net.fnsco.withhold.service.sys.entity;

public class BankCodeDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 机构代码
     */
    private String  code;

    /**
     * 发卡行名称
     */
    private String  bankName;

    /**
     * 卡名
     */
    private String  cardName;

    /**
     * 主账号取值
     */
    private String  cardTrimValue;

    /**
     * 卡种(DC:借记卡、CC：贷记卡、 SCC：准贷记卡、 PC：预付费卡、unK：其他)
     */
    private String  type;

    /**
     * 长度
     */
    private Integer cardTrimLength;

    /**
     * 总卡长度
     */
    private Integer cardTotalLength;

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

    /**
     * cardTrimLength
     *
     * @return  the cardTrimLength
     * @since   CodingExample Ver 1.0
    */

    public Integer getCardTrimLength() {
        return cardTrimLength;
    }

    /**
     * cardTrimLength
     *
     * @param   cardTrimLength    the cardTrimLength to set
     * @since   CodingExample Ver 1.0
     */

    public void setCardTrimLength(Integer cardTrimLength) {
        this.cardTrimLength = cardTrimLength;
    }

    /**
     * cardTotalLength
     *
     * @return  the cardTotalLength
     * @since   CodingExample Ver 1.0
    */

    public Integer getCardTotalLength() {
        return cardTotalLength;
    }

    /**
     * cardTotalLength
     *
     * @param   cardTotalLength    the cardTotalLength to set
     * @since   CodingExample Ver 1.0
     */

    public void setCardTotalLength(Integer cardTotalLength) {
        this.cardTotalLength = cardTotalLength;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", code=" + code + ", bankName=" + bankName + ", cardName=" + cardName + ", cardTrimValue=" + cardTrimValue + ", type=" + type + ", cardTrimLength=" + cardTrimLength
               + ", cardTotalLength=" + cardTotalLength + "]";
    }
}