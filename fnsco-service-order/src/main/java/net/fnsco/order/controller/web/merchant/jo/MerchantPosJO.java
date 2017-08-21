package net.fnsco.order.controller.web.merchant.jo;

import java.util.List;

import com.google.common.collect.Lists;

import net.fnsco.core.base.JO;
import net.fnsco.order.service.domain.MerchantPos;
import net.fnsco.order.service.domain.MerchantTerminal;

public class MerchantPosJO extends JO {
    private Integer posId;

    private String  innerCode;

    private Integer channelId;

    private String  posName;

    private String  snCode;

    private String  posFactory;

    private String  posType;

    private String  status;

    private Integer bankId;

    private String  mercReferName;

    //POS机子信息
    public MerchantPos toMerchantPos() {
        MerchantPos merchantPos = new MerchantPos();
        merchantPos.setId(posId);
        merchantPos.setPosName(posName);
        merchantPos.setSnCode(snCode);
        merchantPos.setBankId(bankId);
        merchantPos.setMercReferName(mercReferName);
        merchantPos.setInnerCode(innerCode);
        merchantPos.setChannelId(channelId);
        merchantPos.setPosType(posType);
        merchantPos.setPosFactory(posFactory);
        return merchantPos;
    }

    //终端信息
    public List<MerchantTerminal> toTerminalList() {
        List<MerchantTerminal> terminals = Lists.newArrayList();
        MerchantTerminal terminal1 = new MerchantTerminal();
        terminal1.setAlipayFee(alipayFee);
        terminal1.setWechatFee(wechatFee);
        terminal1.setTerminalCode(terminalCode1);
        terminal1.setId(terminalId1);
        terminal1.setInnerCode(innerCode);
        terminal1.setTerminalType("01");
        MerchantTerminal terminal2 = new MerchantTerminal();
        terminal2.setDebitCardMaxFee(debitCardMaxFee);
        terminal2.setCreditCardRate(creditCardRate);
        terminal2.setDebitCardRate(debitCardRate);
        terminal2.setTerminalCode(terminalCode2);
        terminal2.setTerminalType("00");
        terminal2.setId(terminalId2);
        terminal2.setInnerCode(innerCode);
        
        terminals.add(terminal1);
        terminals.add(terminal2);

        return terminals;
    }

    //////////////////////////
    private Integer terminalId1;

    private Integer terminalId2;

    private String  terminalCode1;

    private String  terminalCode2;

    private Integer debitCardMaxFee;

    private String  debitCardRate;

    private String  creditCardRate;

    private Integer wechatFee;

    private Integer alipayFee;


    /**
     * posId
     *
     * @return  the posId
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getPosId() {
        return posId;
    }

    /**
     * posId
     *
     * @param   posId    the posId to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    /**
     * innerCode
     *
     * @return  the innerCode
     * @since   CodingExample Ver 1.0
    */

    public String getInnerCode() {
        return innerCode;
    }

