package net.fnsco.order.service.domain;

import java.util.List;

/**
 * @desc POS机信息实体
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月16日 上午10:38:19
 */
public class MerchantPos {
    private Integer id;

    private String innerCode;

    private Integer channelId;

    private String posName;

    private String snCode;

    private String posFactory;

    private String posType;

    private String status;
    
    private Integer bankId;
    
    private List<MerchantTerminal> terminal;//POS机下终端信息
    
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
     * terminal
     *
     * @return  the terminal
     * @since   CodingExample Ver 1.0
    */
    
    public List<MerchantTerminal> getTerminal() {
        return terminal;
    }

    /**
     * terminal
     *
     * @param   terminal    the terminal to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTerminal(List<MerchantTerminal> terminal) {
        this.terminal = terminal;
    }

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
        this.innerCode = innerCode == null ? null : innerCode.trim();
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName == null ? null : posName.trim();
    }

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode == null ? null : snCode.trim();
    }

    public String getPosFactory() {
        return posFactory;
    }

    public void setPosFactory(String posFactory) {
        this.posFactory = posFactory == null ? null : posFactory.trim();
    }

    public String getPosType() {
        return posType;
    }

    public void setPosType(String posType) {
        this.posType = posType == null ? null : posType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}