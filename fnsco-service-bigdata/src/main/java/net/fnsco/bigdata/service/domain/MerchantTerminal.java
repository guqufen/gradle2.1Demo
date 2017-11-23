package net.fnsco.bigdata.service.domain;

import com.alibaba.fastjson.JSONObject;

import net.fnsco.core.base.DTO;

/**
 * @desc 商家终端信息表 
 * @author tangliang
 * @date 2017年6月22日 下午3:16:09
 */
public class MerchantTerminal extends DTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1471352444275357646L;

	private Integer id;

    private String innerCode;

//    private Integer posId; 第九版需求删除

    private String terminalCode;

    private String channelTerminalCode;

//    private String terminalBatch;第九版需求删除

//    private String terminalPara;第九版需求删除

    private Integer chargesType;

    private String debitCardRate;

    private String creditCardRate;

    private Integer debitCardFee;

    private Integer creditCardFee;

    private Integer debitCardMaxFee;

    private Integer creditCardMaxFee;

//    private String dealSwitch;第九版需求删除	

//    private String recordState;第九版需求删除

//    private String termAuditState;第九版需求删除

//    private String termName;第九版需求删除

    private String wechatFee;

    private String alipayFee;

    private String terminalType;
    
    //新增终端相关字段
    private Integer subAppId;
	private String qGroupId;
    private String categroryId;
    private String settleCycle;
    private String jsapiPath;

    public Integer getSubAppId() {
  		return subAppId;
  	}

  	public void setSubAppId(Integer subAppId) {
  		this.subAppId = subAppId;
  	}

	public String getqGroupId() {
		return qGroupId;
	}

	public void setqGroupId(String qGroupId) {
		this.qGroupId = qGroupId;
	}

	public String getCategroryId() {
		return categroryId;
	}

	public void setCategroryId(String categroryId) {
		this.categroryId = categroryId;
	}

	public String getSettleCycle() {
		return settleCycle;
	}

	public void setSettleCycle(String settleCycle) {
		this.settleCycle = settleCycle;
	}

	public String getJsapiPath() {
		return jsapiPath;
	}

	public void setJsapiPath(String jsapiPath) {
		this.jsapiPath = jsapiPath;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode == null ? null : terminalCode.trim();
    }
    /**
     * channelTerminalCode
     *
     * @return  the channelTerminalCode
     * @since   CodingExample Ver 1.0
    */
    
    public String getChannelTerminalCode() {
        return channelTerminalCode;
    }

    /**
     * channelTerminalCode
     *
     * @param   channelTerminalCode    the channelTerminalCode to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setChannelTerminalCode(String channelTerminalCode) {
        this.channelTerminalCode = channelTerminalCode;
    }

    public Integer getChargesType() {
        return chargesType;
    }

    public void setChargesType(Integer chargesType) {
        this.chargesType = chargesType;
    }

    public String getDebitCardRate() {
        return debitCardRate;
    }

    public void setDebitCardRate(String debitCardRate) {
        this.debitCardRate = debitCardRate == null ? null : debitCardRate.trim();
    }

    public String getCreditCardRate() {
        return creditCardRate;
    }

    public void setCreditCardRate(String creditCardRate) {
        this.creditCardRate = creditCardRate == null ? null : creditCardRate.trim();
    }

    public Integer getDebitCardFee() {
        return debitCardFee;
    }

    public void setDebitCardFee(Integer debitCardFee) {
        this.debitCardFee = debitCardFee;
    }

    public Integer getCreditCardFee() {
        return creditCardFee;
    }

    public void setCreditCardFee(Integer creditCardFee) {
        this.creditCardFee = creditCardFee;
    }

    public Integer getDebitCardMaxFee() {
        return debitCardMaxFee;
    }

    public void setDebitCardMaxFee(Integer debitCardMaxFee) {
        this.debitCardMaxFee = debitCardMaxFee;
    }

    public Integer getCreditCardMaxFee() {
        return creditCardMaxFee;
    }

    public void setCreditCardMaxFee(Integer creditCardMaxFee) {
        this.creditCardMaxFee = creditCardMaxFee;
    }

    /**
     * wechatFee
     *
     * @return  the wechatFee
     * @since   CodingExample Ver 1.0
    */
    
    public String getWechatFee() {
        return wechatFee;
    }

    /**
     * wechatFee
     *
     * @param   wechatFee    the wechatFee to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setWechatFee(String wechatFee) {
        this.wechatFee = wechatFee;
    }

    /**
     * alipayFee
     *
     * @return  the alipayFee
     * @since   CodingExample Ver 1.0
    */
    
    public String getAlipayFee() {
        return alipayFee;
    }

    /**
     * alipayFee
     *
     * @param   alipayFee    the alipayFee to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setAlipayFee(String alipayFee) {
        this.alipayFee = alipayFee;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType == null ? null : terminalType.trim();
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return JSONObject.toJSONString(this);
    }
    
    @Override
    public int hashCode() {
    	// TODO Auto-generated method stub
    	return super.hashCode();
    }
}