    /**
     * innerCode
     *
     * @param   innerCode    the innerCode to set
     * @since   CodingExample Ver 1.0
     */

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }

    /**
     * channelId
     *
     * @return  the channelId
     * @since   CodingExample Ver 1.0
    */

    public Integer getChannelId() {
        return channelId;
    }

    /**
     * channelId
     *
     * @param   channelId    the channelId to set
     * @since   CodingExample Ver 1.0
     */

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    /**
     * posName
     *
     * @return  the posName
     * @since   CodingExample Ver 1.0
    */

    public String getPosName() {
        return posName;
    }

    /**
     * posName
     *
     * @param   posName    the posName to set
     * @since   CodingExample Ver 1.0
     */

    public void setPosName(String posName) {
        this.posName = posName;
    }

    /**
     * snCode
     *
     * @return  the snCode
     * @since   CodingExample Ver 1.0
    */

    public String getSnCode() {
        return snCode;
    }

    /**
     * snCode
     *
     * @param   snCode    the snCode to set
     * @since   CodingExample Ver 1.0
     */

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    /**
     * posFactory
     *
     * @return  the posFactory
     * @since   CodingExample Ver 1.0
    */

    public String getPosFactory() {
        return posFactory;
    }

    /**
     * posFactory
     *
     * @param   posFactory    the posFactory to set
     * @since   CodingExample Ver 1.0
     */

    public void setPosFactory(String posFactory) {
        this.posFactory = posFactory;
    }

    /**
     * posType
     *
     * @return  the posType
     * @since   CodingExample Ver 1.0
    */

    public String getPosType() {
        return posType;
    }

    /**
     * posType
     *
     * @param   posType    the posType to set
     * @since   CodingExample Ver 1.0
     */

    public void setPosType(String posType) {
        this.posType = posType;
    }

    /**
     * status
     *
     * @return  the status
     * @since   CodingExample Ver 1.0
    */

    public String getStatus() {
        return status;
    }

    /**
     * status
     *
     * @param   status    the status to set
     * @since   CodingExample Ver 1.0
     */

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * bankId
     *
     * @return  the bankId
     * @since   CodingExample Ver 1.0
    */

    public Integer getBankId() {
        return bankId;
    }

    /**
     * bankId
     *
     * @param   bankId    the bankId to set
     * @since   CodingExample Ver 1.0
     */

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    /**
     * mercReferName
     *
     * @return  the mercReferName
     * @since   CodingExample Ver 1.0
    */

    public String getMercReferName() {
        return mercReferName;
    }

    /**
     * mercReferName
     *
     * @param   mercReferName    the mercReferName to set
     * @since   CodingExample Ver 1.0
     */

    public void setMercReferName(String mercReferName) {
        this.mercReferName = mercReferName;
    }

    /**
     * terminalId1
     *
     * @return  the terminalId1
     * @since   CodingExample Ver 1.0
    */

    public Integer getTerminalId1() {
        return terminalId1;
    }

    /**
     * terminalId1
     *
     * @param   terminalId1    the terminalId1 to set
     * @since   CodingExample Ver 1.0
     */

    public void setTerminalId1(Integer terminalId1) {
        this.terminalId1 = terminalId1;
    }

    /**
     * terminalId2
     *
     * @return  the terminalId2
     * @since   CodingExample Ver 1.0
    */

    public Integer getTerminalId2() {
        return terminalId2;
    }

    /**
     * terminalId2
     *
     * @param   terminalId2    the terminalId2 to set
     * @since   CodingExample Ver 1.0
     */

    public void setTerminalId2(Integer terminalId2) {
        this.terminalId2 = terminalId2;
    }

    /**
     * terminalCode1
     *
     * @return  the terminalCode1
     * @since   CodingExample Ver 1.0
    */

    public String getTerminalCode1() {
        return terminalCode1;
    }

    /**
     * terminalCode1
     *
     * @param   terminalCode1    the terminalCode1 to set
     * @since   CodingExample Ver 1.0
     */

    public void setTerminalCode1(String terminalCode1) {
        this.terminalCode1 = terminalCode1;
    }

    /**
     * terminalCode2
     *
     * @return  the terminalCode2
     * @since   CodingExample Ver 1.0
    */

    public String getTerminalCode2() {
        return terminalCode2;
    }

    /**
     * terminalCode2
     *
     * @param   terminalCode2    the terminalCode2 to set
     * @since   CodingExample Ver 1.0
     */

    public void setTerminalCode2(String terminalCode2) {
        this.terminalCode2 = terminalCode2;
    }

    /**
     * debitCardMaxFee
     *
     * @return  the debitCardMaxFee
     * @since   CodingExample Ver 1.0
    */

    public Integer getDebitCardMaxFee() {
        return debitCardMaxFee;
    }

    /**
     * debitCardMaxFee
     *
     * @param   debitCardMaxFee    the debitCardMaxFee to set
     * @since   CodingExample Ver 1.0
     */

    public void setDebitCardMaxFee(Integer debitCardMaxFee) {
        this.debitCardMaxFee = debitCardMaxFee;
    }

    /**
     * debitCardRate
     *
     * @return  the debitCardRate
     * @since   CodingExample Ver 1.0
    */

    public String getDebitCardRate() {
        return debitCardRate;
    }

    /**
     * debitCardRate
     *
     * @param   debitCardRate    the debitCardRate to set
     * @since   CodingExample Ver 1.0
     */

    public void setDebitCardRate(String debitCardRate) {
        this.debitCardRate = debitCardRate;
    }

    /**
     * creditCardRate
     *
     * @return  the creditCardRate
     * @since   CodingExample Ver 1.0
    */

    public String getCreditCardRate() {
        return creditCardRate;
    }

    /**
     * creditCardRate
     *
     * @param   creditCardRate    the creditCardRate to set
     * @since   CodingExample Ver 1.0
     */

    public void setCreditCardRate(String creditCardRate) {
        this.creditCardRate = creditCardRate;
    }

    /**
     * wechatFee
     *
     * @return  the wechatFee
     * @since   CodingExample Ver 1.0
    */

    public Integer getWechatFee() {
        return wechatFee;
    }

    /**
     * wechatFee
     *
     * @param   wechatFee    the wechatFee to set
     * @since   CodingExample Ver 1.0
     */

    public void setWechatFee(Integer wechatFee) {
        this.wechatFee = wechatFee;
    }

    /**
     * alipayFee
     *
     * @return  the alipayFee
     * @since   CodingExample Ver 1.0
    */

    public Integer getAlipayFee() {
        return alipayFee;
    }

    /**
     * alipayFee
     *
     * @param   alipayFee    the alipayFee to set
     * @since   CodingExample Ver 1.0
     */

    public void setAlipayFee(Integer alipayFee) {
        this.alipayFee = alipayFee;
    }

}
