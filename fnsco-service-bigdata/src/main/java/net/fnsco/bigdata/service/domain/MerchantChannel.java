package net.fnsco.bigdata.service.domain;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import net.fnsco.core.base.DTO;
/**
 * @desc 商户渠道信息实体
 * @author tangliang
 * @date 2017年6月22日 下午4:05:28
 */
public class MerchantChannel extends DTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5475717139994171135L;

	private Integer id;

    private String innerCode;
    
    private String channelType;

    private String channelMerId;

    private String channelMerKey;

    private Date createTime;

    private Date modifyTime;

    private Integer modifyUserId;
    
    private List<MerchantPos> posInfos;//POS机信息列表
  
  	private List<MerchantTerminal> terminaInfos;//关联所有终端信息
    public List<MerchantTerminal> getTerminaInfos() {
		return terminaInfos;
	}

	public void setTerminaInfos(List<MerchantTerminal> terminaInfos) {
		this.terminaInfos = terminaInfos;
	}

	private String entityInnerCode;
    
	/**
     * entityInnerCode
     *
     * @return  the entityInnerCode
     * @since   CodingExample Ver 1.0
    */
    
    public String getEntityInnerCode() {
        return entityInnerCode;
    }

    /**
     * entityInnerCode
     *
     * @param   entityInnerCode    the entityInnerCode to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setEntityInnerCode(String entityInnerCode) {
        this.entityInnerCode = entityInnerCode;
    }

    /**
     * posInfos
     *
     * @return  the posInfos
     * @since   CodingExample Ver 1.0
    */
    
    public List<MerchantPos> getPosInfos() {
        return posInfos;
    }

    /**
     * posInfos
     *
     * @param   posInfos    the posInfos to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setPosInfos(List<MerchantPos> posInfos) {
        this.posInfos = posInfos;
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

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType == null ? null : channelType.trim();
    }

    public String getChannelMerId() {
        return channelMerId;
    }

    public void setChannelMerId(String channelMerId) {
        this.channelMerId = channelMerId == null ? null : channelMerId.trim();
    }

    public String getChannelMerKey() {
        return channelMerKey;
    }

    public void setChannelMerKey(String channelMerKey) {
        this.channelMerKey = channelMerKey == null ? null : channelMerKey.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
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