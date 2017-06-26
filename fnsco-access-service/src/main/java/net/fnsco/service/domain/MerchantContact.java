package net.fnsco.service.domain;

import com.alibaba.fastjson.JSONObject;

import net.fnsco.core.base.DTO;

/**
 * @desc 商家联系人信息实体
 * @author tangliang
 * @date 2017年6月21日 下午2:33:25
 */
public class MerchantContact extends DTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8307574239429757415L;

	private Integer id;

    private String innerCode;

    private String contactName;

    private String contactMobile;

    private String contactEmail;

    private String contactTelphone;

    private String contactJobs;

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

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile == null ? null : contactMobile.trim();
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail == null ? null : contactEmail.trim();
    }

    public String getContactTelphone() {
        return contactTelphone;
    }

    public void setContactTelphone(String contactTelphone) {
        this.contactTelphone = contactTelphone == null ? null : contactTelphone.trim();
    }

    public String getContactJobs() {
        return contactJobs;
    }

    public void setContactJobs(String contactJobs) {
        this.contactJobs = contactJobs == null ? null : contactJobs.trim();
    }
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return JSONObject.toJSONString(this);
    }
    
    /**
     * @todo toString和hashCode重写
     * @author tangliang
     * @date 2017年6月21日 下午2:44:21
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
    	int result = 17;  
        result = result * 31 + contactName.hashCode();  
        result = result * 31 + id;  
        return result;  
    }
}