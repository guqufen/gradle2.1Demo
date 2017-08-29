package net.fnsco.bigdata.api.dto;

import net.fnsco.core.base.DTO;

/**
 * @desc APP单个商家详情实体
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年6月29日 下午6:03:12
 *
 */
public class MerChantCoreDetailDTO extends DTO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = -8779200357318908110L;
    
    private Integer id;
    
    private String merName;
    
    private String legalPerson;
    
    private String cardNum;
    
    private String accountNo;
    
    private String openBank;
    
    private String subBankName;;
    
    /**
     * accountNo
     *
     * @return  the accountNo
     * @since   CodingExample Ver 1.0
    */
    
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * accountNo
     *
     * @param   accountNo    the accountNo to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * openBank
     *
     * @return  the openBank
     * @since   CodingExample Ver 1.0
    */
    
    public String getOpenBank() {
        return openBank;
    }

    /**
     * openBank
     *
     * @param   openBank    the openBank to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    /**
     * subBankName
     *
     * @return  the subBankName
     * @since   CodingExample Ver 1.0
    */
    
    public String getSubBankName() {
        return subBankName;
    }

    /**
     * subBankName
     *
     * @param   subBankName    the subBankName to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setSubBankName(String subBankName) {
        this.subBankName = subBankName;
    }

    /**
     * id
     *
     * @return  the id
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getId() {
        return id;
    }

    /**
     * id
     *
     * @param   id    the id to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * merName
     *
     * @return  the merName
     * @since   CodingExample Ver 1.0
    */
    
    public String getMerName() {
        return merName;
    }

    /**
     * merName
     *
     * @param   merName    the merName to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setMerName(String merName) {
        this.merName = merName;
    }

    /**
     * legalPerson
     *
     * @return  the legalPerson
     * @since   CodingExample Ver 1.0
    */
    
    public String getLegalPerson() {
        return legalPerson;
    }

    /**
     * legalPerson
     *
     * @param   legalPerson    the legalPerson to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    /**
     * cardNum
     *
     * @return  the cardNum
     * @since   CodingExample Ver 1.0
    */
    
    public String getCardNum() {
        return cardNum;
    }

    /**
     * cardNum
     *
     * @param   cardNum    the cardNum to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

}
