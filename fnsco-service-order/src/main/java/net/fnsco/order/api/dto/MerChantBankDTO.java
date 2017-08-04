package net.fnsco.order.api.dto;

import net.fnsco.core.base.DTO;

/**
 * @desc 商户详情银行信息DTO
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年6月30日 上午10:48:08
 *
 */

public class MerChantBankDTO extends DTO{
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = 7998043269521380396L;
    
    private String accountNo;
    
    private String openBank;
    
    private String openBankNum;

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
     * openBankNum
     *
     * @return  the openBankNum
     * @since   CodingExample Ver 1.0
    */
    
    public String getOpenBankNum() {
        return openBankNum;
    }

    /**
     * openBankNum
     *
     * @param   openBankNum    the openBankNum to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setOpenBankNum(String openBankNum) {
        this.openBankNum = openBankNum;
    }
    
}
