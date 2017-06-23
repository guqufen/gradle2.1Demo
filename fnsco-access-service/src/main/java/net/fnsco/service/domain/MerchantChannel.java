package net.fnsco.service.domain;

import java.util.Date;

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
    
    private Integer agentId;
    
    private String channelType;

    private String channelMerId;

    private String channelMerKey;

    private Date createTime;

    private Date modifyTime;

    private Integer modifyUserId;
    
    
    public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
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