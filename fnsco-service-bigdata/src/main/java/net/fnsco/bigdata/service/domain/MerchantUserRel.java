package net.fnsco.bigdata.service.domain;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

import net.fnsco.core.base.DTO;
/**
 * @desc 商家和APP用户关系表
 * @author tangliang
 * @date 2017年6月22日 下午3:57:45
 */
public class MerchantUserRel extends DTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6724885733786627450L;

	private Integer id;

    private String innerCode;
    
    private String entityInnerCode;//实体商户号

    private Integer appUserId;

    private Date modefyTime;

    public String getEntityInnerCode() {
		return entityInnerCode;
	}

	public void setEntityInnerCode(String entityInnerCode) {
		this.entityInnerCode = entityInnerCode;
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

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public Date getModefyTime() {
        return modefyTime;
    }

    public void setModefyTime(Date modefyTime) {
        this.modefyTime = modefyTime;
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