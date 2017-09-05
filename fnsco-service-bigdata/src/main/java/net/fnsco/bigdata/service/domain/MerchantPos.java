package net.fnsco.bigdata.service.domain;

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
    
    private String mercReferName;
    
    private String posAddr;
    
    private Integer posProvince;
    
    private Integer posCity;
    
    private Integer posArea;
    
	private List<MerchantTerminal> terminal;//POS机下终端信息
    
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
    public String getPosAddr() {
		return posAddr;
	}

	public void setPosAddr(String posAddr) {
		this.posAddr = posAddr;
	}

	public Integer getPosProvince() {
		return posProvince;
	}

	public void setPosProvince(Integer posProvince) {
		this.posProvince = posProvince;
	}

	public Integer getPosCity() {
		return posCity;
	}

	public void setPosCity(Integer posCity) {
		this.posCity = posCity;
	}

	public Integer getPosArea() {
		return posArea;
	}

	public void setPosArea(Integer posArea) {
		this.posArea = posArea;
	}

}